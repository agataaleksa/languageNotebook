package com.aleksa.langunotebook.controller.dto;

import java.util.ArrayList;
import java.util.List;
import com.aleksa.langunotebook.controller.dto.request.response.ExampleResponseDTO;
import com.aleksa.langunotebook.controller.dto.request.response.TranslationResponseDTO;
import com.aleksa.langunotebook.controller.dto.request.response.EntireTranslationResponseDTO;
import com.aleksa.langunotebook.controller.dto.request.response.WordResponseDTO;
import com.aleksa.langunotebook.dao.entity.ExampleEntity;
import com.aleksa.langunotebook.dao.entity.TranslationEntity;
import com.aleksa.langunotebook.dao.entity.WordEntity;


public class Mapper {

	public static WordResponseDTO wordToWordResponseDTO(WordEntity wordEntity) {
		WordResponseDTO dto = new WordResponseDTO();
		dto.setWord(wordEntity.getWord());
		dto.setId(wordEntity.getId());
		List<String> list = new ArrayList<>();
        List<TranslationEntity> translations = wordEntity.getTranslations();
        for (TranslationEntity translation : translations) {
            list.add(translation.getTranslation());
            list.add(translation.getDescription());
        }
        dto.setTranslations(null);
		return dto;
	}
	
	public static List<WordResponseDTO> wordsToWordResponseDTO(List<WordEntity> words) {
        List<WordResponseDTO> dtos = new ArrayList<>();
        for (WordEntity word : words) {
        	dtos.add(wordToWordResponseDTO(word));
        }
        return dtos;
	}
	
	public static TranslationResponseDTO translationToTranslationDTO(TranslationEntity translationEntity) {
		TranslationResponseDTO dto = new TranslationResponseDTO();
		dto.setTranslation(translationEntity.getTranslation());
		dto.setDescription(translationEntity.getDescription());
		dto.setExample(translationEntity.getExample().getExample());
		dto.setWord(translationEntity.getWord().getWord());
        return dto;
	}
	
	 public static List<TranslationResponseDTO> translationsToTranslationDTO(List<TranslationEntity> translations){
	        List<TranslationResponseDTO> dtos = new ArrayList<>();
	        for (TranslationEntity translation : translations) {
	        	dtos.add(translationToTranslationDTO(translation));
	        }
	        return dtos;
	 }
	 
	public static ExampleResponseDTO exampleToExampleDTO(ExampleEntity exampleEntity) {
		ExampleResponseDTO dto = new ExampleResponseDTO();
		dto.setExample(exampleEntity.getExample());
		return dto;
	}

	public static List<ExampleResponseDTO> examplesToExampleDTO(List<ExampleEntity> examples) {
        List<ExampleResponseDTO> dtos = new ArrayList<>();
        for (ExampleEntity example : examples) {
        	dtos.add(exampleToExampleDTO(example));
        }
        return dtos;
	}
	
	public static EntireTranslationResponseDTO entireTranslationToResponseDTO(TranslationEntity translationEntity) {
		EntireTranslationResponseDTO dto = new EntireTranslationResponseDTO();
		dto.setWord(translationEntity.getWord().getWord());
		dto.setWord(translationEntity.getWord().getLanguage());
		dto.setWord(translationEntity.getWord().getTranslationSet());
		dto.setTranslation(translationEntity.getTranslation());
		dto.setDescription(translationEntity.getDescription());
		dto.setExample(translationEntity.getExample().getExample());
		return dto;
	}
	
	
}

