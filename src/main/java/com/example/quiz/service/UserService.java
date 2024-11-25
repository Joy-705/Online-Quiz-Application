package com.example.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.quiz.model.Attempt;
import com.example.quiz.model.Question;
import com.example.quiz.model.User;
import com.example.quiz.model.UserLogin;
import com.example.quiz.repository.AttemptRepository;
import com.example.quiz.repository.QuestionRepository;
import com.example.quiz.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final AttemptRepository attemptRepository;
    private final QuestionRepository questionRepository;

    @Override
    public Optional<User> getUser(UserLogin user) throws NotFoundException {
        Optional<User> foundUser = userRepository.findById(user.getId());

        if (foundUser.isPresent()) {
            User existingUser = foundUser.get();
            if (user.getPassword().equals(existingUser.getPassword())) {
                return foundUser;
            } else {
                throw new NotFoundException();
            }
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User newAttempt(Long id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User theUser = user.get();
            int size = theUser.getAttempt().size();
            Attempt attempt = new Attempt();
            attempt.setAttempt(size);
            attempt.setScore(0);
            List<Attempt> userAttempt = theUser.getAttempt();
            userAttempt.add(attempt);
            theUser.setAttempt(userAttempt);
            return userRepository.save(theUser);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public Attempt quiz(Long id, List<String> answers, Long qId) throws NotFoundException {
        Optional<Attempt> attempt = attemptRepository.findById(id);
        Optional<Question> question = questionRepository.findById(qId);
        if (attempt.isPresent() && question.isPresent()) {
            Attempt theAttempt = attempt.get();
            Question theQuestion = question.get();
            if (theQuestion.getCorrectAnswers() == answers) {
                theAttempt.setScore(theAttempt.getScore() + 1);
            } else {
                theAttempt.setScore(theAttempt.getScore());
            }
            return attemptRepository.save(theAttempt);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    public User updateAdmin(Long id, String password) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User theUser = user.get();
            if (theUser.getPassword().equals(password)) {
                theUser.setAdmin(true);
                return theUser;
            } else
                throw new ChangeSetPersister.NotFoundException();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }
}
