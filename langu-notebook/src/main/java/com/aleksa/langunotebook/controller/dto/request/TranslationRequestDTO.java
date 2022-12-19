package com.aleksa.langunotebook.controller.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.aleksa.langunotebook.dao.entity.ExampleEntity;

public class TranslationRequestDTO {
	
	@NotNull(message = "Field cannot be null.")
	@NotBlank(message = "Field cannot be null.")
	@Size(max = 30)
	private String translation;
	@NotNull(message = "Field cannot be null.")
	@NotBlank(message = "Field cannot be null.")
	@Size(max = 100)
	private String description;
	@Size(max = 200)
	private ExampleEntity example;
	@NotNull(message = "Field cannot be null.")
	@NotBlank(message = "Field cannot be null.")
	private Long word_id;

	
	public TranslationRequestDTO(String translation, String description, ExampleEntity example, Long word_id) {
		this.translation = translation;
		this.description = description;
		this.example = example;
		this.word_id = word_id;
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

	public ExampleEntity getExample() {
		return example;
	}

	public void setExample(ExampleEntity example) {
		this.example = example;
	}

	public Long getWord_id() {
		return word_id;
	}

	public void setWord_id(Long word_id) {
		this.word_id = word_id;
	}

	
	
	//------ BUILDER ------

	private TranslationRequestDTO(Builder builder) {
		this.translation = builder.translation;
		this.description = builder.description;
		this.example = builder.example;
		this.word_id = builder.word_id;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String translation;
		private String description;
		private ExampleEntity example;
		private Long word_id;

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

		public Builder withExample(ExampleEntity example) {
			this.example = example;
			return this;
		}

		public Builder withWord_id(Long word_id) {
			this.word_id = word_id;
			return this;
		}

		public TranslationRequestDTO build() {
			return new TranslationRequestDTO(this);
		}
	}
	
}