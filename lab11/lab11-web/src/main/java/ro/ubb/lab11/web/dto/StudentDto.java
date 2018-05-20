package ro.ubb.lab11.web.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StudentDto extends BaseDto {
    private String serialNumber;
    private String name;
    private Set<Long> problems;


}
