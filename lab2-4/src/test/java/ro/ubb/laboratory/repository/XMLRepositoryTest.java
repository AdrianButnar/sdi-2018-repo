package ro.ubb.laboratory.repository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.ubb.laboratory.domain.Problem;
import ro.ubb.laboratory.domain.Student;
import ro.ubb.laboratory.domain.validators.*;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XMLRepositoryTest {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Problem> problemValidator = new ProblemValidator();

    Repository<Long, Student> studentRepository =
            new StudentXmlRepository("./data/students.xml", studentValidator);
    Repository<Long, Problem> problemRepository =
            new ProblemXmlRepository("./data/problems.xml", problemValidator);

    private Student student1;
    private Problem problem1;


    @Before
    public void setUp() throws Exception {

        student1 = new Student("123456", "Ana");
        student1.setId(21L);

        problem1 = new Problem(2, "Do 1 + 1!");
        problem1.setId(10L);


    }
    @Test(expected = EntityPresentException.class)
    public void testExistentStudent(){
        studentRepository.save(student1);

    }

    @Test(expected = EntityNonExistentException.class)
    public void testInexistentStudent() {
        studentRepository.remove(93L);
    }

    @Test
    public void addTest(){
        problemRepository.save(problem1);
    }

    @Test
    public void removeTest(){
        problemRepository.remove(problem1.getId());
    }
    @Test
    public void findAll() throws Exception
    {
        assertEquals(problemRepository.findAll().spliterator().getExactSizeIfKnown(),6 );

    }
}