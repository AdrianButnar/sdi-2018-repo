package ro.ubb.socket.server.service;

import ro.ubb.socket.common.ServiceInterface;
import ro.ubb.socket.common.domain.Assignment;
import ro.ubb.socket.common.domain.Problem;
import ro.ubb.socket.common.domain.Student;
import ro.ubb.socket.common.domain.validators.AssignmentValidator;
import ro.ubb.socket.common.domain.validators.ProblemValidator;
import ro.ubb.socket.common.domain.validators.StudentValidator;
import ro.ubb.socket.common.domain.validators.Validator;
import ro.ubb.socket.common.repository.AssignmentDbRepository;
import ro.ubb.socket.common.repository.ProblemDbRepository;
import ro.ubb.socket.common.repository.Repository;
import ro.ubb.socket.common.repository.StudentDbRepository;
import ro.ubb.socket.common.service.AssignmentDbService;
import ro.ubb.socket.common.service.ProblemService;
import ro.ubb.socket.common.service.StudentService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServerServiceImpl implements ServiceInterface {
    private ExecutorService executorService;
    private StudentService studentService;
    private ProblemService problemService;
    private AssignmentDbService assignmentDbService;

    public ServerServiceImpl(ExecutorService executorService, StudentService studentService, ProblemService problemService, AssignmentDbService assignmentDbService, Validator<Student> studentValidator, Validator<Assignment> assignmentValidator, Validator<Problem> problemValidator) {
        this.executorService = executorService;
    }

    public ServerServiceImpl(ExecutorService executorService, StudentService studentService, ProblemService problemService, AssignmentDbService assignmentDbService) {
        this.executorService = executorService;
        this.studentService = studentService;
        this.problemService = problemService;
        this.assignmentDbService = assignmentDbService;
    }

    @Override
    public CompletableFuture<String> addStudent(String paramsAndTypes) {
        String[] args=paramsAndTypes.split(";");
        try{
            Student s= new Student(args[1],args[2]);
            s.setId(Long.parseLong(args[0]));
            //((StudentDbRepository)srepo).getValidator().validate(s);
            studentService.addStudent(s);
            return CompletableFuture.supplyAsync(() -> "Student was added successfully! ",executorService);
        }
        catch (Exception ex){
            return CompletableFuture.supplyAsync(() -> "Student data was invalid! ",executorService);
        }
    }

    @Override
    public CompletableFuture<String> printAllStudents(String paramsAndTypes) {
        String out ="";
        StringBuilder sb = new StringBuilder();
        for (Student s: studentService.getAllStudents()){
            sb.append(s.toString());
            sb.append("\n");
           // out = out + s.toString()+" \n";
        }
        final String finalOut = sb.toString();
        System.out.println(sb.toString());
        return CompletableFuture.supplyAsync(() -> finalOut,executorService);
    }

    @Override
    public CompletableFuture<String> removeStudent(String paramsAndTypes) {
        try{
            studentService.removeStudent(Long.parseLong(paramsAndTypes));
            return CompletableFuture.supplyAsync(() -> "Student was removed! ",executorService);
        }
        catch (Exception ex){
            return CompletableFuture.supplyAsync(() -> "Student id is invalid! ",executorService);
        }
    }

    @Override
    public CompletableFuture<String> addProblem(String paramsAndTypes) {
        String[] args=paramsAndTypes.split(";");
        try{
            Problem pb= new Problem(Integer.parseInt(args[1]),args[2]);
            pb.setId(Long.parseLong(args[0]));
            problemService.addProblem(pb);
            return CompletableFuture.supplyAsync(() -> "Problem was added successfully! ",executorService);
        }
        catch (Exception ex){
            return CompletableFuture.supplyAsync(() -> "Problem data was invalid!",executorService);
        }
    }

    @Override
    public CompletableFuture<String> printAllProblems(String paramsAndTypes) {
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
    public CompletableFuture<String> removeProblem(String paramsAndTypes) {
        try{
            problemService.removeProblem(Long.parseLong(paramsAndTypes));
            return CompletableFuture.supplyAsync(() -> "Problem removed successfully! ",executorService);
        }
        catch (Exception ex){
            return CompletableFuture.supplyAsync(() -> "Problem id is invalid!",executorService);
        }
    }

    @Override
    public CompletableFuture<String> assignProblemToStudent(String paramsAndTypes) {
        return null;
    }

    @Override
    public CompletableFuture<String> showAllProblemsOfAStudent(String paramsAndTypes) {
        return null;
    }

    @Override
    public CompletableFuture<String> showTheMostAssignedProblems(String paramsAndTypes) {
        return null;
    }
}
