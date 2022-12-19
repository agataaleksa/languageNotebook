package com.aleksa.langunotebook.controller.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ExampleRequestDTO {
	
	@NotEmpty(message = "Field cannot be null.")
	@Size(max = 200)
	private String example;
	
	public ExampleRequestDTO() {}

	public ExampleRequestDTO(String example) {
		this.example = example;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	
	// ------ BUILDER ------
	
	private ExampleRequestDTO(Builder builder) {
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

		public ExampleRequestDTO build() {
			return new ExampleRequestDTO(this);
		}
	}	
	

}
