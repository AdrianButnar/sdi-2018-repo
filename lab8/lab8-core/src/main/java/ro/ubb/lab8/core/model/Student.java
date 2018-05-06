package ro.ubb.lab8.core.model;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Student extends BaseEntity<Long> {
    private String serialNumber;
    private String name;


    public Student(String serialNumber, String Name) {
        this.serialNumber = serialNumber;
        this.name = Name;
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets a new serial number for this student
     *
     * @param serialNumber - Serial number of the student
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    /**
     * Set the name of the student
     *
     * @param name - Name of the student
     */
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Student {" +
                "ID = " + this.getId() +
                ", SerialNumber = '" + serialNumber + '\'' +
                ", Name = '" + name + '\'' +
                '}';
    }
}
/*
    @Override
    public boolean equals(Object o) {
              if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (name.equals(student.getName())) return false;
        return serialNumber.equals(student.getSerialNumber());


    }

 */