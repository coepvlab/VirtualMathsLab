/**
 * 
 */
package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.entity.TestInstanceState;
import in.ac.coep.service.TestInstanceStateService;

/**
 * @author Prashant
 *
 */
@Controller
@RequestMapping(value = "testInstanceState")
public class TestInstanceStateController {

	private static final Logger LOGGER = Logger.getLogger(TestInstanceStateController.class);

	@Autowired
	private TestInstanceStateService testInstanceStateService;

	@RequestMapping(value = "/update/currentTime", method = RequestMethod.POST)
	public @ResponseBody String saveCurrentTestTime(@RequestBody TestInstanceState instanceState) {
//		LOGGER.info("update current Test time ....Start");
		JSONObject data = new JSONObject();
		try {
			data = testInstanceStateService.updateCurrentTestTime(instanceState);
			return data.toString();
		} catch (Exception e) {

			LOGGER.error("submitTest-Exception-", e);

			return null;
		}

	}

}
