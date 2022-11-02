/**
 * 
 */
package in.ac.coep.service;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import in.ac.coep.entity.QuestionGroup;
import in.ac.coep.vo.QuestionGroupVO;
import in.ac.coep.vo.QuestionVO;

/**
 * @author Prashant
 *
 */
public interface QuestionService {

	/**
	 * @param questionGroupVO
	 * @param questionGroup 
	 * 
	 * @return {JSONObject} 
	 * @throws Exception 
	 */
	public JSONObject addQuestion(QuestionGroupVO questionGroupVO, QuestionGroup questionGroup) throws Exception;

	/**
	 * @param questionVO
	 * 
	 * @return {JSONObject} 
	 * @throws Exception 
	 */
	public JSONObject modifyQuestion(QuestionVO questionVO)throws Exception;

	/**
	 * @param status 
	 * @return
	 */
	public JSONObject fetchQuestions(String status)throws Exception;

	/**
	 * @param file
	 * @return
	 */
	public JSONObject uploadQuestionExcel(MultipartFile file)throws Exception;

	/**
	 * @param questionId
	 * @return
	 */
	public JSONObject fetchQuestionsByQuestionId(long questionId)throws Exception;

	/**
	 * @param questionId
	 * @return
	 */
	public JSONObject deleteQuestion(long questionId)throws Exception;

	/**
	 * @param questionGroupId
	 */
	public void deleteAllQuestionByQuestionGroupId(long questionGroupId)throws Exception;

}
