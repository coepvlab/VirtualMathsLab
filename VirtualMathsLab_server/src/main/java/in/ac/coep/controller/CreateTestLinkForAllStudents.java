//package in.ac.coep.controller;
//
//import org.apache.log4j.Logger;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import in.ac.coep.service.TestLinkService;
//
//@Controller
//public class CreateTestLinkForAllStudents {
//
//	private static final Logger LOGGER = Logger.getLogger(CreateTestLinkForAllStudents.class);
//	
//	@Autowired
//	TestLinkService testLinkService;
//	
//	@RequestMapping(value="/api/test/user/createTestLinks", method = RequestMethod.POST)
//	public @ResponseBody String createTestLinkForAllStudents(@RequestParam int testLevel) {
//		
//		JSONObject data = new JSONObject();
//		try {
//			LOGGER.info("fetch data to create Test links start");
//			testLinkService.getAllStudentsListFromTestInstanceState(testLevel);
//			data.put("msg", "Test links created succesfully");
//			data.put("done", true);
//			LOGGER.info("fetch data to createTest links  end");
//		} catch (Exception e) {
//			e.printStackTrace();
//			LOGGER.error("Error while createing Test links  ....",e);
//			// TODO: handle exception
//		}
//		return data.toString();
//
//	}
//	
//}
