package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.service.MoveDataFromTestInstanceToCompletionService;

@Controller
public class MoveDataFromTestInstanceToCompletionController {
	
	private static final Logger LOGGER = Logger.getLogger(MoveDataFromTestInstanceToCompletionController.class);
	
	
	@Autowired
	private MoveDataFromTestInstanceToCompletionService moveDataToCompletionService;
	
	
	
	@RequestMapping(value = "testInstanceToCompletionForUser", method = RequestMethod.POST)
	public @ResponseBody String MoveDataFromTestInstanceToCompletionByTISID(@RequestParam long userId, @RequestParam long tISID ) {

		JSONObject data = new JSONObject();
		try {
			
			data = moveDataToCompletionService.moveDataFromTestInstToComp(userId, tISID);
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("MoveDataFromTestInstanceToCompletion Exception-", e);
			return null;
		}

	}
	
	@RequestMapping(value = "testInstanceToCompletion",method = RequestMethod.POST)
	public @ResponseBody String MoveDataFromTestInstanceToCompletion(@RequestParam long userId, @RequestParam int testLevel ) {
		LOGGER.info("Start");
		JSONObject data = new JSONObject();
		try {
			
//			data = moveDataToCompletionService.moveData(userId,testLevel);
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}
	
	@RequestMapping(value = "all/testInstanceToCompletion",method = RequestMethod.POST)
	public @ResponseBody String MoveDataFromTestInstanceToCompletionForAllStudents() {
		LOGGER.info("Start");
		JSONObject data = new JSONObject();
		try {
			
//			data = moveDataToCompletionService.MoveDataFromTestInstanceToCompletionForAllStudents();
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}
	
	
	@RequestMapping(value = "moveRecordsFromTestInstanceToCompletion", method = RequestMethod.POST)
	public @ResponseBody String MoveRecordFromTestInstanceToCompletionForAllStudents(@RequestParam String[] emailIds) {
		LOGGER.info("Start");
		JSONObject data = new JSONObject();
		try {
			
//			data = moveDataToCompletionService.MoveDataFromTestInstanceToCompletion(emailIds);
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}

}
