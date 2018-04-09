package ro.ubb.laboratory.domain.validators;

public class InexistentEntityException extends ValidatorException {
    public InexistentEntityException(String message) {
        super(message);
    }

}