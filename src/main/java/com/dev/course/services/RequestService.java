package com.dev.course.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.course.domain.ItemRequest;
import com.dev.course.domain.PaymentWithBoleto;
import com.dev.course.domain.Product;
import com.dev.course.domain.Request;
import com.dev.course.domain.enums.StatusPayment;
import com.dev.course.repositories.ItemRequestRepository;
import com.dev.course.repositories.PaymentRepository;
import com.dev.course.repositories.ProductRepository;
import com.dev.course.repositories.RequestRepository;
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
	
	public Request findById(Integer id) {
		Optional<Request> req = repo.findById(id);
		return req.orElseThrow(
				() -> new ObjectNotFoundException("Not found id: " + id + " type: " + Request.class.getSimpleName()));
	}
	
	@Transactional
	public Request insert(Request obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.getPayment().setStatus(StatusPayment.PENDING);
		obj.getPayment().setRequest(obj);
		if(obj.getPayment() instanceof PaymentWithBoleto) {
			PaymentWithBoleto pay = (PaymentWithBoleto) obj.getPayment();
			boletoService.fillPaymentoWithBoleto(pay, obj.getInstant());
		}
		obj =repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for(ItemRequest ir : obj.getItens()) {
			ir.setDiscount(0.0);
			ir.setPrice(productService.findById(ir.getProduct().getId()).getPrice());
			ir.setRequest(obj);
		}
		itemRequestRepository.saveAll(obj.getItens());
		return obj;
	}

}
