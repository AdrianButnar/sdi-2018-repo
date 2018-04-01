package ro.ubb.socket.server.service;

import ro.ubb.socket.common.ServiceInterface;
import ro.ubb.socket.common.domain.Assignment;
import ro.ubb.socket.common.domain.Problem;
import ro.ubb.socket.common.domain.Student;
import ro.ubb.socket.common.domain.validators.AssignmentValidator;
import ro.ubb.socket.common.domain.validators.ProblemValidator;
import ro.ubb.socket.common.domain.validators.StudentValidator;
import ro.ubb.socket.common.domain.validators.Validator;
import ro.ubb.socket.common.repository.AssignmentDbRepository;
import ro.ubb.socket.common.repository.ProblemDbRepository;
import ro.ubb.socket.common.repository.Repository;
import ro.ubb.socket.common.repository.StudentDbRepository;
import ro.ubb.socket.common.service.AssignmentDbService;
import ro.ubb.socket.common.service.ProblemService;
import ro.ubb.socket.common.service.StudentService;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServerServiceImpl implements ServiceInterface {
    private ExecutorService executorService;
    private Repository srepo;
    private Repository pbrepo;
    private Repository asrepo;

    public ServerServiceImpl(ExecutorService executorService, StudentService studentService, ProblemService problemService, AssignmentDbService assignmentDbService, Validator<Student> studentValidator, Validator<Assignment> assignmentValidator, Validator<Problem> problemValidator) {
        this.executorService = executorService;
    }

    public ServerServiceImpl(ExecutorService executorService, Repository<Long, Student> srepo, Repository<Long, Problem> pbrepo, Repository<Long, Assignment> asrepo) {
        this.executorService = executorService;
        this.srepo = srepo;
        this.pbrepo = pbrepo;
        this.asrepo = asrepo;
    }

    @Override
    public Future<String> addStudent(String paramsAndTypes) {
        String[] args=paramsAndTypes.split(";");
        try{
            Student s= new Student(args[1],args[2]);
            s.setId(Long.parseLong(args[0]));
            //((StudentDbRepository)srepo).getValidator().validate(s);
            srepo.save(s);
            return executorService.submit(() -> "added student! ");
        }
        catch (Exception ex){
            return executorService.submit(()->"Student data was invalid!");
        }
    }
}
