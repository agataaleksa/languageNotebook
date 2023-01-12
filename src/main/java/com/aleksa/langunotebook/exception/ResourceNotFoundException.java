package com.aleksa.langunotebook.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException() {
		super("Object does not exist in the database.");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	

}
