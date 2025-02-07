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
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ac.coep.dao.TestConfigurationDao;
import in.ac.coep.entity.QuestionGroup;
import in.ac.coep.entity.TestConfiguration;

/**
 * @author Prashant
 *
 */
@Repository
@Transactional
public class TestConfigurationDaoImpl implements TestConfigurationDao{

	
	private static final Logger LOGGER = Logger.getLogger(TestDaoImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see in.ac.coep.dao.TestConfigurationDao#configureTest(in.ac.coep.entity.TestConfiguration)
	 */
	@Override
	public long configureTest(TestConfiguration testConfiguration) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Serializable serializable = session.save(testConfiguration);

			long testConfigurationId = (Long) serializable;

			LOGGER.debug("configure Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return testConfigurationId;

		} catch (HibernateException e) {
			LOGGER.error("configure Test failed", e);
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
	 * @see in.ac.coep.dao.TestConfigurationDao#getTestConfigurationById(long)
	 */
	@Override
	public TestConfiguration getTestConfigurationById(long testConfigId) throws Exception {
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

			TestConfiguration testConfiguration = (TestConfiguration) session.get(TestConfiguration.class, testConfigId);

			LOGGER.debug("fetch Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return testConfiguration;

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

	/* (non-Javadoc)
	 * @see in.ac.coep.dao.TestConfigurationDao#modifyTestPaper(in.ac.coep.entity.TestConfiguration)
	 */
	@Override
	public void modifyTestPaper(TestConfiguration testConfiguration) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.update(testConfiguration);


			LOGGER.debug("configure Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.error("configure Test failed", e);
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
	 * @see in.ac.coep.dao.TestConfigurationDao#fetchtestConfigurationDataBySectionId(int)
	 */
//	@Override
//	public List<TestConfiguration> fetchtestConfigurationDataBySectionId(int sectionId,long testId) throws Exception {
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
//			Criteria criteria = session.createCriteria(TestConfiguration.class,"tc");
//			criteria.add(Restrictions.eq("tc.section.sectionId", sectionId));
//			criteria.add(Restrictions.eq("tc.test.testId", testId));
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//			
//			
//			@SuppressWarnings("unchecked")
//			List<TestConfiguration> testConfigurations = criteria.list();
//			
//
//			LOGGER.debug("fetch Test successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return testConfigurations;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch Test", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}

	/* (non-Javadoc)
	 * @see in.ac.coep.dao.TestConfigurationDao#fetchTestConfigurationOfTestIdGroupBySection(long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TestConfiguration> fetchTestConfigurationOfTestIdGroupBySection(long testId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(TestConfiguration.class,"tc");
			criteria.add(Restrictions.eq("tc.test.testId", testId));
			ProjectionList list = Projections.projectionList();
			list.add(Projections.distinct(Projections.property("tc.topic").as("topic")));
			list.add(Projections.property("tc.topicTimeLimit").as("topicTimeLimit"));
			criteria.setProjection(list);
			criteria.setResultTransformer(Transformers.aliasToBean(TestConfiguration.class));
			List<TestConfiguration> testConfigurations = criteria.list();
			

			LOGGER.debug("fetch Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return testConfigurations;

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
	public List<TestConfiguration> getAllTestConfigDetails(long testId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(TestConfiguration.class, "testConfig");
			criteria.add(Restrictions.eq("testConfig.test.testId", testId));

			List<TestConfiguration> test = criteria.list();

			LOGGER.debug("fetch Test configuration successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return test;

		} catch (HibernateException e) {
			LOGGER.debug("fetch Test Configuration", e);
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
	public List<TestConfiguration> fetchComplexcityLvlBySectId(int sectId, int complexLevel) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qgp");
			criteria.add(Restrictions.eq("qgp.section.sectionId", sectId));
			criteria.add(Restrictions.eq("qgp.complexityLevel.qgComplexityLevelId", complexLevel));
			criteria.add(Restrictions.eq("qgp.isArchive", false));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			List<TestConfiguration> test = criteria.list();

			LOGGER.debug("fetch Test configuration successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return test;

		} catch (HibernateException e) {
			LOGGER.debug("fetch Test Configuration", e);
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
	public List<TestConfiguration> fetchtestConfigurationDataByTopicId(long topicId, long testId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(TestConfiguration.class,"tc");
			criteria.add(Restrictions.eq("tc.topic.topicId", topicId));
			criteria.add(Restrictions.eq("tc.test.testId", testId));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			
			@SuppressWarnings("unchecked")
			List<TestConfiguration> testConfigurations = criteria.list();
			

			LOGGER.debug("fetch Test successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return testConfigurations;

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
