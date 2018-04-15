package ro.ubb.lab6.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.lab6.common.ServiceInterface;

public class ServerApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.lab6.server.config");

        ServiceInterface serviceInterface=context.getBean(ServiceInterface.class);
//        studentService.findAll().forEach(System.out::println);

        System.out.println("bye - server");
    }
}
