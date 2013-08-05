package com.github.aint.jfeeder.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Stores the information about a feed.
 * 
 * @author Olexandr Tyshkovets
 */
@javax.persistence.Entity
@Table(name = "FEED")
@AttributeOverride(name = "id", column = @Column(name = "FEED_ID", updatable = false, nullable = false))
public class Feed extends Entity {
    private static final long serialVersionUID = 5350524030230570660L;

    private String author;
    private String description;
    private String feedType;
    private String feedImageUrl;
    private String language;
    private String link;
    private Date publishedDate;
    private String title;
    private List<String> supportedFeedTypes = new ArrayList<String>();
    private List<FeedEntry> entries = new ArrayList<FeedEntry>();
    private User user;

    /**
     * The default constructor for hibernate.
     */
    protected Feed() {
    }

    /**
     * Constructs a new feed with specified arguments.
     * 
     * @param author
     *            the feed's author
     * @param description
     *            the feed's description
     * @param feedImageUrl
     *            the feed's image URL
     * @param feedType
     *            the feed's type
     * @param language
     *            the feed's language
     * @param link
     *            the feed's link
     * @param publishedDate
     *            the feed's published date
     * @param title
     *            the feed's title
     * @param user
     *            the feed's user
     */
    public Feed(String author, String description, String feedImageUrl, String feedType, String language, String link,
            Date publishedDate, String title, User user) {
        this.author = author;
        this.description = description;
        this.feedImageUrl = feedImageUrl;
        this.feedType = feedType;
        this.language = language;
        this.link = link;
        this.publishedDate = publishedDate;
        this.title = title;
        this.user = user;
    }

    /**
     * @return the author
     */
    @Column(name = "AUTHOR")
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the description
     */
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the feed type
     */
    @Column(name = "TYPE")
    public String getFeedType() {
        return feedType;
    }

    /**
     * @param feedType
     *            the feed type to set
     */
    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    /**
     * @return the feed image URL
     */
    @Column(name = "IMAGE_URL")
    public String getFeedImageUrl() {
        return feedImageUrl;
    }

    /**
     * @param feedImageUrl
     *            the feed image URL to set
     */
    public void setFeedImageUrl(String feedImageUrl) {
        this.feedImageUrl = feedImageUrl;
    }

    /**
     * @return the language
     */
    @Column(name = "LANGUAGE")
    public String getLanguage() {
        return language;
    }

    /**
     * @param language
     *            the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the link
     */
    @Column(name = "LINK")
    public String getLink() {
        return link;
    }

    /**
     * @param link
     *            the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the published date
     */
    @Column(name = "PUBLISHED_DATE")
    public Date getPublishedDate() {
        return publishedDate;
    }

    /**
     * @param publishedDate
     *            the published date to set
     */
    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     * @return the title
     */
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the supported feed types
     */
    @ElementCollection
    @CollectionTable(name = "FEED_TYPE", joinColumns = @JoinColumn(name = "FEED_ID"))
    @Column(name = "SUPPORTED_TYPES")
    public List<String> getSupportedFeedTypes() {
        return supportedFeedTypes;
    }

    /**
     * @param supportedFeedTypes
     *            the supported feed types to set
     */
    public void setSupportedFeedTypes(List<String> supportedFeedTypes) {
        this.supportedFeedTypes = supportedFeedTypes;
    }

    /**
     * @return the feed entries
     */
    @OneToMany(mappedBy = "feed")
    public List<FeedEntry> getEntries() {
        return entries;
    }

    /**
     * @param entries
     *            the feed entries to set
     */
    public void setEntries(List<FeedEntry> entries) {
        this.entries = entries;
    }

    /**
     * @return the user
     */
    @ManyToOne
    @JoinColumn(name = "FK_USER", nullable = false)
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
        this.user = user;
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
                + ", author=" + author
                + ", description=" + description
                + ", feedType=" + feedType
                + ", feedImageUrl=" + feedImageUrl
                + ", language=" + language
                + ", link=" + link
                + ", publishedDate=" + publishedDate
                + ", title=" + title
                + ", supportedFeedTypes=" + supportedFeedTypes
                + ", user.username=" + user.getUsername()
                + "]";
    }

}
