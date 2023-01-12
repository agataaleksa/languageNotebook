package com.aleksa.langunotebook.service;

import org.springframework.stereotype.Component;

import com.aleksa.langunotebook.exception.ObjectAlreadyExistsException;
import com.aleksa.langunotebook.exception.ResourceNotFoundException;
import com.aleksa.langunotebook.exception.ValidationMessage;
import com.aleksa.langunotebook.repository.WordRepository;

@Component
public class WordValidator {
	
	private final WordRepository wordRepository;
	
	public WordValidator(WordRepository wordRepository) {
		this.wordRepository = wordRepository;
	}
	
	public void validateWord(String word) {
		
	if(wordRepository.checkIfWordExists(word) == 1) { 
		throw new ObjectAlreadyExistsException("Word already exists.", ValidationMessage.VALUE_ALREADY_EXISTS); }
	
	}
	
	public void findWord(String word) {
		
		if(wordRepository.checkIfWordExists(word) == 0) { 
			throw new ResourceNotFoundException("Word does not exist in the database."); }
		
		}
	
	
}
	
	

