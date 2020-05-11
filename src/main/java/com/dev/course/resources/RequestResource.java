package com.dev.course.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Request> insert(@Valid @RequestBody Request obj){
		Request req = service.insert(obj);
		URI uriReq = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(req.getId()).toUri();
		return ResponseEntity.created(uriReq).build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Request>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
												@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
												@RequestParam(value = "orderBy", defaultValue = "instant") String orderBy,
												@RequestParam(value = "direction", defaultValue = "DESC") String direction){
		Page<Request> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
}
