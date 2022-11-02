/**
 * 
 */
package in.ac.coep.daoImpl;

import java.io.Serializable;
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
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import in.ac.coep.constants.Constants;
import in.ac.coep.dao.TestInstanceDao;
import in.ac.coep.entity.TestInstance;
import in.ac.coep.entity.TestInstanceCompletion;

/**
 * @author Prashant
 *
 */
@Service
public class TestInstanceDaoImpl implements TestInstanceDao {

	private static final Logger LOGGER = Logger.getLogger(TestInstanceDaoImpl.class);

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.TestInstanceDao#save(in.ac.coep.entity.TestInstance)
	 */
	@Override
	public long save(TestInstance instance) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Serializable serializable = session.save(instance);

			long instanceId = (Long) serializable;

			LOGGER.debug("save TestInstance  successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return instanceId;
		} catch (HibernateException e) {
			LOGGER.error("save TestInstance failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.TestInstanceDao#updateTestInstance(in.ac.coep.entity.
	 * TestInstance)
	 */
	@Override
	public void updateTestInstance(TestInstance testInstance) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			session.update(testInstance);

			LOGGER.debug("updateTestInstance successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.debug("updateTestInstance failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.TestInstanceDao#getTestInstanceById(long)
	 */
	@Override
	public TestInstance getTestInstanceById(long testInstanceId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			TestInstance testInstance = (TestInstance) session.get(TestInstance.class, testInstanceId);

			LOGGER.debug("updateTestInstance successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return testInstance;

		} catch (HibernateException e) {
			LOGGER.debug("updateTestInstance failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.TestInstanceDao#getTestInstanceByTISId(long)
	 */
	@Override
	public List<TestInstance> getTestInstanceByTISId(long testInstanceStateId) throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstance.class, "ti");
			if (testInstanceStateId != 0) {
				criteria.add(Restrictions.eq("ti.testInstanceState.testInstanceStateId", testInstanceStateId));
			}
			criteria.add(Restrictions.eq("result", false));

			@SuppressWarnings("unchecked")
			List<TestInstance> testInstances = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return testInstances;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.dao.TestInstanceDao#delete(in.ac.coep.entity.TestInstance)
	 */
	@Override
	public void delete(TestInstance testInstance) throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			session.delete(testInstance);

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.TestInstanceDao#getTestInstanceByQidUsrIdTSid(long,
	 * long, long)
	 */
	@Override
	public List<TestInstance> getTestInstanceByQidUsrIdTSid(long questionId, long userId, long testInstanceStateId)
			throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstance.class, "ti");
			if (testInstanceStateId != 0) {
				criteria.add(Restrictions.eq("ti.testInstanceState.testInstanceStateId", testInstanceStateId));
			}
			if (userId != 0) {
				criteria.add(Restrictions.eq("ti.user.userId", userId));
			}
			if (questionId != 0) {
				criteria.add(Restrictions.eq("ti.questionId", questionId));
			}

			@SuppressWarnings("unchecked")
			List<TestInstance> testInstances = criteria.list();
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return testInstances;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.dao.TestInstanceDao#getCountOfTestInstanceByTISidAndSecId(
	 * long, java.lang.String)
	 */
	@Override
	public long getCountOfTestInstanceByTISidAndSecId(long testInstanceStateId, String topicId, boolean passed)
			throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceCompletion.class, "ti");
			criteria.add(Restrictions.eq("ti.testInstanceState.testInstanceStateId", testInstanceStateId));
			criteria.add(Restrictions.eq("ti.topic.topicId", Long.parseLong(topicId)));
			
			if (passed == true) {
				criteria.add(Restrictions.eq("result", passed));
			}

			Projection rowCountProjection = Projections.countDistinct("ti.question");
			criteria.setProjection(rowCountProjection);

			long rowCount = ((Long) criteria.uniqueResult()).intValue();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return rowCount;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.TestInstanceDao#fetchAttemptedAndTotalQuestion(long)
	 */
	@Override
	public List<Object[]> fetchAttemptedAndTotalQuestion(long tisId,boolean ntAnswer) throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstance.class, "ti");
			criteria.add(Restrictions.eq("ti.testInstanceState.testInstanceStateId", tisId));
			if(ntAnswer){
				criteria.add(Restrictions.ne("ti.answersId", Constants.answer_Dummy_Record_Id));
			}
			criteria.setProjection(Projections.projectionList()
					.add(Projections.groupProperty("ti.topic.topicId").as("topic"))
					.add(Projections.countDistinct("ti.questionId").as("question"))).list();

			
			@SuppressWarnings("unchecked")
			List<Object[]> testInstances = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return testInstances;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<TestInstance> getTestInstanceByUsrId(long userId) throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstance.class, "ti");
			
			if (userId != 0) {
				criteria.add(Restrictions.eq("ti.user.userId", userId));
			}
			
			criteria.setFetchMode("ti.testInstanceState", FetchMode.JOIN);
			criteria.createAlias("ti.testInstanceState", "testIns", CriteriaSpecification.LEFT_JOIN);
			
