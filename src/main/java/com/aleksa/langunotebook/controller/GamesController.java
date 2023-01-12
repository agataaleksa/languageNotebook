package com.aleksa.langunotebook.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleksa.langunotebook.controller.dto.response.GamesResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.SynonymResponseDTO;
import com.aleksa.langunotebook.service.GamesService;

@RestController
@RequestMapping("/api/games")
public class GamesController {

	private final GamesService gamesService;
	
	public GamesController(GamesService gamesService) {
		this.gamesService = gamesService;
	}

	@GetMapping("/flashcards/{numberOfFlashcards}")
	public List<GamesResponseDTO> learnWithFlashcards(@PathVariable int numberOfFlashcards) {
		return gamesService.learnWithFlashcards(numberOfFlashcards) ;
	}
	
	@GetMapping("/synonyms/{word}")
	public SynonymResponseDTO guessEnglishWordBySynonym(@PathVariable String word) {
		return gamesService.guessEnglishWordBySynonym(word);
	}
}
