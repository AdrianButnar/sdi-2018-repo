package ro.ubb.lab11.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.lab11.core.model.Problem;
import ro.ubb.lab11.core.repository.ProblemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemServiceImpl implements ProblemService {

    private static final Logger log = LoggerFactory.getLogger(ProblemServiceImpl.class);

    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public List<Problem> getAllProblems() {
        log.trace("getAllProblems --- method entered");

        List<Problem> problems = problemRepository.findAll();

        log.trace("getAllProblems: problems={}", problems);

        return problems;
    }

    @Override
    public Problem createProblem(int number,String text) {
        log.trace("saveProblem: number ={}, text={} ", number,text);

        Problem pb = problemRepository.save(new Problem(number,text));
        log.trace("saveProblem: problem={}", pb);

        return pb;
    }

    @Override
    @Transactional
    public Optional<Problem> updateProblem(Long problemId, int number, String text) {
        log.trace("updateProblem: problemId={}, number={},  text={}", problemId, number,text);

        Optional<Problem> optionalProblem = problemRepository.findById(problemId);

        optionalProblem.ifPresent(pb -> {
            pb.setNumber(number);
            pb.setText(text);
        });

        log.trace("updateProblem: optionalProblem={}", optionalProblem);

        return optionalProblem;
    }

    @Override
    public void deleteProblem(Long id) {
        log.trace("deleteProblem: id={}", id);

        problemRepository.deleteById(id);

        log.trace("deleteProblem --- method finished");
    }
}
