package in.ac.coep.daoImpl;

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

import in.ac.coep.dao.RoleDao;
import in.ac.coep.entity.Roles;


@Repository(value = "RoleDaoImpl")
@Transactional
public class RoleDaoImpl implements RoleDao {
	
	private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	@Override
	public Roles getRoleByRoleName(String roleName) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		
		Roles roles  = null;
		
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			LOGGER.info("fetch role start ");
			Criteria criteria = session.createCriteria(Roles.class);
			criteria.add(Restrictions.eq("roleName", roleName));

			roles = (Roles) criteria.uniqueResult();
			LOGGER.info("fetch role end ");
			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return roles;

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);
		}
		
		
	
	}
	
	

}
