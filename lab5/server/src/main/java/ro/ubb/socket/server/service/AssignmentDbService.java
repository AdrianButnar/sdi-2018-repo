package ro.ubb.socket.server.service;


import ro.ubb.socket.common.domain.Assignment;
import ro.ubb.socket.common.domain.validators.EntityCannotBeSavedException;
import ro.ubb.socket.common.domain.validators.InexistentEntityException;
import ro.ubb.socket.server.repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
