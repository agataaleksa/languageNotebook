package com.aleksa.langunotebook.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplcationExceptionHandler {
	
	
	// HANDLING VALIDATOR-ERRORS:
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
		.forEach(error -> {errorMap.put(error.getField(), error.getDefaultMessage()); });
		return errorMap;
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(WordNotFoundException.class)
	public String WordNotFoundHandler(WordNotFoundException ex) {
		return ex.getMessage();
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(TranslationNotFoundException.class)
	public String TranslationNotFoundHandler(TranslationNotFoundException ex) {
		return ex.getMessage();
	}
	

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(WordAlreadyExistsException.class)
	public String WordAlreadyExistsHandler(WordAlreadyExistsException ex) {
		return ex.getMessage();
	}
}
