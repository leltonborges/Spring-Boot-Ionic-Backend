package com.dev.course.dto;

import com.dev.course.domain.City;

public class CityDTO {

	private Integer id;
	private String name;
	
	public CityDTO() {
	}

	public CityDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CityDTO(City obj) {
		this.id = obj.getId();
		this.name = obj.getName();
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
}
