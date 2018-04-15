package ro.ubb.lab5.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.support.ExecutorServiceAdapter;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.lab5.client.service.ClientServiceImpl;
import ro.ubb.lab5.client.tcp.TcpClient;
import ro.ubb.socket.common.ServiceInterface;
import ro.ubb.socket.server.service.StudentService;

import java.util.concurrent.ExecutorService;

@Configuration
public class ClientConfig {
    private ExecutorService executorService;
    @Bean
    ServiceInterface studentServiceClient(){
        return new ClientServiceImpl(executorService,tcpClient); //sau executor service din spring??-dar ala e ceva adapter

    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBean(){
        RmiProxyFactoryBean proxy=new RmiProxyFactoryBean();
        proxy.setServiceInterface(StudentService.class);
        proxy.setServiceUrl("rmi://localhost:1099/ServiceInterface");
        return proxy;
    }
}
