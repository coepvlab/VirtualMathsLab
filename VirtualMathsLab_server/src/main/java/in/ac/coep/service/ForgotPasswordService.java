package in.ac.coep.service;

import org.json.JSONObject;

import in.ac.coep.entity.User;

public interface ForgotPasswordService {
	
	public JSONObject checkForgetPasswordData(String emailId) throws Exception;

	public JSONObject ChangePasswordData(String newpwd, User user)throws Exception;
}
