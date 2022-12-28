package com.aleksa.langunotebook.controller.dto.response;


public class MeaningResponseDTO {
	
	private String meaning;
	private String example;

	public MeaningResponseDTO() {}

	public MeaningResponseDTO(String meaning, String example) {
		this.meaning = meaning;
		this.example = example;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	//------ BUILDER ------

	private MeaningResponseDTO(Builder builder) {
		this.meaning = builder.meaning;
		this.example = builder.example;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String meaning;
		private String example;

		private Builder() {
		}

		public Builder withMeaning(String meaning) {
			this.meaning = meaning;
			return this;
		}

		public Builder withExample(String example) {
			this.example = example;
			return this;
		}

		public MeaningResponseDTO build() {
			return new MeaningResponseDTO(this);
		}
	}

}
