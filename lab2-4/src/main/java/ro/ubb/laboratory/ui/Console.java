package ro.ubb.laboratory.ui;

import ro.ubb.laboratory.domain.Student;
import ro.ubb.laboratory.service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Adrian Butnar
 * @version 1.0.0
 */

public class Console {
    private StudentService studentService;

    public Console(StudentService studentService) {
        this.studentService = studentService;
    }

    public void printMenu(){
        System.out.println(
                "\n\n----------------------Menu----------------------\n\n"+
                "1.Add a new student to the repository\n"+
                "2.Show all students\n"+
                "0.Exit\n\n"+
                "Choose one of the commands above:\n "+
                "----------------------------------------------------");

    }

    /**
     * Shows the user interface
     */
    public void runConsole() {
        while (true) {
            printMenu();
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            switch (command) {
                case "1":
                    addStudents();
                    continue;
                case "2":
                    printAllStudents();
                    continue;
                case "0":
                    System.exit(0);
            }
        }

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
//        Student student = readStudent();
//        while (student.getName().equals("") || student.getId() < 0) {
//            student = readStudent();

        try {
            Student student = readStudent();
            studentService.addStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Reads a new student from the standard input
     * @return The read student if the data was filled correctly or null otherwise
     */
    private Student readStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student id: ");
        Long id = Long.valueOf(sc.nextLine());
        System.out.print("Enter serial number: ");
        String serialNumber = sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        Student student = new Student(serialNumber, name);
        student.setId(id);

        return student;
    }
}
