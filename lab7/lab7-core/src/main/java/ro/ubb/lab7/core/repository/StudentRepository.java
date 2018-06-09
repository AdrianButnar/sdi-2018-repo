package ro.ubb.lab7.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.lab7.core.model.BaseEntity;
import ro.ubb.lab7.core.model.Student;

import java.io.Serializable;

public interface StudentRepository extends MainRepository<Student, Long> {
}
