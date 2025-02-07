package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.entity.User;
import in.ac.coep.service.TestService;
import in.ac.coep.vo.TestVO;

/**
 * @author Prashant
 *
 * Handle all requests for configure test.
 * 
 * and save or update records into Test table
 */
@Controller
@RequestMapping(value = "configureTestPaper")
public class TestController {

	private static final Logger LOGGER = Logger.getLogger(TestController.class);
	
	@Autowired
	private TestService testService;

	/**
	 * 
	 * @param testVO
	 * @return JSONObject as a String 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String cratePracticeTest(Authentication authentication, @RequestBody TestVO testVO) {

		LOGGER.info("configureTestPaper ....Start");
		JSONObject data = new JSONObject();
		try {
			User user = (User) authentication.getPrincipal();
			data = testService.configureTestPaper(testVO, user);
			LOGGER.info("configureTestPaper....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}

	/**
	 * 
	 * @param testVO
	 * @return JSONObject as a String 
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody String modifyTestPaper(Authentication authentication,@RequestBody TestVO testVO) {
		
		LOGGER.info("modifyTestPaper ....Start");
		JSONObject data = new JSONObject();
		try {
			User user = (User) authentication.getPrincipal();
			data = testService.modifyTestPaper(testVO,user);
			LOGGER.info("modifyTestPaper....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}
	
//	@RequestMapping(value = "/{deptId}",method = RequestMethod.GET)
//	public @ResponseBody String fetchSectionsHavingAtleastOneQuestion(@PathVariable("deptId") int deptId) {
//
//		JSONObject data = new JSONObject();
//
//		try {
////			data = sectionService.fetchSectionsHavingAtleastOneQuestion(deptId);
//			return data.toString();
//		} catch (Exception e) {
//			// TODO: handle exception
//			return null;
//		}
//
//	}
	
	@RequestMapping(value = "/{deptId}",method = RequestMethod.GET)
	public @ResponseBody String fetchTestForOtherTest(@PathVariable("deptId") int deptId) {

		JSONObject data = new JSONObject();

		try {
//			data = sectionService.fetchSectionsHavingAtleastOneQuestion(deptId);
			return data.toString();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/set/configuration",method = RequestMethod.POST)
	public @ResponseBody String setConfigurationForTest(Authentication authentication, @RequestBody TestVO testVO) {

		JSONObject data = new JSONObject();

		try {
			User user = (User) authentication.getPrincipal();
			data = testService.setTestConfigurationForSelectedTopic(testVO, user);
			return data.toString();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;
		}
	}
}
