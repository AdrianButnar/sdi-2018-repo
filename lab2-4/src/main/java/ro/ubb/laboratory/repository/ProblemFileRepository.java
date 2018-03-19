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

import ro.ubb.laboratory.domain.Problem;
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

public class ProblemFileRepository extends InMemoryRepository<Long, Problem> {
    private String fileName;

    public ProblemFileRepository(Validator<Problem> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        System.out.println(fileName);
        loadData();
    }

    private int number;
    private String text;

    private void loadData() {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));
                if (fileName.equals(".\\data\\problemFile")) {
                    System.out.println("Not ready to read form problem file");
                    Long id = Long.valueOf(items.get(0));
                    int number = Integer.parseInt(items.get(1));
                    String text = items.get((2));

                    Problem problem = new Problem(number, text);
                    problem.setId(id);

                    try {
                        super.save(problem);
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
    public Optional<Problem> save(Problem entity) throws ValidatorException {
        Optional<Problem> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    private void saveToFile(Problem entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    entity.getId() + "," + entity.getNumber() + "," + entity.getText());

            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}