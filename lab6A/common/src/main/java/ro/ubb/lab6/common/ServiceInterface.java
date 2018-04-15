package ro.ubb.lab6.common;

import ro.ubb.lab6.common.domain.Student;

import java.util.List;

public interface ServiceInterface {
    List<Student> findAll();
    void addStudent();

}
