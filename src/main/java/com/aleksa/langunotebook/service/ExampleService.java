package com.aleksa.langunotebook.service;

import com.aleksa.langunotebook.controller.dto.request.ExampleRequestDTO;
import com.aleksa.langunotebook.controller.dto.response.ExampleResponseDTO;


public interface ExampleService {

	 public ExampleResponseDTO getExampleById(Long id);
	 public void updateExampleById(Long id, ExampleRequestDTO requestDTO);
}
