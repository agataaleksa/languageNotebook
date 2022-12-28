package com.aleksa.langunotebook.dao.entity;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import com.aleksa.langunotebook.controller.dto.Language;

@Entity
public class WordEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String word;
	@Enumerated(EnumType.STRING)
	private Language language;
	private String category;
    @Version
	private Long version;
	
	public WordEntity() {}

	public WordEntity(String word, Language language, String category) {
		this.word = word;
		this.language = language;
		this.category = category;
	}

	public Long getId() {
		return id;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
}
