//package in.ac.coep.controller;
//
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import in.ac.coep.service.ModifyStudentTestService;
//
//@Controller
//public class ModifyStudentTestController {
//	
//	@Autowired
//	ModifyStudentTestService modifystudenttestservice;
//	
//	
//	@RequestMapping(value = "/api/create/getAll/linkGeneratedStudentList", method = RequestMethod.GET)
//	public @ResponseBody String getAllLinkGeneratedStudentList(Authentication authentication) {
////		User user = (User) authentication.getPrincipal();
//		
//		JSONObject data = new JSONObject();
//		try {
//			
//			data = modifystudenttestservice.getAllStudentList();
//			return data.toString();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}	
//	}
//	
//	@RequestMapping(value = "/api/create/getAll/userTestInfo", method = RequestMethod.GET)
//	public @ResponseBody String getUserTestInstanceDetails(Authentication authentication, @RequestParam long userId) {
////		User user = (User) authentication.getPrincipal();
//		
//		JSONObject data = new JSONObject();
//		try {
//			
//			data = modifystudenttestservice.getTestInstanceStaedetailByUserId(userId);
//			return data.toString();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}	
//	}
//	
////	@RequestMapping(value = "/updateTestInstanceState", method = RequestMethod.PUT)
////	public @ResponseBody String updateTestInstanceStateDetails(@RequestBody TestInstanceState testInstanceState) {
////
////		
////		JSONObject data = new JSONObject();
////		try {
////			
////			data = modifystudenttestservice.updateTestInstanceStaedetail(testInstanceState);
////			return data.toString();
////		
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////			return null;
////		}	
////
////	}
////	
//	
//	@RequestMapping(value = "/api/create/getAll/getsectionfromtestid", method = RequestMethod.GET)
//	public @ResponseBody String getsectionFromTestId( @RequestParam long testId, @RequestParam long userId ) {
//
//		
//		JSONObject data = new JSONObject();
//		try {
//			
//			data = modifystudenttestservice.getSectionsFromTestId(testId, userId);
//			return data.toString();
//		
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}	
//
//	}
//	
//	@RequestMapping(value = "/getScoreCardLinkByUserIdAndTestLevel", method = RequestMethod.GET)
//	public @ResponseBody String getScoreCardLinkByUserIdAndTestLevel( @RequestParam long userId, @RequestParam int testLevel ) {
//
//		
//		JSONObject data = new JSONObject();
//		try {
////			LOGGER.info("fetch data reports start");
//			data = modifystudenttestservice.gettestInstanceStateForScoreCardLink(userId, testLevel);
////			LOGGER.info("fetch data reports end");
//			return data.toString();
//		
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}	
//
//	}
//	
//	
//}
