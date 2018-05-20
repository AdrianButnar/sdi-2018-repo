package ro.ubb.lab11.core.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.lab11.core.model.BaseEntity;
import ro.ubb.lab11.core.model.Problem;
import ro.ubb.lab11.core.model.Student;
import ro.ubb.lab11.core.repository.ProblemRepository;
import ro.ubb.lab11.core.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProblemRepository problemRepository;
    @Override
    public List<Student> findAll() {
        log.trace("getAllStudents --- method entered");

        List<Student> students = studentRepository.findAll();

        log.trace("getAllStudents: students={}", students);

        return students;
    }

    @Override
    public Student createStudent(String serialNumber,String name) {
        log.trace("saveStudent: serialNumber ={}, name={} ", serialNumber,name);

        Student student = (Student) studentRepository.save(new Student(serialNumber,name)); //TODO This seems bad, just to solve conflict

        log.trace("saveStudent: student={}", student);

        return student;
    }

    @Override
    @Transactional
    public Optional<Student> updateStudent(Long studentId, String serialNumber, String name, Set<Long>problems) {
        log.trace("updateStudent: studentId={}, serialNumber={},  name={},  problems={}", studentId, serialNumber,name,problems);

        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        optionalStudent.ifPresent(st -> {
            st.setSerialNumber(serialNumber);
            st.setName(name);

            //the problems part needs also to be adjusted
            st.getProblems().stream()
                    .map(BaseEntity::getId)
                    .forEach(problems::remove);
            List<Problem> problemList = problemRepository.findAllById(problems);
            problemList.forEach(st::addProblem);
        });



        log.trace("updateStudent: optionalStudent={}", optionalStudent);

        return optionalStudent;
    }

    @Override
    public void deleteStudent(Long id) {
        log.trace("deleteStudent: id={}", id);

        studentRepository.deleteById(id);

        log.trace("deleteStudent --- method finished");
    }


}

