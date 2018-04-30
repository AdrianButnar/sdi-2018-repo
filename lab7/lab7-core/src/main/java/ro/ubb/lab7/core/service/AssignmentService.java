package ro.ubb.lab7.core.service;


import ro.ubb.lab7.core.model.Assignment;

import java.util.List;
import java.util.Optional;

public interface AssignmentService {

    List<Assignment> getAllAssignments();

    Assignment createAssignment(Long studentId, Long problemId);

    //Optional<Assignment> updateAssignment(Long studentId, Long problemId);

    void deleteAssignment(Long id);
}
