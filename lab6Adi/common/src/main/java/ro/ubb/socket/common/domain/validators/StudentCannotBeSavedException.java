package ro.ubb.socket.common.domain.validators;

public class StudentCannotBeSavedException extends ValidatorException {

    public StudentCannotBeSavedException(String message) {
        super(message);
    }
}
