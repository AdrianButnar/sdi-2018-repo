package ro.ubb.laboratory.ui;

import ro.ubb.laboratory.domain.Assignment;
import ro.ubb.laboratory.domain.Problem;
import ro.ubb.laboratory.domain.Student;
import ro.ubb.laboratory.domain.validators.*;
import ro.ubb.laboratory.repository.AssignmentDbRepository;
import ro.ubb.laboratory.service.AssignmentDbService;
import ro.ubb.laboratory.service.ProblemService;
import ro.ubb.laboratory.service.StudentService;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Adrian Butnar
 * @version 1.0.0
 */

public class Console {
    private StudentService studentService;
    private ProblemService problemService;
    private AssignmentDbService assignmentService;
    private int flag;

    public Console(StudentService studentService, ProblemService problemService) {
        this.studentService = studentService;
        this.problemService = problemService;
        flag = 2; //if there is not a database Repo
    }

    public Console (StudentService studentService, ProblemService problemService, AssignmentDbService assignmentService) {
        this.studentService = studentService;
        this.problemService = problemService;
        this.assignmentService = assignmentService;
        flag = 1; //with database Repo
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
                    addStudent();
                    continue;
                case "2":
                    printAllStudents();
                    continue;
                case "3":
                    removeStudent();
                    continue;
                case "4":
                    addProblem();
                    continue;
                case "5":
                    printAllProblems();
                    continue;
                case "6":
                    removeProblem();
                    continue;
                case "7":
                    assignProblemToStudent();
                    continue;
                case "8":
                    showAllProblemsOfAStudent();
                    continue;
                case "9":
                    showStudentsByNameMatch();
                    continue;
                case "10":
                    showTheMostAssignedProblems();
                    continue;
                case "0":
                    System.exit(0);
            }
        }
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

    /**
     * Prints to the standard output all the students in the repository
     */
    private void printAllStudents() {
        Set<Student> students = studentService.getAllStudents();
        students.stream().forEach(System.out::println);
    }

    private void removeStudent(){
        try {
            System.out.print("Enter id: ");
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            if (!isLong(id)){
                throw new InexistentEntityException("Invalid id!\n");
            }
            studentService.removeStudent(Long.parseLong(id));
            System.out.println("Student successfully removed!");
        }
        catch (InexistentEntityException se){
            se.printStackTrace();
            myWait(1);
        }
    }

    private void addProblem() {
        try {
            Problem problem = readProblem();
            if(problem == null)
                return;
            problemService.addProblem(problem);
        }
        catch ( ValidatorException ex){
            ex.printStackTrace();
            myWait(1);

        }
    }


    /**
     * Prints to the standard output all the problems in the repository
     */
    private void printAllProblems() {
        Set<Problem> problems = problemService.getAllProblems();
        problems.stream().forEach(System.out::println);
    }


    private void removeProblem(){
        try {
            System.out.print("Enter id: ");
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            if (!isLong(id)){
                throw new InexistentEntityException("Invalid id!\n");
            }
            problemService.removeProblem(Long.parseLong(id));
            System.out.println("Problem successfully removed!");

        }
        catch (InexistentEntityException ex){
            ex.printStackTrace();
            myWait(1);
        }
    }


    private void assignProblemToStudent(){
        try {
            //if (this.getClass().getConstructor().getParameterCount()!= 3) {
            if (flag == 2)
            {
                System.out.print("Enter a student id: ");
                Scanner sc = new Scanner(System.in);
                String studentId = sc.nextLine();

                System.out.print("Enter a problem id: ");
                String problemId = sc.nextLine();
                if (!isLong(studentId) || !isLong(problemId)) {
                    throw new InexistentEntityException("Invalid id!\n");
                }
                Set<Student> students = this.studentService.getAllStudents();
                Set<Problem> problems = this.problemService.getAllProblems();
                boolean problemExists = false;
                for (Problem pb : problems) {
                    if (pb.getId() == Integer.parseInt(problemId)) {
                        problemExists = true;
                    }
                }
                if (problemExists) {
                    boolean studentExists = false;
                    for (Student s : students) {
                        if (s.getId() == Integer.parseInt(studentId)) {
                            s.addProblem(Integer.parseInt(problemId));
                            studentExists = true;
                        }
                    }
                    if (studentExists) {
                        System.out.println("Problem " + problemId + " added to student " + studentId + ".");
                    } else {
                        throw new InexistentStudentException("Student with given id does not exist!");
                    }
                } else
                    throw new InexistentProblemException("Problem with given id does not exist!");

            } else {

                System.out.print("Enter an assignment id: ");
                Scanner sc = new Scanner(System.in);
                String assignmentId = sc.nextLine();

                System.out.print("Enter a student id: ");
                String studentId = sc.nextLine();

                System.out.print("Enter a problem id: ");
                String problemId = sc.nextLine();

                Assignment as = new Assignment(Long.parseLong(studentId), Long.parseLong(problemId));
                as.setId(Long.parseLong(assignmentId));
                assignmentService.addAssignment(as);
            }
        }
        catch (InexistentEntityException | InexistentStudentException | InexistentProblemException | EntityPresentException ex) {
            ex.printStackTrace();
            myWait(1);
        }
    }

    private void showAllProblemsOfAStudent() {
        if(flag == 2) {
            try {
                System.out.print("Enter a student id: ");
                Scanner sc = new Scanner(System.in);
                String studentId = sc.nextLine();
                if (!isLong(studentId)) {
                    throw new InexistentEntityException("Invalid id!\n");
                }
                Set<Student> students = this.studentService.getAllStudents();
                boolean studentExists = false;
                for (Student s : students) {
                    if (s.getId() == Integer.parseInt(studentId)) {
                        if (s.getProblemList().size()!=0){
                            System.out.println("Student " + s.getId() + " has the following problems assigned: " + s.getProblemListToString());
                        }
                        else{
                            System.out.println("This student has no assigned problems!");
                        }
                        studentExists = true;
                    }
                }
                if (!studentExists){
                    throw new InexistentEntityException("This student does not exist in the list!");
                }

            } catch (InexistentEntityException ex) {
                ex.printStackTrace();
                myWait(1);
            }
        }
        else
        {
            System.out.print("Enter a student id: ");
            Scanner sc = new Scanner(System.in);
            String studentId = sc.nextLine();
            if (!isLong(studentId)) {
                throw new InexistentEntityException("Invalid id!\n");
            }
            Set<Assignment> assignments = this.assignmentService.getAllAssignments();
            for (Assignment a : assignments) {
                if (a.getStudentID() == Long.parseLong(studentId)) {
                    System.out.println("Student " + a.getStudentID() + " has the following problems assigned: " + a.getProblemID());

                }
            }
            System.out.println("Done");
        }
    }


    private void showStudentsByNameMatch() {

        try{
            System.out.print("Enter a name: ");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            Set<Student> students = studentService.getAllStudents();
            boolean found=false;
            for (Student s:students){
                if (s.getName().toLowerCase().contains(name)){
                    System.out.println(s);
                    found = true;
                }
            }
            if(!found){
                throw new InexistentEntityException("No student matches this name!");
            }

        }
        catch (InexistentEntityException e){
            e.printStackTrace();
            myWait(1);
        }
    }

    private void showTheMostAssignedProblems() {
        if(flag == 2) {
            List<Integer> allProblems = new ArrayList<>();
            for (Student s :studentService.getAllStudents()){
                allProblems.addAll(s.getProblemList());
            }
            Map<Object, Long> times = allProblems.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
            Comparator<Object> comparator = new Comparator<Object>() { //in my case not, so warning dismissed! *drop the mic*
                @Override
                public int compare(Object key1, Object key2) {
                    int returned = times.get(key2).intValue()-times.get(key1).intValue();

                    if (returned == 0 && !key1.equals(key2))
                        returned = -1;

                    return returned;

                }
            };
            TreeMap<Object,Long> tm = new TreeMap<Object,Long>(comparator);
            tm.putAll(times);
            for (Object item:tm.keySet()){
                System.out.println("Problem "+ item + " was assigned " + times.get(item) + " time/s.");
            }
        }
        else
        {
            Set<Assignment> assignments = this.assignmentService.getAllAssignments();
            HashMap<Long, Integer> elementCountMap = new HashMap<>();
            for(Assignment as : assignments)
            {
                if (elementCountMap.containsKey(as.getProblemID()))
                {
                    elementCountMap.put(as.getProblemID(), elementCountMap.get(as.getProblemID())+1);
                }
                else
                {
                    elementCountMap.put(as.getProblemID(), 1);
                }
            }
            Set<Map.Entry<Long, Integer>> entrySet = elementCountMap.entrySet();
            long element = 0;
            int frequency = 1;

            for (Map.Entry<Long, Integer> entry : entrySet)
            {
                if(entry.getValue() > frequency)
                {
                    element = entry.getKey();
                    frequency = entry.getValue();
                }
            }
            System.out.println("Most assigned problem is " + element + ".");
            System.out.println("Database");
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

    private static boolean isLong(String id){
        try{
            Long id1 = Long.parseLong(id);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    private static boolean isInteger(String id){
        try{
            Integer id1 = Integer.parseInt(id);
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

            if (!isInteger(number)) {
                throw new IllegalIdException("Invalid number!\n");
            }

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
