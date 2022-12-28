package com.aleksa.langunotebook.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import com.aleksa.langunotebook.controller.dto.Language;
import com.aleksa.langunotebook.dao.entity.WordEntity.Builder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)

public class ExampleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String example;
	@Version
	private Long version;
	
	public ExampleEntity() {}
	
	public ExampleEntity(Builder builder) {
		this.example = builder.example;
	}

	public ExampleEntity(String example) {
		this.example = example;
	}

	public Long getId() {
		return id;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	public static Builder builder(String example) {
        return new Builder(example);
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static class Builder {

        private String example;
        
        private Builder(String example) {
            this.example = example;
        }

        private Builder(ExampleEntity exampleEntity) {
            this.example = exampleEntity.getExample();
        }

        public Builder word(String example) {
            this.example = example;
            return this;
        }
        
        public ExampleEntity build() {
            return new ExampleEntity(this);
        }

    }

}
