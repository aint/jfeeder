/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Olexandr Tyshkovets <olexandr.tyshkovets@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.aint.jfeeder.model.dao;

import java.util.List;

import com.github.aint.jfeeder.model.entity.Entity;

/**
 * This interface represents basic persistence methods for all entity objects.
 * 
 * @author Olexandr Tyshkovets
 * 
 * @param <T>
 *            the type of entity object
 */
public interface GenericDao<T extends Entity> {
    /**
     * Finds the entity object for the given {@code id}.
     * 
     * @param id
     *            the entity's primary identifier
     * @return the entity with the given {@code id} or {@code null}
     */
    T get(Long id);

    /**
     * Finds the entity object for the given {@code uuid}.
     * 
     * @param uuid
     *            the entity's unique id
     * @return the entity with the given {@code uuid} or {@code null}
     */
    T get(String uuid);

    /**
     * Returns all entities.
     * 
     * @return the list of entities
     */
    List<T> getAll();

    /**
     * Saves the given {@code entity} to the data source.
     * 
     * @param entity
     *            the entity object that will be saved
     */
    void save(T entity);

    /**
     * Saves or updates the given {@code entity} to the data source. If the object has no persistent identifier, save
     * it, otherwise update it.
     * 
     * @param entity
     *            the entity object that will be saved or updated
     */
    void saveOrUpdate(T entity);

    /**
     * Checks the existence of entity by the given {@code id}.
     * 
     * @param id
     *            the entity's primary identifier
     * @return {@code true} if entity with the given id exists; {@code false} otherwise
     */
    boolean isExist(Long id);

    /**
     * Returns the amount of entities.
     * 
     * @return the amount of entities
     */
    Long getCount();

    /**
     * Deletes the entity object by the given {@code id}.
     * 
     * @param id
     *            the entity's primary identifier
     */
    void delete(Long id);

    /**
     * Deletes the given entity object.
     * 
     * @param entity
     *            the entity that will be deleted
     */
    void delete(T entity);

}
