package com.aleksa.langunotebook.model;

import com.aleksa.langunotebook.controller.dto.request.WordRequestDTO;

public class WordEntityFactory {
	
	WordEntityFactory() {
	}

    public static WordEntity create(WordRequestDTO wordRequestDTO) {
    	return WordEntity.builder(wordRequestDTO.getWord(), wordRequestDTO.getLanguage())
    			.category(wordRequestDTO.getCategory())
    			.build();
    }
    
    public static WordEntity update(WordEntity wordEntity, WordRequestDTO wordRequestDTO) {
    	return wordEntity.toBuilder()
    			.word(wordRequestDTO.getWord())
    			.language(wordRequestDTO.getLanguage())
    			.category(wordRequestDTO.getCategory())
    			.build();
    }

}
