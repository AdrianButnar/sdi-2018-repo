package ro.ubb.lab7.core.service;


import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.lab7.core.model.Assignment;
import ro.ubb.lab7.core.repository.AssignmentRepository;
import ro.ubb.lab7.core.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

public interface AssignmentService {



    List<Assignment> getAllAssignments();

    Assignment createAssignment(Long studentId, Long problemId);

    Optional<Assignment> updateAssignment(Long assignmentId, Long studentId, Long problemId);

    void deleteAssignment(Long id);
}
