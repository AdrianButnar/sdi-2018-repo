package ro.ubb.catalog.repository;

import ro.ubb.catalog.domain.Student;
import ro.ubb.catalog.domain.validators.CatalogException;
import ro.ubb.catalog.domain.validators.Validator;
import ro.ubb.catalog.domain.validators.ValidatorException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by radu.
 */
public class StudentDbRepository implements Repository<Long, Student> {
    private Validator<Student> validator;
    private String url;
    private String username;
    private String password;

    public StudentDbRepository(Validator<Student> studentValidator, String url, String username, String password) {
        this.validator = studentValidator;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Optional<Student> findOne(Long id) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public Iterable<Student> findAll() {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public Optional<Student> save(Student entity) throws ValidatorException {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public Optional<Student> delete(Long id) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public Optional<Student> update(Student entity) throws ValidatorException {
        throw new RuntimeException("not yet implemented");
    }
}
