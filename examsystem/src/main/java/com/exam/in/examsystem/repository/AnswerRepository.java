package com.exam.in.examsystem.repository;

import com.exam.in.examsystem.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByUser_UserId(Long userId);
}
