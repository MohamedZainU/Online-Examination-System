package com.exam.in.examsystem.controller;

import com.exam.in.examsystem.config.JwtService;
import com.exam.in.examsystem.service.DemoService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class DemoController {

    private final String secretKey = "YIujOJYxUAbV8681QvSYDhcwIiJ9dq86XCWDdhFVneg="; // Replace with your actual secret key

    @Autowired
    private JwtService jwtService;

    @Autowired
    private DemoService demoService;

    private String extractUserName(String authHeader){
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(token)
                        .getBody();
                return jwtService.extractUsername(token);
            } catch (JwtException e) {
                return null;
            }
        }
        return null;
    }
    @GetMapping("/data")
    public ResponseEntity<?> secureMethod(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String email = extractUserName(authHeader);
        return ResponseEntity.ok(demoService.getUserDetails(email));
    }
}