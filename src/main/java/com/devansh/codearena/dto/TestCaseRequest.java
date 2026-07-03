package com.devansh.codearena.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCaseRequest {

    private String input;

    private String expectedOutput;

    private boolean sample;
}