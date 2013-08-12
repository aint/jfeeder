package com.github.aint.jfeeder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * The parent class for all tests.
 * 
 * @author Olexandr Tyshkovets
 */
@ContextConfiguration(locations = {
        "classpath:/com/github/aint/jfeeder/model/applicationContext-dao.xml",
        "classpath:/com/github/aint/jfeeder/model/applicationTextContext-dao.xml"
})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@Transactional
@DatabaseSetup("/com/github/aint/jfeeder/model/dao/hibernate/CommonDataset.xml")
public abstract class ParentTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;
    protected Session session;

    @BeforeMethod
    public void setUp() throws Exception {
        session = sessionFactory.getCurrentSession();
    }

}
