package ro.ubb.lab7.core.service;


import ro.ubb.lab7.core.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();

    Student createStudent(String serialNumber, String name);

    Optional<Student> updateStudent(Long studentId,String serialNumber, String name);

    void deleteStudent(Long id);
}