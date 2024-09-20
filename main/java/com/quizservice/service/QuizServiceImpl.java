package com.quizservice.service;

import com.quizservice.feign.QuizInterface;
import com.quizservice.model.Question;
import com.quizservice.model.QuestionWrapper;
import com.quizservice.model.Quiz;
import com.quizservice.model.Response;
import com.quizservice.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuizInterface quizInterface;


    @Override
    public ResponseEntity<String> createQuiz(String category, Integer numQ, String quizTitle) {

        List<Integer> question = quizInterface.getQuestionForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(quizTitle);
        quiz.setQuestionIds(question);
        quizRepo.save(quiz);
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz=quizRepo.findById(id).get();
        List<Integer>questionForIds=quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questionForUser=quizInterface.getQuestionsByIds(questionForIds);

        return questionForUser;
    }

    @Override
    public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> responses) {
        ResponseEntity<Integer> result=quizInterface.getScore(responses);

        return result;
    }


}
