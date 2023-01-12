package com.aleksa.langunotebook.controller.dto.response;

import com.aleksa.langunotebook.dao.entity.ExampleEntity;

public class ExampleResponseDTOFactory {
	
	public ExampleResponseDTOFactory() {
	}
	
	public static ExampleResponseDTO create(ExampleEntity exampleEntity) {
		return new ExampleResponseDTO(exampleEntity.getExample());
	}

}
