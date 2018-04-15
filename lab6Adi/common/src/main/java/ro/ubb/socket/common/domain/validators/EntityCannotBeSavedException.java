package ro.ubb.socket.common.domain.validators;

public class EntityCannotBeSavedException extends ValidatorException {

    public EntityCannotBeSavedException(String message) {
        super(message);
    }
}
