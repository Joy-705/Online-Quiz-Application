package com.example.quiz.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Entity
public class UserLogin {
    @NotBlank
    private Long id;
    @NotBlank
    private String password;
}
