package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.service.TestConfigurationService;

@Controller
@RequestMapping(value = "/getAllTest")
public class AvailableTestController {
	
	private static final Logger LOGGER = Logger.getLogger(TestConfigurationController.class);
	
	@Autowired
	private TestConfigurationService testConfigurationService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String fetchAvailTest() {
		
		LOGGER.info("Available test Fetching ....Start");
		JSONObject data = new JSONObject();
		try {
			data = testConfigurationService.fetchAvailableOtherTestToConfig();
			LOGGER.info("Available test Fetching....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}
	}
	
//	@RequestMapping(method = RequestMethod.PUT)
//	public @ResponseBody String updateAvailTestDetails(@RequestBody TestVO testVO) {
//		
//		LOGGER.info("Test Details updation ....Start");
//		JSONObject data = new JSONObject();
//		try {
////			data = testConfigurationService.updateTestDetails(testVO);
//			LOGGER.info("Test Details updation....End");
//			return data.toString();
//		} catch (Exception e) {
//			LOGGER.error("Exception-", e);
//			return null;
//		}
//	}
}
