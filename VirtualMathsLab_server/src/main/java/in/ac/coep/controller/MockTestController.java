package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.entity.User;
import in.ac.coep.service.MockTestService;
import in.ac.coep.service.TestLinkService;

@Controller
@RequestMapping(value = "/mockTest")
public class MockTestController {

	private static final Logger LOGGER = Logger.getLogger(PracticeTestController.class);
	
	@Autowired
	private TestLinkService testLinkService;
	
	@Autowired
	private MockTestService mockTestService;
	
	
	@RequestMapping(value = "/generate/tag/{topicId}/{topicNo}", method = RequestMethod.GET)
	public @ResponseBody String generateTagForStud(Authentication authentication, @PathVariable long topicId, @PathVariable String topicNo) {
		
		JSONObject data = new JSONObject();
	
		User user = (User) authentication.getPrincipal();
		
		try {
			data = mockTestService.generateTag(user, topicId, topicNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data.toString();
	}
	
	
	
	
//	@RequestMapping(value = "/api/create/{topicId}/{testTypeId}/{testId}/{compLevel}", method = RequestMethod.GET)
//	public @ResponseBody String createMockTestLink(Authentication authentication, @PathVariable long topicId, @PathVariable long testTypeId, @PathVariable long testId, @PathVariable int compLevel) {
//		
//		JSONObject data = new JSONObject();
//		
//		LOGGER.info("create mock test link... started");
//		try {
//			
//			User user = (User) authentication.getPrincipal();
//			
//			data = testLinkService.generatePracticeTestLinkForRequestedUser(user, topicId, testTypeId, testId, compLevel);
//			LOGGER.info("create mock test link... end");
//			return data.toString();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			LOGGER.error("Exception-",e);
//			e.printStackTrace();
//			data.put("msg", "Test link is not available for given test level");
//			return data.toString();
//		}
//	}
}
