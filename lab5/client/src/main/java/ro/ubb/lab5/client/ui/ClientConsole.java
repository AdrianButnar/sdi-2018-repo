package ro.ubb.lab5.client.ui;

import ro.ubb.socket.common.ServiceInterface;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ClientConsole {
    private ServiceInterface helloService;

    public ClientConsole(ServiceInterface helloService) {
        this.helloService = helloService;
    }

    public void runConsole() {
        sayHello();
    }

    private void sayHello() {
        Scanner sc= new Scanner(System.in);
        System.out.println("Input command: ");
        String command = sc.nextLine();
        System.out.println("Enter params: ");
        String name = sc.nextLine();
        Future<String> res;
        switch (command) {
            case "addStudent":
                res = helloService.addStudent(name);
                break;
            default:
                res= null;
                break;
        }

        try {
            System.out.println(res.get());//blocking :(
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
