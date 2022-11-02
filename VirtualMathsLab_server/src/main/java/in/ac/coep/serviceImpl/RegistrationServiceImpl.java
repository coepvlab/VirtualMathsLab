package in.ac.coep.serviceImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import in.ac.coep.constants.Constants;
import in.ac.coep.dao.RoleDao;
import in.ac.coep.dao.UserDao;
import in.ac.coep.entity.ContributorInfo;
import in.ac.coep.entity.Medium;
import in.ac.coep.entity.ParentInfo;
import in.ac.coep.entity.Roles;
import in.ac.coep.entity.Standard;
import in.ac.coep.entity.StudentInfo;
import in.ac.coep.entity.TeacherInfo;
import in.ac.coep.entity.User;
import in.ac.coep.service.CountryService;
import in.ac.coep.service.RegistrationService;
import in.ac.coep.utility.EncoderDecoder;
import in.ac.coep.utility.MailSenderForRegistration;
import in.ac.coep.utility.Md5Encryption;
import in.ac.coep.vo.ContributorInfoVO;
import in.ac.coep.vo.ParentInfoVO;
import in.ac.coep.vo.StudentInfoVO;
import in.ac.coep.vo.TeacherInfoVO;
import in.ac.coep.vo.UserVO;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Autowired
	private TemplateEngine classtemplateEngine;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public JSONObject insertUserInDatabase(UserVO userVO, ContributorInfoVO contVO, TeacherInfoVO teachVO, StudentInfoVO studVO, ParentInfoVO parVO) throws ConstraintViolationException, Exception {
		// TODO SAVE OR ADD TEACHER/CONTRIBUTOR/STUDENT/PARENT USER
		JSONObject data = new JSONObject();

		if (userVO != null) {

			User getUser = userDao.getUserByEmailId(userVO.getEmailId());

			if (getUser != null) {
				data.put("done", false);
				data.put("msg", "User with this Email Id is already registered");
			} else {
				User user = new User();
				Roles roles = null;
				user.setEmailId(userVO.getEmailId());
				user.setFirstName(userVO.getFirstName());
				user.setMiddleName(userVO.getMiddleName());
				user.setLastName(userVO.getLastName());
				user.setPhoneNumber(userVO.getPhoneNumber());
				user.setWhatsAppNo(userVO.getWhatsAppNo());
				user.setGender(userVO.getGender());
				user.setPassword(Md5Encryption.md5(userVO.getPassword()));
				user.setPincode(userVO.getPincode());
				Date date = new Date(userVO.getDateOfBirth());
				user.setDateOfBirth(date);
				user.setCityId(userVO.getCityId());
				user.setStateId(userVO.getStateId());
				user.setCountryId(101);
				user.setUserType(userVO.getUserType());
				
				Standard standard = new Standard();
				standard.setStdId(1);
				user.setStandard(standard);
				
				userVO.setCreatedDate(System.currentTimeMillis());
				userVO.setUpdatedDate(System.currentTimeMillis());
				
				Date cT = new Date(userVO.getCreatedDate());
				user.setCreatedDate(cT);

				Date uT = new Date(userVO.getUpdatedDate());
				user.setUpdatedDate(uT);
				user.setTermsAndConditionsAccepted(true);
				user.setEmailValidated(false);	
				user.setDeleted(true);	
				user.setAccountExpired(false);		
				user.setAccountLocked(true);
				user.setCredentialsExpired(false);
				
				if(userVO.getUserType().equals("Contributor")) {
					ContributorInfo contr = new ContributorInfo();
					contr.setSchoolName(contVO.getSchoolName());
					contr.setSchoolAdd(contVO.getSchoolAdd());
					contr.setSchoolType(contVO.getSchoolType());
					contr.setExperience(contVO.getExperience());
					contr.setCanContributeInLatex(contVO.getCanContributeInLatex());
					
					Medium medium = new Medium();
					medium.setMediumId(contVO.getMediumId());
					contr.setMedium(medium);
					
					roles = roleDao.getRoleByRoleName("CONTRIBUTOR");
					Set<Roles> set = new HashSet<>();
					if (roles != null) {
						set.add(roles);
					}
					user.setRoles(set);
					
					user.setCredentialsExpired(true);
					User usr = userDao.addUser(user);
					contr.setUserId(usr);
					userDao.saveContributorInfo(contr);
				}

				if(userVO.getUserType().equals("Teacher")) {
					TeacherInfo teach = new TeacherInfo();
					teach.setSchoolName(teachVO.getSchoolName());
					teach.setSchoolAdd(teachVO.getSchoolAdd());
					teach.setSchoolType(teachVO.getSchoolType());
					teach.setExperience(teachVO.getExperience());
					teach.setTeachingGroup(teachVO.getTeachingGroup());
					
					Medium medium = new Medium();
					medium.setMediumId(teachVO.getMediumId());
					teach.setMedium(medium);
					
					roles = roleDao.getRoleByRoleName("TEACHER");
					Set<Roles> set = new HashSet<>();
					if (roles != null) {
						set.add(roles);
					}
					user.setRoles(set);
					
					User usr = userDao.addUser(user);
					teach.setUserId(usr);
					userDao.saveTeacherInfo(teach);
				}
				
				if(userVO.getUserType().equals("Parent")) {
					ParentInfo par = new ParentInfo();
					par.setQualification(parVO.getQualification());
					par.setOccupation(parVO.getOccupation());
					par.setDesignation(parVO.getDesignation());
					par.setServiceLength(parVO.getServiceLength());
					par.setCompanyName(parVO.getCompanyName());
					
					roles = roleDao.getRoleByRoleName("PARENT");
					Set<Roles> set = new HashSet<>();
					if (roles != null) {
						set.add(roles);
					}
					user.setRoles(set);
					
					User usr = userDao.addUser(user);
					par.setUserId(usr);
					userDao.saveParentInfo(par);
				}
				
				if(userVO.getUserType().equals("Student")) {
					StudentInfo stud = new StudentInfo();
					stud.setParEmailId(studVO.getParEmailId());
					stud.setSchoolName(studVO.getSchoolName());
					stud.setSchoolAdd(studVO.getSchoolAdd());
					stud.setSchoolType(studVO.getSchoolType());
					stud.setStandard(studVO.getStandard());
					
					Medium medium = new Medium();
					medium.setMediumId(studVO.getMediumId());
					stud.setMedium(medium);
					
					roles = roleDao.getRoleByRoleName("STUDENT");
					Set<Roles> set = new HashSet<>();
					if (roles != null) {
						set.add(roles);
					}
					user.setRoles(set);
					
					User usr = userDao.addUser(user);
					stud.setUserId(usr);
					userDao.saveStudentInfo(stud);
				}
				

				JSONObject mailData = new JSONObject();
				mailData.put("emailId", userVO.getEmailId());
				mailData.put("userId", user.getUserId());

				String link = user.getEmailId();
				link = EncoderDecoder.encodeString(link);
				link = Constants.baseURLPath + "userProfile/emailVerification?token=" + link;
				mailData.put("link", link);
				
				Thread.sleep(2000);
				Thread t = new Thread(new MailSenderForRegistration(mailData,
						mailSender, classtemplateEngine));
				t.start();
				
				data.put("done", true);
				data.put("msg", "You have registered successfully. A verificaion link has been sent to your registered email account.");
			}
		} else {
			data.put("done", false);
			data.put("msg", "Something went wrong. Please try again");
		}
		return data;
	}

	@Override
	public JSONObject doEmailVerification(String token) throws Exception {
		// TODO email verification
	JSONObject json = new JSONObject();
		
		if (token == null) {
			json.put("valid", "False");
			json.put("msg", "Email Verification URL is invalid.");
		} else {
			
			String decodedToken = EncoderDecoder.decodeString(token);
			
			User user =  userDao.getUserByEmailId(decodedToken);
			
				user.setEmailValidated(true);
				user.setAccountLocked(false);
				
			userDao.updateUSer(user);
			
			json.put("valid", "True");
			json.put("msg", "Redirecting to email verification.");
			json.put("email", decodedToken);
			json.put("name", user.getFirstName()+" "+user.getLastName());
		}
		return json;
	}

	@Override
	public JSONObject getAllContributorRequest() throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();
		JSONArray userArr = new JSONArray();

		List<User> userList = userDao.getUsersWithContributorRequest();

		if (userList.size() != 0) {
			String email = "";
			for (User user : userList) {
				if (email != user.getEmailId()) {
					JSONObject json = new JSONObject();
					json.put("emailId", user.getEmailId());
					json.put("userId", user.getUserId());
					json.put("name", user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());
					userArr.put(json);
					email = user.getEmailId();
				}
			}

			data.put("users", userArr);
			data.put("done", true);
			data.put("msg", "User fetch successfully");

		} else {
			data.put("done", false);
			data.put("msg", "No such pending user request found");
		}
		return data;
	}

	@Override
	public JSONObject setApprovedContribRequest(long userId) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		User user = userDao.getUserById(userId);

		if (user != null) {
			Roles roles = roleDao.getRoleByRoleName("CONTRIBUTOR");

			Set<Roles> set = new HashSet<>();
			if (roles != null) {
				set.add(roles);
			}
			user.setRoles(set);
			user.setCredentialsExpired(false);
			
			userDao.updateUSer(user);
			
			data.put("done", true);
			data.put("msg", "Contributor role set to " + user.getEmailId() + " successfully");
		} else {
			data.put("done", false);
			data.put("msg", "Something went wrong. Please try again!!");
		}

		return data;
	}

	@Override
	public JSONObject getAccountLockedUsers() throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
		JSONArray userArr = new JSONArray();
				
		List<User> userList = userDao.getUsersWithAccountLockedStatus();
		
		if(userList.size() != 0) {
			String email = "";
			for (User user : userList) {
				if (email != user.getEmailId()) {
					JSONObject json = new JSONObject();
					json.put("emailId", user.getEmailId());
					json.put("userId", user.getUserId());
					json.put("name", user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());
					userArr.put(json);
					email = user.getEmailId();
				}
			}

			data.put("users", userArr);			
			data.put("done", true);
			data.put("msg", "user fetch successfully");
		}else {
			data.put("done", false);
			data.put("msg", "No such user found");
		}
		
		return data;
	}

	@Override
	public JSONObject getAccountUnlocked(long userId) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		User user = userDao.getUserById(userId);

		if(user != null) {
			user.setAccountLocked(false);
			user.setEmailValidated(true);
			userDao.updateUSer(user);
			
			data.put("done", true);
			data.put("msg", "Account with email Id " + user.getEmailId() + " activated successfully");
		} else {
			data.put("done", false);
			data.put("msg", "Something went wrong. Please try again!!");
		}

		return data;
	}
	
	@Override
	public JSONObject fetchUser(User user1) throws Exception {
		// TODO FETCH USER
		
		JSONObject data = new JSONObject();
		int roleCnt = 0;
//		fetch common fields by following method for all roles
		User user = userDao.getUserById(user1.getUserId());
		
		JSONObject roleJson = new JSONObject();
		
		Set<Roles> roles = user.getRoles();

		Iterator<Roles> it = roles.iterator();
		while (it.hasNext()) {
			roleJson.put(it.next().getRoleName(), roleCnt);
			roleCnt++;
		}
		
		data = getCommonDataFromUser(data, user);		
		data.put("cities", countryService.fetchAllCity(22));
		
		ContributorInfo conInfo = userDao.getContributorInfoByUserId(user.getUserId());
		TeacherInfo teachInfo = userDao.getTeacherInfoByUserId(user.getUserId());
		StudentInfo studInfo = userDao.getStudentInfoByUserId(user.getUserId());
		ParentInfo pi = userDao.getParentInfoByUserId(user.getUserId());
		
		if (roleJson.has("TEACHER")) {
			JSONObject teachJson = new JSONObject();
			if (teachInfo != null) {
				teachJson.put("schoolName", teachInfo.getSchoolName());
				teachJson.put("schoolType", teachInfo.getSchoolType());
				teachJson.put("medium", teachInfo.getMedium().getMediumId());
				teachJson.put("exp", teachInfo.getExperience());
				teachJson.put("schoolAdd", teachInfo.getSchoolAdd());
				teachJson.put("teachGroup", teachInfo.getTeachingGroup());
				data.put("teacher", teachJson);
			}else {
				teachJson.put("schoolName", "");
				teachJson.put("schoolType", 0);
				teachJson.put("medium", 0);
				teachJson.put("exp", "");
				teachJson.put("schoolAdd", "");
				teachJson.put("teachGroup", 0);
				data.put("teacher", teachJson);
			}
		}
		
		if (roleJson.has("CONTRIBUTOR")) {
			JSONObject contJson = new JSONObject();
			if (conInfo != null) {				
				contJson.put("schoolName", conInfo.getSchoolName());
				contJson.put("schoolType", conInfo.getSchoolType());
				contJson.put("medium", conInfo.getMedium().getMediumId());
				contJson.put("exp", conInfo.getExperience());
				contJson.put("schoolAdd", conInfo.getSchoolAdd());
				contJson.put("canContribInLatex", conInfo.getCanContributeInLatex());
				data.put("contributor", contJson);
			}else {
				contJson.put("schoolName", "");
				contJson.put("schoolType", 0);
				contJson.put("medium", 0);
				contJson.put("exp", "");
				contJson.put("schoolAdd", "");
				contJson.put("canContribInLatex", 0);
				data.put("contributor", contJson);
			}
		}
		
		if (roleJson.has("PARENT")) {
			JSONObject parJson = new JSONObject();
			if (pi != null) {
				parJson.put("qualif", pi.getQualification());
				parJson.put("occup", pi.getOccupation());
				parJson.put("desig", pi.getDesignation());
				parJson.put("servLeng", pi.getServiceLength());
				parJson.put("company", pi.getCompanyName());
				data.put("parent", parJson);
			}else {
				parJson.put("qualif", "");
				parJson.put("occup", "");
				parJson.put("desig", "");
				parJson.put("servLeng", "");
				parJson.put("company", "");
				data.put("parent", parJson);
			}
		}
		
		if (roleJson.has("STUDENT")) {
			JSONObject studJson = new JSONObject();
			if (studInfo != null) {
				studJson.put("parEmailId", studInfo.getParEmailId());
				studJson.put("schoolName", studInfo.getSchoolName());
				studJson.put("schoolAdd", studInfo.getSchoolAdd());
				studJson.put("schoolType", studInfo.getSchoolType());
				studJson.put("standard", studInfo.getStandard());
				studJson.put("medium", studInfo.getMedium().getMediumId());

				studJson.put("faname", studInfo.getF_name());
				studJson.put("fqualif", studInfo.getF_qualification());
				studJson.put("foccup", studInfo.getF_occupation());
				studJson.put("fdesig", studInfo.getF_designation());
				studJson.put("fcompany", studInfo.getF_company());
				studJson.put("fservLeng", studInfo.getF_serviceLength());
				studJson.put("fcity", studInfo.getF_city());
				studJson.put("fage", studInfo.getF_age());

				studJson.put("maname", studInfo.getM_name());
				studJson.put("mqualif", studInfo.getM_qualification());
				studJson.put("moccup", studInfo.getM_occupation());
				studJson.put("mdesig", studInfo.getM_designation());
				studJson.put("mcompany", studInfo.getM_company());
				studJson.put("mservLeng", studInfo.getM_serviceLength());
				studJson.put("mcity", studInfo.getM_city());
				studJson.put("mage", studInfo.getM_age());
				data.put("student", studJson);
			}else {
				studJson.put("parEmailId", "");
				studJson.put("schoolName", "");
				studJson.put("schoolAdd", "");
				studJson.put("schoolType", 0);
				studJson.put("standard", 0);
				studJson.put("medium", 0);

				studJson.put("faname", "");
				studJson.put("fqualif", "");
				studJson.put("foccup", "");
				studJson.put("fdesig", "");
				studJson.put("fcompany", "");
				studJson.put("fservLeng", "");
				studJson.put("fcity", "");
				studJson.put("fage", "");

				studJson.put("maname", "");
				studJson.put("mqualif", "");
				studJson.put("moccup", "");
				studJson.put("mdesig", "");
				studJson.put("mcompany", "");
				studJson.put("mservLeng", "");
				studJson.put("mcity", "");
				studJson.put("mage", "");
				data.put("student", studJson);
			
			}
		}
		
		data.put("roleJson", roleJson);
		return data;
	}

	private JSONObject getCommonDataFromUser(JSONObject data, User user) {
		
		data.put("userId", user.getUserId());
		data.put("emailId", user.getEmailId());
		data.put("fname", user.getFirstName());
		data.put("mname", user.getMiddleName());
		data.put("lname", user.getLastName());
		data.put("ph", user.getPhoneNumber());
		data.put("whatsApp", user.getWhatsAppNo());
		data.put("gender", user.getGender());
		data.put("pincode", user.getPincode());
		data.put("city", user.getCityId());
		data.put("dob", user.getDateOfBirth());
		data.put("url", user.getProfilePicUrl());
		
		return data;
	}

	@Override
	public JSONObject updateUser(String userProfileJson) throws Exception {
		// TODO UPDATE USER
		
		JSONObject data = new JSONObject();
		JSONObject userJson = new JSONObject(userProfileJson);
		int roleCnt = 0;
		
		if(userJson != null) {
			
			User user = userDao.getUserByEmailId(userJson.getString("s_email"));
			
			JSONObject roleJson = new JSONObject();
			
			Set<Roles> roles = user.getRoles();

			Iterator<Roles> it = roles.iterator();
			while (it.hasNext()) {
				roleJson.put(it.next().getRoleName(), roleCnt);
				roleCnt++;
			}
			
//			Common fields for all type of user
			user.setFirstName(userJson.getString("s_fname"));
			user.setMiddleName(userJson.getString("s_mname"));
			user.setLastName(userJson.getString("s_lname"));
			user.setWhatsAppNo(userJson.getString("s_whtap"));
			user.setGender(userJson.getInt("s_gender"));
			user.setPincode(userJson.getLong("s_pincode"));
			user.setCityId(userJson.getInt("s_city"));
			Date date = new Date(userJson.getLong("s_dob"));
			user.setDateOfBirth(date);
			
//			role based remaining fields for teacher
			if(roleJson.has("TEACHER")) {
				TeacherInfo teach = userDao.getTeacherInfoByUserId(user.getUserId());
				if(teach != null) {
					JSONObject teacher = userJson.getJSONObject("teacher");
					teach.setSchoolName(teacher.getString("T_schoolName"));
					teach.setSchoolAdd(teacher.getString("T_SchAddress"));
					teach.setSchoolType(teacher.getString("T_schType"));
					teach.setExperience(teacher.getString("T_exp"));
					teach.setTeachingGroup(teacher.getString("T_teachGroup"));
					
					Medium medium = new Medium();
					medium.setMediumId(teacher.getInt("T_medium"));
					teach.setMedium(medium);
					userDao.updateTeacherInfo(teach);
				}else {
					TeacherInfo teach1 = new TeacherInfo();
					JSONObject teacher = userJson.getJSONObject("teacher");
					teach1.setSchoolName(teacher.getString("T_schoolName"));
					teach1.setSchoolAdd(teacher.getString("T_SchAddress"));
					teach1.setSchoolType(teacher.getString("T_schType"));
					teach1.setExperience(teacher.getString("T_exp"));
					teach1.setTeachingGroup(teacher.getString("T_teachGroup"));
					
					Medium medium = new Medium();
					medium.setMediumId(teacher.getInt("T_medium"));
					teach1.setMedium(medium);
					
					teach1.setUserId(user);
					userDao.saveTeacherInfo(teach1);
				}
			}
			
//			role based remaining fields for contributor
			if(roleJson.has("CONTRIBUTOR")) {
				ContributorInfo con = userDao.getContributorInfoByUserId(user.getUserId());
				
				if(con != null) {
					JSONObject contrib = userJson.getJSONObject("contributor");
					con.setSchoolName(contrib.getString("C_schoolName"));
					con.setSchoolAdd(contrib.getString("C_SchAddress"));
					con.setSchoolType(contrib.getString("C_schType"));
					con.setExperience(contrib.getString("C_exp"));
					con.setCanContributeInLatex(contrib.getString("C_canContrib"));
					
					Medium medium = new Medium();
					medium.setMediumId(contrib.getInt("C_medium"));
					con.setMedium(medium);
				
					userDao.updateContributorInfo(con);
				}else {
					ContributorInfo con1 = new ContributorInfo();
					JSONObject contrib = userJson.getJSONObject("contributor");
					con1.setSchoolName(contrib.getString("C_schoolName"));
					con1.setSchoolAdd(contrib.getString("C_SchAddress"));
					con1.setSchoolType(contrib.getString("C_schType"));
					con1.setExperience(contrib.getString("C_exp"));
					con1.setCanContributeInLatex(contrib.getString("C_canContrib"));
					
					Medium medium = new Medium();
					medium.setMediumId(contrib.getInt("C_medium"));
					con1.setMedium(medium);
				
					con1.setUserId(user);
					userDao.saveContributorInfo(con1);
				}
			}
			
//			role based remaining fields for parent add or update parentInfo
			if(roleJson.has("PARENT")) {
				ParentInfo par = userDao.getParentInfoByUserId(user.getUserId());
				
				if(par != null) {
				JSONObject parent = userJson.getJSONObject("parent");
					par.setQualification(parent.getString("P_qualif"));
					par.setOccupation(parent.getString("P_occup"));
					par.setDesignation(parent.getString("P_desig"));
					par.setCompanyName(parent.getString("P_company"));
					par.setServiceLength(parent.getInt("P_exp"));
					
					userDao.updateParentInfo(par);
				}else {
					ParentInfo par1 = new ParentInfo();
					JSONObject parent = userJson.getJSONObject("parent");
					par1.setQualification(parent.getString("P_qualif"));
					par1.setOccupation(parent.getString("P_occup"));
					par1.setDesignation(parent.getString("P_desig"));
					par1.setCompanyName(parent.getString("P_company"));
					par1.setServiceLength(parent.getInt("P_exp"));
					
					par1.setUserId(user);
					userDao.saveParentInfo(par1);
				}
			}
			
//			role based remaining fields for student
			if(roleJson.has("STUDENT")) {
				StudentInfo stud = userDao.getStudentInfoByUserId(user.getUserId());

					if(stud != null) {
					JSONObject student = userJson.getJSONObject("student");
					stud.setSchoolName(student.getString("s_schoolName"));
					stud.setSchoolAdd(student.getString("s_SchAddress"));
					stud.setSchoolType(student.getString("s_schType"));
					stud.setStandard(student.getInt("s_standard"));
					stud.setParEmailId(student.getString("s_pemail"));
					
					Medium medium = new Medium();
					medium.setMediumId(student.getInt("s_medium"));
					stud.setMedium(medium);
					
					stud.setF_name(student.getString("s_faname"));
					stud.setM_name(student.getString("s_moname"));
					stud.setF_age(student.getInt("s_fage"));
					stud.setM_age(student.getInt("s_mage"));
					stud.setF_qualification(student.getString("s_fqualif"));
					stud.setM_qualification(student.getString("s_mqualif"));
					stud.setF_occupation(student.getString("s_foccup"));
					stud.setM_occupation(student.getString("s_moccup"));
					stud.setF_company(student.getString("s_fcompany"));
					stud.setM_company(student.getString("s_mcompany"));
					stud.setF_designation(student.getString("s_fdesig"));
					stud.setM_designation(student.getString("s_mdesig"));
					stud.setF_serviceLength(student.getString("s_fserviceLength"));
					stud.setM_serviceLength(student.getString("s_mserviceLength"));
					stud.setF_city(student.getInt("fcity"));
					stud.setM_city(student.getInt("mcity"));
					
					userDao.updateStudentInfo(stud);
				}else {
					StudentInfo stud1 = new StudentInfo();
					JSONObject student = userJson.getJSONObject("student");
					stud1.setSchoolName(student.getString("s_schoolName"));
					stud1.setSchoolAdd(student.getString("s_SchAddress"));
					stud1.setSchoolType(student.getString("s_schType"));
					stud1.setStandard(student.getInt("s_standard"));
					stud1.setParEmailId(student.getString("s_pemail"));
					
					Medium medium = new Medium();
					medium.setMediumId(student.getInt("s_medium"));
					stud1.setMedium(medium);
					
					stud1.setF_name(student.getString("s_faname"));
					stud1.setM_name(student.getString("s_moname"));
					stud1.setF_age(student.getInt("s_fage"));
					stud1.setM_age(student.getInt("s_mage"));
					stud1.setF_qualification(student.getString("s_fqualif"));
					stud1.setM_qualification(student.getString("s_mqualif"));
					stud1.setF_occupation(student.getString("s_foccup"));
					stud1.setM_occupation(student.getString("s_moccup"));
					stud1.setF_company(student.getString("s_fcompany"));
					stud1.setM_company(student.getString("s_mcompany"));
					stud1.setF_designation(student.getString("s_fdesig"));
					stud1.setM_designation(student.getString("s_mdesig"));
					stud1.setF_serviceLength(student.getString("s_fserviceLength"));
					stud1.setM_serviceLength(student.getString("s_mserviceLength"));
					stud1.setF_city(student.getInt("fcity"));
					stud1.setM_city(student.getInt("mcity"));
					
					stud1.setUserId(user);
					userDao.saveStudentInfo(stud1);
				
				}
			}
			
			userDao.updateUSer(user);
			

			data.put("done", true);
			data.put("msg", "The user profile updated");
		}
		return data;
	}

	@Override
	public JSONObject fetchAllUsersExceptStudent() throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
		JSONArray userArr = new JSONArray();
		JSONArray roleArr = new JSONArray();
		JSONArray roleArray = new JSONArray();
		List<User> userList = userDao.getAllUserListExceptStudent();
		List<Roles> roleList = userDao.getallRoles();
		
		for(Roles role : roleList) {
			JSONObject roleJson = new JSONObject();
			roleJson.put("roleId", role.getRoleId());
			roleJson.put("roleName", role.getRoleName());
			roleArray.put(role.getRoleName());
			roleArr.put(roleJson);
		}
		
		String email = "";
		for(User user : userList) {
			if(email != user.getEmailId()) {
				JSONObject json = new JSONObject();
				json.put("emailId", user.getEmailId());
				json.put("userId", user.getUserId());
				json.put("name", user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());
				json.put("roles", user.getRoles());
				userArr.put(json);
				email = user.getEmailId();
			}
		}
				
		data.put("roleArr", roleArray);
		data.put("role", roleArr);
		data.put("userDetails", userArr);
		return data;
	}

	@Override
	public JSONObject updateUserRoles(String manageRole) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();
		JSONObject manageRoleJson = new JSONObject(manageRole);

		if (manageRoleJson != null) {

			long userId = manageRoleJson.getLong("userId");
			JSONArray addRole = manageRoleJson.getJSONArray("add");

			User user = userDao.getUserById(userId);
			Set<Roles> set = new HashSet<>();
			
			for (int i = 0; i < addRole.length(); i++) {
				String role = addRole.get(i).toString();

				Roles roles = roleDao.getRoleByRoleName(role);

				if (roles != null) {

					set.add(roles);
				}
			}
			user.setRoles(set);
			userDao.updateUSer(user);

			data.put("done", true);
			data.put("msg", "The roles for "+ user.getEmailId() +" user updated successfully");
		}

		return data;
	}



	@Override
	public JSONObject getAllRolesForThisUser(long userId) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();
		JSONArray roleArr = new JSONArray();

		User user = userDao.getUserById(userId);

		Set<Roles> roles = user.getRoles();

		Iterator<Roles> it = roles.iterator();
		while (it.hasNext()) {
			roleArr.put(it.next().getRoleName());
		}

		data.put("roles", roleArr);

		return data;
	}
}
