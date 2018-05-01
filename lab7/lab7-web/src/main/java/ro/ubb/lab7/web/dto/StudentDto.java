package ro.ubb.lab7.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentDto extends BaseDto {
    public String name;
    public String serialNumber;


    @Override
    public String toString() {
        return "StudentDto{" +
                "name='" + name + '\'' +
                ", serial number=" + serialNumber +
                "} " + super.toString();
    }
}
