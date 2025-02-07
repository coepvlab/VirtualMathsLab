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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ac.coep.dao.TestInstanceStateDao;
import in.ac.coep.entity.TestInstanceState;

/**
 * @author Prashant
 *
 */
@Repository
@Transactional
public class TestStateDaoImpl implements TestInstanceStateDao {

	private static final Logger LOGGER = Logger.getLogger(TestStateDaoImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.dao.TestStateDao#save(in.ac.coep.entity.TestInstanceState)
	 */
	@Override
	public void save(TestInstanceState instanceState) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.save(instanceState);

			LOGGER.debug("TestInstanceState saved successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.error("TestInstanceState saved failed", e);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.TestStateDao#getTestInstanceStateByUserId(long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TestInstanceState> getTestInstanceStateByUserId(long userId) throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
			if (userId != 0) {
				criteria.add(Restrictions.eq("tis.user.userId", userId));
			}

			List<TestInstanceState> testInstanceStates = criteria.list();

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return testInstanceStates;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.TestInstanceStateDao#getTestInstanceStateById(long)
	 */
	@Override
	public TestInstanceState getTestInstanceStateById(long testInstanceStateId) throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			TestInstanceState testInstanceState = (TestInstanceState) session.get(TestInstanceState.class,
					testInstanceStateId);

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return testInstanceState;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.TestInstanceStateDao#update(in.ac.coep.entity.
	 * TestInstanceState)
	 */
	@Override
	public void update(TestInstanceState instanceState) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.update(instanceState);

			LOGGER.debug("TestInstanceState updated successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.error("TestInstanceState updation failed", e);
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
	 * @see in.ac.coep.dao.TestInstanceStateDao#getTestInstanceStateByUserIdAndTestId(long, long)
	 */
	@Override
	public List<TestInstanceState> getTestInstanceStateByUserIdAndTestId(long userId, long testId)throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
			criteria.add(Restrictions.eq("tis.test.testId", testId));
			criteria.add(Restrictions.eq("tis.user.userId", userId));

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			@SuppressWarnings("unchecked")
			List<TestInstanceState> testInstanceStates = criteria.list();
		

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return testInstanceStates;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
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

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<TestInstanceState> getTestInstanceStateByUserIdAndTopicId(long userId, String topicId, long testTypeId) throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
//			criteria.add(Restrictions.eq("tis.test.testId", topicId)); /// this is wrong
			criteria.add(Restrictions.eq("tis.user.userId", userId));
			
			criteria.setFetchMode("tis.test", FetchMode.JOIN);
			criteria.createAlias("tis.test", "test", CriteriaSpecification.LEFT_JOIN);
			
			criteria.add(Restrictions.eq("test.selectedTopics", topicId));
			criteria.add(Restrictions.eq("tis.testType.testTypeId", testTypeId));
			
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
			List<TestInstanceState> testInstanceStates = criteria.list();

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return testInstanceStates;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
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

	@Override
	public TestInstanceState getTestInstanceStateByUserIdandTestLevelAndTISID(long userId, long tISID)
			throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");			
			criteria.add(Restrictions.eq("tis.user.userId", userId));
//			criteria.add(Restrictions.eq("tis.testLevel", testLevel));
			criteria.add(Restrictions.eq("tis.testInstanceStateId", tISID));

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			TestInstanceState testInstanceStates = (TestInstanceState) criteria.uniqueResult();
		

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return testInstanceStates;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
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
	public List<TestInstanceState> getTestInstanceStateByUserIdAndtestLink(long userId, String urlCheck)
			throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
			if (userId != 0) {
				criteria.add(Restrictions.eq("tis.user.userId", userId));
			}
			criteria.add(Restrictions.eq("tis.TestURL", urlCheck));
			
			List<TestInstanceState> testInstanceStates = criteria.list();

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return testInstanceStates;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
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

	@Override
	public List<TestInstanceState> getTestInstanceStateByUserIdAndTestType(long userId, long practiceTestTypeId)
			throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
			if (userId != 0) {
				criteria.add(Restrictions.eq("tis.user.userId", userId));
				criteria.add(Restrictions.eq("tis.testType.testTypeId", practiceTestTypeId));
			}

			@SuppressWarnings("unchecked")
			List<TestInstanceState> testInstanceStates = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return testInstanceStates;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.dao.TestInstanceStateDao#getTestInstanceStateByStatus(java.
	 * lang.String)
	 */
//	@Override
//	public List<TestInstanceState> getTestInstanceStateByStatus(String status) throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
//			if (status != null) {
//				criteria.add(Restrictions.eq("tis.Status", status));
//			}
//
//			@SuppressWarnings("unchecked")
//			List<TestInstanceState> testInstanceStates = criteria.list();
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.dao.TestInstanceStateDao#getAllStatesByStatusAndPdfGenerate()
	 */
//	@Override
//	public List<TestInstanceState> getAllStatesByStatusAndPdfGenerate() throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
//			criteria.add(Restrictions.eq("tis.Status", "C"));
//			criteria.add(Restrictions.eq("pdfGenerate", false));
//
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//			@SuppressWarnings("unchecked")
//			List<TestInstanceState> testInstanceStates = criteria.list();
//		
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}

	
	
	
//	@Override
//	public TestInstanceState getTestInstanceStateTestLevelByUserIdAndTestId(long userId, long testId) throws Exception{
//
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
//			criteria.add(Restrictions.eq("tis.test.testId", testId));
//			criteria.add(Restrictions.eq("tis.user.userId", userId));
//
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//			TestInstanceState testInstanceStates = (TestInstanceState)criteria.uniqueResult();
//		
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	
//	}

//	@Override
//	public TestInstanceState getTestInstanceStateByEmailIdAndDeptId(long userId, long testId, int testLevel)
//			throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
//			criteria.add(Restrictions.eq("tis.test.testId", testId));
//			criteria.add(Restrictions.eq("tis.user.userId", userId));
//			criteria.add(Restrictions.eq("tis.testLevel", testLevel));
//
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//			TestInstanceState testInstanceStates = (TestInstanceState) criteria.uniqueResult();
//		
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}


