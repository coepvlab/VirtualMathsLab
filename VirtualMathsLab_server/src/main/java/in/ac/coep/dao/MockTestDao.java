package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.PerformanceTag;
import in.ac.coep.entity.TestInstanceStatistics;
import in.ac.coep.entity.User;

public interface MockTestDao {

	public List<TestInstanceStatistics> getTestByUserAndTopicId(User user, long topicId) throws Exception;

	public PerformanceTag getRecordByVariationNo(int varNo, User user) throws Exception;
	
	public void updatePerformanceTag(PerformanceTag pt) throws Exception;

}
