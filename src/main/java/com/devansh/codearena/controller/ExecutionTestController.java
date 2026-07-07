package com.devansh.codearena.controller;

import com.devansh.codearena.executor.ExecutionResult;
import com.devansh.codearena.executor.JavaCodeExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class ExecutionTestController {

    private final JavaCodeExecutor javaCodeExecutor;

    @PostMapping
    public ExecutionResult test(@RequestBody String code) {

        return javaCodeExecutor.executeJava(code, "");
    }
}