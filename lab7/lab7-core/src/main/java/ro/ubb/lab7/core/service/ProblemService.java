package ro.ubb.lab7.core.service;



import ro.ubb.lab7.core.model.Problem;
import ro.ubb.lab7.core.model.validators.EntityCannotBeSavedException;
import ro.ubb.lab7.core.model.validators.InexistentEntityException;
import ro.ubb.lab7.core.repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Alexandru Buhai
 * @version 1.0.2
 */
public class ProblemService {
    private Repository<Long, Problem> repository;

    public ProblemService(Repository<Long, Problem> repository) {
        this.repository = repository;
    }

    public void addProblem(Problem problem) throws EntityCannotBeSavedException
    {
        repository.save(problem);
    }

    public void removeProblem(Long id) throws InexistentEntityException {
        repository.remove(id);
    }

    public Set<Problem> getAllProblems()
    {
        Iterable<Problem> problems = repository.findAll();

        return StreamSupport.stream(problems.spliterator(), false).collect(Collectors.toSet());
    }

}
