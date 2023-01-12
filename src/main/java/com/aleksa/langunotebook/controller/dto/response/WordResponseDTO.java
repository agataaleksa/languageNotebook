package com.aleksa.langunotebook.controller.dto.response;


public class WordResponseDTO {

	private Long id;
	private String word;
	
	private WordResponseDTO(Builder builder) {
		this.id = builder.id;
		this.word = builder.word;
	}
	
	public WordResponseDTO(Long id, String word) {
		this.id = id;
		this.word = word;
	}

	public Long getId() {
		return id;
	}

	public String getWord() {
		return word;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Long id;
		private String word;

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

		public WordResponseDTO build() {
			return new WordResponseDTO(this);
		}
		
	}
	
}
