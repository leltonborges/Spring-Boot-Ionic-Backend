package com.dev.course.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.course.domain.Address;
import com.dev.course.domain.Category;
import com.dev.course.domain.City;
import com.dev.course.domain.Client;
import com.dev.course.domain.ItemRequest;
import com.dev.course.domain.Payment;
import com.dev.course.domain.PaymentCard;
import com.dev.course.domain.PaymentWithBoleto;
import com.dev.course.domain.Product;
import com.dev.course.domain.Request;
import com.dev.course.domain.State;
import com.dev.course.domain.enums.StatusPayment;
import com.dev.course.domain.enums.TypeClient;
import com.dev.course.repositories.AddressRepository;
import com.dev.course.repositories.CategoryRepository;
import com.dev.course.repositories.CityRepository;
import com.dev.course.repositories.ClientRepository;
import com.dev.course.repositories.ItemRequestRepository;
import com.dev.course.repositories.PaymentRepository;
import com.dev.course.repositories.ProductRepository;
import com.dev.course.repositories.RequestRepository;
import com.dev.course.repositories.StateRepository;

@Service
public class DBService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ItemRequestRepository itemRequestRepository;

	public void instatiateTestDatabase() throws ParseException {

		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Data");
		Category cat4 = new Category(null, "Build");
		Category cat5 = new Category(null, "Full Stack");
		Category cat6 = new Category(null, "Books");
		Category cat7 = new Category(null, "Informática");
		Category cat8 = new Category(null, "Escritório");
		Category cat9 = new Category(null, "Data");
		Category cat10 = new Category(null, "Build");
		Category cat11 = new Category(null, "Full Stack");
		Category cat12 = new Category(null, "Books");

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "mesa de escritorio", 3000.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 12000.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);
		Product p12 = new Product(null, "Caneta", 3.80);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10, p12));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1));
		p4.getCategories().addAll(Arrays.asList(cat4));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat10));
		p11.getCategories().addAll(Arrays.asList(cat7));
		p12.getCategories().addAll(Arrays.asList(cat6));

		categoryRepository
				.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São paulo");

		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);

		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Amanda", "amanda@gmail.com", "097.560.864-08", TypeClient.PESSOA_FISICA);
		cli1.getPhones().addAll(Arrays.asList("86464-4646", "94654-5512"));

		Address add1 = new Address(null, "Rua Lores", "300", "Apto 302", "Jardim", "8465-786", cli1, c1);
		Address add2 = new Address(null, "Avenida Matos", "105", "Sala 808", "Centro", "3168-653", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(add1, add2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(add1, add2));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Request rq1 = new Request(null, sdf.parse("2019/12/12 11:34"), cli1, add1);
		Request rq2 = new Request(null, sdf.parse("2020/03/17 10:45"), cli1, add2);

		Payment pay1 = new PaymentCard(null, StatusPayment.SETTLED, rq1, 3);
		rq1.setPayment(pay1);

		Payment pay2 = new PaymentWithBoleto(null, StatusPayment.PENDING, rq2, sdf.parse("2020/01/10 12:30"), null);
		rq2.setPayment(pay2);
		cli1.getRequests().addAll(Arrays.asList(rq1, rq2));

		requestRepository.saveAll(Arrays.asList(rq1, rq2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));

		ItemRequest ir1 = new ItemRequest(rq1, p1, 0.00, 1, 2000.00);
		ItemRequest ir2 = new ItemRequest(rq1, p3, 0.00, 2, 80.00);
		ItemRequest ir3 = new ItemRequest(rq2, p2, 100.00, 1, 800.00);

		rq1.getItens().addAll(Arrays.asList(ir1, ir2));
		rq2.getItens().addAll(Arrays.asList(ir3));

		p1.getItens().addAll(Arrays.asList(ir1));
		p2.getItens().addAll(Arrays.asList(ir3));
		p3.getItens().addAll(Arrays.asList(ir2));

		itemRequestRepository.saveAll(Arrays.asList(ir1, ir2, ir3));
	}
}
