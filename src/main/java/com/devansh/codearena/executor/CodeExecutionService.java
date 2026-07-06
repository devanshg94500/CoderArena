package com.devansh.codearena.executor;

public interface CodeExecutionService {

    ExecutionResult executeJava(
            String sourceCode,
            String input
    );
}