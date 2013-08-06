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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Stores the information about a feed entry.
 * 
 * @author Olexandr Tyshkovets
 */
@javax.persistence.Entity
@Table(name = "FEED_ENTRY")
@AttributeOverride(name = "id", column = @Column(name = "FEED_ENTRY_ID", updatable = false, nullable = false))
public class FeedEntry extends Entity {
    private static final long serialVersionUID = -3791405957538149629L;

    private String author;
    private String description;
    private String link;
    private Date publishedDate;
    private String title;
    private List<String> categories = new ArrayList<String>();
    private Feed feed;

    /**
     * The default constructor for hibernate.
     */
    protected FeedEntry() {
    }

    /**
     * Constructs a new feed entry with specified arguments.
     * 
     * @param author
     *            the feed entry's author
     * @param description
     *            the feed entry's description
     * @param link
     *            the feed entry's link
     * @param publishedDate
     *            the feed entry's published date
     * @param title
     *            the feed entry's title
     * @param categories
     *            the feed entry's categories
     * @param feed
     *            the entry's feed
     */
    public FeedEntry(String author, String description, String link, Date publishedDate, String title,
            List<String> categories, Feed feed) {
        this.author = author;
        this.description = description;
        this.link = link;
        this.publishedDate = publishedDate;
        this.title = title;
        this.categories = categories;
        this.feed = feed;
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
     * @return the categories
     */
    @ElementCollection
    @CollectionTable(name = "FEED_ENTRY_CATEGORY", joinColumns = @JoinColumn(name = "FEED_ENTRY_ID"))
    @Column(name = "CATEGORIES")
    public List<String> getCategories() {
        return categories;
    }

    /**
     * @param categories
     *            the categories to set
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * @return the feed
     */
    @ManyToOne
    @JoinColumn(name = "FK_FEED", nullable = false)
    public Feed getFeed() {
        return feed;
    }

    /**
     * @param feed
     *            the feed to set
     */
    public void setFeed(Feed feed) {
        this.feed = feed;
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
                + ", link=" + link
                + ", publishedDate=" + publishedDate
                + ", title=" + title
                + ", categories=" + categories
                + ", feed=" + feed
                + "]";
    }

}
