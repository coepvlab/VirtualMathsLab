
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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ac.coep.dao.TopicDao;
import in.ac.coep.entity.Topic;
import in.ac.coep.entity.TopicMapping;

/**
 * @author Prashant
 *
 */
@Repository
@Transactional
public class TopicDaoImpl implements TopicDao {

	private static final Logger LOGGER = Logger.getLogger(TopicDaoImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void save(Topic topic) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			session.save(topic);

			LOGGER.debug("Topic saved successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.error("Topic saved failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@Override
	public Topic getTopicByTopicId(long parentId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		Topic topic = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(Topic.class);
			criteria.add(Restrictions.eq("topicId", parentId));

			topic = (Topic) criteria.uniqueResult();

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return topic;

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@Override
	public void updateTopicMpping(Topic topic) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			session.saveOrUpdate(topic);

			LOGGER.debug("Topic saved successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.error("Topic saved failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@Override
	public void saveTopicMapping(TopicMapping mapping) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			session.save(mapping);

			LOGGER.debug("Topic mapping saved successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.error("Topic mapping saved failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getAllTopicDetails() throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<Topic> topics = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(Topic.class);

			topics = criteria.list();

			LOGGER.debug("topic fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.debug("topic fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

		}

		return topics;
	}

	@Override
	public Topic getTopicByTopicNo(String topicNo) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		Topic topic = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(Topic.class);
			criteria.add(Restrictions.eq("topicNo", topicNo));

			topic = (Topic) criteria.uniqueResult();

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return topic;

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TopicMapping> getTopicMappingByTopicId(long parentId, String status) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<TopicMapping> topicMapping = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TopicMapping.class);
			criteria.add(Restrictions.eq("parent.topicId", parentId));
			criteria.add(Restrictions.eq("status", status));

			topicMapping = criteria.list();

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return topicMapping;

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@Override
	public void updateTopic(Topic tp1) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			session.update(tp1);

			LOGGER.debug("Topic updated successfully");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.error("Topic update failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getAllTopicByLikeFilterTopicNo(String topicNo) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<Topic> topics = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(Topic.class);
			criteria.add(Restrictions.like("topicNo", topicNo, MatchMode.START));

			topics = criteria.list();

			LOGGER.debug("topic fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.debug("topic fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();
		}

		return topics;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TopicMapping> getTopicMappingByTopicIdWithParent(long topicId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<TopicMapping> topicMapping = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TopicMapping.class);
			criteria.add(Restrictions.eq("parent.topicId", topicId));

			topicMapping = criteria.list();

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return topicMapping;

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@Override
	public void deleteTopicMapping(TopicMapping tm) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			session.delete(tm);

			LOGGER.debug("Topic mapping deleted successfully");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.error("Topic deleted failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@Override
	public void deleteTopic(Topic topic) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			session.delete(topic);

			LOGGER.debug("Topic deleted successfully");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.error("Topic deleted failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TopicMapping> getTopicMappingByTopicIdWithTopic(long topicId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<TopicMapping> topicMapping = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TopicMapping.class);
			criteria.add(Restrictions.eq("topic.topicId", topicId));

			topicMapping = criteria.list();

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return topicMapping;

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TopicMapping> getAllTopicMappingByStatus(String status) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<TopicMapping> topicMapping = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TopicMapping.class);
			criteria.add(Restrictions.eq("status", status));

			topicMapping = criteria.list();

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return topicMapping;

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@Override
	public TopicMapping checkTopicMappingAlreadyExist(String status, Topic pTopic, Topic topic) throws Exception {
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
			criteria.add(Restrictions.eq("status", status));
			criteria.add(Restrictions.eq("parent.topicId", pTopic.getTopicId()));
			criteria.add(Restrictions.eq("topic.topicId", topic.getTopicId()));

			topicMapping = (TopicMapping) criteria.uniqueResult();

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return topicMapping;

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@Override
	public void removeToppingMapping(TopicMapping tm) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			session.delete(tm);

			LOGGER.debug("Topic mapping removed successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.error("Topic deletion failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@Override
	public TopicMapping getTopicMappingByTMid(long tmId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		TopicMapping tm = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TopicMapping.class);
			criteria.add(Restrictions.eq("topicMappingId", tmId));

			tm = (TopicMapping) criteria.uniqueResult();

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return tm;

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getAllTopicDetailsByLevel(String topicLevel) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<Topic> topic = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(Topic.class);
			criteria.add(Restrictions.eq("level", topicLevel));

			topic = criteria.list();

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return topic;

		} catch (HibernateException e) {
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
	public List<TopicMapping> getChildTopicsByTopicId(long topicId) throws Exception {
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

	@Override
	public TopicMapping getParentTopicsByTopicId(long topicId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		TopicMapping tm = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TopicMapping.class);
			criteria.add(Restrictions.eq("topic.topicId", topicId));
			criteria.add(Restrictions.eq("status", "1")); // 1 is to get parent only, 2 is to get prerequisite

			tm = (TopicMapping) criteria.uniqueResult();

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return tm;

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TopicMapping> getPrerequisiteTopicsByTopicId(long topicId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<TopicMapping> tm = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TopicMapping.class);
			criteria.add(Restrictions.eq("topic.topicId", topicId));
			criteria.add(Restrictions.eq("status", "2")); // 1 is to get parent only, 2 is to get prerequisite
//			tm = (TopicMapping) criteria.uniqueResult();
			tm =  criteria.list();
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return tm;

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getAllVerticalListByTopicLevel(String topicLevel) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<Topic> topics = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(Topic.class);
			criteria.add(Restrictions.eq("level", topicLevel));

			topics = criteria.list();

			LOGGER.debug("get topics fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.debug("topics fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();
		}

		return topics;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getAllTopicDetails1() throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<Topic> topics = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(Topic.class);
			criteria.add(Restrictions.eq("level", "01"));

			topics = criteria.list();

			LOGGER.debug("topic fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.debug("topic fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

		}

		return topics;
	}

}
