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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ac.coep.dao.ComplexityLevelDao;
import in.ac.coep.entity.QGComplexityLevel;

/**
 * @author Prashant
 *
 */

@Repository
@Transactional
public class ComplexityLevelDaoImpl implements ComplexityLevelDao {
	
	private static final Logger LOGGER = Logger.getLogger(ComplexityLevelDaoImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	

	/* (non-Javadoc)
	 * @see in.ac.coep.dao.ComplexityLevelDao#fetchComplexityLevel()
	 */
	@Override
	public List<QGComplexityLevel> fetchComplexityLevel() throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria  criteria = session.createCriteria(QGComplexityLevel.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			@SuppressWarnings("unchecked")
			List<QGComplexityLevel> complexityLevels = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return complexityLevels;

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
