package com.exam.in.examsystem.repository;

import com.exam.in.examsystem.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    // Find exams by exam name
    List<Exam> findByExamName(String examName);

    // Find exams scheduled on a specific date
    List<Exam> findByDate(LocalDate date);

    // Check if an exam exists by name and date
    boolean existsByExamNameAndDate(String examName, LocalDate date);
}
