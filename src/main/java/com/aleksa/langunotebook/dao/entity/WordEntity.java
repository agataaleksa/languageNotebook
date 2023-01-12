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
	
    protected WordEntity() {}

    private WordEntity(Builder builder) {
		this.word = builder.word;
		this.language = builder.language;
		this.category = builder.category;
	}

	public Long getId() {
		return id;
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

	public static Builder builder(String word, Language language) {
        return new Builder(word, language);
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static class Builder {

        private String word;
        private Language language;
        private String category;

        private Builder(String word, Language language) {
            this.word = word;
            this.language = language;
        }

        private Builder(WordEntity wordEntity) {
            this.word = wordEntity.getWord();
            this.language = wordEntity.getLanguage();
            this.category = wordEntity.getCategory();
        }

        public Builder word(String word) {
            this.word = word;
            return this;
        }
        
        public Builder language(Language language) {
            this.language = language;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }
        
        public WordEntity build() {
            return new WordEntity(this);
        }

    }
    
}
