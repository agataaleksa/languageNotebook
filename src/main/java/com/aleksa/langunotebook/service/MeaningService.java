package com.aleksa.langunotebook.service;

import java.util.List;

import com.aleksa.langunotebook.controller.dto.Language;
import com.aleksa.langunotebook.controller.dto.request.MeaningRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.TranslationRequestDTO;
import com.aleksa.langunotebook.controller.dto.response.MeaningResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.TranslationResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.WordResponseDTO;


public interface MeaningService {
	
    public TranslationResponseDTO addTranslation(TranslationRequestDTO requestDTO);
    public MeaningResponseDTO addMeaningById(Long wordId, MeaningRequestDTO requestDTO);	
    public TranslationResponseDTO addMeaning(String word, TranslationRequestDTO requestDTO);
	public MeaningResponseDTO getMeaningById(Long id);
	public List<MeaningResponseDTO> getMeaningsByWord(String word);
    public WordResponseDTO getWordByMeaning(String meaning);
	public List<TranslationResponseDTO> getTranslations(int offset, int pageSize, String field);
	public List<TranslationResponseDTO> getTranslationsByLanguage(Language language, int offset, int pageSize);
	public List<TranslationResponseDTO> getTranslationsByCategory(String category, int offset, int pageSize);
	public List<TranslationResponseDTO> getTranslationsByLanguageAndCategory(Language language, String category, int offset, int pageSize);
	public void deleteMeaning(Long id);
	public void deleteTranslation(Long id);
    public MeaningResponseDTO deleteExampleFromTranslation(Long id);
	public void updateMeaning(Long id, MeaningRequestDTO requestDTO);
}
