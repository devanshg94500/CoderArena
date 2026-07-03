package com.devansh.codearena.repository;

import com.devansh.codearena.entity.Difficulty;
import com.devansh.codearena.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

    Optional<Problem> findBySlug(String slug);

    boolean existsBySlug(String slug);

    List<Problem> findByDifficulty(Difficulty difficulty);

    List<Problem> findByTitleContainingIgnoreCase(String keyword);
}