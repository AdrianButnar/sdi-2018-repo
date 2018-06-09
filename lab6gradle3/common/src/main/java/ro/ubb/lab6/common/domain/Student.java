package ro.ubb.lab6.common.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * <h2> Student class </h2>
 * <p> Student </p>
 * @version 1.0.0
 */

public class Student extends BaseEntity<Long> implements Serializable {
    private String serialNumber;
    private String name;
    private List<Integer> problemList = new ArrayList<>();

    public List<Integer> getProblemList() {
        return problemList;
    }

    public String getProblemListToString() {
        String content="";
        for ( Integer p : problemList){
            content += String.valueOf(p) + ", ";
        }
        content = content.substring(0,content.length()-2)+".";
        return content;
    }


    public Student() {

    }

    public Student(String serialNumber, String Name) {
        this.serialNumber = serialNumber;
        this.name = Name;
    }

    public Student(Long id, String serialNumber, String Name) {
        this.serialNumber = serialNumber;
        this.name = Name;
        this.setId(id);
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

    /**
     * Assigns a problem to this student.
     *
     * @param no - ID of the problem to be added to the list
     */
    public void addProblem(Integer no) {
        problemList.add(no);
    }

    /**
     * Gets the number of assigned problem this student has.
     *
     * @return - the number of problems
     */
    public int getNumberOfProblems() {
        return problemList.size();
    }


    /**
     * Override the equals method of an Object
     *
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

    public String printProblems() {
        String pr = "";
        for(Integer no : problemList)
        {
            pr += no.toString();
            pr += " ";
        }
        return pr;
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
