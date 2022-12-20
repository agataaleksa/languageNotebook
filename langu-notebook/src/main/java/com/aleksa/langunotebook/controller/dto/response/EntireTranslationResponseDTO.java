package com.aleksa.langunotebook.controller.dto.response;


public class EntireTranslationResponseDTO {
	private String translation;
	private String description;
	private String word;
	private String example;

	public EntireTranslationResponseDTO() {}

	public EntireTranslationResponseDTO(String translation, String description, String word, String example) {
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

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}
	
	
	//------ BUILDER ------
	
	private EntireTranslationResponseDTO(Builder builder) {
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
		private String word;
		private String example;

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

		public Builder withWord(String word) {
			this.word = word;
			return this;
		}

		public Builder withExample(String example) {
			this.example = example;
			return this;
		}

		public EntireTranslationResponseDTO build() {
			return new EntireTranslationResponseDTO(this);
		}
	}	
	
}
