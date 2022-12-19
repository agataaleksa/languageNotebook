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
import com.aleksa.langunotebook.controller.dto.request.EntireTranslationRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.WordRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.response.TranslationResponseDTO;
import com.aleksa.langunotebook.controller.dto.request.response.EntireTranslationResponseDTO;
import com.aleksa.langunotebook.controller.dto.request.response.ExampleResponseDTO;
import com.aleksa.langunotebook.controller.dto.request.response.WordResponseDTO;
import com.aleksa.langunotebook.dao.entity.TranslationEntity;
import com.aleksa.langunotebook.controller.dto.Mapper;
import com.aleksa.langunotebook.repository.ExampleRepository;
import com.aleksa.langunotebook.repository.TranslationRepository;
import com.aleksa.langunotebook.repository.WordRepository;
import com.aleksa.langunotebook.service.ExampleServiceImpl;
import com.aleksa.langunotebook.service.TranslationService;
import com.aleksa.langunotebook.service.TranslationServiceImpl;
import com.aleksa.langunotebook.service.WordService;
import com.aleksa.langunotebook.service.WordServiceImpl;

@RestController
@RequestMapping("/api/translation")
public class TranslationController {
	
	private final WordServiceImpl wordService;
	private final TranslationServiceImpl translationService;
	private final ExampleServiceImpl exampleService;
	
	
	
	public TranslationController(WordServiceImpl wordService, TranslationServiceImpl translationService,
			ExampleServiceImpl exampleService) {
		this.wordService = wordService;
		this.translationService = translationService;
		this.exampleService = exampleService;
	}
	
	@PostMapping("/add")
    public EntireTranslationResponseDTO addEntireTranslation(@Valid @RequestBody final EntireTranslationRequestDTO requestDTO) {
		return translationService.addEntireTranslation(requestDTO);
	}
	
	@PostMapping("/addMeaning")
	public TranslationResponseDTO addTranslationToExistingWord(@Valid @RequestBody final TranslationRequestDTO requestDTO) {
		return translationService.addTranslation(requestDTO);
	}

	@GetMapping("/all")
	public List<TranslationResponseDTO> getTranslations() {
		return translationService.getTranslations();
	}
	
	@GetMapping("/{id}")
	public TranslationResponseDTO getTranslactionById(@Valid @PathVariable Long id) {
		return translationService.getTranslactionById(id);
	}
	
	@GetMapping("/{word}")
	public List<TranslationResponseDTO> getTranslationsByWord(@Valid @PathVariable String word) {
		return translationService.getTranslationsByWord(word);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTranslation(@Valid @PathVariable Long id) {
		translationService.deleteTranslation(id);
		return ResponseEntity.noContent().build();
	}
	
}

	
	

	
	

