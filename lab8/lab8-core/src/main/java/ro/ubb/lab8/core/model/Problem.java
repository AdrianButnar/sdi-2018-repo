package ro.ubb.lab8.core.model;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
@EqualsAndHashCode
public class Problem extends BaseEntity<Long> implements Serializable, Comparable<Long> {
    private int number;
    private String text;
    public Problem(){}
    public Problem(Integer number, String text) {
        this.number = number;
        this.text = text;
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
    public String toString() {
        return  "Problem {" +
                "ID = " + this.getId() +
                ", Number = '" + number + '\'' +
                ", Text = '" + text + '\'' +
                '}';
    }

    @Override
    public int compareTo(Long o) {
        return 0;
    }
}
/*

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

 */