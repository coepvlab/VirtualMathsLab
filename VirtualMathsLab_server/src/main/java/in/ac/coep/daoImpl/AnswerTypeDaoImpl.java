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

import in.ac.coep.dao.AnserTypeDao;
import in.ac.coep.entity.AnswerType;

@Repository
@Transactional
public class AnswerTypeDaoImpl implements AnserTypeDao{
	
	private static final Logger LOGGER = Logger.getLogger(AnswerTypeDaoImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public List<AnswerType> fetchAnswerType() throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria  criteria = session.createCriteria(AnswerType.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			@SuppressWarnings("unchecked")
			List<AnswerType> answerType = criteria.list();

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return answerType;

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

}
