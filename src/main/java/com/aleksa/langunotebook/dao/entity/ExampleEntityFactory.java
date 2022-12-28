package com.aleksa.langunotebook.dao.entity;

import com.aleksa.langunotebook.controller.dto.request.ExampleRequestDTO;

public class ExampleEntityFactory {
	
	private ExampleEntityFactory() {
    }

    public static ExampleEntity create(ExampleRequestDTO exampleRequestDTO) {
        return ExampleEntity.builder(exampleRequestDTO.getExample())
        		.build();
    }
   
    public static ExampleEntity update(ExampleEntity exampleEntity, ExampleRequestDTO exampleRequestDTO) {
    	return exampleEntity.toBuilder()
    			.word(exampleRequestDTO.getExample())
    			.build();
    }
    
}
