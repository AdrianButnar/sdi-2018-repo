//package ro.ubb.lab7.web.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ro.ubb.lab7.core.model.Assignment;
//import ro.ubb.lab7.core.service.AssignmentService;
//import ro.ubb.lab7.web.converter.AssignmentConverter;
//import ro.ubb.lab7.web.dto.*;
//
//import java.util.List;
//
//@RestController
//public class AssignmentController {
//
//    private static final Logger log = LoggerFactory.getLogger(AssignmentController.class);
//
//    @Autowired
//    private AssignmentService assignmentService;
//
//    @Autowired
//    private AssignmentConverter assignmentConverter;
//
//
//    @RequestMapping(value = "/assignments", method = RequestMethod.GET)
//    public AssignmentsDto getAssignments() {
//        log.trace("getAssignments");
//
//        List<Assignment> assignments = assignmentService.getAllAssignments();
//
//        log.trace("getAssignments: assignments={}", assignments);
//
//        return new AssignmentsDto(assignmentConverter.convertModelsToDtos(assignments));
//    }
//
//
//    @RequestMapping(value = "/assignments", method = RequestMethod.POST)
//    public AssignmentDto createAssignment(
//            @RequestBody final AssignmentDto assignmentDto) {
//        log.trace("createAssignment: assignmentDtoMap={}", assignmentDto);
//
//        Assignment assignment = assignmentService.createAssignment(
//                assignmentDto.getStudentId(), assignmentDto.getProblemId());
//
//        AssignmentDto result = assignmentConverter.convertModelToDto(assignment);
//
//        log.trace("createAssignment: result={}", result);
//        return result;
//    }
//
//
//    @RequestMapping(value = "assignments/{assignmentId}", method = RequestMethod.DELETE)
//    public ResponseEntity deleteAssignment(@PathVariable final Long assignmentId) {
//        log.trace("deleteAssignment: assignmentId={}", assignmentId);
//
//        assignmentService.deleteAssignment(assignmentId);
//
//        log.trace("deleteAssignment - method end");
//
//        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
//    }
//
//}
