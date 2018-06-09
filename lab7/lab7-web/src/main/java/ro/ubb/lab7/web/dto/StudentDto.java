package ro.ubb.lab7.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
public class StudentDto extends BaseDto {
    public String name;
    public String serialNumber;

    public String getSerialNumber()
    {
        return serialNumber;
    }
    public String getName()
    {
        return name;
    }


    @Override
    public String toString() {
        return "StudentDto{" +
                "name='" + name + '\'' +
                ", serial number=" + serialNumber +
                "} " + super.toString();
    }
}
