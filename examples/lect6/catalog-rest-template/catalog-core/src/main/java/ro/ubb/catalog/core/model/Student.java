package ro.ubb.catalog.core.model;

import javax.persistence.Entity;

@Entity
public class Student extends BaseEntity<Long> {
    private String name;
    private int grade;
    private int car;

    public void setCar(int car) {
        this.car = car;
    }

    public int getCar() {
        return car;
    }

    public Student() {
    }

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                "} " + super.toString();
    }
}
