package ro.ubb.lab11.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (callSuper = false, exclude = "assignments")
@Builder
public class Problem extends BaseEntity<Long> {

    @Max(99)
    @Column(name = "number", nullable = false)
    private int number;

    @NotEmpty
    @Column(name = "text", nullable = false)
    private String text;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //nu stiu daca trebuie si aici, la el era
    private Set<Assignment> assignments = new HashSet<>();

    public Problem(int number, String text) {
        this.number = number;
        this.text = text;
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(
                assignments.stream()
                        .map(Assignment::getStudent)
                        .collect(Collectors.toSet())
        );
    }

    public void addStudent(Student student) {
        Assignment assignment = new Assignment();
        assignment.setStudent(student);
        assignment.setProblem(this);
        assignments.add(assignment);
    }

    public void addGrade(Student student, Integer grade) {
        Assignment assignment = new Assignment();
        assignment.setStudent(student);
        assignment.setGrade(grade);
        assignment.setProblem(this);
        assignments.add(assignment);
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return  "Problem {" +
                "ID = " + this.getId() +
                ", Number = '" + number + '\'' +
                ", Text = '" + text + '\'' +
                '}';
    }

}
/*

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problem problem = (Problem) o;
        return number == problem.number &&
                Objects.equals(text, problem.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number, text);
    }

 */