package in.ac.coep.daoImpl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.ForgotPasswordDao;
import in.ac.coep.entity.User;

@Service
public class ForgotPasswordDaoImpl implements ForgotPasswordDao{

	
	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;
	
	@Override
	public long chekForgetPassData(Long uid, String emailId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();
			Criteria criteria = session.createCriteria(User.class, "user")
					.add(Restrictions.eq("user.userId", uid))
					.add(Restrictions.eq("user.emailId", emailId))
					.setProjection(Projections.rowCount());
			long result = (Long) criteria.uniqueResult();
			System.out.println("result:" + result);

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();
			return result;
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
