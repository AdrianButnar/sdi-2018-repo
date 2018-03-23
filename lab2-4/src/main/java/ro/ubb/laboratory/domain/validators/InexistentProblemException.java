package ro.ubb.laboratory.domain.validators;

public class InexistentProblemException extends ValidatorException{
    public InexistentProblemException(String message) {
        super(message);
    }
}
