package com.exam.in.examsystem.service;

import com.exam.in.examsystem.exception.AccessDeniedException;
import com.exam.in.examsystem.exception.InvalidInputException;
import com.exam.in.examsystem.model.Question;
import com.exam.in.examsystem.model.User;
import com.exam.in.examsystem.repository.QuestionRepository;
import com.exam.in.examsystem.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private EmailRetrivalService emailRetrivalService;
    @Autowired
    private UserRepository userRepository;
    public Page<Question> getQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size));
    }

    public Question addQuestion(HttpServletRequest request, Question question) {
        String email = emailRetrivalService.extractUserName(request);
        User user = userRepository.findByEmail(email).get();
        if (user.getRole().toString().equals("ADMIN")) {
            // Validate if questionText is null or empty
            if (question.getQuestionText() == null || question.getQuestionText().trim().isEmpty()) {
                throw new InvalidInputException("Invalid input. The 'questionText' field is required.");
            }
            return questionRepository.save(question);
        }

        throw new AccessDeniedException("Access denied. Only admins can add questions.");
    }
}

