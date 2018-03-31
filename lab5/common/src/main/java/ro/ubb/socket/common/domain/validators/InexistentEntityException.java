package ro.ubb.socket.common.domain.validators;

public class InexistentEntityException extends ValidatorException {
    public InexistentEntityException(String message) {
        super(message);
    }

}