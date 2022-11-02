/**
 * 
 */
package in.ac.coep.service;

import java.util.Set;

import org.json.JSONObject;

import in.ac.coep.entity.Question;
import in.ac.coep.vo.AnswersVO;

/**
 * @author Prashant
 *
 */
public interface AnswerService {

	/**
	 * @param answers
	 * @param question2
	 * @return 
	 */
	public JSONObject addAnswers(Set<AnswersVO> answers, Question question2)throws Exception;

	/**
	 * @param questionId
	 * @return 
	 */
	public JSONObject deleteAllAnswersByQuestionId(long questionId)throws Exception;
	
	
}
