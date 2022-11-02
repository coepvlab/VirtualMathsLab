//package in.ac.coep.serviceImpl;
//
//import java.util.List;
//
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.TemplateEngine;
//
//import in.ac.coep.constants.Constants;
//import in.ac.coep.dao.TestInstanceStateDao;
//import in.ac.coep.dao.UserDao;
//import in.ac.coep.entity.TestInstanceState;
//import in.ac.coep.service.SendPdfReportsToUserService;
//import in.ac.coep.utility.MailSenderForPdfReport;
//
//
//@Service
//public class SendPdfReportsToUserServiceImpl implements SendPdfReportsToUserService{
//
//	@Autowired
//	TestInstanceStateDao testInstanceStateDao;
//	
//	
//	@Autowired
//	private JavaMailSenderImpl mailSender;
//
//	@Autowired
//	private TemplateEngine classtemplateEngine;
//	
//
//	@Autowired
//	private TestInstanceStateDao instanceStateDao;
//	
////	@Autowired
////	private UserDao userDao;
//	
//	
//	
//	@SuppressWarnings("static-access")
//	@Override
//	public void getAllDataToSendPDFReports() throws Exception {
//		// TODO Auto-generated method stub
//		List<TestInstanceState> instanceStates = testInstanceStateDao.getUsersByStatusAndPdfGenerateToSendReports();
//		
//		JSONObject data = new JSONObject();
//		
//		for (TestInstanceState testInstanceState : instanceStates) {	
//			
//			
//			data.put("EmailId", testInstanceState.getUser().getEmailId());
//			data.put("UserName", testInstanceState.getUser().getFirstName());
//			data.put("TestName", testInstanceState.getTest().getName());
//			data.put("UserId", testInstanceState.getUser().getUserId());
//			data.put("RoundNo",	 Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND"));
//			
//			Thread t = new Thread(new MailSenderForPdfReport(data,
//					mailSender, classtemplateEngine));
//			t.start();	
//			t.sleep(3000);		
//			
////			testInstanceState.setScorecardSentStatus(true);
////			instanceStateDao.update(testInstanceState);
//		}
//		
//		
//		
//		
//	}
//
//}
