/**
 * 
 */
package in.ac.coep.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.constants.Constants;
import in.ac.coep.dao.AnswerDao;
import in.ac.coep.dao.QuestionDao;
import in.ac.coep.dao.QuestionGroupDao;
import in.ac.coep.dao.TestDao;
import in.ac.coep.dao.TestInstanceCompletionDao;
import in.ac.coep.dao.TestInstanceDao;
import in.ac.coep.dao.TestInstanceStateDao;
import in.ac.coep.dao.TestStatisticDao;
import in.ac.coep.entity.Answers;
import in.ac.coep.entity.Question;
import in.ac.coep.entity.QuestionGroup;
import in.ac.coep.entity.Test;
import in.ac.coep.entity.TestInstance;
import in.ac.coep.entity.TestInstanceCompletion;
import in.ac.coep.entity.TestInstanceState;
import in.ac.coep.entity.TestInstanceStatistics;
import in.ac.coep.entity.Topic;
import in.ac.coep.service.BatchJobService;
import in.ac.coep.service.TopicService;

/**
 * @author Prashant
 *
 */

@Service
public class BatchJobServiceImpl implements BatchJobService {

	private static final Logger LOGGER = Logger.getLogger(BatchJobServiceImpl.class);

	@Autowired
	private TestInstanceDao testInstanceDao;

	@Autowired
	private TestInstanceCompletionDao testInstanceCompletionDao;
	
	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	private QuestionGroupDao questionGroupDao;

	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private TestStatisticDao testStatisticDao;

	@Autowired
	private TestDao testDao;

	@Autowired
	private TestInstanceStateDao testInstanceStateDao;
	
