package in.ac.coep.service;
import org.json.JSONObject;

import in.ac.coep.entity.User;

public interface MailService {
	
	public void sendForgetPasswordSuccessfuLData(User users,String password)throws Exception;
	
	public JSONObject sendPassword(User users) throws Exception;


}
