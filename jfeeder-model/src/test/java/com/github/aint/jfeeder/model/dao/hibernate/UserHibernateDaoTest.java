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

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.github.aint.jfeeder.model.dao.UserDao;
import com.github.aint.jfeeder.model.entity.User;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

/**
 * @author Olexandr Tyshkovets
 * @see UserHibernateDao
 */
public class UserHibernateDaoTest extends ParentTest {
    private static final Long USER_ID = 1L;
    private static final Long UNEXISTING_USER_ID = -1L;

    private static final String USER_UUID = "9cd75bac-102b-4aae-a185-c075d0a47891";
    private static final String UNEXISTING_USER_UUID = "0000-0000-0000-0000-0000";

    private static final String USER_USERNAME = "ronaldo";
    private static final String UNEXISTING_USERNAME = "blah-blah-blah";

    private static final String USER_EMAIL = "c.ronaldo@gmail.com";
    private static final String UNEXISTING_USER_EMAIL = "blah-blah@blah.com";

    private static final Long USER_COUNT = 2L;
    private static final Long USER_ID_TO_DELETE = 2L;
    private static final String USER_UPDATED_USERNAME = "new_username";

    private static final String NEW_USER_UUID = "9cd75bac-102b-4aae-a185-c075d0a47893";
    private static final String NEW_USER_USERNAME = "f.lampard";
    private static final String NEW_USER_PASSWORD = "111111";
    private static final String NEW_USER_EMAIL = "f.lampard@gmail.com";

    @Autowired
    private UserDao userDao;

    /* specific methods */

    @Test
    public void getByUsername() {
        assertEquals(USER_USERNAME, userDao.getByUsername(USER_USERNAME).getUsername());
    }

    @Test
    public void getByUsernameNotFound() {
        assertNull(userDao.getByUsername(UNEXISTING_USERNAME));
    }

    @Test
    public void getByEmail() throws Exception {
        assertEquals(USER_EMAIL, userDao.getByEmail(USER_EMAIL).getEmail());
    }

    @Test
    public void getByEmailNotFound() throws Exception {
        assertNull(userDao.getByEmail(UNEXISTING_USER_EMAIL));
    }

    /* common methods */

    @Test
    public void get() {
        assertEquals(USER_ID, userDao.get(USER_ID).getId());
    }

    @Test
    public void getNotFound() {
        assertNull(userDao.get(UNEXISTING_USER_ID));
    }

    @Test
    public void getByUuid() {
        assertEquals(USER_UUID, userDao.get(USER_UUID).getUuid());
    }

    @Test
    public void getByUuidNotFound() {
        assertNull(userDao.get(UNEXISTING_USER_UUID));
    }

    @Test
    public void getAll() {
        assertEquals(USER_COUNT.intValue(), userDao.getAll().size());
    }

    @Test
    @ExpectedDatabase(value = "/com/github/aint/jfeeder/model/dao/hibernate/UserHibernateDaoTest.save.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void save() {
        User user = new User(NEW_USER_USERNAME, NEW_USER_EMAIL, NEW_USER_PASSWORD);
        user.setUuid(NEW_USER_UUID);
        userDao.save(user);
    }

    @Test
    @ExpectedDatabase(value = "/com/github/aint/jfeeder/model/dao/hibernate/UserHibernateDaoTest.saveOrUpdate.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void saveOrUpdate() {
        User user = (User) session.get(User.class, USER_ID);
        user.setUsername(USER_UPDATED_USERNAME);
        userDao.saveOrUpdate(user);
    }

    @Test
    public void isExist() {
        assertTrue(userDao.isExist(USER_ID));
    }

    @Test
    public void isExistNotFound() {
        assertFalse(userDao.isExist(UNEXISTING_USER_ID));
    }

    @Test
    public void getCount() {
        assertEquals(userDao.getCount(), USER_COUNT);
    }

    @Test
    @ExpectedDatabase(value = "/com/github/aint/jfeeder/model/dao/hibernate/UserHibernateDaoTest.delete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void delete() {
        User user = new User(NEW_USER_USERNAME, NEW_USER_EMAIL, NEW_USER_PASSWORD);
        user.setId(USER_ID_TO_DELETE);
        userDao.delete(user);
        session.flush();
    }

    @Test
    @ExpectedDatabase(value = "/com/github/aint/jfeeder/model/dao/hibernate/UserHibernateDaoTest.delete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteById() {
        userDao.delete(USER_ID_TO_DELETE);
    }

}
