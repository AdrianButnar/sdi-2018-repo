package ro.ubb.lab7.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.lab7.core.model.Assignment;
import ro.ubb.lab7.core.model.Problem;
import ro.ubb.lab7.core.repository.AssignmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentService{

    private static final Logger log = LoggerFactory.getLogger(AssignmentServiceImpl.class);

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public List<Assignment> getAllAssignments() {
        log.trace("getAllAssignments --- method entered");

        List<Assignment> assignments = assignmentRepository.findAll();

        log.trace("getAllAssignments: assignments={}", assignments);

        return assignments;
    }

    @Override
    @Transactional
    public Optional<Assignment> updateAssignment(Long assignmentId, Long studentId, Long problemId) {
        log.trace("update Assignment: assignmentId={}, studentId={},  problemId={}", assignmentId, studentId, problemId);

        Optional<Assignment> assignmentOptional = assignmentRepository.findById(assignmentId);

        assignmentOptional.ifPresent(pb -> {
            pb.setStudentId(studentId);
            pb.setProblemId(problemId);
        });

        log.trace("update Assignment: assignmentOptional={}", assignmentOptional);

        return assignmentOptional;
    }

    @Override
    public Assignment createAssignment(Long studentId, Long problemId) {
        log.trace("save Assignment: studentId ={}, problemId={} ", studentId, problemId);

        Assignment as = assignmentRepository.save(new Assignment(studentId,problemId));
        log.trace("save Assignment: assignment={}", as);

        return as;
    }

    @Override
    public void deleteAssignment(Long id) {
        log.trace("delete Assignment: id={}", id);

        assignmentRepository.deleteById(id);

        log.trace("delete Assignment --- method finished");

    }
}
