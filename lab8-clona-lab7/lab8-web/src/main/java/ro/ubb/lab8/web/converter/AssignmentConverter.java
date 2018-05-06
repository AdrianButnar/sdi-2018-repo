package ro.ubb.lab8.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.lab8.core.model.Assignment;
import ro.ubb.lab8.web.dto.AssignmentDto;

@Component
public class AssignmentConverter extends BaseConverter<Assignment, AssignmentDto> {

    private static final Logger log = LoggerFactory.getLogger(StudentConverter.class);

    @Override
    public Assignment convertDtoToModel(AssignmentDto dto) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public AssignmentDto convertModelToDto(Assignment assignment) {
        AssignmentDto assignmentDto = new AssignmentDto(assignment.getStudentId(), assignment.getProblemId());
        assignmentDto.setId(assignment.getId());
        return assignmentDto;
    }
}