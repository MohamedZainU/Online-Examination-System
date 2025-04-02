package com.exam.in.examsystem.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class ResultDTO {
    @JsonProperty("examId")
    private Long examId;
    @JsonProperty("examName")
    private String examName;
    @JsonProperty("dateTaken")
    private LocalDate dateTaken;
    @JsonProperty("score")
    private int score;
    @JsonProperty("status")
    private String status;

    public ResultDTO() {}

    public ResultDTO(Long examId, String examName, LocalDate dateTaken, int score, String status) {
        this.examId = examId;
        this.examName = examName;
        this.dateTaken = dateTaken;
        this.score = score;
        this.status = status;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public LocalDate getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(LocalDate dateTaken) {
        this.dateTaken = dateTaken;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "examId=" + examId +
                ", examName='" + examName + '\'' +
                ", dateTaken=" + dateTaken +
                ", score=" + score +
                ", status='" + status + '\'' +
                '}';
    }
}