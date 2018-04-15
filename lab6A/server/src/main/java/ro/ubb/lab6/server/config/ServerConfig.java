package ro.ubb.lab6.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.lab6.common.ServiceInterface;
import ro.ubb.lab6.server.service.ServerService;

@Configuration
public class ServerConfig {
    @Bean
    ServiceInterface serviceInterface() {
        return new ServerService();
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
