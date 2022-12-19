package com.aleksa.langunotebook.dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class WordEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String word;
	private String language;
	private String translationSet;
	@OneToMany(mappedBy = "word", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TranslationEntity> translations = new ArrayList<>();
	@Version
	private Long version;
	
	public WordEntity() {}

	public WordEntity(Long id, String word, String language, String translationSet, List<TranslationEntity> translations) {
		this.word = word;
		this.language = language;
		this.translationSet = translationSet;
		this.translations = translations;
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTranslationSet() {
		return translationSet;
	}

	public void setTranslationSet(String translationSet) {
		this.translationSet = translationSet;
	}

	public List<TranslationEntity> getTranslations() {
		return translations;
	}

	public void setTranslations(List<TranslationEntity> translations) {
		this.translations = translations;
	}
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public void addTranslation(TranslationEntity translation) {
		translations.add(translation);
	}
	
	public void deleteTranslation(TranslationEntity translation) {
	    translations.remove(translation);
	}

}
