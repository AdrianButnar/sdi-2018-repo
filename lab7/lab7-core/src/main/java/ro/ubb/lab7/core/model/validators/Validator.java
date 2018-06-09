package ro.ubb.lab7.core.model.validators;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;

}
