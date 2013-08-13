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

import com.github.aint.jfeeder.model.dao.FeedEntryDao;
import com.github.aint.jfeeder.model.entity.Feed;
import com.github.aint.jfeeder.model.entity.FeedEntry;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

/**
 * @author Olexandr Tyshkovets
 * @see FeedEntryHibernateDao
 */
public class FeedEntryHibernateDaoTest extends ParentTest {
    private static final Long FEED_ENTRY_ID = 1L;
    private static final Long UNEXISTING_FEED_ENTRY_ID = -1L;

    private static final String FEED_ENTRY_UUID = "8h4ne2l1-b20jr-9d63ls-0cz2-lk730fnmqp1";
    private static final String UNEXISTING_FEED_ENTRY_UUID = "0000-0000-0000-0000-0000";

    private static final Long DELETED_FEED_ENTRY_ID = 2L;
    private static final Long FEED_ENTRY_LIST_SIZE = 2L;
    private static final String FEED_ENTRY_UPDATED_TITLE = "New Title";

    private static final String NEW_FEED_ENTRY_UUID = "8h4ne2l1-b20jr-9d63ls-0cz2-lk730fnmqp3";
    private static final String NEW_FEED_ENTRY_AUTHOR = "Interesting Author";
    private static final String NEW_FEED_ENTRY_DESCRIPTION = "Truth About Universe";
    private static final String NEW_FEED_ENTRY_LINK = "http://sciencearticle.com/1";
    private static final String NEW_FEED_ENTRY_DATE = "2009-07-27";
    private static final String NEW_FEED_ENTRY_TITLE = "Science Article 1";
    private static final Long NEW_FEED_ENTRY_FEED_ID = 1L;

    @Autowired
    private FeedEntryDao feedEntryDao;

    /* common methods */

    @Test
    public void get() {
        assertEquals(FEED_ENTRY_ID, feedEntryDao.get(FEED_ENTRY_ID).getId());
    }

    @Test
    public void getNotFound() {
        assertNull(feedEntryDao.get(UNEXISTING_FEED_ENTRY_ID));
    }

    @Test
    public void getByUuid() {
        assertEquals(FEED_ENTRY_UUID, feedEntryDao.get(FEED_ENTRY_UUID).getUuid());
    }

    @Test
    public void getByUuidNotFound() {
        assertNull(feedEntryDao.get(UNEXISTING_FEED_ENTRY_UUID));
    }

    @Test
    public void getAll() {
        assertEquals(FEED_ENTRY_LIST_SIZE.intValue(), feedEntryDao.getAll().size());
    }

    @Test
    @ExpectedDatabase(value = "/com/github/aint/jfeeder/model/dao/hibernate/FeedEntryHibernateDaoTest.save.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void save() throws Exception {
        FeedEntry feedEntry = getFeedEntry();
        feedEntry.setUuid(NEW_FEED_ENTRY_UUID);
        feedEntryDao.save(feedEntry);
    }

    @Test
    @ExpectedDatabase(value = "/com/github/aint/jfeeder/model/dao/hibernate/FeedEntryHibernateDaoTest.saveOrUpdate.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void saveOrUpdate() {
        FeedEntry feedEntry = (FeedEntry) session.get(FeedEntry.class, FEED_ENTRY_ID);
        feedEntry.setTitle(FEED_ENTRY_UPDATED_TITLE);
        feedEntryDao.saveOrUpdate(feedEntry);
    }

    @Test
    public void isExist() {
        assertTrue(feedEntryDao.isExist(FEED_ENTRY_ID));
    }

    @Test
    public void isExistNotFound() {
        assertFalse(feedEntryDao.isExist(UNEXISTING_FEED_ENTRY_ID));
    }

    @Test
    public void getCount() {
        assertEquals(feedEntryDao.getCount(), FEED_ENTRY_LIST_SIZE);
    }

    @Test
    @ExpectedDatabase(value = "/com/github/aint/jfeeder/model/dao/hibernate/FeedEntryHibernateDaoTest.delete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void delete() throws Exception {
        FeedEntry feedEntry = getFeedEntry();
        feedEntry.setId(DELETED_FEED_ENTRY_ID);
        feedEntryDao.delete(feedEntry);
        session.flush();
    }

    @Test
    @ExpectedDatabase(value = "/com/github/aint/jfeeder/model/dao/hibernate/FeedEntryHibernateDaoTest.delete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteById() {
        feedEntryDao.delete(DELETED_FEED_ENTRY_ID);
    }

    private FeedEntry getFeedEntry() throws ParseException {
        return new FeedEntry(
                NEW_FEED_ENTRY_AUTHOR,
                NEW_FEED_ENTRY_DESCRIPTION,
                NEW_FEED_ENTRY_LINK,
                new SimpleDateFormat("yyyy-MM-dd").parse(NEW_FEED_ENTRY_DATE),
                NEW_FEED_ENTRY_TITLE,
                null,
                (Feed) session.get(Feed.class, NEW_FEED_ENTRY_FEED_ID));
    }

}
