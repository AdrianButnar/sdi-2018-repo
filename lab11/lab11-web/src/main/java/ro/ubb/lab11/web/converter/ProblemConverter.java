package ro.ubb.lab11.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.lab11.core.model.Problem;
import ro.ubb.lab11.web.dto.ProblemDto;

@Component
public class ProblemConverter extends BaseConverter<Problem, ProblemDto> {

    private static final Logger log = LoggerFactory.getLogger(ProblemConverter.class);

    @Override
    public Problem convertDtoToModel(ProblemDto dto) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public ProblemDto convertModelToDto(Problem problem) {

        ProblemDto dto = ProblemDto.builder()
                .number(problem.getNumber())
                .text(problem.getText())
                .build();
        dto.setId(problem.getId());
        return dto;
    }
}
