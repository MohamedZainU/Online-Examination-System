package com.exam.in.examsystem.service;

import com.exam.in.examsystem.model.Exam;
import com.exam.in.examsystem.model.User;
import com.exam.in.examsystem.repository.ExamRepository;
import com.exam.in.examsystem.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private EmailRetrivalService emailRetrivalService;
    @Autowired
    private UserRepository userRepository;

    public Exam scheduleExam(HttpServletRequest request,Exam exam) throws Exception {
        String email=emailRetrivalService.extractUserName(request);
        User user = userRepository.findByEmail(email).get();
        exam.setUser(user);
        if(user.getRole().toString().equals("USER"))
            return examRepository.save(exam);
        throw new Exception();
    }

    public Exam getExamById(Long id) {
        return examRepository.findById(id).orElse(null);
    }
}

