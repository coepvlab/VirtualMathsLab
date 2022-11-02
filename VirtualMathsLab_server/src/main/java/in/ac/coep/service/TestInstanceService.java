/**
 * 
 */
package in.ac.coep.service;

import org.json.JSONObject;

import in.ac.coep.vo.TestInstanceVO;

/**
 * @author Prashant
 *
 */
public interface TestInstanceService {

	/**
	 * @param testInstanceVO
	 * @return
	 */
	public JSONObject updateTestInstance(TestInstanceVO[] testInstanceVO) throws Exception;

//	public JSONObject updateTestInstanceGO(long tiId, boolean mcqFlag) throws Exception;

	/**
	 * @param userId
	 * @return  test instances for resume test
	 */
//	public JSONObject fetchTestQuestionGroupsForResumeTest(long userId)throws Exception;

	
	
}
