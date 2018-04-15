package ro.ubb.jdbctemplate.domain;

public class Student {
    private Long id;
    private String name;
    private int grade;

    public Student(Long id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Student(String name, int grade) {
        this(null, name, grade);
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                '}';
    }
}
