/**
 * 
 */
package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.Answers;

/**
 * @author Prashant
 *
 */
public interface AnswerDao {

	/**
	 * @param answer
	 * @return
	 */
	public long addAnswer(Answers answer) throws Exception;

	/**
	 * 
	 * @param questionId
	 * @return  List<Answers>
	 * @throws Exception
	 */
	public List<Answers> getAllAnswersByQuestionId(long questionId)throws Exception;

	/**
	 * @param answers2
	 */
	public void deleteAnswer(Answers answers2)throws Exception;

	/**
	 * @param ansId
	 * @return
	 */
	public Answers getAnswerById(long ansId)throws Exception;

}
