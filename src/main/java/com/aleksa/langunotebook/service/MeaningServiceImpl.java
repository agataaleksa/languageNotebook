package com.aleksa.langunotebook.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.aleksa.langunotebook.controller.dto.Language;
import com.aleksa.langunotebook.controller.dto.request.MeaningRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.TranslationRequestDTO;
import com.aleksa.langunotebook.controller.dto.response.MeaningResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.MeaningResponseDTOFactory;
import com.aleksa.langunotebook.controller.dto.response.TranslationResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.TranslationResponseDTOFactory;
import com.aleksa.langunotebook.controller.dto.response.WordResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.WordResponseDTOFactory;
import com.aleksa.langunotebook.dao.entity.ExampleEntity;
import com.aleksa.langunotebook.dao.entity.ExampleEntityFactory;
import com.aleksa.langunotebook.dao.entity.MeaningEntity;
import com.aleksa.langunotebook.dao.entity.MeaningEntityFactory;
import com.aleksa.langunotebook.dao.entity.WordEntity;
import com.aleksa.langunotebook.exception.ResourceNotFoundException;
import com.aleksa.langunotebook.repository.MeaningRepository;

@Service
@Transactional
public class MeaningServiceImpl implements MeaningService {
	
	private final MeaningRepository meaningRepository;
	private final WordService wordService;
	private final WordValidator wordValidator;
	private final MeaningValidator meaningValidator;

	
	public MeaningServiceImpl(MeaningRepository meaningRepository, WordService wordService, 
			WordValidator wordValidator, MeaningValidator meaningValidator) {
		this.meaningRepository = meaningRepository;
		this.wordService = wordService;
		this.wordValidator = wordValidator;
		this.meaningValidator = meaningValidator;
	}

	@Override
	public TranslationResponseDTO addTranslation(TranslationRequestDTO requestDTO) {
		MeaningEntity meaning = MeaningEntityFactory.create(requestDTO);

		wordValidator.validateWord(meaning.getWord().getWord());
		meaningRepository.save(meaning);
	    return TranslationResponseDTOFactory.create(meaning);
	}
	
	@Override
	public MeaningResponseDTO addMeaningById(Long wordId, MeaningRequestDTO requestDTO) {
		Optional<WordEntity> word = wordService.getWord(wordId);
		if (word.isEmpty()) { throw new ResourceNotFoundException("Word does not exist in the database."); }
		
		ExampleEntity example = ExampleEntityFactory.create(requestDTO.getExample());
		MeaningEntity meaning = MeaningEntityFactory.create(requestDTO, word.get(), example);
		
		meaningValidator.validateMeaning(meaning.getMeaning(), wordId);
		meaningRepository.save(meaning);
		return MeaningResponseDTOFactory.create(meaning);
	}
	
	@Override
	public TranslationResponseDTO addMeaning(String word, TranslationRequestDTO requestDTO) {
		Optional<WordEntity> existingWord = wordService.getWords().stream()
				.filter(w -> w.getWord().equalsIgnoreCase(word))
                .findFirst();
		
		if (existingWord.isEmpty()) { 
			TranslationResponseDTO dto = addTranslation(requestDTO);
			return dto;
			
		} else {
			MeaningEntity newMeaning = MeaningEntityFactory.create(requestDTO, existingWord.get());
			meaningRepository.save(newMeaning);
		    return TranslationResponseDTOFactory.create(newMeaning);
		    }
	}

	public MeaningEntity getMeaning(Long id) {
		MeaningEntity meaning = meaningRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return meaning;
    }
	
	@Override
	public MeaningResponseDTO getMeaningById(Long id) {
		if(!meaningRepository.existsById(id)) {
			  throw new ResourceNotFoundException();
			}
		return MeaningResponseDTOFactory.create(getMeaning(id));
	}
	
	@Override
	public List<MeaningResponseDTO> getMeaningsByWord(String word) {				
		wordValidator.findWord(word);
		List<MeaningEntity> meanings = meaningRepository.findAll().stream()
				.filter(m -> m.getWord().getWord().equals(word))
				.collect(Collectors.toList());
		
		if (meanings.isEmpty()) { throw new ResourceNotFoundException(); }
		 
	    return MeaningResponseDTOFactory.create(meanings);
	}
	
	@Override
    public WordResponseDTO getWordByMeaning(String meaning) {
		Optional<MeaningEntity> meaningEntity = meaningRepository.findAll().stream()
				.filter(m -> m.getMeaning().equalsIgnoreCase(meaning))
				.findFirst();
		
		if (meaningEntity.isEmpty()) { throw new ResourceNotFoundException(); }
		WordEntity wordEntity = meaningEntity.get().getWord();

		return WordResponseDTOFactory.create(wordEntity);
	}

	@Override
	public List<TranslationResponseDTO> getTranslations(int offset, int pageSize, String field) {
		  Pageable paging = PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field));
		  Page<MeaningEntity> pagedResult = meaningRepository.findAll(paging);
		  
		  return TranslationResponseDTOFactory.create(pagedResult.getContent());
	}
	
	@Override
	public List<TranslationResponseDTO> getTranslationsByLanguage(Language language, int offset, int pageSize) { 
		Pageable paging = PageRequest.of(offset, pageSize);
		List<MeaningEntity> filteredTranslations = meaningRepository.filterByLanguage(language, paging);
	 return TranslationResponseDTOFactory.create(filteredTranslations);
	}
	
	@Override
	public List<TranslationResponseDTO> getTranslationsByCategory(String category, int offset, int pageSize) { 
		Pageable paging = PageRequest.of(offset, pageSize);
		List<MeaningEntity> filteredTranslations = meaningRepository.filterByCategory(category, paging);
	 return TranslationResponseDTOFactory.create(filteredTranslations);
	}
	
	@Override
	public List<TranslationResponseDTO> getTranslationsByLanguageAndCategory(Language language, String category, int offset, int pageSize) { 
		Pageable paging = PageRequest.of(offset, pageSize);
		List<MeaningEntity> filteredTranslations = meaningRepository.filterByLanguageAndCategory(language, category, paging);
	 return TranslationResponseDTOFactory.create(filteredTranslations);
	}

	@Override
	public void deleteMeaning(Long id) {
		if(!meaningRepository.existsById(id)) {
			  throw new ResourceNotFoundException();
			  }
		meaningRepository.deleteById(id);
	}
	
	@Override
	public void deleteTranslation(Long id) {
		if (wordService.getWord(id).isEmpty()) { throw new ResourceNotFoundException(); }
		
		List<MeaningEntity> toBeDeletedList = meaningRepository.findAll().stream()
				.filter(m -> m.getWord().getId().equals(id))
				.collect(Collectors.toList());

		if (toBeDeletedList.isEmpty()) { wordService.deleteWord(id); 
		} else {
			toBeDeletedList.stream().forEach(m -> deleteMeaning(m.getId()));	
		    wordService.deleteWord(id);
		}
	}

	@Override
	@Transactional
	public void updateMeaning(Long id, MeaningRequestDTO requestDTO) {
		MeaningEntity meaning = MeaningEntityFactory.update(getMeaning(id), requestDTO);
		meaningRepository.save(meaning);
	}
	
	@Override
    public MeaningResponseDTO deleteExampleFromTranslation(Long id) {
        if (!meaningRepository.existsById(id)) {
            throw new ResourceNotFoundException(); }
        MeaningEntity meaning = getMeaning(id);
        meaning = MeaningEntityFactory.clearExample(meaning);
        return MeaningResponseDTOFactory.create(meaning);
    }
	
}

