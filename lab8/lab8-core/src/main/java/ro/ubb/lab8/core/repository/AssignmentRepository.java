package ro.ubb.lab8.core.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.lab8.core.model.Assignment;


public interface AssignmentRepository extends MainRepository<Assignment, Long> {
}
