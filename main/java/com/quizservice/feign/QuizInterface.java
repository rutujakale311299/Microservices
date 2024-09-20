package com.quizservice.feign;

import com.quizservice.model.QuestionWrapper;
import com.quizservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QuestionService")
public interface QuizInterface {
  @PostMapping("question/getScore")
  public ResponseEntity<Integer> getScore(List<Response> responses);

    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category, @RequestParam Integer numOfQ);

    @PostMapping("question/getQuestionsForId")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(List<Integer> questionIds);
}