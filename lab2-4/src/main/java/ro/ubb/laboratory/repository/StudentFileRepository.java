/*
 * Copyright (c) 2018. Gandaceii
 *
 * MIT
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package ro.ubb.laboratory.repository;

import ro.ubb.laboratory.domain.Student;
import ro.ubb.laboratory.domain.validators.TestingException;
import ro.ubb.laboratory.domain.validators.Validator;
import ro.ubb.laboratory.domain.validators.ValidatorException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Alexandru Buhai
 */
public class StudentFileRepository extends InMemoryRepository<Long, Student>  {
    private String fileName;

    public StudentFileRepository(Validator<Student> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        System.out.println(fileName);
        loadData();
    }

    private void loadData() {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));
                if(fileName.equals(".\\data\\studentFile")) {
                    Long id = Long.valueOf(items.get(0));
                    String serialNumber = items.get(1);
                    String name = items.get((2));

                    Student student = new Student(serialNumber, name);
                    student.setId(id);

                    try {
                        super.save(student);
                    } catch (ValidatorException e) {
                        e.printStackTrace();
                    }
                }

            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Student> save(Student entity) throws ValidatorException {
        Optional<Student> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    private void saveToFile(Student entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    entity.getId() + "," + entity.getSerialNumber() + "," + entity.getName());

            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
