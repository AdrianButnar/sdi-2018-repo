package ro.ubb.lab6.common.domain.validators;

public class InexistentProblemException extends ValidatorException{
    public InexistentProblemException(String message) {
        super(message);
    }
}
