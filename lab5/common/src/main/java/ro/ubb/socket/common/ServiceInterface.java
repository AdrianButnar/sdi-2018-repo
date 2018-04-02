package ro.ubb.socket.common;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface ServiceInterface {
    String SERVER_HOST = "localhost";
    int SERVER_PORT = 1234;

    String ADD_STUDENT="addStudent";
    String PRINT_ALL_STUDENTS="printAllStudents";
    String REMOVE_STUDENT = "removeStudent";
    String ADD_PROBLEM = "addProblem";
    String PRINT_ALL_PROBLEMS = "printAllProblems";
    String REMOVE_PROBLEM="removeProblem";
    String ASSIGN_PROBLEM_TO_STUDENT = "assignProblemToStudent";
    String SHOW_ALL_PROBLEMS_OF_A_STUDENT = "showAllProblemsOfAStudent";
    String SHOW_THE_MOST_ASSIGNED_PROBLEMS = "showTheMostAssignedProblems";

    CompletableFuture<String> addStudent(String paramsAndTypes);
    CompletableFuture<String> printAllStudents(String paramsAndTypes);
    CompletableFuture<String> removeStudent(String paramsAndTypes);
    CompletableFuture<String> addProblem(String paramsAndTypes);
    CompletableFuture<String> printAllProblems(String paramsAndTypes);
    CompletableFuture<String> removeProblem(String paramsAndTypes);
    CompletableFuture<String> assignProblemToStudent(String paramsAndTypes);
    CompletableFuture<String> showAllProblemsOfAStudent(String paramsAndTypes);
    CompletableFuture<String> showTheMostAssignedProblems(String paramsAndTypes);

}

