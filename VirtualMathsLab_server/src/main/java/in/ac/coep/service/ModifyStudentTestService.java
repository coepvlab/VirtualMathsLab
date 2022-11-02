package in.ac.coep.service;

import org.json.JSONObject;

public interface ModifyStudentTestService {

	public JSONObject getAllStudentList()throws Exception;

	public JSONObject getTestInstanceStaedetailByUserId(long userId)throws Exception;

//	public JSONObject updateTestInstanceStaedetail(TestInstanceState testInstanceState)throws Exception;
	
	public JSONObject getSectionsFromTestId(long testId, long userId)throws Exception;

	public JSONObject gettestInstanceStateForScoreCardLink(long userId, int testLevel)throws Exception;
}
