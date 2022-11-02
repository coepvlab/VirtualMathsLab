package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.service.TestConfigurationService;
import in.ac.coep.vo.TestConfigurationVO;

/**
 * @author Prasahnt
 *
 *Handle all request for set test paper.
 *
 *and save records into TestConfiguration table
 */
@Controller
@RequestMapping(value = "setTestPaper")
public class TestConfigurationController {

private static final Logger LOGGER = Logger.getLogger(TestConfigurationController.class);
	
	@Autowired
	private TestConfigurationService testConfigurationService;
	
	/**
	 * 
	 * @param testConfigurationVOs
	 * @return JSONObject as a String 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String setTestPaper(@RequestBody TestConfigurationVO[] testConfigurationVOs) {
		
		LOGGER.info("setTestPaper ....Start");
		JSONObject data = new JSONObject();
		try {
			data = testConfigurationService.setTestPaper(testConfigurationVOs);
			LOGGER.info("setTestPaper....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}
	
	
//	@RequestMapping(method = RequestMethod.PUT)
//	public @ResponseBody String modifyTestPaper(@RequestBody TestConfigurationVO[] testConfigurationVOs) {
//		
//		LOGGER.info("setTestPaper ....Start");
//		JSONObject data = new JSONObject();
//		try {
////			data = testConfigurationService.modifyTestPaper(testConfigurationVOs);
//			LOGGER.info("setTestPaper....End");
//			return data.toString();
//		} catch (Exception e) {
//			LOGGER.error("Exception-", e);
//			return null;
//		}
//
//	}
	
//	@RequestMapping(value = "preview",method = RequestMethod.GET)
//	public @ResponseBody String fetchtestConfigurationDataBySectionId(@RequestParam int sectionId,@RequestParam long testId) {
//		
//		LOGGER.info("previewTestPaper ....Start");
//		JSONObject data = new JSONObject();
//		try {
////			data = testConfigurationService.fetchtestConfigurationDataBySectionId(sectionId,testId);
//			LOGGER.info("previewTestPaper....End");
//			return data.toString();
//		} catch (Exception e) {
//			LOGGER.error("Exception-", e);
//			return null;
//		}
//
//	}
	
	
	
//	@RequestMapping(value = "preview",method = RequestMethod.POST)
//	public @ResponseBody String fetchLevelWiseQuestionGroupsBySectionId(@RequestParam int sectionId) {
//		
//		LOGGER.info("previewTestPaper ....Start");
//		JSONObject data = new JSONObject();
//		try {
//			data = testConfigurationService.fetchLevelWiseQuestionGroupsBySectionId(sectionId);
//			LOGGER.info("previewTestPaper....End");
//			return data.toString();
//		} catch (Exception e) {
//			LOGGER.error("Exception-", e);
//			return null;
//		}
//
//	}
	
}
