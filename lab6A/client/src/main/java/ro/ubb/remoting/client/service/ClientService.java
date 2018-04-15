package ro.ubb.remoting.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.lab6.common.ServiceInterface;
import ro.ubb.lab6.common.domain.Student;

import java.util.List;

public class ClientService implements ServiceInterface {
    @Autowired
    private ServiceInterface service;

    @Override
    public List<Student> findAll() {
        return service.findAll();
    }

    @Override
    public void addStudent() {
        service.addStudent();
    }
}
