package ro.ubb.lab6.common.domain.validators;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;

}
