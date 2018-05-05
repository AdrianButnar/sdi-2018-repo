package ro.ubb.lab8.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProblemDto  extends BaseDto{
    private int number;
    private String text;

    @Override
    public String toString() {
        return "ProblemDto{" +
                "number=" + number +
                ", text='" + text + '\'' +
                "} "+ super.toString();
    }
}

