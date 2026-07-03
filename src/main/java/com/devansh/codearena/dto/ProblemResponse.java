package com.devansh.codearena.dto;

import com.devansh.codearena.entity.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemResponse {

    private Long id;
    private String title;
    private String slug;
    private String description;
    private Difficulty difficulty;
    private Integer timeLimit;
    private Integer memoryLimit;
    private String inputFormat;
    private String outputFormat;
    private String constraints;
    private List<String> tags;
    private List<TestCaseResponse> testCases;

    private String starterCodeJava;
    private String starterCodeCpp;
    private String starterCodePython;
    private String solution;
}