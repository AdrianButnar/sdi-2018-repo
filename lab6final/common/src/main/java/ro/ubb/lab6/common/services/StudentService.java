package ro.ubb.lab6.common.services;

import ro.ubb.lab6.common.domain.Student;
import ro.ubb.lab6.common.domain.validators.ValidatorException;

import java.sql.*;
import java.sql.Connection;
import java.util.*;
import java.util.concurrent.CompletableFuture;


public interface StudentService extends Repository<Long, Student> {
//
//    Optional<Student> findOne(Long id);
//    Iterable<Student> findAll();
//    Optional<Student> save(Student entity) throws ValidatorException;
//    Optional<Student> remove(Long id);
//
//    Connection getConnection();


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

    CompletableFuture<String> addStudent(Long studentId, String serialNumber, String name);
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
