package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.service.AnswerTypeService;

@Controller
@RequestMapping(value = "/answerType")
public class AnswerTypeController {
private static final Logger LOGGER = Logger.getLogger(AnswerTypeController.class);
	
	@Autowired 
	private AnswerTypeService answerTypeService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String getComplexityLevel(){
		JSONObject jsonObject = new JSONObject();
		
		try{
			LOGGER.info("fetch All AnswerTypes  Start");
			jsonObject = answerTypeService.fetchAnswerTypes();
			LOGGER.info("fetch All AnswerTypes  End");
			return jsonObject.toString();
		} catch (Exception e) {
			
			LOGGER.error("fetch All AnswerTypes  Failed",e);
			// TODO: handle exception
			return null;
		}
		
	}
}
