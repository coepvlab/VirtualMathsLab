package in.ac.coep.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.service.CheckLinkCreationService;

@Controller
public class TestLinkCreationController {

	
	@Autowired
	private CheckLinkCreationService checklinkcreationservice;
	
	
	@RequestMapping(value = "/api/test/user/create/all/user", method = RequestMethod.GET)
	public @ResponseBody String linkGenerator(@RequestParam int testLevel) {

		
		JSONObject data = new JSONObject();
		try {
			
			data = checklinkcreationservice.checklinkcreation(testLevel);
			return data.toString();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	

	}
	
}
