package com.dev.course.resources.exceptions;

import java.io.Serializable;

public class FieldMessege implements Serializable{
	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String messege;
	
	public FieldMessege() {
	}

	public FieldMessege(String fieldName, String messege) {
		this.fieldName = fieldName;
		this.messege = messege;
	}

	public String getFielName() {
		return fieldName;
	}

	public void setFielName(String fielName) {
		this.fieldName = fielName;
	}

	public String getMessege() {
		return messege;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}
}
