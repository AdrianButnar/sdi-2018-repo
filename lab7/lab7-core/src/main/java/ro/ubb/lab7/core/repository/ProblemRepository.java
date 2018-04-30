package ro.ubb.lab7.core.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.lab7.core.model.Problem;


public interface ProblemRepository extends MainRepository<Problem, Long> {
}
