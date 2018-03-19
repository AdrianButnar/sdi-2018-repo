package ro.ubb.laboratory.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <h2> Student class </h2>
 * <p> Student </p>
 * @version 1.0.0
 */

public class Student extends BaseEntity<Long> {
    private String serialNumber;
    private String name;
    private List<Problem> problemList = new ArrayList<>();

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
     * @param name - Name of the student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Assigns a problem to this student.
     * @param pb - pproblem to be added to the list
     */
    public void addProblem(Problem pb) {
        problemList.add(pb);
    }

    /**
     * Gets the number of assigned problem this student has.
     * @return - the number of problems
     */
    public int getNumberOfProblems() {
        return problemList.size();
    }

    /**
     * Override the equals method of an Object
     * @param o - object to be compared to
     * @return True - if equals | False - otherwise
     */
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
    public int hashCode() {
        int result = serialNumber != null ? serialNumber.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
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
