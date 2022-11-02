package in.ac.coep.utility;

import org.json.JSONObject;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import in.ac.coep.constants.Constants;

public class MailSenderForRegistration implements Runnable {

	private JSONObject data;
	private JavaMailSenderImpl mailSender;
	private TemplateEngine classTemplateEngine;

	public MailSenderForRegistration(JSONObject data, JavaMailSenderImpl mailSender,
			TemplateEngine classTemplateEngine) {
		super();
		this.data = data;
		this.mailSender = mailSender;
		this.classTemplateEngine = classTemplateEngine;
		
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		// TODO Auto-generated method stub

		try {
			
				final Context ctx = new Context();
				
			
				ctx.setVariable("link", data.getString("link"));
				ctx.setVariable("userId",data.getLong("userId"));
//				ctx.setVariable("et", new Date(data.getLong("endDate")));
           
				MailUtil.sendMail(ctx,
						Constants.REGISTER_SUCCESS_MAIL_SUBJECT ,
						Constants.REGISTER_SUCCESS_MAIL_TEMPLATE,
						Constants.FROM_EMAIL, data.getString("emailId").split(","),
						null, null, mailSender, classTemplateEngine);

		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
