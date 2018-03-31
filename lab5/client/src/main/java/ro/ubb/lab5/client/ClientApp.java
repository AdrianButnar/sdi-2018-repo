package ro.ubb.lab5.client;

import ro.ubb.lab5.client.service.ServiceClient;
import ro.ubb.lab5.client.tcp.TcpClient;
import ro.ubb.lab5.client.ui.ClientConsole;
import ro.ubb.socket.common.ServiceInterface;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpClient tcpClient = new TcpClient(executorService, ServiceInterface.SERVER_HOST, ServiceInterface.SERVER_PORT);
        ServiceInterface helloService = new ServiceClient(executorService, tcpClient);
        ClientConsole clientConsole = new ClientConsole(helloService);
        clientConsole.runConsole();

        executorService.shutdownNow();

        System.out.println("bye - client");
    }
}

