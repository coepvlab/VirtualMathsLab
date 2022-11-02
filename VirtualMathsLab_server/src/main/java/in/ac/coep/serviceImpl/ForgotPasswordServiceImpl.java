package in.ac.coep.serviceImpl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import in.ac.coep.dao.ForgotPasswordDao;
import in.ac.coep.dao.UserDao;
import in.ac.coep.entity.User;
import in.ac.coep.service.ForgotPasswordService;
import in.ac.coep.utility.MailSendForForgotPassWord;
import in.ac.coep.utility.Md5Encryption;
import in.ac.coep.utility.PasswordGenerator;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Autowired
	ForgotPasswordDao forgotPwdDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Autowired
	private TemplateEngine classtemplateEngine;
	
	@SuppressWarnings("static-access")
	@Override
	public JSONObject checkForgetPasswordData(String emailId) throws Exception {
		// TODO Auto-generated method stub

		JSONObject jsonObject = new JSONObject();
		long cnt = 0;

		User users = userDao.getUserByEmailId(emailId);
		if (users == null) {
			jsonObject.put("done", false);
			jsonObject.put("msg", "Given details are invalid");
		} else {

			cnt = forgotPwdDao.chekForgetPassData(users.getUserId(), emailId);

			if (cnt <= 0) {
				jsonObject.put("done", false);
				jsonObject.put("msg", "Given details are invalid");
			} else {

				String password = PasswordGenerator.generatePassword();

				users.setPassword(new Md5Encryption().md5(password));

				userDao.updateUSer(users);

				JSONObject mailData = new JSONObject();
				mailData.put("emailId", users.getEmailId());
				mailData.put("FIRSTNAME", users.getFirstName());
				mailData.put("UPDATEDPASSWORD", password);
				
				Thread.sleep(2000);
				Thread t = new Thread(new MailSendForForgotPassWord(mailData,
						mailSender, classtemplateEngine));
				t.start();
				
					jsonObject.put("msg", "New password is sent successfully on your registered email address");
					jsonObject.put("done", true);
			}
		}
		return jsonObject;
	}

	@SuppressWarnings("static-access")
	@Override
	public JSONObject ChangePasswordData(String newpwd, User user) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject jsonObject = new JSONObject();
		
		if(user != null){
			user.setPassword(new Md5Encryption().md5(newpwd));		
			userDao.updateUSer(user);
			jsonObject.put("msg", "Password Updated successfully.");
			jsonObject.put("done", true);
		}else{
			
			jsonObject.put("msg", "Something went Wrong. Please try again");
			jsonObject.put("done", false);
		}
		
		return jsonObject;
	}
}
