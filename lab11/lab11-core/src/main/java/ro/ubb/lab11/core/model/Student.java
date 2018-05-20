package ro.ubb.lab11.core.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class Student extends BaseEntity<Long> {
    private static final int SERIAL_NUMBER_LENGTH = 16;

    @Column(name = "serial_number", nullable = false, length = SERIAL_NUMBER_LENGTH)
    private String serialNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Assignment> assignments = new HashSet<>();

    public Student(String serialNumber, String name) {
        this.serialNumber = serialNumber;
        this.name = name;
    }

    public Set<Problem> getProblems() {
        return Collections.unmodifiableSet(
                this.assignments.stream().
                        map(Assignment::getProblem).
                        collect(Collectors.toSet()));
    }

    public void addProblem(Problem problem) {
        Assignment assignment = new Assignment();
        assignment.setProblem(problem);
        assignment.setStudent(this);
        assignments.add(assignment);
    }

    public void addProblems(Set<Problem> problems) {
        problems.forEach(this::addProblem);
    }


    public void addGrade(Problem problem, Integer grade) {
        Assignment assignment = new Assignment();
        assignment.setProblem(problem);
        assignment.setGrade(grade);
        assignment.setStudent(this);
        assignments.add(assignment);
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