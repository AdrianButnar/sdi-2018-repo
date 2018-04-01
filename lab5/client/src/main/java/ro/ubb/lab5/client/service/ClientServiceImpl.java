package ro.ubb.lab5.client.service;

import ro.ubb.lab5.client.tcp.TcpClient;
import ro.ubb.socket.common.Message;
import ro.ubb.socket.common.ServiceInterface;
import ro.ubb.socket.common.service.StudentService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ClientServiceImpl implements ServiceInterface {
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public ClientServiceImpl(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

//    @Override
//    public Future<String> sayHello(String name) {
//        return executorService.submit(() -> {
////            Message request = new Message(HelloService.SAY_HELLO, name);
//            Message request = Message.builder()
//                    .header(HelloService.SAY_HELLO)
//                    .body(name)
//                    .build();
//            Message response = tcpClient.sendAndReceive(request);
//            //!!!! err handling
//            return response.getBody();
//        });
//
//    }

    public Future<String> processCommand(String paramsAndTypes,String header){
        return executorService.submit(()->{
            Message request = Message.builder()
                    .header(header)
                    .body(paramsAndTypes)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }
    @Override
    public Future<String> addStudent(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.ADD_STUDENT);
    }

    @Override
    public Future<String> printAllStudents(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.PRINT_ALL_STUDENTS);
    }

    @Override
    public Future<String> removeStudent(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.REMOVE_STUDENT);
    }

    @Override
    public Future<String> addProblem(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.ADD_PROBLEM);

    }

    @Override
    public Future<String> printAllProblems(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.PRINT_ALL_PROBLEMS);
    }

    @Override
    public Future<String> removeProblem(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.REMOVE_PROBLEM);
    }

    @Override
    public Future<String> assignProblemToStudent(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.ASSIGN_PROBLEM_TO_STUDENT);
    }

    @Override
    public Future<String> showAllProblemsOfAStudent(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.SHOW_ALL_PROBLEMS_OF_A_STUDENT);
    }

    @Override
    public Future<String> showTheMostAssignedProblems(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.SHOW_THE_MOST_ASSIGNED_PROBLEMS);
    }
    @Override
    public Future<String> showStudentsByNameMatch(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.SHOW_STUDENTS_BY_NAME_MATCH);
    }

}

