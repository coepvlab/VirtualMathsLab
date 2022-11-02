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

import in.ac.coep.service.MediaTypeService;

/**
 * @author Prashant
 *
 */

@Controller
@RequestMapping(value = "/mediaType")
public class MediaTypeController {
	
private static final Logger LOGGER = Logger.getLogger(MediaTypeController.class);
	
	@Autowired 
	private MediaTypeService mediaTypeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String getComplexityLevel(){
		JSONObject jsonObject = new JSONObject();
		
		try{
			LOGGER.info("fetch All MediaType Start");
			jsonObject = mediaTypeService.fetchMediaTypes();
			LOGGER.info("fetch All MediaType End");
			return jsonObject.toString();
		} catch (Exception e) {
			
			LOGGER.error("fetch All MediaType Failed",e);
			// TODO: handle exception
			return null;
		}
		
	}


	
}
