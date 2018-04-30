package ro.ubb.lab7.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.lab7.core.model.Student;
import ro.ubb.lab7.core.service.StudentService;
import ro.ubb.lab7.web.converter.StudentConverter;
import ro.ubb.lab7.web.dto.EmptyJsonResponse;
import ro.ubb.lab7.web.dto.StudentDto;
import ro.ubb.lab7.web.dto.StudentsDto;

import java.util.List;

@RestController
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentConverter studentConverter;


    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public StudentsDto getStudents() {
        log.trace("getStudents");

        List<Student> students = studentService.getAllStudents();

        log.trace("getStudents: students={}", students);

        return new StudentsDto(studentConverter.convertModelsToDtos(students));
    }


//    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.PUT)
//    public StudentDto updateStudent(
//            @PathVariable final Long studentId,
//            @RequestBody final StudentDto studentDto) {
//        log.trace("updateStudent: studentId={}, studentDtoMap={}", studentId, studentDto);
//
//        Optional<Student> studentOptional = studentService.updateStudent(studentId, studentDto.getSerialNumber(),
//                studentDto.getName());
//
//        Map<String, StudentDto> result = new HashMap<>();
//        studentOptional.ifPresent(
//                student -> result.put("student", studentConverter.convertModelToDto(student)));
//                //() -> result.put("student", studentConverter.convertModelToDto(new Student())));
//
//        log.trace("updateStudent: result={}", result);
//
//        return result.get("student");
//    }


//<<<<<<< HEAD I left this here just before doing some builds.
//    @RequestMapping(value = "/students", method = RequestMethod.POST)
//    public StudentDto createStudent(
//            @RequestBody final StudentDto studentDto) {
//        log.trace("createStudent: studentDtoMap={}", studentDto);
//
//        Student student = studentService.createStudent(
//                studentDto.getName(), studentDto.getGrade());
//
//        StudentDto result = studentConverter.convertModelToDto(student);
//
//        log.trace("createStudent: result={}", result);
//        return result;
//    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public StudentDto createStudent(
            @RequestBody final StudentDto studentDto) {
        log.trace("create Student: studentDtoMap={}", studentDto);

        Student student = studentService.createStudent(
                studentDto.getSerialNumber(), studentDto.getName());

        StudentDto result = studentConverter.convertModelToDto(student);

        log.trace("create Student: result={}", result);
        return result;
    }


    @RequestMapping(value = "students/{studentId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteStudent(@PathVariable final Long studentId) {
        log.trace("delete Student: studentId={}", studentId);

        studentService.deleteStudent(studentId);

        log.trace("delete Student - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
