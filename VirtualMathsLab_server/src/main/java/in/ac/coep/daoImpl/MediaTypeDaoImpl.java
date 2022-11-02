package in.ac.coep.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ac.coep.dao.MediaTypeDao;
import in.ac.coep.entity.MediaType;

@Repository
@Transactional
public class MediaTypeDaoImpl implements MediaTypeDao {

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	@Override
	public MediaType getMediaTypeById(int mediaTypeId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			MediaType mediaType = (MediaType) session.get(MediaType.class, mediaTypeId);

			// LOGGER.debug("fetch successful");
			session.flush();
			session.clear();
			tx.commit();
			session.close();
	
			return mediaType;

		} catch (HibernateException e) {
			// LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}

	@Override
	public List<MediaType> fetchMediaType() throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.flush();
			session.clear();

			Criteria  criteria = session.createCriteria(MediaType.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			@SuppressWarnings("unchecked")
			List<MediaType> mediaType = criteria.list();

			session.flush();
			session.clear();
			tx.commit();
			session.close();

			return mediaType;

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			if (session != null && session.isOpen())
				session.close();

			throw new Exception(e);

		}
	}
}
