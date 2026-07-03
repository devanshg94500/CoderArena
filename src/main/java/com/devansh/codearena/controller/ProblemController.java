package com.devansh.codearena.controller;

import com.devansh.codearena.dto.ProblemListResponse;
import com.devansh.codearena.dto.ProblemRequest;
import com.devansh.codearena.dto.ProblemResponse;
import com.devansh.codearena.service.ProblemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping
    public ProblemResponse createProblem(@Valid @RequestBody ProblemRequest request) {
        return problemService.createProblem(request);
    }

    @GetMapping
    public List<ProblemListResponse> getAllProblems() {
        return problemService.getAllProblems();
    }

    @GetMapping("/{slug}")
    public ProblemResponse getProblem(@PathVariable String slug) {
        return problemService.getProblemBySlug(slug);
    }

    @DeleteMapping("/{id}")
    public void deleteProblem(@PathVariable Long id) {
        problemService.deleteProblem(id);
    }

    @PutMapping("/{id}")
    public ProblemResponse updateProblem(
           @Valid @PathVariable Long id,
            @Valid @RequestBody ProblemRequest request) {

        return problemService.updateProblem(id, request);
    }
}