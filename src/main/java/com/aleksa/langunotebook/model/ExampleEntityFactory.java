package com.aleksa.langunotebook.model;

import com.aleksa.langunotebook.controller.dto.request.ExampleRequestDTO;

public class ExampleEntityFactory {
	
	private ExampleEntityFactory() {
    }

    public static ExampleEntity create(ExampleRequestDTO exampleRequestDTO) {
        return new ExampleEntity(exampleRequestDTO.getExample());
    }
   
    public static ExampleEntity update(ExampleEntity exampleEntity, ExampleRequestDTO exampleRequestDTO) {
    	return exampleEntity.toBuilder()
    			.example(exampleRequestDTO.getExample())
    			.build();
    }
    
}
