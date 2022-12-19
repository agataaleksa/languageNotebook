package com.aleksa.langunotebook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aleksa.langunotebook.controller.dto.request.EntireTranslationRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.TranslationRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.response.EntireTranslationResponseDTO;
import com.aleksa.langunotebook.controller.dto.request.response.TranslationResponseDTO;
import com.aleksa.langunotebook.dao.entity.TranslationEntity;

@Service
public interface TranslationService {
	

	public TranslationEntity getTranslation(Long id);
	public TranslationResponseDTO getTranslactionById(Long id);
	public List<TranslationResponseDTO> getTranslations();
	public TranslationResponseDTO addTranslation(TranslationRequestDTO translationRequestDTO);
	public void deleteTranslation(Long id);
	//public TranslationResponseDTO addExampleToTranslation(Long translation_id, Long example_id);
    public TranslationResponseDTO deleteExampleFromTranslation(Long translation_id);      
    public EntireTranslationResponseDTO addEntireTranslation(EntireTranslationRequestDTO requestDTO);
	List<TranslationResponseDTO> getTranslationsByWord(String word);
    
}
