package ro.ubb.lab11.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.lab11.core.model.Student;
import ro.ubb.lab11.core.service.StudentService;
import ro.ubb.lab11.web.converter.StudentConverter;
import ro.ubb.lab11.web.dto.EmptyJsonResponse;
import ro.ubb.lab11.web.dto.StudentDto;

import java.util.*;

@RestController
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentConverter studentConverter;


    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<StudentDto> getStudents() {
        log.trace("getStudents");
        //log.warn("getStudents");
        //log.info("Hello");

        List<Student> students = studentService.findAll();

        log.trace("getStudents: students={}", students);

        return new ArrayList<>(studentConverter.convertModelsToDtos(students)); //AICI ERA PROBLEMA! TU RETURNAI UN SET, EL RETURNA UN ARRAYLIST
    }


    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.PUT)
    public StudentDto updateStudent(
            @PathVariable final Long studentId,
            @RequestBody final StudentDto studentDto) {
        log.info("Hello");

        log.trace("updateStudent: studentId={}, studentDtoMap={}", studentId, studentDto);
        Student studentOptional = studentService.updateStudent(studentId,
                studentDto.getSerialNumber(),
                studentDto.getName(),
                studentDto.getProblems());

        StudentDto result = studentConverter.convertModelToDto(studentOptional);

        log.trace("updateStudent: result={}", result);

        return result;
    }



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
        log.trace("deleteStudent: studentId={}", studentId);

        studentService.deleteStudent(studentId);

        log.trace("deleteStudent - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
