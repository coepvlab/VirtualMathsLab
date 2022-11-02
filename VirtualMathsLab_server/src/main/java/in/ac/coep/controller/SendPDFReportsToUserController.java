//package in.ac.coep.controller;
//
//import org.apache.log4j.Logger;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import in.ac.coep.service.SendPdfReportsToUserService;
//
//@Controller
//public class SendPDFReportsToUserController {
//
//	private static final Logger LOGGER = Logger.getLogger(SendPDFReportsToUserController.class);
//	
//	@Autowired
//	SendPdfReportsToUserService sendPdfReportsToUser;
//	
//	@RequestMapping(value="/api/test/user/sendPDF", method = RequestMethod.POST)
//	public @ResponseBody String sendPdfReportsToUser() {
//		
//		JSONObject data = new JSONObject();
//		try {
//			LOGGER.info("fetch data to create PDF reports start");
//			sendPdfReportsToUser.getAllDataToSendPDFReports();
//			data.put("msg", "reports send succesfully");
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
////	@SuppressWarnings("unused")
////	@RequestMapping(value="/api/test/user/PDF", method = RequestMethod.GET)
////	public @ResponseBody String ceatePdfReportsUser() {
////		
////		JSONObject data = new JSONObject();
////		try {
////			LOGGER.info("fetch data to create PDF reports start");
////			data = createPdfReportService.getAllDatatoPDFReport();;
//////			data.put("msg", "reports created succesfully");
//////			data.put("done", true);
////			LOGGER.info("fetch data to create PDF reports  end");
////		} catch (Exception e) {
////			e.printStackTrace();
////			LOGGER.error("Error while fetching data From Database to pdf reports ....",e);
////			// TODO: handle exception
////		}
////		return data.toString();
////
////	}
//}
