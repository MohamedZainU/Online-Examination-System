package com.exam.in.examsystem.service;

import com.exam.in.examsystem.config.JwtService;
import com.exam.in.examsystem.controller.AuthenticationRequest;
import com.exam.in.examsystem.controller.AuthenticationResponse;
import com.exam.in.examsystem.controller.RegisterRequest;
import com.exam.in.examsystem.model.User;
import com.exam.in.examsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest request) throws Exception {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new Exception("User Alredy Exists!");
        else {
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
//              return AuthenticationResponse.builder()
//                      .token(jwtToken)
//                      .build();
            return "User Registered Sucessfully!";
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new Exception("User Not Found!"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
