package in.ac.coep.service;

import org.json.JSONObject;

import in.ac.coep.entity.User;

/**
 * 
 * @author Prashant
 *
 */
public interface TestLinkService {

	
	public JSONObject generatePracticeTestLinkForRequestedUser(User user, long topicId, long testTypeId, long testId, int compLevel) throws Exception;
	
	public JSONObject generateOtherTestLinkForRequestedUser(User user,  long testTypeId, long testId) throws Exception;
//	/**
//	 * @param email
//	 * @param domain
//	 * @param testlvl 
//	 * @return
//	 * @throws Exception 
//	 */
//	public JSONObject generateLinkForRequestedMailId(long phoneNumber, int domain, int testlvl) throws Exception;
//
//	/**
//	 * @param userVO
//	 * @return
//	 */
//	public JSONObject insertUserInDatabase(UserVO userVO)throws ConstraintViolationException,Exception;
//	
//	/**
//	 * @param userVO
//	 * @return
//	 */
//	public JSONObject updateUserInDatabase(UserVO userVO) throws Exception;
//
//	public JSONObject updatePaymentInfo(UserVO userVO) throws Exception;
//
//	public JSONObject getAllStudentsListFromTestInstanceState(int testLevel) throws Exception;
//	
//	
//	public JSONObject generateLinkForNextRound(String email, int domain, int testlvl) throws Exception;
//
//	public JSONObject insertUserInDatabaseToRegisterRandomUsers(int nooFusers) throws Exception;
//
//	public JSONObject updateUserCurrentRoundForNextRoundForGanit() throws Exception;
//
//	public JSONObject getAccountUnlocked(String userMailId) throws Exception;
//
//	public JSONObject getAllStudentsIdCardsDetails() throws Exception;
//
//	public JSONObject createTestLinkCurrentRoundForNextRoundForGanit() throws Exception;
//
//	public JSONObject emailVerificationService(String token)throws Exception;

	
}
