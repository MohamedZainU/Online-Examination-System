package com.exam.in.examsystem.repository;

import com.exam.in.examsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username
    Optional<User> findByName(String username);

    Optional<User> findById(long id);

    // Find user by email
    Optional<User> findByEmail(String email);

    // Check if a username exists
    boolean existsByName(String username);

    // Check if an email exists
    boolean existsByEmail(String email);

    // Fetch user along with their answers (if needed for specific use cases)
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.answers WHERE u.id = :userId")
    Optional<User> findUserWithAnswersById(@Param("userId") Long userId);
}