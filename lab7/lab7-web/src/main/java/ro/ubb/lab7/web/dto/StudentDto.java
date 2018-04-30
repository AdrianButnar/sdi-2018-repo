package ro.ubb.lab7.web.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class StudentDto extends BaseDto {
    public String serialNumber;
    public String name;

    public StudentDto(String serialNumber1, String name1)
    {
        this.serialNumber = serialNumber1;
        this.name = name1;

    }

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
