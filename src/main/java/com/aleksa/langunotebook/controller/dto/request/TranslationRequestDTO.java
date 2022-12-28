package com.aleksa.langunotebook.controller.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class TranslationRequestDTO {
	
	@NotBlank
	@Size(max = 30)
	private String meaning;
	@Size(max = 100)
	@NotEmpty
	private String description;
	private WordRequestDTO word;
	private ExampleRequestDTO example;

	public TranslationRequestDTO(String meaning, String description, 
			WordRequestDTO word, ExampleRequestDTO example) {
		this.meaning = meaning;
		this.description = description;
		this.word = word;
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

	public WordRequestDTO getWord() {
		return word;
	}

	public void setWord(WordRequestDTO word) {
		this.word = word;
	}

	public ExampleRequestDTO getExample() {
		return example;
	}

	public void setExample(ExampleRequestDTO example) {
		this.example = example;
	}

}
