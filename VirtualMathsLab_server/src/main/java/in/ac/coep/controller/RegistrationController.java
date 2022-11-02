package in.ac.coep.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;

import in.ac.coep.entity.User;
import in.ac.coep.service.RegistrationService;
import in.ac.coep.vo.ContributorInfoVO;
import in.ac.coep.vo.ParentInfoVO;
import in.ac.coep.vo.StudentInfoVO;
import in.ac.coep.vo.TeacherInfoVO;
import in.ac.coep.vo.UserVO;

/**
 * @author Vaibhav
 *
 */

@Controller
@RequestMapping(value = "/userProfile")
public class RegistrationController {

	private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

	@Autowired
	private RegistrationService registrationService;

	// User Registration
	@RequestMapping(value = "/api/user/create", method = RequestMethod.POST)
	public @ResponseBody String registration(@RequestBody String userSt) {
		
		JSONObject data = new JSONObject();
		try {
			
			ContributorInfoVO contVO = null;
			TeacherInfoVO teachVO = null;
			StudentInfoVO studVO = null;
			ParentInfoVO parVO = null;
			ObjectMapper mapper = new ObjectMapper();
			JSONObject json = new JSONObject(userSt);
			
			UserVO userVO = mapper.readValue(new TreeTraversingParser(
					convertJsonFormat(json.getJSONObject("user"))), UserVO.class);	
			
			if(json.has("contrib")){
			contVO = mapper.readValue(new TreeTraversingParser(
					convertJsonFormat(json.getJSONObject("cont"))), ContributorInfoVO.class);	
			}
			
			if(json.has("teacher")){
				teachVO = mapper.readValue(new TreeTraversingParser(
						convertJsonFormat(json.getJSONObject("teach"))), TeacherInfoVO.class);	
				}
			
			if(json.has("student")){
				studVO = mapper.readValue(new TreeTraversingParser(
						convertJsonFormat(json.getJSONObject("stud"))), StudentInfoVO.class);	
				}
			
			if(json.has("parent")){
				parVO = mapper.readValue(new TreeTraversingParser(
						convertJsonFormat(json.getJSONObject("par"))), ParentInfoVO.class);	
				}
			
			data = registrationService.insertUserInDatabase(userVO, contVO, teachVO, studVO, parVO);
			return data.toString();
			
		}catch (ConstraintViolationException exception) {
			LOGGER.info("User with this Email Id already exist");
			LOGGER.error("Exception-", exception);
			data.put("done", false);
			data.put("msg", "User with this Email Id already exist");
			return data.toString();
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/emailVerification", method = RequestMethod.GET)
	public String linkHandler(@RequestParam String token) {

		try {
			if (token != null) {
				registrationService.doEmailVerification(token);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/getContributorRequest", method = RequestMethod.GET)
	public @ResponseBody String getContributorRequest() {

		JSONObject data = new JSONObject();
		try {
			data = registrationService.getAllContributorRequest();
			return data.toString();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/approvedContributorRequest", method = RequestMethod.GET)
	public @ResponseBody String setRoleForContributorRequest(@RequestParam long userId) {

		JSONObject data = new JSONObject();
		try {
			data = registrationService.setApprovedContribRequest(userId);
			return data.toString();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/getPendingAccountActivation", method = RequestMethod.GET)
	public @ResponseBody String getAccountLockedUsers() {

		JSONObject data = new JSONObject();
		try {
			data = registrationService.getAccountLockedUsers();
			return data.toString();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/activateAccount", method = RequestMethod.GET)
	public @ResponseBody String setAccountUnlock(@RequestParam long userId) {

		JSONObject data = new JSONObject();
		try {
			data = registrationService.getAccountUnlocked(userId);
			return data.toString();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public @ResponseBody String fetchUser(Authentication authentication, HttpServletRequest request) {

		User user = (User) authentication.getPrincipal();
		JSONObject data = new JSONObject();
		try {
			data = registrationService.fetchUser(user);
			return data.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public @ResponseBody String updateUser(@RequestBody String userProfileJson) {

		JSONObject data = new JSONObject();
		try {
			data = registrationService.updateUser(userProfileJson);
			return data.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/fetchUsersExceptStudent", method = RequestMethod.GET)
	public @ResponseBody String fetchUsersExceptStudent() {

		JSONObject data = new JSONObject();
		try {
			data = registrationService.fetchAllUsersExceptStudent();
			return data.toString();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@RequestMapping(value = "/manageRole", method = RequestMethod.POST)
	public @ResponseBody String ManageRoleOfUser(@RequestBody String manageRoleJson) {

		JSONObject data = new JSONObject();
		try {
			data = registrationService.updateUserRoles(manageRoleJson);
			return data.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	static JsonNode convertJsonFormat(JSONObject json) {
		ObjectNode ret = JsonNodeFactory.instance.objectNode();

    Iterator<String> iterator = json.keys();
    for (; iterator.hasNext();) {
        String key = iterator.next();
        Object value;
        try {
            value = json.get(key);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        if (json.isNull(key))
            ret.putNull(key);
        else if (value instanceof String)
            ret.put(key, (String) value);
        else if (value instanceof Integer)
            ret.put(key, (Integer) value);
        else if (value instanceof Long)
            ret.put(key, (Long) value);
        else if (value instanceof Double)
            ret.put(key, (Double) value);
        else if (value instanceof Boolean)
            ret.put(key, (Boolean) value);
        else if (value instanceof JSONObject)
            ret.put(key, convertJsonFormat((JSONObject) value));
        else if (value instanceof JSONArray)
            ret.put(key, convertJsonFormat((JSONArray) value));
        else
            throw new RuntimeException("not prepared for converting instance of class " + value.getClass());
    }
    return ret;
    }
	
	static JsonNode convertJsonFormat(JSONArray json) {
	    ArrayNode ret = JsonNodeFactory.instance.arrayNode();
	    for (int i = 0; i < json.length(); i++) {
	        Object value;
	        try {
	            value = json.get(i);
	        } catch (JSONException e) {
	            throw new RuntimeException(e);
	        }
	        if (json.isNull(i))
	            ret.addNull();
	        else if (value instanceof String)
	            ret.add((String) value);
	        else if (value instanceof Integer)
	            ret.add((Integer) value);
	        else if (value instanceof Long)
	            ret.add((Long) value);
	        else if (value instanceof Double)
	            ret.add((Double) value);
	        else if (value instanceof Boolean)
	            ret.add((Boolean) value);
	        else if (value instanceof JSONObject)
	            ret.add(convertJsonFormat((JSONObject) value));
	        else if (value instanceof JSONArray)
	            ret.add(convertJsonFormat((JSONArray) value));
	        else
	            throw new RuntimeException("not prepared for converting instance of class " + value.getClass());
	    }
	    return ret;
	}

}
