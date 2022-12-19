package com.aleksa.langunotebook.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.aleksa.langunotebook.controller.dto.Mapper;
import com.aleksa.langunotebook.controller.dto.request.WordRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.response.WordResponseDTO;
import com.aleksa.langunotebook.dao.entity.WordEntity;
import com.aleksa.langunotebook.exception.WordNotFoundException;
import com.aleksa.langunotebook.repository.WordRepository;

@Service
public class WordServiceImpl implements WordService 
{
	
	private final WordRepository wordRepository;

	public WordServiceImpl(WordRepository wordRepository) {
		this.wordRepository = wordRepository;
	}

	
	@Override
	public WordEntity getWord(Long id) {
		return wordRepository.findById(id).orElseThrow(WordNotFoundException::new);
	}
	
	@Override
	public WordResponseDTO getWordById(Long id) {
		WordEntity word = wordRepository.findById(id).orElseThrow(NoSuchElementException::new);	
		return Mapper.wordToWordResponseDTO(word);
	}
	
	@Override
	public List<WordResponseDTO> getWords() {
		List<WordEntity> words = StreamSupport
                .stream(wordRepository.findAll().spliterator(), false)
                 .collect(Collectors.toList());
        return Mapper.wordsToWordResponseDTO(words);
	}
	
	@Override
	public WordResponseDTO addWord(WordRequestDTO wordRequestDTO) {
		WordEntity word = new WordEntity();
		word.setWord(wordRequestDTO.getWord());
		
		wordRepository.save(word);
        return Mapper.wordToWordResponseDTO(word);
	}

	@Override
	public WordResponseDTO deleteWordById(Long id) {
		WordEntity word = getWord(id);
		wordRepository.delete(word);
	        return Mapper.wordToWordResponseDTO(word);
	}


	@Override
	public int checkIfWordExists(String word) {
		return wordRepository.checkIfWordExists(word);
	}

	
}