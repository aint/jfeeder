package com.github.aint.jfeeder.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Stores the information about a user.
 * 
 * @author Olexandr Tyshkovets
 */
@javax.persistence.Entity
@Table(name = "USER")
@AttributeOverride(name = "id", column = @Column(name = "USER_ID", updatable = false, nullable = false))
public class User extends Entity {
    private static final long serialVersionUID = -5434689193714179402L;

    private String username;
    private String email;
    private String password;
    private List<Feed> feeds = new ArrayList<Feed>();

    /**
     * The default constructor for hibernate.
     */
    protected User() {
    }

    /**
     * Constructs a new user with specified arguments.
     * 
     * @param username
     *            the user's username
     * @param email
     *            the user's email
     * @param password
     *            the user's password
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * @return the username
     */
    @Column(name = "USERNAME", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    @Column(name = "EMAIL", nullable = false)
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    @Column(name = "PASSWORD", nullable = false)
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the feeds
     */
    @OneToMany(mappedBy = "user")
    public List<Feed> getFeeds() {
        return feeds;
    }

    /**
     * @param feeds
     *            the feeds to set
     */
    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }

    /**
     * Returns a string representation of the feed.
     * 
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return getClass().getName()
                + "[id=" + getId()
                + ", uuid=" + getUuid()
                + ", username=" + username
                + ", email=" + email
                + ", password=" + password
                + "]";
    }
}
