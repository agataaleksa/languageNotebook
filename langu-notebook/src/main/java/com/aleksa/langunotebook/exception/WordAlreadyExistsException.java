package com.aleksa.langunotebook.exception;

public class WordAlreadyExistsException extends RuntimeException {

	public WordAlreadyExistsException() {
		super("Word already exists in the database.");
	}
}
