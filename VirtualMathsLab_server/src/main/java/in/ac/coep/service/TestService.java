package in.ac.coep.service;

import org.json.JSONObject;

import in.ac.coep.entity.User;
import in.ac.coep.vo.TestVO;

/**
 * @author Prashant
 *
 */
public interface TestService {

	/**
	 * @param testVO
	 * @param user 
	 * @return
	 */
	public JSONObject configureTestPaper(TestVO testVO, User user) throws Exception;

	/**
	 * @param testVO
	 * @return
	 */
	public JSONObject modifyTestPaper(TestVO testVO,User user) throws Exception;

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public JSONObject getAvailableTestForLoggedInUser(Long userId) throws Exception;
	
	
	

}
