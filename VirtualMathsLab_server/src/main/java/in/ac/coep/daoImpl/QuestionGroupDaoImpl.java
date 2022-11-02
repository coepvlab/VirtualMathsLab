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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ac.coep.dao.QuestionGroupDao;
import in.ac.coep.entity.QuestionGroup;

@Repository
@Transactional
public class QuestionGroupDaoImpl implements QuestionGroupDao {

	private static final Logger LOGGER = Logger.getLogger(QuestionGroupDaoImpl.class);

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	@Override
	public long addQuestionGroup(QuestionGroup questionGroup) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Serializable serializable = session.save(questionGroup);

			long questionGroupId = (Long) serializable;

			LOGGER.debug("add QuestionGroup successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroupId;

		} catch (HibernateException e) {
			LOGGER.error("add QuestionGroup failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public QuestionGroup getQuestionGroupById(long questionGroupId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			// QuestionGroup questionGroup = (QuestionGroup)
			// session.get(QuestionGroup.class, questionGroupId);

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.setFetchMode("qg.questions", FetchMode.JOIN);
			criteria.createAlias("qg.questions", "ques", CriteriaSpecification.LEFT_JOIN);
			criteria.setFetchMode("ques.answers", FetchMode.JOIN);
			criteria.createAlias("ques.answers", "ans", CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("qg.questionGroupId", questionGroupId));

			QuestionGroup questionGroup = (QuestionGroup) criteria.uniqueResult();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroup;

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
	 * @see in.ac.coep.dao.QuestionGroupDao#getAllQuestionGroupsByStatus(java.lang.
	 * String)
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<QuestionGroup> getAllQuestionGroupsByStatus(String status) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		List<QuestionGroup> questionGroups = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.setFetchMode("qg.questions", FetchMode.JOIN);
			criteria.createAlias("qg.questions", "ques", CriteriaSpecification.LEFT_JOIN);

			if (status.equalsIgnoreCase("active")) {
				criteria.add(Restrictions.eq("isArchive", false));
			} else {
				criteria.add(Restrictions.eq("isArchive", true));
			}

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

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
	 * @see in.ac.coep.dao.QuestionGroupDao#modifyQuestionGroup(in.ac.coep.entity.
	 * QuestionGroup)
	 */
	@Override
	public void modifyQuestionGroup(QuestionGroup questionGroup) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			session.update(questionGroup);

			LOGGER.debug("add QuestionGroup successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.error("add QuestionGroup failed", e);
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
	 * @see in.ac.coep.dao.QuestionGroupDao#fetchQuestionGroupsOnDifferentCeiteria(
	 * org.json.JSONObject)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionGroup> fetchQuestionGroupsOnDifferentCeiteria(JSONObject filtersJson) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		List<QuestionGroup> questionGroups = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.add(Restrictions.eq("isArchive", filtersJson.getBoolean("status")));

			if (filtersJson.getInt("LVL") != 0) {

				criteria.add(Restrictions.eq("qg.complexityLevel.qgComplexityLevelId", filtersJson.getInt("LVL")));

			}
			if (filtersJson.getInt("SEC") != 0) {

				criteria.add(Restrictions.eq("qg.section.sectionId", filtersJson.getInt("SEC")));

			}
			if (filtersJson.getInt("DEPT") != 0) {

				criteria.add(Restrictions.eq("qg.departmant.departmentId", filtersJson.getInt("DEPT")));

			}
			if (!filtersJson.getString("PT").equalsIgnoreCase("All")) {

				criteria.add(Restrictions.eq("qg.type", filtersJson.getString("PT")));
			}

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

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
	 * @see in.ac.coep.dao.QuestionGroupDao#
	 * fetchLevelWiseCountOfQuestionGroupsBySections(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> fetchLevelWiseCountOfQuestionGroupsBySections(int sectionId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		List<Object[]> questionGroups = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			// criteria.add(Restrictions.eq("qg.level", 1));
			if (sectionId != 0) {
				criteria.add(Restrictions.eq("qg.section.sectionId", sectionId));
			}
			criteria.add(Restrictions.eq("qg.isArchive", false));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.groupProperty("qg.complexityLevel.qgComplexityLevelId").as("level"))
					// .add(Projections.rowCount())
					.add(Projections.countDistinct("qg.questionGroupId").as("questionGroupId"))).list();

			// criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

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
	 * @see in.ac.coep.dao.QuestionGroupDao#fetchLevelWiseQuestionGroupsBySectionId(
	 * int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkQuestionGroupsExistForSectionId(int sectionId) throws Exception {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		boolean result = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			// criteria.add(Restrictions.eq("qg.level", 1));
			if (sectionId != 0) {
				criteria.add(Restrictions.eq("qg.section.sectionId", sectionId));
				criteria.setMaxResults(1);
			}

			List<QuestionGroup> questionGroups = criteria.list();

			if (!questionGroups.isEmpty()) {
				result = true;
			}

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return result;

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
	 * @see in.ac.coep.dao.QuestionGroupDao#getAllQuestionGroupsBySectionId(int,
	 * int, int)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<QuestionGroup> getAllQuestionGroupsBySectionId(int sectionId, int noOfQuestionGroup,
			int qgComplexityLevelId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.setFetchMode("qg.questions", FetchMode.JOIN);
			criteria.createAlias("qg.questions", "ques", CriteriaSpecification.LEFT_JOIN);
			criteria.setFetchMode("ques.answers", FetchMode.JOIN);
			criteria.createAlias("ques.answers", "ans", CriteriaSpecification.LEFT_JOIN);

			criteria.add(Restrictions.eq("qg.complexityLevel.qgComplexityLevelId", qgComplexityLevelId));
			criteria.add(Restrictions.eq("qg.section.sectionId", sectionId));
			criteria.add(Restrictions.eq("qg.isArchive", false));

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<QuestionGroup> questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

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
	 * in.ac.coep.dao.QuestionGroupDao#getAllQuestionGroupsBySectionIdAndLevel(int,
	 * int)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<QuestionGroup> getAllQuestionGroupsBySectionIdAndLevel(int psysecid, int psyseclevelforintro)
			throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.setFetchMode("qg.questions", FetchMode.JOIN);
			criteria.createAlias("qg.questions", "ques", CriteriaSpecification.LEFT_JOIN);
			criteria.setFetchMode("ques.answers", FetchMode.JOIN);
			criteria.createAlias("ques.answers", "ans", CriteriaSpecification.LEFT_JOIN);

			criteria.add(Restrictions.eq("qg.complexityLevel.qgComplexityLevelId", psyseclevelforintro));
			criteria.add(Restrictions.eq("qg.section.sectionId", psysecid));
			criteria.add(Restrictions.eq("qg.isArchive", false));

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<QuestionGroup> questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<QuestionGroup> getAllQuestionGroupsByUserId(long userId, String status) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		List<QuestionGroup> questionGroups = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.setFetchMode("qg.questions", FetchMode.JOIN);
			criteria.createAlias("qg.questions", "ques", CriteriaSpecification.LEFT_JOIN);
//			criteria.add(Restrictions.eq("isArchive", false));
			criteria.add(Restrictions.eq("createdBy", String.valueOf(userId)));

			if (status.equalsIgnoreCase("active")) {
				criteria.add(Restrictions.eq("isArchive", false));
			} else {
				criteria.add(Restrictions.eq("isArchive", true));
			}

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<QuestionGroup> getAllQuestionGroupsToApproveByStatus(String status) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		List<QuestionGroup> questionGroups = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.setFetchMode("qg.questions", FetchMode.JOIN);
			criteria.createAlias("qg.questions", "ques", CriteriaSpecification.LEFT_JOIN);

			if (status.equalsIgnoreCase("Non-Approved")) {
				criteria.add(Restrictions.eq("isArchive", false));
				criteria.add(Restrictions.eq("isApproved", false));
			} else {
				criteria.add(Restrictions.eq("isArchive", false));
				criteria.add(Restrictions.eq("isApproved", true));
			}

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

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
	public void deleteQuestionByQuestionGroup(QuestionGroup questionGroup) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			session.delete(questionGroup);

