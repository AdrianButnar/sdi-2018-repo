package ro.ubb.lab11.core.model;

import lombok.*;

import java.io.Serializable;

/**
 * Created by radu.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
class AssignmentPK implements Serializable {
    private Student student;
    private Problem problem;
}
