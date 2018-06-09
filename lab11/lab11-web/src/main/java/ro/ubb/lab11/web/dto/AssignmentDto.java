package ro.ubb.lab11.web.dto;

import lombok.*;

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
