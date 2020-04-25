package com.dev.course.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.course.domain.Request;
import com.dev.course.services.RequestService;

@RestController
@RequestMapping(value = "/requests")
public class RequestResource {

	@Autowired
	private RequestService service;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Request> findById(@PathVariable Integer id) {
		Request prod = service.findById(id);
		return ResponseEntity.ok().body(prod);
	}

}
