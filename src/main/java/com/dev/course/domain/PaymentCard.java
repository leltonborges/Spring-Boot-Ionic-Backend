package com.dev.course.domain;

import javax.persistence.Entity;

import com.dev.course.domain.enums.StatusPayment;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("PaymentCard")
public class PaymentCard extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer numberPlots;

	public PaymentCard() {
	}

	public PaymentCard(Integer id, StatusPayment status, Request request, Integer numberPlots) {
		super(id, status, request);
		this.numberPlots = numberPlots;
	}

	public Integer getNumberPlots() {
		return numberPlots;
	}

	public void setNumberPlots(Integer numberPlots) {
		this.numberPlots = numberPlots;
	}
}
