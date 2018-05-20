package ro.ubb.lab11.core.model.validators;

public class InexistentEntityException extends ValidatorException {
    public InexistentEntityException(String message) {
        super(message);
    }

}