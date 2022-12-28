package com.aleksa.langunotebook.controller.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ExampleRequestDTO {
	
	@NotBlank
	@Size(max = 200)
    private String example;
	
	public ExampleRequestDTO() {}
	
	public ExampleRequestDTO(String example) {
		this.example = example;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

}
