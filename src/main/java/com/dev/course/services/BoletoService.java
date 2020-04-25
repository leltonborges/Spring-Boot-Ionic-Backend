package com.dev.course.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.dev.course.domain.PaymentWithBoleto;

@Service
public class BoletoService {

	public void fillPaymentoWithBoleto(PaymentWithBoleto pay, Date instant) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pay.setDateExpiration(cal.getTime());
	}
}
