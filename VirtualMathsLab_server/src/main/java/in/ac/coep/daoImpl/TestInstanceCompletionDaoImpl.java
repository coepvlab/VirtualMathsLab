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
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ac.coep.dao.TestInstanceCompletionDao;
import in.ac.coep.entity.TestInstance;
import in.ac.coep.entity.TestInstanceCompletion;

/**
 * @author Prashant
 *
 */
@Repository
@Transactional
public class TestInstanceCompletionDaoImpl implements TestInstanceCompletionDao {

	private static final Logger LOGGER = Logger.getLogger(TestInstanceCompletionDaoImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.TestInstanceCompletionDao#save(in.ac.coep.entity.
	 * TestInstanceCompletion)
	 */
	@Override
	public void save(TestInstanceCompletion completion) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			session.save(completion);

			LOGGER.debug("TestInstanceCompletion saved successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.error("TestInstanceCompletion saved failed", e);
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
	 * @see in.ac.coep.dao.TestInstanceCompletionDao#
	 * fetchRecordsGroupByUserAndSections()
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Object[]> fetchRecordsGroupByUserAndSections() throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			// String q = "select * from testinstance t left join"+
			// "sections s on(s.sectionId = t.section_sectionId)"+
			// "order by t.section_sectionId,t.user_userId";

			// SQLQuery query = session.createSQLQuery(q);
			// query.addEntity(TestInstance.class);
			// List<TestInstance> results = query.list();

			Criteria criteria = session.createCriteria(TestInstance.class, "tic");
			criteria.add(Restrictions.eq("testInstanceState.testInstanceStateId", 4l));
			// criteria.setFetchMode("tic.user", FetchMode.JOIN);
			// criteria.createAlias("tic.user", "user1",
			// CriteriaSpecification.LEFT_JOIN);
			criteria.setFetchMode("tic.section", FetchMode.JOIN);
			criteria.createAlias("tic.section", "section1", CriteriaSpecification.LEFT_JOIN);
			// criteria.setFetchMode("tic.questionGroup", FetchMode.JOIN);
			// criteria.createAlias("tic.questionGroup", "questionGroup",
			// CriteriaSpecification.LEFT_JOIN);
			// criteria.setFetchMode("tic.question", FetchMode.JOIN);
			// criteria.createAlias("tic.question", "question",
			// CriteriaSpecification.LEFT_JOIN);
			// criteria.setFetchMode("tic.answers", FetchMode.JOIN);
			// criteria.createAlias("tic.answers", "answers",
			// CriteriaSpecification.LEFT_JOIN);
			criteria.setProjection(Projections.projectionList().add(Projections.property("tic.user").as("user"))
					.add(Projections.groupProperty("tic.section").as("section"))
					.add(Projections.property("tic.questionGroup").as("questionGroup"))
					.add(Projections.property("tic.question").as("question"))
					.add(Projections.property("tic.answers").as("answers"))).list();

			// DetachedCriteria detachedCriteria =
			// DetachedCriteria.forClass(TestInstanceCompletion.class, "tic1");
			// detachedCriteria.setProjection(
			// Projections.projectionList().add(Projections.groupProperty("tic1.user.userId").as("user"))
			// .add(Projections.groupProperty("tic1.section.sectionId").as("section")));
			// criteria.add(Subqueries.in("user.userId ,section.sectionId",
			// detachedCriteria));

			// criteria.setResultTransformer(Transformers.aliasToBean(TestInstance.class));
			List<Object[]> completions = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return completions;

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
	 * in.ac.coep.dao.TestInstanceCompletionDao#getByQuestionGroupIdAndTisId(
	 * java.lang.Long, long)
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<TestInstanceCompletion> getByQuestionGroupIdAndTisId(Long long1, long testInstanceStateId,boolean lvl1)
			throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceCompletion.class,"ti");
			criteria.add(Restrictions.eq("ti.testInstanceState.testInstanceStateId", testInstanceStateId));
			criteria.add(Restrictions.eq("ti.questionGroup.questionGroupId", long1));

			
			if(lvl1){
				ProjectionList list = Projections.projectionList();
				list.add(Projections.distinct(Projections.property("ti.question").as("question")));
				list.add(Projections.property("ti.actualGivenOptionsAnsId").as("actualGivenOptionsAnsId"));
				
				criteria.setProjection(list);

				criteria.setResultTransformer(Transformers.aliasToBean(TestInstanceCompletion.class));
			}
			
			List<TestInstanceCompletion> completion = criteria.list();

			LOGGER.debug("TestInstanceCompletion saved successful");

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return completion;

		} catch (HibernateException e) {
			LOGGER.error("TestInstanceCompletion saved failed", e);
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
	 * in.ac.coep.dao.TestInstanceCompletionDao#getByQuestionGroupLvlAndTisId(
	 * int, long)
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<TestInstanceCompletion> getByQuestionGroupLvlAndTisId(int i, long testInstanceStateId)
			throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceCompletion.class, "tic");
			criteria.add(Restrictions.eq("tic.testInstanceState.testInstanceStateId", testInstanceStateId));
//			criteria.add(Restrictions.eq("tic.section.sectionId", Constants.psySecId));
			criteria.setFetchMode("tic.questionGroup", FetchMode.JOIN);
			criteria.createAlias("tic.questionGroup", "questionGroup");
			criteria.add(Restrictions.eq("questionGroup.complexityLevel.qgComplexityLevelId", i));

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<TestInstanceCompletion> completion = criteria.list();

			LOGGER.debug("TestInstanceCompletion saved successful");

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return completion;

		} catch (HibernateException e) {
			LOGGER.error("TestInstanceCompletion saved failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestInstanceCompletion> getTestInstanceByTISTdfromCompletion(long testInstanceStateId, long userId)
			throws Exception {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceCompletion.class, "tic");
			criteria.add(Restrictions.eq("tic.testInstanceState.testInstanceStateId", testInstanceStateId));
			criteria.add(Restrictions.eq("tic.user.userId", userId));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<TestInstanceCompletion> completion = criteria.list();

			LOGGER.debug("TestInstanceCompletion saved successful");

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return completion;

		} catch (HibernateException e) {
			LOGGER.error("TestInstanceCompletion saved failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestInstanceCompletion> fetchRecordsGroupByUserIdAndTISID(long userId, long tisId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(TestInstanceCompletion.class, "tic");
			criteria.add(Restrictions.eq("tic.testInstanceState.testInstanceStateId", tisId));
			criteria.add(Restrictions.eq("tic.user.userId", userId));

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<TestInstanceCompletion> completion = criteria.list();

			LOGGER.debug("TestInstanceCompletion saved successful");

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return completion;

		} catch (HibernateException e) {
			LOGGER.error("getTestPaper failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

}
