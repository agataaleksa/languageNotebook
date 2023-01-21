package com.aleksa.langunotebook.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aleksa.langunotebook.controller.dto.Language;
import com.aleksa.langunotebook.model.MeaningEntity;

@Repository
public interface MeaningRepository extends JpaRepository<MeaningEntity, Long> {
    
    @Query(value = "SELECT COUNT(me) FROM MeaningEntity me WHERE LOWER(meaning) = LOWER(:meaning) AND word.id = :wordId")
	public int checkIfMeaningExists(String meaning, Long wordId);
    
    @Query(value = "SELECT me FROM MeaningEntity me WHERE word.language = :language")
	public List<MeaningEntity> filterByLanguage(Language language, Pageable pageable);
    
    @Query(value = "SELECT me FROM MeaningEntity me WHERE word.category = :category")
	public List<MeaningEntity> filterByCategory(String category, Pageable pageable);
    
    @Query(value = "SELECT me FROM MeaningEntity me WHERE word.language = :language AND word.category = :category")
	public List<MeaningEntity> filterByLanguageAndCategory(Language language, String category, Pageable pageable);

    @Query(value = "SELECT me FROM MeaningEntity me ORDER BY RAND()")
	public List<MeaningEntity> getMeaningsShuffled(Pageable pageable);
   
}
