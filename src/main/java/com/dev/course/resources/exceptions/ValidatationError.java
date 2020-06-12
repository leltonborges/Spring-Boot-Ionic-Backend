package com.dev.course.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidatationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<FieldMessage>();

	public ValidatationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}
	public void addError(String fieldName, String error) {
		errors.add(new FieldMessage(fieldName, error));
	}

}
