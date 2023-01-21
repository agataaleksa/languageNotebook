package com.aleksa.langunotebook.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aleksa.langunotebook.controller.dto.Word;
import com.aleksa.langunotebook.controller.dto.response.GamesResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.GamesResponseDTOFactory;
import com.aleksa.langunotebook.controller.dto.response.SynonymResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.SynonymResponseDTOFactory;
import com.aleksa.langunotebook.model.MeaningEntity;
import com.aleksa.langunotebook.model.WordEntity;
import com.aleksa.langunotebook.repository.MeaningRepository;
import com.aleksa.langunotebook.repository.WordRepository;

@Service
public class GamesServiceImpl implements GamesService {
	
	private final MeaningRepository meaningRepository;
	private final WordRepository wordRepository;
	private final RestTemplate restTemplate;
	private final String URL = "https://api.datamuse.com/words?ml=";
	
	public GamesServiceImpl(MeaningRepository meaningRepository, WordRepository wordRepository,
			RestTemplate restTemplate) {
		this.meaningRepository = meaningRepository;
		this.wordRepository = wordRepository;
		this.restTemplate = restTemplate;
	}
	
	@Override
	@Cacheable(cacheNames = "learnWithFlashcards")
	public List<GamesResponseDTO> learnWithFlashcards(int numberOfMeanings)  {
		Pageable paging = PageRequest.of(0, numberOfMeanings);
		List<MeaningEntity> randomMeanings = meaningRepository.getMeaningsShuffled(paging);

		return GamesResponseDTOFactory.create(randomMeanings);
	}

	@Override
	@Cacheable(cacheNames = "guessEnglishWordBySynonym")
	public SynonymResponseDTO guessEnglishWordBySynonym (String word) {
		    String wordUrl = URL + word;
		    WordEntity wordEntity = wordRepository.findWordByWord(word);
		    
		    Word[] result = restTemplate.getForObject(wordUrl, Word[].class);
		    String[] synonyms = {result[0].getWord(), result[1].getWord(), result[2].getWord()};
		   return SynonymResponseDTOFactory.create(wordEntity, synonyms);
		}
	
	}
	



