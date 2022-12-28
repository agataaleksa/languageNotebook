package com.aleksa.langunotebook.controller.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MeaningRequestDTO {
	
	@NotBlank
	@Size(max = 30)
	private String meaning;
	@NotEmpty
	@Size(max = 100)
	private String description;
	private ExampleRequestDTO example;
	
	public MeaningRequestDTO(String meaning, String description, ExampleRequestDTO example) {
		this.meaning = meaning;
		this.description = description;
		this.example = example;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ExampleRequestDTO getExample() {
		return example;
	}

	public void setExample(ExampleRequestDTO example) {
		this.example = example;
	}
	
}