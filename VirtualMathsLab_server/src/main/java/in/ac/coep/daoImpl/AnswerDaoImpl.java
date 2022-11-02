/**
 * 
 */
package in.ac.coep.daoImpl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.codehaus.plexus.component.annotations.Component;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.ac.coep.dao.AnswerDao;
import in.ac.coep.entity.Answers;

/**
 * @author Prashant
 *
 */
@Repository
@Transactional
public class AnswerDaoImpl implements AnswerDao {

	private static final Logger LOGGER = Logger.getLogger(AnswerDaoImpl.class);

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.AnswerDao#addAnswer(in.ac.coep.entity.Answers)
	 */
	@Override
	public long addAnswer(Answers answer) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Serializable serializable = session.save(answer);

			long answerId = (Long) serializable;

			LOGGER.debug("addAnswer  successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return answerId;

		} catch (HibernateException e) {
			LOGGER.error("addAnswer failed", e);
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
	 * @see in.ac.coep.dao.AnswerDao#getAllAnswersByQuestionId(long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Answers> getAllAnswersByQuestionId(long questionId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		List<Answers> answers = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();
			Criteria criteria = session.createCriteria(Answers.class, "answer");

			criteria.add(Restrictions.eq("answer.question.questionId", questionId));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			answers = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return answers;

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
	 * @see in.ac.coep.dao.AnswerDao#deleteAnswer(in.ac.coep.entity.Answers)
	 */
	@Override
	public void deleteAnswer(Answers answers2) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			session.delete(answers2);

			LOGGER.debug("remove successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.error("remove failed", e);
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
	 * @see in.ac.coep.dao.AnswerDao#getAnswerById(long)
	 */
	@Override
	public Answers getAnswerById(long ansId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();
			Answers answers = (Answers) session.get(Answers.class, ansId);

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return answers;

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
