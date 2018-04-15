package ro.ubb.lab6.server.service;

import ro.ubb.lab6.common.domain.Student;
import ro.ubb.lab6.common.services.StudentService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class StudentServiceImpl implements StudentService{

    private ExecutorService executorService;
    private StudentService studentService;
    private ProblemService problemService;
    private AssignmentDbService assignmentDbService;

//    public ServerServiceImpl(ExecutorService executorService, StudentService studentService, ProblemService problemService, AssignmentDbService assignmentDbService, Validator<Student> studentValidator, Validator<Assignment> assignmentValidator, Validator<Problem> problemValidator) {
//        this.executorService = executorService;
//    }

    public ServerServiceImpl(ExecutorService executorService, StudentService studentService, ProblemService problemService, AssignmentDbService assignmentDbService) {
        this.executorService = executorService;
        this.studentService = studentService;
        this.problemService = problemService;
        this.assignmentDbService = assignmentDbService;
    }

    @Override
    public CompletableFuture<String> addStudent(Long studentId, String serialNumber, String name) {
        // String[] args=paramsAndTypes.split(";");
        try{
            Student s= new Student(serialNumber,name);
            s.setId(studentId);
            studentService.addStudent(s);
            return CompletableFuture.supplyAsync(() -> "Student was added successfully! ",executorService);
        }
        catch (Exception ex){
            return CompletableFuture.supplyAsync(() -> "Student data was invalid! ",executorService);
        }
    }

    @Override
    public CompletableFuture<String> printAllStudents(String options) {
        StringBuilder sb = new StringBuilder();
        for (Student s: studentService.getAllStudents()){
            sb.append(s.toString());
            sb.append(";");
        }
        final String finalOut = sb.toString();
        System.out.println(sb.toString());
        return CompletableFuture.supplyAsync(() -> finalOut,executorService);
    }

    @Override
    public CompletableFuture<String> removeStudent(Long studentId) {
        try{
            studentService.removeStudent(studentId);
            return CompletableFuture.supplyAsync(() -> "Student was removed! ",executorService);
        }
        catch (Exception ex){
            return CompletableFuture.supplyAsync(() -> "Student id is invalid! ",executorService);
        }
    }

    @Override
    public CompletableFuture<String> addProblem(Long problemId,Integer number,String text) {
        //String[] args=paramsAndTypes.split(";");
        try{
            Problem pb= new Problem(number, text);
            pb.setId(problemId);
            problemService.addProblem(pb);
            return CompletableFuture.supplyAsync(() -> "Problem was added successfully! ",executorService);
        }
        catch (Exception ex){
            return CompletableFuture.supplyAsync(() -> "Problem data was invalid!",executorService);
        }
    }

    @Override
    public CompletableFuture<String> printAllProblems(String options) {
        StringBuilder sb = new StringBuilder();
        for (Problem s: problemService.getAllProblems()){
            sb.append(s.toString());
            sb.append(";");
        }
        final String finalOut = sb.toString();
        System.out.println(sb.toString());
        return CompletableFuture.supplyAsync(() -> finalOut,executorService);
    }

    @Override
    public CompletableFuture<String> removeProblem(Long problemId) {
        try{
            problemService.removeProblem(problemId);
            return CompletableFuture.supplyAsync(() -> "Problem removed successfully! ",executorService);
        }
        catch (Exception ex){
            return CompletableFuture.supplyAsync(() -> "Problem id is invalid!",executorService);
        }
    }


    @Override
    public CompletableFuture<String> assignProblemToStudent(Long assignmentId, Long studentId, Long problemId) {
        //String[] args = paramsAndTypes.split(";");
        try{
            Assignment as = new Assignment(studentId, problemId);
            as.setId(assignmentId);
            assignmentDbService.addAssignment(as);
            return CompletableFuture.supplyAsync(() -> "Assignment was added successfully! ",executorService);
        }
        catch (Exception ex){
            return CompletableFuture.supplyAsync(()->"Assignment data was invalid!",executorService);
        }
    }


    @Override
    public CompletableFuture<String> showAllProblemsOfAStudent(Long studentId) {
        StringBuilder sb = new StringBuilder();
        //String[] args = paramsAndTypes.split(";");
        for (Assignment as: assignmentDbService.getAllAssignments()){
            if (as.getStudentID() == studentId) {
                sb.append(as.toString());
                sb.append(";");
            }

        }
        final String finalOut = sb.toString();
        System.out.println(sb.toString());
        return CompletableFuture.supplyAsync(()->finalOut,executorService);
    }

    @Override
    public CompletableFuture<String> showStudentsByNameMatch(String name){
        StringBuilder sb = new StringBuilder();
        for (Student s: studentService.getAllStudents()){
            if(s.getName().toLowerCase().contains(name)) {
                sb.append(s.toString());
                sb.append(";");
            }
        }
        final String finalOut = sb.toString();
        return CompletableFuture.supplyAsync(() -> finalOut,executorService);
    }

    @Override
    public CompletableFuture<String> showTheMostAssignedProblems(String paramsAndTypes) {
        throw new NotImplementedException();
    }
}
