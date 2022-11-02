package in.ac.coep.service;

import org.json.JSONObject;

import in.ac.coep.vo.TopicVO;

public interface TopicService {

	public JSONObject addTopic(TopicVO topicVO) throws Exception;

	public JSONObject assignParent(long topicId, long[] parentId, String status)  throws Exception;

	public JSONObject getLTopicsDetails() throws Exception;

	public JSONObject checkAvailability(String topicNo) throws Exception;

	public JSONObject assignPrerequisite(long topicId, long[] preId, String status)  throws Exception;

	public JSONObject updateTopic(TopicVO topicVO) throws Exception;

	public JSONObject deleteTopic(String topicNo)throws Exception;

	public JSONObject getTopicMappingDetails(String status)throws Exception;

	public JSONObject updateParent(String parentTMJson)throws Exception;

	public JSONObject updatePrerequisite(String parentTMJson)throws Exception;

	public JSONObject deleteTomppingMappingByTMid(long tmId)throws Exception;

	public JSONObject getLTopicsToHomePage() throws Exception;

	public JSONObject getTopicByTopicID(long topicId) throws Exception;

	public JSONObject getVariationNoByTopicId(long[] topicId, String status);

	public JSONObject getTopicNameByTopicID(long topicId)  throws Exception;

	public JSONObject getParentTopicByTopicID(long topicId)  throws Exception;

	public JSONObject getPrerequisiteTopicByTopicID(long topicId) throws Exception;

	public JSONObject getnextTopicByTopicID(long topicId) throws Exception;

	
}
