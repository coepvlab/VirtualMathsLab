/**
 * 
 */
package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.service.ComplexityLevelService;

/**
 * @author Prashant
 *
 */
@Controller
@RequestMapping(value = "/complexitylevel")
public class CompexityLevelController {
	private static final Logger LOGGER = Logger.getLogger(CompexityLevelController.class);
	
	@Autowired 
	ComplexityLevelService complexityLevelService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String getComplexityLevel(){
		JSONObject jsonObject = new JSONObject();
		
		try{
			LOGGER.info("fetch All Complexity level Start");
			jsonObject = complexityLevelService.fetchComplexityLevel();
			LOGGER.info("fetch All Complexity level End");
			return jsonObject.toString();
		} catch (Exception e) {
			
			LOGGER.error("fetch All Complexity level Failed",e);
			// TODO: handle exception
			return null;
		}
		
	}
}
