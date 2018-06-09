package ro.ubb.lab6.server.service;


import org.springframework.stereotype.Service;
import ro.ubb.lab6.common.domain.Problem;
import ro.ubb.lab6.common.domain.validators.EntityCannotBeSavedException;
import ro.ubb.lab6.common.domain.validators.InexistentEntityException;
import ro.ubb.lab6.server.repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
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

        Set<Problem> problemSet = StreamSupport.stream(problems.spliterator(), false).collect(Collectors.toSet());
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        for(Problem pb : problemSet)
//        {
//            System.out.println(pb.toString());
//        }
        return problemSet;
    }

}
