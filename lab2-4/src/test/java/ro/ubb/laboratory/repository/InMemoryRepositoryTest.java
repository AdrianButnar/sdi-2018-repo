/*
 * Copyright (c) 2018. Gandaceii
 *
 * MIT
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package ro.ubb.laboratory.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.ubb.laboratory.domain.Problem;
import ro.ubb.laboratory.domain.Student;
import ro.ubb.laboratory.domain.validators.EntityNonExistentException;
import ro.ubb.laboratory.domain.validators.InexistentStudentException;
import ro.ubb.laboratory.domain.validators.StudentValidator;
import ro.ubb.laboratory.domain.validators.Validator;

import java.util.Arrays;

import static org.junit.Assert.*;

public class InMemoryRepositoryTest {

    Validator<Student> studentValidator = new StudentValidator();

    private Repository<Long, Student> studentRepository = new InMemoryRepository<>(studentValidator);
    //private Repository<Long, ProblemFileRepository> problemsRepository = new InMemoryRepository<>(studentValidator);

    private Student student1;
    private Student student2;
    private Student student3;
//    private ProblemFileRepository problem1;
//    private ProblemFileRepository problem2;
//    private ProblemFileRepository problem3;


    @Before
    public void setUp() throws Exception {

        student1 = new Student("123456", "Ana");
        student1.setId(21L);
        student2 = new Student("789101", "Maria");
        student2.setId(34L);
        student3 = new Student("121314", "Ioana");
        student3.setId(55L);
//        problem1 = new ProblemFileRepository(2, "Do 1 + 1!");
//        problem1.setId(1L);
//        problem2 = new ProblemFileRepository(2, "Do 1 + 2!");
//        problem2.setId(2L);
//        problem3 = new ProblemFileRepository(2, "Do 1 + 3!");
//        problem3.setId(3L);

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
//        problemsRepository.save(problem1);
//        problemsRepository.save(problem2);
//        problemsRepository.save(problem3);
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void findOne() throws Exception {
      assertTrue("Something went wrong in the Repo Student finder", studentRepository.findOne(21L).isPresent() && student1.equals(studentRepository.findOne(21L).get()));
//      assertTrue("Something went wrong in the Repo ProblemFileRepository finder", problemsRepository.findOne(1L).isPresent() && problem1.equals(problemsRepository.findOne(1L).get()));

    }

    @Test(expected = EntityNonExistentException.class)
    public void testEntityNonExistentException()
    {
        studentRepository.remove(94L);
    }

    @Test
    public void findAll() throws Exception
    {
        Iterable<Student> students = Arrays.asList(student1, student2, student3);
        int count = 0;
        assertEquals(studentRepository.findAll().spliterator().getExactSizeIfKnown(), 3);
        assertTrue("Something went wrong in the Repo Student finder", studentRepository.findOne(21L).isPresent() && student1.equals(studentRepository.findOne(21L).get()));

        //assertThat(Iterables .studentRepository.findAll(),Matcher<Student>);
    }

    @Test
    public void save() throws Exception {
        Student student4 = new Student("151617", "Carmen");
        student4.setId(89L);
        Problem problem4 = new Problem(4, "Do 1 + 4!");
        problem4.setId(4L);
        studentRepository.save(student4);
//        problemsRepository.save(problem4);
        assertTrue("Something went wrong in the Repo Student save", studentRepository.findOne(89L).isPresent() && student4.equals((studentRepository.findOne(89L).get())));
//        assertTrue("Something went wrong in the Repo ProblemFileRepository save", problemsRepository.findOne(4L).isPresent() && problem4.equals((problemsRepository.findOne(4L).get())));

    }

}