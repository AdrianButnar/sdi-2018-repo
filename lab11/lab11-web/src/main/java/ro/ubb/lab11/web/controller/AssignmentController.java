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


    @RequestMapping(value = "/assignments", method = RequestMethod.GET)
    public Set<AssignmentDto> getAssignments() {
        log.trace("getAssignments: studentId={}");

        Optional<Student> studentOptional;
        Set<AssignmentDto> result = new HashSet<>();
        for(Student s:studentService.findAll()) {
            studentOptional = studentService.findStudent(s.getId());
            studentOptional.ifPresent(student -> {
                Set<Assignment> assignments = student.getAssignments();
                result.addAll(assignmentConverter
                        .convertModelsToDtos(assignments));
            });
        }

        log.trace("getAssignments: result={}", result);

        return result;
    }

    @RequestMapping(value = "/assignments/{studentId}", method = RequestMethod.PUT)
    public Set<AssignmentDto> updateStudentGrades(
            @PathVariable final Long studentId,
            @RequestBody final Set<AssignmentDto> studentDisciplineDtos) {
        log.trace("updateStudentGrades: studentId={}, studentDisciplineDtos={}",
                studentId, studentDisciplineDtos);

        Map<Long, Integer> grades = new HashMap<>();
        studentDisciplineDtos.forEach(sd ->
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
