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

package ro.ubb.laboratory.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final String SERIAL_NUMBER = "baie2079";
    private static final String NEW_SERIAL_NUMBER = "baie2081";
    private static final String NAME = "Bob";
    private static final String NEW_NAME = "Alice";

    private Student student;

    @Before
    public void setUp() throws Exception {
        student = new Student(SERIAL_NUMBER, NAME);
        student.setId(ID);
    }

    @After
    public void tearDown() throws Exception {
        student=null;
    }

    @Test
    public void getSerialNumber() throws Exception {
        assertEquals("Something went wrong in the Student SERIAL getter",
                SERIAL_NUMBER, student.getSerialNumber());
    }

    @Test
    public void setSerialNumber() throws Exception {
        student.setSerialNumber(NEW_SERIAL_NUMBER);
        assertEquals("Something went wrong in the Student SERIAL setter",
                NEW_SERIAL_NUMBER, student.getSerialNumber());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Something went wrong in the Student NAME getter",
                NAME, student.getName());
    }

    @Test
    public void setName() throws Exception {
        student.setName(NEW_NAME);
        assertEquals("Something went wrong in the Student NAME setter",
                NEW_NAME, student.getName());
    }

    @Test
    public void getID() throws Exception {
        assertEquals("Something went wrong in the Student ID getter",
                ID, student.getId());
    }

    @Test
    public void setID() throws Exception {
        student.setId(NEW_ID);
        assertEquals("Something went wrong in the Student ID setter",
                NEW_ID, student.getId());
    }




}