package in.ac.coep.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.constants.Constants;
import in.ac.coep.dao.QuestionDao;
import in.ac.coep.dao.QuestionGroupDao;
import in.ac.coep.dao.TestConfigurationDao;
import in.ac.coep.dao.TestDao;
import in.ac.coep.dao.TestInstanceDao;
import in.ac.coep.dao.TestInstanceStateDao;
import in.ac.coep.dao.TopicDao;
import in.ac.coep.dao.TopicMappingDao;
import in.ac.coep.dao.UserDao;
import in.ac.coep.entity.Answers;
import in.ac.coep.entity.QGComplexityLevel;
import in.ac.coep.entity.Question;
import in.ac.coep.entity.QuestionGroup;
import in.ac.coep.entity.Standard;
import in.ac.coep.entity.Test;
import in.ac.coep.entity.TestConfiguration;
import in.ac.coep.entity.TestInstance;
import in.ac.coep.entity.TestInstanceState;
import in.ac.coep.entity.Topic;
import in.ac.coep.entity.TopicMapping;
import in.ac.coep.entity.User;
import in.ac.coep.service.TestConfigurationService;
import in.ac.coep.utility.EncoderDecoder;
import in.ac.coep.vo.TestConfigurationVO;
import in.ac.coep.vo.TestVO;

/**
 * @author Prashant
 *
 */
@Service
public class TestConfigurationServiceImpl implements TestConfigurationService {

	private static final Logger LOGGER = Logger.getLogger(TestConfigurationServiceImpl.class);

	@Autowired
	private TestConfigurationDao testConfigurationDao;

	@Autowired
	private TestDao testDao;

	@Autowired
	private QuestionGroupDao questionGroupDao;

//	@Autowired
//	private QuestionDao questionDao;

	@Autowired
	private TestInstanceDao testInstanceDao;
	
	@Autowired
	private TopicDao topicDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private TestInstanceStateDao testStateDao;
	
	@Autowired
	private TopicMappingDao topicMappingDao;
	
