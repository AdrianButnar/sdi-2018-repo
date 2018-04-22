package ro.ubb.lab6.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.lab6.common.ServiceInterface;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ServerApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.lab6.server.config");



        System.out.println("Server is ready! ");
    }

}

