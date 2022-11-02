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

import in.ac.coep.service.RegisterInTechStudentService;

/**
 * @author COEP
 *
 */

@Controller
public class RegisterInTechStudentController {
private static final Logger LOGGER = Logger.getLogger(RegisterInTechStudentController.class);
	
	@Autowired
	private RegisterInTechStudentService regUserService;
	
	@RequestMapping(value="/addUserToVMathsLab", method = RequestMethod.POST)
	public @ResponseBody String addRegisteredStudentsFromInTech(@RequestBody String json) {
		
		LOGGER.info("Add student ....Start");
		JSONObject data = new JSONObject();
		try {
			data = regUserService.getRegisterStudents(json);
			LOGGER.info("Add student....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}
	}
}
