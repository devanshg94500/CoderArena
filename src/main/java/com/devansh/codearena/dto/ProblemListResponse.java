package com.devansh.codearena.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProblemListResponse {

    private Long id;

    private String title;

    private String slug;

    private String difficulty;
}