package com.exam.in.examsystem.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class Result {
    private int totalQuestions;
    private int correctAnswers;
    private int score;

    public Result(int totalQuestions, int correctAnswers, int score) {
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.score = score;
    }

    
    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getScore() {
        return score;
    }
}

