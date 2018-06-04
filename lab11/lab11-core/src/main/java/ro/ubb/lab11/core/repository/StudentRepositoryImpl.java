package ro.ubb.lab11.core.repository;


import com.sun.xml.internal.bind.v2.model.core.ID;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.lab11.core.model.Assignment;
import ro.ubb.lab11.core.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import ro.ubb.lab11.core.model.Student_;
import ro.ubb.lab11.core.model.Assignment_;


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

    @Override
    public Optional<Student> findOneWithJPQL(Long id) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct s from Student s " +
                "left join fetch s.assignments ass " +
                "left join fetch ass.problem "+
                "where s.id="+id);
        List<Student> students = query.getResultList();
        return Optional.ofNullable(students.get(0));
    }


    @Override
    @Transactional
    public List<Student> findAllWithAssignmentsAndProblemsSQL() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {s.*},{ass.*},{p.*} " +
                "from student s " +
                "left join assignmentnew ass on s.id=ass.studentid " + //aici nu stiu daca trebuie .student
                "left join problem p on ass.problemid=p.id ")
                .addEntity("s",Student.class)
                .addJoin("ass", "s.assignments")
                .addJoin("p", "ass.problem")
                .addEntity("s",Student.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Student> students = query.getResultList();


        return students;
    }

    @Override
    public List<Student> findAllWithAssignmentsAndProblemsCriteriaAPI() {

        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
        query.distinct(Boolean.TRUE);
        Root<Student> root = query.from(Student.class);
        Fetch<Student, Assignment> authorBookFetch = root.fetch(Student_.assignments);//el are si bara jos??
        authorBookFetch.fetch(Assignment_.problem);

        List<Student> students = entityManager.createQuery(query).getResultList();
        return students;
        //return null;
    }


}
