package com.devansh.codearena.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "problems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String slug;

    @Lob
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private Integer timeLimit;

    private Integer memoryLimit;

    @Lob
    private String inputFormat;

    @Lob
    private String outputFormat;

    @Lob
    private String constraints;

    @Lob
    private String starterCodeJava;

    @Lob
    private String starterCodeCpp;

    @Lob
    private String starterCodePython;

    @Lob
    private String solution;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "problem",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TestCase> testCases;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "problem_tags",
            joinColumns = @JoinColumn(name = "problem_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @OneToMany(mappedBy = "problem")
    private List<Submission> submissions = new ArrayList<>();
}