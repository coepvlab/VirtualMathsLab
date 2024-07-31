/**
 * 
 */
package in.ac.coep.service;

import org.json.JSONObject;
import in.ac.coep.entity.User;
import in.ac.coep.vo.QuestionGroupVO;

/**
 * @author Prashant
 *
 */
public interface QuestionGroupService {

	/**
	 * @param questionGroupVO
	 * @param user 
	 * @return
	 */
	public JSONObject addQuestionGroup(QuestionGroupVO questionGroupVO, User user)throws Exception;

	/**
	 * @param status
	 * @return
	 */
	public JSONObject fetchQuestionGroups(String status)throws Exception;

	/**
	 * @param questionGroupId
	 * @return
	 */
	public JSONObject fetchQuestionsGroupById(long questionGroupId)throws Exception;

	/**
	 * @param questionGroupVO
	 * @param user 
	 * @return
	 */
	public JSONObject modifyQuestionGroup(QuestionGroupVO questionGroupVO, User user)throws Exception;

	/**
	 * @param questionGroupId
	 * @return
	 */
	public JSONObject archivedQuestionsGroupById(long questionGroupId)throws Exception;

	/**
	 * @param refineJson
	 * @return
	 */
	public JSONObject fetchQuestionGroupsOnDifferentCeiteria(String refineJson)throws Exception;

	/**
	 * @param sectionId
	 * @return
	 */
	public JSONObject fetchLevelWiseCountOfQuestionGroupsBySections(int sectionId)throws Exception;

	public JSONObject fetchQuestionsGroupByUserId(long userId, String status) throws Exception;

	public JSONObject approveQuestionsGroupById(User user, long qGID)  throws Exception;

	public JSONObject approveMultipleQuestionsGroupById(User user, long[] qGIDArr)   throws Exception;

	public JSONObject archivedMultipleQuestionsGroupById(User user, long[] qGIDArr)    throws Exception;

	public JSONObject deleteQuestionsGroupById(long questionGroupId)  throws Exception;

	public JSONObject getQuestionGroupsByFilter(String status, long[] topicID, long[] variationNum, int[] difficultyLevel)  throws Exception;

	public JSONObject fetchQuestionsGroupsForFilter(String status)   throws Exception;

	public JSONObject fetchQuestionsGroupsForFilterFromMapping(String status)    throws Exception;

	public JSONObject changeTimeForGivenVariationNo(String status, long[] topicID, long[] variationNum,
			int[] difficultyLevel, int[] time)   throws Exception;

	public JSONObject deleteArchiveQuestions() throws Exception;

	public JSONObject fetchQuestionsGroupsForFilterFromMapping(String status, String topicNo)throws Exception;

	
	
	
	
}
