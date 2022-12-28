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

	private MeaningEntity(Builder builder) {
        this.meaning = builder.meaning;
        this.description = builder.description;
        this.example = builder.example;
        this.word = builder.word;
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
	
	public static Builder builder(String meaning, String description) {
        return new Builder(meaning, description);
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static class Builder {

        private String meaning;
        private String description;
        private ExampleEntity example;
        private WordEntity word;

        private Builder(String meaning, String description) {
            this.meaning = meaning;
            this.description = description;
        }

        private Builder(MeaningEntity meaningEntity) {
            this.meaning = meaningEntity.getMeaning();
            this.description = meaningEntity.getDescription();
            this.example = meaningEntity.getExample();
            this.word = meaningEntity.getWord();
        }

        public Builder meaning(String meaning) {
            this.meaning = meaning;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder example(ExampleEntity example) {
            this.example = example;
            return this;
        }

        public Builder word(WordEntity word) {
            this.word = word;
            return this;
        }

        public MeaningEntity build() {
            return new MeaningEntity(this);
        }

    }

}
