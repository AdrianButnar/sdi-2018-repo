package ro.ubb.socket.common;

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

    Future<String> addStudent(String paramsAndTypes);
    Future<String> printAllStudents(String paramsAndTypes);
    Future<String> removeStudent(String paramsAndTypes);
    Future<String> addProblem(String paramsAndTypes);
    Future<String> printAllProblems(String paramsAndTypes);
    Future<String> removeProblem(String paramsAndTypes);
    Future<String> assignProblemToStudent(String paramsAndTypes);
    Future<String> showAllProblemsOfAStudent(String paramsAndTypes);
    Future<String> showTheMostAssignedProblems(String paramsAndTypes);

}

