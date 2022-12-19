package com.aleksa.langunotebook.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.aleksa.langunotebook.controller.dto.Mapper;
import com.aleksa.langunotebook.controller.dto.request.response.ExampleResponseDTO;
import com.aleksa.langunotebook.dao.entity.ExampleEntity;
import com.aleksa.langunotebook.repository.ExampleRepository;

@Service
public class ExampleServiceImpl implements ExampleService {

	
	private final ExampleRepository exampleRepository;
	
	public ExampleServiceImpl(ExampleRepository exampleRepository) {
		this.exampleRepository = exampleRepository;
	}

	@Override
	public ExampleEntity getExample(Long id) {
		return exampleRepository.findById(id).orElseThrow(NoSuchElementException::new);	
	}
	
	@Override
	public ExampleResponseDTO getExampleById(Long id) {
		ExampleEntity example = exampleRepository.findById(id).orElseThrow(NoSuchElementException::new);	
		return Mapper.exampleToExampleDTO(example);
		
	}
	
	@Override
	public List<ExampleResponseDTO> getExamples() {
		return Mapper.examplesToExampleDTO(exampleRepository.findAll());
	}
}
