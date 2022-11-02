/**
 * 
 */
package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.TestInstanceState;

/**
 * @author Prashant
 *
 */
public interface TestInstanceStateDao {

	/**
	 * @param instanceState
	 */
	public void save(TestInstanceState instanceState)throws Exception;

	/**
	 * @param userId
	 * @return
	 */
	public List<TestInstanceState> getTestInstanceStateByUserId(long userId)throws Exception;

	/**
	 * @param testInstanceStateId
	 * @return
	 */
	public TestInstanceState getTestInstanceStateById(long testInstanceStateId)throws Exception;

	/**
	 * @param instanceState
	 */
	public void update(TestInstanceState instanceState)throws Exception;

	/**
	 * @param status
	 * @return
	 */
//	public List<TestInstanceState> getTestInstanceStateByStatus(String status)throws Exception;

	/**
	 * @return
	 */
//	public List<TestInstanceState> getAllStatesByStatusAndPdfGenerate()throws Exception;

	/**
	 * @param userId
	 * @param testId
	 * @return
	 */
	public List<TestInstanceState> getTestInstanceStateByUserIdAndTestId(long userId, long testId)throws Exception;

	public List<TestInstanceState> getTestInstanceStateByUserIdAndTopicId(long userId, String topicId, long tetsTypeId) throws Exception;

	public TestInstanceState getTestInstanceStateByUserIdandTestLevelAndTISID(long userId, long tISID)  throws Exception;

	public List<TestInstanceState> getTestInstanceStateByUserIdAndtestLink(long userId, String urlCheck)  throws Exception;

	public List<TestInstanceState> getTestInstanceStateByUserIdAndTestType(long userId, long practicetesttypeid)   throws Exception;

	
//	/**
//	 * @param userId
//	 * @param testId
//	 * @return
//	 */
//	public TestInstanceState getTestInstanceStateByEmailIdAndDeptId(long userId, long testId, int testLevel)throws Exception;
//
//	public TestInstanceState getTestInstanceStateByUserIdandTestLevel(long userId, int testLevel) throws Exception;
//
//	public List<TestInstanceState> getAllStatesByStatusAndPdfGeneratetoupgrade(int deptId, int testLevel) throws Exception;
//
//	public List<TestInstanceState> getRound2AndRound3Percentage(int deptId) throws Exception;
//
//	public List<TestInstanceState> getAveragePercentage(int deptId, int testLevel) throws Exception;
	
	
//	public List<TestInstanceState> getUsersByStatusAndPdfGenerateToSendReports() throws Exception;
//
//	public List<TestInstanceState> getAllUSersListWithLastRound(int testLevel) throws Exception;
//
//	public TestInstanceState getTestInstanceStateByUserIDAndCurrRound(long userId, int currRoundNo) throws Exception;
//
//	public TestInstanceState getTestInstanceStateTestLevelByUserIdAndTestId(long userId, long testId)throws Exception;
//
//	public List<TestInstanceState> getTestInstanceStateByUserIdForResult(long userId) throws Exception;
//
//	public TestInstanceState getTestInstanceStateByUserIdandTestLevelByMediaId(Long userId, int testlevel, String mediaID) throws Exception;
//
//	public List<TestInstanceState> getTotalTestCompletedAndTestInProgressCount(int testLevel) throws Exception;
//
//	public List<TestInstanceState> getAllTestInstanceState()throws Exception;
//
//	public TestInstanceState getTestInstanceStateByStudentsUserId(long userId) throws Exception;
//
//	public List<TestInstanceState> getAllTestInstanceStateByStatus() throws Exception;
//
//	public List<TestInstanceState> getAllUsersWithLastRoundTestCompleted(int testLevel) throws Exception;

	
	
	
}
