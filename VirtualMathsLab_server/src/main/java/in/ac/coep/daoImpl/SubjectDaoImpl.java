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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ac.coep.dao.SubjectDao;
import in.ac.coep.entity.Subject;

/**
 * @author Prashant
 *
 */
@Repository
@Transactional
public class SubjectDaoImpl implements SubjectDao{

	
	private static final Logger LOGGER = Logger.getLogger(SubjectDaoImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void save(Subject subject) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.save(subject);

			LOGGER.debug("Subject saved successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.error("Subject saved failed", e);
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
	public List<Subject> getAllSubjects() throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<Subject> subjects = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(Subject.class);

			subjects = criteria.list();

			LOGGER.debug("Subject fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.debug("Subject fetch failed", e);
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

		return subjects;
	}

	@Override
	public Subject getSubjectById(long subjectId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		Subject subject = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(Subject.class);
			criteria.add(Restrictions.eq("subjectId", subjectId));

			subject = (Subject) criteria.uniqueResult();
			

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return subject;

		} catch (HibernateException e) {
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
	
	
}
