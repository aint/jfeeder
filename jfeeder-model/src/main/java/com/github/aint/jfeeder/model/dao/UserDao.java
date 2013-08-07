package com.github.aint.jfeeder.model.dao;

import com.github.aint.jfeeder.model.entity.User;

/**
 * This interface represents persistence methods to operate with a {@code User} entity.
 * 
 * @author Olexandr Tyshkovets
 * @see User
 */
public interface UserDao extends GenericDao<User> {
    /**
     * Finds a user by the given {@code username}.
     * 
     * @param username
     *            the requested user's username
     * @return the user with the given {@code username} or {@code null} if not found
     */
    User getByUsername(String username);

    /**
     * Finds a user by the given {@code email}.
     * 
     * @param email
     *            the requested user's email
     * @return the user with the given {@code email} or {@code null} if not found
     */
    User getByEmail(String email);

}
