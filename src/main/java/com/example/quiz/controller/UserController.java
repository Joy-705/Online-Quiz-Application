package com.example.quiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.model.User;
import com.example.quiz.model.UserLogin;
import com.example.quiz.service.IUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @PostMapping("/create-new-user")
    public User createNewUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return createdUser;
    }

    @GetMapping("/login")
    public ResponseEntity<Optional<User>> loginUser(@Valid @RequestBody UserLogin obj) throws NotFoundException {
        Optional<User> user = userService.getUser(obj);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> putMethodName(@PathVariable Long id, @RequestBody String password)
            throws NotFoundException {
        User user = userService.updateAdmin(id, password);
        return ResponseEntity.ok(user);
    }
}
