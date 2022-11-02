///**
// * 
// */
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
//import in.ac.coep.service.CreatePdfReportService;
//import in.ac.coep.service.CreatePdfforSingleUserService;
//
///**
// * @author Prashant
// *
// */
//@Controller
//public class CreatePdfReportsController {
//
//	private static final Logger LOGGER = Logger.getLogger(CreatePdfReportsController.class);
//	
//	@Autowired
//	CreatePdfReportService createPdfReportService;
//	
//	
//	@Autowired
//	CreatePdfforSingleUserService createPdfforSingleUserService;
//	
//	@RequestMapping(value="/api/test/user/createPDF", method = RequestMethod.POST)
//	public @ResponseBody String sendPdfReportsToUser() {
//		
//		JSONObject data = new JSONObject();
//		try {
//			LOGGER.info("fetch data to create PDF reports start");
//			createPdfReportService.getAllDataForPDFReport();
//			data.put("msg", "reports created succesfully");
//			data.put("done", true);
//			LOGGER.info("fetch data to create PDF reports  end");
//		} catch (Exception e) {
//			e.printStackTrace();
//			LOGGER.error("Error while fetching data From Database to pdf reports ....",e);
//			// TODO: handle exception
//		}
//		return data.toString();
//
//	}
//	
//	@RequestMapping(value="/api/test/user/createSingleResultFileForAllStud", method = RequestMethod.GET)
//	public @ResponseBody String createSinglePDFResult(@RequestParam String date) {
//		
//		JSONObject data = new JSONObject();
//		try {
//			LOGGER.info("fetch pdf to create single PDF reports start");
//			createPdfReportService.createSinglePDFResult(date);
//			data.put("msg", "Final reports created succesfully");
//			data.put("done", true);
//			LOGGER.info("fetch pdf to create single PDF reports  end");
//		} catch (Exception e) {
//			e.printStackTrace();
//			LOGGER.error("Error while fetching pdf file....",e);
//			// TODO: handle exception
//		}
//		return null;
//	}
//	@RequestMapping(value="/api/test/user/createPDFforSingleUser", method = RequestMethod.GET)
//	public @ResponseBody String createPdfReportsForSingleUser(@RequestParam long userId, @RequestParam int testLevel) {
//		
//		JSONObject data = new JSONObject();
//		try {
//			LOGGER.info("fetch data to create PDF reports start");
//			createPdfforSingleUserService.getAllDataForPDFReportforSingleUser(userId, testLevel);
//			data.put("msg", "reports created succesfully");
//			data.put("done", true);
//			LOGGER.info("fetch data to create PDF reports  end");
//		} catch (Exception e) {
//			e.printStackTrace();
//			LOGGER.error("Error while fetching data From Database to pdf reports ....",e);
//			// TODO: handle exception
//		}
//		return data.toString();
//
//	}
//
//
//}
