package ro.ubb.lab7.web.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AssignmentDto extends BaseDto {
    private Long studentId;
    private Long problemId;

    public AssignmentDto(Long studentId1, Long problemId1)
    {
        studentId = studentId1;
        problemId = problemId1;
    }

}
