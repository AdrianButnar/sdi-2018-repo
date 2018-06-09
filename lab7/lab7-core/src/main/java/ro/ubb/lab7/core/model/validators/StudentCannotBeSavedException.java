package ro.ubb.lab7.core.model.validators;

public class StudentCannotBeSavedException extends ValidatorException {

    public StudentCannotBeSavedException(String message) {
        super(message);
    }
}
