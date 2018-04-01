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
    public Future<String> addStudent(String paramsAndTypes) {
        String[] args=paramsAndTypes.split(";");
        try{
            Student s= new Student(args[1],args[2]);
            s.setId(Long.parseLong(args[0]));
            //((StudentDbRepository)srepo).getValidator().validate(s);
            studentService.addStudent(s);
            return executorService.submit(() -> "Student was added successfully! ");
        }
        catch (Exception ex){
            return executorService.submit(()->"Student data was invalid!");
        }
    }

    @Override
    public Future<String> printAllStudents(String paramsAndTypes) {
        String out ="";
        StringBuilder sb = new StringBuilder();
        for (Student s: studentService.getAllStudents()){
            sb.append(s.toString());
            sb.append(";");
           // out = out + s.toString()+" \n";
        }
        final String finalOut = sb.toString();
        System.out.println(sb.toString());
        return executorService.submit(()->finalOut);
    }

    @Override
    public Future<String> removeStudent(String paramsAndTypes) {
        try{
            studentService.removeStudent(Long.parseLong(paramsAndTypes));
            return executorService.submit(() -> "Student was removed! ");
        }
        catch (Exception ex){
            return executorService.submit(()->"Student id was invalid!");
        }
    }

    @Override
    public Future<String> addProblem(String paramsAndTypes) {
        String[] args=paramsAndTypes.split(";");
        try{
            Problem pb= new Problem(Integer.parseInt(args[1]),args[2]);
            pb.setId(Long.parseLong(args[0]));
            problemService.addProblem(pb);
            return executorService.submit(() -> "Problem was added successfully! ");
        }
        catch (Exception ex){
            return executorService.submit(()->"Problem data was invalid!");
        }
    }

    @Override
    public Future<String> printAllProblems(String paramsAndTypes) {
        String out ="";
        for (Problem pb: problemService.getAllProblems()){
            out = out +  pb.toString()+" \n";
        }
        final String finalOut = out;
        return executorService.submit(()->finalOut);
    }

    @Override
    public Future<String> removeProblem(String paramsAndTypes) {
        try{
            problemService.removeProblem(Long.parseLong(paramsAndTypes));
            return executorService.submit(() -> "Problem removed successfully! ");
        }
        catch (Exception ex){
            return executorService.submit(()->"Problem id was invalid!");
        }
    }

    @Override
    public Future<String> assignProblemToStudent(String paramsAndTypes) {
        return null;
    }

    @Override
    public Future<String> showAllProblemsOfAStudent(String paramsAndTypes) {
        return null;
    }

    @Override
    public Future<String> showTheMostAssignedProblems(String paramsAndTypes) {
        return null;
    }
}
