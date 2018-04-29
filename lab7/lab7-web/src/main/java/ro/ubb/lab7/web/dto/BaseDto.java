package ro.ubb.lab7.web.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Created by radu.
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseDto implements Serializable {
    private Long id;

    public void setId(Long Id) //Lombok has problems
    {
        id = Id;
    }
    public Long getId()
    {
        return id;
    }
}
