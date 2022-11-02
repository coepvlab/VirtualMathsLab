package in.ac.coep.service;

import org.json.JSONObject;

public interface MoveDataFromTestInstanceToCompletionService {

	public JSONObject moveDataFromTestInstToComp(long userId, long tISID) throws Exception;
	
}