			LOGGER.debug("remove successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (ConstraintViolationException constraintViolationException) {
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();
			LOGGER.error("This question can not be modified or delete.", constraintViolationException);
			throw new ConstraintViolationException("This question can not be modified or delete.",
					constraintViolationException.getSQLException(), "Username");
		} catch (HibernateException e) {
			LOGGER.error("remove failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Object[]> fetchLevelWiseCountOfQuestionGroupsByTopicId(long topicId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		List<Object[]> questionGroups = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			DetachedCriteria crt = DetachedCriteria.forClass(QuestionGroup.class,
					"qg");
			crt.setFetchMode("qg.topicSet", FetchMode.JOIN);
			
			crt.createAlias("qg.topicSet", "tp",CriteriaSpecification.LEFT_JOIN);
			
			crt.add(Restrictions.eq("tp.topicId", topicId));
			
			crt.add(Restrictions.isNotNull("qg.varNo"));
			
			crt.add(Restrictions.eq("qg.isArchive", false));
			crt.add(Restrictions.eq("qg.isApproved", true));
			(  crt.setProjection(Projections.projectionList()
					.add(Projections.groupProperty("qg.complexityLevel.qgComplexityLevelId").as("level"))
					// .add(Projections.rowCount())
					.add(Projections.countDistinct("qg.questionGroupId").as("questionGroupId")))).getExecutableCriteria(session).list();
			
			questionGroups = crt.getExecutableCriteria(session).list();
			

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
		return questionGroups;

	}

