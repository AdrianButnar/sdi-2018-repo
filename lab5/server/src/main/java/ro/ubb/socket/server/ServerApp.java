package ro.ubb.socket.server;

import ro.ubb.socket.common.Message;
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
import ro.ubb.socket.server.service.ServerServiceImpl;
import ro.ubb.socket.server.tcp.TcpServer;

import java.util.concurrent.*;

public class ServerApp {

    private static Message process(Future<String> res){
        try {
            String result = res.get();
            return Message.builder()
                    .header(Message.OK)
                    .body(result)
                    .build();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            //return new Message(Message.ERROR, "");
            return Message.builder()
                    .header(Message.ERROR)
                    .build();

        }
    }

    public static void main(String[] args) {

        //here we put data from service and repo from lab4
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Problem> problemValidator = new ProblemValidator();
        Validator<Assignment> assignmentValidator = new AssignmentValidator();
        Repository<Long, Student> studentRepository = new StudentDbRepository(studentValidator, "jdbc:postgresql://localhost:5432/Mppdatabase");
        Repository<Long, Problem> problemRepository = new ProblemDbRepository(problemValidator, "jdbc:postgresql://localhost:5432/Mppdatabase");
        Repository<Long, Assignment> assignmentRepository = new AssignmentDbRepository(assignmentValidator, "jdbc:postgresql://localhost:5432/Mppdatabase");

        StudentService studentService = new StudentService(studentRepository);
        ProblemService problemService = new ProblemService(problemRepository);
        AssignmentDbService assignmentDbService = new AssignmentDbService(assignmentRepository);
        //

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpServer tcpServer = new TcpServer(executorService, ServiceInterface.SERVER_HOST, ServiceInterface.SERVER_PORT);
        ServiceInterface serverService = new ServerServiceImpl(executorService,studentService,problemService,assignmentDbService);


        tcpServer.addHandler(ServiceInterface.ADD_STUDENT, (request) -> {
            CompletableFuture<String> res = serverService.addStudent(request.getBody());
            return process(res);
        });

        tcpServer.addHandler(ServiceInterface.PRINT_ALL_STUDENTS, (request) -> {
            CompletableFuture<String> res = serverService.printAllStudents(request.getBody());
            return process(res);
        });

        tcpServer.addHandler(ServiceInterface.REMOVE_STUDENT, (request) -> {
            CompletableFuture<String> res = serverService.removeStudent(request.getBody());
            return process(res);
        });

        tcpServer.addHandler(ServiceInterface.ADD_PROBLEM, (request) -> {
            CompletableFuture<String> res = serverService.addProblem(request.getBody());
            return process(res);
        });

        tcpServer.addHandler(ServiceInterface.PRINT_ALL_PROBLEMS, (request) -> {
            CompletableFuture<String> res = serverService.printAllProblems(request.getBody());
            return process(res);
        });
        tcpServer.addHandler(ServiceInterface.REMOVE_PROBLEM, (request) -> {
            Future<String> res = serverService.removeProblem(request.getBody());
            return process(res);
        });
        tcpServer.addHandler(ServiceInterface.ASSIGN_PROBLEM_TO_STUDENT, (request) -> {
            Future<String> res = serverService.assignProblemToStudent(request.getBody());
            return process(res);
        });
        tcpServer.addHandler(ServiceInterface.SHOW_ALL_PROBLEMS_OF_A_STUDENT, (request) -> {
            Future<String> res = serverService.showAllProblemsOfAStudent(request.getBody());
            return process(res);
        });
        tcpServer.addHandler(ServiceInterface.SHOW_THE_MOST_ASSIGNED_PROBLEMS, (request) -> {
            Future<String> res = serverService.showTheMostAssignedProblems(request.getBody());
            return process(res);
        });
        tcpServer.startServer();

        System.out.println("bye - server");
    }
}
