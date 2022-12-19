package com.aleksa.langunotebook.controller.dto.request.response;

import java.util.List;
import java.util.Collections;

public class WordResponseDTO {

	private Long id;
	private String word;
	private List<String> translations;
	
	public WordResponseDTO() {}
	
	
	public WordResponseDTO(Long id, String word, List<String> translations) {
		this.id = id;
		this.word = word;
		this.translations = translations;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public List<String> getTranslations() {
		return translations;
	}
	public void setTranslations(List<String> list) {
		this.translations = list;
	}

	
	//------ BUILDER ------
	
	private WordResponseDTO(Builder builder) {
		this.id = builder.id;
		this.word = builder.word;
		this.translations = builder.translations;
	}
	
	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Long id;
		private String word;
		private List<String> translations = Collections.emptyList();

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withWord(String word) {
			this.word = word;
			return this;
		}

		public Builder withTranslations(List<String> translations) {
			this.translations = translations;
			return this;
		}

		public WordResponseDTO build() {
			return new WordResponseDTO(this);
		}
	}
	
}
