package com.aleksa.langunotebook.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.aleksa.langunotebook.controller.dto.Language;
import com.aleksa.langunotebook.controller.dto.Mapper;
import com.aleksa.langunotebook.controller.dto.request.MeaningRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.TranslationRequestDTO;
import com.aleksa.langunotebook.controller.dto.response.MeaningResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.TranslationResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.WordResponseDTO;
import com.aleksa.langunotebook.dao.entity.ExampleEntity;
import com.aleksa.langunotebook.dao.entity.MeaningEntity;
import com.aleksa.langunotebook.dao.entity.WordEntity;
import com.aleksa.langunotebook.exception.ResourceNotFoundException;
import com.aleksa.langunotebook.exception.ValidationException;
import com.aleksa.langunotebook.repository.MeaningRepository;

@Service
public class MeaningServiceImpl implements MeaningService {
	
	private final MeaningRepository meaningRepository;
	private final WordService wordService;
	
	public MeaningServiceImpl(MeaningRepository meaningRepository, WordService wordService) {
		this.meaningRepository = meaningRepository;
		this.wordService = wordService;
	}

	@Override
	public TranslationResponseDTO addTranslation(TranslationRequestDTO requestDTO) {
		MeaningEntity meaning = new MeaningEntity();
		meaning.setMeaning(requestDTO.getMeaning());
		meaning.setDescription(requestDTO.getDescription());
		WordEntity word = new WordEntity();
		word.setWord(requestDTO.getWord().getWord());
		word.setLanguage(requestDTO.getWord().getLanguage());
		word.setCategory(requestDTO.getWord().getCategory());
		meaning.setWord(word);
		ExampleEntity example = new ExampleEntity();
		example.setExample(requestDTO.getExample().getExample());
		meaning.setExample(example);

		if(wordService.checkIfWordExists(requestDTO.getWord().getWord()) == 1) { 
			throw new ValidationException(wordService.findWordsId(word.getWord())); }
		meaningRepository.save(meaning);
	    return Mapper.translationToTranslationDTO(meaning);
	}
	
	@Override
	public MeaningResponseDTO addMeaningById(Long wordId, MeaningRequestDTO requestDTO) {
		MeaningEntity meaning = new MeaningEntity();
		meaning.setMeaning(requestDTO.getMeaning());
		meaning.setDescription(requestDTO.getDescription());
		ExampleEntity example = new ExampleEntity();
		example.setExample(requestDTO.getExample().getExample());
		meaning.setExample(example);
		WordEntity word = wordService.getWord(wordId).get();
		meaning.setWord(word);
		
		if(meaningRepository.checkIfMeaningExists(meaning.getMeaning(), word.getId()) == 1) { 
			throw new ValidationException(); } 
		MeaningEntity meaningToSave = meaningRepository.save(meaning);
		return Mapper.meaningToMeaningDTO(meaningToSave);
	}
	
	@Override
	public TranslationResponseDTO addMeaning(String word, TranslationRequestDTO requestDTO) {
		Optional<WordEntity> existingWord = wordService.getWords().stream()
				.filter(w -> w.getWord().equals(word))
                .findFirst();
		
		if (existingWord.isEmpty()) { 
			TranslationResponseDTO dto = addTranslation(requestDTO);
			return dto;
			
		} else {
			MeaningEntity newMeaning = new MeaningEntity();
			newMeaning.setMeaning(requestDTO.getMeaning());
			newMeaning.setDescription(requestDTO.getDescription());
			ExampleEntity example = new ExampleEntity();
			example.setExample(requestDTO.getExample().getExample());
			newMeaning.setExample(example);		
			newMeaning.setWord(existingWord.get());
			meaningRepository.save(newMeaning);
		
		TranslationResponseDTO dto =  Mapper.translationToTranslationDTO(newMeaning);
		return dto;
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
		
		return Mapper.meaningToMeaningDTO(getMeaning(id));
	}
	
	@Override
	public List<MeaningResponseDTO> getMeaningsByWord(String word) {				
		if(wordService.checkIfWordExists(word) == 0) { throw new ResourceNotFoundException(); }
		List<MeaningEntity> meanings = meaningRepository.findAll().stream()
				.filter(m -> m.getWord().getWord().equals(word))
				.collect(Collectors.toList());
		
		if (meanings.isEmpty()) { throw new ResourceNotFoundException(); }
		 
	    return Mapper.meaningsToMeaningDTO(meanings);  
	}
	
	@Override
    public WordResponseDTO getWordByMeaning(String meaning) {
		Optional<MeaningEntity> meaningEntity = meaningRepository.findAll().stream()
				.filter(m -> m.getMeaning().equals(meaning))
				.findFirst();
		
		if (meaningEntity.isEmpty()) { throw new ResourceNotFoundException(); }
		WordEntity wordEntity = meaningEntity.get().getWord();

		return Mapper.wordToWordResponseDTO(wordEntity);
	}

	@Override
	public List<TranslationResponseDTO> getTranslations(int offset, int pageSize, String field) {
		  Pageable paging = PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field));
		  Page<MeaningEntity> pagedResult = meaningRepository.findAll(paging);
		  
		  return Mapper.translationsToTranslationDTO(pagedResult.getContent());
	}
	
	@Override
	public List<MeaningResponseDTO> getTranslationsByLanguageAndCategory(Language language, String category) { 
	List<MeaningEntity> sortedList = meaningRepository.findAll().stream()
			.filter(m -> m.getWord().getCategory().equals(category))
			.filter(m -> m.getWord().getLanguage().equals(language))
			.collect(Collectors.toList());
	
	if (sortedList.isEmpty()) { throw new ResourceNotFoundException("No translations meeting required criteria."); }
	
	return Mapper.meaningsToMeaningDTO(sortedList);
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
	public void updateTranslation (Long id, TranslationRequestDTO requestDTO) {
		MeaningEntity meaning = getMeaning(id);
		if (meaning == null) { throw new ResourceNotFoundException(); }
		
		meaning.setMeaning(requestDTO.getMeaning());
		meaning.setDescription(requestDTO.getDescription());
		ExampleEntity example = new ExampleEntity();
		example.setExample(requestDTO.getExample().getExample());
		meaning.setExample(example);
		WordEntity word = new WordEntity();
		word.setWord(requestDTO.getWord().getWord());
		word.setLanguage(requestDTO.getWord().getLanguage());
		word.setCategory(requestDTO.getWord().getCategory());
		meaning.setWord(word);
		meaningRepository.save(meaning);
	}
	
}


	

