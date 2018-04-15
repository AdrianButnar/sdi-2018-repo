package ro.ubb.lab6.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.lab6.common.ServiceInterface;
import ro.ubb.lab6.common.domain.Student;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ClientService implements ServiceInterface {

    //Completely lost here
    @Autowired
    private ServiceInterface service;
//
//    @Override
//    public List<Student> findAll() {
//        return service.findAll(); //the implementation?
//    }

    @Override
    public CompletableFuture<String> addStudent(Long studentId, String serialNumber, String name) {
        service.addStudent(studentId, serialNumber, name);
    }
}
