package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.service.CountryService;


@Controller
public class LocationController {
	
	private static final Logger LOGGER = Logger.getLogger(LocationController.class);
	
	@Autowired
	CountryService countryService;
	
	@RequestMapping(value = "/country", method = RequestMethod.GET)
	public @ResponseBody String fetchAllCountries() {
		
		JSONObject data = new JSONObject();
		
		try {
			LOGGER.info("fetch All countries Start");
			data = countryService.fetchAllCountries();
			LOGGER.info("fetch All countries End");
			return data.toString();
		} catch (Exception e) {
			
			LOGGER.error("fetch All departments Failed",e);
			// TODO: handle exception
			return null;
		}
				
	}

	
	@RequestMapping(value = "/state", method = RequestMethod.GET)
	public @ResponseBody String fetchAllStates(@RequestParam int country_id) {
		
		JSONObject data = new JSONObject();
		
		try {
			LOGGER.info("fetch All states Start");
			data = countryService.fetchAllStates(country_id);
			LOGGER.info("fetch All states End");
			
			return data.toString();
			
		} catch (Exception e) {
			
			LOGGER.error("fetch All states Failed",e);
			// TODO: handle exception
			return null;
		}				
	}
	
	
	
	@RequestMapping(value = "/city", method = RequestMethod.GET)
	public @ResponseBody String fetchAllCity(@RequestParam int state_id) {
		
		JSONObject data = new JSONObject();
		
		try {
			LOGGER.info("fetch All city Start");
			data = countryService.fetchAllCity(state_id);
			LOGGER.info("fetch All city End");
			return data.toString();
		} catch (Exception e) {
			
			LOGGER.error("fetch All city Failed",e);
			// TODO: handle exception
			return null;
		}
				
	}
	
	
}
