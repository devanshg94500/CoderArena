package com.devansh.codearena.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String input;

    @Lob
    private String expectedOutput;

    private boolean sample;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;
}