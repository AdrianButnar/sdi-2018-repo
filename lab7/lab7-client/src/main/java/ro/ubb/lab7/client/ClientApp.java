package ro.ubb.lab7.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import ro.ubb.lab7.web.dto.StudentDto;
import ro.ubb.lab7.web.dto.StudentsDto;



public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.lab7.client.config");
        RestTemplate restTemplate = context.getBean(RestTemplate.class);

        StudentsDto studentsDto = restTemplate
                .getForObject("http://localhost:8080/api/students", StudentsDto.class);
        studentsDto.getStudents()
                .forEach(System.out::println);


        StudentDto student = restTemplate
                .postForObject("http://localhost:8080/api/students",
                        new StudentDto("Mihai","miie2019"),
                        StudentDto.class);
        System.out.println(student);

        String newSerialNumber = student.getSerialNumber() + "1";
        System.out.println(newSerialNumber+"\n");
        student.setSerialNumber(newSerialNumber);
        restTemplate
                .put("http://localhost:8080/api/students/{studentId}",
                        student, student.getId());

        System.out.println("New print:");
        studentsDto.getStudents()
                .forEach(System.out::println);
//
//        restTemplate
//                .delete("http://localhost:8080/api/students/{studentId}",
//                        student.getId());

        System.out.println("bye ");
    }
}
