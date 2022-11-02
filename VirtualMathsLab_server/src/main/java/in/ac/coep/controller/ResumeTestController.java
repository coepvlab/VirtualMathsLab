/**
 * 
 */
package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.ac.coep.entity.User;

/**
 * @author Prashant
 *
 */
@Controller
@RequestMapping(value = "resumeTest")
public class ResumeTestController {

	
	
	private static final Logger LOGGER = Logger.getLogger(StudentTestController.class);
	
	
//	@Autowired
//	private TestInstanceService testInstanceService;
	

	@RequestMapping(method = RequestMethod.GET)
	public String fetchTestQuestionGroupsForResumeTest(Authentication authentication,Model model) {

		JSONObject data = new JSONObject();
		User user = (User) authentication.getPrincipal();
		try {
			
//			long userId = Long.parseLong(authentication.getName());
			
			LOGGER.info("fetch Question From Database for Test Start ....");
//			data = testInstanceService.fetchTestQuestionGroupsForResumeTest(userId);
			model.addAttribute("data",data);
			model.addAttribute("FN",user.getFirstName());
			LOGGER.info("fetch Question From Database for Test End....");
			return "candidateTest";
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Error while fetching Question From Database for Test ....",e);
			e.printStackTrace();
			return null;
		}

	}
	
	

}
