package in.ac.coep.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.entity.User;
import in.ac.coep.service.ForgotPasswordService;

@Controller
public class ForgotPasswordController {
	@Autowired
	ForgotPasswordService forgotPasswordService;

	@RequestMapping(value = "/checkForgetPassData/{emailId:.+}", method = RequestMethod.POST)
	public  @ResponseBody String getforgotPwdData(@PathVariable String emailId,Model model) throws Exception {
		JSONObject data = new JSONObject();

		data = forgotPasswordService.checkForgetPasswordData(emailId);
		return data.toString();
	}
	
	@RequestMapping(value = "/confirmPassData/{newpwd}", method = RequestMethod.POST)
	public  @ResponseBody String getConfirmPwdData(@PathVariable String newpwd, Model model, Authentication authentication) throws Exception {
		
		User user= (User) authentication.getPrincipal();
		JSONObject data = new JSONObject();
		data = forgotPasswordService.ChangePasswordData(newpwd, user);
		return data.toString();
	}
}
