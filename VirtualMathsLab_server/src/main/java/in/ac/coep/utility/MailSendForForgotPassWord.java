package in.ac.coep.utility;

import org.json.JSONObject;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import in.ac.coep.constants.Constants;

public class MailSendForForgotPassWord implements Runnable {
	
	
	private JSONObject data;
	private JavaMailSenderImpl mailSender;
	private TemplateEngine classTemplateEngine;

	public MailSendForForgotPassWord(JSONObject data, JavaMailSenderImpl mailSender,
			TemplateEngine classTemplateEngine) {
		super();
		this.data = data;
		this.mailSender = mailSender;
		this.classTemplateEngine = classTemplateEngine;
		
	}
	
	
	public void run() {
		
		
		try {
			
			final Context ctx = new Context();
			
		
			ctx.setVariable("FirstName", data.getString("FIRSTNAME"));
			ctx.setVariable("pwd",data.getString("UPDATEDPASSWORD"));
			
       
			MailUtil.sendMailforForgotPassWord(ctx,
					Constants.FORGOTPASSWORD_MAIL_SUBJECT,
					Constants.FORGOTPASSWORD_MAIL_TEMPLATE,
					Constants.FROM_EMAIL, data.getString("emailId").split(","),
					null, null, mailSender, classTemplateEngine);

	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}


}
