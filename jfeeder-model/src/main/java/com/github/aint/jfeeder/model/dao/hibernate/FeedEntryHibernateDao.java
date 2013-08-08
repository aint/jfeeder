package com.github.aint.jfeeder.model.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.github.aint.jfeeder.model.dao.FeedEntryDao;
import com.github.aint.jfeeder.model.entity.FeedEntry;

/**
 * The implementation of the {@code FeedEntryDao} interface.
 * 
 * @author Olexandr Tyshkovets
 * @see FeedEntry
 */
@Repository
public class FeedEntryHibernateDao extends AbstractHibernateDao<FeedEntry> implements FeedEntryDao {

}
