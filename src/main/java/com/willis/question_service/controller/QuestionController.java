package com.willis.question_service.controller;


import com.willis.question_service.model.Question;
import com.willis.question_service.model.QuestionWrapper;
import com.willis.question_service.model.Response;
import com.willis.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addQuestions(@RequestBody Question question){
        return questionService.addQuestions(question);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestions(@PathVariable Integer id){
        return questionService.deleteQuestions(id);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateQuestions(@RequestBody Question question){
        return questionService.updateQuestions(question);
    }
    //generate
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions){
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }


    //getQuestions (questionId)
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        return questionService.getQuestionsFromId(questionIds);
    }
    //getScore
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);

    }
}
