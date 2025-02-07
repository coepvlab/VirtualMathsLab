/**
 * 
 */
package in.ac.coep.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.TestStatisticDao;
import in.ac.coep.entity.TestInstanceStatistics;

/**
 * @author Prashant
 *
 */
@Service
public class TestStatisticDaoImpl implements TestStatisticDao {

	private static final Logger LOGGER = Logger.getLogger(TestStatisticDaoImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.TestStatisticDao#save(in.ac.coep.entity.
	 * TestInstanceStatistics)
	 */
	@Override
	public void save(TestInstanceStatistics instanceStatistics) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.save(instanceStatistics);

			LOGGER.debug("save TestInstance Statistic  successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.error("save TestInstance Statistic failed", e);
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

			throw new Exception(e);
		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

	}

	/* (non-Javadoc)
	 * @see in.ac.coep.dao.TestStatisticDao#getAllUsersStatisticsByuserIdFrmState(long)
	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<TestInstanceStatistics> getAllUsersStatisticsByuserIdFrmState(long userId)throws Exception {
//		// TODO Auto-generated method stub
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceStatistics.class,"stat");
//			criteria.add(Restrictions.eq("stat.user.userId", userId));
//
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//			List<TestInstanceStatistics> instanceStatistics = criteria.list();
//			
//			LOGGER.debug("fetch TestInstance Statistic  successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return instanceStatistics;
//		} catch (HibernateException e) {
//			LOGGER.error("fetch TestInstance Statistic failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//		}
//
//	}

	/* (non-Javadoc)
	 * @see in.ac.coep.dao.TestStatisticDao#fetchStatisticsByUserId(long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TestInstanceStatistics> fetchStatisticsByUserId(long userId, Integer[] sections) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null; 
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();
			Criteria criteria = session.createCriteria(TestInstanceStatistics.class, "tstIns");
				criteria.add(Restrictions.eq("tstIns.user.userId", userId));
				criteria.add(Restrictions.in("tstIns.section.sectionId", sections));

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<TestInstanceStatistics> testInstanceStatistics = criteria.list();

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();
			return testInstanceStatistics;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

			throw new Exception(e);
		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestInstanceStatistics> getAlltestStatisticsRecords() throws Exception {
		Session session = null;
		Transaction tx = null;

		List<TestInstanceStatistics> testInstanceStatistics = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceStatistics.class);

			testInstanceStatistics = criteria.list();

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

		return testInstanceStatistics;
	}

	@Override
	public void delete(TestInstanceStatistics testInstanceStatistics1) throws Exception {

		// TODO Auto-generated method stub
				Session session = null;
				Transaction tx = null;

				try {
					session = sessionFactory.openSession();
					tx = session.beginTransaction();

//					session.flush();
//					session.clear();

					session.delete(testInstanceStatistics1);

					LOGGER.debug("fetch successful");
//					session.flush();
//					session.clear();
					tx.commit();
//					session.close();

				} catch (HibernateException e) {
					LOGGER.debug("fetch failed", e);
					if (tx != null)
						tx.rollback();
//					if (session != null && session.isOpen())
//						session.close();

					throw new Exception(e);

				}finally {
			        if (session != null && session.isOpen()) {
			            session.close();
			        }
			    }
		
		
		
		

	}

	@Override
	public TestInstanceStatistics getAlltestStatisticsRecordsFromIds(long instanceStatisticsId, long userId)
			throws Exception {
		Session session = null;
		Transaction tx = null;

		TestInstanceStatistics testInstanceStatistics = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceStatistics.class);
			criteria.add(Restrictions.eq("TestInstanceStatisticId", instanceStatisticsId));
			criteria.add(Restrictions.eq("user.userId", userId));
			testInstanceStatistics = (TestInstanceStatistics) criteria.uniqueResult();
			
			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

		return testInstanceStatistics;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestInstanceStatistics> getAlltestStatisticsRecordsByUserId(long userId) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceStatistics.class,"stat");
			criteria.add(Restrictions.eq("stat.user.userId", userId));

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<TestInstanceStatistics> instanceStatistics = criteria.list();
			
			LOGGER.debug("fetch TestInstance Statistic  successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return instanceStatistics;
		} catch (HibernateException e) {
			LOGGER.error("fetch TestInstance Statistic failed", e);
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

			throw new Exception(e);
		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

	}

	
}
