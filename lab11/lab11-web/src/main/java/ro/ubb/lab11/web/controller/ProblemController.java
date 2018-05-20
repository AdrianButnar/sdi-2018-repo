package ro.ubb.lab11.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.lab11.core.model.Problem;
import ro.ubb.lab11.core.service.ProblemService;
import ro.ubb.lab11.web.converter.ProblemConverter;
import ro.ubb.lab11.web.dto.EmptyJsonResponse;
import ro.ubb.lab11.web.dto.ProblemDto;
import ro.ubb.lab11.web.dto.*;

import java.util.*;

@RestController
public class ProblemController {
    private static final Logger log = LoggerFactory.getLogger(ProblemController.class);


    @Autowired
    private ProblemService problemService;

    @Autowired
    private ProblemConverter problemConverter;


    @RequestMapping(value = "/problems", method = RequestMethod.GET)
    public List<ProblemDto> getProblems() {
        log.trace("getProblems");

        List<Problem> problems = problemService.getAllProblems();

        log.trace("getProblems: problems={}", problems);

        return new ArrayList<>(problemConverter.convertModelsToDtos(problems));
    }

    @RequestMapping(value = "/problems", method = RequestMethod.POST)
    public ProblemDto createProblem(
            @RequestBody final ProblemDto problemDto) {
        log.trace("createProblem: problemDtoMap={}", problemDto);

        Problem problem = problemService.createProblem(
                problemDto.getNumber(), problemDto.getText());

        ProblemDto result = problemConverter.convertModelToDto(problem);

        log.trace("createProblem: result={}", result);
        return result;
    }

    @RequestMapping(value = "/problems/{problemId}", method = RequestMethod.PUT)
    public ProblemDto updateStudent(
            @PathVariable final Long problemId,
            @RequestBody final ProblemDto problemDto) {
        log.trace("updateProblem: problemId={}, problemDtoMap={}", problemId, problemDto);

        Optional<Problem> problemOptional = problemService.updateProblem(problemId, problemDto.getNumber(),
                problemDto.getText());

        Map<String, ProblemDto> result = new HashMap<>();
        problemOptional.ifPresent(
                problem -> result.put("problem", problemConverter.convertModelToDto(problem)));

        log.trace("updateProblem: result={}", result);

        return result.get("problem");
    }

    @RequestMapping(value = "problems/{problemId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProblem(@PathVariable final Long problemId) {
        log.trace("deleteProblem: problemId={}", problemId);

        problemService.deleteProblem(problemId);

        log.trace("deleteProblem - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

}
