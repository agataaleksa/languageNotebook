package com.aleksa.langunotebook.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aleksa.langunotebook.controller.dto.request.ExampleRequestDTO;
import com.aleksa.langunotebook.controller.dto.response.ExampleResponseDTO;
import com.aleksa.langunotebook.controller.dto.response.ExampleResponseDTOFactory;
import com.aleksa.langunotebook.exception.ResourceNotFoundException;
import com.aleksa.langunotebook.model.ExampleEntity;
import com.aleksa.langunotebook.model.ExampleEntityFactory;
import com.aleksa.langunotebook.repository.ExampleRepository;

@Service
public class ExampleServiceImpl implements ExampleService {

	
	private final ExampleRepository exampleRepository;
	
	public ExampleServiceImpl(ExampleRepository exampleRepository) {
		this.exampleRepository = exampleRepository;
	}
	
	@Override
	public ExampleResponseDTO getExampleById(Long id) {
		ExampleEntity example = exampleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		return ExampleResponseDTOFactory.create(example);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	@CachePut(value = "exampleUpdate")
	public void updateExampleById(Long id, ExampleRequestDTO requestDTO) {
	   ExampleEntity example = exampleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
	   exampleRepository.save(ExampleEntityFactory.update(example, requestDTO));
	 }
	
}
