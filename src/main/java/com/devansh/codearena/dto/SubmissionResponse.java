package com.devansh.codearena.dto;

import com.devansh.codearena.entity.Language;
import com.devansh.codearena.entity.SubmissionStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionResponse {

    private Long id;

    private String problemTitle;

    private Language language;

    private SubmissionStatus status;

    private Double executionTime;

    private Double memoryUsed;

    private Integer testCasesPassed;

    private LocalDateTime submittedAt;
}