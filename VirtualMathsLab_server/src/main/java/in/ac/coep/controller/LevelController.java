///**
// * 
// */
//package in.ac.coep.controller;
//
//import org.apache.log4j.Logger;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import in.ac.coep.service.LevelService;
//
///**
// * @author Prashant
// *
// */
//
//@Controller
//public class LevelController {
//
//	
//	private static final Logger LOGGER = Logger.getLogger(LevelController.class);
//	
//	
//	@Autowired
//	private LevelService levelService;
//	
//	@RequestMapping(value = "/api/get/levelNo", method = RequestMethod.GET)
//	public @ResponseBody String getLevelByLevelNo(@RequestParam String levelNo) {
//
//		LOGGER.info("get level ....Start");
//
//
//		JSONObject data = new JSONObject();
//		try {
//			data = levelService.checkAvailability(levelNo);
//
//			LOGGER.info("get level ....End");
//			return data.toString();
//		} catch (Exception e) {
//
//			LOGGER.error("get level error-", e);
//
//			return null;
//		}
//
//	}
//}
