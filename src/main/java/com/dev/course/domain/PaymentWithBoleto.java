package com.dev.course.domain;
import java.util.Date;

import javax.persistence.Entity;

import com.dev.course.domain.enums.StatusPayment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("PaymentWithBoleto")
public class PaymentWithBoleto extends Payment{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
	private Date dateExpiration;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
	private Date datePaymento;
	
	public PaymentWithBoleto() {
	}

	public PaymentWithBoleto(Integer id, StatusPayment status, Request request, Date dateExpiration, Date datePayment) {
		super(id, status, request);
		this.dateExpiration = dateExpiration;
		this.datePaymento = datePayment;
	}

	public Date getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public Date getDatePaymento() {
		return datePaymento;
	}

	public void setDatePaymento(Date datePaymento) {
		this.datePaymento = datePaymento;
	}
}
