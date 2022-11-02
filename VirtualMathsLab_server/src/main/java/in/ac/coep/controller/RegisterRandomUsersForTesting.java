/**
 * 
 */
package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Prashant
 *
 */
@Controller
@RequestMapping(value = "/registerRandomUsers")
public class RegisterRandomUsersForTesting {

	
//	@Autowired
//	private TestLinkService testLinkService;
	
	
	private static final Logger LOGGER = Logger.getLogger(RegisterRandomUsersForTesting.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String fetchAvailTest(@RequestParam int noOFUsers) {
		
		LOGGER.info("  ....Start");
		JSONObject data = new JSONObject();
		try {
			
//			data = testLinkService.insertUserInDatabaseToRegisterRandomUsers(noOFUsers);
			LOGGER.info(" Fetching....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}
	}
	
}
