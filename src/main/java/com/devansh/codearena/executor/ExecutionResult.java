package com.devansh.codearena.executor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExecutionResult {

    private boolean compiled;

    private boolean passed;

    private String output;

    private String error;

    private long executionTime;

    private long memoryUsed;
}