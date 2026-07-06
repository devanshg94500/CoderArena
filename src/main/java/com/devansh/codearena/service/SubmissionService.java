package com.devansh.codearena.service;

import com.devansh.codearena.dto.SubmissionRequest;
import com.devansh.codearena.dto.SubmissionResponse;

public interface SubmissionService {

    SubmissionResponse submitSolution(SubmissionRequest request);

}