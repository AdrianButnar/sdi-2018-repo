package ro.ubb.lab5.client.ui;

import ro.ubb.socket.common.ServiceInterface;
import ro.ubb.socket.common.domain.Student;
import ro.ubb.socket.common.domain.validators.IllegalIdException;
import ro.ubb.socket.common.domain.validators.InexistentEntityException;
import ro.ubb.socket.common.domain.validators.ValidatorException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.plaf.InternalFrameUI;
import java.util.Scanner;
import java.util.concurrent.*;

public class ClientConsole {
    private ServiceInterface helloService;

    public ClientConsole(ServiceInterface helloService) {
        this.helloService = helloService;
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
                    removeProblem();
                    break;
                case "7":
                    assignProblemToStudent();
                    break;
                case "8":
                    showAllProblemsOfAStudent();
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
    private String readStudent() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter student id: ");
            String id = sc.nextLine();
            System.out.print("Enter serial number: ");
            String serialNumber = sc.nextLine();
            System.out.print("Enter name: ");
            String name = sc.nextLine();

            String returnString = id+";"+serialNumber+";"+name;

            if (!isLong(id)) {
                throw new IllegalIdException("Invalid id\n");
            }

            return returnString;
        }
        catch (IllegalIdException | ValidatorException ex) {
            ex.printStackTrace();
            myWait(1);

        }
        return "";
    }

    private  void addStudent() {
        try {
            String received = readStudent();
            if (!received.equals("")) {
                CompletableFuture<String> s = helloService.addStudent(received);
                handleResult(s);
            }
            else
            {
                System.out.println("Please try again, something is not right client-side");
            }
        }
        catch (Exception ex)
        {
            System.out.println("Exception client-side: ");
            ex.printStackTrace();
            myWait(1);
        }
    }

    /**
     * Prints to the standard output all the students in the repository
     */
//    private  Future<String> printAllStudents() {
    private void printAllStudents() {
        try {
            CompletableFuture<String> result = helloService.printAllStudents("");
            handleResult(result);
        } catch (CancellationException ex)
        {
            System.out.println("Error serverside(or canceled):");
            ex.printStackTrace();
        }
    }

    private void removeStudent(){
        try {
            System.out.print("Enter id: ");
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            if (!isLong(id)){
                throw new InexistentEntityException("Invalid id!\n");
            }
            CompletableFuture<String> result = helloService.removeStudent(id);
            handleResult(result);
        }
        catch (InexistentEntityException se){
            System.out.println("Exception client-side: ");
            se.printStackTrace();
            myWait(1);
        }
    }

    /**
     * Reads a new problem from the standard input
     * @return The read problem as a string or null string otherwise
     */
    private String readProblem() {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter problem id: ");
            String id = sc.nextLine();
            System.out.print("Enter problem number: ");
            String number = sc.nextLine();
            System.out.print("Enter text: ");
            String text = sc.nextLine();
            String returnString = id+";"+number+";"+text;

            if (!isLong(id)) {
                throw new IllegalIdException("Invalid id\n");
            }

            return returnString;
        }
        catch (IllegalIdException | ValidatorException ex) {
            System.out.println("Exception client-side: ");
            ex.printStackTrace();
            myWait(1);

        }
        return "";
    }


    private void addProblem() {
        try {
            String received = readProblem();
            if (!received.equals("")) {
                CompletableFuture<String> result = helloService.addProblem(received);
                handleResult(result);
            }
            else
            {
                System.out.println("Please try again, something is not right client-side");
            }
        }
        catch (Exception ex)
        {
            System.out.println("Exception client-side: ");
            ex.printStackTrace();
            myWait(1);
        }
    }

    private void removeProblem(){
        try {
            System.out.print("Enter id: ");
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            if (!isLong(id)){
                throw new InexistentEntityException("Invalid id!\n");
            }
            CompletableFuture<String> result = helloService.removeProblem(id);
            handleResult(result);
        }
        catch (InexistentEntityException ex){
            System.out.println("Exception client-side: ");
            ex.printStackTrace();
            myWait(1);
        }
    }
    private void printAllProblems() {
        try{
            CompletableFuture<String> result = helloService.printAllProblems("");
            handleResult(result);

        } catch (CancellationException ex)
//=======
//        Future<String> students = helloService.printAllProblems("");
//        try {
//            while (!students.isDone()) { //if- doesn't really work //also kind of blocking
////            if (students.isDone()){
//                String result = students.get(); //kind of blocking
//                String[] args = result.split(";");
//                for (String row : args) {
//                    System.out.println(row);
//                }
//            }
//        } catch (CancellationException | ExecutionException | InterruptedException ex)
//>>>>>>> 1b515d704f6bf1c5e573cacb40ea4bc912a31fb7
        {
            System.out.println("Error serverside(or canceled):");
            ex.printStackTrace();
        }
    }
    private void assignProblemToStudent(){
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter an assignment id: ");
            String assignmentId = sc.nextLine();

            System.out.print("Enter a student id: ");
            String studentId = sc.nextLine();

            System.out.print("Enter a problem id: ");
            String problemId = sc.nextLine();

            String returnString = assignmentId+";"+studentId+";"+problemId;
//            String returnString = " "+";"+studentId+";"+problemId;
            Future<String> s = helloService.assignProblemToStudent(returnString);
            {
                String result = s.get(); //blocking
                System.out.println(result);
            }

        } catch (Exception ex) {
            System.out.println("Exception client-side: ");
            ex.printStackTrace();
            myWait(1);
        }
    }

    private void showAllProblemsOfAStudent() {
        try {
            System.out.print("Enter a student id: ");
            Scanner sc = new Scanner(System.in);
            String studentId = sc.nextLine();
            if (!isLong(studentId)) {
                throw new InexistentEntityException("Invalid id!\n");
            }
            Future<String> assignments = helloService.showAllProblemsOfAStudent(studentId);

            String result = assignments.get(); //kind of blocking
            String[] args = result.split(";");
            for (String row : args) {
                System.out.println(row);
            }
        } catch (Exception ex) {
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

    private void handleResult(CompletableFuture<String> result){
        result.whenComplete((task,throwable)->{
        if (throwable!=null)
            System.err.println(throwable.getMessage());
            try{
                System.out.println(result.get());
            } catch (InterruptedException|ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

}
