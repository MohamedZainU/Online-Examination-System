package com.exam.in.examsystem.controller;

import com.exam.in.examsystem.dto.ResultDTO;
import com.exam.in.examsystem.service.HistoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("/{userId}/history")
    public ResponseEntity<List<ResultDTO>> getUserHistory(HttpServletRequest request,@PathVariable Long userId) {
        List<ResultDTO> history = historyService.getUserHistory(request,userId);
        return ResponseEntity.ok(history);
    }
}

