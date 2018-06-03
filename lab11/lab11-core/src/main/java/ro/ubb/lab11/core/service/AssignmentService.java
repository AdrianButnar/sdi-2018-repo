package ro.ubb.lab11.core.service;


import ro.ubb.lab11.core.model.Assignment;

import java.util.List;
import java.util.Optional;

public interface AssignmentService {



    List<Assignment> getAllAssignments();

    Assignment createAssignment(Long studentId, Long problemId);

    Optional<Assignment> updateAssignment(Long assignmentId, Long studentId, Long problemId);

    void deleteAssignment(Long id);
}
