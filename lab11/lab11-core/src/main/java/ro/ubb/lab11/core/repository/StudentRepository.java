package ro.ubb.lab11.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import ro.ubb.lab11.core.model.Student;

import java.util.List;

public interface StudentRepository extends MainRepository<Student, Long>, StudentRepositoryCustom {
    @Query("select distinct s from Student s")
    @EntityGraph(value = "studentWithAssignments", type = EntityGraph.EntityGraphType.LOAD)
    List<Student> findAllWithAssignments();

    @Query("select distinct s from Student s")
    @EntityGraph(value = "studentWithAssignmentsAndProblems", type = EntityGraph.EntityGraphType.LOAD)
    List<Student> findAllWithAssignmentsAndProblems();
}
