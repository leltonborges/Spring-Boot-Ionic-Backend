package com.dev.course.dto;

import java.io.Serializable;

import com.dev.course.domain.State;

public class StateDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	
	public StateDTO() {
	}

	public StateDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public StateDTO(State obj) {
		this.id=obj.getId();
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
