package ro.ubb.lab11.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.lab11.core.model.Assignment;
import ro.ubb.lab11.core.model.BaseEntity;
import ro.ubb.lab11.core.model.Problem;
import ro.ubb.lab11.core.model.Student;
import ro.ubb.lab11.core.repository.ProblemRepository;
import ro.ubb.lab11.core.repository.StudentRepository;

import java.util.*;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private static final Logger log = LoggerFactory.getLogger(AssignmentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Assignment> getAllAssignments() {
        log.trace("getAllAssignments --- method entered");

        List<Student> students = studentRepository.findAll();
        List<Assignment> assignments = new ArrayList<>();
        for (Student s: students)
            for(Problem p :s.getProblems()){
                assignments.add(new Assignment(s,p,10));//ca trebuie si nota..
            }
        log.trace("getAllAssignments: assignments={}", assignments);

        return assignments;
    }
//
    @Override
    public Assignment createAssignment(Long studentId, Long problemId) {
        return null;
    }

    @Override
    public Optional<Assignment> updateAssignment(Long assignmentId, Long studentId, Long problemId) {
        return Optional.empty();
    }

    @Override
    public void deleteAssignment(Long id) {

    }
}
