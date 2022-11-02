//package in.ac.coep.daoImpl;
//
//import java.io.Serializable;
//
//import javax.annotation.Resource;
//
//import org.apache.log4j.Logger;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import in.ac.coep.dao.MediaDao;
//import in.ac.coep.entity.Media;
//
//
//@Repository
//@Transactional
//public class MediaDaoImpl implements MediaDao {
//	
//	private static final Logger LOGGER = Logger.getLogger(MediaDaoImpl.class);
//	
//	@Resource(name = "sessionFactory")
//	SessionFactory sessionFactory;
//	
//	@Override
//	public Media getMediaById(int mediaId) throws Exception {
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Media media = (Media) session.get(Media.class, mediaId);
//
////			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return media;
//
//		} catch (HibernateException e) {
////			LOGGER.debug("fetch failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see in.ac.coep.dao.MediaDao#saveMedia(in.ac.coep.entity.Media)
//	 */
//	@Override
//	public int saveMedia(Media media) throws Exception {
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//			LOGGER.info("save Start");
//			
//			Serializable serializable = session.save(media);
//			
//			int mediaId = (Integer) serializable;
//
//			LOGGER.info("save successful");
//			
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//			
//			return mediaId;
//			
//
//		} catch (HibernateException e) {
//			LOGGER.error("save failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//
//		}
//	}
//
//}
