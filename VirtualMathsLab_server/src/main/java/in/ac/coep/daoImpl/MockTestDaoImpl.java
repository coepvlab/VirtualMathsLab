package in.ac.coep.daoImpl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ac.coep.dao.MockTestDao;
import in.ac.coep.entity.PerformanceTag;
import in.ac.coep.entity.TestInstanceStatistics;
import in.ac.coep.entity.User;

@Repository
@Transactional
public class MockTestDaoImpl implements MockTestDao {
	
	private static final Logger LOGGER = Logger.getLogger(MockTestDaoImpl.class);

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<TestInstanceStatistics> getTestByUserAndTopicId(User user, long topicId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceStatistics.class, "tis");
			
			criteria.add(Restrictions.eq("topic.topicId", topicId));
			criteria.add(Restrictions.eq("user.userId", user.getUserId()));
//			criteria.add(Restrictions.eq("Status", "C"));
			criteria.addOrder(Order.desc("TestInstanceStatisticId"));
//			criteria.setMaxResults(3);
			

			List<TestInstanceStatistics> tisList = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return tisList;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	@Override
	public PerformanceTag getRecordByVariationNo(int varNo, User user) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(PerformanceTag.class);
			
			criteria.add(Restrictions.eq("variationNo", varNo));
			criteria.add(Restrictions.eq("user.userId", user.getUserId()));
			

			PerformanceTag pt = (PerformanceTag) criteria.uniqueResult();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return pt;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	@Override
	public void updatePerformanceTag(PerformanceTag pt) throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			session.saveOrUpdate(pt);

			LOGGER.debug("addAnswer  successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.error("addAnswer failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	
	
	
	
	
}
