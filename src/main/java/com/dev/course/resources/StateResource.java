package com.dev.course.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.course.domain.City;
import com.dev.course.domain.State;
import com.dev.course.dto.CityDTO;
import com.dev.course.dto.StateDTO;
import com.dev.course.services.CityService;
import com.dev.course.services.StateService;

@RestController
@RequestMapping(value = "/states")
public class StateResource {
	@Autowired
	private StateService service;
	@Autowired
	private CityService cityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StateDTO>> findAll(){
		List<State> list = service.findAll();
		List<StateDTO> listDTO = list.stream().map(x -> new StateDTO(x)).collect(Collectors.toList());		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{stateId}/cities")
	public ResponseEntity<List<CityDTO>> findStateByCity(@PathVariable Integer stateId){
		List<City> list = cityService.findByState(stateId);
		List<CityDTO> listDTO = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
