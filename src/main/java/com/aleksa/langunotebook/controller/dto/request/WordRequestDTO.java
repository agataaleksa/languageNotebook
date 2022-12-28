package com.aleksa.langunotebook.controller.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.aleksa.langunotebook.controller.dto.Language;

public class WordRequestDTO {
	
	@NotBlank
	@Size(max = 30)
	private String word;
	private Language language;
	@NotBlank
	private String category;

	public WordRequestDTO(String word, Language language, String category) {
		this.word = word;
		this.language = language;
		this.category = category;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
