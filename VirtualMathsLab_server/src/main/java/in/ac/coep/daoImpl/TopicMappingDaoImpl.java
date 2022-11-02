/**
 * 
 */
package in.ac.coep.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ac.coep.dao.TopicMappingDao;
import in.ac.coep.entity.TopicMapping;

/**
 * @author Prashant
 *
 */

@Repository
@Transactional
public class TopicMappingDaoImpl implements TopicMappingDao{

	
	private static final Logger LOGGER = Logger.getLogger(TopicMappingDaoImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public TopicMapping getTopicParentByTopicId(long topicId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		TopicMapping topicMapping = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TopicMapping.class);
			criteria.add(Restrictions.eq("topic.topicId", topicId));
			criteria.add(Restrictions.eq("status", "1")); // 1 represents parent
			topicMapping = (TopicMapping) criteria.uniqueResult();
			LOGGER.debug("Parent Topic get successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return topicMapping;

		} catch (HibernateException e) {
			LOGGER.error("Parent Topic get failed");
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<TopicMapping> getTopicChildsByTopicId(long topicId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<TopicMapping> childTopics = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TopicMapping.class, "tm");
			detachedCriteria.setFetchMode("tm.topic", FetchMode.JOIN);
			detachedCriteria.createAlias("tm.topic", "topicMapping", CriteriaSpecification.LEFT_JOIN);
			detachedCriteria.add(Restrictions.eq("tm.parent.topicId", topicId));
			detachedCriteria.add(Restrictions.eq("tm.status", "1"));
			detachedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			childTopics = detachedCriteria.getExecutableCriteria(session).list();

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return childTopics;

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

}
