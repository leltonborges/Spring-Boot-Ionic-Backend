package com.dev.course.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Request implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
	private Date instant;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "request")
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "ADDRESS_DELIVERY_ID")
	private Address AddressDelivery;
	
	@OneToMany(mappedBy = "id.request")
	private Set<ItemRequest> itens = new HashSet<ItemRequest>();
	
	public Request() {
	}

	public Request(Integer id, Date instant, Client client, Address addressDelivery) {
		super();
		this.id = id;
		this.instant = instant;
		this.client = client;
		AddressDelivery = addressDelivery;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getAddressDelivery() {
		return AddressDelivery;
	}

	public void setAddressDelivery(Address addressDelivery) {
		AddressDelivery = addressDelivery;
	}

	public Set<ItemRequest> getItens() {
		return itens;
	}

	public void setItens(Set<ItemRequest> itens) {
		this.itens = itens;
	}
	
	public Double getValorTotal() {
		double som = 0.0;
		for(ItemRequest item : itens) {
			som += item.getSubTotal();
		}
		return som;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
