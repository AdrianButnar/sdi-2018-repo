package ro.ubb.lab7.client.ui;


import org.springframework.web.client.RestTemplate;
import ro.ubb.lab7.core.model.Assignment;
import ro.ubb.lab7.core.model.Problem;
import ro.ubb.lab7.core.model.Student;
import ro.ubb.lab7.web.dto.*;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ClientConsole {
    private RestTemplate restTemplate;

    public ClientConsole(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private void printMenu(){
        System.out.println(
                "\n\n----------------------Menu----------------------\n\n"+
                        "1.  Add a new student to the repository\n"+
                        "2.  Show all students\n"+
                        "3.  Remove a student\n"+
                        "4.  Add a new problem to the repository of problems\n"+
                        "5.  Show all problems\n"+
                        "6.  Remove a problem \n"+
                        "7.  Assign problem from repository to a student\n"+
                        "8.  Show problems assigned to a student\n"+
                        "9.  Find a student by name(supports partial match)\n"+
                        "0.  Exit\n\n"+
                        "Choose one of the commands above:\n "+
                        "----------------------------------------------------");
    }

    public void runConsole() {

        while (true) {
            printMenu();
            Scanner sc = new Scanner(System.in);
            System.out.println("Input command: ");
            String command = sc.nextLine();

            switch (command) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    printAllStudents();
                    break;
                case "3":
                    removeStudent();
                    break;
                case "4":
                    addProblem();
                    break;
                case "5":
                    printAllProblems();
                    break;
                case "6":
                    removeProblem();
                    break;
                case "7":
                    assignProblemToStudent();
                    break;
                case "8":
                    showAllProblemsOfAStudent();
                    break;
                case "9":
                    showStudentsByNameMatch();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Command not recognised, try again. ");
                    break;

            }
        }
    }

    /**
     * Reads a new student from the standard input
     * @return The read student as a string or null string otherwise
     */
    private Student readStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter serial number: ");
        String serialNumber = sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        return new Student(serialNumber,name);
    }

    private  void addStudent() {

        Student st = readStudent();
        StudentDto student = restTemplate
                .postForObject("http://localhost:8080/api/students",
                        new StudentDto(st.getSerialNumber(),st.getName()),
                        StudentDto.class);
        restTemplate
                .put("http://localhost:8080/api/students/{studentId}",
                        student, student.getId());

    }

    /**
     * Prints to the standard output all the students in the repository
     */
    private void printAllStudents() {
        StudentsDto studentsDto = restTemplate
                .getForObject("http://localhost:8080/api/students", StudentsDto.class);
        studentsDto.getStudents()
                .forEach(System.out::println); //asta e print - :))
    }


    private void removeStudent(){
        System.out.println("Enter student id:");
        Scanner sc = new Scanner(System.in);
        Long id = Long.valueOf(sc.nextLine());
        restTemplate
                .delete("http://localhost:8080/api/students/{studentId}",
                        id);
    }

    /**
     * Reads a new problem from the standard input
     * @return The read problem as a string or null string otherwise
     */
    private Problem readProblem() {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter problem number: ");
            String number = sc.nextLine();
            System.out.print("Enter text: ");
            String text = sc.nextLine();
            return new Problem(Integer.parseInt(number), text);

        } catch (Exception ex) {
            System.out.println("Is this what you call a valid problem number? Really? ");
            ex.printStackTrace();
            //myWait(1);
            return null;
        }
    }


    private void addProblem() {

        Problem received = readProblem();
        ProblemDto problem = restTemplate
                .postForObject("http://localhost:8080/api/problems",
                        new ProblemDto(received.getNumber(),received.getText()),
                        ProblemDto.class);
        restTemplate
                .put("http://localhost:8080/api/problems/{problemId}",
                        problem, problem.getId());


    }

    private void removeProblem(){
        System.out.println("Enter problem id:");
        Scanner sc = new Scanner(System.in);
        Long id = Long.valueOf(sc.nextLine());
        restTemplate
                .delete("http://localhost:8080/api/problems/{problemId}",
                        id);
    }
    private void printAllProblems() {
        ProblemsDto problemsDto = restTemplate
                .getForObject("http://localhost:8080/api/problems", ProblemsDto.class);
        problemsDto.getProblems()
                .forEach(System.out::println);
    }

    private void assignProblemToStudent(){
        try {
            Scanner sc = new Scanner(System.in);

//            System.out.print("Enter an assignment id: ");
//            String assignmentId = sc.nextLine();

            System.out.print("Enter a student id: ");
            String studentId = sc.nextLine();

            System.out.print("Enter a problem id: ");
            String problemId = sc.nextLine();

            Assignment as = new Assignment(Long.parseLong(studentId), Long.parseLong(problemId));
            AssignmentDto assignmentDto = restTemplate
                    .postForObject("http://localhost:8080/api/assignments",
                            new AssignmentDto(as.getStudentId(),as.getProblemId()),
                            AssignmentDto.class);
            restTemplate
                    .put("http://localhost:8080/api/assignments/{assignmentId}",
                            assignmentDto, assignmentDto.getId());

        } catch (Exception ex) {
            System.out.println("Exception client-side: ");
            ex.printStackTrace();
        }
    }

    private void showAllProblemsOfAStudent() {
        try {
            System.out.print("Enter a student id: ");
            Scanner sc = new Scanner(System.in);
            String studentId = sc.nextLine();
            AssignmentsDto assignmentsDto = restTemplate
                    .getForObject("http://localhost:8080/api/assignments", AssignmentsDto.class);
            Set<AssignmentDto> assignments = assignmentsDto.getAssignments();
            String problems="";
            for(AssignmentDto a : assignments)
            {
                if (a.getStudentId() == Long.parseLong(studentId)) {
                    problems += a.getProblemId()+" ";
                }
            }
            if (!problems.equals("")){
                System.out.println("Student " + studentId + " has the following problems assigned: " + problems);
            }
            else
                throw new Exception("This student has no assigned problems!");


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showStudentsByNameMatch() {

        try{
            System.out.print("Enter a name: ");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            StudentsDto studentsDto = restTemplate
                    .getForObject("http://localhost:8080/api/students", StudentsDto.class);

            Set<StudentDto> students =  studentsDto.getStudents();
            boolean found=false;
            for (StudentDto s:students){
                if (s.getName().toLowerCase().contains(name)){
                    System.out.println(s);
                    found = true;
                }
            }
            if(!found){
                throw new Exception("No student matches this name!");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



}
