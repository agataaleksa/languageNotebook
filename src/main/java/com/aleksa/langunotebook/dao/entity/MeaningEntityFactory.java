package com.aleksa.langunotebook.dao.entity;

import com.aleksa.langunotebook.controller.dto.request.MeaningRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.TranslationRequestDTO;

public class MeaningEntityFactory {

	private MeaningEntityFactory() {
    }

    public static MeaningEntity create(MeaningRequestDTO meaningRequestDTO,
                                           WordEntity wordEntity, ExampleEntity exampleEntity) {
        return MeaningEntity.builder(meaningRequestDTO.getMeaning(), meaningRequestDTO.getDescription())
                .example(exampleEntity)
                .word(wordEntity)
                .build();
    }

    public static MeaningEntity create(TranslationRequestDTO translationRequestDTO) {
        return MeaningEntity.builder(translationRequestDTO.getMeaning(),translationRequestDTO.getDescription())
                .example(ExampleEntityFactory.create(translationRequestDTO.getExample()))
                .word(WordEntityFactory.create(translationRequestDTO.getWord()))
                .build();
    }
    
    public static MeaningEntity create(TranslationRequestDTO translationRequestDTO, WordEntity wordEntity) {
        return MeaningEntity.builder(translationRequestDTO.getMeaning(),translationRequestDTO.getDescription())
                .example(ExampleEntityFactory.create(translationRequestDTO.getExample()))
                .word(wordEntity)
                .build();
    }
    
    public static MeaningEntity update(MeaningEntity meaningEntity,
            MeaningRequestDTO meaningRequestDTO) {
    	return meaningEntity.toBuilder()
    			.meaning(meaningRequestDTO.getMeaning())
    			.description(meaningRequestDTO.getDescription())
    			.build();
}

    public static MeaningEntity clearExample(MeaningEntity meaningEntity) {
        return meaningEntity.toBuilder()
                .example(null)
                .build();
    }

}
