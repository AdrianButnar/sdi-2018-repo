package ro.ubb.lab5.client.service;

import ro.ubb.lab5.client.tcp.TcpClient;
import ro.ubb.socket.common.Message;
import ro.ubb.socket.common.ServiceInterface;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServiceClient implements ServiceInterface {
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public ServiceClient(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

//    @Override
//    public Future<String> sayHello(String name) {
//        return executorService.submit(() -> {
////            Message request = new Message(HelloService.SAY_HELLO, name);
//            Message request = Message.builder()
//                    .header(HelloService.SAY_HELLO)
//                    .body(name)
//                    .build();
//            Message response = tcpClient.sendAndReceive(request);
//            //!!!! err handling
//            return response.getBody();
//        });
//
//    }

    @Override
    public Future<String> doCommand(String name) {
        return executorService.submit(()->{
            Message request = Message.builder()
                    .header(ServiceClient.ADD)
                    .body(name)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }
}

