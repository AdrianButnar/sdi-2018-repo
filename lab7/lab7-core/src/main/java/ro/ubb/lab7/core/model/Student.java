package ro.ubb.lab7.core.model;

import javax.persistence.Entity;

@Entity
public class Student extends BaseEntity<Long> {
    private String serialNumber;
    private String name;

    public Student() {

    }

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
    public boolean equals(Object o) {
        //can we use ifs here?
        /*
        boolean result1 = this == o;
        boolean result2 = !(o == null || getClass() != o.getClass());

        Student student = (Student) o;
        boolean result3 = serialNumber.equals(student.getName()) && name.equals(student.getName());
        return result3 || result2 || result1;
        */
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (name.equals(student.getName())) return false;
        return serialNumber.equals(student.getSerialNumber());


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
