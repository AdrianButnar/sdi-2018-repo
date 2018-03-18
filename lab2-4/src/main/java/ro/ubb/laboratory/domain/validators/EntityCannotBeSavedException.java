package ro.ubb.laboratory.domain.validators;

public class EntityCannotBeSavedException extends ValidatorException {

    public EntityCannotBeSavedException(String message) {
        super(message);
    }
}
