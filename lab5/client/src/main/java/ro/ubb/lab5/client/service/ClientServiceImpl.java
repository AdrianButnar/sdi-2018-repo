package ro.ubb.lab5.client.service;

import ro.ubb.lab5.client.tcp.TcpClient;
import ro.ubb.socket.common.Message;
import ro.ubb.socket.common.ServiceInterface;

import java.util.concurrent.CompletableFuture;
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

    public CompletableFuture<String> processCommand(String paramsAndTypes,String header){
        return CompletableFuture.supplyAsync(()->{
            Message request = Message.builder()
                    .header(header)
                    .body(paramsAndTypes)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        },executorService);
    }
    @Override
    public CompletableFuture<String> addStudent(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.ADD_STUDENT);
    }

    @Override
    public CompletableFuture<String> printAllStudents(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.PRINT_ALL_STUDENTS);
    }

    @Override
    public CompletableFuture<String> removeStudent(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.REMOVE_STUDENT);
    }

    @Override
    public CompletableFuture<String> addProblem(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.ADD_PROBLEM);

    }

    @Override
    public CompletableFuture<String> printAllProblems(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.PRINT_ALL_PROBLEMS);
    }

    @Override
    public CompletableFuture<String> removeProblem(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.REMOVE_PROBLEM);
    }

    @Override
    public CompletableFuture<String> assignProblemToStudent(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.ASSIGN_PROBLEM_TO_STUDENT);
    }

    @Override

    public CompletableFuture<String> showAllProblemsOfAStudent(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.SHOW_ALL_PROBLEMS_OF_A_STUDENT);
    }

    @Override
    public CompletableFuture<String> showTheMostAssignedProblems(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.SHOW_THE_MOST_ASSIGNED_PROBLEMS);
    }

    @Override
    public CompletableFuture<String> showStudentsByNameMatch(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.SHOW_STUDENTS_BY_NAME_MATCH);
    }

}

