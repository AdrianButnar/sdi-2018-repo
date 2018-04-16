package ro.ubb.lab6.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.lab6.common.ServiceInterface;
import ro.ubb.lab6.common.domain.Problem;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ClientService implements ServiceInterface {



    @Autowired
    private ServiceInterface service;
//
//    @Override
//    public List<Student> findAll() {
//        return service.findAll(); //the implementation?
//    }

    @Override
    public void addStudent(Long studentId, String serialNumber, String name) {
        service.addStudent(studentId, serialNumber, name);

    }

    @Override
    public CompletableFuture<String> printAllStudents(String options) {
        return null;
//        return processCommand(options,ServiceInterface.PRINT_ALL_STUDENTS);
    }

    @Override
    public CompletableFuture<String> removeStudent(Long studentId) {
        return null;
    }

    @Override
    public CompletableFuture<String> addProblem(Long problemId, Integer number, String text) {
        return null;
    }

    @Override
    public List<Problem> printAllProblems(String options) {
        return service.printAllProblems("");
    }

    @Override
    public CompletableFuture<String> removeProblem(Long problemId) {
        return null;
    }

    @Override
    public CompletableFuture<String> assignProblemToStudent(Long assignmentId, Long studentId, Long problemId) {
        return null;
    }

    @Override
    public CompletableFuture<String> showAllProblemsOfAStudent(Long studentId) {
        return null;
    }

    @Override
    public CompletableFuture<String> showTheMostAssignedProblems(String paramsAndTypes) {
        return null;
    }

    @Override
    public CompletableFuture<String> showStudentsByNameMatch(String name) {
        return null;
    }
}
