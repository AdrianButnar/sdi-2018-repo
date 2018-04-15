package ro.ubb.lab6.common.services;

import ro.ubb.lab6.common.domain.BaseEntity;
import ro.ubb.lab6.common.domain.validators.EntityCannotBeSavedException;
import ro.ubb.lab6.common.domain.validators.InexistentEntityException;

import java.util.Optional;

public interface Repository<ID, T extends BaseEntity<ID>> {

    /**
     * Find the entity with the given {@code id}.
     *
     * @param id
     *            must be not null.
     * @return an {@code Optional} encapsulating the entity with the given id.
     * @throws IllegalArgumentException
     *             if the given id is null.
     */
    Optional<T> findOne(ID id);

    /**
     *
     * @return all entities.
     */
    Iterable<T> findAll();

    /**
     * Saves the given entity.
     *
     * @param entity
     *            must not be null.
     * @return an {@code Optional} - null if the entity was saved otherwise (e.g. id already exists) returns the entity.
     * @throws EntityCannotBeSavedException
     *             if the given entity cannot be saved.
     */
    Optional<T> save(T entity) throws EntityCannotBeSavedException;


    /**
     * Removes the entity with the given id.
     *
     * @param id
     *            must not be null.
     * @return an {@code Optional} - null if there is no entity with the given id, otherwise the removed entity.
     * @throws IllegalArgumentException
     *             if the given id is null.
     * @throws InexistentEntityException
     *             if the entity does not exist.
     */
    Optional<T> remove(ID id) throws InexistentEntityException;

}
