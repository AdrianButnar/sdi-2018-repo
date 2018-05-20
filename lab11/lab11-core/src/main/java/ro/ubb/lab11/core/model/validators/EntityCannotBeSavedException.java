package ro.ubb.lab11.core.model.validators;

public class EntityCannotBeSavedException extends ValidatorException {

    public EntityCannotBeSavedException(String message) {
        super(message);
    }
}
