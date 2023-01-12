package com.aleksa.langunotebook.controller.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.aleksa.langunotebook.dao.entity.MeaningEntity;

public class TranslationResponseDTOFactory {

	public TranslationResponseDTOFactory() {
	}
	
	 public static TranslationResponseDTO create(MeaningEntity meaningEntity) {
        return TranslationResponseDTO.builder()
                .withWord(meaningEntity.getWord().getWord())
                .withMeaning(meaningEntity.getMeaning())
                .withDescription(meaningEntity.getDescription())
                .withExample(meaningEntity.getExample().getExample())
                .build();
    }
	 
	 public static List<TranslationResponseDTO> create(List<MeaningEntity> meanings){
	        List<TranslationResponseDTO> dtos = new ArrayList<>();
	        for (MeaningEntity meaning : meanings) {
	        	dtos.add(create(meaning));
	        }
	        return dtos;
	    }
	 
}
