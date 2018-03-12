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

import org.hamcrest.Matcher;
import org.hamcrest.core.Every;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ro.ubb.laboratory.domain.Problems;
import ro.ubb.laboratory.domain.Student;

import java.util.Arrays;

import static org.junit.Assert.*;

public class InMemoryRepositoryTest {


    private Repository<Long, Student> studentRepository = new InMemoryRepository<>();
    private Repository<Long, Problems> problemsRepository = new InMemoryRepository<>();

    Student student1;
    Student student2;
    Student student3;
    Problems problem1;
    Problems problem2;
    Problems problem3;


    @Before
    public void setUp() throws Exception {

        student1 = new Student("123456", "Ana");
        student1.setId(21L);
        student2 = new Student("789101", "Maria");
        student2.setId(34L);
        student3 = new Student("121314", "Ioana");
        student3.setId(55L);
        problem1 = new Problems(2, "Do 1 + 1!");
        problem1.setId(1L);
        problem2 = new Problems(2, "Do 1 + 2!");
        problem2.setId(2L);
        problem3 = new Problems(2, "Do 1 + 3!");
        problem3.setId(3L);

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        problemsRepository.save(problem1);
        problemsRepository.save(problem2);
        problemsRepository.save(problem3);
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void findOne() throws Exception {
      assertTrue("Something went wrong in the Repo Student finder", studentRepository.findOne(21L).isPresent() && student1.equals(studentRepository.findOne(21L).get()));
      assertTrue("Something went wrong in the Repo Problem finder", problemsRepository.findOne(1L).isPresent() && problem1.equals(problemsRepository.findOne(1L).get()));

    }

    @Test
    public void findAll() throws Exception
    {
        Iterable<Student> students = Arrays.asList(student1, student2, student3);
        //Still left this to do 
        //assertThat(studentRepository.findAll(),Matcher<Student>);
    }

    @Test
    public void save() throws Exception {
        Student student4 = new Student("151617", "Carmen");
        student4.setId(89L);
        Problems problem4 = new Problems(4, "Do 1 + 4!");
        problem4.setId(4L);
        studentRepository.save(student4);
        problemsRepository.save(problem4);
        assertTrue("Something went wrong in the Repo Student save", studentRepository.findOne(89L).isPresent() && student4.equals((studentRepository.findOne(89L).get())));
        assertTrue("Something went wrong in the Repo Problem save", problemsRepository.findOne(4L).isPresent() && problem4.equals((problemsRepository.findOne(4L).get())));

    }

}