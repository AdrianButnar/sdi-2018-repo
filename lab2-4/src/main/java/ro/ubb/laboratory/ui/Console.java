package ro.ubb.laboratory.ui;

import ro.ubb.laboratory.domain.Problem;
import ro.ubb.laboratory.domain.Student;
import ro.ubb.laboratory.domain.validators.*;
import ro.ubb.laboratory.service.ProblemService;
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
    private ProblemService problemService;

    public Console(StudentService studentService, ProblemService problemService) {
        this.studentService = studentService;
        this.problemService = problemService;
    }

    private void printMenu(){
        System.out.println(
                "\n\n----------------------Menu----------------------\n\n"+
                        "1. Add a new student to the repository\n"+
                        "2. Show all students\n"+
                        "3. Remove a student\n"+
                        "4. Add a new problem to the repository of problems\n"+
                        "5. Show all problems\n"+
                        "6. Remove a problem \n"+
                        "7. Assign problem form repository to a student\n"+
                        "0. Exit\n\n"+
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
                case "4":
                    addProblems();
                    continue;
                case "5":
                    printAllProblems();
                    continue;
                case "6":
                    removeProblem();
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

    /**
     * Prints to the standard output all the students in the repository
     */
    private void printAllStudents() {
        Set<Student> students = studentService.getAllStudents();
        students.stream().forEach(System.out::println);
    }

    /**
     * Prints to the standard output all the problems in the repository
     */
    private void printAllProblems() {
        Set<Problem> problems = problemService.getAllProblems();
        problems.stream().forEach(System.out::println);
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
            System.out.println("Student successfully removed!");
        }
        catch (InexistentStudentException se){
            se.printStackTrace();
            myWait(1);
        }
    }
    private void removeProblem(){
        try {
            System.out.print("Enter id: ");
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            if (!isLong(id)){
                throw new EntityNonExistentException("Invalid id!\n");
            }
            problemService.removeProblem(Long.parseLong(id));
            System.out.println("Problem successfully removed!");

        }
        catch (EntityNonExistentException ex){
            ex.printStackTrace();
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

    private void addProblems() {
        try {
            Problem problem = readProblem();
            if(problem == null)
                return;
            problemService.addProblem(problem);
        }
        catch (EntityCannotBeSavedException ex){
            ex.printStackTrace();
            myWait(1);

        }
    }




    private static boolean isLong(String id){
        try{
            Long id1 = Long.parseLong(id);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    /**
     * Reads a new student from the standard input
     * @return The read student if the data was filled correctly or null otherwise
     */
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

    /**
     * Reads a new problem from the standard input
     * @return The read student if the data was filled correctly or null otherwise
     */
    private Problem readProblem() {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter problem id: ");
            String id = sc.nextLine();
            System.out.print("Enter problem number: ");
            String number = sc.nextLine();
            System.out.print("Enter text: ");
            String text = sc.nextLine();

            Problem problem = new Problem(Integer.parseInt(number), text);

            if (isLong(id))
                problem.setId(Long.parseLong(id));
            else
                throw new IllegalIdException("Invalid id\n");

            return problem;


        }
        catch (IllegalIdException ex) {
            ex.printStackTrace();
            myWait(1);

        }
        return null;
    }
}
