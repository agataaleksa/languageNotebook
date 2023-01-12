package com.aleksa.langunotebook.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.aleksa.langunotebook.controller.dto.response.ExampleResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.MeaningResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.TranslationResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.WordResponseDTO;
import com.aleksa.langunotebook.dao.entity.ExampleEntity;
import com.aleksa.langunotebook.dao.entity.MeaningEntity;
import com.aleksa.langunotebook.dao.entity.WordEntity;


public class Mapper {

//	public static WordResponseDTO wordToWordResponseDTO(WordEntity word) {
//		WordResponseDTO dto = new WordResponseDTO();
//		dto.setId(word.getId());
//		dto.setWord(word.getWord());
//		return dto;
//	}
//	
//	public static List<WordResponseDTO> wordsToWordResponseDTO(List<WordEntity> words) {
//        List<WordResponseDTO> dtos = new ArrayList<>();
//        for (WordEntity word : words) {
//        	dtos.add(wordToWordResponseDTO(word));
//        }
//        return dtos;
//	}
//	
//	public static MeaningResponseDTO meaningToMeaningDTO(MeaningEntity meaning) {
//		MeaningResponseDTO dto = new MeaningResponseDTO();
//		dto.setMeaning(meaning.getMeaning());
//		dto.setExample(meaning.getExample().getExample());
//        return dto;
//	}
//	
//	 public static List<MeaningResponseDTO> meaningsToMeaningDTO(List<MeaningEntity> meanings){
//	        List<MeaningResponseDTO> dtos = new ArrayList<>();
//	        for (MeaningEntity meaning : meanings) {
//	        	dtos.add(meaningToMeaningDTO(meaning));
//	        }
//	        return dtos;
//	 }
//	 
//	public static ExampleResponseDTO exampleToExampleDTO(ExampleEntity example) {
//		ExampleResponseDTO dto = new ExampleResponseDTO();
//		dto.setExample(example.getExample());
//		return dto;
//	}
//
//	public static List<ExampleResponseDTO> examplesToExampleDTO(List<ExampleEntity> examples) {
//        List<ExampleResponseDTO> dtos = new ArrayList<>();
//        for (ExampleEntity example : examples) {
//        	dtos.add(exampleToExampleDTO(example));
//        }
//        return dtos;
//	}
//	
//	public static TranslationResponseDTO translationToTranslationDTO(MeaningEntity meaning) {
//		TranslationResponseDTO dto = new TranslationResponseDTO();
//		dto.setWord(meaning.getWord().getWord());
//		dto.setMeaning(meaning.getMeaning());
//		dto.setDescription(meaning.getDescription());
//		dto.setExample(meaning.getExample().getExample());
//		return dto;
//	}
//	
//	public static List<TranslationResponseDTO> translationsToTranslationDTO(List<MeaningEntity> meanings) {
//        List<TranslationResponseDTO> dtos = new ArrayList<>();
//        for (MeaningEntity meaning : meanings) {
//        	dtos.add(translationToTranslationDTO(meaning));
//        }
//        return dtos;
//	}
//	
}