	@Autowired
	private TopicService topicService;

	
	static int wrngNtDummy = 0;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.service.BatchJobService#
	 * moveDataFromTestInstanceToTestInstanceCompletion(in.ac.coep.entity.
	 * TestInstanceState)
	 */
	@Override
	public JSONObject moveDataFromTestInstanceToTestInstanceCompletion(TestInstanceState testInstanceState)
			throws Exception {
		// TODO Auto-generated method stub

		JSONObject jsonObject = new JSONObject();
		
		List<TestInstance> instances = null;
		
		List<TestInstanceCompletion> testInstanceCompletions = testInstanceCompletionDao.getTestInstanceByTISTdfromCompletion(testInstanceState.getTestInstanceStateId(), testInstanceState.getUser().getUserId());
		
		JSONArray userAnsArr = new JSONArray();
		
		try {
			
			if(testInstanceCompletions.isEmpty()){
				
//				instances =  testInstanceDao.getTestInstanceByTISId(testInstanceState.getTestInstanceStateId());
				instances =  testInstanceDao.getTestInstanceByTISIdAndUserId(testInstanceState.getTestInstanceStateId(), testInstanceState.getUser().getUserId());
				
				if (!instances.isEmpty()) {
					
					long prv = 0;
					float totalquestionsTime = 0;
					
					for (TestInstance testInstance : instances) {
						

						JSONObject userAnsObj = new JSONObject();
						
						TestInstanceCompletion completion = new TestInstanceCompletion();

						completion.setActualAnsEndTime(testInstance.getActualAnsEndTime());
						completion.setActualAnsStartTime(testInstance.getActualAnsStartTime());
						completion.setPlannedAnsEndTime(testInstance.getPlannedAnsEndTime());
						
						Question question = questionDao.getQuestionByQuestionId(testInstance.getQuestionId()); 
						completion.setQuestion(question);
						
						QuestionGroup questionGroup = questionGroupDao.getQuestionGroupById(testInstance.getQuestionGroupId());
						completion.setQuestionGroup(questionGroup);
						
							if (question.getAnswerType().getAnswerTypeId() == 5) {
							
							if (prv == question.getQuestionGroup().getQuestionGroupId() ) {
//								System.out.println("qgId - "+ question.getQuestionGroup().getQuestionGroupId());
							}else {
								boolean passed = chkIsPassedForMcaType(question,
										testInstance.getUser().getUserId(),
										testInstance.getTestInstanceState().getTestInstanceStateId(), testInstanceDao,
										answerDao);
								userAnsObj.put("USERANSQID",""+question.getQuestionId());
								
								if (wrngNtDummy == 4) {
									
									userAnsObj.put("USERANS", "unanswered");
									wrngNtDummy = 0;
									
								}else if (passed) {
									
									userAnsObj.put("USERANS", "Correct"); // this is to save user given answers option Id
									wrngNtDummy = 0;
								}else {
									userAnsObj.put("USERANS", "Incorrect");
									wrngNtDummy = 0;
								}								
								completion.setResult(passed);
							}
								
							}else if (question.getAnswerType().getAnswerTypeId() == 8) {
								
								if (prv == question.getQuestionGroup().getQuestionGroupId() ) {
//									System.out.println("qgId - "+ question.getQuestionGroup().getQuestionGroupId());
								}else {
									boolean passed = chkIsPassedForMcaType(question,
											testInstance.getUser().getUserId(),
											testInstance.getTestInstanceState().getTestInstanceStateId(), testInstanceDao,
											answerDao);
									userAnsObj.put("USERANSQID",""+question.getQuestionId());
									
									if (wrngNtDummy == 4) {
										userAnsObj.put("USERANS", "unanswered");
										wrngNtDummy = 0;
									}else if (passed) {
										
										userAnsObj.put("USERANS", "Correct");
										wrngNtDummy = 0;
									}else {
										userAnsObj.put("USERANS", "Incorrect");
										wrngNtDummy = 0;
									}								
									completion.setResult(passed);
								}
								
								
							} else if(!(question.getAnswerType().getAnswerTypeId() == 5) && !(question.getAnswerType().getAnswerTypeId() == 8)){
								
								Answers answers = answerDao.getAnswerById(testInstance.getAnswersId());
								if (answers != null) {
									if (answers.isRightAnswer()) {
										userAnsObj.put("USERANSQID", ""+question.getQuestionId());
										userAnsObj.put("USERANS", "Correct");
										completion.setResult(true);
									} else {
										userAnsObj.put("USERANSQID", ""+question.getQuestionId());
										userAnsObj.put("USERANS", "Incorrect");
										completion.setResult(false);
									}
								}else {
									
									userAnsObj.put("USERANSQID", ""+question.getQuestionId());
									userAnsObj.put("USERANS", "unanswered");
									completion.setResult(false);
								}
								
							}
							
						completion.setActualGivenOptionsAnsId(testInstance.getActualGivenOptionsAnsId());

						Answers answers = answerDao.getAnswerById(testInstance.getAnswersId());
						
						completion.setAnswers(answers);
						
						completion.setTopic(testInstance.getTopic());
						completion.setTagged(testInstance.isTagged());
						completion.setUser(testInstance.getUser());
						completion.setTestInstanceState(testInstance.getTestInstanceState());
						
					
						totalquestionsTime += question.getTime();
						
						testInstanceCompletionDao.save(completion);
						
						if (!(userAnsObj.length() == 0)) {
							userAnsArr.put(userAnsObj);
						}
						
						prv  = question.getQuestionGroup().getQuestionGroupId();
					}

					String[] CT = testInstanceState.getCurrentTestTime().split("-");// currentTime for resume test
					
					float Rtime =  (Float.parseFloat(CT[0]));
					float GivenTime = (totalquestionsTime);
					
					float timeTakenToCompleteTest =  GivenTime - Rtime;
					
					long minute = TimeUnit.SECONDS.toMinutes((long) timeTakenToCompleteTest) - (TimeUnit.SECONDS.toHours((long) timeTakenToCompleteTest)* 60);
			        long second = TimeUnit.SECONDS.toSeconds((long) timeTakenToCompleteTest) - (TimeUnit.SECONDS.toMinutes((long) timeTakenToCompleteTest) * 60);
					
					String timeTakenInTimeFormat = minute + " Minute " + second + " Seconds ";
			          
					jsonObject = createStatisticsDataForReport(testInstanceState, GivenTime, timeTakenToCompleteTest, timeTakenInTimeFormat );
					
					int corrCnt = 0;
					int incorrCnt = 0;
					int unansCnt = 0;
					
				for (int i = 0, size = userAnsArr.length(); i < size; i++)
				    {
					      JSONObject objectInArray = userAnsArr.getJSONObject(i);

					      String[] elementNames = JSONObject.getNames(objectInArray);
					      
					      for (String elementName : elementNames)
					      {
					        String value = objectInArray.getString(elementName);
					        if (value.equals("Correct")) {
					        	corrCnt++;
							}else if (value.equals("Incorrect")) {
								incorrCnt++;
							}else if (value.equals("unanswered")) {
								unansCnt++;
							}
					      }
					    }
				
					JSONObject corrAnsData = new JSONObject();
					
					jsonObject.put("USERANSDATA", userAnsArr);
					corrAnsData.put("CORRANS", corrCnt);
					corrAnsData.put("INCORRANS", incorrCnt);
					corrAnsData.put("UNANS", unansCnt);
					jsonObject.put("ANSDATA", corrAnsData);
					
					if (jsonObject.getBoolean("done")) {
						
						jsonObject.put("msg", "All Records Moved Successfully..");
					}

				} else{
					LOGGER.info("instances are not present in db");
					jsonObject.put("done", false);
					jsonObject.put("msg", "No record found in testInstance");
				}
			}else{
				jsonObject.put("done", false);
				jsonObject.put("msg", "Records are already present in testInstaceCompletion for this user ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("done", false);
			jsonObject.put("msg", "Something went wrong.. Please try later ");
		}
		
		return jsonObject;

	} 

	/**
	 * @param question
	 * @param userId
	 * @param testInstanceStateId
	 * @return
	 */
	private static boolean chkIsPassedForMcaType(Question question, long userId, long testInstanceStateId,
			TestInstanceDao testInstanceDao, AnswerDao answerDao) throws Exception {
		// TODO Auto-generated method stub
		List<TestInstance> instances = testInstanceDao.getTestInstanceByQidUsrIdTSid(question.getQuestionId(), userId,
				testInstanceStateId);

		int rtAnsCount = 0;
		int wrngAnsCount = 0;

		int givenRight = 0;
		int givenWrong = 0;
//		int wrngNtDummy = 0;

		for (TestInstance testInstance : instances) {
			
			Answers answers = answerDao.getAnswerById(testInstance.getActualGivenOptionsAnsId());

			if (answers.isRightAnswer()) {
				rtAnsCount++;
			} else {
				wrngAnsCount++;
			}

		}
		for (TestInstance testInstance1 : instances) {

			Answers answers = answerDao.getAnswerById(testInstance1.getAnswersId());
			if (answers != null) {
				if (answers.isRightAnswer()) {
					givenRight++;
				} else  if (answers.getAnswersId() == Constants.answer_Dummy_Record_Id){
					wrngNtDummy++;
				} else if (answers.getAnswersId() != Constants.answer_Dummy_Record_Id){
					givenWrong++;
				}
			}else {
				wrngNtDummy++;
			}
			

		}

////		if (rtAnsCount == givenRight && wrngAnsCount == givenWrong && wrngNtDummy == 0) { //old logic
//			return true;
//		} else {
//			return false;
//		}

		
		if (rtAnsCount == givenRight && wrngAnsCount == wrngNtDummy && givenWrong == 0 ) { //new logic
//			if (rtAnsCount == givenRight && wrngAnsCount == givenWrong && wrngNtDummy == 0) {
				return true;
			} else {
				return false;
			}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.service.TestInstanceStateService#createStatisticsDataForReport
	 * ()
	 */
	@SuppressWarnings("unused")
	public JSONObject createStatisticsDataForReport(TestInstanceState testInstanceState, float timeGivenToCompleteTest, float timeTakenToCompleteTest, String timeTakenInTimeFormat) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		Test test = testDao.getTestByTestId(testInstanceState.getTest().getTestId());
		
//		TestInstanceState testInstanceStateToSetLevelCompleteFlag = new TestInstanceState();
		
		boolean flag = false;
		int compLevelOnePassCntVarType1 = 0;
		int compLevelOneFailCntVarType1  = 0;
		
		int compLevelOnePassCntVarType2 = 0;
		int compLevelOneFailCntVarType2 = 0;
		
		int compLevelTwoPassCntVarType1 = 0;
		int compLevelTwoFailCntVarType1 = 0;
		
		int compLevelTwoPassCntVarType2 = 0;
		int compLevelTwoFailCntVarType2 = 0;
		
		int compLevelThreePassCntVarType1 = 0;
		int compLevelThreeFailCntVarType1 = 0;
		
		int compLevelThreePassCntVarType2 = 0;
		int compLevelThreeFailCntVarType2 = 0;
		
		int compLevelFourPassCntVarType1 = 0;
		int compLevelFourFailCntVarType1 = 0;
		
		int compLevelFourPassCntVarType2 = 0;
		int compLevelFourFailCntVarType2 = 0;
		
		int compLevelFivePassCntVarType1 = 0;
		int compLevelFiveFailCntVarType1 = 0;
		
		int compLevelFivePassCntVarType2 = 0;
		int compLevelFiveFailCntVarType2 = 0;
		
		int compLevelFinalPassCntVarType = 0;
		int compLevelFinalFailCntVarType = 0;
		

		String topics[] = test.getSelectedTopics().split(",");
		String resultState = "";
		
		
		for (int i = 0; i < topics.length; i++) {


				long totalCount = testInstanceDao.getCountOfTestInstanceByTISidAndSecId(
						testInstanceState.getTestInstanceStateId(), topics[i], false);

				long passedCount = testInstanceDao.getCountOfTestInstanceByTISidAndSecId(
						testInstanceState.getTestInstanceStateId(), topics[i], true);

				TestInstanceStatistics instanceStatistics = new TestInstanceStatistics();
				Topic topic = new Topic();
				topic.setTopicId(Integer.parseInt(topics[i]));

				instanceStatistics.setTopic(topic);
				instanceStatistics.setTestInstanceState(testInstanceState);
				
				instanceStatistics.setUser(testInstanceState.getUser());
				instanceStatistics.setTotalNoOfQuestions((int) totalCount);
				instanceStatistics.setPassedQuestions((int) (passedCount));
				
				
				instanceStatistics.setTimeGivenToCompleteTest(timeGivenToCompleteTest);
				instanceStatistics.setTimeTakenToCompleteTest(timeTakenToCompleteTest);

				float a = passedCount;
				float b = totalCount;
				
				boolean result = false;

				float percentage = (a / b) * 100; 

				instanceStatistics.setPercentage(percentage);
				
				testInstanceState.setPercentage(String.valueOf(percentage));

//				int passFailCnt = testInstanceState.getPassFailCnt();
				
				int levelNo = 0;

				if (testInstanceState.getComplexityLevel() == 1) {
					if (percentage > Constants.percentageCriteriaForPractiseTestCompLevel1) {
						instanceStatistics.setGrade("A");
						testInstanceState.setResult("P"); 
						if (test.getVarType().equals("1")) {
							
							levelNo = testInstanceState.getComplexityLevel();
//							resultState = Constants.passedFirstHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedFirstHalfDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.passedFirstHalfDiffLevelMsgArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
							
						}else if (test.getVarType().equals("2")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState =  Constants.passedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.passedFirstHalfDiffLevelMsgArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
						}
						
						result = true;
					} else {
						instanceStatistics.setGrade("B");
						testInstanceState.setResult("F");
						
						if (test.getVarType().equals("1")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState = Constants.failedFirstHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							resultState = Constants.failedFirstHalfDiffLevelMsgArray[random];
						}else if (test.getVarType().equals("2")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState =  Constants.failedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							resultState = Constants.failedSecondHalfDiffLevelMsgArray[random];
						}
						
						result = false;
					}
				}else if (testInstanceState.getComplexityLevel() == 2) {
					
					if (percentage > Constants.percentageCriteriaForPractiseTestCompLevel2) {
						instanceStatistics.setGrade("A");
						testInstanceState.setResult("P"); 
						if (test.getVarType().equals("1")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState = Constants.passedFirstHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.passedFirstHalfDiffLevelMsgArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
							
						}else if (test.getVarType().equals("2")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState =  Constants.passedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.passedFirstHalfDiffLevelMsgArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
						}
						result = true;
					} else {
						instanceStatistics.setGrade("B");
						testInstanceState.setResult("F");
						if (test.getVarType().equals("1")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState = Constants.failedFirstHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							resultState = Constants.failedFirstHalfDiffLevelMsgArray[random];
						}else if (test.getVarType().equals("2")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState =  Constants.failedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							resultState = Constants.failedSecondHalfDiffLevelMsgArray[random];
						}
						result = false;
					}
				}else if (testInstanceState.getComplexityLevel() == 3) {
					
					if (percentage > Constants.percentageCriteriaForPractiseTestCompLevel3) {
						instanceStatistics.setGrade("A");
						testInstanceState.setResult("P"); 
						
						if (test.getVarType().equals("1")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState = Constants.passedFirstHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedFirstHalfDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.passedFirstHalfDiffLevelMsgArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
							
						}else if (test.getVarType().equals("2")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState =  Constants.passedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.passedFirstHalfDiffLevelMsgArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
						}
						
						result = true;
					} else {
						instanceStatistics.setGrade("B");
						testInstanceState.setResult("F");
						if (test.getVarType().equals("1")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState = Constants.failedFirstHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							resultState = Constants.failedFirstHalfDiffLevelMsgArray[random];
							
						}else if (test.getVarType().equals("2")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState =  Constants.failedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							resultState = Constants.failedSecondHalfDiffLevelMsgArray[random];
						}
						result = false;
					}
				}else if (testInstanceState.getComplexityLevel() == 4) {
					
					if (percentage > Constants.percentageCriteriaForPractiseTestCompLevel4) {
						instanceStatistics.setGrade("A");
						testInstanceState.setResult("P");
						
						if (test.getVarType().equals("1")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState = Constants.passedFirstHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedFirstHalfDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.passedFirstHalfDiffLevelMsgArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
							
						}else if (test.getVarType().equals("2")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState =  Constants.passedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.passedFirstHalfDiffLevelMsgArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
						}
						
						result = true;
					} else {
						instanceStatistics.setGrade("B");
						testInstanceState.setResult("F");
						if (test.getVarType().equals("1")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState = Constants.failedFirstHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							resultState = Constants.failedFirstHalfDiffLevelMsgArray[random];
						}else if (test.getVarType().equals("2")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState =  Constants.failedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							resultState = Constants.failedSecondHalfDiffLevelMsgArray[random];
						}
						result = false;
					}
				}else if (testInstanceState.getComplexityLevel() == 5) {
					
					if (percentage > Constants.percentageCriteriaForPractiseTestCompLevel5) {
						instanceStatistics.setGrade("A");
						testInstanceState.setResult("P"); 
						
						if (test.getVarType().equals("1")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState = Constants.passedFirstHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedFirstHalfDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.passedFirstHalfDiffLevelMsgArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
							
						}else if (test.getVarType().equals("2")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState =  Constants.passedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.passedFirstHalfDiffLevelMsgArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
						}
						
						result = true;
					} else {
						instanceStatistics.setGrade("B");
						testInstanceState.setResult("F");
						if (test.getVarType().equals("1")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState = Constants.failedFirstHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							resultState = Constants.failedFirstHalfDiffLevelMsgArray[random];
						}else if (test.getVarType().equals("2")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState =  Constants.failedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							resultState = Constants.failedSecondHalfDiffLevelMsgArray[random];
						}
						result = false;
					}
				}else if (testInstanceState.getComplexityLevel() == 6) {
					
					if (percentage > Constants.percentageCriteriaForPractiseTestCompLevel5) {
						instanceStatistics.setGrade("A");
						testInstanceState.setResult("P");
						
						if (test.getVarType().equals("ALL")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState = Constants.passedFirstHalfOfFinalDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);
							if (testInstanceState.getPracticeTestNo() == 2) { // it was 3 before for last stage
								resultState = Constants.passedSecondHalfOfFinalDiffLevelMsgArray[random]; 
							}else {
								resultState = Constants.passedFirstHalfOfFinalDiffLevelMsgArray[random];
							}
							
						}else if (test.getVarType().equals("ALL")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState =  Constants.passedSecondHalfOfFinalDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedSecondHalfOfFinalDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.passedFirstHalfDiffLevelMsgArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
						}
						result = true;
					} else {
						instanceStatistics.setGrade("B");
						testInstanceState.setResult("F");
						if (test.getVarType().equals("ALL")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState = Constants.failedFirstHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							resultState = Constants.failedFirstHalfDiffLevelMsgArray[random];
						}else if (test.getVarType().equals("ALL")) {
							levelNo = testInstanceState.getComplexityLevel();
//							resultState =  Constants.failedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							resultState = Constants.failedSecondHalfDiffLevelMsgArray[random];
						}
						result = false;
					}
				}
				
				
//				testInstanceState.setTestLevel(testLevel);
				testInstanceStateDao.update(testInstanceState);

				testStatisticDao.save(instanceStatistics);
				
				
				// code to get failed test count start
				List<TestInstanceState> testInstanceStateToGetCount = testInstanceStateDao.getTestInstanceStateByUserIdAndTopicId(testInstanceState.getUser().getUserId(), test.getSelectedTopics(), test.getTestType().getTestTypeId()); // this is to check whether user has given any test on topic

				if (testInstanceStateToGetCount.isEmpty()) {
					
//					String varType = "1"; // since the testinstancestate is empty by default vartype will be 1
//					int complLevel = 1;
//					data = createTestPaperForPracticeTest(testVO, testInstanceState.getUser(), test, varType, complLevel);
					
//					return data;	
					
				}else {
					
					for (TestInstanceState testInstanceState2: testInstanceStateToGetCount) {
						
						if (testInstanceState2.getStatus().equals("N") || testInstanceState2.getStatus().equals("P")) {
							
							data.put("done", true);
							data.put("COMPLEVEL", testInstanceState2.getComplexityLevel()); 
							data.put("TID", testInstanceState2.getTest().getTestId());
							data.put("msg", Constants.testcreationsuccess);
							
							flag = true;
							
						}else if (testInstanceState2.getStatus().equalsIgnoreCase("C")) {
							
							if ( testInstanceState2.getComplexityLevel() == 1) {
								if (testInstanceState2.getTest().getVarType().equals("1") && testInstanceState2.getResult().equals("P") ) {
									compLevelOnePassCntVarType1++;
								}else if (testInstanceState2.getTest().getVarType().equals("1") && testInstanceState2.getResult().equals("F") ) {
									compLevelOneFailCntVarType1++;
								}
								if (testInstanceState2.getTest().getVarType().equals("2") && testInstanceState2.getResult().equals("P") ) {
									compLevelOnePassCntVarType2++;
								}else if (testInstanceState2.getTest().getVarType().equals("2") && testInstanceState2.getResult().equals("F") ) {
									compLevelOneFailCntVarType2++; 
								}
								
							}else if ( testInstanceState2.getComplexityLevel() == 2) {
								
								if (testInstanceState2.getTest().getVarType().equals("1") && testInstanceState2.getResult().equals("P") ) {
									compLevelTwoPassCntVarType1++;
								}else if (testInstanceState2.getTest().getVarType().equals("1") && testInstanceState2.getResult().equals("F") ) {
									compLevelTwoFailCntVarType1++;
								}
								if (testInstanceState2.getTest().getVarType().equals("2") && testInstanceState2.getResult().equals("P") ) {
									compLevelTwoPassCntVarType2++;
								}else if (testInstanceState2.getTest().getVarType().equals("2") && testInstanceState2.getResult().equals("F") ) {
									compLevelTwoFailCntVarType2++;
								}
							}else if ( testInstanceState2.getComplexityLevel() == 3) {
								
								if (testInstanceState2.getTest().getVarType().equals("1") && testInstanceState2.getResult().equals("P") ) {
									compLevelThreePassCntVarType1++;
								}else if (testInstanceState2.getTest().getVarType().equals("1") && testInstanceState2.getResult().equals("F") ) {
									compLevelThreeFailCntVarType1++;
								}
								if (testInstanceState2.getTest().getVarType().equals("2") && testInstanceState2.getResult().equals("P") ) {
									compLevelThreePassCntVarType2++;
								}else if (testInstanceState2.getTest().getVarType().equals("2") && testInstanceState2.getResult().equals("F") ) {
									compLevelThreeFailCntVarType2++;
								}
								
							}else if ( testInstanceState2.getComplexityLevel() == 4) {
								
								if (testInstanceState2.getTest().getVarType().equals("1") && testInstanceState2.getResult().equals("P") ) {
									compLevelFourPassCntVarType1++;
								}else if (testInstanceState2.getTest().getVarType().equals("1") && testInstanceState2.getResult().equals("F") ){
									compLevelFourFailCntVarType1++;
								}
								if (testInstanceState2.getTest().getVarType().equals("2") && testInstanceState2.getResult().equals("P") ) {
									compLevelFourPassCntVarType2++;
								}else if (testInstanceState2.getTest().getVarType().equals("2") && testInstanceState2.getResult().equals("F") ) {
									compLevelFourFailCntVarType2++;
								}
								
							}else if ( testInstanceState2.getComplexityLevel() == 5) {
								
								if (testInstanceState2.getTest().getVarType().equals("1") && testInstanceState2.getResult().equals("P") ) {
									compLevelFivePassCntVarType1++;
								} else if (testInstanceState2.getTest().getVarType().equals("1") && testInstanceState2.getResult().equals("F") ) {  
									compLevelFiveFailCntVarType1++;
								}
								if (testInstanceState2.getTest().getVarType().equals("2") && testInstanceState2.getResult().equals("P") ) {
									compLevelFivePassCntVarType2++;
								}else if (testInstanceState2.getTest().getVarType().equals("2") && testInstanceState2.getResult().equals("F") ) {
									compLevelFiveFailCntVarType2++;
								}
								
//								if (compLevelFivePassFailCntVarType2 >= 2) {
//									compLevelFinalPassFailCntVarType++;
//								}
								
							}else if (testInstanceState2.getComplexityLevel() == 6) {
								
								if (testInstanceState2.getTest().getVarType().equals("ALL") && testInstanceState2.getResult().equals("P") ) {
									compLevelFinalPassCntVarType++;
								}else if (testInstanceState2.getTest().getVarType().equals("ALL") && testInstanceState2.getResult().equals("F") ) {
									compLevelFinalFailCntVarType++;
								}
								
							}
						}
						
					}// for loop end
					
					
				}
				
				// code to get whether there is only one variation for the DoD start
				 boolean ifOnlyOneVariationAvailableInDOD = false;
				String[] selectedTopics = test.getSelectedTopics().split(",");
				for (int j = 0; j < selectedTopics.length; j++) {
					List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsByTopicId(Long.parseLong((selectedTopics[j])));
					
					Set<Integer> varNO_Set = new HashSet<Integer>();
					
					for (QuestionGroup questionGroup : questionGroups) {
						
						if (levelNo != 6) {
							if (questionGroup.getComplexityLevel().getQgComplexityLevelId() == levelNo) {
								if (questionGroup.getVarNo() != null) {
									varNO_Set.add(Integer.parseInt(questionGroup.getVarNo()));
								}
								
							}
						}else if (levelNo == 6)  {
							if (questionGroup.getComplexityLevel().getQgComplexityLevelId() == 5) {
								if (questionGroup.getVarNo() != null) {
									varNO_Set.add(Integer.parseInt(questionGroup.getVarNo()));
								}
								
							}
						}
						
					}
					
					System.out.println(varNO_Set);
					System.out.println("size -"+varNO_Set.size());
					
					
					List<Integer> varNoList = new ArrayList<>(varNO_Set);
			        Collections.sort(varNoList);
			        
			        System.out.println(varNoList);
			        System.out.println("size -"+varNoList.size());
			        
			        Object[] varNoArray =  varNoList.toArray();
			        
			        Object[] varNoSetArray1 = {};
			        Object[] varNoSetArray2 = {};
			        
					if (varNoList.size() == 1) {

						ifOnlyOneVariationAvailableInDOD = true;
						varNoSetArray1 = varNoArray;
						System.out.println("v1 " + varNoSetArray1);
						varNoSetArray2 = varNoArray;
						System.out.println("v2 " + varNoSetArray2);

					} else {

						ifOnlyOneVariationAvailableInDOD = false;
						varNoSetArray1 = Arrays.copyOfRange(varNoArray, 0, varNoArray.length / 2);
						System.out.println("v21 " + varNoSetArray1);
						varNoSetArray2 = Arrays.copyOfRange(varNoArray, varNoArray.length / 2, varNoArray.length);
						System.out.println("v22 " + varNoSetArray2);
					}
				}
				
				// code to get whether there is only one variation for the DoD end
		        
				if (levelNo == 1) {
					
					if (compLevelOneFailCntVarType1 >= 2 && compLevelOnePassCntVarType1 != 2) {
						
						JSONObject getPrerequisiteData = new JSONObject();
						getPrerequisiteData = topicService.getPrerequisiteTopicByTopicID(Long.valueOf(topics[i]));
						if (getPrerequisiteData.getBoolean("done")) {
							String temp = resultState;
							resultState =  Constants.prerequisiteMsg + getPrerequisiteData.getString("TOPICNM") + " </span></br>" +  temp;
						}
						
					}else if (compLevelOneFailCntVarType2 >= 2 && compLevelOnePassCntVarType2 != 2) {
						JSONObject getPrerequisiteData = new JSONObject();
						getPrerequisiteData = topicService.getPrerequisiteTopicByTopicID(Long.valueOf(topics[i]));
						if (getPrerequisiteData.getBoolean("done")) {
							String temp = resultState;
							resultState =  Constants.prerequisiteMsg +getPrerequisiteData.getString("TOPICNM") + " </span></br>" +  temp;
						}
					}else if (compLevelOnePassCntVarType1 == 2 && testInstanceState.getTest().getVarType().equals("1")) {
						
						if (ifOnlyOneVariationAvailableInDOD) {
//							resultState = Constants.passedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
						}else {
//							resultState = Constants.completedFirstHalfSameDiffLevel;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
						}
					 }else if (compLevelOnePassCntVarType2 == 2 && testInstanceState.getTest().getVarType().equals("2")) {
							
//							if (ifOnlyOneVariationAvailableInDOD) {
//								resultState = Constants.passedSecondHalfDiffLevelMsg;
						 	int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
						 	if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
//							}else {
//								resultState = Constants.completedFirstHalfSameDiffLevel;
//							}
						
					}
				}else if (levelNo == 2) {
					
					if (compLevelTwoFailCntVarType1 >= 2  && compLevelTwoPassCntVarType1 != 2) {
						
						JSONObject getPrerequisiteData = new JSONObject();
						getPrerequisiteData = topicService.getPrerequisiteTopicByTopicID(Long.valueOf(topics[i]));
						if (getPrerequisiteData.getBoolean("done")) {
							String temp = resultState;
							resultState =  Constants.prerequisiteMsg +getPrerequisiteData.getString("TOPICNM") + " </span></br>" +  temp;
						}
					}else if (compLevelTwoFailCntVarType2 >= 2  && compLevelTwoPassCntVarType2 != 2) {
						JSONObject getPrerequisiteData = new JSONObject();
						getPrerequisiteData = topicService.getPrerequisiteTopicByTopicID(Long.valueOf(topics[i]));
						if (getPrerequisiteData.getBoolean("done")) {
							String temp = resultState;
							resultState =  Constants.prerequisiteMsg +getPrerequisiteData.getString("TOPICNM") + " </span></br>" +  temp;
						}
					}else if (compLevelTwoPassCntVarType1 == 2 && testInstanceState.getTest().getVarType().equals("1")) {

						if (ifOnlyOneVariationAvailableInDOD) {
//							resultState = Constants.passedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
						} else {
//							resultState = Constants.completedFirstHalfSameDiffLevel;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
							
						}
					}else if (compLevelTwoPassCntVarType2 == 2 && testInstanceState.getTest().getVarType().equals("2")) {

//						if (ifOnlyOneVariationAvailableInDOD) {
//							resultState = Constants.passedSecondHalfDiffLevelMsg;
						int random = (int) (Math.random()*((4 - 1) +1)+1);   
//						resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
						if (testInstanceState.getPracticeTestNo() == 1) {
							resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
						}else {
							resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
						}
//						} else {
//							resultState = Constants.passedSecondHalfDiffLevelMsg;
//						}

					}
				}else if (levelNo == 3) {
					
					if (compLevelThreeFailCntVarType1 >= 2 && compLevelThreePassCntVarType1 != 2) {
						
						JSONObject getPrerequisiteData = new JSONObject();
						getPrerequisiteData = topicService.getPrerequisiteTopicByTopicID(Long.valueOf(topics[i]));
						if (getPrerequisiteData.getBoolean("done")) {
							String temp = resultState;
							resultState =  Constants.prerequisiteMsg +getPrerequisiteData.getString("TOPICNM") + " </span></br>" +  temp;
						}
					}else if (compLevelThreeFailCntVarType2 >= 2 && compLevelThreePassCntVarType2 != 2) {
						JSONObject getPrerequisiteData = new JSONObject();
						getPrerequisiteData = topicService.getPrerequisiteTopicByTopicID(Long.valueOf(topics[i]));
						if (getPrerequisiteData.getBoolean("done")) {
							String temp = resultState;
							resultState =  Constants.prerequisiteMsg +getPrerequisiteData.getString("TOPICNM") + " </span></br>" +  temp;
						}
					}else if (compLevelThreePassCntVarType1 == 2 && testInstanceState.getTest().getVarType().equals("1")) {

						if (ifOnlyOneVariationAvailableInDOD) {
//							resultState = Constants.passedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
						} else {
//							resultState = Constants.completedFirstHalfSameDiffLevel;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
						}
					}else if (compLevelThreePassCntVarType2 == 2 && testInstanceState.getTest().getVarType().equals("2")) {

//						if (ifOnlyOneVariationAvailableInDOD) {
//							resultState = Constants.passedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
//						} else {
//							resultState = Constants.completedFirstHalfSameDiffLevel;
//						}
					}
					
				}else if (levelNo == 4) {
					
					if (compLevelFourFailCntVarType1 >= 2 && compLevelFourPassCntVarType1 != 2) {
						
						JSONObject getPrerequisiteData = new JSONObject();
						getPrerequisiteData = topicService.getPrerequisiteTopicByTopicID(Long.valueOf(topics[i]));
						if (getPrerequisiteData.getBoolean("done")) {
							String temp = resultState;
							resultState =  Constants.prerequisiteMsg +getPrerequisiteData.getString("TOPICNM") + " </span></br>" +  temp;
						}
					}else if (compLevelFourFailCntVarType2 >= 2 && compLevelFourPassCntVarType2 != 2) {
						JSONObject getPrerequisiteData = new JSONObject();
						getPrerequisiteData = topicService.getPrerequisiteTopicByTopicID(Long.valueOf(topics[i]));
						if (getPrerequisiteData.getBoolean("done")) {
							String temp = resultState;
							resultState =  Constants.prerequisiteMsg +getPrerequisiteData.getString("TOPICNM") + " </span></br>" +  temp;
						}
					}else if (compLevelFourPassCntVarType1 == 2 && testInstanceState.getTest().getVarType().equals("1")) {

						if (ifOnlyOneVariationAvailableInDOD) {
//							resultState = Constants.passedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
						} else {
//							resultState = Constants.completedFirstHalfSameDiffLevel;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
						}
					}else if (compLevelFourPassCntVarType2 == 2 && testInstanceState.getTest().getVarType().equals("2")) {

//						if (ifOnlyOneVariationAvailableInDOD) {
//							resultState = Constants.passedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
//							resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							if (testInstanceState.getPracticeTestNo() == 1) {
								resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
							}else {
								resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
							}
//						} else {
//							resultState = Constants.completedFirstHalfSameDiffLevel;
//						}
					}
					
				}else if (levelNo == 5) {
					
					if (compLevelFiveFailCntVarType1 >= 2  && compLevelFivePassCntVarType1 != 2) {
						
						JSONObject getPrerequisiteData = new JSONObject();
						getPrerequisiteData = topicService.getPrerequisiteTopicByTopicID(Long.valueOf(topics[i]));
						if (getPrerequisiteData.getBoolean("done")) {
							String temp = resultState;
							resultState =  Constants.prerequisiteMsg +getPrerequisiteData.getString("TOPICNM") + " </span></br>" +  temp;
						}
					}else if (compLevelFiveFailCntVarType2 >= 2   && compLevelFivePassCntVarType2 != 2) {
						JSONObject getPrerequisiteData = new JSONObject();
						getPrerequisiteData = topicService.getPrerequisiteTopicByTopicID(Long.valueOf(topics[i]));
						if (getPrerequisiteData.getBoolean("done")) {
							String temp = resultState;
							resultState =  Constants.prerequisiteMsg +getPrerequisiteData.getString("TOPICNM") + " </span></br>" +  temp;
						}
					}else if (compLevelFivePassCntVarType1 == 2 && testInstanceState.getTest().getVarType().equals("1")) {

						if (ifOnlyOneVariationAvailableInDOD) {
//							resultState = Constants.passedSecondHalfDiffLevelMsg;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
						} else {
//							resultState = Constants.completedFirstHalfSameDiffLevel;
							int random = (int) (Math.random()*((4 - 1) +1)+1);   
							resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
						}
					}else if (compLevelFivePassCntVarType2 == 2 && testInstanceState.getTest().getVarType().equals("2")) {

//						if (ifOnlyOneVariationAvailableInDOD) {
//							resultState = Constants.passedSecondHalfDiffLevelMsg;
						int random = (int) (Math.random()*((4 - 1) +1)+1);   
//						resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
						if (testInstanceState.getPracticeTestNo() == 1) {
							resultState = Constants.completedFirstHalfSameDiffLevelArray[random];
						}else {
							resultState = Constants.passedSecondHalfDiffLevelMsgArray[random];
						}
//						} else {
//							resultState = Constants.completedFirstHalfSameDiffLevel;
//						}
					}
					
				}
				
//				if (compLevelOneFailCntVarType1 >= 2) {
//					
//					JSONObject getPrerequisiteData = new JSONObject();
//					getPrerequisiteData = topicService.getPrerequisiteTopicByTopicID(Long.valueOf(topics[i]));
//					if (getPrerequisiteData.getBoolean("done")) {
//						String temp = resultState;
//						resultState =  Constants.prerequisiteMsg +getPrerequisiteData.getString("TOPICNM") + " </span></br>" +  temp;
//					}
//					
////					resultState =
//				}else {
//					resultState = Constants.failedFirstHalfDiffLevelMsg;
//				}
				// // code to get failed test count end 
				
				
				

				data.put("PERC", percentage);
				data.put("RESULT", result);
				
				data.put("PassedFailedMsg", resultState);
				
				data.put("GivenTIME", (timeGivenToCompleteTest / 60 ));
				data.put("TakenTIME", timeTakenInTimeFormat);
				
			data.put("done", true);
		}
		return data;

		// }

	}


}
