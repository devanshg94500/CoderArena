package com.devansh.codearena.service;

import com.devansh.codearena.dto.ProblemListResponse;
import com.devansh.codearena.dto.ProblemRequest;
import com.devansh.codearena.dto.ProblemResponse;

import java.util.List;

public interface ProblemService {

    ProblemResponse createProblem(ProblemRequest request);

    ProblemResponse updateProblem(Long id, ProblemRequest request);

    ProblemResponse getProblemBySlug(String slug);

    void deleteProblem(Long id);

    List<ProblemListResponse> getAllProblems();
}