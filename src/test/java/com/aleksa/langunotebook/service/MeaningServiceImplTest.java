package com.aleksa.langunotebook.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aleksa.langunotebook.controller.dto.Language;
import com.aleksa.langunotebook.controller.dto.request.ExampleRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.TranslationRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.WordRequestDTO;
import com.aleksa.langunotebook.controller.dto.response.TranslationResponseDTOFactory;
import com.aleksa.langunotebook.model.ExampleEntity;
import com.aleksa.langunotebook.model.MeaningEntity;
import com.aleksa.langunotebook.model.MeaningEntityFactory;
import com.aleksa.langunotebook.model.WordEntity;
import com.aleksa.langunotebook.repository.MeaningRepository;

@ExtendWith(MockitoExtension.class)
class MeaningServiceImplTest {

	private MeaningRepository meaningRepository;
	private WordService wordService;
	private WordValidator wordValidator;
	private MeaningValidator meaningValidator;	
	private AutoCloseable autoCloseable;
	private MeaningServiceImpl meaningServiceImpl;
	
	@BeforeEach
	void setUp() {
		meaningRepository = mock(MeaningRepository.class);
		wordService = mock(WordService.class);
		wordValidator = mock(WordValidator.class);
		meaningValidator = mock(MeaningValidator.class);
		autoCloseable = MockitoAnnotations.openMocks(this);
		meaningServiceImpl = new MeaningServiceImpl(meaningRepository, wordService, wordValidator, meaningValidator);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}
		
	@Test
	void shouldAddTranslation() {		
		
		TranslationRequestDTO translationToSave = new TranslationRequestDTO("woda", "Water is a liquid.", 
				new WordRequestDTO("water", Language.valueOf("ENGLISH"), "noun"), 
				new ExampleRequestDTO("You should drink more water."));
		MeaningEntity meaning = MeaningEntityFactory.create(translationToSave);

		when(meaningRepository.save(any(MeaningEntity.class))).thenReturn(meaning);
        assertThat(meaningServiceImpl.addTranslation(translationToSave)).usingRecursiveComparison().isEqualTo(TranslationResponseDTOFactory.create(meaning));
        verifyNoMoreInteractions(meaningRepository);
	}
	
	@Test
	void shouldGetMeaning() {
		MeaningEntity meaningEntity = MeaningEntity.builder("woda", "Liquid composed of hydrogen and oxygen.")
				.word(WordEntity.builder("water", Language.valueOf("ENGLISH")).category("noun").build())
				.example(ExampleEntity.builder("Drink water.").build())
				.build();
		
		when(meaningRepository.findById(1L)).thenReturn(Optional.of(meaningEntity));
		assertThat(meaningServiceImpl.getMeaning(1L)).isEqualTo(meaningEntity);
        verifyNoMoreInteractions(meaningRepository);
	}
	
}










