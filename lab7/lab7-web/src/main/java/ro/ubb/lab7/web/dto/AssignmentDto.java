package ro.ubb.lab7.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AssignmentDto extends BaseDto {
    private Long studentID;
    private Long problemID;
}
