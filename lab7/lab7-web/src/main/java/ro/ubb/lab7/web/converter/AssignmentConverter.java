package ro.ubb.lab7.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.ubb.lab7.core.model.Assignment;
import ro.ubb.lab7.web.dto.AssignmentDto;

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