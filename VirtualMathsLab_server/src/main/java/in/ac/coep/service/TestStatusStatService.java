/**
 * 
 */
package in.ac.coep.service;

import org.json.JSONObject;

/**
 * @author Vaibhav
 *
 */
public interface TestStatusStatService {
	
	public JSONObject inTechDetails(int testLevel) throws Exception;
	
	public JSONObject inTechTotalRegistered() throws Exception;

	public JSONObject totalStudInProgState(int testLevel) throws Exception;

//	public JSONObject studInProgStateUpdateByInstStId(TestInstanceState testInstanceState) throws Exception;

	public JSONObject totalStudInCompletedTestTimeBound(int testLevel, long startDate, long endDate) throws Exception;

	public JSONObject totalStudInProgressTestTimeBound(int testLevel, long startDate, long endDate) throws Exception;

	public JSONObject totalStudInProgStateForLastHr(int testLevel) throws Exception;

}
