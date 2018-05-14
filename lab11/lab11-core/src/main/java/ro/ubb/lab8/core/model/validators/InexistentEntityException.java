package ro.ubb.lab8.core.model.validators;

public class InexistentEntityException extends ValidatorException {
    public InexistentEntityException(String message) {
        super(message);
    }

}