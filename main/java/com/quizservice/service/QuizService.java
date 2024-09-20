package com.quizservice.service;

import com.quizservice.model.QuestionWrapper;
import com.quizservice.model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {
    ResponseEntity<String> createQuiz(String category, Integer numQ, String quizTitle);

  public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id);

    ResponseEntity<Integer> submitQuiz(Integer id, List<Response> responses);
}
