package ro.ubb.remoting.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.lab6.common.ServiceInterface;
import ro.ubb.remoting.client.ui.ClientConsole;

public class ClientApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.remoting.client.config");

        ServiceInterface serviceInterface = (ServiceInterface) context.getBean("studentServiceClient");
        ClientConsole console = new ClientConsole(serviceInterface);
        console.runConsole();

        System.out.println("bye - client");
    }
}
