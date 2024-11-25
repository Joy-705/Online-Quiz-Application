package com.example.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.quiz.model.Attempt;
import com.example.quiz.model.User;
import com.example.quiz.model.UserLogin;

public interface IUserService {
    Optional<User> getUser(UserLogin user) throws NotFoundException;

    User createUser(User user);

    User newAttempt(Long id) throws NotFoundException;

    Attempt quiz(Long id, List<String> answers, Long qId) throws NotFoundException;

    User updateAdmin(Long id, String password) throws NotFoundException;
}
