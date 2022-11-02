//package in.ac.coep.serviceImpl;
//
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//
//import in.ac.coep.constants.Constants;
//import in.ac.coep.entity.User;
//import in.ac.coep.service.MailService;
//import in.ac.coep.utility.MailUtil;
//
//@Service
//public class MailServiceImpl implements MailService{
//
//	
//
//	@Autowired
//	private JavaMailSenderImpl mailSender;
//	
//	@Autowired
//	private TemplateEngine classtemplateEngine;
//	
//	@Override
//	public void sendForgetPasswordSuccessfuLData(User users, String password) throws Exception {
//		// TODO Auto-generated method stub
//		
//		final Context ctx = new Context();
////		ctx.setVariable("email", users.getEmailId());
//		ctx.setVariable("email", users.getParEmailId());
////		ctx.setVariable("UsreID",users.getUserId());
//		ctx.setVariable("FirstName", users.getFirstName());
//		ctx.setVariable("pwd", password);
//		
////		MailUtil.sendMail(ctx, "Forget Password",
////				"forgotpass.html",
////				"vtu.admin@coep.ac.in", users.getEmailId().split(","), null,
////				null, mailSender, classtemplateEngine);
//		MailUtil.sendMailforForgotPassWord(ctx,
//			    Constants.FORGOTPASSWORD_MAIL_SUBJECT,
//			    Constants.FORGOTPASSWORD_MAIL_TEMPLATE,
//			    Constants.FROM_EMAIL, users.getParEmailId().split(","),
//			    null, null, mailSender, classtemplateEngine);
//		
//	}
//
//	@Override
//	public JSONObject sendPassword(User users) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
