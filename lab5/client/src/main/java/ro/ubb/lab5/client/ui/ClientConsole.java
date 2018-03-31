package ro.ubb.lab5.client.ui;

import ro.ubb.socket.common.ServiceInterface;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ClientConsole {
    private ServiceInterface helloService;

    public ClientConsole(ServiceInterface helloService) {
        this.helloService = helloService;
    }

    public void runConsole() {
        sayHello();
    }

    private void sayHello() {
        String name = "id:99;name:gigi;serialNumber:asd";
        Future<String> res = helloService.doCommand(name);

        try {
            System.out.println(res.get());//blocking :(
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
