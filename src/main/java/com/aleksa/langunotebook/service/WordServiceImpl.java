package com.aleksa.langunotebook.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aleksa.langunotebook.controller.dto.Mapper;
import com.aleksa.langunotebook.controller.dto.request.WordRequestDTO;
import com.aleksa.langunotebook.controller.dto.response.WordResponseDTO;
import com.aleksa.langunotebook.dao.entity.WordEntity;
import com.aleksa.langunotebook.exception.ResourceNotFoundException;
import com.aleksa.langunotebook.exception.ValidationException;
import com.aleksa.langunotebook.repository.WordRepository;

@Service
public class WordServiceImpl implements WordService {
	
	private final WordRepository wordRepository;

	public WordServiceImpl(WordRepository wordRepository) {
		this.wordRepository = wordRepository;
	}
	
	@Override
	public WordResponseDTO addWord(WordRequestDTO requestDTO) {
		WordEntity word = new WordEntity();
		word.setWord(requestDTO.getWord());
		word.setLanguage(requestDTO.getLanguage());
		word.setCategory(requestDTO.getCategory());
		
		if(checkIfWordExists(requestDTO.getWord()) == 1) { 
			throw new ValidationException(); }
		wordRepository.save(word);
        return Mapper.wordToWordResponseDTO(word);
	}

	@Override
	public Optional<WordEntity> getWord(Long id) {
		return wordRepository.findById(id);
	}
	
	@Override
	public WordResponseDTO getWordById(Long id) {
		WordEntity word = wordRepository.findById(id).orElseThrow(NoSuchElementException::new);	
		return Mapper.wordToWordResponseDTO(word);
	}
	
	@Override
	public List<WordEntity> getWords() {
		List<WordEntity> words = wordRepository.findAll();
        return words;
	}
	
	@Override
	public void deleteWord(Long id) {
		if(!wordRepository.existsById(id)) {
			  throw new ResourceNotFoundException();
			  }
		wordRepository.deleteById(id);
	}
	
	@Override
	public int checkIfWordExists(String word) {
		return wordRepository.checkIfWordExists(word);
	}
	
	@Override
	public int findWordsId(String word) {
		return wordRepository.findWordsId(word);
	}
	
}
						