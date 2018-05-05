package ro.ubb.lab8.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter

public class AssignmentDto extends BaseDto {
    private Long studentId;
    private Long problemId;

    public Long getProblemId() {
        return problemId;
    }

    public Long getStudentId() {

        return studentId;
    }

    @Override
    public String toString() {
        return "AssignmentDto{" +
                "studentId=" + studentId +
                ", problemId=" + problemId +
                "} "+ super.toString();
    }
}
