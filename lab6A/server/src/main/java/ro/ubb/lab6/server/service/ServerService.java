package ro.ubb.lab6.server.service;

import org.springframework.stereotype.Service;
import ro.ubb.lab6.common.ServiceInterface;
import ro.ubb.lab6.common.domain.Student;

import java.util.Arrays;
import java.util.List;

@Service
public class ServerService implements ServiceInterface {
    @Override
    public List<Student> findAll() {
        return Arrays.asList(
                new Student("baie2", "John"),
                new Student("caie2", "Cena")
        );
    }

    @Override
    public void addStudent() {

    }
}