	@SuppressWarnings("deprecation")
	@Override
	public List<QuestionGroup> getAllQuestionGroupsByTopicId(long topicId, int noOfQuestionGroup,
			int qgComplexityLevelId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.setFetchMode("qg.questions", FetchMode.JOIN);
			criteria.createAlias("qg.questions", "ques", CriteriaSpecification.LEFT_JOIN);
			criteria.setFetchMode("ques.answers", FetchMode.JOIN);
			criteria.createAlias("ques.answers", "ans", CriteriaSpecification.LEFT_JOIN);
			
			criteria.setFetchMode("qg.topicSet", FetchMode.JOIN);
			criteria.createAlias("qg.topicSet", "tp",CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("tp.topicId", topicId));

			criteria.add(Restrictions.eq("qg.complexityLevel.qgComplexityLevelId", qgComplexityLevelId));
//			criteria.add(Restrictions.eq("qg.topic.topicId", topicId));
			criteria.add(Restrictions.eq("qg.isArchive", false));

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<QuestionGroup> questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

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
	public List<QuestionGroup> getAllQuestionGroupsByTopicId(long topicId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.setFetchMode("qg.questions", FetchMode.JOIN);
			criteria.createAlias("qg.questions", "ques", CriteriaSpecification.LEFT_JOIN);
			criteria.setFetchMode("ques.answers", FetchMode.JOIN);
			criteria.createAlias("ques.answers", "ans", CriteriaSpecification.LEFT_JOIN);
			
			criteria.setFetchMode("qg.topicSet", FetchMode.JOIN);
			criteria.createAlias("qg.topicSet", "tp",CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("tp.topicId", topicId));

//			criteria.add(Restrictions.eq("qg.complexityLevel.qgComplexityLevelId", qgComplexityLevelId));
//			criteria.add(Restrictions.eq("qg.topic.topicId", topicId));
//			criteria.add(Restrictions.eq("qg.isApproved", true));
//			criteria.add(Restrictions.eq("qg.isArchive", false));
			
			

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<QuestionGroup> questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

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
	public List<QuestionGroup> getAllQuestionGroupsByTestConfiguration(long topicId, int noOfQuestionGroup,
			int qgComplexityLevelId, String[] varNo) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.setFetchMode("qg.questions", FetchMode.JOIN);
			criteria.createAlias("qg.questions", "ques", CriteriaSpecification.LEFT_JOIN);
			criteria.setFetchMode("ques.answers", FetchMode.JOIN);
			criteria.createAlias("ques.answers", "ans", CriteriaSpecification.LEFT_JOIN);
			
			criteria.setFetchMode("qg.topicSet", FetchMode.JOIN);
			criteria.createAlias("qg.topicSet", "tp",CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("tp.topicId", topicId));

			criteria.add(Restrictions.eq("qg.complexityLevel.qgComplexityLevelId", qgComplexityLevelId));
			criteria.add(Restrictions.in("qg.varNo", varNo));
//			criteria.add(Restrictions.eq("qg.topic.topicId", topicId));
			criteria.add(Restrictions.eq("qg.isApproved", true));
			criteria.add(Restrictions.eq("qg.isArchive", false));

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<QuestionGroup> questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public List<QuestionGroup> getAllQuestionGroupsByFilter(String status, long topicId, String[] varNo, Object[] difficultyLevel)
			throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.setFetchMode("qg.questions", FetchMode.JOIN);
			criteria.createAlias("qg.questions", "ques", CriteriaSpecification.LEFT_JOIN);
			criteria.setFetchMode("ques.answers", FetchMode.JOIN);
			criteria.createAlias("ques.answers", "ans", CriteriaSpecification.LEFT_JOIN);
			
			criteria.setFetchMode("qg.topicSet", FetchMode.JOIN);
			criteria.createAlias("qg.topicSet", "tp",CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("tp.topicId", topicId));

			if (varNo.length == 1 && varNo[0].equals("0")) {
				System.out.println("getAllQuestionGroupsByFilter");
			}else if (varNo.length == 1 && varNo[0].equals("100")) {
				criteria.add(Restrictions.isNull("qg.varNo"));
			}else {
				boolean flag = false;
				for (int i = 0; i < varNo.length; i++) {
					if (varNo[i].equals("100")) {
						flag = true;
					}
				}
				if (flag) {
					criteria.add(Restrictions.or(Restrictions.isNull("qg.varNo"), Restrictions.in("qg.varNo", varNo )));
				}else {
					criteria.add(Restrictions.in("qg.varNo", varNo));
				}
				
			}
			
			if (difficultyLevel.length == 1 && difficultyLevel[0].equals(0)) {
				System.out.println("getAllQuestionGroupsByFilter");
			}else {
				criteria.add(Restrictions.in("qg.complexityLevel.qgComplexityLevelId", difficultyLevel ));
			}
		
			
			if (status.equalsIgnoreCase("Non-Approved")) {
				criteria.add(Restrictions.eq("isArchive", false));
				criteria.add(Restrictions.eq("isApproved", false));
			} else {
				criteria.add(Restrictions.eq("isArchive", false));
				criteria.add(Restrictions.eq("isApproved", true));
			}
			
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<QuestionGroup> questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

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
	public List<QuestionGroup> getAllQuestionGroupsByTopicIdAndStatus(String status, long topicId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.setFetchMode("qg.questions", FetchMode.JOIN);
			criteria.createAlias("qg.questions", "ques", CriteriaSpecification.LEFT_JOIN);
			criteria.setFetchMode("ques.answers", FetchMode.JOIN);
			criteria.createAlias("ques.answers", "ans", CriteriaSpecification.LEFT_JOIN);
			
			criteria.setFetchMode("qg.topicSet", FetchMode.JOIN);
			criteria.createAlias("qg.topicSet", "tp",CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("tp.topicId", topicId));

//			criteria.add(Restrictions.eq("qg.complexityLevel.qgComplexityLevelId", qgComplexityLevelId));
//			criteria.add(Restrictions.eq("qg.topic.topicId", topicId));
//			criteria.add(Restrictions.eq("qg.isApproved", true));
//			criteria.add(Restrictions.eq("qg.isArchive", false));
			
			if (status.equalsIgnoreCase("Non-Approved")) {
				criteria.add(Restrictions.eq("isArchive", false));
				criteria.add(Restrictions.eq("isApproved", false));
			} else {
				criteria.add(Restrictions.eq("isArchive", false));
				criteria.add(Restrictions.eq("isApproved", true));
			}

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<QuestionGroup> questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<QuestionGroup> getAllQuestionGroupsFromQuesGroupMappingToApproveByStatus(String status)
			throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		List<QuestionGroup> topics = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			DetachedCriteria crt = DetachedCriteria.forClass(QuestionGroup.class,
					"qg");
			
			if (status.equalsIgnoreCase("Non-Approved")) {
				crt.add(Restrictions.eq("qg.isArchive", false));
				crt.add(Restrictions.eq("qg.isApproved", false));
			} else {
				crt.add(Restrictions.eq("qg.isArchive", false));
				crt.add(Restrictions.eq("qg.isApproved", true));
			}
			crt.setFetchMode("qg.topicSet", FetchMode.JOIN);
			
			crt.createAlias("qg.topicSet", "tp",CriteriaSpecification.LEFT_JOIN);
			
//			crt.add(Restrictions.eq("tp.topicId", id));
			
			crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			topics = crt.getExecutableCriteria(session).list();
			
			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return topics;

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
	public List<QuestionGroup> getAllApprovedQuestionGroupsByTopicId(long topicId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.setFetchMode("qg.questions", FetchMode.JOIN);
			criteria.createAlias("qg.questions", "ques", CriteriaSpecification.LEFT_JOIN);
			criteria.setFetchMode("ques.answers", FetchMode.JOIN);
			criteria.createAlias("ques.answers", "ans", CriteriaSpecification.LEFT_JOIN);
			
			criteria.setFetchMode("qg.topicSet", FetchMode.JOIN);
			criteria.createAlias("qg.topicSet", "tp",CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("tp.topicId", topicId));

//			criteria.add(Restrictions.eq("qg.complexityLevel.qgComplexityLevelId", qgComplexityLevelId));
			criteria.add(Restrictions.isNotNull("qg.varNo"));
			criteria.add(Restrictions.eq("qg.isApproved", true));
			criteria.add(Restrictions.eq("qg.isArchive", false));
			
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<QuestionGroup> questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

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
	public List<QuestionGroup> getAllApprovedQuestionGroupsByTopicIdAndcomplLevel(long topicId, int complLevel)
			throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.setFetchMode("qg.questions", FetchMode.JOIN);
			criteria.createAlias("qg.questions", "ques", CriteriaSpecification.LEFT_JOIN);
			criteria.setFetchMode("ques.answers", FetchMode.JOIN);
			criteria.createAlias("ques.answers", "ans", CriteriaSpecification.LEFT_JOIN);
			
			criteria.setFetchMode("qg.topicSet", FetchMode.JOIN);
			criteria.createAlias("qg.topicSet", "tp",CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("tp.topicId", topicId));

			criteria.add(Restrictions.eq("qg.complexityLevel.qgComplexityLevelId", complLevel));
			criteria.add(Restrictions.isNotNull("qg.varNo"));
			criteria.add(Restrictions.eq("qg.isApproved", true));
			criteria.add(Restrictions.eq("qg.isArchive", false));
			
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<QuestionGroup> questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

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
	public List<QuestionGroup> getAllApprovedQuestionGroupsByTopicIdAndAllcomplLevel(long topicId, Object[] complLevelArr)
			throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.setFetchMode("qg.questions", FetchMode.JOIN);
			criteria.createAlias("qg.questions", "ques", CriteriaSpecification.LEFT_JOIN);
			criteria.setFetchMode("ques.answers", FetchMode.JOIN);
			criteria.createAlias("ques.answers", "ans", CriteriaSpecification.LEFT_JOIN);
			
			criteria.setFetchMode("qg.topicSet", FetchMode.JOIN);
			criteria.createAlias("qg.topicSet", "tp",CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("tp.topicId", topicId));
			criteria.add(Restrictions.in("qg.complexityLevel.qgComplexityLevelId", complLevelArr));
//			criteria.add(Restrictions.eq("qg.complexityLevel.qgComplexityLevelId", complLevel));
			criteria.add(Restrictions.isNotNull("qg.varNo"));
			criteria.add(Restrictions.eq("qg.isApproved", true));
			criteria.add(Restrictions.eq("qg.isArchive", false));
			
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<QuestionGroup> questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Object[]> fetchVarNoWiseCountOfQuestionGroupsByTopicId(long topicId, String[] selectedVarNo)
			throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		List<Object[]> questionGroups = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			DetachedCriteria crt = DetachedCriteria.forClass(QuestionGroup.class,
					"qg");
			crt.setFetchMode("qg.topicSet", FetchMode.JOIN);
			
			crt.createAlias("qg.topicSet", "tp",CriteriaSpecification.LEFT_JOIN);
			
			crt.add(Restrictions.eq("tp.topicId", topicId));
			
			crt.add(Restrictions.isNotNull("qg.varNo"));
			if (selectedVarNo.length != 0) {
				crt.add(Restrictions.in("qg.varNo", selectedVarNo));
			}
			
			crt.add(Restrictions.eq("qg.isArchive", false));
			crt.add(Restrictions.eq("qg.isApproved", true));
			(  crt.setProjection(Projections.projectionList()
//					.add(Projections.groupProperty("qg.complexityLevel.qgComplexityLevelId").as("level"))
					.add(Projections.groupProperty("qg.varNo").as("varNo"))
					// .add(Projections.rowCount())
					.add(Projections.countDistinct("qg.questionGroupId").as("questionGroupId")))).getExecutableCriteria(session).list();
			
			questionGroups = crt.getExecutableCriteria(session).list();
			

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
		return questionGroups;

	}

	@SuppressWarnings("deprecation")
	@Override
	public List<QuestionGroup> getAllApprovedQuestionGroupsByTopicIdAndVarNo(long topicId,
			String varNo)
			throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(QuestionGroup.class, "qg");
			criteria.setFetchMode("qg.questions", FetchMode.JOIN);
			criteria.createAlias("qg.questions", "ques", CriteriaSpecification.LEFT_JOIN);
			criteria.setFetchMode("ques.answers", FetchMode.JOIN);
			criteria.createAlias("ques.answers", "ans", CriteriaSpecification.LEFT_JOIN);
			
			criteria.setFetchMode("qg.topicSet", FetchMode.JOIN);
			criteria.createAlias("qg.topicSet", "tp",CriteriaSpecification.LEFT_JOIN);
			
			criteria.add(Restrictions.eq("tp.topicId", topicId));
			criteria.add(Restrictions.eq("qg.varNo", varNo));

//			criteria.add(Restrictions.eq("qg.complexityLevel.qgComplexityLevelId", qgComplexityLevelId));
			criteria.add(Restrictions.isNotNull("qg.varNo"));
			criteria.add(Restrictions.eq("qg.isApproved", true));
			criteria.add(Restrictions.eq("qg.isArchive", false));
			
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<QuestionGroup> questionGroups = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return questionGroups;

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

//	@Override
//	public List<QuestionGroup> getQuestionGroupByUserIdAndTestInstanceStateId(long userId, long testInstanceStateId)
//			throws Exception {
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
////			Criteria criteria = session.createCriteria(TestInstance.class, "ti");
////			criteria.add(Restrictions.eq("ti.user.userId", userId));
////			criteria.add(Restrictions.eq("ti.testInstanceState.testInstanceStateId", testInstanceStateId));
//			
////			criteria.setFetchMode("ti.questionGroup", FetchMode.JOIN);
////			criteria.createAlias("ti.questionGroup", "qg", CriteriaSpecification.LEFT_JOIN);
//			
////			criteria.setFetchMode("ques.answers", FetchMode.JOIN);
////			criteria.createAlias("ques.answers", "ans", CriteriaSpecification.LEFT_JOIN);
//			
//
////			criteria.add(Restrictions.eq("qg.questionGroup", psyseclevelforintro));
//			
//			
////			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//
//			@SuppressWarnings("unchecked")
////			List<QuestionGroup> questionGroups = criteria.setProjection(Projections.projectionList().add(Projections.distinct(Projections.property("ti.questionGroup.questionGroupId")))).list();
//
////			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return questionGroups;
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
