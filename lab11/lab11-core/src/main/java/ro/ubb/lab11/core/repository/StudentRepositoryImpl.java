package ro.ubb.lab11.core.repository;


import ro.ubb.lab11.core.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by radu.
 */
public class StudentRepositoryImpl extends CustomRepositorySupport<Student,Long> implements StudentRepositoryCustom {
    @Override
    public List<Student> findAllWithJpql() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct s from Student s " +
                "left join fetch s.assignments ass " +
                "left join fetch ass.problem");
        List<Student> students = query.getResultList();

        return students;
    }


}
