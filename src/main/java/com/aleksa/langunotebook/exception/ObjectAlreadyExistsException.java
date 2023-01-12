package com.aleksa.langunotebook.exception;

public class ObjectAlreadyExistsException extends ValidationException {

	public ObjectAlreadyExistsException(String message, ValidationMessage validationMessage) {
		super(message, validationMessage);
	}
}
