package com.questionservice.QuestionService.service;

import com.questionservice.QuestionService.model.Question;
import com.questionservice.QuestionService.model.QuestionWrapper;
import com.questionservice.QuestionService.model.Response;
import com.questionservice.QuestionService.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    @Override
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepo.findQuestionsByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);


    }

    @Override
    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionRepo.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return new ResponseEntity<>("Not added", HttpStatus.CREATED);

    }

    @Override
    public Question updateQuestion(Question question, Integer id) {
        Question existingQue = questionRepo.findById(question.getId()).get();
        existingQue.setCategory(question.getCategory());
        existingQue.setQuestionTitle(question.getQuestionTitle());
        existingQue.setDifficultyLevel(question.getDifficultyLevel());
        existingQue.setRightAnswer(question.getRightAnswer());
        existingQue.setOption1(question.getOption1());
        existingQue.setOption2(question.getOption2());
        existingQue.setOption3(question.getOption3());
        existingQue.setOption4(question.getOption4());
        Question updatedQuestion = questionRepo.save(existingQue);
        return updatedQuestion;

    }

    @Override
    public void deleteQuestion(Integer id) {
        questionRepo.deleteById(id);
    }

    @Override
    public ResponseEntity<Question> getQuestionById(Integer id) {
        Optional<Question> existingOne = questionRepo.findById(id);
        return new ResponseEntity<>(existingOne.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numOfQ) {
        List<Integer> question = questionRepo.findRandomQuestionsByCategory(category, numOfQ);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = questionIds.stream().
                map(id -> questionRepo.findById(id)).
                filter(Optional::isPresent).map(Optional::get).map(question ->
                {
                    QuestionWrapper wrapper = new QuestionWrapper();
                    wrapper.setId(question.getId());
                    wrapper.setQuestionTitle(question.getQuestionTitle());
                    wrapper.setOption1(question.getOption1());
                    wrapper.setOption2(question.getOption2());
                    wrapper.setOption3(question.getOption3());
                    wrapper.setOption4(question.getOption4());
                    return wrapper;
                }).collect(Collectors.toList());

//       List<Question>questions=new ArrayList<>();
//       for(Integer id: questionIds){
//           questions.add(questionRepo.findById(id).get());
//       }
//       for(Question question:questions){
//           QuestionWrapper wrapper=new QuestionWrapper();
//           wrapper.setId(question.getId());
//           wrapper.setQuestionTitle(question.getQuestionTitle());
//           wrapper.setOption1(question.getOption1());
//           wrapper.setOption2(question.getOption2());
//           wrapper.setOption3(question.getOption3());
//           wrapper.setOption4(question.getOption4());
//           wrappers.add(wrapper);
//       }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right=0;

        for (Response response : responses) {
            Question question = questionRepo.findById(response.getId()).orElse(null);
            if (question != null && response.getResponses()!=null && response.getResponses().equals(question.getRightAnswer())) {
                right++;
            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}
