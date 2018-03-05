package ro.ubb.laboratory.ui;

import ro.ubb.laboratory.domain.Student;
import ro.ubb.laboratory.service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

/**
 * @author AdrianButnar
 * @version 1.0.0
 */

public class Console {
    private StudentService studentService;

    public Console(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Shows the user interface
     */
    public void runConsole() {
        addStudents();
        printAllStudents();
    }

    /**
     * Displays all the students from the repository
     */
    private void printAllStudents() {
        Set<Student> students = studentService.getAllStudents();
        students.stream().forEach(System.out::println);
    }

    /**
     * Adds a new student to the repository
     */
    private void addStudents() {
        while (true) {
            Student student = readStudent();
            if (student == null || student.getId() < 0) {
                break;
            }
            try {
                studentService.addStudent(student);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads a new student from the standard input
     * @return The read student if the data was filled correctly or null otherwise
     */
    private Student readStudent() {
        System.out.println("Read student {id,serialNumber,name}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            String serialNumber = bufferRead.readLine();
            String name = bufferRead.readLine();

            Student student = new Student(serialNumber, name);
            student.setId(id);

            return student;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
