package ro.ubb.lab11.core.repository;

import com.sun.xml.internal.bind.v2.model.core.ID;
import ro.ubb.lab11.core.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepositoryCustom {
    List<Student> findAllWithJpql();

    Optional<Student> findOneWithJPQL(Long id); //nu stiu daca trebuie long sau id?????????????????

    List<Student> findAllWithAssignmentsAndProblemsSQL();

   List<Student> findAllWithAssignmentsAndProblemsCriteriaAPI();

}
