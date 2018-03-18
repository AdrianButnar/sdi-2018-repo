package ro.ubb.laboratory.ui;

import ro.ubb.laboratory.domain.Student;
import ro.ubb.laboratory.domain.validators.*;
import ro.ubb.laboratory.service.StudentService;

import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
                        "1.Add a new student to the laboratory.domain.repository\n"+
                        "2.Show all students\n"+
                        "3.Remove a student\n"+
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
                case "3":
                    removeStudent();
                    continue;
                case "0":
                    System.exit(0);
            }
        }

    }

    /**
     * Displays all the students from the laboratory.domain.repository
     */
    private void myWait(long f){
        try {
            TimeUnit.SECONDS.sleep(f);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }


    private void printAllStudents() {
        Set<Student> students = studentService.getAllStudents();
        students.stream().forEach(System.out::println);
    }

    /**
     * Adds a new student to the laboratory.domain.repository
     */
    private void removeStudent(){
        try {
            System.out.print("Enter id: ");
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            if (!isLong(id)){
                throw new InexistentStudentException("Invalid id!\n");
            }
            studentService.removeStudent(Long.parseLong(id));
        }
        catch (InexistentStudentException se){
            se.printStackTrace();
            myWait(1);
        }
    }


    private void addStudents() {
        try {
            Student student = readStudent();
            if(student==null)
                return;
            studentService.addStudent(student);
        }
        catch (StudentCannotBeSavedException se){
            se.printStackTrace();
            myWait(1);

        }



    }

    /**
     * Reads a new student from the standard input
     * @return The read student if the data was filled correctly or null otherwise
     */

    private static boolean isLong(String id){
        try{
            Long id1 = Long.parseLong(id);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    private Student readStudent() {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter student id: ");
            String id = sc.nextLine();
            System.out.print("Enter serial number: ");
            String serialNumber = sc.nextLine();
            System.out.print("Enter name: ");
            String name = sc.nextLine();

            Student student = new Student(serialNumber, name);

            if (isLong(id))
                student.setId(Long.parseLong(id));
            else
                throw new IllegalIdException("Invalid id\n");

            StudentValidator sv = new StudentValidator();
            sv.validate(student);

            return student;
        }

        catch (IllegalIdException|ValidatorException ex) {
            ex.printStackTrace();
            myWait(1);

        }


    return null;
    }
}
