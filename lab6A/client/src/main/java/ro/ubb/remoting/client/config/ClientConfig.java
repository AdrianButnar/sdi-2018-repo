package ro.ubb.remoting.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.lab6.common.ServiceInterface;
import ro.ubb.remoting.client.service.ClientService;

@Configuration
public class ClientConfig {
    @Bean
    ServiceInterface studentServiceClient(){
        return new ClientService();
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBean(){
        RmiProxyFactoryBean proxy=new RmiProxyFactoryBean();
        proxy.setServiceInterface(ServiceInterface.class);
        proxy.setServiceUrl("rmi://localhost:1099/ServiceInterface");
        return proxy;
    }
}
