package ro.ubb.lab6.common.domain.validators;

public class InexistentEntityException extends ValidatorException {
    public InexistentEntityException(String message) {
        super(message);
    }

}