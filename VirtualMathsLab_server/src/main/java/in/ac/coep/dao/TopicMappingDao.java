/**
 * 
 */
package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.TopicMapping;

/**
 * @author Prashant
 *
 */
public interface TopicMappingDao {

	
	public TopicMapping getTopicParentByTopicId(long topicId) throws Exception;
	
	public List<TopicMapping> getTopicChildsByTopicId(long topicId) throws Exception;
}
