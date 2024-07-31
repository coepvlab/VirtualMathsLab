package in.ac.coep.service;

import org.json.JSONObject;

import in.ac.coep.entity.User;

public interface MockTestService {

	public JSONObject generateTag(User user, long topicId, String topicNo)throws Exception;

}
