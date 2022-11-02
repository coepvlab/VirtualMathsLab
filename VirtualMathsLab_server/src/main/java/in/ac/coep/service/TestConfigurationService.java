/**
 * 
 */
package in.ac.coep.service;

import org.json.JSONObject;

import in.ac.coep.vo.TestConfigurationVO;
import in.ac.coep.vo.TestVO;

/**
 * @author Prashant
 *
 */
public interface TestConfigurationService {

	/**
	 * @param testConfigurationVO
	 * @return
	 */
	public JSONObject setTestPaper(TestConfigurationVO[] testConfigurationVOs)throws Exception;

	/**
	 * @param testConfigurationVOs
	 * @return
	 */
	public JSONObject modifyTestPaper(TestConfigurationVO[] testConfigurationVOs)throws Exception;

	/**
	 * @param sectionId
	 * @return
	 */
	public JSONObject fetchLevelWiseQuestionGroupsBySectionId(int sectionId)throws Exception;

	/**
	 * @param sectionId
	 * @param testId 
	 * @return
//	 */
//	public JSONObject fetchtestConfigurationDataBySectionId(int sectionId, long testId)throws Exception;

	/**
	 * @param testId
	 * @param userId 
	 * @return
	 */
	public JSONObject fetchTestQuestionGroupsForTest(long testId, long userId)throws Exception;

	/**
	 * @param token
	 */
//	public JSONObject fetchSectionNameAndTimeForTest(String token)throws Exception;

	public JSONObject fetchAvailableOtherTestToConfig()throws Exception;

	public JSONObject updateTestDetails(TestVO testVO) throws Exception;

//	public JSONObject fetchTestToConfigDetails(long testId) throws Exception;

	public JSONObject updateTestConfigDetails(TestConfigurationVO[] testConfigurationVOs) throws Exception;

	public JSONObject fetchTopicNameAndTimeForPracticeTest(String token)  throws Exception;

	public JSONObject fetchTestQuestionGroupsForOtherTest(long testId, long userId) throws Exception;

}
