package com.github.aint.jfeeder.model.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.github.aint.jfeeder.model.dao.UserDao;
import com.github.aint.jfeeder.model.entity.User;

/**
 * The implementation of the {@code UserDao} interface.
 * 
 * @author Olexandr Tyshkovets
 * @see User
 */
@Repository
public class UserHibernateDao extends AbstractHibernateDao<User> implements UserDao {
    /**
     * {@inheritDoc}
     */
    @Override
    public User getByUsername(String username) {
        return (User) getCurrentSession()
                .getNamedQuery("User.getByUsername")
                .setString(0, username)
                .uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getByEmail(String email) {
        return (User) getCurrentSession()
                .getNamedQuery("User.getByEmail")
                .setString(0, email)
                .uniqueResult();
    }

}
