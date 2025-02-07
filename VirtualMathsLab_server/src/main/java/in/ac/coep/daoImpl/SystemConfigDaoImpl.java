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

import in.ac.coep.dao.SystemConfigDao;
import in.ac.coep.entity.SystemConfig;

/**
 * @author Prashant
 *
 */
@Repository
@Transactional
public class SystemConfigDaoImpl implements SystemConfigDao {

	private static final Logger LOGGER = Logger.getLogger(SystemConfigDaoImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.dao.SystemConfigDao#fetchAllRecordsFromSystemConfig()
	 */
	@Override
	public List<SystemConfig> fetchAllRecordsFromSystemConfig() throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(SystemConfig.class);
			@SuppressWarnings("unchecked")
			List<SystemConfig> list = criteria.list();

			LOGGER.debug("fetch SystemConfig successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return list;

		} catch (HibernateException e) {
			LOGGER.debug("fetch SystemConfig", e);
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
	public SystemConfig getcurrentItoregistrationId() throws Exception {
		// TODO Auto-generated method stub
		
		
		
		Session session = null;
		Transaction tx = null;

		SystemConfig itorid = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(SystemConfig.class);
			criteria.add(Restrictions.eq("configKey", "CURRENT_MORID"));
			itorid = (SystemConfig) criteria.uniqueResult();
			
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

		return itorid;
		
	}

	@Override
	public void update(SystemConfig sysconfig) throws Exception {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.update(sysconfig);

			LOGGER.debug("SystemConfig saved successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.error("SystemConfig saved failed", e);
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
