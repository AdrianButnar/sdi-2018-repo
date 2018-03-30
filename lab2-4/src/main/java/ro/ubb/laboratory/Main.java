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

import ro.ubb.laboratory.domain.Assignment;
import ro.ubb.laboratory.domain.Problem;
import ro.ubb.laboratory.domain.Student;
import ro.ubb.laboratory.domain.validators.AssignmentValidator;
import ro.ubb.laboratory.domain.validators.ProblemValidator;
import ro.ubb.laboratory.domain.validators.StudentValidator;
import ro.ubb.laboratory.domain.validators.Validator;
import ro.ubb.laboratory.repository.*;
import ro.ubb.laboratory.service.AssignmentDbService;
import ro.ubb.laboratory.service.ProblemService;
import ro.ubb.laboratory.service.StudentService;
import ro.ubb.laboratory.ui.Console;

import java.io.File;
import java.sql.*;
import java.util.Scanner;
import java.io.IOException;

/**
 * <h1>
 *     Laboratory grades management application.
 * </h1>
 *
 * <p>
 * A teacher manages information about students and lab problems.
 * Create an application which allows to:
 * - perform CRUD operations on students and lab problems
 * - assign problems to students; assign grades
 * - filter entities based on various criteria
 * - reports: e.g. find the problem that was assigned most times
 * </p>
 * <br>
 * <p>
 * I1 (deadline week 2):
    - two features (e.g: addStudent and printAllStudents)<br>
    - java doc in html format (see the example from the group - project_root/doc/index.html - repository interface) <br>
    - only inmemory repository is enough <br>
 * I2 (deadline week 3):
    - three features <br>
 * I3 (deadline week 4):
    - all features <br>
 * </p>
 *
 * Should we choose the ID? - we choose it
 * Can the user see the ID?
 * How to assign grades? Student has an array of grades and problems?
 *
 */

public class Main {

    public static void main(String[] args) throws Exception {
        //xml-repo
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Problem> problemValidator = new ProblemValidator();

        Repository<Long, Student> studentRepository =
                new StudentXmlRepository("./data/students.xml",studentValidator);
        Repository<Long, Problem> problemRepository =
                new ProblemXmlRepository("./data/problems.xml",problemValidator);
        StudentService studentService = new StudentService(studentRepository);
        ProblemService problemService = new ProblemService(problemRepository);
        Console console = new Console(studentService,problemService);
        console.runConsole();
//
      
//        In memory repository
//        Validator<Student> studentValidator = new StudentValidator();
//        Validator<Problem> problemValidator = new ProblemValidator();
//        Repository<Long, Student> studentRepository = new InMemoryRepository(studentValidator);
//        Repository<Long, Problem> problemRepository = new InMemoryRepository(problemValidator);
//        StudentService studentService = new StudentService(studentRepository);
//        ProblemService problemService = new ProblemService(problemRepository);
//        Console console = new Console(studentService, problemService);
//        console.runConsole();
//
//        System.out.println("Hello world!");

//       File Repository
//        try {
//            System.out.println(new File(".").getCanonicalPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //in file repo

//         Repository<Long, Student> studentRepository =
//                 new StudentXmlRepository("./data/students.xml");
//         StudentService studentService = new StudentService(studentRepository);
//         Console console = new Console(studentService);
//         console.runConsole();


//        In memory repository
//        Validator<Student> studentValidator = new StudentValidator();
//        Validator<Problem> problemValidator = new ProblemValidator();
//        Repository<Long, Student> studentRepository = new InMemoryRepository(studentValidator);
//        Repository<Long, Problem> problemRepository = new InMemoryRepository(problemValidator);
//        StudentService studentService = new StudentService(studentRepository);
//        ProblemService problemService = new ProblemService(problemRepository);
//        Console console = new Console(studentService, problemService);
//        console.runConsole();
//
//        System.out.println("Hello world!");

//       File Repository
//        try {
//            System.out.println(new File(".").getCanonicalPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        //in file repo
//        Validator<Student> studentValidator = new StudentValidator();
//        Validator<Problem> problemValidator = new ProblemValidator();
//        Repository<Long, Student> studentRepository = new StudentFileRepository(studentValidator, ".\\data\\studentFile");
//        Repository<Long, Problem> problemRepository = new ProblemFileRepository(problemValidator, ".\\data\\problemFile");
//        StudentService studentService = new StudentService(studentRepository);
//        ProblemService problemService = new ProblemService(problemRepository);
//        Console console = new Console(studentService, problemService);
//        console.runConsole();


        //in database repo
//        Validator<Student> studentValidator = new StudentValidator();
//        Validator<Problem> problemValidator = new ProblemValidator();
//        Validator<Assignment> assignmentValidator = new AssignmentValidator();
//        Repository<Long, Student> studentRepository = new StudentDbRepository(studentValidator, "jdbc:postgresql://localhost:5432/Mppdatabase");
//        Repository<Long, Problem> problemRepository = new ProblemDbRepository(problemValidator, "jdbc:postgresql://localhost:5432/Mppdatabase");
//        Repository<Long, Assignment> assignmentRepository = new AssignmentDbRepository(assignmentValidator, "jdbc:postgresql://localhost:5432/Mppdatabase");
//
//        StudentService studentService = new StudentService(studentRepository);
//        ProblemService problemService = new ProblemService(problemRepository);
//        AssignmentDbService assignmentDbService = new AssignmentDbService(assignmentRepository);
//        Console console = new Console(studentService, problemService,assignmentDbService);
//        console.runConsole();

    }
}
