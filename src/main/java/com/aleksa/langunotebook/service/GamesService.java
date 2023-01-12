package com.aleksa.langunotebook.service;

import java.util.List;

import com.aleksa.langunotebook.controller.dto.response.GamesResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.SynonymResponseDTO;

public interface GamesService {

	public List<GamesResponseDTO> learnWithFlashcards(int numberOfMeanings);
	public SynonymResponseDTO guessEnglishWordBySynonym(String word);

}
