package ro.ubb.lab6.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Repository;
import ro.ubb.lab6.common.ServiceInterface;
import ro.ubb.lab6.server.service.AssignmentDbService;
import ro.ubb.lab6.server.service.ProblemService;
import ro.ubb.lab6.server.service.ServerService;
import ro.ubb.lab6.server.service.StudentService;

import java.util.concurrent.ExecutorService;

@Configuration
public class ServerConfig {


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
        return new ServerService(executorService,studentService,problemService,assignmentDbService);
    }

    @Bean
    public StudentService studentService(){
        return new StudentService();
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
