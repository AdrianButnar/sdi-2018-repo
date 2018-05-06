package ro.ubb.lab8.core.model.validators;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;

}
