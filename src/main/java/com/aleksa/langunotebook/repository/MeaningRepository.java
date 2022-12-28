package com.aleksa.langunotebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.aleksa.langunotebook.dao.entity.MeaningEntity;

@Repository
public interface MeaningRepository extends JpaRepository<MeaningEntity, Long> {
    
    @Query(value = "SELECT COUNT(me) FROM MeaningEntity me WHERE meaning = :meaning AND word_id = :wordId")
	public int checkIfMeaningExists(String meaning, Long wordId);

}
