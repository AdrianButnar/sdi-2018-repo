package ro.ubb.lab11.core.service;


import ro.ubb.lab11.core.model.Student;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface StudentService {
    List<Student> findAll();

    Student createStudent(String serialNumber, String name);

    Student updateStudent(Long studentId, String serialNumber, String name, Set<Long> problems);

    void deleteStudent(Long id);

    Optional<Student> findStudent(Long studentId);

    Optional<Student> updateStudentGrades(Long studentId, Map<Long, Integer> grades);
}