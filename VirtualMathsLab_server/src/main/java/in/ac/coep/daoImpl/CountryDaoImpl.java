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

import in.ac.coep.dao.CountryDao;
import in.ac.coep.entity.Cities;
import in.ac.coep.entity.Countries;
import in.ac.coep.entity.States;

@Repository
@Transactional
public class CountryDaoImpl implements CountryDao {
	
	private static final Logger LOGGER = Logger.getLogger(CountryDaoImpl.class);

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	@Override
	public List<Countries> fetchAllCountries() throws Exception {
		// TODO Auto-generated method stub
		
		
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria  criteria = session.createCriteria(Countries.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			@SuppressWarnings("unchecked")
			List<Countries> countries = criteria.list();

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return countries;

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
	public List<States> fetchAllStates(int id) throws Exception {
		// TODO Auto-generated method stub
		
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria  criteria = session.createCriteria(States.class);
			
			criteria.add(Restrictions.eq("country_id", id));
			
			
			
			@SuppressWarnings("unchecked")
			List<States> states = criteria.list();
			
			

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return states;

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
	public List<Cities> fetchAllCity(int id) throws Exception {
		// TODO Auto-generated method stub
		
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria  criteria = session.createCriteria(Cities.class);
			
			criteria.add(Restrictions.eq("state_id", id));
			
			
			
			@SuppressWarnings("unchecked")
			List<Cities> city = criteria.list();
			
			

			LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return city;

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
