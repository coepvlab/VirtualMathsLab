/**
 * 
 */
package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.TestInstance;

/**
 * @author Prashant
 *
 */
public interface TestInstanceDao {

	/**
	 * @param instance
	 * @throws Exception 
	 */
	public long save(TestInstance instance) throws Exception;

	/**
	 * @param testInstance
	 */
	public void updateTestInstance(TestInstance testInstance)throws Exception;

	/**
	 * @param testInstanceId
	 * @return
	 */
	public TestInstance getTestInstanceById(long testInstanceId)throws Exception;

	/**
	 * @param testInstanceStateId
	 * @return
	 */
	public List<TestInstance> getTestInstanceByTISId(long testInstanceStateId)throws Exception;

	/**
	 * @param testInstance
	 */
	public void delete(TestInstance testInstance)throws Exception;

	/**
	 * @param questionId
	 * @param userId
	 * @param testInstanceStateId
	 * @return
	 */
	public List<TestInstance> getTestInstanceByQidUsrIdTSid(long questionId, long userId, long testInstanceStateId)throws Exception;

	/**
	 * 
	 * @param testInstanceStateId
	 * @param secId
	 * @param passed
	 * @return
	 * @throws Exception
	 */
	public long getCountOfTestInstanceByTISidAndSecId(long testInstanceStateId, String topicId, boolean passed)throws Exception;

	/**
	 * @param tisId
	 * @param ntAnswer 
	 * @return
	 */
	public List<Object[]> fetchAttemptedAndTotalQuestion(long tisId, boolean ntAnswer)throws Exception;

	public List<TestInstance> getTestInstanceByUsrId(long userId) throws Exception;

	public List<TestInstance> getTestInstanceByUsrIdAndTestLvl(long userId, int testLevel) throws Exception;

	public List<TestInstance> getAllQuestionGroupsByTopicIdForResumeTest(long topicId, long userId, long testInstanceStateId) throws Exception;

	public TestInstance getTestInstanceByQuesionIdAndUserId(long questionId, long questionGroupId, long userId, long testInstanceStateId) throws Exception;

	public List<TestInstance> getTestInstanceListByQuesionIdAndUserId(long questionId, long questionGroupId,
			long userId, long testInstanceStateId) throws Exception;

	public List<TestInstance> getTestInstanceByQuesId(long QuesId) throws Exception;

//	public List<TestInstance> getTestInstanceByUsrIdAndPracticeTestNo(long userId, int practiceTestNo) throws Exception;

	public List<TestInstance> getTestInstanceByUsrIdAndTestInstanceStateId(long userId, long testInstanceStateId) throws Exception;

	public List<TestInstance> getTestInstanceByTISIdAndUserId(long testInstanceStateId, long userId)  throws Exception;


}
