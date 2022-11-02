/**
 * 
 */
package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.entity.User;
import in.ac.coep.service.TestInstanceCompletionService;

/**
 * @author Prashant
 *
 */

@Controller
@RequestMapping(value = "testInstanceCompletion")
public class TestInstanceCompletionController {
	
	private static final Logger LOGGER = Logger.getLogger(TestInstanceCompletionController.class);
	
	@Autowired
	private TestInstanceCompletionService testInstanceCompletionService;

	@RequestMapping(value = "/api/get/quetionPaper/{tisId}", method = RequestMethod.GET)
	public @ResponseBody String getTestInstanceCompletionRecord(Authentication authentication, @PathVariable long tisId) {
		User user = (User) authentication.getPrincipal();
//		LOGGER.info("update current Test time ....Start");
		JSONObject data = new JSONObject();
		try {
			data = testInstanceCompletionService.getTestQuestionPaperRecordByUserIdAndTISID(tisId, user.getUserId());
			return data.toString();
		} catch (Exception e) {

			LOGGER.error("getTestpaper-Exception-", e);

			return null;
		}

	}

}
