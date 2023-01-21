package com.aleksa.langunotebook.controller.dto.response;


import com.aleksa.langunotebook.model.WordEntity;

public class SynonymResponseDTOFactory {
	
	private SynonymResponseDTOFactory() {
    }

    public static SynonymResponseDTO create(WordEntity wordEntity, String[] synonyms) {
        return new SynonymResponseDTO(wordEntity.getWord(), wordEntity.getLanguage(), wordEntity.getCategory(), synonyms);
    }

}
