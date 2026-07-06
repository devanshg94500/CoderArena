package com.devansh.codearena.repository;

import com.devansh.codearena.entity.Problem;
import com.devansh.codearena.entity.Submission;
import com.devansh.codearena.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByUser(User user);

    List<Submission> findByProblem(Problem problem);

    List<Submission> findByUserAndProblem(User user, Problem problem);
}