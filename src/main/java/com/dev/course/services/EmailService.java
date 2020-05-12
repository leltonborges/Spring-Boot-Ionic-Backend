package com.dev.course.services;

import org.springframework.mail.SimpleMailMessage;

import com.dev.course.domain.Client;
import com.dev.course.domain.Request;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Request obj);
	void sendEmail(SimpleMailMessage msg);
	void sendNewPasswordEmail(Client obj, String password);

}
