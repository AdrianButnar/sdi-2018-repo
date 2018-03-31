package ro.ubb.socket.server;

import ro.ubb.socket.common.Message;
import ro.ubb.socket.common.ServiceInterface;
import ro.ubb.socket.server.service.ServiceImpl;
import ro.ubb.socket.server.tcp.TcpServer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpServer tcpServer = new TcpServer(executorService, ServiceInterface.SERVER_HOST, ServiceInterface.SERVER_PORT);
        ServiceInterface helloService = new ServiceImpl(executorService);

        tcpServer.addHandler(ServiceInterface.ADD, (request) -> {
            Future<String> res = helloService.doCommand(request.getBody());
            try {
                String result = res.get();
                return Message.builder()
                        .header(Message.OK)
                        .body(result)
                        .build();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                //return new Message(Message.ERROR, "");
                return Message.builder()
                        .header(Message.ERROR)
                        .build();
            }
        });

        tcpServer.startServer();

        System.out.println("bye - server");
    }
}
