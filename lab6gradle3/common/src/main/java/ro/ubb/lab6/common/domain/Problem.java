package ro.ubb.lab6.common.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * <h2> ProblemFileRepository class </h2>
 * <p> ProblemFileRepository </p>
 * @author Alexandru Buhai
 * @version 1.0.1
 */
public class Problem extends BaseEntity<Long> implements Serializable {
    private int number;
    private String text;
    private int grade;


    public Problem(Integer number, String text) {
        this.number = number;
        this.text = text;
    }
    public Problem(Long id, Integer number, String text)
    {
        this.number = number;
        this.text = text;
        this.setId(id);
    }

    public Problem() {
        this.number = 0;
        this.text = "";
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problem problem = (Problem) o;
        return number == problem.number &&
                Objects.equals(text, problem.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number, text);
    }

    @Override
    public String toString() {
        return  "Problem {" +
                "ID = " + this.getId() +
                ", Number = '" + number + '\'' +
                ", Text = '" + text + '\'' +
                '}';
    }
}
