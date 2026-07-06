package com.devansh.codearena.dto;

import com.devansh.codearena.entity.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionRequest {

    @NotBlank(message = "Source code cannot be empty")
    private String sourceCode;

    @NotNull(message = "Language is required")
    private Language language;

    @NotBlank(message = "Problem slug is required")
    private String problemSlug;
}