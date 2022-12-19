package com.aleksa.langunotebook.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;
import com.aleksa.langunotebook.controller.dto.Mapper;
import com.aleksa.langunotebook.controller.dto.request.EntireTranslationRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.TranslationRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.response.EntireTranslationResponseDTO;
import com.aleksa.langunotebook.controller.dto.request.response.TranslationResponseDTO;
import com.aleksa.langunotebook.dao.entity.ExampleEntity;
import com.aleksa.langunotebook.dao.entity.WordEntity;
import com.aleksa.langunotebook.dao.entity.TranslationEntity;
import com.aleksa.langunotebook.exception.TranslationNotFoundException;
import com.aleksa.langunotebook.exception.WordAlreadyExistsException;
import com.aleksa.langunotebook.exception.WordNotFoundException;
import com.aleksa.langunotebook.repository.TranslationRepository;

@Service
public class TranslationServiceImpl implements TranslationService
{
	
	private final TranslationRepository translationRepository;
	private final ExampleService exampleService;
	private final WordService wordService;
	
	
	public TranslationServiceImpl(TranslationRepository translationRepository, ExampleService exampleService,
			WordService wordService) {
		this.translationRepository = translationRepository;
		this.exampleService = exampleService;
		this.wordService = wordService;
	}

	@Override
	public TranslationEntity getTranslation(Long id) {
		TranslationEntity translation = translationRepository.findById(id).orElseThrow(WordNotFoundException::new);
        return translation;
    }
	
	@Override
	public TranslationResponseDTO getTranslactionById(Long id) {
		if(!translationRepository.existsById(id)) {
			  throw new TranslationNotFoundException();
			}
		return Mapper.translationToTranslationDTO(getTranslation(id));
	}
	
	@Override
	public List<TranslationResponseDTO> getTranslationsByWord(String word) {
		 List<TranslationEntity> translations = StreamSupport
				 .stream(translationRepository.findAll().spliterator(), false)
				 .filter(t -> t.getWord().getWord().equals(word))
	             .collect(Collectors.toList());
		 
		 if(wordService.checkIfWordExists(word) == 0) { throw new WordNotFoundException(); }
		 if (translations.isEmpty()) { throw new TranslationNotFoundException(); }
	     return Mapper.translationsToTranslationDTO(translations);
	    
	}

	@Override
	public List<TranslationResponseDTO> getTranslations() {
		 List<TranslationEntity> translations = StreamSupport
				 .stream(translationRepository.findAll().spliterator(), false)
	             .collect(Collectors.toList());
		 
		 if (translations.isEmpty()) { throw new TranslationNotFoundException(); }
	     return Mapper.translationsToTranslationDTO(translations);
	    
	}

	@Override
	public TranslationResponseDTO addTranslation(TranslationRequestDTO translationRequestDTO) {
		TranslationEntity translation = new TranslationEntity();
		translation.setTranslation(translationRequestDTO.getTranslation());
		translation.setDescription(translationRequestDTO.getDescription());
		ExampleEntity example = new ExampleEntity();
		example.setExample(translationRequestDTO.getExample().getExample());
		translation.setExample(example);
		WordEntity word = wordService.getWord(translationRequestDTO.getWord_id());
		translation.setWord(word);
		TranslationEntity translationToSave = translationRepository.save(translation);
		return Mapper.translationToTranslationDTO(translationToSave);
	}
	
	public EntireTranslationResponseDTO addEntireTranslation(EntireTranslationRequestDTO requestDTO) {
		TranslationEntity translation = new TranslationEntity();
		translation.setTranslation(requestDTO.getTranslation());
		translation.setDescription(requestDTO.getDescription());
		WordEntity word = new WordEntity();
		word.setWord(requestDTO.getWord().getWord());
		word.setLanguage(requestDTO.getWord().getLanguage());
		word.setTranslationSet(requestDTO.getWord().getTranslationSet());
		translation.setWord(word);
		ExampleEntity example = new ExampleEntity();
		example.setExample(requestDTO.getExample().getExample());
		translation.setExample(example);

		if(wordService.checkIfWordExists(requestDTO.getWord().getWord()) == 1) { throw new WordAlreadyExistsException(); }
		translationRepository.save(translation);
	    return Mapper.entireTranslationToResponseDTO(translation);
	}

	@Override
	public void deleteTranslation(Long id) {
		if(!translationRepository.existsById(id)) {
			  throw new TranslationNotFoundException();
			}
		translationRepository.deleteById(id);
	}

	@Override
	public TranslationResponseDTO deleteExampleFromTranslation(Long id) {
		if(!translationRepository.existsById(id)) {
			  throw new TranslationNotFoundException();
			}
		TranslationEntity translation = getTranslation(id);
		translation.setExample(null);    
	    return Mapper.translationToTranslationDTO(translation);
	}

	
}
