package com.exam.in.examsystem.controller;

import com.exam.in.examsystem.model.Exam;
import com.exam.in.examsystem.model.Question;
import com.exam.in.examsystem.model.User;
import com.exam.in.examsystem.service.ExamService;
import com.exam.in.examsystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;
    @Autowired
    private UserService userService;
    @PostMapping("/schedule")
    public ResponseEntity<?> scheduleExam(HttpServletRequest request,@RequestBody Exam exam) throws Exception {
        Exam savedExam = examService.scheduleExam(request,exam);
        return ResponseEntity.ok(createSuccessResponse(savedExam));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExamById(@PathVariable Long id) {
        Exam exam = examService.getExamById(id);
        if (exam == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exam);
    }
    private Map<String, Object> createSuccessResponse(Exam exam) {
        Map<String, Object> response = new HashMap<>();
        response.put("ExamId", exam.getId());
        response.put("Exam Name", exam.getExamName());
        response.put("date", exam.getDate());
        response.put("start time", exam.getStartTime());
        response.put("endTime", exam.getEndTime());
        response.put("message", "Exam scheduled successfully.");
        return response;
    }
}