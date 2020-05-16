package com.dev.course.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidatationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessege> messeges = new ArrayList<FieldMessege>();

	public ValidatationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessege> getErrors() {
		return messeges;
	}
	public void addError(String fieldName, String error) {
		messeges.add(new FieldMessege(fieldName, error));
	}

}
