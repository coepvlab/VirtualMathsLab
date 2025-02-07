/**
 * 
 */
package in.ac.coep.daoImpl;

import java.io.Serializable;

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

import in.ac.coep.dao.QuesGroupMediaLinksDao;
import in.ac.coep.entity.QuesGroupMediaLinks;

/**
 * @author Prashant
 *
 */

@Repository
@Transactional
public class QuesGroupMediaLinksDaoImpl implements QuesGroupMediaLinksDao {

	private static final Logger LOGGER = Logger.getLogger(QuesGroupMediaLinksDaoImpl.class);

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	
	@Override
	public long addQuesGroupMediaLinks(QuesGroupMediaLinks mediaLinks) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Serializable serializable = session.save(mediaLinks);

			long quesGroupMediaLinkId = (Long) serializable;

			LOGGER.debug("addQuesGroupMediaLink  successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return quesGroupMediaLinkId;

		} catch (HibernateException e) {
			LOGGER.error("addQuesGroupMediaLink failed", e);
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
	public QuesGroupMediaLinks getQGMedilLinkById(long quesGroupMediaLinkId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		QuesGroupMediaLinks quesGroupMediaLinks = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(QuesGroupMediaLinks.class);
			criteria.add(Restrictions.eq("quesGroupMediaLinkId", quesGroupMediaLinkId));

			quesGroupMediaLinks = (QuesGroupMediaLinks) criteria.uniqueResult();
			

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return quesGroupMediaLinks;

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


	@Override
	public void updateQuesGroupMediaLinks(QuesGroupMediaLinks quesGroupMediaLinks) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.update(quesGroupMediaLinks);

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


	@Override
	public QuesGroupMediaLinks getQGMedilLinkByMediaId(long quesGroupMediaLinks) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		QuesGroupMediaLinks qgml= null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(QuesGroupMediaLinks.class);
			criteria.add(Restrictions.eq("quesGroupMediaLinkId", quesGroupMediaLinks));

			qgml = (QuesGroupMediaLinks) criteria.uniqueResult();
			

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return qgml;

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


	@Override
	public void deleteQuesGroupMediaLink(QuesGroupMediaLinks qgml) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.delete(qgml);

			LOGGER.debug("Subject deleted");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.error("Subject delete failed", e);
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
