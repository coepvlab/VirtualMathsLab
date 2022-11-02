///**
// * 
// */
//package in.ac.coep.daoImpl;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.apache.log4j.Logger;
//import org.hibernate.Criteria;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.criterion.Restrictions;
//import org.hibernate.exception.ConstraintViolationException;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import in.ac.coep.dao.SectionDao;
//import in.ac.coep.entity.Section;
//
///**
// * @author Prashant
// *
// */
//@Repository
//@Transactional
//public class SectionDaoImpl implements SectionDao {
//
//	private static final Logger LOGGER = Logger.getLogger(SectionDaoImpl.class);
//
//	@Resource(name = "sessionFactory")
//	SessionFactory sessionFactory;
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see in.ac.coep.dao.SectionDao#addSection(in.ac.coep.pojo.Section)
//	 */
//	@Override
//	public void addSection(Section section) throws ConstraintViolationException, Exception {
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
//			session.save(section);
//			LOGGER.debug("save successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//		} catch (ConstraintViolationException exception) {
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//			LOGGER.error("Section already exist", exception);
//			throw new ConstraintViolationException("Section already exist", exception.getSQLException(), "sectionName");
//		}
//
//		catch (HibernateException e) {
//			e.printStackTrace();
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see in.ac.coep.dao.SectionDao#deleteSection(Section)
//	 */
//	@Override
//	public void deleteSection(Section section) throws Exception {
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
//			session.delete(section);
//			LOGGER.debug("remove successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//		} catch (HibernateException e) {
//			LOGGER.error("remove failed", e);
//			if (tx != null)
//				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
//
//			throw new Exception(e);
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see in.ac.coep.dao.SectionDao#getSectionBySectionId(long)
//	 */
//	@Override
//	public Section getSectionBySectionId(int secId) throws Exception {
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
//			Section section = (Section) session.get(Section.class, secId);
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return section;
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
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
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see in.ac.coep.dao.SectionDao#updateSection(in.ac.coep.entity.Section)
//	 */
//	@Override
//	public void updateSection(Section section) throws Exception {
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
//			session.saveOrUpdate(section);
//			LOGGER.debug("merge successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//		} catch (HibernateException e) {
//			LOGGER.debug("merge failed", e);
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
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see in.ac.coep.dao.SectionDao#fetchSections(long)
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Section> fetchSections(int deptId) throws Exception {
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		List<Section> sections = null;
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(Section.class,"section");
//			if (deptId != 0) {
//				criteria.add(Restrictions.eq("section.department.departmentId", deptId));
//			}
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//
//			sections = criteria.list();
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return sections;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
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
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Section> fetchSectionsHavingAtleastOneQuestion(int deptId)throws Exception {
//		// TODO Auto-generated method stub
//		Session session = null;
//		Transaction tx = null;
//
//		List<Section> sections = null;
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//
//			session.flush();
//			session.clear();
//
//			Criteria criteria = session.createCriteria(Section.class,"section");
//			if (deptId != 0) {
//				criteria.add(Restrictions.eq("section.department.departmentId", deptId));
//			}
//			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//
//			sections = criteria.list();
//
//			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
//			tx.commit();
//			session.close();
//
//			return sections;
//
//		} catch (HibernateException e) {
//			LOGGER.debug("fetch failed", e);
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
