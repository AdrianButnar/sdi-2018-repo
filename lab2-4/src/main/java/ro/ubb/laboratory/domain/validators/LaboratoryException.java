package ro.ubb.laboratory.domain.validators;

public class LaboratoryException extends RuntimeException{

    public LaboratoryException(String message) {
        super(message);
    }

    public LaboratoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public LaboratoryException(Throwable cause) {
        super(cause);
    }
}
