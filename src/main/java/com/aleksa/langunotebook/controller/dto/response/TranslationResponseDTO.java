package com.aleksa.langunotebook.controller.dto.response;


public class TranslationResponseDTO {
	private String meaning;
	private String description;
	private String word;
	private String example;

	public TranslationResponseDTO() {}

	public TranslationResponseDTO(String meaning, String description, String word, String example) {
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
	
	private TranslationResponseDTO(Builder builder) {
		this.meaning = builder.meaning;
		this.description = builder.description;
		this.word = builder.word;
		this.example = builder.example;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String meaning;
		private String description;
		private String word;
		private String example;

		private Builder() {
		}

		public Builder withMeaning(String meaning) {
			this.meaning = meaning;
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

		public TranslationResponseDTO build() {
			return new TranslationResponseDTO(this);
		}
	}	
	
}