//	@Override
//	public TestInstanceState getTestInstanceStateByUserIdandTestLevel(long userId, int testLevel)
//			throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");			
//			criteria.add(Restrictions.eq("tis.user.userId", userId));
//			criteria.add(Restrictions.eq("tis.testLevel", testLevel));
//
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//			TestInstanceState testInstanceStates = (TestInstanceState) criteria.uniqueResult();
//		
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<TestInstanceState> getAllStatesByStatusAndPdfGeneratetoupgrade(int deptId, int testLevel)
//			throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
//			criteria.add(Restrictions.eq("tis.Status", "C"));
//			criteria.add(Restrictions.eq("pdfGenerate", true));
//			criteria.add(Restrictions.eq("tis.department.departmentId", deptId));
//			criteria.add(Restrictions.eq("tis.testLevel", testLevel));
//
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//			List<TestInstanceState> testInstanceStates = criteria.list();
//		
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<TestInstanceState> getRound2AndRound3Percentage(int deptId) throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
//			criteria.add(Restrictions.eq("tis.Status", "C"));
//			criteria.add(Restrictions.eq("pdfGenerate", true));
//			criteria.add(Restrictions.eq("tis.department.departmentId", deptId));
//			criteria.add(Restrictions.eq("tis.testLevel", 3));
//
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//			List<TestInstanceState> testInstanceStates = criteria.list();
//		
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<TestInstanceState> getAveragePercentage(int deptId, int testLevel) throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
//			criteria.add(Restrictions.eq("tis.Status", "C"));
//			criteria.add(Restrictions.eq("pdfGenerate", true));
//			criteria.add(Restrictions.eq("tis.department.departmentId", deptId));
//			criteria.add(Restrictions.eq("tis.testLevel", testLevel));
//
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//			List<TestInstanceState> testInstanceStates = criteria.list();
//		
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}
//	
//	@SuppressWarnings({ "unchecked", "deprecation" })
//	@Override
//	public List<TestInstanceState> getUsersByStatusAndPdfGenerateToSendReports() throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
//			criteria.add(Restrictions.eq("tis.Status", "C"));
//			//criteria.add(Restrictions.eq("tis.scorecardSentStatus", false));
//			criteria.setFetchMode("tis.user", FetchMode.JOIN);
//			criteria.createAlias("tis.user", "tisuser", CriteriaSpecification.LEFT_JOIN);
//			criteria.add(Restrictions.eq("tisuser.IsPaymentDone", true));
//			
////			criteria.add(Restrictions.eq("tis.Status", "C"));
//			criteria.add(Restrictions.eq("pdfGenerate", true));
////
////			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
////			@SuppressWarnings("unchecked")
//			List<TestInstanceState> testInstanceStates = criteria.list();
//		
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}
//
//	@SuppressWarnings({ "deprecation", "unchecked" })
//	@Override
//	public List<TestInstanceState> getAllUSersListWithLastRound(int testLevel) throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
//			criteria.add(Restrictions.eq("tis.testLevel", testLevel));
//			//criteria.add(Restrictions.eq("tis.scorecardSentStatus", false));
//			criteria.setFetchMode("tis.user", FetchMode.JOIN);
//			criteria.createAlias("tis.user", "tisuser", CriteriaSpecification.LEFT_JOIN);
//			criteria.add(Restrictions.eq("tisuser.IsAdmin", false));
//			
////			criteria.add(Restrictions.eq("tis.Status", "C"));
////			criteria.add(Restrictions.eq("pdfGenerate", true));
////
////			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
////			@SuppressWarnings("unchecked")
//			List<TestInstanceState> testInstanceStates = criteria.list();
//		
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}
//
//	@Override
//	public TestInstanceState getTestInstanceStateByUserIDAndCurrRound(long userId, int currRoundNo) throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
//			criteria.add(Restrictions.eq("tis.user.userId", userId));
//			criteria.add(Restrictions.eq("tis.testLevel", currRoundNo));
//
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//			TestInstanceState testInstanceStates = (TestInstanceState) criteria.uniqueResult();
//		
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<TestInstanceState> getTestInstanceStateByUserIdForResult(long userId) throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
//			if (userId != 0) {
//				criteria.add(Restrictions.eq("tis.user.userId", userId));
//			}
//
//			List<TestInstanceState> testInstanceStates = criteria.list();
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}
//
//	@Override
//	public TestInstanceState getTestInstanceStateByUserIdandTestLevelByMediaId(Long userId, int testlevel,
//			String mediaID) throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");			
//			criteria.add(Restrictions.eq("tis.user.userId", userId));
//			criteria.add(Restrictions.eq("tis.testLevel", testlevel));
//			criteria.add(Restrictions.eq("tis.scoreCardLink", mediaID));
//
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//			TestInstanceState testInstanceStates = (TestInstanceState) criteria.uniqueResult();
//		
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<TestInstanceState> getTotalTestCompletedAndTestInProgressCount(int testLevel) throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");			
//			criteria.add(Restrictions.eq("tis.testLevel",testLevel ));
////			criteria.add(Restrictions.eq("tis.scoreCardLink", mediaID));
//
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//			List<TestInstanceState> testInstanceStates =  criteria.list();
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<TestInstanceState> getAllTestInstanceState() throws Exception {
//		Session session = null;
//		Transaction tx = null;
//
//		List<TestInstanceState> testInstanceState = null;
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class);
//
//			testInstanceState = criteria.list();
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//		}
//
//		return testInstanceState;
//	}
//
//	@Override
//	public TestInstanceState getTestInstanceStateByStudentsUserId(long userId) throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
//			criteria.add(Restrictions.eq("tis.user.userId", userId));
//			criteria.add(Restrictions.eq("isActive", true));
//
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//			TestInstanceState testInstanceStates = (TestInstanceState) criteria.uniqueResult();
//		
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}
//
//	@Override
//	public List<TestInstanceState> getAllTestInstanceStateByStatus() throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
//				criteria.add(Restrictions.ne("tis.Status", "C"));
//
//			@SuppressWarnings("unchecked")
//			List<TestInstanceState> testInstanceStates = criteria.list();
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}
//
//	@Override
//	public List<TestInstanceState> getAllUsersWithLastRoundTestCompleted(int testLevel) throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(TestInstanceState.class, "tis");
//				criteria.add(Restrictions.eq("tis.Status", "C"));
//				criteria.add(Restrictions.eq("tis.testLevel", testLevel));
//
//			@SuppressWarnings("unchecked")
//			List<TestInstanceState> testInstanceStates = criteria.list();
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testInstanceStates;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}



}
