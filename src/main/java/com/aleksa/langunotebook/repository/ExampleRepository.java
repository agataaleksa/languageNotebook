package com.aleksa.langunotebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksa.langunotebook.model.ExampleEntity;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {

	}
