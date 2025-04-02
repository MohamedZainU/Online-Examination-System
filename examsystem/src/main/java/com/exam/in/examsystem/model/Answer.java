package com.exam.in.examsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long questionOfId;  // This should map to the JSON input

	private String selectedAns;

	@ManyToOne
	@JoinColumn(name = "questionid", referencedColumnName = "questionid")
	private Question question;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	public Answer() {}

//	public Question getQuestion() {
//		return question;
//	}
//
//	public void setQuestion(Question question) {
//		this.question = question;
//	}
//
//	// Getters and Setters
//
//	public Long getQuestionOfId() {
//		return questionOfId;
//	}
//
//	public void setQuestionOfId(Long questionOfId) {
//		this.questionOfId = questionOfId;
//	}
//
//
//	public String getSelectedAns() {
//		return selectedAns;
//	}
//
//	public void setSelectedAns(String selectedAns) {
//		this.selectedAns = selectedAns;
//	}
}
