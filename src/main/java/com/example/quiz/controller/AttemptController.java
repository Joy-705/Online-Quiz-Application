package com.example.quiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.model.Attempt;
import com.example.quiz.model.User;
import com.example.quiz.service.IUserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/quizzes")
@RestController
@RequiredArgsConstructor
public class AttemptController {
    private final IUserService userService;

    @PostMapping("/newAttempt")
    public ResponseEntity<User> newAttempt(@RequestParam Long id) throws NotFoundException {
        User user = userService.newAttempt(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/quiz/{id}/{qId}")
    public ResponseEntity<Attempt> quiz(@PathVariable Long id, @RequestBody List<String> answers,
            @PathVariable Long qId) throws NotFoundException {
        Attempt attempt = userService.quiz(id, answers, qId);
        return ResponseEntity.ok(attempt);
    }

}
