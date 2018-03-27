/*
 * Copyright (c) 2018. Gandaceii
 *
 * MIT
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package ro.ubb.laboratory.repository;

import ro.ubb.laboratory.domain.BaseEntity;
import ro.ubb.laboratory.domain.validators.EntityCannotBeSavedException;
import ro.ubb.laboratory.domain.validators.EntityNonExistentException;
import ro.ubb.laboratory.domain.validators.InexistentStudentException;
import ro.ubb.laboratory.domain.validators.StudentCannotBeSavedException;

import java.util.Optional;

/**
 *
 * @author Alexandru Buhai
 * @version 1.0.0
 * @since 05/03/2018
 */
public interface Repository<ID, T extends BaseEntity<ID>> {

    Optional<T> findOne(ID id);

    Iterable<T> findAll();

    Optional<T> save(T entity) throws EntityCannotBeSavedException;

    Optional<T> remove(ID id) throws EntityNonExistentException; 

//    Optional<T> update(T entity) throws Exception;

}
