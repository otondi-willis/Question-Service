package com.willis.question_service.service;


import com.willis.question_service.QuestionServiceApplication;
import com.willis.question_service.dao.QuestionDao;
import com.willis.question_service.model.Question;
import com.willis.question_service.model.QuestionWrapper;
import com.willis.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addQuestions(Question question) {
        questionDao.save(question);

       try {
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e){
           e.printStackTrace();
       }
        return new ResponseEntity<>("Failed to add", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestions(Integer id) {
        questionDao.deleteById(id);
        try {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Deleted", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestions(Question question) {
        questionDao.save(question);
        try {
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName,numQuestions);

        return new ResponseEntity<>(questions,HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id : questionIds){
            questions.add(questionDao.findById(id).get());
        }

        for(Question question : questions){
            QuestionWrapper wrapper= new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestiontitle(question.getQuestiontitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {


        int right = 0;

        for(Response response:responses){
            Question question = questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightanswer()))
                right++;

        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
