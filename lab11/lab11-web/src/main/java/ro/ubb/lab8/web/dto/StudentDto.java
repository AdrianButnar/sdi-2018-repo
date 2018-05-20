package ro.ubb.lab8.web.dto;

import lombok.*;
import ro.ubb.lab8.core.model.Assignment;

import java.util.HashSet;
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
