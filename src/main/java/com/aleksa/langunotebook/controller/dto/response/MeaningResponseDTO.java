package com.aleksa.langunotebook.controller.dto.response;


public class MeaningResponseDTO {
	
	private String meaning;
	private String example;

	public MeaningResponseDTO(String meaning, String example) {
		this.meaning = meaning;
		this.example = example;
	}

	public String getMeaning() {
		return meaning;
	}

	public String getExample() {
		return example;
	}

}
