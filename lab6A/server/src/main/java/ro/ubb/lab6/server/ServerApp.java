package ro.ubb.lab6.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.lab6.common.ServiceInterface;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ServerApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.lab6.server.config");

        //ServiceInterface serviceInterface=context.getBean(ServiceInterface.class);
       // CompletableFuture<String> result =  serviceInterface.printAllStudents("");//.findAll().forEach(System.out::println);
       // handleResult(result);
       // ServiceInterface serviceInterface=context.getBean(ServiceInterface.class);
     //   CompletableFuture<String> result =  serviceInterface.printAllProblems("");//.findAll().forEach(System.out::println);
     //   handleResult(result);
        System.out.println("Server is ready! ");
    }

    private static void handleResult(CompletableFuture<String> result){
        result.whenComplete((task,throwable)->{
            if (throwable!=null)
                System.err.println(throwable.getMessage());
            try{
                System.out.println(result.get().replace(";","\n"));
            } catch (InterruptedException|ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}

