package ro.ubb.lab7.web.dto;

import lombok.*;

/**
 * Created by radu.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentDto extends BaseDto {
    public String serialNumber;
    private String name;

    @Override
    public String toString() {
        return "StudentDto{" +
                "name='" + name + '\'' +
                ", serial number=" + serialNumber +
                "} " + super.toString();
    }
}