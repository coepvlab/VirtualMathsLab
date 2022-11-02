/**
 * 
 */
package in.ac.coep.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.QuestionGroupDao;
import in.ac.coep.dao.TestInstanceCompletionDao;
import in.ac.coep.entity.Answers;
import in.ac.coep.entity.Question;
import in.ac.coep.entity.QuestionGroup;
import in.ac.coep.entity.TestInstanceCompletion;
import in.ac.coep.entity.Topic;
import in.ac.coep.service.TestInstanceCompletionService;

/**
 * @author Prashant
 *
 */

@Service
public class TestInstanceCompletionServiceImpl implements TestInstanceCompletionService {

	@Autowired
	private TestInstanceCompletionDao testInstanceCompletionDao;
	
//	@Autowired
//	private AnswerDao answerDao;
	
	@Autowired
	private QuestionGroupDao questionGroupDao;
	
	
	@Override
	public JSONObject getTestQuestionPaperRecordByUserIdAndTISID(long tisId, long userId) throws Exception {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		
		List<TestInstanceCompletion> testInstanceCompletions = testInstanceCompletionDao.fetchRecordsGroupByUserIdAndTISID(userId, tisId);
		
		if (!testInstanceCompletions.isEmpty()) {
			
			JSONArray questionGroupArr = new JSONArray();
			List<QuestionGroup> questionGroups = new ArrayList<QuestionGroup>();
			
//			JSONObject givenAnsIdData = new JSONObject();
//			JSONObject actualAnsIdData = new JSONObject();
			JSONObject testICdData = new JSONObject();
			JSONObject ansData = new JSONObject();
			
			for (TestInstanceCompletion testInstanceCompletion : testInstanceCompletions) {
				
				QuestionGroup questionGroup = questionGroupDao.getQuestionGroupById(testInstanceCompletion.getQuestionGroup().getQuestionGroupId());
				questionGroups.add(questionGroup);
//				givenAnsIdData.put(testInstanceCompletion.getQuestionGroup().getQuestionGroupId()+"", testInstanceCompletion.getActualGivenOptionsAnsId());
//				actualAnsIdData.put(testInstanceCompletion.getQuestionGroup().getQuestionGroupId()+"", testInstanceCompletion.getAnswers().getAnswersId());
				testICdData.put(testInstanceCompletion.getQuestionGroup().getQuestionGroupId()+"", testInstanceCompletion.getTestInstanceCompletionId());
				ansData.put(testInstanceCompletion.getQuestion().getQuestionId()+"", testInstanceCompletion.isResult());
			}
			
			System.out.println(questionGroups);
			int totalquestionsTime = 0;
//			for (TestInstanceCompletion testInstanceCompletion : testInstanceCompletions) {
//				questionGroups = getRandomList(questionGroups, testConfiguration.getNoOfQuestionGroup());
			
				for (QuestionGroup questionGroup : questionGroups) {

					JSONObject questionGroupObj = new JSONObject();

					questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
					questionGroupObj.put("NME", questionGroup.getName());
					questionGroupObj.put("QGTYP", questionGroup.getType());
					
					questionGroupObj.put("varNo", questionGroup.getVarNo());
					
					questionGroupObj.put("QGLVL", questionGroup.getComplexityLevel().getQgComplexityLevelId());
					if(questionGroup.getQuesGroupMediaLinks().getQuesUsage().equals("question") ) {
						questionGroupObj.put("QGMEDIA", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
					}
					
					
					JSONArray topicArr = new JSONArray();
					Set<Topic> topics = questionGroup.getTopicSet();

					for (Topic topic : topics) {
						JSONObject topicData = new JSONObject();
						topicData.put("TID", topic.getTopicId());
						topicData.put("TNO", topic.getTopicNo());
						topicData.put("TNM", topic.getTopicName());
						topicData.put("TNM1", topic.getTopicName1());
						topicArr.put(topicData);
					}

					
					questionGroupObj.put("QGTOPICS", topicArr);
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy") ;
		            String creationDate =dateFormat.format(questionGroup.getCreationTime());
		            questionGroupObj.put("DATE", creationDate);

					JSONArray questionArr = new JSONArray();

					Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
					
					for (Question question : myOrderedSet) {

						JSONObject questionObj = new JSONObject();

						questionGroupObj.put("RESULT", ansData.get(question.getQuestionId() +""));
						questionObj.put("QID", question.getQuestionId());
						questionObj.put("QTXT", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
						questionObj.put("QTYP", question.getAnswerType().getName());
						questionObj.put("QTYPID", question.getAnswerType().getAnswerTypeId());
						questionObj.put("QGID", question.getQuestionGroup().getQuestionGroupId());
						questionObj.put("QTIM", question.getTime());
						questionObj.put("QSOLTYPE", question.getSolType());
						questionObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
						questionObj.put("QSOLMEDIA", question.getSolMedia());
						
//						questionObj.put("GIVENANSID", givenAnsIdData.get(questionGroup.getQuestionGroupId()+""));
//						questionObj.put("ACTUALANSID", actualAnsIdData.get(questionGroup.getQuestionGroupId()+""));
						questionObj.put("TICID", testICdData.get(questionGroup.getQuestionGroupId()+""));
						
						
						JSONArray ans = new JSONArray();
						for (Answers answer : question.getAnswers()) {

							JSONObject ansObj = new JSONObject();
							ansObj.put("ansId", answer.getAnswersId());
							ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
							ansObj.put("rightAnswer", answer.isRightAnswer());
							ansObj.put("ansMedia", answer.getMedia());
							ans.put(ansObj);
						}
						questionObj.put("answers", ans);
						questionArr.put(questionObj);
						totalquestionsTime += question.getTime();
					}
					
						questionGroupObj.put("QUESTION", questionArr);
						questionGroupArr.put(questionGroupObj);
					}
				
			data.put("TOTALTIME", totalquestionsTime);
			System.out.println(questionGroupArr);
			data.put("data", questionGroupArr);
		}
		
		
		return data;
	}

}
