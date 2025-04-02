package com.exam.in.examsystem.controller;

import com.exam.in.examsystem.dto.QuestionResponse;
import com.exam.in.examsystem.model.Exam;
import com.exam.in.examsystem.model.Question;
import com.exam.in.examsystem.repository.ExamRepository;
import com.exam.in.examsystem.service.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;





@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamRepository examRepository;


    @PostMapping
    public ResponseEntity<?> addQuestion(HttpServletRequest request, @RequestBody Question question) {
        // Authorization Check (Assuming an isAdmin flag or role-based check)
        if (!isAdmin()) {
            return ResponseEntity.status(403).body(
                    createErrorResponse("Access denied. Only admins can add questions."));
        }

        // Input Validation: Check if question text is provided
        if (question.getQuestionText() == null || question.getQuestionText().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    createErrorResponse("Invalid input. The 'questionText' field is required."));
        }

        if (question.getExam() == null || question.getExam().getId() == null) {
            return ResponseEntity.badRequest().body(
                    createErrorResponse("Exam must be specified for the question."));
        }

        // Fetch the Exam to ensure it exists
        Exam exam = examRepository.findById(question.getExam().getId())
                .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + question.getExam().getId()));

        // Set the exam for the question
        question.setExam(exam);

        // Save the question
        Question savedQuestion = null;
        try {
            savedQuestion = questionService.addQuestion(request, question);
            return ResponseEntity.ok(createSuccessResponse(savedQuestion));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Create success response

    }

    @GetMapping
    public ResponseEntity<?> fetchQuestions(@RequestParam int page, @RequestParam int size) {
        Page<Question> questions = questionService.getQuestions(page, size);
        QuestionResponse response = new QuestionResponse(questions);
        return ResponseEntity.ok(response);
    }

    // Simulated Admin Check (Replace with actual logic or security check)
    private boolean isAdmin() {
        // Example: Check user role (use Spring Security or a role field in User)
        return true;  // Change to actual role check
    }

    // Success Response
    private Map<String, Object> createSuccessResponse(Question question) {
        Map<String, Object> response = new HashMap<>();
        response.put("questionId", question.getQuestionid());
        response.put("examId", question.getExam().getId());
        response.put("questionText", question.getQuestionText());
        response.put("options", question.getOptions());
        response.put("correctOption", question.getCorrectOption());
        response.put("marks", question.getMarks());
        response.put("message", "Question added successfully.");
        return response;
    }

    // Error Response
    private Map<String, String> createErrorResponse(String errorMessage) {
        Map<String, String> response = new HashMap<>();
        response.put("error", errorMessage);
        return response;
    }

}
