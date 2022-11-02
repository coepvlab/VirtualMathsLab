/**
 * 
 */
package in.ac.coep.serviceImpl;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.AnswerDao;
import in.ac.coep.entity.Answers;
import in.ac.coep.entity.Question;
import in.ac.coep.service.AnswerService;
import in.ac.coep.vo.AnswersVO;

/**
 * @author Prashant
 *
 */
@Service
public class AnswerServiceImpl implements AnswerService {

	private static final Logger LOGGER = Logger.getLogger(AnswerServiceImpl.class);

	@Autowired
	private AnswerDao answerDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.service.AnswerService#addAnswers(java.util.Set,
	 * in.ac.coep.entity.Question)
	 */
	@Override
	public JSONObject addAnswers(Set<AnswersVO> answers, Question question2) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		if (answers.size() != 0) {
			for (AnswersVO answersVO : answers) {
				LOGGER.info("Add Answers...Start");
				Answers answer = new Answers();
				answer.setQuestion(question2);
				
//				answer.setContent(answersVO.getContent());
				
				String dataType = getDataType(answersVO.getContent());
				if(dataType == "int") {
					answer.setContent(answersVO.getContent());
				}else if (dataType == "str") {
					answer.setContent(answersVO.getContent());
				}else if (dataType == "double") {
					double temp = Double.parseDouble(answersVO.getContent());
					if (temp == Math.round(temp)) {
						String ans = String.valueOf(Math.round(temp));
						answer.setContent(ans);
					}else {
						answer.setContent(answersVO.getContent());
					}
				}
					
				
				answer.setRightAnswer(answersVO.isRightAnswer());
				answer.setMedia(answersVO.getMedia());
				answerDao.addAnswer(answer);
				LOGGER.info("Add Answers...End");
				data.put("done", true);
			}
		} else {
			LOGGER.error("Add Answers...Failed");
			data.put("done", false);
		}
		return data;
	}

	
	
	
	public static String getDataType(Object obj) {
		String answer = "";
		try {
			int temp = Integer.parseInt((String) obj);

			if (temp == Integer.parseInt((String) obj)) {
				answer = "int";
			}
		} catch (NumberFormatException e) {
			try {
				Float temp = Float.parseFloat((String) obj);

				if (temp == Float.parseFloat((String) obj)) {
					answer = "double";
				}

			} catch (NumberFormatException ee) {
				answer = "str";
			}

		}

		return answer;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.service.AnswerService#deleteAllAnswersByQuestionId(long)
	 */
	@Override
	public JSONObject deleteAllAnswersByQuestionId(long questionId) throws Exception {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();

		if (questionId != 0) {
			LOGGER.info("fetch Answers...Start");
			
			List<Answers> answers = answerDao.getAllAnswersByQuestionId(questionId);
			
			for (Answers answers2 : answers) {
				answerDao.deleteAnswer(answers2);
			}

			LOGGER.info("fetch Answers...End");
			data.put("done", true);

		} else {
			LOGGER.error("fetch Answers...Failed");
			data.put("done", false);
		}
		return data;

	}

}
