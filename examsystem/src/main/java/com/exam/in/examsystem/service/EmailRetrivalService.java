package com.exam.in.examsystem.service;

import com.exam.in.examsystem.config.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailRetrivalService {

    private final String secretKey = "YIujOJYxUAbV8681QvSYDhcwIiJ9dq86XCWDdhFVneg="; // Replace with your actual secret key

    @Autowired
    private JwtService jwtService;

    public String extractUserName(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
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
}
