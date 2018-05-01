//package ro.ubb.lab7.web.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ro.ubb.lab7.core.model.Problem;
//import ro.ubb.lab7.core.service.ProblemService;
//import ro.ubb.lab7.web.converter.ProblemConverter;
//import ro.ubb.lab7.web.dto.*;
//
//import java.util.List;
//
//@RestController
//public class ProblemController {
//    private static final Logger log = LoggerFactory.getLogger(ProblemController.class);
//
//
//    @Autowired
//    private ProblemService problemService;
//
//    @Autowired
//    private ProblemConverter problemConverter;
//
//
//    @RequestMapping(value = "/problems", method = RequestMethod.GET)
//    public ProblemsDto getStudents() {
//        log.trace("getProblems");
//
//        List<Problem> problems = problemService.getAllProblems();
//
//        log.trace("getProblems: problems={}", problems);
//
//        return new ProblemsDto(problemConverter.convertModelsToDtos(problems));
//    }
//
//    @RequestMapping(value = "/problems", method = RequestMethod.POST)
//    public ProblemDto createProblem(
//            @RequestBody final ProblemDto problemDto) {
//        log.trace("createProblem: problemDtoMap={}", problemDto);
//
//        Problem problem = problemService.createProblem(
//                problemDto.getNumber(), problemDto.getText());
//
//        ProblemDto result = problemConverter.convertModelToDto(problem);
//
//        log.trace("createProblem: result={}", result);
//        return result;
//    }
//
//
//    @RequestMapping(value = "problems/{problemId}", method = RequestMethod.DELETE)
//    public ResponseEntity deleteProblem(@PathVariable final Long problemId) {
//        log.trace("deleteProblem: problemId={}", problemId);
//
//        problemService.deleteProblem(problemId);
//
//        log.trace("deleteProblem - method end");
//
//        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
//    }
//
//}