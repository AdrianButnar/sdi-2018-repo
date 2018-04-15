package ro.ubb.lab6.server.service;


import org.springframework.stereotype.Service;
import ro.ubb.lab6.common.domain.Assignment;
import ro.ubb.lab6.common.domain.validators.EntityCannotBeSavedException;
import ro.ubb.lab6.common.domain.validators.InexistentEntityException;
import ro.ubb.lab6.server.repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AssignmentDbService {
    public Repository<Long, Assignment> repository;

    public AssignmentDbService(Repository<Long, Assignment> repository) {
        this.repository = repository;
    }

    public void addAssignment(Assignment as) throws EntityCannotBeSavedException
    {
        repository.save(as);
    }

    public void removeAssignment(Long id) throws InexistentEntityException {
        repository.remove(id);
    }

    public Set<Assignment> getAllAssignments()
    {
        Iterable<Assignment> assignments = repository.findAll();

        return StreamSupport.stream(assignments.spliterator(), false).collect(Collectors.toSet());
    }
}
