package in.ac.coep.dao;

import java.util.List;

import org.json.JSONObject;

import in.ac.coep.entity.QuestionGroup;
import in.ac.coep.entity.TestInstanceCompletion;

public interface QuestionGroupDao {
	/**
	 * 
	 * @param group
	 * @return
	 * @throws Exception
	 */
	public long addQuestionGroup(QuestionGroup group) throws Exception;

	/**
	 * 
	 * @param questionGroupId
	 * @return
	 * @throws Exception
	 */
	public QuestionGroup getQuestionGroupById(long questionGroupId) throws Exception;

	/**
	 * @param status 
	 * @return 
	 * 
	 */
	public List<QuestionGroup> getAllQuestionGroupsByStatus(String status) throws Exception;

	/**
	 * @param questionGroup
	 * @return
	 */
	public void modifyQuestionGroup(QuestionGroup questionGroup)throws Exception;

	/**
	 * @param filtersJson
	 * @return
	 */
	public List<QuestionGroup> fetchQuestionGroupsOnDifferentCeiteria(JSONObject filtersJson)throws Exception;

	/**
	 * @param sectionId
	 * @return
	 */
	public List<Object[]> fetchLevelWiseCountOfQuestionGroupsBySections(int sectionId)throws Exception;

	/**
	 * @param sectionId
	 */
	public boolean checkQuestionGroupsExistForSectionId(int sectionId)throws Exception;

	/**
	 * @param sectionId
	 * @param noOfQuestionGroup
	 * @param qgComplexityLevelId
	 * @return
	 */
	public List<QuestionGroup> getAllQuestionGroupsBySectionId(int sectionId, int noOfQuestionGroup,
			int qgComplexityLevelId)throws Exception;

	/**
	 * @param psysecid
	 * @param psyseclevelforintro
	 * @return
	 */
	public List<QuestionGroup> getAllQuestionGroupsBySectionIdAndLevel(int psysecid, int psyseclevelforintro) throws Exception;

	public List<QuestionGroup> getAllQuestionGroupsByUserId(long userId, String status) throws Exception;

	public List<QuestionGroup> getAllQuestionGroupsToApproveByStatus(String status) throws Exception;

	public void deleteQuestionByQuestionGroup(QuestionGroup questionGroupId) throws Exception;

	public List<Object[]> fetchLevelWiseCountOfQuestionGroupsByTopicId(long topicId)  throws Exception;

	public List<QuestionGroup> getAllQuestionGroupsByTopicId(long topicId, int noOfQuestionGroup,
			int qgComplexityLevelId)  throws Exception;

	public List<QuestionGroup> getAllQuestionGroupsByTopicId(long topicId) throws Exception; // This method is to create test for practise test

	public List<QuestionGroup> getAllQuestionGroupsByTestConfiguration(long topicId, int noOfQuestionGroup,
			int qgComplexityLevelId, String[] varNo) throws Exception;

	public List<QuestionGroup> getAllQuestionGroupsByFilter(String status, long topicId, String[] varNo, Object[] difficultyLevel)  throws Exception;

	public List<QuestionGroup> getAllQuestionGroupsByTopicIdAndStatus(String status, long topicId)  throws Exception;

	public List<QuestionGroup> getAllQuestionGroupsFromQuesGroupMappingToApproveByStatus(String status)  throws Exception;

	public List<QuestionGroup> getAllApprovedQuestionGroupsByTopicId(long topicId) throws Exception;

	public List<QuestionGroup> getAllApprovedQuestionGroupsByTopicIdAndcomplLevel(long topicId, int complLevel)  throws Exception;

	public List<QuestionGroup> getAllApprovedQuestionGroupsByTopicIdAndAllcomplLevel(long topicId, Object[] complLevelArr)  throws Exception;

	public List<Object[]> fetchVarNoWiseCountOfQuestionGroupsByTopicId(long topicId, String[] selectedVarNo)  throws Exception;

	public List<QuestionGroup> getAllApprovedQuestionGroupsByTopicIdAndVarNo(long topicId,
			String varNo)  throws Exception;

	public List<QuestionGroup> getArchiveQuestions() throws Exception;

	public List<QuestionGroup> getAllQuestionGroupsFromQuesGroupMappingToApproveByStatus(String status, String topicNo)throws Exception;

	public TestInstanceCompletion getTestInstanceCompetionRecordByQuestionGroupId(long questionGroupId)throws Exception;

//	public List<QuestionGroup> getAllQuestionGroupsToApproveByStatusForFiler(String status)   throws Exception;


//	public List<QuestionGroup> getQuestionGroupByUserIdAndTestInstanceStateId(long userId, long testInstanceStateId) throws Exception;

}
