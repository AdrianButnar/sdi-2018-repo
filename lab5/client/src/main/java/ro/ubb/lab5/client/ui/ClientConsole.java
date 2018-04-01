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

            Future<String> res = null;
            switch (command) {
                case "1":
//                    res = addStudent();
                    addStudent();
                    break;

                case "2":
//                    res = printAllStudents();
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
                    /*
                case "9":
                    res = helloService.addStudent(name);
                    showAllProblemsOfAStudent();
                    continue;
                case "10":
                    res = helloService.addStudent(name);
                    showStudentsByNameMatch();
                    continue;
                case "11":
                    res = helloService.addStudent(name);
                    showTheMostAssignedProblems();
                    continue;
                    */
                case "0":
                    System.exit(0);
                    res = null;
                    break;
                default:
                    res = null;
                    break;

            }
//            try {
//                //if(res.isDone())
//                System.out.println(res.get());
//            } catch (InterruptedException | ExecutionException | NullPointerException e) {
//                e.printStackTrace();
//            }

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
                Future<String> s = helloService.addStudent(received);
                if(s.isDone())
                {
                    String result = s.get();
                    System.out.println(result);
                }
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
//        throw new NotImplementedException(); //I don't understand futures
    }

    /**
     * Prints to the standard output all the students in the repository
     */
//    private  Future<String> printAllStudents() {
    private void printAllStudents() {
        Future<String> students = helloService.printAllStudents("");
       // System.out.println(students);
        try {
            while (!students.isDone()) { //if- doesn't really work //also kind of blocking
//            if (students.isDone()){
                String result = students.get(); //kind of blocking
                String[] args = result.split(";");
                for (String row : args) {
                    System.out.println(row);
                }
            }
//           return students;
        } catch (CancellationException | ExecutionException | InterruptedException ex)
        {
            System.out.println("Error serverside(or canceled):");
            ex.printStackTrace();
        }

//        throw new NotImplementedException();
    }

    private void removeStudent(){
        try {
            System.out.print("Enter id: ");
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            if (!isLong(id)){
                throw new InexistentEntityException("Invalid id!\n");
            }
            Future<String> s = helloService.removeStudent(id);
//            if(s.isDone())
            {
                String result = s.get(); //blocking also
                System.out.println(result);
            }
        }
        catch (InexistentEntityException|InterruptedException|ExecutionException se){
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
                Future<String> s = helloService.addProblem(received);
//                if(s.isDone())
                {
                    String result = s.get(); //blocking
                    System.out.println(result);
                }
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
            Future<String> s = helloService.removeProblem(id);
//            if(s.isDone())
            {
                String result = s.get(); //blocking
                System.out.println(result);
            }
        }
        catch (InexistentEntityException|InterruptedException|ExecutionException|NullPointerException ex){
            System.out.println("Exception client-side: ");
            ex.printStackTrace();
            myWait(1);
        }
    }
    private void printAllProblems() {
        Future<String> students = helloService.printAllProblems("");
        // System.out.println(students);
        try {
            while (!students.isDone()) { //if- doesn't really work //also kind of blocking
//            if (students.isDone()){
                String result = students.get(); //kind of blocking
                String[] args = result.split(";");
                for (String row : args) {
                    System.out.println(row);
                }
            }
//           return students;
        } catch (CancellationException | ExecutionException | InterruptedException ex)
        {
            System.out.println("Error serverside(or canceled):");
            ex.printStackTrace();
        }

//        throw new NotImplementedException();
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

//            if (!problems.equals("")){
//                System.out.println("Student " + studentId + " has the following problems assigned: " + problems);
//            }
//            else
//                throw new InexistentEntityException("This student has no assigned problems!");
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


}
