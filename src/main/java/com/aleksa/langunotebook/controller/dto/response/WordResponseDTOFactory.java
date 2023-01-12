package com.aleksa.langunotebook.controller.dto.response;

import com.aleksa.langunotebook.dao.entity.WordEntity;

public class WordResponseDTOFactory {
	
	public WordResponseDTOFactory() {
	}
	
	public static WordResponseDTO create(WordEntity wordEntity) {
		return WordResponseDTO.builder()
				.withId(wordEntity.getId())
				.withWord(wordEntity.getWord())
				.build();
	}
}
