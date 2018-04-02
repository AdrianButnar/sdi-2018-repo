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
    String SHOW_STUDENTS_BY_NAME_MATCH = "showStudentsByNameMatch";

    CompletableFuture<String> addStudent(Long studentId,String serialNumber,String name);
    CompletableFuture<String> printAllStudents(String paramsAndTypes);
    CompletableFuture<String> removeStudent(Long studentId);
    CompletableFuture<String> addProblem(Long problemID,Integer number,String text);
    CompletableFuture<String> printAllProblems(String paramsAndTypes);
    CompletableFuture<String> removeProblem(Long problemId);
    CompletableFuture<String> assignProblemToStudent(Long assignmentId, Long studentId, Long problemId);
    CompletableFuture<String> showAllProblemsOfAStudent(Long studentId);
    CompletableFuture<String> showTheMostAssignedProblems(String paramsAndTypes);
    CompletableFuture<String> showStudentsByNameMatch(String name);

//    Future<String> addStudent(String paramsAndTypes);
//    Future<String> printAllStudents(String paramsAndTypes);
//    Future<String> removeStudent(String paramsAndTypes);
//    Future<String> addProblem(String paramsAndTypes);
//    Future<String> printAllProblems(String paramsAndTypes);
//    Future<String> removeProblem(String paramsAndTypes);
//    Future<String> assignProblemToStudent(String paramsAndTypes);
//    Future<String> showAllProblemsOfAStudent(String paramsAndTypes);
//    Future<String> showTheMostAssignedProblems(String paramsAndTypes);
//    Future<String> showStudentsByNameMatch(String paramsAndTypes);
//
}

