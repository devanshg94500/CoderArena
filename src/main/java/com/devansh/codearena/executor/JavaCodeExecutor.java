package com.devansh.codearena.executor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.stereotype.Service;

@Service
public class JavaCodeExecutor implements CodeExecutionService {

    @Override
    public ExecutionResult executeJava(String sourceCode, String input) {

        try {

            // Create a temporary directory
            File tempDir = Files.createTempDirectory("codearena").toFile();

            // Create Main.java inside it
            File javaFile = new File(tempDir, "Main.java");

            // Write submitted code
            FileWriter writer = new FileWriter(javaFile);
            writer.write(sourceCode);
            writer.close();

            ProcessBuilder compileProcess = new ProcessBuilder(
                    "javac",
                    javaFile.getAbsolutePath()
            );

            compileProcess.directory(tempDir);

            Process process = compileProcess.start();

            int exitCode = process.waitFor();

            if (exitCode != 0) {

                String error = new String(
                        process.getErrorStream().readAllBytes()
                );

                System.out.println(error);

                return ExecutionResult.builder()
                        .compiled(false)
                        .error(error)
                        .build();
            }

            System.out.println("Compilation Successful");

            ProcessBuilder runProcess = new ProcessBuilder(
                    "java",
                    "Main"
            );

            runProcess.directory(tempDir);

            Process processRun = runProcess.start();

            String output = new String(
                    processRun.getInputStream().readAllBytes()
            );

            String error = new String(
                    processRun.getErrorStream().readAllBytes()
            );

            int runExitCode = processRun.waitFor();

            System.out.println("Program Output:");
            System.out.println(output);

            if (runExitCode != 0) {

                return ExecutionResult.builder()
                        .compiled(true)
                        .passed(false)
                        .error(error)
                        .build();
            }

            return ExecutionResult.builder()
                    .compiled(true)
                    .passed(true)
                    .output(output)
                    .executionTime(0)
                    .memoryUsed(0)
                    .build();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}