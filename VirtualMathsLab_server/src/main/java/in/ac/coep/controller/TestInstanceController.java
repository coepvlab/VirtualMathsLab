package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.entity.TestInstanceState;
import in.ac.coep.service.TestInstanceService;
import in.ac.coep.service.TestInstanceStateService;
import in.ac.coep.vo.TestInstanceVO;

/**
 * @author Prashant
 *
 */
@Controller
@RequestMapping(value = "testInstance")
public class TestInstanceController {

	private static final Logger LOGGER = Logger.getLogger(TestInstanceController.class);
	
	@Autowired
	private TestInstanceService testInstanceService;
	
	@Autowired
	private TestInstanceStateService testInstanceStateService;
	
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody String updateTestInstance(@RequestBody TestInstanceVO[] testInstanceVO) {
//		LOGGER.info("updateTestInstance....Start");
		JSONObject data = new JSONObject();
		try {
			data = testInstanceService.updateTestInstance(testInstanceVO);
//			LOGGER.info("updateTestInstance....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("updateTestInstance-Exception-", e);
			return null;
		}

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String submitTest(@RequestBody TestInstanceState instanceState) {
		LOGGER.info("submitTest....Start");
		JSONObject data = new JSONObject();
		try {
			
			data = testInstanceStateService.updateTestInstanceState(instanceState);
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("submitTest-Exception-", e);
			return null;
		}

	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String fetchData(@RequestParam long tisId) {
		LOGGER.info("submitTest....Start");
		JSONObject data = new JSONObject();
		try {
			data = testInstanceStateService.fetchAttemptedAndTotalQuestion(tisId);
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("submitTest-Exception-", e);
			return null;
		}

	}
	
//	@RequestMapping(value = "/updateForGOResume", method = RequestMethod.GET)
//	public @ResponseBody String updateTestInstanceGO(@RequestParam long tiId, @RequestParam boolean mcqFlag) {
////		LOGGER.info("updateTestInstance....Start");
//		JSONObject data = new JSONObject();
//		try {
//			data = testInstanceService.updateTestInstanceGO(tiId,mcqFlag);
////			LOGGER.info("updateTestInstance....End");
//			return data.toString();
//		} catch (Exception e) {
//			LOGGER.error("updateTestInstance-Exception-", e);
//			return null;
//		}
//
//	}
	
}
