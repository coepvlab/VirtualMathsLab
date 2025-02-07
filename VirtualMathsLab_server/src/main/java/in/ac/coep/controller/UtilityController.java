package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import in.ac.coep.entity.User;
import in.ac.coep.service.UtilityService;

/**
 * @author Vaibhav
 *
 */

@Controller
@RequestMapping(value = "/utility")
public class UtilityController {
	private static final Logger LOGGER = Logger.getLogger(UtilityController.class);
	@Autowired
	private UtilityService utilityService;

	// User Registration
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET)
	public @ResponseBody String getUserList() {
		
		JSONObject data = new JSONObject();
		try {
			data = utilityService.getUserListForExcelDownload();
			return data.toString();
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/demoCall", method = RequestMethod.GET)
	public @ResponseBody String getCall() {
		
		JSONObject data = new JSONObject();
		try {
			data = utilityService.getDataForDemoCall();
			return data.toString();
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/uploadQuesExcel", method = RequestMethod.POST)
    public @ResponseBody String uploadFileHandler(Authentication authentication, @RequestParam MultipartFile file, @RequestParam("flag") int flag) {
	
		JSONObject data = new JSONObject();
		User user = (User) authentication.getPrincipal();
		try {
			data = utilityService.uploadQuesByExcel(file, user, flag);
			return data.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/topic/status/change", method = RequestMethod.GET)
	public @ResponseBody String updateTopicStatus(@RequestParam long tid, @RequestParam boolean flag) {
		
		JSONObject data = new JSONObject();
		try {
			data = utilityService.updateTopicStatusByTID(tid, flag);
			return data.toString();
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping(value = "/changeDOD/api/newDOD", method = RequestMethod.POST)
    public @ResponseBody String updateDODByTID(@RequestBody String json) {
		LOGGER.info("updateDODByTID ....Start");
		JSONObject data = new JSONObject();
		try {
			data = utilityService.updateDODlevel(json);
			LOGGER.info("updateDODByTID ....end");
			return data.toString();
			
		} catch (Exception e) {
			LOGGER.error("changeDOD - ", e);
			e.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping(value = "/get/varCount/byDOD", method = RequestMethod.POST)
    public @ResponseBody String getVarCountByDOD(@RequestBody String json) {
		LOGGER.info("getVarCountByDOD ....Start");
		JSONObject data = new JSONObject();
		try {
			data = utilityService.getVarCountOfDOD(json);
			LOGGER.info("getVarCountByDOD ....end");
			return data.toString();
			
		} catch (Exception e) {
			LOGGER.error("getVarCountByDOD - ", e);
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
