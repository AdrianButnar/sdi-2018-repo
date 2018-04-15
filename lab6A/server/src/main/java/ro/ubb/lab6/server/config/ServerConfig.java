package ro.ubb.lab6.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.support.ExecutorServiceAdapter;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Repository;
import ro.ubb.lab6.common.ServiceInterface;
import ro.ubb.lab6.server.repository.AssignmentDbRepository;
import ro.ubb.lab6.server.repository.ProblemDbRepository;
import ro.ubb.lab6.server.repository.StudentDbRepository;
import ro.ubb.lab6.server.service.AssignmentDbService;
import ro.ubb.lab6.server.service.ProblemService;
import ro.ubb.lab6.server.service.ServerService;
import ro.ubb.lab6.server.service.StudentService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ServerConfig {

    @Autowired
    private StudentDbRepository studentDbRepository;

    @Bean
    public StudentDbRepository studentDbRepository()
    {
        return new StudentDbRepository("jdbc:postgresql://localhost:5432/Mppdatabase");
    }

    private ProblemDbRepository problemDbRepository = new ProblemDbRepository("jdbc:postgresql://localhost:5432/Mppdatabase");
    private AssignmentDbRepository assignmentDbRepository = new AssignmentDbRepository("jdbc:postgresql://localhost:5432/Mppdatabase");

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private AssignmentDbService assignmentDbService;

    @Autowired
    private ExecutorService executorService;

    @Bean
    public ServiceInterface serviceInterface() {
        return new ServerService(executorService ,studentService,problemService,assignmentDbService);
    }

    @Bean
    public StudentService studentService(){
        return new StudentService(studentDbRepository);
    }
    @Bean
    public ProblemService problemService(){
        return new ProblemService(problemDbRepository);
    }
    @Bean
    public AssignmentDbService assignmentDbService(){
        return new AssignmentDbService(assignmentDbRepository);
    }
    @Bean
    public ExecutorService executorService(){
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Bean
    RmiServiceExporter rmiServiceExporter(){
        RmiServiceExporter exporter =new RmiServiceExporter();
        exporter.setServiceName("ServiceInterface");
        exporter.setServiceInterface(ServiceInterface.class);
        exporter.setService(serviceInterface());
        return exporter;
    }

}
