package ro.ubb.lab11.web.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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

