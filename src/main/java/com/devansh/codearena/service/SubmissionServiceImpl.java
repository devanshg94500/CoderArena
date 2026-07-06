package com.devansh.codearena.service;

import com.devansh.codearena.dto.SubmissionRequest;
import com.devansh.codearena.dto.SubmissionResponse;
import com.devansh.codearena.entity.Problem;
import com.devansh.codearena.entity.Submission;
import com.devansh.codearena.entity.SubmissionStatus;
import com.devansh.codearena.entity.User;
import com.devansh.codearena.exception.ProblemNotFoundException;
import com.devansh.codearena.mapper.SubmissionMapper;
import com.devansh.codearena.repository.ProblemRepository;
import com.devansh.codearena.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;

    private final ProblemRepository problemRepository;

    private final CurrentUserService currentUserService;

    @Override
    public SubmissionResponse submitSolution(SubmissionRequest request) {
        Problem problem = problemRepository.findBySlug(request.getProblemSlug())
                .orElseThrow(() ->
                        new ProblemNotFoundException("Problem not found"));
        User currentUser = currentUserService.getCurrentUser();

        Submission submission = Submission.builder()
                .problem(problem)
                .user(currentUser)
                .sourceCode(request.getSourceCode())
                .language(request.getLanguage())
                .status(SubmissionStatus.PENDING)
                .submittedAt(LocalDateTime.now())
                .executionTime(0.0)
                .memoryUsed(0.0)
                .testCasesPassed(0)
                .build();

        Submission savedSubmission =
                submissionRepository.save(submission);

        return SubmissionMapper.toSubmissionResponse(savedSubmission);
    }
}