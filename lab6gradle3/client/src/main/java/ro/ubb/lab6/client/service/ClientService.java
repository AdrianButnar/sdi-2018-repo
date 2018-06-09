package ro.ubb.lab6.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.lab6.common.ServiceInterface;
import ro.ubb.lab6.common.domain.Assignment;
import ro.ubb.lab6.common.domain.Problem;
import ro.ubb.lab6.common.domain.Student;

import java.util.ArrayList;
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
    public List<Student> printAllStudents(String options) {
        return service.printAllStudents(options);
    }

    @Override
    public void removeStudent(Long studentId) {
        service.removeStudent(studentId);
    }

    @Override
    public void addProblem(Long problemId, Integer number, String text) {
        service.addProblem(problemId, number, text);
    }

    @Override
    public List<Problem> printAllProblems(String options) {
        List<Problem> problems = service.printAllProblems("");
//        System.out.println("In service: ");
//        for(Problem pb : problems)
//        {
//            System.out.println(pb);
//        }
//        System.out.println("");
        return problems;
    }

    @Override
    public void removeProblem(Long problemId) {
        service.removeProblem(problemId);
    }

    @Override
    public void assignProblemToStudent(Long assignmentId, Long studentId, Long problemId) {
        service.assignProblemToStudent(assignmentId, studentId, problemId);
    }

    @Override
    public  List<Assignment> showAllProblemsOfAStudent(Long studentId) {
        return service.showAllProblemsOfAStudent(studentId);
    }

//    @Override
//    public List<String> showTheMostAssignedProblems(String paramsAndTypes) {
//        return null;
//    }

    @Override
    public List<Student> showStudentsByNameMatch(String name) {
        return service.showStudentsByNameMatch(name);
    }
}
