package com.dev.course.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dev.course.domain.Client;
import com.dev.course.domain.ItemRequest;
import com.dev.course.domain.PaymentWithBoleto;
import com.dev.course.domain.Request;
import com.dev.course.domain.enums.StatusPayment;
import com.dev.course.repositories.ItemRequestRepository;
import com.dev.course.repositories.PaymentRepository;
import com.dev.course.repositories.RequestRepository;
import com.dev.course.security.UserSS;
import com.dev.course.services.exceptions.AuthorizationException;
import com.dev.course.services.exceptions.ObjectNotFoundException;

@Service
public class RequestService {
	@Autowired
	private RequestRepository repo;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private ItemRequestRepository itemRequestRepository;
	@Autowired
	private ClientService clientService;
	@Autowired
	private EmailService emailService;
	
	public Request findById(Integer id) {
		Optional<Request> req = repo.findById(id);
		return req.orElseThrow(
				() -> new ObjectNotFoundException("Not found id: " + id + " type: " + Request.class.getSimpleName()));
	}
	
	@Transactional
	public Request insert(Request obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientService.find(obj.getClient().getId()));
		obj.getPayment().setStatus(StatusPayment.PENDING);
		obj.getPayment().setRequest(obj);
		if (obj.getPayment() instanceof PaymentWithBoleto) {
			PaymentWithBoleto pagto = (PaymentWithBoleto) obj.getPayment();
			boletoService.fillPaymentoWithBoleto(pagto, obj.getInstant());
		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (ItemRequest ip : obj.getItens()) {
			ip.setDiscount(0.0);
			ip.setProduct(productService.findById(ip.getProduct().getId()));
			ip.setPrice(ip.getProduct().getPrice());
			ip.setRequest(obj);
		}
		itemRequestRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}

	public Page<Request> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso negago");
		}
		Client client = clientService.find(user.getId());
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return repo.findByClient(client, pageRequest);
	}
}
