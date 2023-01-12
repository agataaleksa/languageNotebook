package com.aleksa.langunotebook.dao.entity;

import com.aleksa.langunotebook.controller.dto.request.ExampleRequestDTO;

public class ExampleEntityFactory {
	
	private ExampleEntityFactory() {
    }

    public static ExampleEntity create(ExampleRequestDTO exampleRequestDTO) {
        return new ExampleEntity(exampleRequestDTO.getExample());
    }
   
    public static ExampleEntity update(ExampleEntity exampleEntity, ExampleRequestDTO exampleRequestDTO) {
    	exampleEntity.setExample(exampleRequestDTO.getExample());
    	return exampleEntity;
//    	return exampleEntity.toBuilder()
//    			.example(exampleRequestDTO.getExample())
//    			.build();
    }
    
}
