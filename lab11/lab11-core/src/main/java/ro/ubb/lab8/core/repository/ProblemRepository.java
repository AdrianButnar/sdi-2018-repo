package ro.ubb.lab8.core.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.lab8.core.model.Problem;


public interface ProblemRepository extends MainRepository<Problem, Long> {
}
