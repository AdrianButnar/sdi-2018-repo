package ro.ubb.lab6.common;

import ro.ubb.lab6.common.domain.Assignment;
import ro.ubb.lab6.common.domain.Problem;
import ro.ubb.lab6.common.domain.Student;


import java.util.List;
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
    List<Student> printAllStudents(String options);
    void removeStudent(Long studentId);
    void addProblem(Long problemId,Integer number,String text);
    List<Problem> printAllProblems(String options);
    void removeProblem(Long problemId);
    void assignProblemToStudent(Long assignmentId, Long studentId, Long problemId);
    List<Assignment> showAllProblemsOfAStudent(Long studentId);
//    void showTheMostAssignedProblems(String paramsAndTypes);
    List<Student> showStudentsByNameMatch(String name);

}
