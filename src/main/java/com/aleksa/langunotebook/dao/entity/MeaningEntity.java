package com.aleksa.langunotebook.dao.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;


@Entity
public class MeaningEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String meaning;
	private String description;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "example_id")
	private ExampleEntity example;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private WordEntity word;
	@Version
	private Long version;
	
	public MeaningEntity() {}

	public MeaningEntity(String meaning, String description, ExampleEntity example, WordEntity word) {
		this.meaning = meaning;
		this.description = description;
		this.example = example;
		this.word = word;
	}

	public Long getId() {
		return id;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
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
