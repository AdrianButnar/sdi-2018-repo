package ro.ubb.laboratory.domain.validators;

public class EntityNonExistentException extends ValidatorException {
    public EntityNonExistentException(String message) {
        super(message);
    }

}