package com.dev.course.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev.course.domain.Client;
import com.dev.course.domain.enums.TypeClient;
import com.dev.course.dto.ClientNewDTO;
import com.dev.course.repositories.ClientRepository;
import com.dev.course.resources.exceptions.FieldMessege;
import com.dev.course.services.validation.util.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessege> list = new ArrayList<>();
		if(objDto.getTipo().equals(TypeClient.PESSOA_FISICA.getCode()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessege("CpfOuCnpj", "CPF inválido"));
		}
		if(objDto.getTipo().equals(TypeClient.PESSOA_JURIDICA.getCode()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessege("CpfOuCnpj", "CNPJ inválido"));
		}
		
		Client aux = clientRepository.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessege("email", "Email já existente"));
		}
		
		for (FieldMessege e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessege()).addPropertyNode(e.getFielName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}