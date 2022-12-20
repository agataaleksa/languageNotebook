package com.aleksa.langunotebook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aleksa.langunotebook.controller.dto.request.WordRequestDTO;
import com.aleksa.langunotebook.controller.dto.response.WordResponseDTO;
import com.aleksa.langunotebook.dao.entity.WordEntity;

@Service
public interface WordService {
	
	 public WordEntity getWord(Long id);
	 public WordResponseDTO getWordById(Long id);
     public List<WordResponseDTO> getWords();
     public WordResponseDTO addWord(WordRequestDTO wordRequestDTO);
     public WordResponseDTO deleteWordById(Long id);
     public int checkIfWordExists(String word);



}
