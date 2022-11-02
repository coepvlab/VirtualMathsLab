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

import in.ac.coep.service.ReflectionService;

/**
 * @author Prashant
 *
 */

@Controller
@RequestMapping(value = "/reflection")
public class ReflectionController {
	
	private static final Logger LOGGER = Logger.getLogger(ReflectionController.class);
	
	@Autowired 
	private ReflectionService reflectionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String getJavaFiles(){
		JSONObject jsonObject = new JSONObject();
		
		try{
			LOGGER.info("fetch All java files  Start");
			jsonObject = reflectionService.fetchJavaFiles();
			LOGGER.info("fetch All java files  End");
			return jsonObject.toString();
		} catch (Exception e) {
			
			LOGGER.error("fetch All java files  Failed",e);
			// TODO: handle exception
			return null;
		}
		
	}
}
