package ro.ubb.lab6.common;

import java.util.concurrent.CompletableFuture;

public interface ServiceInterface {

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

    void addStudent(Long studentId, String serialNumber, String name);
    CompletableFuture<String> printAllStudents(String options);
    CompletableFuture<String> removeStudent(Long studentId);
    CompletableFuture<String> addProblem(Long problemId,Integer number,String text);
    CompletableFuture<String> printAllProblems(String options);
    CompletableFuture<String> removeProblem(Long problemId);
    CompletableFuture<String> assignProblemToStudent(Long assignmentId, Long studentId, Long problemId);
    CompletableFuture<String> showAllProblemsOfAStudent(Long studentId);
    CompletableFuture<String> showTheMostAssignedProblems(String paramsAndTypes);
    CompletableFuture<String> showStudentsByNameMatch(String name);

}
