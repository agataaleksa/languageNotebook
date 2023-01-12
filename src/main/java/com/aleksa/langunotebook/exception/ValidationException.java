package com.aleksa.langunotebook.exception;

public class ValidationException extends RuntimeException {
	
	private ValidationMessage validationMessage;
	
	public ValidationException(String message, ValidationMessage validationMessage) {
		super(message);
		this.validationMessage = validationMessage;
	}

}
