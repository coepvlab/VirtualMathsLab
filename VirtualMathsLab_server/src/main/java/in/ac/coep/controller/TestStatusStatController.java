//package in.ac.coep.controller;
//
//import org.apache.log4j.Logger;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import in.ac.coep.entity.TestInstanceState;
//import in.ac.coep.service.TestStatusStatService;
//
//@Controller
//public class TestStatusStatController {
//
//	private static final Logger LOGGER = Logger.getLogger(TestStatusStatController.class);
//	
//	@Autowired
//	private TestStatusStatService  testStatusStatService;
//	
//	@RequestMapping(value="/totalRegisteredCount", method = RequestMethod.GET)
//	public @ResponseBody String inTechTotalRegistered() {
//		
//		JSONObject data = new JSONObject();
//		try {
//			LOGGER.info("Registered Count Fetch start..");
//			data = testStatusStatService.inTechTotalRegistered();
//			LOGGER.info("Registered Count Fetch end..");
//			return data.toString();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	@RequestMapping(value="/total", method = RequestMethod.GET)
//	public String inTechTotalRegisteredCount(ModelMap model) {
//		
//		JSONObject data = new JSONObject();
//		try {
//			data = testStatusStatService.inTechDetails(1);
//			model.addAttribute("PD", data.get("PD"));
//			model.addAttribute("TR", data.get("TR"));
//			model.addAttribute("TC", data.get("TC"));
//			model.addAttribute("TP", data.get("TP"));
//			model.addAttribute("TTP", data.get("TTP"));
//			return "details";
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	@RequestMapping(value="/totalStudentInProgressState", method = RequestMethod.GET)
//	public @ResponseBody String totalStudInProgressState(@RequestParam int testLevel) {
//		
//		JSONObject data = new JSONObject();
//		try {
//			LOGGER.info("Total Student in Progress state common Fetch start..");
//			data = testStatusStatService.totalStudInProgState(testLevel);
//			LOGGER.info("Total Student in Progress state common Fetch end..");
//			return data.toString();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	@RequestMapping(value="/StudentInProgressStateUpdate", method = RequestMethod.PUT)
//	public @ResponseBody String StudInProgressStateUpdate(@RequestBody TestInstanceState testInstanceState) {
//		
//		JSONObject data = new JSONObject();
//		try {
//			LOGGER.info("Student in Progress state update start..");
//			data = testStatusStatService.studInProgStateUpdateByInstStId(testInstanceState);
//			LOGGER.info("Student in Progress state update end..");
//			data.put("done", true);
//			data.put("msg", "Student record updated");
//			return data.toString();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			data.put("done", false);
//			data.put("msg", "Student record not updated");
//			return data.toString();
//		}
//	}
//	
//	@RequestMapping(value="/totalStudentInCompletedTestTimeBound", method = RequestMethod.GET)
//	public @ResponseBody String totalStudInCompletedTestTimeBound(@RequestParam int testLevel, @RequestParam long startDate, @RequestParam long endDate) {
//		
//		JSONObject data = new JSONObject();
//		try {
//			LOGGER.info("Total Student in Completed Test state with time bound fetch start..");
//			data = testStatusStatService.totalStudInCompletedTestTimeBound(testLevel, startDate, endDate);
//			LOGGER.info("Total Student in Completed Test state with time bound fetch end..");
//			return data.toString();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	@RequestMapping(value="/totalStudentInProgressTestTimeBound", method = RequestMethod.GET)
//	public @ResponseBody String totalStudInProgressStateTimeBound(@RequestParam int testLevel, @RequestParam long startDate, @RequestParam long endDate) {
//		
//		JSONObject data = new JSONObject();
//		try {
//			LOGGER.info("Total Student in Progress Test state with time bound fetch start..");
//			data = testStatusStatService.totalStudInProgressTestTimeBound(testLevel, startDate, endDate);
//			LOGGER.info("Total Student in Progress Test state with time bound fetch end..");
//			return data.toString();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	
//	@RequestMapping(value="/totalStudentInProgressStateForLastHr", method = RequestMethod.GET)
//	public @ResponseBody String totalStudInProgressStateForLastHr(@RequestParam int testLevel) {
//		
//		JSONObject data = new JSONObject();
//		try {
//			LOGGER.info("Total Student in Progress Test state in last hour fetch start..");
//			data = testStatusStatService.totalStudInProgStateForLastHr(testLevel);
//			LOGGER.info("Total Student in Progress Test state in last hour fetch end..");
//			return data.toString();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//}
