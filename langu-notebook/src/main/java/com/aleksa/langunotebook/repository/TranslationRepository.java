package com.aleksa.langunotebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleksa.langunotebook.dao.entity.TranslationEntity;

@Repository
public interface TranslationRepository extends JpaRepository<TranslationEntity, Long> {

	
}
