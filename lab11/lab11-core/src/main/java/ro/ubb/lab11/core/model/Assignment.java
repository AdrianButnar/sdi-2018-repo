package ro.ubb.lab11.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by radu.
 */

@Entity
@Table(name = "assignmentNEW")
@IdClass(AssignmentPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode (exclude ={"student", "problem"})
public class Assignment implements Serializable {

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId")
    private Student student;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "problemId")
    private Problem problem;

    @Column(name = "grade")
    private Integer grade;

    @Override
    public String toString() {
        return "Assignment{" +
                "student=" + student.getId() +
                ", problem=" + problem.getId() +
                ", grade=" + grade +
                '}';
    }
}
