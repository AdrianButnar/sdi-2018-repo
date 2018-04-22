package ro.ubb.lab7.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import ro.ubb.lab7.web.dto.StudentDto;
import ro.ubb.lab7.web.dto.StudentsDto;

/**
 * Created by radu.
 */
public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.catalog.client.config");
        RestTemplate restTemplate = context.getBean(RestTemplate.class);

        StudentsDto studentsDto = restTemplate
                .getForObject("http://localhost:8080/api/students", StudentsDto.class);
        studentsDto.getStudents()
                .forEach(System.out::println);


        StudentDto student = restTemplate
                .postForObject("http://localhost:8080/api/students",
                        new StudentDto("s1", 10),
                        StudentDto.class);
        System.out.println(student);


        student.setGrade(student.getGrade() + 1);
        restTemplate
                .put("http://localhost:8080/api/students/{studentId}",
                        student, student.getId());


//        restTemplate
//                .delete("http://localhost:8080/api/students/{studentId}",
//                        student.getId());

        System.out.println("bye ");
    }
}
