/**
 * 
 */
package in.ac.coep.service;

import org.json.JSONObject;

import in.ac.coep.entity.TestInstanceState;

/**
 * @author Prashant
 *
 */
public interface TestInstanceStateService {

	/**
	 * @param token
	 * @param currentRoundNo 
	 * @return
	 */
	public String isURLValid(String token, long userId) throws Exception;


	/**
	 * 
	 * @param instanceState
	 * @return
	 * @throws Exception
	 */
	public JSONObject updateTestInstanceState(TestInstanceState instanceState) throws Exception;

	/**
	 * @param testInstanceState
	 * 
	 */
//	public void moveDataFromTestInstanceToTestInstanceCompletion(TestInstanceState testInstanceState) throws Exception;

	/**
	 * 
	 */
//	public void createStatisticsDataForReport() throws Exception;

	/**
	 * @param tisId
	 * @return
	 */
	public JSONObject fetchAttemptedAndTotalQuestion(long tisId) throws Exception;

//	public JSONObject updateGrade(int max, int mid, int deptId, int testLevel) throws Exception;

//	public JSONObject generateAvgPercentage(int deptId) throws Exception;

//	public JSONObject updateFinalGradeCriteriaGrade(int min, int deptId, int testLevel) throws Exception;

	public JSONObject updateCurrentTestTime(TestInstanceState instanceState) throws Exception;


}
