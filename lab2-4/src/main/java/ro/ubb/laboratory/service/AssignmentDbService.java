package ro.ubb.laboratory.service;

import ro.ubb.laboratory.domain.Assignment;
import ro.ubb.laboratory.domain.validators.EntityCannotBeSavedException;
import ro.ubb.laboratory.domain.validators.InexistentEntityException;
import ro.ubb.laboratory.repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AssignmentDbService {
    public Repository<Long, Assignment> repository;

    public void addAssignment(Assignment ass) throws EntityCannotBeSavedException
    {
        repository.save(ass);
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
