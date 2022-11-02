package in.ac.coep.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import in.ac.coep.entity.Cities;
import in.ac.coep.entity.ContributorInfo;
import in.ac.coep.entity.ParentInfo;
import in.ac.coep.entity.Roles;
import in.ac.coep.entity.StudentInfo;
import in.ac.coep.entity.TeacherInfo;
import in.ac.coep.entity.User;


public interface UserDao extends UserDetailsService{

	
	public User getUserById(long userId) throws Exception;
	
	public User getUserByEmailId(String emailId)throws Exception;
		
//	public User getUserById(long userId);
	
	public User addUser(User user)throws Exception;
	
	public void updateUSer(User user)throws Exception;

	public User getUserByIdOnly(long userId);

	public void deleteUser(User user);

	public List<User> fetchUsers();

	public Roles getRolesByName(String itoadmin) throws Exception;

	public List<User> listofUserbyRoleID(long id)throws Exception;

	public List<User> getAllIndustrialAdminAndMentors()throws Exception;

	public List<User> getAllUserList() throws Exception;
//	public List<User> getAllStudentsList()throws Exception;

	public List<User> getAllUsersWithoutPayment()throws Exception;

	public List<User> getAllStudentIdCardDetails()throws Exception;

	public User getUserByLoginEmailId(String username)throws Exception;

	public List<User> getAllUserListExceptStudent()throws Exception;

	public List<Roles> getallRoles()throws Exception;

	public List<User> getUsersWithContributorRequest()throws Exception;

	public List<User> getUsersWithAccountLockedStatus()throws Exception;

	public User getUserByLoginUserId(long userId)throws Exception;

	public Cities getCityNameByCityId(int cityId)throws Exception;

	public void saveContributorInfo(ContributorInfo contr)throws Exception;

	public void saveTeacherInfo(TeacherInfo teach)throws Exception;

	public void saveStudentInfo(StudentInfo stud)throws Exception;

	public void saveParentInfo(ParentInfo pi)throws Exception;

	public ContributorInfo getContributorInfoByUserId(long userId)throws Exception;

	public TeacherInfo getTeacherInfoByUserId(long userId)throws Exception;

	public StudentInfo getStudentInfoByUserId(long userId)throws Exception;

	public ParentInfo getParentInfoByUserId(long userId)throws Exception;

	public void updateContributorInfo(ContributorInfo con)throws Exception;

	public void updateTeacherInfo(TeacherInfo teach)throws Exception;

	public void updateStudentInfo(StudentInfo stud)throws Exception;

	public void updateParentInfo(ParentInfo parInfo)throws Exception;
	
}
