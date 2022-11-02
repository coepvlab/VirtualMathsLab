package in.ac.coep.service;

import org.json.JSONObject;

public interface UserRegistrationService {
	
	public JSONObject forceFullyLogoutUserByEmail(String email)throws Exception;
}
