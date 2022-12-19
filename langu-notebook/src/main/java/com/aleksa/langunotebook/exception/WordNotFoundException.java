package com.aleksa.langunotebook.exception;

public class WordNotFoundException extends RuntimeException{
	
	public WordNotFoundException() {
		super("Word does not exist in the database.");
	}
	
	
}
