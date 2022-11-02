/**
 * 
 */
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

import in.ac.coep.dao.UtilityServiceDao;
import in.ac.coep.entity.MathsFileStorage;

/**
 * @author Prashant
 *
 */

@Repository
@Transactional
public class UtilityServiceDaoImpl implements UtilityServiceDao{

	
	private static final Logger LOGGER = Logger.getLogger(UtilityServiceDaoImpl.class);
	
	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	@Override
	public void save(MathsFileStorage fileStorage) throws Exception {
		// TODO Auto-generated method stub
		
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			session.save(fileStorage);

			LOGGER.debug("file deatils saved successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			LOGGER.error("Saving file deatils failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MathsFileStorage> getAllFiles() throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<MathsFileStorage> fileStorage = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria criteria = session.createCriteria(MathsFileStorage.class);
			criteria.add(Restrictions.eq("fileExt", "java"));

			fileStorage = criteria.list();

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

		}

		return fileStorage;
	}

}
