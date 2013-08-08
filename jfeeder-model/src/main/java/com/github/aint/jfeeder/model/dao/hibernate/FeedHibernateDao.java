package com.github.aint.jfeeder.model.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.github.aint.jfeeder.model.dao.FeedDao;
import com.github.aint.jfeeder.model.entity.Feed;

/**
 * The implementation of the {@code FeedDao} interface.
 * 
 * @author Olexandr Tyshkovets
 * @see Feed
 */
@Repository
public class FeedHibernateDao extends AbstractHibernateDao<Feed> implements FeedDao {

}
