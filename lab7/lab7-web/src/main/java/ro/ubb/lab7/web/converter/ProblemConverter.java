package ro.ubb.lab7.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.lab7.core.model.Problem;
import ro.ubb.lab7.web.dto.ProblemDto;

@Component
public class ProblemConverter extends BaseConverter<Problem, ProblemDto> {

    private static final Logger log = LoggerFactory.getLogger(ProblemConverter.class);

    @Override
    public Problem convertDtoToModel(ProblemDto dto) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public ProblemDto convertModelToDto(Problem problem) {
        ProblemDto problemDto = new ProblemDto(problem.getNumber(),problem.getText());
        problemDto.setId(problem.getId());
        return problemDto;
    }
}