	@Autowired
	private QuestionDao questionDao;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.service.TestConfigurationService#configureTest(in.ac.coep.vo.
	 * TestConfigurationVO[])
	 */
	@Override
	public JSONObject setTestPaper(TestConfigurationVO[] testConfigurationVOs) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();
		
		if (testConfigurationVOs != null) {
			
		Test test = testDao.getTestByTestId(testConfigurationVOs[0].getTestVO().getTestId());
		if (test.getTestType().getTestTypeId() == 5) {

			for (TestConfigurationVO testConfigurationVO : testConfigurationVOs) {

				TestConfiguration testConfiguration = new TestConfiguration();

				testConfiguration = copyFromTestConfigurationVOToTestConfigurationForOtherTest(testConfiguration,
						testConfigurationVO, test);

				long testConfigurationId = testConfigurationDao.configureTest(testConfiguration);

				data.put("TID", testConfiguration.getTopic().getTopicId());
				data.put("LVL" + testConfiguration.getComplexityLevel().getQgComplexityLevelId(), testConfigurationId);

			}
			data.put("done", true);
			data.put("msg", Constants.testConfigurationsuccess);
//			return data;
			
		}else if (test.getTestType().getTestTypeId() == 1) {
			
				for (TestConfigurationVO testConfigurationVO : testConfigurationVOs) {

				TestConfiguration testConfiguration = new TestConfiguration();

				testConfiguration = copyFromTestConfigurationVOToTestConfiguration(testConfiguration,
						testConfigurationVO);

				long testConfigurationId = testConfigurationDao.configureTest(testConfiguration);

				data.put("TID", testConfiguration.getTopic().getTopicId());
				data.put("LVL" + testConfiguration.getComplexityLevel().getQgComplexityLevelId(), testConfigurationId);

			}
			data.put("done", true);
			data.put("msg", Constants.testConfigurationsuccess);
//			return data;
		}
		

		} else {
			data.put("done", false);
			data.put("msg", Constants.testConfigurationfail);
//			return data;
		}
		
		return data;

	}

	private TestConfiguration copyFromTestConfigurationVOToTestConfigurationForOtherTest(
			TestConfiguration testConfiguration, TestConfigurationVO testConfigurationVO, Test test2) throws Exception {

		String selTopic[] = test2.getSelectedTopics().split(",");
		
//		int[] selTopics = Arrays.stream(selTopic)
//                .mapToInt(Long::parseLong)
//                .toArray();
		
//		long[] selTopic = Arrays.stream(test2.getSelectedTopics().split(","))
//                .map(String::trim)
//                .mapToLong(Long::valueOf)
//                .toArray();
		Object[] selTopics = new Object[selTopic.length];
						
		for (int i = 0; i < selTopic.length; i++) {
			selTopics[i] = Long.parseLong((String) selTopic[i]);
		}
		
		System.out.println(selTopics);
//		String selVariations[] = test2.getVarNo().split(",");
		
		
		List<QuestionGroup> questionGroups = questionGroupDao.getAllApprovedQuestionGroupsByTopicIdAndVarNo(testConfigurationVO.getTopicVO().getTopicId(), testConfigurationVO.getVarNo());
		
		int compLevel = 0;
		int time = 0;
		if (!questionGroups.isEmpty()) {
			for (QuestionGroup questionGroup : questionGroups) {
				compLevel = questionGroup.getComplexityLevel().getQgComplexityLevelId();
//				QuestionG question = (Question) questionDao.getQuestionsByQuestionGroupId(questionGroupId)
				List<Question> questions =  questionDao.getQuestionsByQuestionGroupId(questionGroup.getQuestionGroupId());
				for (Question question : questions) {
					time = question.getTime();
				}
				
				break;
			}
		}
		
		int finalTime = time * testConfigurationVO.getNoOfQuestionGroup();
		
		QGComplexityLevel complexityLevel = new QGComplexityLevel();
		complexityLevel.setQgComplexityLevelId(compLevel);
		testConfiguration.setComplexityLevel(complexityLevel);

		testConfiguration.setTopicTimeLimit(finalTime);
		
		Standard standard = new Standard();
		standard.setStdId(testConfigurationVO.getStandardVO().getStdId());
		testConfiguration.setStandard(standard);
		
		testConfiguration.setNoOfQuestionGroup(testConfigurationVO.getNoOfQuestionGroup());

		Topic topic = new Topic();
		topic.setTopicId(testConfigurationVO.getTopicVO().getTopicId());
		testConfiguration.setTopic(topic);

		
		Test test = testDao.getTestByTestId(testConfigurationVO.getTestVO().getTestId());
		testConfiguration.setTest(test);

		return testConfiguration;
	}

	private TestConfiguration copyFromTestConfigurationVOToTestConfiguration(TestConfiguration testConfiguration,
			TestConfigurationVO testConfigurationVO) throws Exception {

		QGComplexityLevel complexityLevel = new QGComplexityLevel();
		complexityLevel.setQgComplexityLevelId(testConfigurationVO.getComplexityLevel().getQgComplexityLevelId());
		testConfiguration.setComplexityLevel(complexityLevel);

		testConfiguration.setTopicTimeLimit(testConfigurationVO.getTopicTimeLimit());
		
		Standard standard = new Standard();
		standard.setStdId(testConfigurationVO.getStandardVO().getStdId());
		testConfiguration.setStandard(standard);
		
		testConfiguration.setNoOfQuestionGroup(testConfigurationVO.getNoOfQuestionGroup());

		Topic topic = new Topic();
		topic.setTopicId(testConfigurationVO.getTopicVO().getTopicId());
		testConfiguration.setTopic(topic);

		
		Test test = testDao.getTestByTestId(testConfigurationVO.getTestVO().getTestId());
		testConfiguration.setTest(test);

		return testConfiguration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.service.TestConfigurationService#modifyTestPaper(in.ac.coep.vo
	 * .TestConfigurationVO[])
	 */
	@Override
	public JSONObject modifyTestPaper(TestConfigurationVO[] testConfigurationVOs) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		if (testConfigurationVOs != null) {

			for (TestConfigurationVO testConfigurationVO : testConfigurationVOs) {

				TestConfiguration testConfiguration = testConfigurationDao
						.getTestConfigurationById(testConfigurationVO.getTestConfigId());

				testConfiguration = copyFromTestConfigurationVOToTestConfiguration(testConfiguration,
						testConfigurationVO);

				testConfigurationDao.modifyTestPaper(testConfiguration);

				data.put("LVL" + testConfiguration.getComplexityLevel().getQgComplexityLevelId(),
						testConfiguration.getTestConfigId());

//				data.put("SID", testConfiguration.getSection().getSectionId());

			}
			data.put("done", true);
			// data.put("TID", testId);
			data.put("msg", Constants.testConfigurationsuccess);
			return data;

		} else {
			data.put("done", false);
			data.put("msg", Constants.testConfigurationfail);
			return data;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.service.TestConfigurationService#
	 * fetchtestConfigurationDataBySectionId(int)
	 */
//	@Override
//	public JSONObject fetchtestConfigurationDataBySectionId(int sectionId, long testId) throws Exception {
//		// TODO Auto-generated method stub
//		JSONObject data = new JSONObject();
//		if (sectionId != 0) {
//
//			List<TestConfiguration> testConfigurations = testConfigurationDao
//					.fetchtestConfigurationDataBySectionId(sectionId, testId);
//
//			if (!testConfigurations.isEmpty()) {
//
//				JSONObject quesGrpObj = null;
//				JSONArray quesGrpArray = new JSONArray();
//
////				for (TestConfiguration testConfiguration : testConfigurations) {
////
////					List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsBySectionId(
////							testConfiguration.getSection().getSectionId(), testConfiguration.getNoOfQuestionGroup(),
////							testConfiguration.getComplexityLevel().getQgComplexityLevelId());
////
////					questionGroups = getRandomList(questionGroups, testConfiguration.getNoOfQuestionGroup());
////
//////					for (QuestionGroup questionGroup : questionGroups) {
//////
//////						quesGrpObj = new JSONObject();
//////
//////						quesGrpObj.put("QGRPID", questionGroup.getQuestionGroupId())
//////								.put("name", questionGroup.getName()).put("content", questionGroup.getContent())
//////								.put("MEDTYP", questionGroup.getMediaType().getMediaTypeId());
//////
//////						if (questionGroup.getMedia() != null) {
//////							quesGrpObj.put("MED", questionGroup.getMedia());
//////						}
//////
//////						List<Question> questions = questionDao
//////								.getQuestionsByQuestionGroupId(questionGroup.getQuestionGroupId());
//////
//////						JSONArray quesArray = new JSONArray();
//////						JSONObject quesObj = null;
//////						for (Question question : questions) {
//////
//////							quesObj = new JSONObject();
//////							quesObj.put("content", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />"))
//////									.put("id", question.getQuestionId())
//////									.put("ANSTYP", question.getAnswerType().getAnswerTypeId());
//////
//////							JSONArray ansArray = new JSONArray();
//////
//////							if (question.getAnswers().size() > 4) {
//////								List<Answers> answers = getFourRandomOptions(question.getAnswers(),
//////										question.getAnswerType().getAnswerTypeId());
//////
//////								for (Answers answer : answers) {
//////
//////									JSONObject ansObj = new JSONObject();
//////									ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />"));
//////									ansArray.put(ansObj);
//////								}
//////							} else {
//////								for (Answers answer : question.getAnswers()) {
//////
//////									JSONObject ansObj = new JSONObject();
//////									ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />"));
//////									ansObj.put("ANSID", answer.getAnswersId());
//////									ansArray.put(ansObj);
//////								}
//////							}
//////
//////							// for (Answers answers : question.getAnswers()) {
//////							//
//////							// ansArray.put(new JSONObject().put("content",
//////							// answers.getContent()));
//////							//
//////							// }
//////							quesObj.put("ANS", ansArray);
//////							quesArray.put(quesObj);
//////						}
//////
//////						quesGrpObj.put("QUES", quesArray);
//////						quesGrpArray.put(quesGrpObj);
//////					}
////
////				}
//				data.put("done", true);
//				data.put("data", quesGrpArray);
//
//				return data;
//			} else {
//				data.put("done", false);
//				data.put("msg", "No Test has configured for this topic Id and test Id");
//
//				return data;
//			}
//
//			/*
//			 * List<Object[]> list =
//			 * questionGroupDao.fetchLevelWiseCountOfQuestionGroupsBySections(
//			 * sectionId);
//			 * 
//			 * JSONObject totalNoQues = new JSONObject();
//			 * 
//			 * if (list.size() != 0) { for (Object[] objects : list) {
//			 * 
//			 * totalNoQues.put("LVL" + objects[0], objects[1]);
//			 * 
//			 * }
//			 * 
//			 * totalNoQues.put("done", true);
//			 * 
//			 * } else { totalNoQues.put("done", false); totalNoQues.put("msg",
//			 * "QuestionGroups are not avilable for this section"); }
//			 * 
//			 * data.put("done", true); data.put("savedQues", jsonArray);
//			 * data.put("data", totalNoQues); } else { data.put("done", false);
//			 * data.put("msg", "Data Not available"); }
//			 * 
//			 * return data;
//			 */
//		} else {
//			data.put("done", false);
//			data.put("msg", "section Id is null");
//
//			return data;
//		}
//
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.service.TestConfigurationService#
	 * fetchLevelWiseQuestionGroupsBySectionId(int)
	 */
	@Override
	public JSONObject fetchLevelWiseQuestionGroupsBySectionId(int sectionId) throws Exception {
		// TODO Auto-generated method stub

		if (sectionId != 0) {

			// List<QuestionGroup> questionGroups =
			// questionGroupDao.fetchLevelWiseQuestionGroupsBySectionId(sectionId);

		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.service.TestConfigurationService#
	 * fetchTestQuestionGroupsForTest(long)
	 */
	@Override
	public JSONObject fetchTestQuestionGroupsForTest(long testId, long userId) throws Exception {
		// TODO Auto-generated method stub

		Random rand = new Random(); 
		int value = rand.nextInt((500 - 100) + 1) + 100;
		Thread.sleep(value);
		
		JSONObject data = new JSONObject();
		// List<TestConfiguration> configurations =
		// testConfigurationDao.fetchTestConfigurationOfTestIdGroupBySection(testId);
		User user = userDao.getUserById(userId);
		TestInstanceState testInstanceState = null;
		
//		List<TestInstanceState> instanceState = testStateDao.getTestInstanceStateByUserId(userId);
		List<TestInstanceState> instanceState = testStateDao.getTestInstanceStateByUserIdAndTestId(userId, testId);
		for (TestInstanceState testInstanceState2 : instanceState) {
			
			long expiredTime = testInstanceState2.getPlannedTestEndTime().getTime();
			long currentTime = System.currentTimeMillis();
			if((testInstanceState2.getStatus().equalsIgnoreCase("N") || testInstanceState2.getStatus().equalsIgnoreCase("P")) && expiredTime > currentTime){
				testInstanceState = testInstanceState2;
			}
			
		}
		
//		List<TestInstance> testInstanceStatus = testInstanceDao.getTestInstanceByUsrIdAndPracticeTestNo(userId, testInstanceState.getPracticeTestNo());  // practice test is entered hard core for now
		if (testInstanceState != null) {
			List<TestInstance> testInstanceStatus = testInstanceDao.getTestInstanceByUsrIdAndTestInstanceStateId(userId, testInstanceState.getTestInstanceStateId());
//		}
		
//		testInstanceDao.getTestInstanceByUsrIdAndTestLvl(userId,testInstanceState.getTestLevel());
		
		Test test = testDao.getTestByTestId(testInstanceState.getTest().getTestId());
		
		if(testInstanceStatus.isEmpty()){

			testInstanceState.setLevelComplete(false);
			
			testInstanceState.setTickTime(new Date(System.currentTimeMillis()));
			testInstanceState.setTestStart(new Date(System.currentTimeMillis()));
			
			testInstanceState.setStatus("P");
			testStateDao.update(testInstanceState);

			
			String topics[] = test.getSelectedTopics().split(",");
//			JSONObject sectionObj = new JSONObject();
			
			JSONObject topicObj = new JSONObject();
			
			JSONObject topObj = new JSONObject();
			
			for (int i = 0; i < topics.length; i++) {

				JSONArray questionGroupsArr = new JSONArray();

				List<TestConfiguration> testConfigurations = testConfigurationDao
						.fetchtestConfigurationDataByTopicId(Long.parseLong(topics[i]), test.getTestId());
				
//				testConfigurationDao
//				.fetchtestConfigurationDataBySectionId(Integer.parseInt(topics[i]), test.getTestId());

				int totalquestionsTime = 0;
				
				if (!testConfigurations.isEmpty()) {

					for (TestConfiguration testConfiguration : testConfigurations) {

						String[] varNoArray = testConfiguration.getTest().getVarNo().split(",");
						List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsByTestConfiguration(
								testConfiguration.getTopic().getTopicId(), testConfiguration.getNoOfQuestionGroup(),
								testConfiguration.getComplexityLevel().getQgComplexityLevelId(), varNoArray);
						
//						questionGroupDao.getAllQuestionGroupsBySectionId(
//						testConfiguration.getSection().getSectionId(), testConfiguration.getNoOfQuestionGroup(),
//						testConfiguration.getComplexityLevel().getQgComplexityLevelId());

						
						if (!questionGroups.isEmpty()) {
							questionGroups = getRandomList(questionGroups, testConfiguration.getNoOfQuestionGroup());
							ArrayList<String> varString = new ArrayList<String>(); 
							
//							for (QuestionGroup questionGroup : questionGroups) {
//							for(int q = 0; q < questionGroups.size(); q++) {
							Iterator<QuestionGroup> it = questionGroups.iterator(); 
							for(int q = 0; q < questionGroups.size(); q++) {
								QuestionGroup questionGroup = new QuestionGroup();
								while(it.hasNext()){								
									questionGroup = it.next();
//									System.out.println(questionGroup.getQuestionGroupId() + "_while_" + q);
									if (!varString.contains(questionGroup.getVarNo())) {
										
										varString.add(questionGroup.getVarNo());

										JSONObject questionGroupObj = new JSONObject();
										questionGroupObj.put("QGN", questionGroup.getName());
										questionGroupObj.put("QGC", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
										questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
										questionGroupObj.put("MEDTYP", questionGroup.getMediaType().getMediaTypeId());

										if (questionGroup.getQuesGroupMediaLinks().getMediaURLText() != null) {
											questionGroupObj.put("MED", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
										}
										JSONArray quesArr = new JSONArray();

										TestInstance instance = new TestInstance();

										Topic topic = new Topic();
										topic.setTopicId(Integer.parseInt(topics[i]));
										instance.setTopic(topic);

										instance.setUser(user);
										instance.setTestInstanceState(testInstanceState);

										// instance.setQuestionGroup(questionGroup);

										instance.setQuestionGroupId(questionGroup.getQuestionGroupId());

										Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());

										for (Question question : myOrderedSet) {

											JSONObject quesObj = new JSONObject();
											quesObj.put("QC", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
											quesObj.put("QID", question.getQuestionId());
											quesObj.put("ANSTYP", question.getAnswerType().getAnswerTypeId());

											quesObj.put("QSOLTYPE", question.getSolType());
											quesObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
											quesObj.put("QSOLMEDIA", question.getSolMedia());

											quesArr.put(quesObj);

											JSONArray answerArr = new JSONArray();//

											// instance.setQuestion(question);

											instance.setQuestionId(question.getQuestionId());

											long time = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(question.getTime());
											Date date = new Date(time);

											instance.setPlannedAnsEndTime(date);

											if (question.getAnswers().size() > 4) {
												List<Answers> answers = getFourRandomOptions(question.getAnswers(), 
														question.getAnswerType().getAnswerTypeId());

												for (Answers answer : answers) {
													JSONObject ansObj = new JSONObject();
													if (question.getAnswerType().getAnswerTypeId() == 5 || question.getAnswerType().getAnswerTypeId() == 8) {
														// Answers answer1 = new Answers();
														// answer1.setAnswersId(Constants.answer_Dummy_Record_Id);
														// instance.setAnswers(answer1);
														instance.setAnswersId(Constants.answer_Dummy_Record_Id);
														// instance.setActAnswersId(answer.getAnswersId()); // new field
														// to set actual answer Id
														instance.setActualGivenOptionsAnsId(answer.getAnswersId());// new
																													// column
																													// for
																													// save
																													// randomly
																													// given
																													// options
																													// to
																													// user
														long instanceId = testInstanceDao.save(instance);
														ansObj.put("TI_ID", instanceId);

														ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
														ansObj.put("RANS", answer.isRightAnswer()); // newly added
														ansObj.put("ansMedia", answer.getMedia());
														answerArr.put(ansObj);

													} else {
														// instance.setActAnswersId(answer.getAnswersId()); // new field
														// to set actual answer Id
														instance.setActualGivenOptionsAnsId(answer.getAnswersId());// new
																													// column
																													// for
																													// save
																													// randomly
																													// given
																													// options
																													// to
																													// user
														ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
//														if(answer.isRightAnswer() == true) {
//															ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />")+"_Ans");
//														}else {
//															ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
//														}
														ansObj.put("ANSID", answer.getAnswersId());
														ansObj.put("RANS", answer.isRightAnswer()); // newly added
														ansObj.put("ansMedia", answer.getMedia());
														answerArr.put(ansObj);
													}

												}
											} else {
												for (Answers answer : question.getAnswers()) {
													JSONObject ansObj = new JSONObject();
													if (question.getAnswerType().getAnswerTypeId() == 5 || question.getAnswerType().getAnswerTypeId() == 8) {
														// Answers answer1 = new Answers();
														// answer1.setAnswersId(Constants.answer_Dummy_Record_Id);
														// instance.setAnswers(answer1);
														instance.setAnswersId(Constants.answer_Dummy_Record_Id);
														// instance.setActAnswersId(answer.getAnswersId()); // new field
														// to set actual answer Id
														instance.setActualGivenOptionsAnsId(answer.getAnswersId());// new
																													// column
																													// for
																													// save
																													// randomly
																													// given
																													// options
																													// to
																													// user
														long instanceId = testInstanceDao.save(instance);
														ansObj.put("TI_ID", instanceId);

														ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
														ansObj.put("ANSID", answer.getAnswersId());
														ansObj.put("RANS", answer.isRightAnswer()); // newly added
														ansObj.put("ansMedia", answer.getMedia());
														answerArr.put(ansObj);

													} else {
														// instance.setActAnswersId(answer.getAnswersId()); // new field
														// to set actual answer Id
														instance.setActualGivenOptionsAnsId(answer.getAnswersId());// new
																													// column
																													// for
																													// save
																													// randomly
																													// given
																													// options
																													// to
																													// user
														ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
//														if(answer.isRightAnswer() == true) {
//															ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />")+"_Ans");
//														}else {
//															ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
//														}
														ansObj.put("ANSID", answer.getAnswersId());
														ansObj.put("RANS", answer.isRightAnswer()); // newly added
														ansObj.put("ansMedia", answer.getMedia());
														answerArr.put(ansObj);
													}

												}
											}

											if (question.getAnswerType().getAnswerTypeId() != 5) {
												// Answers answerObj = new Answers();
												// answerObj.setAnswersId(Constants.answer_Dummy_Record_Id);
												// instance.setAnswers(answer);
												if (question.getAnswerType().getAnswerTypeId() != 8) {

													instance.setAnswersId(Constants.answer_Dummy_Record_Id);

													long instanceId = testInstanceDao.save(instance);

													quesObj.put("TI_ID", instanceId);
													// LOGGER.info("Test Instance Saved");
												}

											}

											quesObj.put("ANS", answerArr);

											totalquestionsTime += question.getTime();

										}
										questionGroupObj.put("QUES", quesArr);
										questionGroupsArr.put(questionGroupObj);

									}else {
										q--;
									}
								}
								//	System.out.println(varString.toString());
						}
						}else {
							data.put("done", false);
							data.put("msg", "No questions in the database to display..");
							return data;
//							break;
						}

//						topicObj.put("time" + topics[i], testConfiguration.getTopicTimeLimit());
						topicObj.put("time" + topics[i], totalquestionsTime);
					}
				} else {
					data.put("done", false);
					data.put("msg", "No Test has configured for this topic Id and test Id");

					return data;
				}

//				Topic topic = new Topic();
				String topicName = "";
				topicName =  getParentTopicNameBytopicId(Long.parseLong(topics[i]));
//				topic = topicDao.getTopicByTopicId(Integer.parseInt(topics[i]));
//				System.out.println(topicName);
				
				data.put("topics", topicName);
				
				
				topicObj.put("TOPIC" + topics[i], questionGroupsArr);
				topicObj.put("TOPICID", topics[i]);
				

				// JSONObject secObj = new JSONObject();
				topObj.put(topics[i] + "", Constants.topics.topicsMap.get(Long.parseLong(topics[i])));

				data.put("TISID", testInstanceState.getTestInstanceStateId());
				data.put("STD", testInstanceState.getTest().getStandard().getName());
				data.put("topicObj", topObj);
				
				// data.put("SEC", sectionObj.getJSONArray("SEC" + sections[0]));
				data.put("data", topicObj);
				
				data.put("emailId", testInstanceState.getUser().getEmailId());
//				data.put("testLevel", Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND"));
				data.put("testLevel", testInstanceState.getTestLevel());
				data.put("stdId", testInstanceState.getTest().getStandard().getStdId());
				data.put("TOPICS", topics);
				data.put("RESUME", false);
				data.put("done", true);
			}
		
		} else {

				String currentTopic = "";
				testInstanceState.setTickTime(new Date(System.currentTimeMillis()));
				testInstanceState.setStatus("P");
				
				testInstanceState.setCurrentTestTime(testInstanceState.getCurrentTestTime());
				
				Thread.sleep(500);
				
				int resumeCount = testInstanceState.getRescheduleNo() + 1;
				
				testInstanceState.setRescheduleNo(testInstanceState.getRescheduleNo() + 1 );
				
				testStateDao.update(testInstanceState);
				
				String topics[] = test.getSelectedTopics().split(",");
				JSONObject topicObj = new JSONObject();
				JSONObject topObj = new JSONObject();
				
				for (int i = 0; i < topics.length; i++) {
				
					JSONArray questionGroupsArr = new JSONArray();
				
					List<TestConfiguration> testConfigurations = testConfigurationDao.fetchtestConfigurationDataByTopicId(Integer.parseInt(topics[i]), testInstanceState.getTest().getTestId());
							
							
//							.fetchtestConfigurationDataBySectionId(Integer.parseInt(topics[i]), test.getTestId());
				
					int testEndTime = 0 ;
					
					if (!testConfigurations.isEmpty()) {
				
						JSONObject saveTestConfigData = new JSONObject();
						
						for (TestConfiguration testConfiguration : testConfigurations) {
								saveTestConfigData.put(topics[i], testConfiguration.getTopicTimeLimit());
							}
				
								List<TestInstance> testInstances = testInstanceDao.getAllQuestionGroupsByTopicIdForResumeTest(Long.parseLong(topics[i]), userId,testInstanceState.getTestInstanceStateId());
				
							
//							List<QuestionGroup> questionGroup = questionGroupDao.getQuestionGroupByUserIdAndTestInstanceStateId(user.getUserId(),testInstanceState.getTestInstanceStateId());
							
							List<QuestionGroup> questionGroup = new ArrayList<>();
							 List <QuestionGroup> newList = new ArrayList <QuestionGroup>();
							 Set <Long> set = new LinkedHashSet <Long>();
							for (TestInstance testInstance : testInstances) {
								
								QuestionGroup group =  questionGroupDao.getQuestionGroupById(testInstance.getQuestionGroupId());
								
//								    for (Iterator <QuestionGroup>iter = list.iterator();    iter.hasNext(); ) {
//								       Object element = iter.next();
								       if (set.add(group.getQuestionGroupId())) {
								          newList.add((QuestionGroup) group);
								        questionGroup.add(group); 
								       }
								
							}
							
//							System.out.println(questionGroup);
							
//							List<QuestionGroup> questionGroups = removeDuplicate(questionGroup);
					List<QuestionGroup> questionGroups = newList;
							
							for (QuestionGroup  QGroup : questionGroups) {
				
								JSONObject questionGroupObj = new JSONObject();
								questionGroupObj.put("QGN", QGroup.getName());
								questionGroupObj.put("QGC", QGroup.getQuesGroupMediaLinks().getMediaURLText());
								questionGroupObj.put("QGID", QGroup.getQuestionGroupId());
								questionGroupObj.put("MEDTYP", QGroup.getMediaType().getMediaTypeId());
								
								if (QGroup.getQuesGroupMediaLinks().getMediaURLText() != null) {
									questionGroupObj.put("MED", QGroup.getQuesGroupMediaLinks().getMediaURLText());
								}
								JSONArray quesArr = new JSONArray();
				
								@SuppressWarnings("unused")
								int kl = 0;
								
								Set<Question> questions = new LinkedHashSet<Question>(QGroup.getQuestions());
								
								for (Question question : questions) {
				
									JSONObject quesObj = new JSONObject();
									quesObj.put("QC", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
									quesObj.put("QID", question.getQuestionId());
									quesObj.put("ANSTYP", question.getAnswerType().getAnswerTypeId());

									quesObj.put("QSOLTYPE", question.getSolType());
									quesObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
									quesObj.put("QSOLMEDIA", question.getSolMedia());
									
									quesArr.put(quesObj);
				
									JSONArray answerArr = new JSONArray();//
									
				
									if (question.getAnswers().size() > 4) {
										List<Answers> answers = getFourOptionsToResumeTest(question.getAnswers(),
												question.getAnswerType().getAnswerTypeId());
				
										for (Answers answer : answers) {
											JSONObject ansObj = new JSONObject();
											if (question.getAnswerType().getAnswerTypeId() == 5 || question.getAnswerType().getAnswerTypeId() == 8) {
												
												List<TestInstance> instance = testInstanceDao.getTestInstanceListByQuesionIdAndUserId(question.getQuestionId(),QGroup.getQuestionGroupId(), user.getUserId(),testInstanceState.getTestInstanceStateId());
//												
												for (TestInstance testInstance2 : instance) {
													
													ansObj.put("TI_ID", testInstance2.getTestInstanceId());
													
												}
												
												ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
												
												ansObj.put("ANSID", answer.getAnswersId());
												ansObj.put("RANS", answer.isRightAnswer()); // newly added
												ansObj.put("ansMedia", answer.getMedia());
												answerArr.put(ansObj);
				
											} else {
												
//												ansObj.put("GANSID", testInstance.getAnswers().getAnswersId()); // given ans id
												
												ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
//												if(answer.isRightAnswer() == true) {
//													ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />")+"_Ans");
//												}else {
//													ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
//												}
												ansObj.put("ANSID", answer.getAnswersId());
												ansObj.put("RANS", answer.isRightAnswer()); // newly added
												ansObj.put("ansMedia", answer.getMedia());
//												List<TestInstance> instance = testInstanceDao.getTestInstanceListByQuesionIdAndUserId(question.getQuestionId(),QGroup.getQuestionGroupId(), user.getUserId(),testInstanceState.getTestInstanceStateId());
//												for (TestInstance testInstance : instance) {
//													if(testInstance.getTopic().getName().equalsIgnoreCase(Constants.psySecname) && testInstance.getQuestionGroup().getComplexityLevel().getQgComplexityLevelId() == 1){
//														ansObj.put("PSYID", answer.getAnswersId());
//													}
//												}
												
												answerArr.put(ansObj);
											}
										}
										
									} else {
										
										int  j = 0;
										for (Answers answer : question.getAnswers()) {
											JSONObject ansObj = new JSONObject();
											int k = 0;
											if (question.getAnswerType().getAnswerTypeId() == 5 || question.getAnswerType().getAnswerTypeId() == 8) {
												
												
												List<TestInstance> instance = testInstanceDao.getTestInstanceListByQuesionIdAndUserId(question.getQuestionId(), QGroup.getQuestionGroupId(), user.getUserId(),testInstanceState.getTestInstanceStateId());
												
												Map<Integer, Long> map =  new HashMap<Integer, Long>();
												
												
												for (TestInstance testInstance2 : instance) {
													
													map.put(k++,  testInstance2.getTestInstanceId());
													
												}
												
												ansObj.put("TI_ID", map.get(j));
												j++;
											
												
//												ansObj.put("GANSID", testInstance.getAnswers().getAnswersId()); // given ans id
				
												ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
												ansObj.put("ANSID", answer.getAnswersId());
												ansObj.put("RANS", answer.isRightAnswer()); // newly added
												ansObj.put("ansMedia", answer.getMedia());
												answerArr.put(ansObj);
				
											} else {
												
//												ansObj.put("GANSID", testInstance.getAnswers().getAnswersId()); // given ans id
												
												ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
//												if(answer.isRightAnswer() == true) {
//													ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />")+"_Ans");
//												}else {
//													ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
//												}
												ansObj.put("ANSID", answer.getAnswersId());
												ansObj.put("RANS", answer.isRightAnswer()); // newly added
												ansObj.put("ansMedia", answer.getMedia());
//												List<TestInstance> instance = testInstanceDao.getTestInstanceListByQuesionIdAndUserId(question.getQuestionId(),QGroup.getQuestionGroupId(), user.getUserId(),testInstanceState.getTestInstanceStateId());
//												for (TestInstance testInstance : instance) {
//													if(testInstance.getSection().getName().equalsIgnoreCase(Constants.psySecname) && testInstance.getQuestionGroup().getComplexityLevel().getQgComplexityLevelId() == 1){
//														ansObj.put("PSYID", testInstance.getGivenAnsForPsy());
//													}
//												}
												answerArr.put(ansObj);
											}
				
										}
									}
				
									if (question.getAnswerType().getAnswerTypeId() != 5) {
//										Answers answer = new Answers();
//										answer.setAnswersId(Constants.answer_Dummy_Record_Id);
//										instance.setAnswers(answer);
//										long instanceId = testInstanceDao.save(instance);
										
										if (question.getAnswerType().getAnswerTypeId() != 8) {
											
											TestInstance instance = testInstanceDao.getTestInstanceByQuesionIdAndUserId(question.getQuestionId(),QGroup.getQuestionGroupId(), user.getUserId(),testInstanceState.getTestInstanceStateId());
											quesObj.put("TI_ID", instance.getTestInstanceId());
											
//											quesObj.put("PSYID", instance.getGivenAnsForPsy());
//											LOGGER.info("Test Instance Saved");
										}
										
										
									}
				
									quesObj.put("ANS", answerArr);
									
									int k = 0;
									JSONArray jArray = new JSONArray();
									
											if(question.getAnswerType().getAnswerTypeId() == 5 || question.getAnswerType().getAnswerTypeId() == 8){
												
												List<TestInstance> instance = testInstanceDao.getTestInstanceListByQuesionIdAndUserId(question.getQuestionId(), QGroup.getQuestionGroupId(), user.getUserId(),testInstanceState.getTestInstanceStateId());
												
												Map<Integer, Long> map =  new HashMap<Integer, Long>();
												
												
												JSONObject json  = new JSONObject();	
												for (TestInstance testInstance2 : instance) {
													
													map.put(k++,  testInstance2.getAnswersId());
													json.put("GANSID"+k, testInstance2.getAnswersId());
													
												}
												
												jArray.put(json);
												quesObj.put("GANSID",jArray);
												kl++;
												
											}else{
												
												TestInstance instance = testInstanceDao.getTestInstanceByQuesionIdAndUserId(question.getQuestionId(), QGroup.getQuestionGroupId(), user.getUserId(),testInstanceState.getTestInstanceStateId());
												quesObj.put("GANSID", instance.getAnswersId()); // given ans id
												
											}
									
									
									}
								questionGroupObj.put("QUES", quesArr);
								questionGroupsArr.put(questionGroupObj);
							}
							
							
							String[] CT = testInstanceState.getCurrentTestTime().split("-");// currentTime for resume test
							
							if(CT.length == 1){
								String[] temp = new String[2];
								int updateTime =  (int) ( saveTestConfigData.get(topics[i]));
//								System.out.println(updateTime);	
								int timeToConvert =  updateTime;
								timeToConvert = timeToConvert * 60;
								String FinalTime = timeToConvert+"-"+topics[i];
								testInstanceState.setCurrentTestTime(FinalTime);
								temp[0] = String.valueOf(timeToConvert);
								temp[1] = topics[i];
								testStateDao.update(testInstanceState);
								
								if(topics[i].equals(temp[1])){
									
									topicObj.put("time" + topics[i],Integer.parseInt(temp[0]));
									
									topicObj.put("RTime" + topics[i],(Integer.parseInt(temp[0]) / 60));
									
									currentTopic = temp[1];
									
								}else{
									
									topicObj.put("time" + topics[i], saveTestConfigData.get(topics[i]));
									topicObj.put("RTime" + topics[i], saveTestConfigData.get(topics[i]));
									
								}
								
							}else {
								
								if(topics[i].equals(CT[1])){
									
									topicObj.put("time" + topics[i],Integer.parseInt(CT[0]));
									
									topicObj.put("RTime" + topics[i],(Integer.parseInt(CT[0]) / 60));
									
									currentTopic = CT[1];
									
								}else{
									
									topicObj.put("time" + topics[i], saveTestConfigData.get(topics[i]));
									topicObj.put("RTime" + topics[i], saveTestConfigData.get(topics[i]));
									
								}
							}
							
//							if (CT.length == 0) {
//								CT[0] = (String) saveTestConfigData.get(topics[i]);
//								CT[1] = topics[i];
//								topicObj.put("time" + topics[i], saveTestConfigData.get(topics[i]));
//								topicObj.put("RTime" + topics[i], saveTestConfigData.get(topics[i]));
//							}
							
							
							
							// topicObj.put("noQ" + sections[i],
							// testConfiguration.getNoOfQuestionGroup());
//						}
					} else {
						data.put("done", false);
						data.put("msg", "No Test has configured for this topic Id and test Id");
				
						return data;
					}
				
					topicObj.put("TOPIC" + topics[i], questionGroupsArr);
					
					
//					Topic topic = new Topic();
//					topic = topicDao.getTopicByTopicId(Integer.parseInt(topics[i]));
//					data.put("topics", topic.getTopicName() + "-"+ topic.getTopicName1());
					
					String topicName = "";
					topicName =  getParentTopicNameBytopicId(Long.parseLong(topics[i]));
//					topic = topicDao.getTopicByTopicId(Integer.parseInt(topics[i]));
//					System.out.println(topicName);
					
					data.put("topics", topicName);
					
					topicObj.put("TOPIC" + topics[i], questionGroupsArr);
					topicObj.put("TOPICID", topics[i]);
						
					// JSONObject secObj = new JSONObject();
//					secObj.put(topics[i] + "", Constants.topics.topicsMap.get(Integer.parseInt(topics[i])));
					topObj.put(topics[i] + "", Constants.topics.topicsMap.get(Long.parseLong(topics[i])));
					
					data.put("TISID", testInstanceState.getTestInstanceStateId());
					data.put("STD", testInstanceState.getTest().getStandard().getName());
					data.put("topObj", topObj);
				
					data.put("data", topicObj);
					
					data.put("emailId", testInstanceState.getUser().getEmailId());
//					data.put("testLevel", Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND"));
					data.put("testLevel", testInstanceState.getTestLevel());
					data.put("stdId", testInstanceState.getTest().getStandard().getStdId());
					data.put("TOPICS", topics);
					
					data.put("CURRSEC", currentTopic);
					data.put("TETRESUME", testEndTime);
					data.put("RESUMECOUNT", resumeCount);
					data.put("RESUME", true);
					data.put("done", true);
				}
				
		}
	}else {
		data.put("done", false);
//		data.put("msg", "page refreshed condition");
	}
		return data;
	}
		

	
	
	private String getParentTopicNameBytopicId(long topicId) throws Exception {
		
		Topic topic = new Topic();
		
		topic = topicDao.getTopicByTopicId(topicId);
		
		int len = (Integer.parseInt(topic.getLevel()) - 1);
		String TopicName = ""; 
		String result = "";
		long temp = 0l;
		
		for (int i = 0; i < len; i++) {
			String result2 = "";
			
			if (i == 0) {
				 result2 =  getTopicParentFromId(topicId);
				result = result2.split("-")[0];
				temp = Long.parseLong(result2.split("-")[1]);
				 String[] temp2 = result.split("-");
				 if (temp2[0].equals("01")) {
					 Topic t2 = topicDao.getTopicByTopicId(temp);
					 TopicName = t2.getTopicName() + " (" + t2.getTopicName1() + " ) >> "+ topic.getTopicName() + " ( " + topic.getTopicName1() + " ) " ;
				}
				
			}else{
				
				result2 =  getTopicParentFromId(temp);
				temp = Long.parseLong(result2.split("-")[1]);
				result = result2.split("-")[0];
				 String[] temp2 = result.split("-");
				 if (temp2[0].equals("01")) {
					 Topic t2 = topicDao.getTopicByTopicId(temp);
					 TopicName = t2.getTopicName() + " (" + t2.getTopicName1() + " ) >> "+ topic.getTopicName() + " ( " + topic.getTopicName1() + " ) " ;
				}
			}
			
			
		}
			
			return TopicName;
		
	}
	
	
	private String getTopicParentFromId(long topicId) throws Exception {
		
		TopicMapping topicMapping = topicMappingDao.getTopicParentByTopicId(topicId);
		
		String level = topicMapping.getParent().getLevel();
		String parentId = String.valueOf(topicMapping.getParent().getTopicId());
		
		String res = level + "-" +parentId;
		
		
		return res;
		
	}

	/**
	 * @param questionGroups
	 * @return
	 */
	private static List<QuestionGroup> getRandomList(List<QuestionGroup> questionGroups, int noOfQuestionGroup) {
		// TODO Auto-generated method stub
		Collections.shuffle(questionGroups);
		List<QuestionGroup> questionGroups2 = new ArrayList<>();
		ArrayList<String> varString = new ArrayList<String>();
		int i = 0;
		Iterator<QuestionGroup> iterator = questionGroups.iterator();

		while (iterator.hasNext()) {
			
			QuestionGroup questionGroup = (QuestionGroup) iterator.next();
			if (!varString.contains(questionGroup.getVarNo())) {
				varString.add(questionGroup.getVarNo());
				if(i < noOfQuestionGroup)
				questionGroups2.add(questionGroup);
				i++;
			}
		}
		return questionGroups2;
	}

	private static List<Answers> getFourRandomOptions(Set<Answers> set, int ansTyp) {

		List<Answers> list = new ArrayList<>();
		list.addAll(set);
		Collections.shuffle(list);

		List<Answers> answers2 = new ArrayList<>();
		int i = 0;
		int countRtAns = 0;
		int countWrAns = 0;
		Iterator<Answers> iterator = set.iterator();
		if (ansTyp == 1) {
			while (iterator.hasNext() && i < 4) {
				Answers answer = (Answers) iterator.next();
				if (answer.isRightAnswer()) {
					answers2.add(answer);
					countRtAns++;
					i++;
				} else if (!answer.isRightAnswer() && countWrAns < 3) {
					answers2.add(answer);
					countWrAns++;
					i++;
				} else {
					continue;
				}

			}

		} else {
			while (iterator.hasNext() && countRtAns <= 1) {
				Answers answer = (Answers) iterator.next();
				if (answer.isRightAnswer() && countRtAns == 0) {
					answers2.add(answer);
					countRtAns++;
				} else if (!answer.isRightAnswer() && countWrAns < 3) {
					answers2.add(answer);
					countWrAns++;
				} else {
					continue;
				}

			}
		}
		return answers2;
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	public static <QuestionGroup> List<QuestionGroup> removeDuplicate(List <QuestionGroup> list) {
	    Set <QuestionGroup> set = new LinkedHashSet <QuestionGroup>();
	    List <QuestionGroup> newList = new ArrayList <QuestionGroup>();
	    for (Iterator <QuestionGroup>iter = list.iterator();    iter.hasNext(); ) {
	       Object element = iter.next();
	       if (set.add((QuestionGroup) element))
	          newList.add((QuestionGroup) element);
	       }
	       list.clear();
	       list.addAll(newList);
	       System.out.println(set);
	       
	       return (List<QuestionGroup>) newList;
	    }
	
	private static List<Answers> getFourOptionsToResumeTest(Set<Answers> set, int ansTyp) {

		List<Answers> list = new ArrayList<>();
		list.addAll(set);
//		Collections.shuffle(list);

		List<Answers> answers2 = new ArrayList<>();
		int i = 0;
		int countRtAns = 0;
		int countWrAns = 0;
		Iterator<Answers> iterator = set.iterator();
		if (ansTyp == 1) {
			while (iterator.hasNext() && i < 4) {
				Answers answer = (Answers) iterator.next();
				if (answer.isRightAnswer()) {
					answers2.add(answer);
					countRtAns++;
					i++;
				} else if (!answer.isRightAnswer() && countWrAns < 3) {
					answers2.add(answer);
					countWrAns++;
					i++;
				} else {
					continue;
				}

			}

		} else {
			while (iterator.hasNext() && countRtAns <= 1) {
				Answers answer = (Answers) iterator.next();
				if (answer.isRightAnswer() && countRtAns == 0) {
					answers2.add(answer);
					countRtAns++;
				} else if (!answer.isRightAnswer() && countWrAns < 3) {
					answers2.add(answer);
					countWrAns++;
				} else {
					continue;
				}

			}
		}
		return answers2;
	}

	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.service.TestConfigurationService#
	 * fetchSectionNameAndTimeForTest(long)
	 */
//	@Override
//	public JSONObject fetchSectionNameAndTimeForTest(String token) throws Exception {
//		// TODO Auto-generated method stub
//		JSONObject jsonObject = new JSONObject();
//
//		String str = EncoderDecoder.decodeString(token);
//
//		String[] tid = str.split(Constants.splitter);
//
//		Test test = testDao.getTestByTestId(Long.parseLong(tid[2]));
//
//		List<TestConfiguration> configurations = testConfigurationDao
//				.fetchTestConfigurationOfTestIdGroupBySection(test.getTestId());
//
//		for (TestConfiguration testConfiguration : configurations) {
//
////			jsonObject.put(testConfiguration.getSection().getName(), testConfiguration.getSectionTimeLimit());
//		}
//
//		return jsonObject;
//	}

	@Override
	public JSONObject fetchAvailableOtherTestToConfig() throws Exception {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		
		long testTypeId = Constants.otherTestTypeId;
		LOGGER.info("Fetch test Start ..");
		List<Test> testList = testDao.getAllTestListByTestTypeId(testTypeId);
		if (testList.size() != 0) {
			JSONArray jsonArray = new JSONArray();
			JSONObject allTest = null;
			
			for (Test availTest : testList) {
				allTest = new JSONObject();
				allTest.put("ID", availTest.getTestId());
				allTest.put("TN", availTest.getName());
				allTest.put("SD", availTest.getStartDate());
				allTest.put("ED", availTest.getEndDate());
				allTest.put("ISACT", availTest.isActive());
				allTest.put("CB", availTest.getCreatedBy());
				
				String[] parts = availTest.getSelectedTopics().split(",");
				
				JSONArray allTopics = new JSONArray();
				
				for (int i = 0; i < parts.length; i++) {
					Topic topic =  topicDao.getTopicByTopicId(Long.parseLong(parts[i]));
					allTopics.put(topic.getTopicName());
				}
				
				allTest.put("TP", allTopics);
				
				allTest.put("VARNO", availTest.getVarNo());
				jsonArray.put(allTest);				
		}
		
		data.put("done", true);
		data.put("data", jsonArray);
		LOGGER.info("Fetch test End ..");
		} else {
			LOGGER.info("Fetch test Failed ..");
			data.put("done", false);
			data.put("msg", "test data not available");
		}
		
		
		return data;
	}

	@Override
	public JSONObject updateTestDetails(TestVO testVO) throws Exception {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();

		if (testVO != null) {
			Test test = testDao.getTestByTestId(testVO.getTestId());
			test.setStartDate(testVO.getStartDate());
			test.setEndDate(testVO.getEndDate());
			test.setActive(testVO.isActive());
			
			testDao.updateTestDetails(test);
			
			data.put("done", true);
			data.put("msg", "Test updated successfully");
		} else {
			data.put("done", false);
			data.put("msg", "");

		}

		return data;
	}
	

//	@Override
//	public JSONObject fetchTestToConfigDetails(long testId) throws Exception {
//			JSONObject data = new JSONObject();
//		
//		LOGGER.info("Fetch test config Start ..");
//		List <TestConfiguration> testConfigList = testConfigurationDao.getAllTestConfigDetails(testId);
//		if (testConfigList.size() != 0) {
//			JSONArray jsonArray = new JSONArray();
//			JSONObject allTestConfig = null;
//			JSONObject dept = null;
//			JSONObject section = null;
//			for (TestConfiguration availTestConfDept : testConfigList) {
//				dept = new JSONObject();
////				dept.put("DEPT", availTestConfDept.getDepartmant().getName());
//			}
//			jsonArray.put(dept);
//			
//			for (TestConfiguration availTestConf : testConfigList) {
//				
////				int sectId = availTestConf.getSection().getSectionId();
//				int complexLevel = availTestConf.getComplexityLevel().getQgComplexityLevelId();
//				
////				List <TestConfiguration> lvlBySecId = testConfigurationDao.fetchComplexcityLvlBySectId(sectId, complexLevel);
//				
//				allTestConfig = new JSONObject();
////				allTestConfig.put("SEC",  availTestConf.getSection().getName());
//				allTestConfig.put("COMPLEVEL", availTestConf.getComplexityLevel().getName());
//				allTestConfig.put("NOQUESGRP", availTestConf.getNoOfQuestionGroup());
////				allTestConfig.put("DEPT", availTestConf.getDepartmant().getName());
////				allTestConfig.put("SECTIME",availTestConf.getSectionTimeLimit());
//				allTestConfig.put("CONFID", availTestConf.getTestConfigId());
////				allTestConfig.put("NOQGPAVAIL", lvlBySecId.size());
//				jsonArray.put(allTestConfig);
//			}
//			
//			
//			data.put("done", true);
//			data.put("data", jsonArray);
//			LOGGER.info("Fetch test Config End ..");
//			} else {
//				LOGGER.info("Fetch test Config Failed ..");
//				data.put("done", false);
//				data.put("msg", "Test configuration not available");
//			}
//		// TODO Auto-generated method stub
//		return data;
//	}

	@Override
	public JSONObject updateTestConfigDetails(TestConfigurationVO[] testConfigurationVOs) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		if (testConfigurationVOs != null) {

			for (TestConfigurationVO testConfigurationVO : testConfigurationVOs) {

				TestConfiguration testConfiguration = testConfigurationDao
						.getTestConfigurationById(testConfigurationVO.getTestConfigId());

			testConfiguration.setNoOfQuestionGroup(testConfigurationVO.getNoOfQuestionGroup());

				testConfigurationDao.modifyTestPaper(testConfiguration);
			}
			data.put("done", true);
			data.put("msg", Constants.testConfigurationUpdatesuccess);
			return data;

		} else {
			data.put("done", false);
			data.put("msg", Constants.testConfigUpdatefail);
			return data;
		}

	}

	@Override
	public JSONObject fetchTopicNameAndTimeForPracticeTest(String token) throws Exception {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();

		String str = EncoderDecoder.decodeString(token);

		String[] tid = str.split(Constants.splitter);

		Test test = testDao.getTestByTestId(Long.parseLong(tid[2]));

		List<TestConfiguration> configurations = testConfigurationDao
				.fetchTestConfigurationOfTestIdGroupBySection(test.getTestId());

		for (TestConfiguration testConfiguration : configurations) {

			jsonObject.put(testConfiguration.getTopic().getTopicName()+"*"+testConfiguration.getTopic().getTopicName1(), testConfiguration.getTopicTimeLimit());
			jsonObject.put("testId", test.getTestId());
			jsonObject.put("testTypeId", test.getTestType().getTestTypeId());
			jsonObject.put("done", true);
			
		}

		return jsonObject;
	}

	
	// This function is for other test types
	@Override
	public JSONObject fetchTestQuestionGroupsForOtherTest(long testId, long userId) throws Exception {
		// TODO Auto-generated method stub

		Random rand = new Random(); 
		int value = rand.nextInt((500 - 100) + 1) + 100;
		Thread.sleep(value);
		
		JSONObject data = new JSONObject();

		User user = userDao.getUserById(userId);
		TestInstanceState testInstanceState = null;
		
		List<TestInstanceState> instanceState = testStateDao.getTestInstanceStateByUserIdAndTestId(userId, testId);
		for (TestInstanceState testInstanceState2 : instanceState) {
			
			long expiredTime = testInstanceState2.getPlannedTestEndTime().getTime();
			long currentTime = System.currentTimeMillis();
			if((testInstanceState2.getStatus().equalsIgnoreCase("N") || testInstanceState2.getStatus().equalsIgnoreCase("P")) && expiredTime > currentTime){
				testInstanceState = testInstanceState2;
			}
			
		}
		
		if (testInstanceState != null) {
			List<TestInstance> testInstanceStatus = testInstanceDao.getTestInstanceByUsrIdAndTestInstanceStateId(userId, testInstanceState.getTestInstanceStateId());
		
		Test test = testDao.getTestByTestId(testInstanceState.getTest().getTestId());
		
		if(testInstanceStatus.isEmpty()){

			testInstanceState.setLevelComplete(false);
			
			testInstanceState.setTickTime(new Date(System.currentTimeMillis()));
			testInstanceState.setTestStart(new Date(System.currentTimeMillis()));
			
			testInstanceState.setStatus("P");
			testStateDao.update(testInstanceState);
			
			String topics[] = test.getSelectedTopics().split(",");
			
			JSONObject topicObj = new JSONObject();
			
			JSONObject topObj = new JSONObject();
			int len = 0;
			
//			JSONArray questionGroupsArrForAllTopics = new JSONArray();
//			JSONObject questionGroupsForAllTopics = new JSONObject();
//			JSONObject topObjForAllTopics = new JSONObject();
//			String topicIds = "";
			
			for (int i = 0; i < topics.length; i++) {

				JSONArray questionGroupsArr = new JSONArray();

				List<TestConfiguration> testConfigurations = testConfigurationDao
						.fetchtestConfigurationDataByTopicId(Long.parseLong(topics[i]), test.getTestId());
				
				int totalquestionsTime = 0;
				
				if (!testConfigurations.isEmpty()) {
					
					String[] selectedVarNosFinal = null;
					
					for (TestConfiguration testConfiguration : testConfigurations) {
						String[] selectedVarNo =  testConfiguration.getTest().getVarNo().split(",");
						String[] selectedVarNosF = new String[selectedVarNo.length];
						
						for (int j = 0; j < selectedVarNo.length; j++) {
							Topic topic = topicDao.getTopicByTopicId(Long.parseLong((topics[i])));
							String[] selectedVarNos = selectedVarNo[j].split(" - "+topic.getTopicNo());
							if (selectedVarNos[0].contains(" - ")) {
//								System.out.println(selectedVarNos[0]);
							}else {
								len++;
								selectedVarNosF[j] =  selectedVarNos[0].trim();
							}
							
						}
						 selectedVarNosFinal = new String[len];
							int x = 0;
							for (int j = 0; j < selectedVarNosF.length; j++) {
								
								if (selectedVarNosF[j] == null ) {
									
								}else {
									selectedVarNosFinal[x] = selectedVarNosF[j];
									x++;
								}
							}
							
						len = 0;
						x = 0;
							
						List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsByTestConfiguration(
								testConfiguration.getTopic().getTopicId(), testConfiguration.getNoOfQuestionGroup(),
								testConfiguration.getComplexityLevel().getQgComplexityLevelId(), selectedVarNosFinal);
						
						if (!questionGroups.isEmpty()) {
							questionGroups = getRandomList(questionGroups, testConfiguration.getNoOfQuestionGroup());

							for (QuestionGroup questionGroup : questionGroups) {

								JSONObject questionGroupObj = new JSONObject();
								questionGroupObj.put("QGN", questionGroup.getName());
								questionGroupObj.put("QGC", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
								questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
								questionGroupObj.put("MEDTYP", questionGroup.getMediaType().getMediaTypeId());

								if (questionGroup.getQuesGroupMediaLinks().getMediaURLText() != null) {
									questionGroupObj.put("MED", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
								}
								
								JSONArray quesArr = new JSONArray();
								TestInstance instance = new TestInstance();

								Topic topic = new Topic();
								topic.setTopicId(Integer.parseInt(topics[i]));
								instance.setTopic(topic);
								
								instance.setUser(user);
								instance.setTestInstanceState(testInstanceState);

								instance.setQuestionGroupId(questionGroup.getQuestionGroupId());

								Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
								
								for (Question question : myOrderedSet) {

									JSONObject quesObj = new JSONObject();
									quesObj.put("QC", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
									quesObj.put("QID", question.getQuestionId());
									quesObj.put("ANSTYP", question.getAnswerType().getAnswerTypeId());
									
									quesObj.put("QSOLTYPE", question.getSolType());
									quesObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
									quesObj.put("QSOLMEDIA", question.getSolMedia());
									
									quesArr.put(quesObj);

									JSONArray answerArr = new JSONArray();//
									
									instance.setQuestionId(question.getQuestionId());

									long time = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(question.getTime());
									Date date = new Date(time);

									instance.setPlannedAnsEndTime(date);

									if (question.getAnswers().size() > 4) {
										List<Answers> answers = getFourRandomOptions(question.getAnswers(),
												question.getAnswerType().getAnswerTypeId());

										for (Answers answer : answers) {
											JSONObject ansObj = new JSONObject();
											if (question.getAnswerType().getAnswerTypeId() == 5 || question.getAnswerType().getAnswerTypeId() == 8) {

												instance.setAnswersId(Constants.answer_Dummy_Record_Id);
//												instance.setActAnswersId(answer.getAnswersId()); // new field to set actual answer Id
												instance.setActualGivenOptionsAnsId(answer.getAnswersId());// new
																											// column
																											// for
																											// save
																											// randomly
																											// given
																											// options
																											// to
																											// user
												long instanceId = testInstanceDao.save(instance);
												ansObj.put("TI_ID", instanceId);

												ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
												ansObj.put("RANS", answer.isRightAnswer()); // newly added
												ansObj.put("ansMedia", answer.getMedia());
												answerArr.put(ansObj);

											} else {
												instance.setActualGivenOptionsAnsId(answer.getAnswersId());// new
																											// column
																											// for
																											// save
																											// randomly
																											// given
																											// options
																											// to
																											// user
												ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
												ansObj.put("ANSID", answer.getAnswersId());
												ansObj.put("RANS", answer.isRightAnswer()); // newly added
												ansObj.put("ansMedia", answer.getMedia());
												answerArr.put(ansObj);
											}

										}
									} else {
										for (Answers answer : question.getAnswers()) {
											JSONObject ansObj = new JSONObject();
											if (question.getAnswerType().getAnswerTypeId() == 5 || question.getAnswerType().getAnswerTypeId() == 8) {
//											
												instance.setAnswersId(Constants.answer_Dummy_Record_Id);
//												instance.setActAnswersId(answer.getAnswersId()); // new field to set actual answer Id
												instance.setActualGivenOptionsAnsId(answer.getAnswersId());// new
																											// column
																											// for
																											// save
																											// randomly
																											// given
																											// options
																											// to
																											// user
												long instanceId = testInstanceDao.save(instance);
												ansObj.put("TI_ID", instanceId);

												ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
												ansObj.put("ANSID", answer.getAnswersId());
												ansObj.put("RANS", answer.isRightAnswer()); // newly added
												ansObj.put("ansMedia", answer.getMedia());
												answerArr.put(ansObj);

											} else {
												instance.setActualGivenOptionsAnsId(answer.getAnswersId());// new
																											// column
																											// for
																											// save
																											// randomly
																											// given
																											// options
																											// to
																											// user
												ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
												ansObj.put("ANSID", answer.getAnswersId());
												ansObj.put("RANS", answer.isRightAnswer()); // newly added
												ansObj.put("ansMedia", answer.getMedia());
												answerArr.put(ansObj);
											}

										}
									}

									if (question.getAnswerType().getAnswerTypeId() != 5) {
//										 
										if (question.getAnswerType().getAnswerTypeId() != 8) {
											
											instance.setAnswersId(Constants.answer_Dummy_Record_Id);
											
											long instanceId = testInstanceDao.save(instance);

											quesObj.put("TI_ID", instanceId);
										}
									}

									quesObj.put("ANS", answerArr);
									totalquestionsTime += question.getTime();

								}
								questionGroupObj.put("QUES", quesArr);
								questionGroupsArr.put(questionGroupObj);
								
							} // 
						}else {
							data.put("done", false);
							data.put("msg", "No questions in the database to display..");
							return data;
						}

						topicObj.put("time" + topics[i], totalquestionsTime);
					}
				} else {
					data.put("done", false);
					data.put("msg", "No Test has configured for this topic Id and test Id");

					return data;
				}

				String topicName = "";
				topicName =  getParentTopicNameBytopicId(Long.parseLong(topics[i]));

				data.put("topics" + topics[i], topicName);
				
				topicObj.put("TOPIC" + topics[i], questionGroupsArr);
				topicObj.put("TOPICID", topics[i]);
				// new code
//				questionGroupsArrForAllTopics.put(questionGroupsArr);
//				topicIds = topicIds +  topics[i]+",";
//				topObjForAllTopics.put("TOPICIDS", topicIds);

				topObj.put(topics[i] + "", Constants.topics.topicsMap.get(Long.parseLong(topics[i])));
//				Constants.topics.topicsMap.entrySet().
				data.put("TISID", testInstanceState.getTestInstanceStateId());
				data.put("STD", testInstanceState.getTest().getStandard().getName());
				data.put("topicObj", topObj);
				
				data.put("data", topicObj);
				
				data.put("emailId", testInstanceState.getUser().getEmailId());
				data.put("testLevel", testInstanceState.getTestLevel());
				data.put("stdId", testInstanceState.getTest().getStandard().getStdId());
				data.put("TOPICS", topics);
				data.put("RESUME", false);
				data.put("done", true);
			}
			
//			questionGroupsForAllTopics.put("ALLDATA", questionGroupsArrForAllTopics);
//			data.put("NEWDATA", questionGroupsForAllTopics);
//			data.put("NEWTOPICDATA", topObjForAllTopics);
		
		} else {

				String currentTopic = "";
				testInstanceState.setTickTime(new Date(System.currentTimeMillis()));
				testInstanceState.setStatus("P");
				
				testInstanceState.setCurrentTestTime(testInstanceState.getCurrentTestTime());
				
				Thread.sleep(500);
				
				int resumeCount = testInstanceState.getRescheduleNo() + 1;
				
				testInstanceState.setRescheduleNo(testInstanceState.getRescheduleNo() + 1 );
				
				testStateDao.update(testInstanceState);
				
				String topics[] = test.getSelectedTopics().split(",");
				JSONObject topicObj = new JSONObject();
				JSONObject topObj = new JSONObject();
				
//				JSONArray questionGroupsArrForAllTopics = new JSONArray();
//				JSONObject questionGroupsForAllTopics = new JSONObject();
//				JSONObject topObjForAllTopics = new JSONObject();
//				String topicIds = "";
				
				for (int i = 0; i < topics.length; i++) {
				
					JSONArray questionGroupsArr = new JSONArray();
				
					List<TestConfiguration> testConfigurations = testConfigurationDao.fetchtestConfigurationDataByTopicId(Integer.parseInt(topics[i]), testInstanceState.getTest().getTestId());
							
					int testEndTime = 0 ;
					
					if (!testConfigurations.isEmpty()) {
				
						JSONObject saveTestConfigData = new JSONObject();
						
						for (TestConfiguration testConfiguration : testConfigurations) {
								saveTestConfigData.put(topics[i], testConfiguration.getTopicTimeLimit());
							}
				
							List<TestInstance> testInstances = testInstanceDao.getAllQuestionGroupsByTopicIdForResumeTest(Long.parseLong(topics[i]), userId,testInstanceState.getTestInstanceStateId());
				
							List<QuestionGroup> questionGroup = new ArrayList<>();
							 List <QuestionGroup> newList = new ArrayList <QuestionGroup>();
							 Set <Long> set = new LinkedHashSet <Long>();
							for (TestInstance testInstance : testInstances) {
								
								QuestionGroup group =  questionGroupDao.getQuestionGroupById(testInstance.getQuestionGroupId());
								
								       if (set.add(group.getQuestionGroupId())) {
								          newList.add((QuestionGroup) group);
								        questionGroup.add(group); 
								       }
								}
							
							List<QuestionGroup> questionGroups = newList;
							
							for (QuestionGroup  QGroup : questionGroups) {
				
								JSONObject questionGroupObj = new JSONObject();
								questionGroupObj.put("QGN", QGroup.getName());
								questionGroupObj.put("QGC", QGroup.getQuesGroupMediaLinks().getMediaURLText());
								questionGroupObj.put("QGID", QGroup.getQuestionGroupId());
								questionGroupObj.put("MEDTYP", QGroup.getMediaType().getMediaTypeId());
								
								if (QGroup.getQuesGroupMediaLinks().getMediaURLText() != null) {
									questionGroupObj.put("MED", QGroup.getQuesGroupMediaLinks().getMediaURLText());
								}
								JSONArray quesArr = new JSONArray();
				
								@SuppressWarnings("unused")
								int kl = 0;
								
								Set<Question> questions = new LinkedHashSet<Question>(QGroup.getQuestions());
								
								for (Question question : questions) {
				
									JSONObject quesObj = new JSONObject();
									quesObj.put("QC", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
									quesObj.put("QID", question.getQuestionId());
									quesObj.put("ANSTYP", question.getAnswerType().getAnswerTypeId());

									quesObj.put("QSOLTYPE", question.getSolType());
									quesObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
									quesObj.put("QSOLMEDIA", question.getSolMedia());
									
									quesArr.put(quesObj);
				
									JSONArray answerArr = new JSONArray();//
									
									if (question.getAnswers().size() > 4) {
										List<Answers> answers = getFourOptionsToResumeTest(question.getAnswers(),
												question.getAnswerType().getAnswerTypeId());
				
										for (Answers answer : answers) {
											JSONObject ansObj = new JSONObject();
											if (question.getAnswerType().getAnswerTypeId() == 5 || question.getAnswerType().getAnswerTypeId() == 8) {
												
												List<TestInstance> instance = testInstanceDao.getTestInstanceListByQuesionIdAndUserId(question.getQuestionId(),QGroup.getQuestionGroupId(), user.getUserId(),testInstanceState.getTestInstanceStateId());
												
												for (TestInstance testInstance2 : instance) {
													
													ansObj.put("TI_ID", testInstance2.getTestInstanceId());
													
												}
												
												ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
												
												ansObj.put("ANSID", answer.getAnswersId());
												ansObj.put("RANS", answer.isRightAnswer()); // newly added
												ansObj.put("ansMedia", answer.getMedia());
												answerArr.put(ansObj);
				
											} else {
												
												ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
												ansObj.put("ANSID", answer.getAnswersId());
												ansObj.put("RANS", answer.isRightAnswer()); // newly added
												ansObj.put("ansMedia", answer.getMedia());
												
												answerArr.put(ansObj);
											}
										}
										
									} else {
										
										int  j = 0;
										for (Answers answer : question.getAnswers()) {
											JSONObject ansObj = new JSONObject();
											int k = 0;
											if (question.getAnswerType().getAnswerTypeId() == 5 || question.getAnswerType().getAnswerTypeId() == 8) {
												
												List<TestInstance> instance = testInstanceDao.getTestInstanceListByQuesionIdAndUserId(question.getQuestionId(), QGroup.getQuestionGroupId(), user.getUserId(),testInstanceState.getTestInstanceStateId());
												
												Map<Integer, Long> map =  new HashMap<Integer, Long>();
												
												for (TestInstance testInstance2 : instance) {
													
													map.put(k++,  testInstance2.getTestInstanceId());
													
												}
												
												ansObj.put("TI_ID", map.get(j));
												j++;
											
												ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
												ansObj.put("ANSID", answer.getAnswersId());
												ansObj.put("RANS", answer.isRightAnswer()); // newly added
												ansObj.put("ansMedia", answer.getMedia());
												answerArr.put(ansObj);
				
											} else {
												
												ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
												ansObj.put("ANSID", answer.getAnswersId());
												ansObj.put("RANS", answer.isRightAnswer()); // newly added
												ansObj.put("ansMedia", answer.getMedia());

												answerArr.put(ansObj);
											}
										}
									}
				
									if (question.getAnswerType().getAnswerTypeId() != 5) {
										
										if (question.getAnswerType().getAnswerTypeId() != 8) {
											
											TestInstance instance = testInstanceDao.getTestInstanceByQuesionIdAndUserId(question.getQuestionId(),QGroup.getQuestionGroupId(), user.getUserId(),testInstanceState.getTestInstanceStateId());
											quesObj.put("TI_ID", instance.getTestInstanceId());
											
										}
									}
				
									quesObj.put("ANS", answerArr);
									
									int k = 0;
									JSONArray jArray = new JSONArray();
									
											if(question.getAnswerType().getAnswerTypeId() == 5 || question.getAnswerType().getAnswerTypeId() == 8){
												
												List<TestInstance> instance = testInstanceDao.getTestInstanceListByQuesionIdAndUserId(question.getQuestionId(), QGroup.getQuestionGroupId(), user.getUserId(),testInstanceState.getTestInstanceStateId());
												
												Map<Integer, Long> map =  new HashMap<Integer, Long>();
												
												
												JSONObject json  = new JSONObject();	
												for (TestInstance testInstance2 : instance) {
													
													map.put(k++,  testInstance2.getAnswersId());
													json.put("GANSID"+k, testInstance2.getAnswersId());
													
												}
												
												jArray.put(json);
												quesObj.put("GANSID",jArray);
												kl++;
												
											}else{
												
												TestInstance instance = testInstanceDao.getTestInstanceByQuesionIdAndUserId(question.getQuestionId(), QGroup.getQuestionGroupId(), user.getUserId(),testInstanceState.getTestInstanceStateId());
												quesObj.put("GANSID", instance.getAnswersId()); // given ans id
												
											}
									
									
									}
								questionGroupObj.put("QUES", quesArr);
								questionGroupsArr.put(questionGroupObj);
							}
							
							
							String[] CT = testInstanceState.getCurrentTestTime().split("-");// currentTime for resume test
							
							if(CT.length == 1){
								String[] temp = new String[2];
								int updateTime =  (int) ( saveTestConfigData.get(topics[i]));
//								System.out.println(updateTime);	
								int timeToConvert =  updateTime;
								timeToConvert = timeToConvert * 60;
								String FinalTime = timeToConvert+"-"+topics[i];
								testInstanceState.setCurrentTestTime(FinalTime);
								temp[0] = String.valueOf(timeToConvert);
								temp[1] = topics[i];
								testStateDao.update(testInstanceState);
								
								if(topics[i].equals(temp[1])){
									
									topicObj.put("time" + topics[i],Integer.parseInt(temp[0]));
									
									topicObj.put("RTime" + topics[i],(Integer.parseInt(temp[0]) / 60));
									
									currentTopic = temp[1];
									
								}else{
									
									topicObj.put("time" + topics[i], saveTestConfigData.get(topics[i]));
									topicObj.put("RTime" + topics[i], saveTestConfigData.get(topics[i]));
									
								}
								
							}else {
								
								if(topics[i].equals(CT[1])){
									
									topicObj.put("time" + topics[i],Integer.parseInt(CT[0]));
									
									topicObj.put("RTime" + topics[i],(Integer.parseInt(CT[0]) / 60));
									
									currentTopic = CT[1];
									
								}else{
									
									topicObj.put("time" + topics[i], saveTestConfigData.get(topics[i]));
									topicObj.put("RTime" + topics[i], saveTestConfigData.get(topics[i]));
									
								}
							}
							
					} else {
						data.put("done", false);
						data.put("msg", "No Test has configured for this topic Id and test Id");
				
						return data;
					}
				
//					topicObj.put("TOPIC" + topics[i], questionGroupsArr);
//					questionGroupsArrForAllTopics.put(questionGroupsArr);
//					topicIds =  topics[i]+",";
//					topObjForAllTopics.put("TOPICIDS", topicIds);
					
					String topicName = "";
					topicName =  getParentTopicNameBytopicId(Long.parseLong(topics[i]));
					
//					data.put("topics", topicName);
					data.put("topics" + topics[i], topicName);
					
					topicObj.put("TOPIC" + topics[i], questionGroupsArr);
					topicObj.put("TOPICID", topics[i]);
						
					topObj.put(topics[i] + "", Constants.topics.topicsMap.get(Long.parseLong(topics[i])));
					
					data.put("TISID", testInstanceState.getTestInstanceStateId());
					data.put("STD", testInstanceState.getTest().getStandard().getName());
					data.put("topObj", topObj);
					data.put("topicObj", topObj);
				
					data.put("data", topicObj);
					
					data.put("emailId", testInstanceState.getUser().getEmailId());
					data.put("testLevel", testInstanceState.getTestLevel());
					data.put("stdId", testInstanceState.getTest().getStandard().getStdId());
					data.put("TOPICS", topics);
					
					data.put("CURRSEC", currentTopic);
					data.put("TETRESUME", testEndTime);
					data.put("RESUMECOUNT", resumeCount);
					data.put("RESUME", true);
					data.put("done", true);
				}
		}
	}else {
		data.put("done", false);
//		data.put("msg", "page refreshed condition");
	}
		return data;
	}

}
