package in.ac.coep.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.constants.Constants;
import in.ac.coep.entity.User;
import in.ac.coep.service.TestLinkService;
import in.ac.coep.vo.UserVO;
/**
 * 
 * @author Prashant
 *
 */
@Controller
public class TestLinkController {

	private static final Logger LOGGER = Logger.getLogger(TestLinkController.class);
	
	@Autowired
	private TestLinkService testLinkService;
	
//	@RequestMapping(value = "/api/test/create", method = RequestMethod.GET)
//	public @ResponseBody String linkGenerator(@RequestParam long phoneNumber,@RequestParam int deptId,@RequestParam int testLevel) {
//
//		
//		JSONObject data = new JSONObject();
//		try {
//			
////			data = testLinkService.generateLinkForRequestedMailId(phoneNumber,deptId,testLevel);
//			return data.toString();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			data.put("msg", "Test link is not available for given test level");
//			return data.toString();
//		}
//		
//
//	}
	
	
	@RequestMapping(value = "/api/create/test/other", method = RequestMethod.GET)
	public @ResponseBody String getUserTestInstanceDetails(Authentication authentication, @RequestParam long testId) {

		User user = (User) authentication.getPrincipal();
		JSONObject data = new JSONObject();
		try {
			
			long testTypeId = Constants.otherTestTypeId;
			data = testLinkService.generateOtherTestLinkForRequestedUser(user, testTypeId, testId);
			return data.toString();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	

	}
	
	
//	@RequestMapping(value = "/api/test/create/other", method = RequestMethod.GET)
//	public @ResponseBody String linkGenerator(Authentication authentication,@RequestParam long testId) {
//
//		
//		JSONObject data = new JSONObject();
//		
//		try {
//			
////			data = testLinkService.generateLinkForRequestedMailId(email,deptId,testLevel);
////			data = testLinkService.generateOtherTestLinkForRequestedUser(U , testTypeId, testId);
//			return data.toString();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			data.put("msg", "Test link is not available for given test level");
//			return data.toString();
//		}
//
//	}
	
	
	
	
//	@RequestMapping(value = "/api/test/user/create", method = RequestMethod.POST)
//	public @ResponseBody String insertUser(@RequestBody UserVO userVO) {
//
//		
//		JSONObject data = new JSONObject();
//		try {
//			
//			data = testLinkService.insertUserInDatabase(userVO);
//			return data.toString();
//			
//		}catch (ConstraintViolationException exception) {
//			LOGGER.info("User with this mobile number already exist");
//			LOGGER.error("Exception-", exception);
//			data.put("done", false);
//			data.put("msg", "User with this mobile number already exist");
//			return data.toString();
//		}  catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		
//
//	}
	
	
	
	@RequestMapping(value = "/authentication", method = RequestMethod.GET)
//	@PreAuthorize("hasAnyRole('ADMIN')")
	public String linkHandler(Authentication authentication,HttpServletRequest request,@RequestParam String token) {
		
		try {
			
//			if(token!= null){
//				return ""
//			}
//			data = testLinkService.generateLinkForRequestedMailId(email);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
		

	}
	
	
	
	@RequestMapping(value = "/api/testLink/create", method = RequestMethod.GET)
	public @ResponseBody String createTestLinkForGanitUser() {
		JSONObject data = new JSONObject();
		try {
//			data = testLinkService.updateUserCurrentRoundForNextRoundForGanit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data.toString();
	}
	
	@RequestMapping(value = "/api/testLink/createNextRound", method = RequestMethod.GET)
	public @ResponseBody String createTestLinkForGanitUserNextRound() {
		JSONObject data = new JSONObject();
		try {
//			data = testLinkService.createTestLinkCurrentRoundForNextRoundForGanit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data.toString();
	}
	
	
	
	
	
	@RequestMapping(value = "/api/test/user/update", method = RequestMethod.POST)
	public @ResponseBody String updateUser(@RequestBody UserVO userVO) {

		
		JSONObject data = new JSONObject();
		try {
			
//			data = testLinkService.updateUserInDatabase(userVO);
			return data.toString();
			
		}catch (ConstraintViolationException exception) {
			LOGGER.info("User with this mail Id already exist");
			LOGGER.error("Exception-", exception);
			data.put("done", false);
			data.put("msg", "User with this mail Id/userId already exist");
			return data.toString();
		}  catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			LOGGER.error("Exception-",e);
			data.put("done", false);
			data.put("msg", "This email id does not exist");
			return data.toString();
		}
		

	}
}
