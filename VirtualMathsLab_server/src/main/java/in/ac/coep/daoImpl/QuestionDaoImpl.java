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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ac.coep.dao.QuestionDao;
import in.ac.coep.entity.Question;

/**
 * @author Prashant
 *
 */
@Repository
@Transactional
public class QuestionDaoImpl implements QuestionDao {

	private static final Logger LOGGER = Logger.getLogger(QuestionDaoImpl.class);

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.QuestionDao#addQuestion(in.ac.coep.entity.Question)
	 */
	@Override
	public long addQuestion(Question question) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Serializable serializable = session.save(question);

			long questionId = (Long) serializable;

			LOGGER.debug("addQuestion  successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return questionId;

		} catch (HibernateException e) {
			LOGGER.error("addQuestion failed", e);
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
	 * @see in.ac.coep.dao.QuestionDao#getQuestionByQuestionId(long)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Question getQuestionByQuestionId(long questionId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

//			Question question = (Question) session.get(Question.class, questionId);
			
			Criteria criteria = session.createCriteria(Question.class,"ques");
			criteria.setFetchMode("ques.answers", FetchMode.JOIN);
			criteria.createAlias("ques.answers", "ans", CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("ques.questionId", questionId));
			
			Question question =  (Question) criteria.uniqueResult();
			
			

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return question;

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
	 * @see in.ac.coep.dao.QuestionDao#fetchQuestions()
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Question> fetchQuestions() throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		List<Question> questions = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();
			Criteria criteria = session.createCriteria(Question.class);

			criteria.add(Restrictions.eq("approved", false));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			questions = criteria.list();

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return questions;

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

//	@SuppressWarnings({ "deprecation", "unchecked" })
//	public List<Section> fetchApprovedQuestions() throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		List<Section> sections = null;
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Section.class, "section");
//
//			detachedCriteria.setFetchMode("section.questions", FetchMode.JOIN);
//			detachedCriteria.createAlias("section.questions", "ques", CriteriaSpecification.LEFT_JOIN);
//			detachedCriteria.add(Restrictions.eq("ques.approved", true));
//
//			detachedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//			sections = detachedCriteria.getExecutableCriteria(session).list();
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return sections;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.dao.QuestionDao#deleteQuestion(in.ac.coep.entity.Question)
	 */
	@Override
	public void deleteQuestion(Question question) throws ConstraintViolationException,Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.delete(question);

			LOGGER.debug("remove successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch(ConstraintViolationException constraintViolationException){
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
			LOGGER.error("This question can not be modified or delete.", constraintViolationException);
			throw new ConstraintViolationException("This question can not be modified or delete.", constraintViolationException.getSQLException(),
					"Username");
		}
		catch (HibernateException e) {
			LOGGER.error("remove failed", e);
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
	 * @see in.ac.coep.dao.QuestionDao#getQuestionByQuestionAndSectionId(long)
	 */
//	@SuppressWarnings({ "deprecation", "unchecked" })
//	@Override
//	public List<Section> getQuestionByQuestionAndSectionId(long questionId) throws Exception {
//
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		List<Section> sections = null;
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Section.class, "section");
//
//			detachedCriteria.setFetchMode("section.questions", FetchMode.JOIN);
//			detachedCriteria.createAlias("section.questions", "ques", CriteriaSpecification.LEFT_JOIN);
//
//			detachedCriteria.add(Restrictions.eq("ques.questionId", questionId));
//
//			detachedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//			sections = detachedCriteria.getExecutableCriteria(session).list();
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return sections;
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

	@Override
	public void updateQuestion(Question question) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.update(question);
			LOGGER.debug("merge successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.debug("merge failed", e);
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
	public int deleteQuestionBySQLQuery(String query, long questionId, long secId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Query sql = session.createSQLQuery(query);
			sql.setLong("QID", questionId);
			sql.setLong("SID", secId);

			int result = sql.executeUpdate();

			// session.delete(question);
			LOGGER.debug("remove successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return result;

		} catch (HibernateException e) {
			LOGGER.error("remove failed", e);
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
	 * @see in.ac.coep.dao.QuestionDao#getQuestionsByQuestionGroupId(long)
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Question> getQuestionsByQuestionGroupId(long questionGroupId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		List<Question> questions = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();
			Criteria criteria = session.createCriteria(Question.class,"question");
			criteria.setFetchMode("question.answers", FetchMode.JOIN);
			criteria.createAlias("question.answers", "ans", CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("question.questionGroup.questionGroupId", questionGroupId));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			questions = criteria.list();

			LOGGER.debug("fetch Question successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return questions;

		} catch (HibernateException e) {
			LOGGER.debug("fetch Question failed", e);
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
