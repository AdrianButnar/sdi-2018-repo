package ro.ubb.lab11.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.lab11.core.model.Assignment;
import ro.ubb.lab11.web.dto.AssignmentDto;

@Component
public class AssignmentConverter extends  AbstractConverter<Assignment, AssignmentDto> {

    private static final Logger log = LoggerFactory.getLogger(StudentConverter.class);

    @Override
    public Assignment convertDtoToModel(AssignmentDto dto) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public AssignmentDto convertModelToDto(Assignment assignment) {
        return AssignmentDto.builder()
                .studentId(assignment.getStudent().getId())
                .problemId(assignment.getProblem().getId())
                .grade(assignment.getGrade())
                .build();
    }
}