package com.example.quiz.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Question required")
    private String question;
    @NotBlank(message = "Please Enter Subject")
    private String subject;
    @NotBlank(message = "Please enter Question Type")
    private String questionType;

    @ElementCollection
    @NotBlank(message = "Please enter the choices")
    private List<String> choices;
    @ElementCollection
    @NotBlank(message = "Enter atleast 1 correct answer")
    private List<String> correctAnswers;
}
