/**
 * 
 */
package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.ac.coep.entity.User;
import in.ac.coep.service.TestConfigurationService;

/**
 * 
 * @author Prashant
 *
 *         This controller used handle request of start test by candidate
 */
@Controller
@RequestMapping(value = "othertest")
public class OtherTestController {

	private static final Logger LOGGER = Logger.getLogger(OtherTestController.class);

	@Autowired
	private TestConfigurationService testConfigurationService;

	// @RequestMapping(method = RequestMethod.GET)
	// public String startTest(Authentication authentication,HttpServletRequest
	// request) {
	//
	// LOGGER.info("candidate page....start");
	//
	// return "candidateTest";
	// }
	/**
	 * 
	 * @param authentication
	 * @param model
	 * @return Question groups according to configuration
	 */
//	@PreAuthorize("hasAnyRole('STUDENT')")
//	@RequestMapping(method = RequestMethod.GET)
//	public String fetchTestQuestionGroupsForTest(Authentication authentication, Model model) {
//
//		JSONObject data = new JSONObject();
//		User user = (User) authentication.getPrincipal();
//		try {
//			long userId = user.getUserId();
//			long testId = 1;
//
//			LOGGER.info("fetch Question From Database for Test Start ....");
//			data = testConfigurationService.fetchTestQuestionGroupsForTest(testId, userId);
//			model.addAttribute("data", data);
//			model.addAttribute("FN", user.getFirstName());
//			LOGGER.info("fetch Question From Database for Test End....");
//			return "practiceTest";
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			LOGGER.error("Error while fetching Question From Database for Test ....", e);
//			e.printStackTrace();
//			return null;
//		}
//
//	}
	
	
	
	
	@PreAuthorize("hasAnyRole('CONTRIBUTOR','MODERATOR','STUDENT')")
	@RequestMapping(method = RequestMethod.GET)
	public String fetchTestQuestionGroupsForOtherTest(Authentication authentication, Model model, @RequestParam long testId, @RequestParam long testTypeId) {

		JSONObject data = new JSONObject();
		User user = (User) authentication.getPrincipal();
		try {
			long userId = user.getUserId();
//			long testId = 1;
			System.out.println(testId);

			LOGGER.info("fetch Question From Database for Test Start ....");
			data = testConfigurationService.fetchTestQuestionGroupsForOtherTest(testId, userId);
			
			if (data.getBoolean("done") == false) {
//				model.addAttribute("data", data);
//				model.addAttribute("FN", user.getFirstName());
				LOGGER.info("fetch Question From Database for Test End....");
				return "redirect:/home";
			}else {
				model.addAttribute("data", data);
				model.addAttribute("FN", user.getFirstName());
				LOGGER.info("fetch Question From Database for Test End....");
				return "otherTest";
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Error while fetching Question From Database for Test ....", e);
			e.printStackTrace();
			return null;
		}

	}

	
	
	
}
