package ro.ubb.laboratory.repository;

import ro.ubb.laboratory.domain.Student;
import ro.ubb.laboratory.domain.validators.StudentCannotBeSavedException;
import ro.ubb.laboratory.domain.validators.Validator;
import ro.ubb.laboratory.domain.validators.ValidatorException;
import ro.ubb.laboratory.util.XmlReader;
import ro.ubb.laboratory.util.XmlWriter;

import java.util.List;
import java.util.Optional;

public class StudentXmlRepository extends InMemoryRepository<Long,Student> {
    private String fileName;

    public StudentXmlRepository(String fileName,Validator<Student> studentValidator) {
        super(studentValidator);
        this.fileName = fileName;
        loadData();
    }

    private void loadData(){
        List<Student> students = new XmlReader<Long,Student>(fileName).loadEntities();
        for (Student student : students){
            try{
                this.save(student);
            }
            catch (ValidatorException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Student> save(Student entity) throws StudentCannotBeSavedException { //aici trebuie entitypresentexception?
        Optional<Student> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        new XmlWriter<Long, Student>(fileName).writeToFile(entity,super.findAll());
        return optional;
    }

}


