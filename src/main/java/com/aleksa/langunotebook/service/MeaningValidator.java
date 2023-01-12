package com.aleksa.langunotebook.service;

import org.springframework.stereotype.Component;

import com.aleksa.langunotebook.exception.ObjectAlreadyExistsException;
import com.aleksa.langunotebook.exception.ValidationMessage;
import com.aleksa.langunotebook.repository.MeaningRepository;

@Component
public class MeaningValidator {
	
private final MeaningRepository meaningRepository;
	
	public MeaningValidator(MeaningRepository meaningRepository) {
		this.meaningRepository = meaningRepository;
	}
	
	public void validateMeaning(String meaning, Long wordId) {
		
	if (meaningRepository.checkIfMeaningExists(meaning, wordId) == 1) { 
		throw new ObjectAlreadyExistsException("Meaning already exists.", ValidationMessage.VALUE_ALREADY_EXISTS); 
		}
	
	}

}
