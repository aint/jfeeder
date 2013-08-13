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
package com.github.aint.jfeeder.model.dao.hibernate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.aint.jfeeder.model.dao.GenericDao;
import com.github.aint.jfeeder.model.entity.Entity;

/**
 * The basic hibernate implementation of the {@code GenericDao} interface.
 * 
 * @author Olexandr Tyshkovets
 * 
 * @param <T>
 *            the type of entity object
 */
public abstract class AbstractHibernateDao<T extends Entity> implements GenericDao<T> {
    private final Class<T> clazz = getType();
    private SessionFactory sessionFactory;

    /**
     * @param sessionFactory
     *            the sessionFactory to set
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public T get(Long id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public T get(String uuid) {
        return (T) getCurrentSession()
                .createQuery("FROM " + clazz.getName() + " WHERE uuid = ?")
                .setString(0, uuid)
                .uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        return getCurrentSession()
                .createQuery("FROM " + clazz.getName())
                .list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(T entity) {
        getCurrentSession().persist(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrUpdate(T entity) {
        getCurrentSession().saveOrUpdate(entity);
        getCurrentSession().flush();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExist(Long id) {
        return getCurrentSession().get(clazz.getName(), id) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getCount() {
        return (Long) getCurrentSession()
                .createQuery("SELECT COUNT(*) FROM " + clazz.getName())
                .iterate()
                .next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        getCurrentSession()
                .createQuery("DELETE " + clazz.getName() + " WHERE id = ?")
                .setLong(0, id)
                .executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    /**
     * @return the current hibernate session
     */
    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * @return parameterized type of entity
     */
    @SuppressWarnings("unchecked")
    protected Class<T> getType() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
