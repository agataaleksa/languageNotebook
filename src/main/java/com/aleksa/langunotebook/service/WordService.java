package com.aleksa.langunotebook.service;

import java.util.List;
import java.util.Optional;

import com.aleksa.langunotebook.controller.dto.request.WordRequestDTO;
import com.aleksa.langunotebook.controller.dto.response.WordResponseDTO;
import com.aleksa.langunotebook.model.WordEntity;


public interface WordService {
	
	public WordResponseDTO addWord(WordRequestDTO wordRequestDTO);
	public Optional<WordEntity> getWord(Long id);
    public WordResponseDTO getWordById(Long id);
    public List<WordEntity> getWords();
	public void deleteWord(Long id);
	public void updateWord(Long id, WordRequestDTO requestDTO);

}
