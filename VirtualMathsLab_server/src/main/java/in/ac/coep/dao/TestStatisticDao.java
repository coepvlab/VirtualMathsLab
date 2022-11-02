/**
 * 
 */
package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.TestInstanceStatistics;

/**
 * @author Prashant
 *
 */
public interface TestStatisticDao {

	/**
	 * @param instanceStatistics
	 */
	public void save(TestInstanceStatistics instanceStatistics)throws Exception;

	/**
	 * @param userId
	 * @return
	 */
//	public List<TestInstanceStatistics> getAllUsersStatisticsByuserIdFrmState(long userId)throws Exception;

	/**
	 * @param userId
	 * @param sectionArr 
	 * @return
	 */
	public List<TestInstanceStatistics> fetchStatisticsByUserId(long userId, Integer[] sectionArr)throws Exception;

	public List<TestInstanceStatistics> getAlltestStatisticsRecords() throws Exception;

	public List<TestInstanceStatistics> getAlltestStatisticsRecordsByUserId(long userId) throws Exception;

	public void delete(TestInstanceStatistics testInstanceStatistics1)throws Exception;

	public TestInstanceStatistics getAlltestStatisticsRecordsFromIds(long instanceStatisticsId, long userId)throws Exception;
	
	
	
}
