//package in.ac.coep.controller;
//
//import org.apache.log4j.Logger;
//import org.json.JSONObject;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping(value = "/getTestConfig")
//public class ModifyTestConfigController {
//	
//		private static final Logger LOGGER = Logger.getLogger(TestConfigurationController.class);
//		
////		@Autowired
////		private TestConfigurationService testConfigurationService;
//		
//		@RequestMapping(method = RequestMethod.GET)
//		public @ResponseBody String fetchTestConfig(@RequestParam long testId) {
//			
//			LOGGER.info("Available test config Fetching ....Start");
//			JSONObject data = new JSONObject();
//			try {
////				data = testConfigurationService.fetchTestToConfigDetails(testId);
//				LOGGER.info("Available test config Fetching....End");
//				return data.toString();
//			} catch (Exception e) {
//				LOGGER.error("Exception-", e);
//				return null;
//			}
//		}
//		
////		@RequestMapping(method = RequestMethod.PUT)
////		public @ResponseBody String updateAvailTestConfigDetails(@RequestBody TestConfigurationVO[] confIdJSONArr) {
////			
////			LOGGER.info("Test config Details updation ....Start");
////			JSONObject data = new JSONObject();
////			try {
//////				data = testConfigurationService.updateTestConfigDetails(confIdJSONArr);
////				LOGGER.info("Test config Details updation....End");
////				return data.toString();
////			} catch (Exception e) {
////				LOGGER.error("Exception-", e);
////				return null;
////			}
////		}
//}
