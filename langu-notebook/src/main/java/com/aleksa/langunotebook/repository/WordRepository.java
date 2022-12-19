package com.aleksa.langunotebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.aleksa.langunotebook.dao.entity.WordEntity;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Long> {
	
	@Query(value = "SELECT COUNT(*) FROM word_entity WHERE word = ?1", nativeQuery = true)
	public int checkIfWordExists(String word);

}
