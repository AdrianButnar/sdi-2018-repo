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
import ro.ubb.socket.server.repository.AssignmentDbRepository;
import ro.ubb.socket.server.repository.ProblemDbRepository;
import ro.ubb.socket.server.repository.Repository;
import ro.ubb.socket.server.repository.StudentDbRepository;
import ro.ubb.socket.server.service.AssignmentDbService;
import ro.ubb.socket.server.service.ProblemService;
import ro.ubb.socket.server.service.StudentService;
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
            return Message.builder()
                    .header(Message.ERROR)
                    .build();

        }
    }

    public static void main(String[] args) {

        Validator<Student> studentValidator = new StudentValidator();
        Validator<Problem> problemValidator = new ProblemValidator();
        Validator<Assignment> assignmentValidator = new AssignmentValidator();
        Repository<Long, Student> studentRepository = new StudentDbRepository(studentValidator, "jdbc:postgresql://localhost:5432/Mppdatabase");
        Repository<Long, Problem> problemRepository = new ProblemDbRepository(problemValidator, "jdbc:postgresql://localhost:5432/Mppdatabase");
        Repository<Long, Assignment> assignmentRepository = new AssignmentDbRepository(assignmentValidator, "jdbc:postgresql://localhost:5432/Mppdatabase");

        StudentService studentService = new StudentService(studentRepository);
        ProblemService problemService = new ProblemService(problemRepository);
        AssignmentDbService assignmentDbService = new AssignmentDbService(assignmentRepository);

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpServer tcpServer = new TcpServer(executorService, ServiceInterface.SERVER_HOST, ServiceInterface.SERVER_PORT);
        ServiceInterface serverService = new ServerServiceImpl(executorService,studentService,problemService,assignmentDbService);


        tcpServer.addHandler(ServiceInterface.ADD_STUDENT, (request) -> {
            String as[] = request.getBody().split(";");
            CompletableFuture<String> res = serverService.addStudent(Long.parseLong(as[0]),as[1],as[2]);
            return process(res);
        });

        tcpServer.addHandler(ServiceInterface.PRINT_ALL_STUDENTS, (request) -> {
            CompletableFuture<String> res = serverService.printAllStudents(request.getBody());
            return process(res);
        });

        tcpServer.addHandler(ServiceInterface.REMOVE_STUDENT, (request) -> {
            CompletableFuture<String> res = serverService.removeStudent(Long.parseLong(request.getBody()));
            return process(res);
        });

        tcpServer.addHandler(ServiceInterface.ADD_PROBLEM, (request) -> {
            String as[] = request.getBody().split(";");
            CompletableFuture<String> res = serverService.addProblem(Long.parseLong(as[0]),Integer.parseInt(as[1]),as[2]);
            return process(res);
        });

        tcpServer.addHandler(ServiceInterface.PRINT_ALL_PROBLEMS, (request) -> {
            CompletableFuture<String> res = serverService.printAllProblems(request.getBody());
            return process(res);
        });
        tcpServer.addHandler(ServiceInterface.REMOVE_PROBLEM, (request) -> {
            Future<String> res = serverService.removeProblem(Long.parseLong(request.getBody()));
            return process(res);
        });
        tcpServer.addHandler(ServiceInterface.ASSIGN_PROBLEM_TO_STUDENT, (request) -> {
            String[] as = request.getBody().split(";");
            Future<String> res = serverService.assignProblemToStudent(Long.parseLong(as[0]),Long.parseLong(as[1]),Long.parseLong(as[2]));
            return process(res);
        });
        tcpServer.addHandler(ServiceInterface.SHOW_ALL_PROBLEMS_OF_A_STUDENT, (request) -> {
            Future<String> res = serverService.showAllProblemsOfAStudent(Long.parseLong(request.getBody()));
            return process(res);
        });
        tcpServer.addHandler(ServiceInterface.SHOW_STUDENTS_BY_NAME_MATCH, (request) -> {
            Future<String> res = serverService.showStudentsByNameMatch(request.getBody());
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
