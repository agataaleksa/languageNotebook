package com.aleksa.langunotebook.exception;

public class ValidationException extends RuntimeException {

    public ValidationException() {
		super("Object already exists in the database.");
	}
    
    public ValidationException(int id) {
		super("Object with an ID = " + id + " already exists in the database.");
	}
	
	public ValidationException(String message) {
		super(message);
	}
	
}
