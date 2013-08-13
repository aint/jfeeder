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

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.github.aint.jfeeder.model.dao.FeedDao;
import com.github.aint.jfeeder.model.entity.Feed;
import com.github.aint.jfeeder.model.entity.User;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

/**
 * @author Olexandr Tyshkovets
 * @see FeedHibernateDao
 */
public class FeedHibernateDaoTest extends ParentTest {
    private static final Long FEED_ID = 1L;
    private static final Long UNEXISTING_FEED_ID = -1L;

    private static final String FEED_UUID = "eh74hfs7-5nf2-l421l-8a21-nf307fndkw1";
    private static final String UNEXISTING_FEED_UUID = "0000-0000-0000-0000-0000";

    private static final Long FEED_ID_TO_DELETE = 2L;
    private static final Long FEED_COUNT = 2L;
    private static final String FEED_UPDATED_TITLE = "New Title";

    private static final String NEW_FEED_UUID = "eh74hfs7-5nf2-l421l-8a21-nf307fndkw3";
    private static final String NEW_FEED_DESCRIPTION = "New Interesting Feed";
    private static final String NEW_FEED_TYPE = "rss_2.0";
    private static final String NEW_FEED_LANGUAGE = "English";
    private static final String NEW_FEED_LINK = "http://newfeed.com";
    private static final String NEW_FEED_DATE = "2013-09-01";
    private static final String NEW_FEED_TITLE = "New Feed";
    private static final Long NEW_FEED_USER_ID = 1L;

    @Autowired
    private FeedDao feedDao;

    /* common methods */

    @Test
    public void get() {
        assertEquals(FEED_ID, feedDao.get(FEED_ID).getId());
    }

    @Test
    public void getNotFound() {
        assertNull(feedDao.get(UNEXISTING_FEED_ID));
    }

    @Test
    public void getByUuid() {
        assertEquals(FEED_UUID, feedDao.get(FEED_UUID).getUuid());
    }

    @Test
    public void getByUuidNotFound() {
        assertNull(feedDao.get(UNEXISTING_FEED_UUID));
    }

    @Test
    public void getAll() {
        assertEquals(FEED_COUNT.intValue(), feedDao.getAll().size());
    }

    @Test
    @ExpectedDatabase(value = "/com/github/aint/jfeeder/model/dao/hibernate/FeedHibernateDaoTest.save.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void save() throws Exception {
        Feed feed = getFeed();
        feed.setUuid(NEW_FEED_UUID);
        feedDao.save(feed);
    }

    @Test
    @ExpectedDatabase(value = "/com/github/aint/jfeeder/model/dao/hibernate/FeedHibernateDaoTest.saveOrUpdate.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void saveOrUpdate() throws Exception {
        Feed feed = (Feed) session.get(Feed.class, FEED_ID);
        feed.setTitle(FEED_UPDATED_TITLE);
        feedDao.saveOrUpdate(feed);
    }

    @Test
    public void isExist() {
        assertTrue(feedDao.isExist(FEED_ID));
    }

    @Test
    public void isExistNotFound() {
        assertFalse(feedDao.isExist(UNEXISTING_FEED_ID));
    }

    @Test
    public void getCount() {
        assertEquals(feedDao.getCount(), FEED_COUNT);
    }

    @Test
    @ExpectedDatabase(value = "/com/github/aint/jfeeder/model/dao/hibernate/FeedHibernateDaoTest.delete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void delete() throws Exception {
        Feed feed = getFeed();
        feed.setId(FEED_ID_TO_DELETE);
        feedDao.delete(feed);
        session.flush();
    }

    @Test
    @ExpectedDatabase(value = "/com/github/aint/jfeeder/model/dao/hibernate/FeedHibernateDaoTest.delete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteById() {
        feedDao.delete(FEED_ID_TO_DELETE);
    }

    private Feed getFeed() throws ParseException {
        return new Feed(
                "",
                NEW_FEED_DESCRIPTION,
                "",
                NEW_FEED_TYPE,
                NEW_FEED_LANGUAGE,
                NEW_FEED_LINK,
                new SimpleDateFormat("yyyy-MM-dd").parse(NEW_FEED_DATE),
                NEW_FEED_TITLE,
                (User) session.get(User.class, NEW_FEED_USER_ID));
    }

}
