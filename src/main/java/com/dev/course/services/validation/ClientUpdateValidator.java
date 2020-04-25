package com.dev.course.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.dev.course.domain.Client;
import com.dev.course.dto.ClientDTO;
import com.dev.course.repositories.ClientRepository;
import com.dev.course.resources.exceptions.FieldMessege;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClientUpdate ann) {
	}

	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessege> list = new ArrayList<>();
		
		Client aux = clientRepository.findByEmail(objDto.getEmail());
		if(aux != null && !aux.getId().equals(uriId)) {
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