package com.exam.in.examsystem.controller;

import com.exam.in.examsystem.model.Answer;
import com.exam.in.examsystem.model.Question;
import com.exam.in.examsystem.model.Result;
import com.exam.in.examsystem.service.AnswerService;
import com.exam.in.examsystem.service.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;


    @PostMapping("/submit")
    public ResponseEntity<Result> submitAnswers(HttpServletRequest request,@RequestBody List<Answer> answers) {

        List<Question> questions = questionService.getQuestions(0, Integer.MAX_VALUE).getContent(); // Fetch all questions
        Result result = answerService.calculateScore(request,answers, questions);
        return ResponseEntity.ok(result);

    }
}
