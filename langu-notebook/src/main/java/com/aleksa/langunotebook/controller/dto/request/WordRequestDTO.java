package com.aleksa.langunotebook.controller.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class WordRequestDTO {
	
	@NotEmpty(message = "Field cannot be null.")
	@Size(max = 30)
	private String word;
	private String language;
	private String translationSet;
	
	public WordRequestDTO() {}

	
	public WordRequestDTO(String word) {
		this.word = word;
	}


	public WordRequestDTO(String word, String language, String translationSet) {
		this.word = word;
		this.language = language;
		this.translationSet = translationSet;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTranslationSet() {
		return translationSet;
	}


	public void setTranslationSet(String translationSet) {
		this.translationSet = translationSet;
	}

	
	// ------ BUILDER ------

	private WordRequestDTO(Builder builder) {
		this.word = builder.word;
		this.language = builder.language;
		this.translationSet = builder.translationSet;
	}
	
	public static Builder builder() {
		return new Builder();
	}


	public static final class Builder {
		private String word;
		private String language;
		private String translationSet;

		private Builder() {
		}

		public Builder withWord(String word) {
			this.word = word;
			return this;
		}

		public Builder withLanguage(String language) {
			this.language = language;
			return this;
		}

		public Builder withTranslationSet(String translationSet) {
			this.translationSet = translationSet;
			return this;
		}

		public WordRequestDTO build() {
			return new WordRequestDTO(this);
		}
	}
	
}
