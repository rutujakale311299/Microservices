package com.questionservice.QuestionService.controller;

import com.questionservice.QuestionService.model.Question;
import com.questionservice.QuestionService.model.QuestionWrapper;
import com.questionservice.QuestionService.model.Response;
import com.questionservice.QuestionService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    //generate quiz
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category, @RequestParam Integer numOfQ) {
        return questionService.getQuestionsForQuiz(category, numOfQ);
    }

    //get questions by Id's

    @PostMapping("getQuestionsForId")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(@RequestBody List<Integer> questionIds) {
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsByIds(questionIds);
    }


    //get score

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return questionService.getScore(responses);
    }

    @GetMapping("byCategory/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("byId/{id}")
    public ResponseEntity<Question> getQuestionsById(@PathVariable Integer id) {

        return questionService.getQuestionById(id);
    }


    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @PutMapping("update/{id}")
    public Question updateQuestion(@RequestBody Question question, @PathVariable Integer id) {
        return questionService.updateQuestion(question, id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteQuestion(@PathVariable Integer id) {

        questionService.deleteQuestion(id);
    }


}