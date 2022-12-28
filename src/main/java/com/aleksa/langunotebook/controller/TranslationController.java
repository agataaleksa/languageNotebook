package com.aleksa.langunotebook.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aleksa.langunotebook.controller.dto.request.TranslationRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.WordRequestDTO;
import com.aleksa.langunotebook.controller.dto.Language;
import com.aleksa.langunotebook.controller.dto.request.ExampleRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.MeaningRequestDTO;
import com.aleksa.langunotebook.controller.dto.response.MeaningResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.TranslationResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.WordResponseDTO;
import com.aleksa.langunotebook.service.ExampleServiceImpl;
import com.aleksa.langunotebook.service.MeaningServiceImpl;
import com.aleksa.langunotebook.service.WordServiceImpl;

@RestController
@RequestMapping("/api")
public class TranslationController {
	
	private final WordServiceImpl wordService;
	private final MeaningServiceImpl meaningService;
	private final ExampleServiceImpl exampleService;
	
	public TranslationController(WordServiceImpl wordService, MeaningServiceImpl meaningService,
			ExampleServiceImpl exampleService) {
		this.wordService = wordService;
		this.meaningService = meaningService;
		this.exampleService = exampleService;
	}

	@PostMapping("/translation")
    public ResponseEntity<TranslationResponseDTO> addTranslation(@Valid @RequestBody final TranslationRequestDTO requestDTO) {
		return new ResponseEntity<>(meaningService.addTranslation(requestDTO),HttpStatus.CREATED);
	}

	@PostMapping("/word")
	public WordResponseDTO addWord(@Valid @RequestBody final WordRequestDTO wordRequestDTO) {
		return wordService.addWord(wordRequestDTO);
	}

	@PostMapping("/word/{wordId}/meaning")
	public ResponseEntity<MeaningResponseDTO> addMeaningById(@PathVariable Long wordId, @Valid @RequestBody final MeaningRequestDTO requestDTO) {
		return new ResponseEntity<>(meaningService.addMeaningById(wordId, requestDTO), HttpStatus.CREATED);
	}
	
	@PostMapping("/meaning/{word}")
	public ResponseEntity<TranslationResponseDTO> addMeaningByWord(@Valid @PathVariable String word, @Valid @RequestBody final TranslationRequestDTO requestDTO) {
		return new ResponseEntity<>(meaningService.addMeaning(word, requestDTO), HttpStatus.CREATED);
	}

	@GetMapping("/meaning/{id}")
	public ResponseEntity<MeaningResponseDTO> getMeaningById(@Valid @PathVariable Long id) {
		return new ResponseEntity<>(meaningService.getMeaningById(id), HttpStatus.ACCEPTED);
	}

    @GetMapping("/meaning")
	public ResponseEntity<List<MeaningResponseDTO>> getMeaningsByWord(@Valid @RequestParam(required = true) String word) {
		return new ResponseEntity<>(meaningService.getMeaningsByWord(word), HttpStatus.ACCEPTED);
	}
    
    @GetMapping("/word")
    public ResponseEntity<WordResponseDTO> getWordByMeaning(@Valid @RequestParam(required = true) String meaning) {
    	return new ResponseEntity<>(meaningService.getWordByMeaning(meaning), HttpStatus.ACCEPTED);
    }
    
	@GetMapping("/translations")
	public ResponseEntity<List<TranslationResponseDTO>> getTranslations(@Valid @RequestParam(defaultValue = "0") int offset, 
			@Valid @RequestParam(defaultValue = "10") int pageSize, @Valid @RequestParam(defaultValue = "word") String field) {
		  return new ResponseEntity<>(meaningService.getTranslations(offset, pageSize, field), HttpStatus.ACCEPTED);
	  }

	@GetMapping("/translations/filterBy")
	public ResponseEntity<List<MeaningResponseDTO>> getTranslationsByLanguageAndCategory
	                          (@Valid @RequestParam(value="language", required = true) Language language, @Valid @RequestParam(value="category", required = false) String category) {
		return new ResponseEntity<>(meaningService.getTranslationsByLanguageAndCategory(language, category), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/meaning/{id}")
    public ResponseEntity<Void> deleteMeaningById(@Valid @PathVariable Long id) {
		meaningService.deleteMeaning(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/translation/{id}")
	public ResponseEntity<Void> deleteTranslationById(@Valid @PathVariable Long id) {
		meaningService.deleteTranslation(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/translation/{id}")
	public ResponseEntity<Void> updateTranslation(@Valid @PathVariable Long id, @Valid @RequestBody final TranslationRequestDTO requestDTO) {
		meaningService.updateTranslation(id, requestDTO);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/example/{id}")
	public ResponseEntity<Void> updateExampleById(@PathVariable Long id, @Valid @RequestBody final ExampleRequestDTO requestDTO) {
		exampleService.updateExampleById(id, requestDTO);
		return ResponseEntity.ok().build();
	}
}