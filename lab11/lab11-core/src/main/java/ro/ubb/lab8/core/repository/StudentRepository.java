package ro.ubb.lab8.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.lab8.core.model.Student;

public interface StudentRepository extends MainRepository<Student, Long> {
}
