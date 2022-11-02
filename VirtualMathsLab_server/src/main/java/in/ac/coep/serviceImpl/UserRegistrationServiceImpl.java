package in.ac.coep.serviceImpl;

import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.RoleDao;
import in.ac.coep.dao.UserDao;
import in.ac.coep.entity.User;
import in.ac.coep.service.UserRegistrationService;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	private static final Logger LOGGER = Logger.getLogger(UserRegistrationServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Resource(name = "sessionRegistry")
	private SessionRegistryImpl sessionRegistryImpl;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	@SuppressWarnings("unused")
	@Override
	public JSONObject forceFullyLogoutUserByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		Iterator<org.springframework.security.core.session.SessionInformation> sessions = null;
		if (email != null) {
			Iterator<Object> iter = sessionRegistryImpl.getAllPrincipals().iterator();
			Object principal = null;
//			String sessionId = "";

			org.springframework.security.core.session.SessionInformation sinfo = null;
			while (iter.hasNext()) {
				principal = iter.next();
				String username = ((User) principal).getUsername();
				if (email != null) {
					if (email.equals(username)) {
						if (principal != null) {
							sessions = sessionRegistryImpl.getAllSessions(principal, false).iterator();
							while (sessions.hasNext()) {
								sinfo = sessions.next();
								String sessionId = sinfo.getSessionId();
								sinfo.expireNow();
							}
							jsonObject.put("done", true);
							jsonObject.put("msg", "User Logged out successfully");
						}
					}
				}
			}
			jsonObject.put("done", true);
			jsonObject.put("msg", "User does not exist");
			return jsonObject;
		} else {
			jsonObject.put("done", false);
			jsonObject.put("msg", "Something went wrong");
			return jsonObject;
		}
	}
}
