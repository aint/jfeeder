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
package com.github.aint.jfeeder.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Defines the common abstract class for all entity objects. Ensures uniqueness to objects.
 * 
 * @author Olexandr Tyshkovets
 */
@MappedSuperclass
public abstract class Entity implements Serializable {
    private static final long serialVersionUID = 1696331578919707541L;

    private Long id;
    private String uuid = java.util.UUID.randomUUID().toString();

    /**
     * @return the primary key of the persistent entity
     */
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    /**
     * Sets the primary key of the entity. Is used as persistent identifier.
     * 
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the unique id
     * @see Entity#equals(Object)
     * @see Entity#hashCode()
     */
    @Column(name = "UUID", unique = true, nullable = false)
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the unique id to the entity. Is used for {@code hashCode()} and {@code equals()} methods.
     * 
     * @param uuid
     *            the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Check whether the entity has been persisted or not.
     * 
     * @return {@code true} if the entity is persisted, {@code else} otherwise
     */
    @Transient
    public boolean isPersistent() {
        return getId() != 0;
    }

    /**
     * Indicates whether some other object is "equal to" this one. Result based on {@code uuid}.
     * 
     * @param obj
     *            the reference object with which to compare.
     * @return {@code true} if this object is the same as the {@code obj} argument; {@code false} otherwise
     * @see Entity#hashCode()
     * @see Entity#getUuid()
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(getClass().isInstance(obj))) {
            return false;
        }
        return uuid.equals(((Entity) obj).uuid);
    }

    /**
     * Returns a hash code value for the object. Result based on {@code uuid}.
     * 
     * @return a hash code value for this object
     * @see Entity#equals(Object)
     * @see Entity#getUuid()
     */
    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

}
