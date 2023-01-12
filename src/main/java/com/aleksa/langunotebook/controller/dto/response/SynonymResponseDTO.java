package com.aleksa.langunotebook.controller.dto.response;

import com.aleksa.langunotebook.controller.dto.Language;

public class SynonymResponseDTO {
	
	private String word;
	private Language language;
	private String category;
	private String[] synonyms;
	
	public SynonymResponseDTO(String word, Language language, String category, String[] synonyms) {
		this.word = word;
		this.language = language;
		this.category = category;
		this.synonyms = synonyms;
	}

	public String getWord() {
		return word;
	}

	public Language getLanguage() {
		return language;
	}

	public String getCategory() {
		return category;
	}

	public String[] getSynonyms() {
		return synonyms;
	}
	
}
