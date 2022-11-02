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
public interface BatchJobService {

	/**
	 * @param testInstanceState
	 * @return 
	 */
	public JSONObject moveDataFromTestInstanceToTestInstanceCompletion(TestInstanceState testInstanceState)throws Exception;

	
	
}
