package in.ac.coep.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.entity.User;
import in.ac.coep.service.TestInstanceStatisticsService;

@Controller
@RequestMapping(value = "/testInstanceStatistics")
public class TestInstanceStatisticsController {

//	private static final Logger LOGGER = Logger.getLogger(TestInstanceStatisticsController.class);
	
	@Autowired
	private TestInstanceStatisticsService statisticsService;
	
	@RequestMapping(value = "/getAlltestStatisticsRecords", method = RequestMethod.GET)
	public @ResponseBody String getAlltestStatisticsRecords(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		
		JSONObject data = new JSONObject();
		try {
			
			data = statisticsService.getAllInstanceStatistics(user);
			return data.toString();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	

	}
}
