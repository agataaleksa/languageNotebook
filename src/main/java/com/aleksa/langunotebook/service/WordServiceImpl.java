package com.aleksa.langunotebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.aleksa.langunotebook.controller.dto.request.WordRequestDTO;
import com.aleksa.langunotebook.controller.dto.response.WordResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.WordResponseDTOFactory;
import com.aleksa.langunotebook.exception.ResourceNotFoundException;
import com.aleksa.langunotebook.model.WordEntity;
import com.aleksa.langunotebook.model.WordEntityFactory;
import com.aleksa.langunotebook.repository.WordRepository;

@Service
@Transactional
public class WordServiceImpl implements WordService {
	
	private final WordRepository wordRepository;
	private final WordValidator wordValidator;

	public WordServiceImpl(WordRepository wordRepository, WordValidator wordValidator) {
		this.wordRepository = wordRepository;
		this.wordValidator = wordValidator;
	}
	
	@Override
	public WordResponseDTO addWord(WordRequestDTO requestDTO) {
		WordEntity word = WordEntityFactory.create(requestDTO);
		wordValidator.validateWord(word.getWord());
		wordRepository.save(word);
        return WordResponseDTOFactory.create(word);
	}

	@Override
	public Optional<WordEntity> getWord(Long id) {
		return wordRepository.findById(id);
	}
	
	@Override
	public WordResponseDTO getWordById(Long id) {
		WordEntity word = wordRepository.findById(id).orElseThrow(ResourceNotFoundException::new);	
		return WordResponseDTOFactory.create(word);
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
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateWord(Long id, WordRequestDTO requestDTO) {
		WordEntity word = wordRepository.findById(id).orElseThrow(ResourceNotFoundException::new);	
		if(!wordRepository.existsById(id)) {
			  throw new ResourceNotFoundException();
			  }
		WordEntity updatedWord = WordEntityFactory.update(word, requestDTO);
		wordRepository.save(updatedWord);
	}
	
	
}
						