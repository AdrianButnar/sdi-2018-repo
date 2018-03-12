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

import java.awt.*;

import static org.junit.Assert.*;

public class ProblemsTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private  static final String TEXT = "Add 2 and 2. What is the result?";
    private static final String NEW_TEXT = "Substract 2 from 3. What is the result?";
    private static final int NUMBER = 7;
    private static final int NEW_NUMBER = 8;


    private Problems problem;

    @Before
    public void setUp() throws Exception {
        problem = new Problems(NUMBER, TEXT);
        problem.setId(ID);
    }

    @After
    public void tearDown() throws Exception {
        problem=null;
    }

    @Test
    public void getNumber() throws Exception {
        assertEquals("Something went wrong in the Problem NUMBER getter",
                TEXT, problem.getText());
    }

    @Test
    public void setNumber() throws Exception {
        problem.setNumber(NEW_NUMBER);
        assertEquals("Something went wrong in the Problem NUMBER setter",
                TEXT, problem.getText());
    }

    @Test
    public void getText() throws Exception {
        assertEquals("Something went wrong in the Problem TEXT getter",
                TEXT, problem.getText());
    }

    @Test
    public void setText() throws Exception {
        problem.setText(NEW_TEXT);
        assertEquals("Something went wrong in the Problem TEXT setter",
                NEW_TEXT, problem.getText());
    }
    @Test
    public void getID() throws Exception {
        assertEquals("Something went wrong in the Problem ID getter",
                TEXT, problem.getText());
    }

    @Test
    public void setId() throws Exception {
        problem.setId(NEW_ID);
        assertEquals("Something went wrong in the Problem ID setter",
                TEXT, problem.getText());
    }

}