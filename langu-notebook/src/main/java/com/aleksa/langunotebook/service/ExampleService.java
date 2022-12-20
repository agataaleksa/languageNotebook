package com.aleksa.langunotebook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aleksa.langunotebook.controller.dto.response.ExampleResponseDTO;
import com.aleksa.langunotebook.dao.entity.ExampleEntity;

@Service
public interface ExampleService {

	 public ExampleEntity getExample(Long id);
	 public ExampleResponseDTO getExampleById(Long id);
	 public List<ExampleResponseDTO> getExamples();
	 
}
