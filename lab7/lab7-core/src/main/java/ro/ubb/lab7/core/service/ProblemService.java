package ro.ubb.lab7.core.service;


import ro.ubb.lab7.core.model.Problem;

import java.util.List;
import java.util.Optional;

public interface ProblemService {

    List<Problem> getAllProblems();

    Problem createProblem(int number, String text);

   // Optional<Problem> updateProblem(Long problemId, int number, String text);

    void deleteProblem(Long id);
}
