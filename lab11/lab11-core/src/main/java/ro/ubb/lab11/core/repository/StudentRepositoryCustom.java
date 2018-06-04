package ro.ubb.lab11.core.repository;

import ro.ubb.lab11.core.model.Student;

import java.util.List;

public interface StudentRepositoryCustom {
    List<Student> findAllWithJpql();
}
