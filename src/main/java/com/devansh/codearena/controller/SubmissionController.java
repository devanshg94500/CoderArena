package com.devansh.codearena.controller;

import com.devansh.codearena.dto.SubmissionRequest;
import com.devansh.codearena.dto.SubmissionResponse;
import com.devansh.codearena.service.SubmissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping
    public SubmissionResponse submitSolution(
            @Valid @RequestBody SubmissionRequest request) {

        return submissionService.submitSolution(request);
    }
}