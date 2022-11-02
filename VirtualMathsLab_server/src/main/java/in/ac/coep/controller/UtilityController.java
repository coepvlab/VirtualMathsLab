package in.ac.coep.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
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
	
	
//	@RequestMapping(value = "/uploadJavaFile", method = RequestMethod.POST)
//    public @ResponseBody String uploadJavaFileHandler(Authentication authentication, @RequestParam MultipartFile file, @RequestParam("flag") int flag) {
//	
//		JSONObject data = new JSONObject();
//		User user = (User) authentication.getPrincipal();
//		try {
//			data = utilityService.uploadJavaFile(file, user, flag);
//			return data.toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
}
