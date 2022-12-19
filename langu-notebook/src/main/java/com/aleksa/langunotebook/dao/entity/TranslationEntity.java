package com.aleksa.langunotebook.dao.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class TranslationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String translation;
	private String description;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "example_id")
	private ExampleEntity example;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "word_id")
	private WordEntity word;
	@Version
	private Long version;
	
	public TranslationEntity() {}

	public TranslationEntity(String translation, String description, ExampleEntity example, WordEntity word) {
		this.translation = translation;
		this.description = description;
		this.example = example;
		this.word = word;
	}

	public Long getId() {
		return id;
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

	public WordEntity getWord() {
		return word;
	}

	public void setWord(WordEntity word) {
		this.word = word;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	
}
