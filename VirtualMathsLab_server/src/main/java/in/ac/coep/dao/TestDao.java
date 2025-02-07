package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.Test;

/**
 * @author Prashant
 *
 */

public interface TestDao {

	/**
	 * @param test
	 */
	public long configureTestPaper(Test test)throws Exception;

	/**
	 * @param testId
	 * @return
	 */
	public Test getTestByTestId(long testId)throws Exception;

	/**
	 * @param test
	 * @return
	 */
	public void modifyTestPaper(Test test)throws Exception;

	/**
	 * @param domain
	 * @return
	 */
	public Test getTestByDepartmentId(int domain)throws Exception;

	/**
	 * @return
	 */
	public long getTestInstanceStateRowCount()throws Exception;

	/**
	 * @param userId
	 * @return
	 */
	public Test getTestByUserId(long userId)throws Exception;

	public List<Test> getAllTestList()throws Exception;

	public void updateTestDetails(Test test)throws Exception;
	
	/**
	 * @param domain
	 * @return
	 */
	public Test getTestByDepartmentIdAndTestLevel(int domain,int testLevel) throws Exception;

	public Test getTestForPracticeTestByTopicId(String selectedTopicId, String varType, int complLevel) throws Exception;

	public Test getTestBytopicIdVarNoAndVarTypeAndCompLevel(String selectedTopic, String varType, int complLevel,
			String varNo)  throws Exception;

	public List<Test> getAllTestListByTestTypeId(long testTypeId) throws Exception;

	public List<Test> getTestForPracticeTestByTopicIdAndTestLevel(String selectedTopics, int testLevel)throws Exception;

	public List<Test> getTestForPracticeTestByTopicIdOnly(String selectedTopics)throws Exception;

//	public Test getTestForPracticeTestByTopicId(String selectedTopicId, String varType)  throws Exception;

	
	


}
