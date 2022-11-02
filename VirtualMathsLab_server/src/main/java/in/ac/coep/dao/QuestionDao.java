/**
 * 
 */
package in.ac.coep.dao;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import in.ac.coep.entity.Question;

/**
 * @author Prashant
 *
 */
public interface QuestionDao {

	/**
	 * @param group
	 * @return 
	 * @throws Exception
	 */
	public long addQuestion(Question question) throws Exception;

	/**
	 * @param questionId
	 * @return
	 */
	public Question getQuestionByQuestionId(long questionId) throws Exception;

	/**
	 * @param status
	 * @return list of Questions which are pending
	 */
	public List<Question> fetchQuestions() throws Exception;

	/**
	 * @return
	 */
//	public List<Section> fetchApprovedQuestions() throws Exception;

	/**
	 * @param question
	 */
	public void deleteQuestion(Question question) throws ConstraintViolationException,Exception;

	/**
	 * @param questionId
	 * @return
	 */
//	public List<Section> getQuestionByQuestionAndSectionId(long questionId) throws Exception;

	/**
	 * 
	 * @param question
	 */
	public void updateQuestion(Question question) throws Exception;

	/**
	 * 
	 * @param query
	 * @param l 
	 * @param questionId 
	 * @return 
	 * @throws Exception
	 */
	public int deleteQuestionBySQLQuery(String query, long questionId, long secId) throws Exception;

	/**
	 * @param questionGroupId
	 * @return
	 */
	public List<Question> getQuestionsByQuestionGroupId(long questionGroupId)throws Exception;

}
