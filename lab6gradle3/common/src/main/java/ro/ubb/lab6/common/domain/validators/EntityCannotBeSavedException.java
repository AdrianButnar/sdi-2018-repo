package ro.ubb.lab6.common.domain.validators;

public class EntityCannotBeSavedException extends ValidatorException {

    public EntityCannotBeSavedException(String message) {
        super(message);
    }
}
