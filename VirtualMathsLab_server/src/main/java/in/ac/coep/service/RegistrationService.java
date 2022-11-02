package in.ac.coep.service;

import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONObject;

import in.ac.coep.entity.User;
import in.ac.coep.vo.ContributorInfoVO;
import in.ac.coep.vo.ParentInfoVO;
import in.ac.coep.vo.StudentInfoVO;
import in.ac.coep.vo.TeacherInfoVO;
import in.ac.coep.vo.UserVO;

public interface RegistrationService {

	public JSONObject insertUserInDatabase(UserVO userVO, ContributorInfoVO contVO, TeacherInfoVO teachVO, StudentInfoVO studVO, ParentInfoVO parVO) throws ConstraintViolationException, Exception ;

	public JSONObject fetchUser(User user)throws Exception;

	public JSONObject updateUser(String userProfileJson)throws Exception;

	public JSONObject fetchAllUsersExceptStudent()throws Exception;

	public JSONObject updateUserRoles(String manageRoleJson)throws Exception;

	public JSONObject doEmailVerification(String token)throws Exception;

	public JSONObject getAllContributorRequest()throws Exception;

	public JSONObject setApprovedContribRequest(long userId)throws Exception;

	public JSONObject getAccountLockedUsers()throws Exception;

	public JSONObject getAccountUnlocked(long userId)throws Exception;

	public JSONObject getAllRolesForThisUser(long userId)throws Exception;


}
