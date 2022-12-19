package com.aleksa.langunotebook.controller.dto.request.response;


public class ExampleResponseDTO {
	
	private String example;
	
	public ExampleResponseDTO() {}

	public ExampleResponseDTO(String example) {
		this.example = example;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}
	
	
	//------ BUILDER ------

	private ExampleResponseDTO(Builder builder) {
		this.example = builder.example;
	}
	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String example;

		private Builder() {
		}

		public Builder withExample(String example) {
			this.example = example;
			return this;
		}

		public ExampleResponseDTO build() {
			return new ExampleResponseDTO(this);
		}
	}

}
