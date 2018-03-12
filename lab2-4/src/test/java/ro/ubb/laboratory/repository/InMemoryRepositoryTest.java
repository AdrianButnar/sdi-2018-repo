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
import org.junit.Ignore;
import org.junit.Test;
import ro.ubb.laboratory.domain.Student;

import static org.junit.Assert.*;

public class InMemoryRepositoryTest {


    private Repository<Long, Student> studentRepository = new InMemoryRepository<>();

    @Before
    public void setUp() throws Exception {

        Student student1 = new Student("123456", "Ana");
        student1.setId(21L);
        Student student2 = new Student("789101", "Maria");
        student2.setId(34L);
        Student student3 = new Student("121314", "Ioana");
        student3.setId(55L);

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Ignore
    @Test
    public void findOne() throws Exception {
      //  studentRepository.findOne()
    }
    @Ignore
    @Test
    public void findAll() throws Exception
    {
        fail("Not yet tested");
    }
    @Ignore
    @Test
    public void save() throws Exception {
        fail("Not yet tested");
    }

}