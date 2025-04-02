package com.exam.in.examsystem.service;

import com.exam.in.examsystem.dto.ResultDTO;
import com.exam.in.examsystem.model.*;
import com.exam.in.examsystem.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryService {
    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailRetrivalService emailRetrivalService;

    @Autowired
    private QuestionService questionService;

    public List<ResultDTO> getUserHistory(HttpServletRequest request,Long userId) {
        List<Answer> userAnswers = answerService.getAnswersByUserId(userId);
        List<Question> questions = questionService.getQuestions(0, Integer.MAX_VALUE).getContent();

        return userAnswers.stream()
                .collect(Collectors.groupingBy(answer -> answer.getQuestion().getExam().getId()))
                .entrySet().stream()
                .map(entry -> {
                    Long examId = entry.getKey();
                    List<Answer> answers = entry.getValue();
                    //HttpServletRequest request = "History needs to be implemented";
                    Result result = answerService.calculateScore(request,answers, questions);

                    Exam exam = questions.stream()
                            .filter(q -> q.getExam().getId().equals(examId))
                            .findFirst()
                            .map(Question::getExam)
                            .orElse(null);

                    if (exam == null) {
                        return null;
                    }

                    return new ResultDTO(examId, exam.getExamName(), exam.getDate(), result.getScore(), result.getScore() >= 50 ? "Pass" : "Fail");
                })
                .filter(resultDTO -> resultDTO != null)
                .collect(Collectors.toList());
    }
}
