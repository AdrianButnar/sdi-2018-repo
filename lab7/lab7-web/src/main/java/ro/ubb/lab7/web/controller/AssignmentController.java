package ro.ubb.lab7.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.lab7.core.model.Assignment;
import ro.ubb.lab7.core.model.Student;
import ro.ubb.lab7.core.service.AssignmentService;
import ro.ubb.lab7.web.converter.AssignmentConverter;
import ro.ubb.lab7.web.dto.AssignmentDto;
import ro.ubb.lab7.web.dto.AssignmentsDto;
import ro.ubb.lab7.web.dto.EmptyJsonResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
public class AssignmentController {

    private static final Logger log = LoggerFactory.getLogger(AssignmentController.class);

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private AssignmentConverter assignmentConverter;


    @RequestMapping(value = "/assignments", method = RequestMethod.GET)
    public AssignmentsDto getAssignments() {
        log.trace("getAssignments");

        List<Assignment> assignments = assignmentService.getAllAssignments();

        log.trace("getAssignments: assignments={}", assignments);

        return new AssignmentsDto(assignmentConverter.convertModelsToDtos(assignments));
    }


    @RequestMapping(value = "/assignments", method = RequestMethod.POST)
    public AssignmentDto createAssignment(
            @RequestBody final AssignmentDto assignmentDto) {
        log.trace("createAssignment: assignmentDtoMap={}", assignmentDto);

        Assignment assignment = assignmentService.createAssignment(
                assignmentDto.getStudentId(), assignmentDto.getProblemId());

        AssignmentDto result = assignmentConverter.convertModelToDto(assignment);

        log.trace("createAssignment: result={}", result);
        return result;
    }



    @RequestMapping(value = "assignments/{assignmentId}", method = RequestMethod.PUT)
    public AssignmentDto updateAssignment(
            @PathVariable final Long assignmentId,
            @RequestBody final AssignmentDto assignmentDto) {

        log.trace("updateAssignment: assignmentId={}, assignmentDtoMap={}", assignmentId, assignmentDto);

        Optional<Assignment> assignmentOptional = assignmentService.updateAssignment(assignmentId, assignmentDto.getStudentId(),
                assignmentDto.getProblemId());

        Map<String, AssignmentDto> result = new HashMap<>();
        assignmentOptional.ifPresent(
                assignment -> result.put("assignment", assignmentConverter.convertModelToDto(assignment)));
        //() -> result.put("student", studentConverter.convertModelToDto(new Student())));

        log.trace("updateAssignment: result={}", result);
        return result.get("assignment");
    }

    @RequestMapping(value = "assignments/{assignmentId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAssignment(@PathVariable final Long assignmentId) {
        log.trace("deleteAssignment: assignmentId={}", assignmentId);

        assignmentService.deleteAssignment(assignmentId);

        log.trace("deleteAssignment - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

}
