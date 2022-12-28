package com.aleksa.langunotebook.service;

import java.util.List;

import com.aleksa.langunotebook.controller.dto.request.ExampleRequestDTO;
import com.aleksa.langunotebook.controller.dto.response.ExampleResponseDTO;


public interface ExampleService {

	 public ExampleResponseDTO getExampleById(Long id);
	 public List<ExampleResponseDTO> getExamples();
	 public void updateExampleById(Long id, ExampleRequestDTO requestDTO);
}
