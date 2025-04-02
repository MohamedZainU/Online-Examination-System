package com.exam.in.examsystem.service;

import com.exam.in.examsystem.model.Answer;
import com.exam.in.examsystem.model.Question;
import com.exam.in.examsystem.model.Result;
import com.exam.in.examsystem.model.User;
import com.exam.in.examsystem.repository.AnswerRepository;
import com.exam.in.examsystem.repository.QuestionRepository;
import com.exam.in.examsystem.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private EmailRetrivalService emailRetrivalService;

//    public User findUserByUsername(String username) {
//        return userRepository.findByName(username).get();
//    }

    public List<Answer> getAnswersByUserId(Long userId) {
        return answerRepository.findByUser_UserId(userId);
    }

//    public List<Answer> submitAnswers(List<Answer> answers, Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//
//        for (Answer answer : answers) {
//            answer.setUser(user);
//            Question question = questionRepository.findById(answer.getQuestionId())
//                    .orElseThrow(() -> new RuntimeException("Question not found"));
//            answer.setQuestion(question);
//        }
//
//        return answerRepository.saveAll(answers);
//    }

    public Result calculateScore(HttpServletRequest request, List<Answer> submittedAnswers, List<Question> questions) {
        String email=emailRetrivalService.extractUserName(request);
        User user = userRepository.findByEmail(email).get();
        int score=0;
        int correctAnswers = 0;

        for (Answer answer : submittedAnswers) {
            // Debug: Print the submitted Answer's questionId
            System.out.println("Submitted Answer Question ID: " + answer.getQuestionOfId());

            Question question = questions.stream()
                    .filter(q -> {
                        // Debug: Print each Question's ID being compared
                        System.out.println("Comparing with Question ID: " + q.getQuestionid());
                        return q.getQuestionid().equals(answer.getQuestionOfId());
                    })
                    .findFirst()
                    .orElse(null);

            if (question != null) {
                answer.setUser(user);
                answer.setQuestion(question);
                if(user.getRole().toString().equals("USER")){
                    answerRepository.save(answer);
                }
                String correctOptionText = question.getOptions().get(question.getCorrectOption()).trim().toLowerCase();
                String selectedAnswerText = answer.getSelectedAns().trim().toLowerCase();


                // Debug: Print the correct option and the selected answer
                System.out.println("Correct Option Text: " + correctOptionText);
                System.out.println("Selected Answer Text: " + selectedAnswerText);

                if (correctOptionText.equals(selectedAnswerText)) {
                    score += question.getMarks();
                    correctAnswers++;
                }
            } else {
                // Debug: If no matching question was found
                System.out.println("No matching question found for Answer Question ID: " + answer.getQuestionOfId());
            }
        }

        // Assuming 1 mark per correct answer
        return new Result(submittedAnswers.size(), correctAnswers, score);
    }

}
