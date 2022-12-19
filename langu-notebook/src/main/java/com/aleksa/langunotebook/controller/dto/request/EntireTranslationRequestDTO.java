package com.aleksa.langunotebook.controller.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class EntireTranslationRequestDTO {
	
	@NotNull(message = "Field cannot be null.")
	@NotBlank(message = "Field cannot be null.")
	@Size(max = 30)
	private String translation;
	@Size(max = 100)
	private String description;
	private WordRequestDTO word;
	private ExampleRequestDTO example;

	public EntireTranslationRequestDTO() {}

	public EntireTranslationRequestDTO(String translation, String description, 
			WordRequestDTO word, ExampleRequestDTO example) {
		this.translation = translation;
		this.description = description;
		this.word = word;
		this.example = example;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
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

	
	
	//------ BUILDER ------

	private EntireTranslationRequestDTO(Builder builder) {
		this.translation = builder.translation;
		this.description = builder.description;
		this.word = builder.word;
		this.example = builder.example;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String translation;
		private String description;
		private WordRequestDTO word;
		private ExampleRequestDTO example;

		private Builder() {
		}

		public Builder withTranslation(String translation) {
			this.translation = translation;
			return this;
		}

		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder withWord(WordRequestDTO word) {
			this.word = word;
			return this;
		}

		public Builder withExample(ExampleRequestDTO example) {
			this.example = example;
			return this;
		}

		public EntireTranslationRequestDTO build() {
			return new EntireTranslationRequestDTO(this);
		}
	}


	
}
