package ro.ubb.laboratory.domain.validators;

public class InexistentStudentException extends ValidatorException {

    public InexistentStudentException(String message) {
        super(message);
    }

}
