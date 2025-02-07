package in.ac.coep.daoImpl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ac.coep.dao.UserDao;
import in.ac.coep.entity.Cities;
import in.ac.coep.entity.ContributorInfo;
import in.ac.coep.entity.ParentInfo;
import in.ac.coep.entity.Roles;
import in.ac.coep.entity.StudentInfo;
import in.ac.coep.entity.TeacherInfo;
import in.ac.coep.entity.User;

@Repository(value = "userDaoImpl")
@Transactional
public class UserDaoImpl implements UserDao {

	private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	@Override
	public User addUser(User user) throws ConstraintViolationException, Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.save(user);
			LOGGER.debug("save successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return user;
			
		} catch (ConstraintViolationException exception) {
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
			LOGGER.error("User with this mailId already exist", exception);
			throw new ConstraintViolationException("User with this mailId already exist", exception.getSQLException(),
					"Username");
		}

		catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

			throw new Exception(e);
		} finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
		

	}

	@Override
	public UserDetails loadUserByUsername(String arg) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetails user = null;

		try {
			if (arg.matches("\\d+")) {
				user = getUserById(Long.parseLong(arg));

			} else {
				user = getUserByEmailId(arg);
			}
			if (user == null) {
				throw new BadCredentialsException("Invalid Username");
			}
			return user;
		} catch (NumberFormatException e) {

			return null;
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid Username");
		}
	}

	@Override
	public User getUserById(long userId) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			User user = (User) session.get(User.class, userId);

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return user;

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

	@Override
	public User getUserByEmailId(String emailId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		User user = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("emailId", emailId));

			user = (User) criteria.uniqueResult();

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return user;

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

			throw new Exception(e);
		} finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

	}

	@Override
	public void updateUSer(User user) throws ConstraintViolationException, Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.update(user);
			LOGGER.debug("update successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (ConstraintViolationException exception) {
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();
			LOGGER.error("User with this mailId already exist", exception);
			throw new ConstraintViolationException("User with this mailId already exist", exception.getSQLException(),
					"Username");
		}

		catch (HibernateException e) {
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

	@Override
	public User getUserByIdOnly(long userId) {
		// TODO Auto-generated method stub

		LOGGER.info("chek id ........" + userId);

		Session session = null;
		Transaction tx = null;

		User user = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("userId", userId));
			user = (User) criteria.uniqueResult();
			LOGGER.info("chek user data by id........... " + user.toString());
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

		return user;

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.delete(user);
			LOGGER.debug("remove successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.error("remove failed", e);
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> fetchUsers() {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<User> users = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.ne("userType", "Admin"));

			users = criteria.list();

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Roles getRolesByName(String Role) throws Exception {
		Roles role = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();
			Iterator<Roles> roles = session.createCriteria(Roles.class)
					.add(Restrictions.eq("roleName", Role)).list()
					.iterator();
			while (roles.hasNext()) {
				role = roles.next();
			}
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();
			return role;

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

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<User> listofUserbyRoleID(long id) throws Exception {
		// TODO Auto-generated method stub
		
		Session session = null;
		Transaction tx = null;

		List<User> userss = null;
		
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
//			session.flush();
//			session.clear();
			
			DetachedCriteria crt = DetachedCriteria.forClass(User.class,
					"users");
			crt.setFetchMode("users.roles", FetchMode.JOIN);
			
			crt.createAlias("users.roles", "rols",CriteriaSpecification.LEFT_JOIN);
			
			crt.add(Restrictions.eq("rols.roleId", id));
			
			crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			userss = crt.getExecutableCriteria(session).list();
			
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
		
		return userss;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllIndustrialAdminAndMentors() throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<User> users = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("IsAdmin", true));

			users = criteria.list();

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUserList() throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<User> users = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.ne("IsAdmin", true));

			users = criteria.list();

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsersWithoutPayment() throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<User> users = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.ne("IsAdmin", true));
			criteria.add(Restrictions.ne("IsPaymentDone", true));

			users = criteria.list();

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllStudentIdCardDetails() throws Exception {
		Session session = null;
		Transaction tx = null;

		List<User> users = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.isNotNull("schoolId"));
			users = criteria.list();

			LOGGER.debug("fetch successful");
//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

		} catch (HibernateException e) {
			LOGGER.debug("fetch failed", e);
			if (tx != null)
				tx.rollback();
//			if (session != null && session.isOpen())
//				session.close();

		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

		return users;
	}

	@Override
	public User getUserByLoginEmailId(String username) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		User user = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("loginEmailId", username));

			user = (User) criteria.uniqueResult();

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return user;

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

	@Override
	public void saveParentInfo(ParentInfo pi) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.save(pi);
			LOGGER.debug("save successful");
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

			throw new Exception(e);
		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

	}

	@Override
	public ParentInfo getParentInfoByUserId(long userId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		ParentInfo pi = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(ParentInfo.class);
			criteria.add(Restrictions.eq("userId.userId", userId));

			pi = (ParentInfo) criteria.uniqueResult();

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return pi;

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

	@Override
	public void updateParentInfo(ParentInfo parInfo) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.update(parInfo);
			LOGGER.debug("update successful");
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

			throw new Exception(e);
		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUserListExceptStudent() throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<User> user = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.ne("userType", "Student"));
			criteria.add(Restrictions.ne("userType", "Admin"));

			user = criteria.list();

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return user;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Roles> getallRoles() throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<Roles> role = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(Roles.class);

			role = criteria.list();

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return role;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersWithContributorRequest() throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<User> user = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("credentialsExpired", true));
			criteria.add(Restrictions.eq("userType", "Contributor"));

			user = criteria.list();

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return user;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersWithAccountLockedStatus() throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		List<User> user = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("accountLocked", true));

			user = criteria.list();

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return user;

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

	@Override
	public User getUserByLoginUserId(long userId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		User user = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("userId", userId));

			user = (User) criteria.uniqueResult();

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return user;

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

	@Override
	public Cities getCityNameByCityId(int cityId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		Cities city = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(Cities.class);
			criteria.add(Restrictions.eq("id", cityId));

			city = (Cities) criteria.uniqueResult();

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return city;

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

	@Override
	public void saveContributorInfo(ContributorInfo contr) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.save(contr);
			LOGGER.debug("save successful");
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

			throw new Exception(e);
		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

	}

	@Override
	public void saveTeacherInfo(TeacherInfo teach) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.save(teach);
			LOGGER.debug("save successful");
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

			throw new Exception(e);
		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

	}

	@Override
	public void saveStudentInfo(StudentInfo stud) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.save(stud);
			LOGGER.debug("save successful");
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

			throw new Exception(e);
		} finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

	}

	@Override
	public ContributorInfo getContributorInfoByUserId(long userId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		ContributorInfo con = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(ContributorInfo.class);
			criteria.add(Restrictions.eq("userId.userId", userId));

			con = (ContributorInfo) criteria.uniqueResult();

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return con;

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

	@Override
	public TeacherInfo getTeacherInfoByUserId(long userId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		TeacherInfo teach = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(TeacherInfo.class);
			criteria.add(Restrictions.eq("userId.userId", userId));

			teach = (TeacherInfo) criteria.uniqueResult();

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return teach;

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

	@Override
	public StudentInfo getStudentInfoByUserId(long userId) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;

		StudentInfo stud = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			Criteria criteria = session.createCriteria(StudentInfo.class);
			criteria.add(Restrictions.eq("userId.userId", userId));

			stud = (StudentInfo) criteria.uniqueResult();

//			session.flush();
//			session.clear();
			tx.commit();
//			session.close();

			return stud;

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

	@Override
	public void updateContributorInfo(ContributorInfo con) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.update(con);
			LOGGER.debug("update successful");
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

			throw new Exception(e);
		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

	}

	@Override
	public void updateTeacherInfo(TeacherInfo teach) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.update(teach);
			LOGGER.debug("update successful");
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

			throw new Exception(e);
		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

	}

	@Override
	public void updateStudentInfo(StudentInfo stud) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

//			session.flush();
//			session.clear();

			session.update(stud);
			LOGGER.debug("update successful");
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

			throw new Exception(e);
		}finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

	}

}
