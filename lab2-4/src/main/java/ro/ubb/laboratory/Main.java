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

package ro.ubb.laboratory;

import ro.ubb.laboratory.domain.BaseEntity;
import ro.ubb.laboratory.domain.Student;
import ro.ubb.laboratory.repository.*;
import ro.ubb.laboratory.service.StudentService;
import ro.ubb.laboratory.ui.Console;

/**
 * <h1>
 *     Laboratory grades management application.
 * </h1>
 * <p> Problem 2 for the SDI course. </p>
 * <p> Problem text:
 * A teacher manages information about students and lab problems.
 * Create an application which allows to:
 * perform CRUD operations on students and lab problems
 * assign problems to students; assign grades
 * filter entities based on various criteria
 * reports: e.g. find the problem that was assigned most times
 * </p>
 * <p>
 *     use Java 8 features (lambda expressions, streams etc); the program should be written <br>
 *     without if statements and loops <br>
 *
 *     persistence: ‘in memory’, text files, xml, db (jdbc); you may use almost any RDBMS, <br>
 *         but we only offer support for PostgreSQL; MS SQL Server is forbidden <br>
 * </p>
 * @author Gandaceii
 * @version 1.0.0
 * @since 5/3/2018
 */

public class Main {

    public static void main(String[] args)
    {
        Repository<Long, Student> studentRepository = new InMemoryRepository<>();
        StudentService studentService = new StudentService(studentRepository);
        Console console = new Console(studentService);
        console.runConsole();

        System.out.println("Hello world!");
    }

}
