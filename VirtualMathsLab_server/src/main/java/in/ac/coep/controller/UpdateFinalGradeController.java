//package in.ac.coep.controller;
//
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import in.ac.coep.service.TestInstanceStateService;
//
//@Controller
//public class UpdateFinalGradeController {
//	
//	@Autowired
//	private TestInstanceStateService testInstanceStateService;
//	
//	
//	
//	@RequestMapping(value = "/updatefinalgrade", method = RequestMethod.PUT)	
//	public @ResponseBody String updatefinalgrade(@RequestParam int max, @RequestParam int mid, @RequestParam int deptId,@RequestParam int testLevel) {
//		
//		JSONObject data = new JSONObject();
//		try {
//			
//			data = testInstanceStateService.updateGrade(max, mid,deptId,testLevel);
//			
//			return data.toString();
//			
//		} catch (Exception e) {
//		
//			return null;
//		}
//
//	}
//	
//	@RequestMapping(value = "/generateAvgerage", method = RequestMethod.GET)	
//	public @ResponseBody String generateAvgerage(@RequestParam int deptId) {
//		
//		JSONObject data = new JSONObject();
//		try {
//			
//			data = testInstanceStateService.generateAvgPercentage(deptId);
//			
//			return data.toString();
//			
//		} catch (Exception e) {
//		
//			return null;
//		}
//
//	}
//	
//
//	@RequestMapping(value = "/update/finalGrade/Criteira", method = RequestMethod.PUT)	
//	public @ResponseBody String criteriaForRound4(@RequestParam int min, @RequestParam int deptId,@RequestParam int testLevel) {
//		
//		JSONObject data = new JSONObject();
//		try {
//			
//			data = testInstanceStateService.updateFinalGradeCriteriaGrade(min,deptId,testLevel);
//			
//			return data.toString();
//			
//		} catch (Exception e) {
//		
//			return null;
//		}
//
//	}
//	
//}
