package com.dev.course.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.dev.course.domain.Client;
import com.dev.course.services.validation.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;

	@NotEmpty(message = "Campo obrigatório")
	@Length(min = 5, max = 120, message = "Campo de 5 à 120 caracteres")
	private String name;
	
	@Email(message = "Email invalido")
	@NotEmpty(message = "Campo obrigatório")
	private String email;
	
	public ClientDTO() {
	}

	public ClientDTO(Integer id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public ClientDTO(Client cli) {
		id = cli.getId();
		name = cli.getName();
		email = cli.getEmail();
	}
}
