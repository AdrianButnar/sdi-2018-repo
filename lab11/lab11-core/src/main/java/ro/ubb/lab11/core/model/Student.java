package ro.ubb.lab11.core.model;


import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Propagation;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "studentWithAssignments",
                attributeNodes = @NamedAttributeNode(value = "assignments")),
        @NamedEntityGraph(name = "studentWithAssignmentsAndProblems",
                attributeNodes = @NamedAttributeNode(value = "assignments", subgraph = "studentWithProblems"),
                subgraphs = @NamedSubgraph(name = "studentWithProblems",
                        attributeNodes = @NamedAttributeNode(value = "problem")))
})

@Entity
@NoArgsConstructor
@EqualsAndHashCode (callSuper = true)
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Student extends BaseEntity<Long> {
    private static final int SERIAL_NUMBER_LENGTH = 16;

    @NotEmpty
    @Max(9999)
    @Column(name = "serialnumber", nullable = false, length = SERIAL_NUMBER_LENGTH)
    private String serialNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Assignment> assignments = new HashSet<>();

    public Student(String serialNumber, String name) {
        this.serialNumber = serialNumber;
        this.name = name;
    }

    public Set<Problem> getProblems() {
        //Hibernate.initialize(this.getAssignments());

        return Collections.unmodifiableSet(
                this.assignments.stream().
                        map(Assignment::getProblem).
                        collect(Collectors.toSet()));
    }

    public void addProblem(Problem problem) {
        Assignment assignment = new Assignment();
        assignment.setProblem(problem);
        assignment.setStudent(this);
        assignment.setGrade(10);
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

    public void removeProblems()
    {
        assignments.clear();
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