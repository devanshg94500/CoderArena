package com.devansh.codearena.mapper;

import com.devansh.codearena.dto.SubmissionResponse;
import com.devansh.codearena.entity.Submission;

public class SubmissionMapper {

    public static SubmissionResponse toSubmissionResponse(Submission submission) {

        return SubmissionResponse.builder()
                .id(submission.getId())
                .problemTitle(submission.getProblem().getTitle())
                .language(submission.getLanguage())
                .status(submission.getStatus())
                .executionTime(submission.getExecutionTime())
                .memoryUsed(submission.getMemoryUsed())
                .testCasesPassed(submission.getTestCasesPassed())
                .submittedAt(submission.getSubmittedAt())
                .build();
    }
}