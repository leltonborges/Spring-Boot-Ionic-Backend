package com.dev.course.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.dev.course.domain.enums.Profile;
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
	@Autowired
	private BCryptPasswordEncoder passDecoder;

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
		
		Product p13 = new Product(null, "Prod3to 13", 2000.00);
		Product p14 = new Product(null, "Produto 14", 800.00);
		Product p15 = new Product(null, "Produto 15", 80.00);
		Product p16 = new Product(null, "Produto 16", 3000.00);
		Product p17 = new Product(null, "Produto 17", 50.00);
		Product p18 = new Product(null, "Produto 18", 200.00);
		Product p19 = new Product(null, "Produto 19", 12000.00);
		Product p20 = new Product(null, "Produto 20", 800.00);
		Product p21 = new Product(null, "Produto 21", 100.00);
		Product p22 = new Product(null, "Produto 22", 180.00);
		Product p23 = new Product(null, "Produto 23", 90.00);
		Product p24 = new Product(null, "Produto 24", 3.80);
		Product p25 = new Product(null, "Produto 25", 50.00);
		Product p26 = new Product(null, "Produto 26", 200.00);
		Product p27 = new Product(null, "Produto 27", 12000.00);
		Product p28 = new Product(null, "Produto 28", 800.00);
		Product p29 = new Product(null, "Produto 29", 100.00);
		Product p30 = new Product(null, "Produto 30", 180.00);
		Product p31 = new Product(null, "Produto 31", 90.00);
		Product p32 = new Product(null, "Produto 32", 3.80);
		Product p33 = new Product(null, "Produto 33", 2000.00);
		Product p34 = new Product(null, "Produto 34", 800.00);
		Product p35 = new Product(null, "Produto 35", 80.00);
		Product p36 = new Product(null, "Produto 36", 3000.00);
		Product p37 = new Product(null, "Produto 37", 50.00);
		Product p38 = new Product(null, "Produto 38", 200.00);
		Product p39 = new Product(null, "Produto 39", 12000.00);
		Product p40 = new Product(null, "Produto 40", 800.00);
		Product p41 = new Product(null, "Produto 41", 100.00);
		Product p42 = new Product(null, "Produto 42", 180.00);
		Product p43 = new Product(null, "Produto 43", 90.00);
		Product p44 = new Product(null, "Produto 44", 3.80);
		Product p45 = new Product(null, "Produto 45", 50.00);
		Product p46 = new Product(null, "Produto 46", 200.00);
		Product p47 = new Product(null, "Produto 47", 12000.00);
		Product p48 = new Product(null, "Produto 48", 800.00);
		Product p49 = new Product(null, "Produto 49", 100.00);
		Product p50 = new Product(null, "Produto 50", 180.00);
		Product p51 = new Product(null, "Produto 51", 90.00);
		Product p52 = new Product(null, "Produto 52", 3.80);
		Product p53 = new Product(null, "Produto 53", 2000.00);
		Product p54 = new Product(null, "Produto 54", 800.00);
		Product p55 = new Product(null, "Produto 55", 80.00);
		Product p56 = new Product(null, "Produto 56", 3000.00);
		Product p57 = new Product(null, "Produto 57", 50.00);
		Product p58 = new Product(null, "Produto 58", 200.00);
		Product p59 = new Product(null, "Produto 59", 12000.00);
		Product p60 = new Product(null, "Produto 60", 800.00);
		Product p61 = new Product(null, "Produto 61", 100.00);
		Product p62 = new Product(null, "Produto 62", 180.00);
		Product p63 = new Product(null, "Produto 63", 90.00);
		Product p64 = new Product(null, "Produto 64", 3.80);
		Product p65 = new Product(null, "Produto 65", 50.00);
		Product p66 = new Product(null, "Produto 66", 200.00);
		Product p67 = new Product(null, "Produto 67", 12000.00);
		Product p68 = new Product(null, "Produto 68", 800.00);
		Product p69 = new Product(null, "Produto 69", 100.00);
		Product p70 = new Product(null, "Produto 70", 180.00);
		Product p71 = new Product(null, "Produto 71", 90.00);
		Product p72 = new Product(null, "Produto 72", 3.80);
		Product p73 = new Product(null, "Produto 73", 2000.00);
		Product p74 = new Product(null, "Produto 74", 800.00);
		Product p75 = new Product(null, "Produto 75", 80.00);
		Product p76 = new Product(null, "Produto 76", 3000.00);
		Product p77 = new Product(null, "Produto 77", 50.00);
		Product p78 = new Product(null, "Produto 78", 200.00);
		Product p79 = new Product(null, "Produto 79", 12000.00);
		Product p80 = new Product(null, "Produto 80", 800.00);

		cat1.getProdutos()
				.addAll(Arrays.asList(p1, p2, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27,
						p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46,
						p47, p48, p49, p50, p51, p52, p53, p54, p55, p56, p57, p58, p59, p60, p61, p62, p63, p64, p65,
						p66, p67, p68, p69, p70, p71, p72, p73, p74, p75, p76, p77, p78, p79, p80));
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

		p13.getCategories().addAll(Arrays.asList(cat1));
		p14.getCategories().addAll(Arrays.asList(cat1));
		p15.getCategories().addAll(Arrays.asList(cat1));
		p16.getCategories().addAll(Arrays.asList(cat1));
		p17.getCategories().addAll(Arrays.asList(cat1));
		p18.getCategories().addAll(Arrays.asList(cat1));
		p19.getCategories().addAll(Arrays.asList(cat1));
		p20.getCategories().addAll(Arrays.asList(cat1));
		p21.getCategories().addAll(Arrays.asList(cat1));
		p22.getCategories().addAll(Arrays.asList(cat1));
		p23.getCategories().addAll(Arrays.asList(cat1));
		p24.getCategories().addAll(Arrays.asList(cat1));
		p25.getCategories().addAll(Arrays.asList(cat1));
		p26.getCategories().addAll(Arrays.asList(cat1));
		p27.getCategories().addAll(Arrays.asList(cat1));
		p28.getCategories().addAll(Arrays.asList(cat1));
		p29.getCategories().addAll(Arrays.asList(cat1));
		p30.getCategories().addAll(Arrays.asList(cat1));
		p31.getCategories().addAll(Arrays.asList(cat1));
		p32.getCategories().addAll(Arrays.asList(cat1));
		p33.getCategories().addAll(Arrays.asList(cat1));
		p34.getCategories().addAll(Arrays.asList(cat1));
		p35.getCategories().addAll(Arrays.asList(cat1));
		p36.getCategories().addAll(Arrays.asList(cat1));
		p37.getCategories().addAll(Arrays.asList(cat1));
		p38.getCategories().addAll(Arrays.asList(cat1));
		p39.getCategories().addAll(Arrays.asList(cat1));
		p40.getCategories().addAll(Arrays.asList(cat1));
		p41.getCategories().addAll(Arrays.asList(cat1));
		p42.getCategories().addAll(Arrays.asList(cat1));
		p43.getCategories().addAll(Arrays.asList(cat1));
		p44.getCategories().addAll(Arrays.asList(cat1));
		p45.getCategories().addAll(Arrays.asList(cat1));
		p46.getCategories().addAll(Arrays.asList(cat1));
		p47.getCategories().addAll(Arrays.asList(cat1));
		p48.getCategories().addAll(Arrays.asList(cat1));
		p49.getCategories().addAll(Arrays.asList(cat1));
		p50.getCategories().addAll(Arrays.asList(cat1));
		p51.getCategories().addAll(Arrays.asList(cat1));
		p52.getCategories().addAll(Arrays.asList(cat1));
		p53.getCategories().addAll(Arrays.asList(cat1));
		p54.getCategories().addAll(Arrays.asList(cat1));
		p55.getCategories().addAll(Arrays.asList(cat1));
		p56.getCategories().addAll(Arrays.asList(cat1));
		p57.getCategories().addAll(Arrays.asList(cat1));
		p58.getCategories().addAll(Arrays.asList(cat1));
		p59.getCategories().addAll(Arrays.asList(cat1));
		p60.getCategories().addAll(Arrays.asList(cat1));
		p61.getCategories().addAll(Arrays.asList(cat1));
		p62.getCategories().addAll(Arrays.asList(cat1));
		p63.getCategories().addAll(Arrays.asList(cat1));
		p64.getCategories().addAll(Arrays.asList(cat1));
		p65.getCategories().addAll(Arrays.asList(cat1));
		p66.getCategories().addAll(Arrays.asList(cat1));
		p67.getCategories().addAll(Arrays.asList(cat1));
		p68.getCategories().addAll(Arrays.asList(cat1));
		p69.getCategories().addAll(Arrays.asList(cat1));
		p70.getCategories().addAll(Arrays.asList(cat1));
		p71.getCategories().addAll(Arrays.asList(cat1));
		p72.getCategories().addAll(Arrays.asList(cat1));
		p73.getCategories().addAll(Arrays.asList(cat1));
		p74.getCategories().addAll(Arrays.asList(cat1));
		p75.getCategories().addAll(Arrays.asList(cat1));
		p76.getCategories().addAll(Arrays.asList(cat1));
		p77.getCategories().addAll(Arrays.asList(cat1));
		p78.getCategories().addAll(Arrays.asList(cat1));
		p79.getCategories().addAll(Arrays.asList(cat1));
		p80.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository
				.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		productRepository.saveAll(Arrays.asList(p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26,
				p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48,
				p49, p50, p51, p52, p54, p55, p56, p57, p58, p59, p60, p61, p62, p63, p64, p65, p66, p67, p68, p69, p70,
				p71, p72, p73, p74, p75, p76, p77, p78, p79, p80));

		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São paulo");

		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);

		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Amanda", "lelltwnpereira@gmail.com", "097.560.864-08", TypeClient.PESSOA_FISICA,
				passDecoder.encode("123"));
		cli1.getPhones().addAll(Arrays.asList("86464-4646", "94654-5512"));

		Client cli2 = new Client(null, "Ana", "lelton.borges1@gmail.com", "418.464.570-41", TypeClient.PESSOA_FISICA,
				passDecoder.encode("123"));
		cli2.addProfile(Profile.ADMIN);
		cli2.getPhones().addAll(Arrays.asList("8532-4276", "93464-7524"));

		Address add1 = new Address(null, "Rua Lores", "300", "Apto 302", "Jardim", "8465-786", cli1, c1);
		Address add2 = new Address(null, "Avenida Matos", "105", "Sala 808", "Centro", "3168-653", cli1, c2);
		Address add3 = new Address(null, "QS 2", "402", "Apto 302", "Sul", "8465-786", cli2, c2);

		cli1.getAddresses().addAll(Arrays.asList(add1, add2));
		cli2.getAddresses().addAll(Arrays.asList(add3));

		clientRepository.saveAll(Arrays.asList(cli1, cli2));
		addressRepository.saveAll(Arrays.asList(add1, add2, add3));

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
