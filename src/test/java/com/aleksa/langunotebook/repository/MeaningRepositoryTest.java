package com.aleksa.langunotebook.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.aleksa.langunotebook.controller.dto.Language;
import com.aleksa.langunotebook.model.MeaningEntity;
import com.aleksa.langunotebook.model.WordEntity;

@DataJpaTest
class MeaningRepositoryTest {

	@Autowired
	private MeaningRepository meaningRepository;
	
	@AfterEach
	void tearDown() { 
		meaningRepository.deleteAll();
	}

	@Test
	void shouldCheckIfMeaningExistsTrue() {
		WordEntity wordEntity = WordEntity.builder("water", Language.valueOf("ENGLISH")).build();
		MeaningEntity meaningEntity = MeaningEntity.builder("woda", "Water is a liquid.")
				.word(wordEntity)
				.build();
		meaningRepository.save(meaningEntity);
		
		assertThat(meaningRepository.checkIfMeaningExists("woda", wordEntity.getId())).isEqualTo(1);
	}
	
	@Test
	void shouldCheckIfMeaningExistsFalse() {
		
		assertThat(meaningRepository.checkIfMeaningExists("woda", 1L)).isEqualTo(0);
	}
	
	@Test
	void shouldFilterByLanguageTrue() {
		MeaningEntity meaningEntity = MeaningEntity.builder("woda", "Water is a liquid.")
				.word(WordEntity.builder("water", Language.valueOf("ENGLISH")).build())
				.build();
		meaningRepository.save(meaningEntity);
		
		MeaningEntity secondMeaningEntity = MeaningEntity.builder("woda", "Wasser ist eine Fluessigkeit.")
				.word(WordEntity.builder("das Wasser", Language.valueOf("GERMAN")).build())
				.build();
		meaningRepository.save(secondMeaningEntity);
		
		assertThat(meaningRepository.filterByLanguage(Language.valueOf("ENGLISH"), null).get(0)).isEqualTo(meaningEntity);
		assertThat(meaningRepository.filterByLanguage(Language.valueOf("ENGLISH"), null).size()).isEqualTo(1);

	}
	
	@Test
	void shouldFilterByCategoryTrue() {
		MeaningEntity meaningEntity = MeaningEntity.builder("woda", "Water is a liquid.")
				.word(WordEntity.builder("water", Language.valueOf("ENGLISH")).category("noun").build())
				.build();
		meaningRepository.save(meaningEntity);
		
		MeaningEntity secondMeaningEntity = MeaningEntity.builder("pić", "You should eat a lot of fruits and vegetables.")
				.word(WordEntity.builder("to eat", Language.valueOf("ENGLISH")).category("verb").build())
				.build();
		meaningRepository.save(secondMeaningEntity);
		
		assertThat(meaningRepository.filterByCategory("noun", null).get(0)).isEqualTo(meaningEntity);
		assertThat(meaningRepository.filterByCategory("noun", null).size()).isEqualTo(1);

	}

	
}
