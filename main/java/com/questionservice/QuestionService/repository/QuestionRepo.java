package com.questionservice.QuestionService.repository;

import com.questionservice.QuestionService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question,Integer> {
    List<Question> findQuestionsByCategory(String category);

    @Query (value ="SELECT q.id FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numOfQ",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, Integer numOfQ);
}
