package com.exam.in.examsystem.service;

import com.exam.in.examsystem.model.User;
import com.exam.in.examsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DemoService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserDetails(String email){
        return userRepository.findByEmail(email);
    }
}
