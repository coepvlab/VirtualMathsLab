/**
 * 
 */
package in.ac.coep.service;

import org.json.JSONObject;

import in.ac.coep.entity.User;

/**
 * @author Prashant
 *
 */
public interface TestInstanceStatisticsService {
	
	
	public JSONObject getAllInstanceStatistics(User user) throws Exception;

//	public JSONObject getAllInstanceStatisticsbyUserId() throws Exception;
}
