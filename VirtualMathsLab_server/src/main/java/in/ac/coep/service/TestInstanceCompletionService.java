package in.ac.coep.service;

import org.json.JSONObject;

public interface TestInstanceCompletionService {

	public JSONObject getTestQuestionPaperRecordByUserIdAndTISID(long tisId, long userId) throws Exception;

}
