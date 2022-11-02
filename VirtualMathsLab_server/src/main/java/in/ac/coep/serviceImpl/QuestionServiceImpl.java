/**
 * 
 */
package in.ac.coep.serviceImpl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.ac.coep.constants.Constants;
import in.ac.coep.dao.QuestionDao;
import in.ac.coep.entity.AnswerType;
import in.ac.coep.entity.Question;
import in.ac.coep.entity.QuestionGroup;
import in.ac.coep.service.AnswerService;
import in.ac.coep.service.QuestionService;
import in.ac.coep.vo.QuestionGroupVO;
import in.ac.coep.vo.QuestionVO;

/**
 * @author Prashant
 *
 */
@Service
public class QuestionServiceImpl implements QuestionService {

	private static final Logger LOGGER = Logger.getLogger(QuestionServiceImpl.class);

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private AnswerService answerService;

	@Override
	public JSONObject addQuestion(QuestionGroupVO questionGroupVO, QuestionGroup questionGroup) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		if (questionGroupVO.getQuestions().size() != 0) {

			if (questionGroupVO.getQuestions().size() < 1) {

				Question question = new Question();

				question.setQuestionGroup(questionGroup);

				AnswerType answerType = new AnswerType();
				answerType.setAnswerTypeId(questionGroupVO.getQuestion().getAnswerType().getAnswerTypeId());
				question.setAnswerType(answerType);

				question.setContent(questionGroupVO.getQuestion().getContent());
				question.setTime(questionGroupVO.getQuestion().getTime());
				
				question.setSolType(questionGroupVO.getQuestion().getSolType());
				
				question.setSolText(questionGroupVO.getQuestion().getSolText());
				question.setSolMedia(questionGroupVO.getQuestion().getSolMedia());

				// save question
				LOGGER.info("save Question");
				long questionId = questionDao.addQuestion(question);
				LOGGER.info("save Question End");
				Question question2 = questionDao.getQuestionByQuestionId(questionId);

				answerService.addAnswers(questionGroupVO.getQuestion().getAnswers(), question2);

				data.put("done", true);
				data.put("msg", Constants.quesGrpMsg);
//				data.put("QGID", questionGroup.getQuestionGroupId());

			} else {
				
				Set<QuestionVO> myOrderedSet = new LinkedHashSet<QuestionVO>(questionGroupVO.getQuestions());
				
				for (QuestionVO questionVO : myOrderedSet) {

					Question question = new Question();

					question.setQuestionGroup(questionGroup);

					AnswerType answerType = new AnswerType();
					answerType.setAnswerTypeId(questionVO.getAnswerType().getAnswerTypeId());
					question.setAnswerType(answerType);
					
					question.setSolType(questionVO.getSolType());
					question.setSolText(questionVO.getSolText());
					question.setSolMedia(questionVO.getSolMedia());

					question.setContent(questionVO.getContent());
					question.setTime(questionVO.getTime());

					// save
					long questionId = questionDao.addQuestion(question);
					LOGGER.info("save Question");
					Question question2 = questionDao.getQuestionByQuestionId(questionId);
					LOGGER.info("save Question End");
					data = answerService.addAnswers(questionVO.getAnswers(), question2);

				}
				data.put("done", true);
				data.put("msg", Constants.quesGrpMsg);
//				data.put("QGID", questionGroup.getQuestionGroupId());
			}
		} else {
			data.put("done", false);
			data.put("msg", Constants.quesGrpMsgFail);
		}
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.service.QuestionService#deleteAllQuestionByQuestionGroupId(
	 * long)
	 */
	@Override
	public void deleteAllQuestionByQuestionGroupId(long questionGroupId) throws ConstraintViolationException,Exception {
		// TODO Auto-generated method stub


		List<Question> questions = questionDao.getQuestionsByQuestionGroupId(questionGroupId);

		for (Question question : questions) {

//			data = answerService.deleteAllAnswersByQuestionId(question.getQuestionId());

		//	if (data.getBoolean("done")) {//
				questionDao.deleteQuestion(question);
		//	}
		}

	}

	@Override
	public JSONObject modifyQuestion(QuestionVO questionVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject fetchQuestions(String status) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject uploadQuestionExcel(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject fetchQuestionsByQuestionId(long questionId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject deleteQuestion(long questionId) throws Exception {
		
		JSONObject data = new JSONObject();
		
		// TODO Auto-generated method stub
		Question question = questionDao.getQuestionByQuestionId(questionId);
		
		questionDao.deleteQuestion(question);
		
		data.put("done", true);
		data.put("msg", "Question Deleted.");
		return null;
	}

}