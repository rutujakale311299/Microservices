package com.questionservice.QuestionService.service;

import com.questionservice.QuestionService.model.Question;
import com.questionservice.QuestionService.model.QuestionWrapper;
import com.questionservice.QuestionService.model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
    ResponseEntity<List<Question>> getAllQuestions();

    ResponseEntity<List<Question>> getQuestionsByCategory(String category);

    ResponseEntity<String>addQuestion(Question question);

    Question updateQuestion(Question question,Integer id);

    void deleteQuestion(Integer id);

    ResponseEntity<Question> getQuestionById(Integer id);

    ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numOfQ);

    ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(List<Integer> questionIds);

    ResponseEntity<Integer> getScore(List<Response> responses);
}
