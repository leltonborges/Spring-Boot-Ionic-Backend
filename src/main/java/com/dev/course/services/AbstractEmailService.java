package com.dev.course.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.dev.course.domain.Client;
import com.dev.course.domain.Request;

public abstract class AbstractEmailService implements EmailService{ 

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Request obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromRequest(obj);
		sendEmail(sm);
		
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromRequest(Request obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Codigo: "+ obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
	@Override
	public void sendNewPasswordEmail(Client obj, String password) {
		SimpleMailMessage sm = prepareNewPasswordMail(obj, password);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordMail(Client obj, String password) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: "+ password);
		return sm;
	}
}
