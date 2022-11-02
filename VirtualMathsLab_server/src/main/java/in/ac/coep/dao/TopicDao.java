package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.Topic;
import in.ac.coep.entity.TopicMapping;

/**
 * @author Prashant
 *
 */
public interface TopicDao {

	public void save(Topic topic) throws Exception;

	public Topic getTopicByTopicId(long parentId) throws Exception;

	public void updateTopicMpping(Topic topic) throws Exception;

	public void saveTopicMapping(TopicMapping mapping)  throws Exception;

	public List<Topic> getAllTopicDetails()  throws Exception;

	public Topic getTopicByTopicNo(String topicNo)  throws Exception;

	public List<TopicMapping> getTopicMappingByTopicId(long parentId, String status)  throws Exception;

	public void updateTopic(Topic tp1) throws Exception;

	public List<Topic> getAllTopicByLikeFilterTopicNo(String topicNo) throws Exception;

	public List<TopicMapping> getTopicMappingByTopicIdWithParent(long topicId) throws Exception;

	public List<TopicMapping> getTopicMappingByTopicIdWithTopic(long topicId) throws Exception;

	public void deleteTopicMapping(TopicMapping tm) throws Exception;

	public void deleteTopic(Topic topic) throws Exception;

	public List<TopicMapping> getAllTopicMappingByStatus(String status) throws Exception;

	public TopicMapping checkTopicMappingAlreadyExist(String status, Topic parentTopic, Topic topic) throws Exception;

	public void removeToppingMapping(TopicMapping tm) throws Exception;

	public TopicMapping getTopicMappingByTMid(long tmId) throws Exception;

	public List<Topic> getAllTopicDetailsByLevel(String topicLevel) throws Exception;

	public List<TopicMapping> getChildTopicsByTopicId(long topicId)  throws Exception;

	public TopicMapping getParentTopicsByTopicId(long topicId) throws Exception;

	public List<TopicMapping> getPrerequisiteTopicsByTopicId(long topicId) throws Exception;

	public List<Topic> getAllVerticalListByTopicLevel(String topicLevel) throws Exception;
	
}
