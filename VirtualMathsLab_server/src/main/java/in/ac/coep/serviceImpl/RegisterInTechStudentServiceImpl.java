/**
 * 
 */
package in.ac.coep.serviceImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.RoleDao;
import in.ac.coep.dao.UserDao;
import in.ac.coep.entity.Medium;
import in.ac.coep.entity.Roles;
import in.ac.coep.entity.Standard;
import in.ac.coep.entity.StudentInfo;
import in.ac.coep.entity.User;
import in.ac.coep.service.RegisterInTechStudentService;

/**
 * @author COEP
 *
 */

@Service
public class RegisterInTechStudentServiceImpl implements RegisterInTechStudentService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public JSONObject getRegisterStudents(String json) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data  = new JSONObject();
		JSONObject userJson = new JSONObject(json);
		
		JSONArray userArr = userJson.getJSONArray("user");
		
		for(int i=0; i< userArr.length();i++) {
			JSONObject obj = userArr.getJSONObject(i);
			User u = userDao.getUserByEmailId(obj.getString("email"));
			
			if(u == null) {
				
				User user = new User();
				Roles roles = null;
				user.setEmailId(obj.getString("email"));
				user.setFirstName(obj.getString("fname"));
				user.setMiddleName(obj.getString("mname"));
				user.setLastName(obj.getString("lname"));
				user.setPhoneNumber(String.valueOf(obj.getLong("phoneNo")));
				user.setCountryId(obj.getInt("countryId"));
				user.setStateId(obj.getInt("stateId"));
				user.setCityId(obj.getInt("cityId"));
				user.setGender(obj.getInt("gender"));
				user.setPincode(obj.getLong("pincode"));
				user.setPassword(obj.getString("pass"));
				
				Standard standard = new Standard();
				standard.setStdId(1);
				user.setStandard(standard);
				
				
				user.setUserType("Student");
				user.setTermsAndConditionsAccepted(true);
				user.setEmailValidated(true);	
				user.setDeleted(true);	
				user.setAccountExpired(false);		
				user.setAccountLocked(false);
				user.setCredentialsExpired(false);
				
				Date cT = new Date(System.currentTimeMillis());
				user.setCreatedDate(cT);

				Date uT = new Date(System.currentTimeMillis());
				user.setUpdatedDate(uT);
				
				Date date = new Date(System.currentTimeMillis());
				user.setDateOfBirth(date);
				
				StudentInfo stud = new StudentInfo();
				stud.setParEmailId("ito@gmail.com");
				stud.setSchoolName(obj.getString("dept"));
				stud.setSchoolAdd(obj.getString("colg"));
				stud.setSchoolType(obj.getString("itoRegId"));
				stud.setStandard(obj.getInt("deptId"));
				
				Medium medium = new Medium();
				medium.setMediumId(1);
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
				
				data.put("done", true);
				data.put("msg", "The user registered successfully");
			}else {
				data.put("done", false);
				data.put("msg", "Email id already exist");
			}
		}
		
		return data;
	}

	
	
}
