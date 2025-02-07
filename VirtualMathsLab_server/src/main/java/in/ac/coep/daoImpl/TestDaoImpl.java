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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ac.coep.dao.TestDao;
import in.ac.coep.entity.Test;
import in.ac.coep.entity.TestInstanceState;

/**
 * @author Prashant
 * 
 */

@Repository
@Transactional
public class TestDaoImpl implements TestDao {

	private static final Logger LOGGER = Logger.getLogger(TestDaoImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.TestDao#configureTest(in.ac.coep.entity.Test)
	 */
	@Override
	public long configureTestPaper(Test test) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Serializable serializable = session.save(test);

			long questionGroupId = (Long) serializable;

			LOGGER.debug("configure Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return questionGroupId;

		} catch (HibernateException e) {
			LOGGER.error("configure Test failed", e);
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

			throw new Exception(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.TestDao#getTestByTestId(long)
	 */
	@Override
	public Test getTestByTestId(long testId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			// QuestionGroup questionGroup = (QuestionGroup)
			// session.get(QuestionGroup.class, questionGroupId);

			Test test = (Test) session.get(Test.class, testId);

			LOGGER.debug("fetch Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return test;

		} catch (HibernateException e) {
			LOGGER.debug("fetch Test", e);
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
	 * @see in.ac.coep.dao.TestDao#modifyTestPaper(in.ac.coep.entity.Test)
	 */
	@Override
	public void modifyTestPaper(Test test) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.update(test);

			LOGGER.debug("modifyTestPaper successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.error("modifyTestPaper failed", e);
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
	 * @see in.ac.coep.dao.TestDao#getTestByDepartmentId(int)
	 */
	@Override
	public Test getTestByDepartmentId(int domain) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(Test.class, "test");
			criteria.add(Restrictions.eq("test.department.departmentId", domain));
			criteria.add(Restrictions.eq("isActive", true));

			Test test = (Test) criteria.uniqueResult();

			LOGGER.debug("fetch Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return test;

		} catch (HibernateException e) {
			LOGGER.debug("fetch Test", e);
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
	 * @see in.ac.coep.dao.TestDao#getTestInstanceRowCount()
	 */
	@Override
	public long getTestInstanceStateRowCount() throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceState.class);

			long totalResult = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();

			LOGGER.debug("fetch Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return totalResult;

		} catch (HibernateException e) {
			LOGGER.debug("fetch Test", e);
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
	 * @see in.ac.coep.dao.TestDao#getTestByUserId(long)
	 */
	@Override
	public Test getTestByUserId(long userId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(Test.class);
			criteria.add(Restrictions.eq("user.userId", userId));
			Test test = (Test) criteria.uniqueResult();

			LOGGER.debug("fetch Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return test;

		} catch (HibernateException e) {
			LOGGER.debug("fetch Test", e);
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
	public List<Test> getAllTestList() throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(Test.class);
//			criteria.add(Restrictions.eq("user.userId", userId));
			List<Test> test = criteria.list();
			LOGGER.debug("fetch Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return test;

		} catch (HibernateException e) {
			LOGGER.debug("fetch Test", e);
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
	public void updateTestDetails(Test test) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.update(test);

			LOGGER.debug("modify Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.error("modify Test failed", e);
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
	public Test getTestByDepartmentIdAndTestLevel(int domain, int testLevel) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(Test.class, "test");
			criteria.add(Restrictions.eq("test.department.departmentId", domain));
			criteria.add(Restrictions.eq("test.testLevel", testLevel));
			criteria.add(Restrictions.eq("isActive", true));

			Test test = (Test) criteria.uniqueResult();

			LOGGER.debug("fetch Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return test;

		} catch (HibernateException e) {
			LOGGER.debug("fetch Test", e);
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
	public Test getTestForPracticeTestByTopicId(String selectedTopicId, String varType, int complLevel) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(Test.class, "test");
			
			criteria.add(Restrictions.eq("test.selectedTopics", selectedTopicId));
			criteria.add(Restrictions.eq("test.testType.testTypeId", 1l)); // for practice test only
			criteria.add(Restrictions.eq("test.varType", varType)); 
			criteria.add(Restrictions.eq("test.testLevel", complLevel));
			criteria.add(Restrictions.eq("test.isActive", true));
			
//			TestConfiguration configuration = (TestConfiguration) criteria.uniqueResult();
			Test test = (Test) criteria.uniqueResult();

			LOGGER.debug("fetch Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return test;

		} catch (HibernateException e) {
			LOGGER.debug("fetch Test", e);
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
	public Test getTestBytopicIdVarNoAndVarTypeAndCompLevel(String selectedTopicId, String varType, int complLevel,
			String varNo) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(Test.class, "test");
			
			criteria.add(Restrictions.eq("test.selectedTopics", selectedTopicId));
			criteria.add(Restrictions.eq("test.testType.testTypeId", 1l)); // for practice test only
			criteria.add(Restrictions.eq("test.varType", varType)); 
			criteria.add(Restrictions.eq("test.testLevel", complLevel));
			criteria.add(Restrictions.eq("test.varNo", varNo)); 
			criteria.add(Restrictions.eq("test.isActive", true));
			
//			TestConfiguration configuration = (TestConfiguration) criteria.uniqueResult();
			Test test = (Test) criteria.uniqueResult();

			LOGGER.debug("fetch Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return test;

		} catch (HibernateException e) {
			LOGGER.debug("fetch Test", e);
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
	public List<Test> getAllTestListByTestTypeId(long testTypeId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(Test.class,"test");
			criteria.add(Restrictions.eq("test.testType.testTypeId", testTypeId));
			criteria.add(Restrictions.eq("isActive", true));

			@SuppressWarnings("unchecked")
			List<Test> test = criteria.list();
			LOGGER.debug("fetch Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return test;

		} catch (HibernateException e) {
			LOGGER.debug("fetch Test", e);
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
	public List<Test> getTestForPracticeTestByTopicIdAndTestLevel(String selectedTopics, int testLevel)
			throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(Test.class);
			criteria.add(Restrictions.eq("isActive", true));
			criteria.add(Restrictions.eq("selectedTopics", selectedTopics));
			criteria.add(Restrictions.eq("testLevel", testLevel));
			
			List<Test> test = criteria.list();
			LOGGER.debug("fetch Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return test;

		} catch (HibernateException e) {
			LOGGER.debug("fetch Test", e);
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
	public List<Test> getTestForPracticeTestByTopicIdOnly(String selectedTopics) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(Test.class);
			criteria.add(Restrictions.eq("isActive", true));
			criteria.add(Restrictions.eq("selectedTopics", selectedTopics));
			
			List<Test> test = criteria.list();
			LOGGER.debug("fetch Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return test;

		} catch (HibernateException e) {
			LOGGER.debug("fetch Test", e);
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