//			criteria.add(Restrictions.eq("testIns.user.userId", userId));
			criteria.add(Restrictions.eq("testIns.testLevel", Integer.parseInt(Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND"))));

			
			@SuppressWarnings("unchecked")
			List<TestInstance> testInstances = criteria.list();
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return  testInstances;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<TestInstance> getTestInstanceByUsrIdAndTestLvl(long userId, int testLevel) throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstance.class, "ti");
			
			if (userId != 0) {
				criteria.add(Restrictions.eq("ti.user.userId", userId));
			}
			
			criteria.setFetchMode("ti.testInstanceState", FetchMode.JOIN);
			criteria.createAlias("ti.testInstanceState", "testIns", CriteriaSpecification.LEFT_JOIN);
			
//			criteria.add(Restrictions.eq("testIns.user.userId", userId));
			criteria.add(Restrictions.eq("testIns.testLevel",testLevel));

			
			@SuppressWarnings("unchecked")
			List<TestInstance> testInstances = criteria.list();
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return  testInstances;

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
	public List<TestInstance> getAllQuestionGroupsByTopicIdForResumeTest(long topicId, long userId, long testInstanceStateId)
			throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstance.class, "ti");
			
			if (userId != 0) {
				criteria.add(Restrictions.eq("ti.user.userId", userId));
			}
			
//			criteria.setFetchMode("ti.section_sectionId", FetchMode.JOIN);
//			criteria.createAlias("ti.testInstanceState", "testIns", CriteriaSpecification.LEFT_JOIN);
			
			criteria.add(Restrictions.eq("ti.topic.topicId", topicId));
			criteria.add(Restrictions.eq("ti.testInstanceState.testInstanceStateId",testInstanceStateId));

			
			@SuppressWarnings("unchecked")
			List<TestInstance> testInstances = criteria.list();
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return  testInstances;

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
	public TestInstance getTestInstanceByQuesionIdAndUserId(long questionId, long questionGroupId, long userId, long testInstanceStateId)
			throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstance.class, "ti");
			
			if (userId != 0) {
				criteria.add(Restrictions.eq("ti.user.userId", userId));
			}
			
//			criteria.setFetchMode("ti.section_sectionId", FetchMode.JOIN);
//			criteria.createAlias("ti.testInstanceState", "testIns", CriteriaSpecification.LEFT_JOIN);
			
			criteria.add(Restrictions.eq("ti.questionId", questionId));
		//	criteria.addOrder(Order.desc("ti.question.questionId"));
			criteria.add(Restrictions.eq("ti.questionGroupId", questionGroupId));
			criteria.add(Restrictions.eq("ti.testInstanceState.testInstanceStateId",testInstanceStateId));

			TestInstance testInstances = (TestInstance) criteria.uniqueResult();
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return  testInstances;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			System.out.println("questionId - "+questionId);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	@Override
	public List<TestInstance> getTestInstanceListByQuesionIdAndUserId(long questionId, long questionGroupId,
			long userId, long testInstanceStateId) throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstance.class, "ti");
			
			if (userId != 0) {
				criteria.add(Restrictions.eq("ti.user.userId", userId));
			}
			
//			criteria.setFetchMode("ti.section_sectionId", FetchMode.JOIN);
//			criteria.createAlias("ti.testInstanceState", "testIns", CriteriaSpecification.LEFT_JOIN);
			
			criteria.add(Restrictions.eq("ti.questionId", questionId));
			criteria.add(Restrictions.eq("ti.questionGroupId", questionGroupId));
			criteria.add(Restrictions.eq("ti.testInstanceState.testInstanceStateId",testInstanceStateId));

			
			@SuppressWarnings("unchecked")
			List<TestInstance> testInstances = criteria.list();
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return  testInstances;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			System.out.println("questionId - "+questionId);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestInstance> getTestInstanceByQuesId(long QuesId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

//			TestInstance testInstance = (TestInstance) session.get(TestInstance.class, "ti");
			Criteria criteria = session.createCriteria(TestInstance.class, "ti");
			
			
			criteria.add(Restrictions.eq("ti.question.questionId", QuesId));
			List<TestInstance> testInstance = criteria.list();
			

			LOGGER.debug("updateTestInstance successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return testInstance;

		} catch (HibernateException e) {
			LOGGER.debug("updateTestInstance failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<TestInstance> getTestInstanceByUsrIdAndTestInstanceStateId(long userId, long testInstanceStateId)
			throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstance.class, "ti");
			
			if (userId != 0) {
				criteria.add(Restrictions.eq("ti.user.userId", userId));
			}
			
			criteria.setFetchMode("ti.testInstanceState", FetchMode.JOIN);
			criteria.createAlias("ti.testInstanceState", "testIns", CriteriaSpecification.LEFT_JOIN);
			
			criteria.add(Restrictions.eq("testIns.testInstanceStateId", testInstanceStateId));
			criteria.add(Restrictions.eq("testIns.isActive", true));
			
			@SuppressWarnings("unchecked")
			List<TestInstance> testInstances = criteria.list();
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return  testInstances;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestInstance> getTestInstanceByTISIdAndUserId(long testInstanceStateId, long userId) throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstance.class, "ti");
			if (testInstanceStateId != 0) {
				criteria.add(Restrictions.eq("ti.testInstanceState.testInstanceStateId", testInstanceStateId));
				criteria.add(Restrictions.eq("ti.user.userId", userId));
			}
			criteria.add(Restrictions.eq("result", false));

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<TestInstance> testInstances = criteria.list();
			

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return testInstances;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	

}
