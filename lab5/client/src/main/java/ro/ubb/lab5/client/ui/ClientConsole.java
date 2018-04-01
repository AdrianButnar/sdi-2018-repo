package ro.ubb.lab5.client.ui;

import ro.ubb.socket.common.ServiceInterface;
import ro.ubb.socket.common.domain.Student;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

            Future<String> res;
            switch (command) {
                case "1":
                    res = helloService.addStudent(name);
                    addStudent();
                    continue;
                case "2":
                    res = helloService.printAllStudents(name);
                    continue;
                case "3":
                    res = helloService.removeStudent();
                    continue;
                case "4":
                    res = helloService.addProblem();
                    continue;
                    /*
                case "5":
                    res = helloService.addStudent(name);
                    printAllProblems();
                    continue;
                case "6":
                    res = helloService.addStudent(name);
                    removeProblem();
                    continue;
                case "7":
                    res = helloService.addStudent(name);
                    assignProblemToStudent();
                    continue;
                case "8":
                    res = helloService.addStudent(name);
                    showAllProblemsOfAStudent();
                    continue;
                case "9":
                    res = helloService.addStudent(name);
                    showStudentsByNameMatch();
                    continue;
                case "10":
                    res = helloService.addStudent(name);
                    showTheMostAssignedProblems();
                    continue;
                    */
                case "0":
                    System.exit(0);
                default:
                    res = null;
                    break;
            }
            try {
                System.out.println(res.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }


        }

    }

    /**
     * Reads a new student from the standard input
     * @return The read student if the data was filled correctly or null otherwise
     */
    private Student readStudent() {
        System.out.println("Enter params: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
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

            return student;
        }
        catch (IllegalIdException|ValidatorException ex) {
            ex.printStackTrace();
            myWait(1);

        }


        return null;
    }
    private void addStudent() {
        try {
            Student student = readStudent();
            if(student==null)
                return;
            studentService.addStudent(student);
        }
        catch (ValidatorException se){
            se.printStackTrace();
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
