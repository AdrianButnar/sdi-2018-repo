package ro.ubb.lab11.core.model.validators;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;

}
