package com.devansh.codearena.mapper;

import com.devansh.codearena.dto.ProblemListResponse;
import com.devansh.codearena.dto.ProblemResponse;
import com.devansh.codearena.entity.Problem;

public class ProblemMapper {

    public static ProblemListResponse toProblemListResponse(Problem problem) {

        return ProblemListResponse.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .slug(problem.getSlug())
                .difficulty(problem.getDifficulty().name())
                .build();
    }

    public static ProblemResponse toProblemResponse(Problem problem) {

        return ProblemResponse.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .slug(problem.getSlug())
                .description(problem.getDescription())
                .difficulty(problem.getDifficulty())
                .timeLimit(problem.getTimeLimit())
                .memoryLimit(problem.getMemoryLimit())
                .inputFormat(problem.getInputFormat())
                .outputFormat(problem.getOutputFormat())
                .constraints(problem.getConstraints())
                .starterCodeJava(problem.getStarterCodeJava())
                .starterCodeCpp(problem.getStarterCodeCpp())
                .starterCodePython(problem.getStarterCodePython())
                .solution(problem.getSolution())
                .build();
    }
}