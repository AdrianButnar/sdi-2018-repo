package ro.ubb.socket.server.service;

import ro.ubb.socket.common.ServiceInterface;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServiceImpl implements ServiceInterface {
    private ExecutorService executorService;

    public ServiceImpl(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Future<String> doCommand(String name) {
        //aici in loc de do command ar trebui sa fie add; si apoi adaugate pentru restul functionalitatilor
        return executorService.submit(() -> "add " + name);
    }
}
