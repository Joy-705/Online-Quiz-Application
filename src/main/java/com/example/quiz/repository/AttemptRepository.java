package com.example.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quiz.model.Attempt;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {

}
