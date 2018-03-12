package ro.ubb.laboratory.domain;

import java.util.Objects;

/**
 * <h2> Problems class </h2>
 * <p> Problem </p>
 * @version 1.0.0
 */
public class Problems extends BaseEntity<Long>  {
    private int number;
    private String text;

    public Problems(int number, String text) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problems problems = (Problems) o;
        return number == problems.number &&
                Objects.equals(text, problems.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number, text);
    }

    @Override
    public String toString() {
        return "Problems{" +
                "number=" + number +
                ", text='" + text + '\'' +
                '}';
    }
}
