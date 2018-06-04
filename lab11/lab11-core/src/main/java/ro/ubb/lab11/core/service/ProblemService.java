package ro.ubb.lab11.core.service;


import ro.ubb.lab11.core.model.Problem;

import java.util.List;
import java.util.Optional;

public interface ProblemService {

    List<Problem> findAll();

    Problem createProblem(int number, String text);

    Problem  updateProblem(Long problemId, int number, String text);

    void deleteProblem(Long id);
}
