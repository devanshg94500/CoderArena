package com.devansh.codearena.dto;

import com.devansh.codearena.entity.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ProblemRequest {

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 255)
    private String title;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Difficulty is required")
    private Difficulty difficulty;

    @Positive(message = "Time limit must be greater than 0")
    private Integer timeLimit;

    @Positive(message = "Memory limit must be greater than 0")
    private Integer memoryLimit;

    private String inputFormat;

    private String outputFormat;

    private String constraints;

    private String starterCodeJava;

    private String starterCodeCpp;

    private String starterCodePython;

    private String solution;

    @NotNull
    private List<String> tags;

    @NotNull
    private List<TestCaseRequest> testCases;
}