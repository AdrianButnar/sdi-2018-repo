package ro.ubb.catalog.repository;

import java.util.List;
import java.util.Optional;

import ro.ubb.catalog.domain.Student;
import ro.ubb.catalog.domain.validators.Validator;
import ro.ubb.catalog.domain.validators.ValidatorException;
import ro.ubb.catalog.util.XmlReader;
import ro.ubb.catalog.util.XmlWriter;

/**
 * @author radu.
 */
public class StudentXmlRepository extends InMemoryRepository<Long, Student> {
    private String fileName;

    public StudentXmlRepository(Validator<Student> validator, String fileName) {
        super(validator);
        this.fileName = fileName;

        loadData();
    }

    private void loadData() {
        List<Student> students = new XmlReader<Long, Student>(fileName).loadEntities();
        for (Student student : students) {
            try {
                super.save(student);
            } catch (ValidatorException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Student> save(Student entity) throws ValidatorException {
        Optional<Student> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        new XmlWriter<Long, Student>(fileName).save(entity);
        return Optional.empty();
    }
}
