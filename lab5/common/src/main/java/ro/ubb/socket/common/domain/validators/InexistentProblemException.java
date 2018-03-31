package ro.ubb.socket.common.domain.validators;

public class InexistentProblemException extends ValidatorException{
    public InexistentProblemException(String message) {
        super(message);
    }
}
