package ro.ubb.lab8.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.lab8.core.model.BaseEntity;
import ro.ubb.lab8.core.model.Student;
import ro.ubb.lab8.web.dto.StudentDto;

import java.util.stream.Collectors;

@Component
public class StudentConverter extends AbstractConverterBaseEntityConverter<Student, StudentDto> {

    private static final Logger log = LoggerFactory.getLogger(StudentConverter.class);

    @Override
    public Student convertDtoToModel(StudentDto dto) {
            throw new RuntimeException("Not yet implemented!");
            }

    @Override
    public StudentDto convertModelToDto(Student student) {
            StudentDto studentDto = StudentDto.builder()
                    .serialNumber(student.getSerialNumber())
                    .name(student.getName())
                    .problems(student.getProblems().stream()
                    .map(BaseEntity::getId).collect(Collectors.toSet()))
                    .build();
            studentDto.setId(student.getId());
            return studentDto;
    }
}
