package in.ac.coep.utility;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import in.ac.coep.dao.UserDao;

public class MailSenderForPdfReport implements Runnable {
	
//	@Autowired
//	 TestInstanceStateDao instanceStateDao;
	
	@Autowired
	 UserDao userDao;
	
	private JSONObject data;
//	private JavaMailSenderImpl mailSender;
//	private TemplateEngine classTemplateEngine;

	public MailSenderForPdfReport(JSONObject data, JavaMailSenderImpl mailSender,
			TemplateEngine classTemplateEngine) {
		super();
		this.data = data;
//		this.mailSender = mailSender;
//		this.classTemplateEngine = classTemplateEngine;
		
	}

	
	
	public void run() {
	//	synchronized (this) {
			
		
		try{
			
			final Context ctx = new Context();
			
			
			ctx.setVariable("username", data.getString("UserName"));
		//	MailUtil mailUtil = new MailUtil();
//			MailUtil.sendMailforpdfreport(ctx,data,
//					Constants.PDFREPORT_SUCCESS_MAIL_SUBJECT+ data.getString("TestName")+"(ROUND "+ data.getString("RoundNo")+")" ,
//					Constants.PDFREPORT_SUCCESS_MAIL_TEMPLATE,
//					Constants.FROM_EMAIL, data.getString("EmailId").split(","),
//					null, null, mailSender, classTemplateEngine);
		
			
	//		updatestatus();
			
//			wait();
			
			
		//	User user = new User();
		//	user = userDao.getUserByEmailId(data.getString("EmailId"));
			
//			TestInstanceState instanceState = new TestInstanceState();
//			instanceState = instanceStateDao.getTestInstanceStateByUserId1(data.getLong("UserId"));
//			instanceState.setScorecardSentStatus(true);
//			instanceStateDao.update(instanceState);
//			System.out.println("status updated");
//			
			
//			notify();
//			System.out.println("about to end this thread");
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	//	}
	}
	
	

	


}
