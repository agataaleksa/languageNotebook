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
import com.aleksa.langunotebook.controller.dto.request.TranslationUpdateRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.EntireTranslationRequestDTO;
import com.aleksa.langunotebook.controller.dto.request.WordRequestDTO;
import com.aleksa.langunotebook.controller.dto.response.EntireTranslationResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.ExampleResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.TranslationResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.WordResponseDTO;
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
    public ResponseEntity<EntireTranslationResponseDTO> addEntireTranslation(@Valid @RequestBody final EntireTranslationRequestDTO requestDTO) {
		return new ResponseEntity<>(translationService.addEntireTranslation(requestDTO),HttpStatus.CREATED);
	}
	
	@PostMapping("/addMeaning")
	public ResponseEntity<TranslationResponseDTO> addTranslationToExistingWord(@Valid @RequestBody final TranslationRequestDTO requestDTO) {
		return new ResponseEntity<>(translationService.addTranslation(requestDTO), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<TranslationResponseDTO>> getTranslations() {
		return new ResponseEntity<>(translationService.getTranslations(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TranslationResponseDTO> getTranslactionById(@Valid @PathVariable Long id) {
		return new ResponseEntity<>(translationService.getTranslactionById(id), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{word}")
	public ResponseEntity<List<TranslationResponseDTO>> getTranslationsByWord(@Valid @PathVariable String word) {
		return new ResponseEntity<>(translationService.getTranslationsByWord(word), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTranslation(@Valid @PathVariable Long id) {
		translationService.deleteTranslation(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{translation_id}")
	public ResponseEntity<Void> updateTranslation(@Valid @PathVariable Long translation_id, @Valid @RequestBody final TranslationUpdateRequestDTO requestDTO) {
		translationService.updateTranslation(translation_id, requestDTO);
		return ResponseEntity.ok().build();
	}
	
	
}

	
	

	
	

