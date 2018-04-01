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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerApp {
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
        //ServiceInterface serverService = new ServerServiceImpl(executorService);
        ServiceInterface serverService = new ServerServiceImpl(executorService,studentRepository,problemRepository,assignmentRepository);

        tcpServer.addHandler(ServiceInterface.ADD_STUDENT, (request) -> {
            Future<String> res = serverService.addStudent(request.getBody());
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
        });

        tcpServer.startServer();

        System.out.println("bye - server");
    }
}
