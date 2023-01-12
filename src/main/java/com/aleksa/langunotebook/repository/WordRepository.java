package com.aleksa.langunotebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.aleksa.langunotebook.dao.entity.WordEntity;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Long> {
	
	@Query(value = "SELECT COUNT(we) FROM WordEntity we WHERE LOWER(word) = LOWER(:word)")
	public int checkIfWordExists(String word);
	
	@Query(value = "SELECT id FROM WordEntity WHERE word = :word")
	public int findWordsId(String word);
	
	@Query(value = "SELECT we FROM WordEntity we  WHERE LOWER(word) = LOWER(:word)")
	public WordEntity findWordByWord(String word);

}
