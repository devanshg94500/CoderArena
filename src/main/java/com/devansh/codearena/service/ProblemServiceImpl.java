package com.devansh.codearena.service;

import com.devansh.codearena.dto.ProblemListResponse;
import com.devansh.codearena.dto.ProblemRequest;
import com.devansh.codearena.dto.ProblemResponse;
import com.devansh.codearena.entity.Problem;
import com.devansh.codearena.entity.Tag;
import com.devansh.codearena.entity.TestCase;
import com.devansh.codearena.exception.DuplicateProblemException;
import com.devansh.codearena.exception.ProblemNotFoundException;
import com.devansh.codearena.mapper.ProblemMapper;
import com.devansh.codearena.repository.ProblemRepository;
import com.devansh.codearena.repository.TagRepository;
import com.devansh.codearena.util.SlugUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;
    private final TagRepository tagRepository;

    @Override
    public ProblemResponse createProblem(ProblemRequest request) {

        String slug = SlugUtil.generate(request.getTitle());

        if (problemRepository.existsBySlug(slug)) {
            throw new DuplicateProblemException("Problem already exists");
        }

        Problem problem = Problem.builder()
                .title(request.getTitle())
                .slug(slug)
                .description(request.getDescription())
                .difficulty(request.getDifficulty())
                .timeLimit(request.getTimeLimit())
                .memoryLimit(request.getMemoryLimit())
                .inputFormat(request.getInputFormat())
                .outputFormat(request.getOutputFormat())
                .constraints(request.getConstraints())
                .starterCodeJava(request.getStarterCodeJava())
                .starterCodeCpp(request.getStarterCodeCpp())
                .starterCodePython(request.getStarterCodePython())
                .solution(request.getSolution())
                .build();

        List<Tag> tags = getOrCreateTags(request.getTags());
        problem.setTags(tags);

        problem.setTags(tags);

        List<TestCase> testCases = request.getTestCases()
                .stream()
                .map(tc ->
                        TestCase.builder()
                                .input(tc.getInput())
                                .expectedOutput(tc.getExpectedOutput())
                                .sample(tc.isSample())
                                .problem(problem)
                                .build()
                )
                .collect(Collectors.toList());

        problem.setTestCases(testCases);

        Problem savedProblem = problemRepository.save(problem);

        return ProblemMapper.toProblemResponse(savedProblem);
    }

    @Override
    public ProblemResponse updateProblem(Long id, ProblemRequest request) {

        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new ProblemNotFoundException("Problem not found"));

        problem.setTitle(request.getTitle());
        problem.setDescription(request.getDescription());
        problem.setDifficulty(request.getDifficulty());

        problem.setTimeLimit(request.getTimeLimit());
        problem.setMemoryLimit(request.getMemoryLimit());

        problem.setInputFormat(request.getInputFormat());
        problem.setOutputFormat(request.getOutputFormat());
        problem.setConstraints(request.getConstraints());

        problem.setStarterCodeJava(request.getStarterCodeJava());
        problem.setStarterCodeCpp(request.getStarterCodeCpp());
        problem.setStarterCodePython(request.getStarterCodePython());

        problem.setSolution(request.getSolution());

        String newSlug = SlugUtil.generate(request.getTitle());

        if (!problem.getSlug().equals(newSlug)
                && problemRepository.existsBySlug(newSlug)) {

            throw new DuplicateProblemException("Problem with this title already exists.");
        }
        problem.setSlug(newSlug);

        List<Tag> tags = request.getTags()
                .stream()
                .map(tagName ->
                        tagRepository.findByName(tagName)
                                .orElseGet(() ->
                                        tagRepository.save(
                                                Tag.builder()
                                                        .name(tagName)
                                                        .build()
                                        )
                                )
                )
                .collect(Collectors.toList());

        problem.getTags().clear();
        problem.getTags().addAll(tags);
        // ---------------- TEST CASES ----------------

        problem.getTestCases().clear();

        List<TestCase> updatedTestCases = request.getTestCases()
                .stream()
                .map(tc ->
                        TestCase.builder()
                                .input(tc.getInput())
                                .expectedOutput(tc.getExpectedOutput())
                                .sample(tc.isSample())
                                .problem(problem)
                                .build()
                )
                .collect(Collectors.toList());

        problem.getTestCases().addAll(updatedTestCases);

// ---------------- SAVE ----------------

        Problem updatedProblem = problemRepository.save(problem);

        return ProblemMapper.toProblemResponse(updatedProblem);
    }

    @Override
    public List<ProblemListResponse> getAllProblems() {

        List<Problem> problems = problemRepository.findAll();

        return problems.stream()
                .map(ProblemMapper::toProblemListResponse)
                .toList();
    }

    @Override
    public ProblemResponse getProblemBySlug(String slug) {

        Problem problem = problemRepository.findBySlug(slug)
                .orElseThrow(() ->
                        new RuntimeException("Problem not found"));

        return ProblemMapper.toProblemResponse(problem);
    }

    @Override
    public void deleteProblem(Long id) {
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new ProblemNotFoundException("Problem not found"));

        problemRepository.delete(problem);
    }

    private List<Tag> getOrCreateTags(List<String> tagNames) {

        return tagNames.stream()
                .map(tagName ->
                        tagRepository.findByName(tagName)
                                .orElseGet(() ->
                                        tagRepository.save(
                                                Tag.builder()
                                                        .name(tagName)
                                                        .build()
                                        )
                                )
                )
                .collect(Collectors.toList());
    }
}