package com.dev.course.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidatationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessege> messeges = new ArrayList<FieldMessege>();

	public ValidatationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessege> getErrors() {
		return messeges;
	}
	public void addError(String fieldName, String error) {
		messeges.add(new FieldMessege(fieldName, error));
	}

}
