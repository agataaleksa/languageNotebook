package com.aleksa.langunotebook.exception;

public class TranslationNotFoundException extends RuntimeException {
	
	public TranslationNotFoundException() {
		super("No translation found.");
	}

}
