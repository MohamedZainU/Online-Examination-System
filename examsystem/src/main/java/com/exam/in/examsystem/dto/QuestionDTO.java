package com.exam.in.examsystem.dto;

import com.exam.in.examsystem.model.Question;

public class QuestionDTO {
    private Long id;
    private String questionText;
    private String options;
    private String correctAns;
    private int marks;

    public QuestionDTO(Question question) {
        this.id = question.getQuestionid();
        this.questionText = question.getQuestionText();
        this.options = question.getOptions().toString();
        this.correctAns = question.getOptions().get(question.getCorrectOption());
        this.marks = question.getMarks();
    }

    public Long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOptions() {
        return options;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public int getMarks() {
        return marks;
    }
}

