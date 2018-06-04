package ro.ubb.lab11.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.lab11.core.model.Assignment;
import ro.ubb.lab11.core.model.Student;
import ro.ubb.lab11.core.service.StudentService;
import ro.ubb.lab11.web.converter.AssignmentConverter;
import ro.ubb.lab11.web.dto.AssignmentDto;

import java.util.*;


@RestController
public class AssignmentController {
    private static final Logger log = LoggerFactory.getLogger(AssignmentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private AssignmentConverter assignmentConverter;


    @RequestMapping(value = "/assignments/{studentId}", method = RequestMethod.GET)
    public Set<AssignmentDto> getAssignments(
            @PathVariable final Long studentId) {
        log.trace("getStudentDisciplines: studentId={}", studentId);

        Optional<Student> studentOptional = studentService.findStudent(studentId);
        Set<AssignmentDto> result = new HashSet<>();
        studentOptional.ifPresent(student -> {
            Set<Assignment> studentDisciplines = student.getAssignments();
            result.addAll(assignmentConverter
                    .convertModelsToDtos(studentDisciplines));
        });

        log.trace("getStudentDisciplines: result={}", result);
        return result;
    }

    @RequestMapping(value = "/assignments/{studentId}", method = RequestMethod.PUT)
    public Set<AssignmentDto> updateStudentGrades(
            @PathVariable final Long studentId,
            @RequestBody final Set<AssignmentDto> assignmentDtos) {
        log.trace("updateStudentGrades: studentId={}, studentDisciplineDtos={}",
                studentId, assignmentDtos);

        Map<Long, Integer> grades = new HashMap<>();
        assignmentDtos.forEach(sd ->
                grades.put(sd.getProblemId(), sd.getGrade()));

        Optional<Student> studentOptional = studentService.updateStudentGrades(studentId, grades);

        Set<AssignmentDto> result = new HashSet<>();
        studentOptional.ifPresent(student -> {
            result.addAll(assignmentConverter.
                    convertModelsToDtos(student.getAssignments()));

        });

        log.trace("getStudentDisciplines: result={}", result);

        return result;
    }

}
