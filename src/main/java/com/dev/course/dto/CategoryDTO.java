package com.dev.course.dto;

import java.io.Serializable;

import com.dev.course.domain.Category;

public class CategoryDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	
	public CategoryDTO() {
	}

	public CategoryDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public CategoryDTO(Category obj) {
		id = obj.getId();
		name = obj.getName();
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
