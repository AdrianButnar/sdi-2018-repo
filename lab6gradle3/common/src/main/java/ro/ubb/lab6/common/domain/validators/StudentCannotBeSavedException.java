package ro.ubb.lab6.common.domain.validators;

public class StudentCannotBeSavedException extends ValidatorException {

    public StudentCannotBeSavedException(String message) {
        super(message);
    }
}
