package ro.ubb.lab6.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.lab6.common.ServiceInterface;
import org.springframework.stereotype.Service;
import ro.ubb.lab6.common.domain.Assignment;
import ro.ubb.lab6.common.domain.Problem;
import ro.ubb.lab6.common.domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
public class ServerService implements ServiceInterface {

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private AssignmentDbService assignmentDbService;

    public ServerService(ExecutorService executorService, StudentService studentService, ProblemService problemService, AssignmentDbService assignmentDbService) {
        this.executorService = executorService;
        this.studentService = studentService;
        this.problemService = problemService;
        this.assignmentDbService = assignmentDbService;
    }

    @Override
    public void addStudent(Long studentId, String serialNumber, String name) {
        // String[] args=paramsAndTypes.split(";");
        try{
            Student s= new Student(serialNumber,name);
            s.setId(studentId);
            studentService.addStudent(s);
        }
        catch (Exception ex){
            System.out.println("Exception Server");
            ex.printStackTrace();
        }
    }

    @Override
    public List<Student> printAllStudents(String options) {
        return new ArrayList<>(studentService.getAllStudents());

    }

    @Override
    public void removeStudent(Long studentId) {
        try{
            studentService.removeStudent(studentId);
        }
        catch (Exception ex){
            System.out.println("Exception Server");
            ex.printStackTrace();
        }
    }

    @Override
    public void addProblem(Long problemId,Integer number,String text) {
        try{
            Problem pb= new Problem(number, text);
            pb.setId(problemId);
            problemService.addProblem(pb);
        }
        catch (Exception ex){
            System.out.println("Exception server service");
            ex.printStackTrace();

        }
    }

    @Override
    public List<Problem> printAllProblems(String options) {
        return new ArrayList<>(problemService.getAllProblems());
//        for(Problem pb : problems)
//        {
//            System.out.println(pb.toString());
//        }

    }

    @Override
    public void removeProblem(Long problemId) {
        try{
            problemService.removeProblem(problemId);
           // return CompletableFuture.supplyAsync(() -> "Problem removed successfully! ",executorService);
        }
        catch (Exception ex){
            System.out.println("Exception server service");
            ex.printStackTrace();
           // return CompletableFuture.supplyAsync(() -> "Problem id is invalid!",executorService);
        }
    }


    @Override
    public void assignProblemToStudent(Long assignmentId, Long studentId, Long problemId) {
        //String[] args = paramsAndTypes.split(";");
        try{
            Assignment as = new Assignment(studentId, problemId);
            as.setId(assignmentId);
            assignmentDbService.addAssignment(as);
        }
        catch (Exception ex){
            System.out.println("Server Service");
            ex.printStackTrace();
        }
    }


    @Override
    public List<Assignment> showAllProblemsOfAStudent(Long studentId) {
       List<Assignment> as = new ArrayList<>(assignmentDbService.getAllAssignments());
       List<Assignment> newAs = new ArrayList<>();
       for(Assignment a : as)
       {
           if(a.getStudentID().equals(studentId))
           {
               newAs.add(a);
           }
       }
       return newAs;
    }

    @Override
    public List<Student> showStudentsByNameMatch(String name){
        List<Student> newStudents = new ArrayList<>();
        for (Student s: studentService.getAllStudents()){
            if(s.getName().toLowerCase().contains(name)) {
                newStudents.add(s);
            }
        }
        return newStudents;
    }

//    @Override
//    public void showTheMostAssignedProblems(String paramsAndTypes) {
//        throw new RuntimeException("Not yet implemented");
//    }
}
