package ro.ubb.lab8.web.dto;

import lombok.*;
import ro.ubb.lab8.core.model.Problem;
import ro.ubb.lab8.core.model.Student;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder

@EqualsAndHashCode
public class AssignmentDto extends BaseDto {
    private Long studentId;
    private Long problemId;
    private Integer grade;

    @Override
    public String toString() {
        return "AssignmentDto{" +
                "student Id =" + studentId +
                ", problem Id =" + problemId +
                "} "+ super.toString();
    }
}
