package ro.ubb.lab7.client.ui;


import org.springframework.web.client.RestTemplate;
import ro.ubb.lab7.core.model.Problem;
import ro.ubb.lab7.core.model.Student;
import ro.ubb.lab7.web.dto.ProblemDto;
import ro.ubb.lab7.web.dto.ProblemsDto;
import ro.ubb.lab7.web.dto.StudentDto;
import ro.ubb.lab7.web.dto.StudentsDto;

import java.util.List;
import java.util.Scanner;
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
                        "10. Show the most assigned problems\n"+
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
                    System.out.println("Not yet implemented");
                    break;
                case "7":
                    System.out.println("Not yet implemented");
                    break;
                case "8":
                    System.out.println("Not yet implemented");
                    break;
                case "9":
                    System.out.println("Not yet implemented");
                    break;
                case "10":
                    System.out.println("Not yet implemented");
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
                .forEach(System.out::println); //asta e print
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
                    .postForObject("http://localhost:8080/api/students",
                            new ProblemDto(received.getNumber(),received.getText()),
                            ProblemDto.class);
            restTemplate
                    .put("http://localhost:8080/api/problems/{problemId}",
                            problem, problem.getId());


    }
//
//    private void removeProblem(){
//        try {
//            System.out.print("Enter id: ");
//            Scanner sc = new Scanner(System.in);
//            String id = sc.nextLine();
//            if (!isLong(id)){
//                throw new InexistentEntityException("Invalid id!\n");
//            }
//            serviceInterface.removeProblem(Long.parseLong(id));
////            CompletableFuture<String> result = serviceInterface.removeProblem(Long.parseLong(id));
////            handleResult(result);
//        }
//        catch (InexistentEntityException ex){
//            System.out.println("Exception client-side: ");
//            ex.printStackTrace();
//            myWait(1);
//        }
//    }
    private void printAllProblems() {
        ProblemsDto problemsDto = restTemplate
                .getForObject("http://localhost:8080/api/problems", ProblemsDto.class);
        problemsDto.getProblems()
                .forEach(System.out::println);
    }
//    private void assignProblemToStudent(){
//        try {
//            Scanner sc = new Scanner(System.in);
//
//            System.out.print("Enter an assignment id: ");
//            String assignmentId = sc.nextLine();
//
//            System.out.print("Enter a student id: ");
//            String studentId = sc.nextLine();
//
//            System.out.print("Enter a problem id: ");
//            String problemId = sc.nextLine();
//
//////          String returnString = " "+";"+studentId+";"+problemId;
////            Future<String> s = helloService.assignProblemToStudent(returnString);
////            {
////                String result = s.get(); //blocking
////                System.out.println(result);
////            }
//            serviceInterface.assignProblemToStudent(Long.parseLong(assignmentId),Long.parseLong(studentId),Long.parseLong(problemId));
//
//        } catch (Exception ex) {
//            System.out.println("Exception client-side: ");
//            ex.printStackTrace();
//            myWait(1);
//        }
//    }
//
//    private void showAllProblemsOfAStudent() {
//        try {
//            System.out.print("Enter a student id: ");
//            Scanner sc = new Scanner(System.in);
//            String studentId = sc.nextLine();
//            if (!isLong(studentId)) {
//                throw new InexistentEntityException("Invalid id!\n");
//            }
////            Future<String> assignments = helloService.showAllProblemsOfAStudent(studentId);
//            serviceInterface.showAllProblemsOfAStudent(Long.parseLong(studentId));
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            myWait(1);
//        }
//    }
//
//    private void showStudentsByNameMatch() {
//
//        try{
//            System.out.print("Enter a name or part of the name: ");
//            Scanner sc = new Scanner(System.in);
//            String name = sc.nextLine();
//            serviceInterface.showStudentsByNameMatch(name);
//
//        }
//        catch (InexistentEntityException e){
//            e.printStackTrace();
//            myWait(1);
//        }
//    }
//
//
//    private boolean isLong(String id){
//        try{
//            Long id1 = Long.parseLong(id);
//            return true;
//        }
//        catch (Exception ex){
//            return false;
//        }
//    }
//
//    /**
//     * Displays all the students from the laboratory.domain.repository
//     */
//    private void myWait(long f){
//        try {
//            TimeUnit.SECONDS.sleep(f);
//        }
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//

}
