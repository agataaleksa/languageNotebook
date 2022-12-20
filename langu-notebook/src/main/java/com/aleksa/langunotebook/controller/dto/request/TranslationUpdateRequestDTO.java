package com.aleksa.langunotebook.controller.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TranslationUpdateRequestDTO {
	
	@NotNull(message = "Field cannot be null.")
	@NotBlank(message = "Field cannot be null.")
	@Size(max = 30)
	private String translation;
	@NotNull(message = "Field cannot be null.")
	@NotBlank(message = "Field cannot be null.")
	@Size(max = 100)
	private String description;
	@Size(max = 200)

	
	public TranslationUpdateRequestDTO(String translation, String description) {
		this.translation = translation;
		this.description = description;
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



}
