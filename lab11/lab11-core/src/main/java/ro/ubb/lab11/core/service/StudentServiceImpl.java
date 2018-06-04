package ro.ubb.lab11.core.service;


import org.hibernate.Hibernate;
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
import ro.ubb.lab11.core.repository.StudentRepositoryCustom;
import ro.ubb.lab11.core.repository.StudentRepositoryImpl;


import java.util.List;
import java.util.Map;
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
//        log.trace("getAllStudents --- method entered");
//        Hibernate.initialize(studentRepository.findAll());
//        List<Student> students = studentRepository.findAll();
//
//        log.trace("getAllStudents: students={}", students);


        //List<Student> students = studentRepository.findAllWithJpql();
        //List<Student> students = studentRepository.findAllWithAssignmentsAndProblems();
        //List<Student> students = studentRepository.findAllWithAssignments();

        //List<Student> students = studentRepository.findAllWithAssignmentsAndProblemsSQL();
        List<Student> students = studentRepository.findAllWithAssignmentsAndProblemsCriteriaAPI();
        return students;
    }

    @Override
    public Student createStudent(String serialNumber,String name) {
        log.trace("saveStudent: serialNumber ={}, name={} ", serialNumber,name);

        Student student = Student.builder()
                .serialNumber(serialNumber)
                .name(name)
                .build();
        student = studentRepository.save(student);

        log.trace("saveStudent: student={}", student);

        return student;
    }

    @Override
    @Transactional
    public Student updateStudent(Long studentId, String serialNumber, String name, Set<Long>problems) {
        log.trace("updateStudent: studentId={}, serialNumber={},  name={},  problems={}", studentId, serialNumber,name,problems);

        Optional<Student> optionalStudent = studentRepository.findOneWithJPQL(studentId);

        optionalStudent.ifPresent(st -> {
            st.setSerialNumber(serialNumber);
            st.setName(name);

            //the problems part needs also to be adjusted
            //Hibernate.initialize(st.getAssignments());
            //Hibernate.initialize(st.getProblems());
            st.getProblems().stream()
                    .map(BaseEntity::getId)
                    .forEach(problems::remove);
            List<Problem> problemList = problemRepository.findAllById(problems);
            problemList.forEach(st::addProblem);
        });

        log.trace("updateStudent: optionalStudent={}", optionalStudent);

        return optionalStudent.orElse(null);
    }



    @Override
    public void deleteStudent(Long id) {
        log.trace("deleteStudent: id={}", id);

        studentRepository.deleteById(id);

        log.trace("deleteStudent --- method finished");
    }

    @Override
    public Optional<Student> findStudent(Long studentId) {
        log.trace("findStudent: studentId={}", studentId);

        //Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Student> studentOptional = studentRepository.findOneWithJPQL(studentId);
        log.trace("findStudent: studentOptional={}", studentOptional);

        return studentOptional;
    }

    @Override
    @Transactional
    public Optional<Student> updateStudentGrades(Long studentId, Map<Long, Integer> grades ) {
        log.trace("updateStudentGrades: studentId={}, grades={}", studentId, grades);

        Optional<Student> studentOptional = studentRepository.findOneWithJPQL(studentId);
        studentOptional.ifPresent(student ->
                student.getAssignments()
                        .forEach(as ->
                                as.setGrade(grades.get(as.getProblem().getId())))
        );

        log.trace("updateStudentGrades: studentOptional={}", studentOptional);

        return studentOptional;
    }
}

