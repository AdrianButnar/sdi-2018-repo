package ro.ubb.socket.server.service;

import ro.ubb.socket.common.HelloService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class HelloServiceImpl implements HelloService {
    private ExecutorService executorService;

    public HelloServiceImpl(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Future<String> sayHello(String name) {
        return executorService.submit(() -> "Hello " + name);
    }
}
