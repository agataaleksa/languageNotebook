package com.aleksa.langunotebook.controller.dto.response;


public class GamesResponseDTO {
	
	private String meaning;
	private String word;
	
	public GamesResponseDTO(String meaning, String word) {
		this.meaning = meaning;
		this.word = word;
	}
	
	public String getMeaning() {
		return meaning;
	}
	
	public String getWord() {
		return word;
	}
	
}
