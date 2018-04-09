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
    public CompletableFuture<String> addStudent(Long studentId,String serialNumber,String name) {
        return processCommand(studentId.toString() + ";" + serialNumber + ";" + name,ServiceInterface.ADD_STUDENT);
    }

    @Override
    public CompletableFuture<String> printAllStudents(String options) {
        return processCommand(options,ServiceInterface.PRINT_ALL_STUDENTS);
    }

    @Override
    public CompletableFuture<String> removeStudent(Long studentId) {
        return processCommand(studentId.toString(),ServiceInterface.REMOVE_STUDENT);
    }

    @Override
    public CompletableFuture<String> addProblem(Long problemId,Integer number,String text) {
        return processCommand(problemId+";"+number.toString()+";"+text,ServiceInterface.ADD_PROBLEM);

    }

    @Override
    public CompletableFuture<String> printAllProblems(String options) {
        return processCommand(options,ServiceInterface.PRINT_ALL_PROBLEMS);
    }

    @Override
    public CompletableFuture<String> removeProblem(Long problemId) {
        return processCommand(problemId.toString(),ServiceInterface.REMOVE_PROBLEM);
    }

    @Override
    public CompletableFuture<String> assignProblemToStudent(Long assignmentId, Long studentId, Long problemId) {
        return processCommand(assignmentId.toString()+";"+studentId.toString()+";"+problemId.toString(),ServiceInterface.ASSIGN_PROBLEM_TO_STUDENT);
    }

    @Override
    public CompletableFuture<String> showAllProblemsOfAStudent(Long studentId) {
        return processCommand(studentId.toString(),ServiceInterface.SHOW_ALL_PROBLEMS_OF_A_STUDENT);
    }

    @Override
    public CompletableFuture<String> showTheMostAssignedProblems(String paramsAndTypes) {
        return processCommand(paramsAndTypes,ServiceInterface.SHOW_THE_MOST_ASSIGNED_PROBLEMS);
    }

    @Override
    public CompletableFuture<String> showStudentsByNameMatch(String name) {
        return processCommand(name,ServiceInterface.SHOW_STUDENTS_BY_NAME_MATCH);
    }

}

