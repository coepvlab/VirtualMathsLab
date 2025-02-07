package in.ac.coep.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.constants.Constants;
import in.ac.coep.constants.Constants.sysConfig;
import in.ac.coep.dao.QuestionGroupDao;
import in.ac.coep.dao.TestConfigurationDao;
import in.ac.coep.dao.TestDao;
import in.ac.coep.dao.TestInstanceStateDao;
import in.ac.coep.dao.TopicDao;
import in.ac.coep.dao.TopicMappingDao;
import in.ac.coep.entity.QGComplexityLevel;
import in.ac.coep.entity.QuestionGroup;
import in.ac.coep.entity.Standard;
import in.ac.coep.entity.Test;
import in.ac.coep.entity.TestConfiguration;
import in.ac.coep.entity.TestInstanceState;
import in.ac.coep.entity.TestType;
import in.ac.coep.entity.Topic;
import in.ac.coep.entity.TopicMapping;
import in.ac.coep.entity.User;
import in.ac.coep.service.TestService;
import in.ac.coep.vo.TestVO;

/**
 * @author Prashant
 *
 * 
 */
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;
	
//	@Autowired
//	private TestConfigurationService testConfigurationService;
	
	@Autowired
	private TestConfigurationDao testConfigurationDao;

	@Autowired
	private QuestionGroupServiceImpl questionGroupServiceImpl;
	
	@Autowired
	private TestInstanceStateDao instanceStateDao;
	
	@Autowired
	private QuestionGroupDao questionGroupDao;
	
	@Autowired
	private TopicDao topicDao;
	
	@Autowired
	private TopicMappingDao topicMappingDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.service.TestService#configureTest(in.ac.coep.vo.TestVO)
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public JSONObject configureTestPaper(TestVO testVO, User user) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		TestInstanceState testInstanceStateToSetLevelCompleteFlag = new TestInstanceState();
		
		if (testVO != null) {
			
			Test test = new Test();
			
			if(testVO.getTestType().getTestTypeId() == 1) { // for TestType - practice test only
				
				List<TestInstanceState> testInstanceState = instanceStateDao.getTestInstanceStateByUserIdAndTopicId(user.getUserId(), testVO.getSelectedTopics(), testVO.getTestType().getTestTypeId()); // this is to check whether user has given any test on topic

				if (testInstanceState.isEmpty()) {
					
					String varType = "1"; // since the testinstancestate is empty by default vartype will be 1
					int complLevel = 1;
					data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
					
					return data;	
					
				}else {
					
					
					for (Iterator iterator = testInstanceState.iterator(); iterator.hasNext();) {
						TestInstanceState testInstanceStateLastGivenTest = (TestInstanceState) iterator.next();
						 if (!iterator.hasNext()) {
					            //last name 
//							 testInstanceState2.set
							 System.out.println(testInstanceStateLastGivenTest.getTestInstanceStateId());
							 testInstanceStateToSetLevelCompleteFlag = testInstanceStateLastGivenTest;
					        }
					}
					
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
					
					for (TestInstanceState testInstanceState2: testInstanceState) {
						
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
					
					
					boolean getcheckForDODWithOnlyOneVariation = checkForDODWithOnlyOneVariation(testInstanceStateToSetLevelCompleteFlag.getComplexityLevel(), testInstanceStateToSetLevelCompleteFlag.getTest().getTestId());
					
//					List<QuestionGroup> questionGroupsByComlLevel =  getQuestionsBycomplLevel(Long.parseLong(testInstanceStateToSetLevelCompleteFlag.getTest().getSelectedTopics()), 1); // 1 is to check weather DOD 1 has no questions
//					
//					if (questionGroupsByComlLevel.isEmpty()) {
//							compLevelOnePassCntVarType1 = 2;
//					}
					
										
					if (testInstanceStateToSetLevelCompleteFlag.getComplexityLevel() == 1) {
						if (getcheckForDODWithOnlyOneVariation) {
							compLevelOnePassCntVarType2 = 2;
						}
					}else if(testInstanceStateToSetLevelCompleteFlag.getComplexityLevel() == 2) {
						compLevelOnePassCntVarType1 = 2; // this might be need to change for all of this if else
						compLevelOnePassCntVarType2 = 2;
						if (getcheckForDODWithOnlyOneVariation) {
							compLevelTwoPassCntVarType2 = 2;
						}
					}else if(testInstanceStateToSetLevelCompleteFlag.getComplexityLevel() == 3) {
						compLevelOnePassCntVarType1 = 2;
						compLevelOnePassCntVarType2 = 2;
						compLevelTwoPassCntVarType2 = 2;
						if (getcheckForDODWithOnlyOneVariation) {
							compLevelThreePassCntVarType2 = 2;
						}
					}else if(testInstanceStateToSetLevelCompleteFlag.getComplexityLevel() == 4) {
						compLevelOnePassCntVarType1 = 2;
						compLevelOnePassCntVarType2 = 2;
						compLevelTwoPassCntVarType2 = 2;
						compLevelThreePassCntVarType2 = 2;
						if (getcheckForDODWithOnlyOneVariation) {
							compLevelFourPassCntVarType2 = 2;
						}
					}else if(testInstanceStateToSetLevelCompleteFlag.getComplexityLevel() == 5) {
						compLevelOnePassCntVarType1 = 2;
						compLevelOnePassCntVarType2 = 2;
						compLevelTwoPassCntVarType2 = 2;
						compLevelThreePassCntVarType2 = 2;
						compLevelFourPassCntVarType2 = 2;
						if (getcheckForDODWithOnlyOneVariation) {
							compLevelFivePassCntVarType2 = 2;
						}
					}else if(testInstanceStateToSetLevelCompleteFlag.getComplexityLevel() == 6) {
						compLevelOnePassCntVarType1 = 2;
						compLevelOnePassCntVarType2 = 2;
						compLevelTwoPassCntVarType2 = 2;
						compLevelThreePassCntVarType2 = 2;
						compLevelFourPassCntVarType2 = 2;
						compLevelFivePassCntVarType2 = 2;
						if (getcheckForDODWithOnlyOneVariation) {
							if (compLevelFinalPassCntVarType >= 2) { // before it was 3
								System.out.println(compLevelFinalPassCntVarType);
							}else {
								compLevelFinalPassCntVarType = 2;
							}
							
						}
					}
					
					if (flag != true) {
						
						if (compLevelOnePassCntVarType1 < 2) {
							 String varType = "1";
							 int complLevel = 1;
//							 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
							 System.out.println(compLevelOneFailCntVarType1);
							 String[] selectedTopics = testVO.getSelectedTopics().split(",");
							 
									JSONObject totalQuestion = new JSONObject();
									totalQuestion = questionGroupServiceImpl
											.fetchLevelWiseCountOfQuestionGroupsByTopics(Long.parseLong((selectedTopics[0])));

									if (totalQuestion.get("TOTALQG").equals("0")) {
										
//										data.put("done", false);
//										data.put("msg", "Under development..");
//										compLevelOnePassFailCntVarType2++;
										compLevelOnePassCntVarType1 = 2;
										compLevelOnePassCntVarType2 = 2;
										if (!(compLevelTwoPassCntVarType1 == 2)) {
											compLevelTwoPassCntVarType1 = 1;
										}
										
									}else {
										 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
									}

						}else if (compLevelOnePassCntVarType1 == 2 && compLevelOnePassCntVarType2 < 2 ){  
							 String varType = "2";
							 int complLevel = 1;
							 System.out.println(compLevelOneFailCntVarType2);
//							 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
							 
							 String[] selectedTopics = testVO.getSelectedTopics().split(",");
							 
									JSONObject totalQuestion = new JSONObject();
									totalQuestion = questionGroupServiceImpl
											.fetchLevelWiseCountOfQuestionGroupsByTopics(Long.parseLong((selectedTopics[0])));

									if (totalQuestion.get("TOTALQG").equals("0")) {
										
										data.put("done", false);
										data.put("msg", "No questions in the database to go for the selected topic");
										
									}else {
										 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
									}
						}
						 
						if (compLevelOnePassCntVarType1 == 2 && compLevelOnePassCntVarType2 == 2 && compLevelTwoPassCntVarType1 < 2) { // it means student have passed the compLevel one
							 String varType = "1";
							 int complLevel = 2;
							 
//							 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
							 
							 String[] selectedTopics = testVO.getSelectedTopics().split(",");
							 
									
									JSONObject totalQuestion = new JSONObject();
									totalQuestion = questionGroupServiceImpl
											.fetchLevelWiseCountOfQuestionGroupsByTopics(Long.parseLong((selectedTopics[0])));

									if (totalQuestion.get("TOTALQG").equals("0")) {
										
//										data.put("done", false);
//										data.put("msg", "Under development..");
										compLevelTwoPassCntVarType2 = 2;
//										compLevelThreePassFailCntVarType1 = 1;
										if (!(compLevelThreePassCntVarType1 == 2)) {
											compLevelThreePassCntVarType1 = 1;
										}
									}else {
										JSONArray temp = new JSONArray();
										temp =  (JSONArray) totalQuestion.get("data");
										
										boolean lvlFlag = false;
										for (Object object : temp) {
											JSONObject tt = new JSONObject(object.toString());
											try {
												if (tt.get("LVL2") == null) {
													lvlFlag = true;
												}else {
													lvlFlag = false;
													 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
													break;
												}
											} catch (JSONException e) {
												// TODO Auto-generated catch block
//												compLevelTwoPassFailCntVarType2++;
												lvlFlag = true;
												e.printStackTrace();
											}
//											System.out.println(tt.get("LVL2"));
//											"LVL"+objects[0]
										}
										if (lvlFlag) {
											compLevelTwoPassCntVarType1 = 2;
											compLevelTwoPassCntVarType2 = 2;
//											compLevelThreePassFailCntVarType1 = 1;
											if (!(compLevelThreePassCntVarType1 == 2)) {
												compLevelThreePassCntVarType1 = 1;
											}
											System.out.println("LVL2 not found");
										}
//										 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
									}

						}else if(compLevelTwoPassCntVarType1 == 2 && compLevelTwoPassCntVarType2 < 2) {
							 String varType = "2";
							 int complLevel = 2;
//							 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
							 
							 String[] selectedTopics = testVO.getSelectedTopics().split(",");
							 
									
									JSONObject totalQuestion = new JSONObject();
									totalQuestion = questionGroupServiceImpl
											.fetchLevelWiseCountOfQuestionGroupsByTopics(Long.parseLong((selectedTopics[0])));

									if (totalQuestion.get("TOTALQG").equals("0")) {
										
										data.put("done", false);
										data.put("msg", "Under development..");
										
									}else {
										 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
									}
							 System.out.println("Move to next complextiy Level");
						}
						
						if (compLevelTwoPassCntVarType1 == 2 && compLevelTwoPassCntVarType2 == 2 && compLevelThreePassCntVarType1 < 2) {
							String varType = "1";
							 int complLevel = 3;
//							 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
							 
							 String[] selectedTopics = testVO.getSelectedTopics().split(",");
							 
									
									JSONObject totalQuestion = new JSONObject();
									totalQuestion = questionGroupServiceImpl
											.fetchLevelWiseCountOfQuestionGroupsByTopics(Long.parseLong((selectedTopics[0])));

									if (totalQuestion.get("TOTALQG").equals("0")) {
										
//										data.put("done", false);
//										data.put("msg", "Under development..");
										compLevelThreePassCntVarType2 = 2;
										if (!(compLevelFourPassCntVarType1 == 2)) {
											compLevelFourPassCntVarType1 = 1;
										}
									}else {
										JSONArray temp = new JSONArray();
										temp =  (JSONArray) totalQuestion.get("data");
										boolean lvlFlag = false;
										for (Object object : temp) {
											JSONObject tt = new JSONObject(object.toString());
											try {
												if (tt.get("LVL3") == null) {
													lvlFlag = true;
												}else {
													lvlFlag = false;
													 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
													 break;
												}
											} catch (JSONException e) {
												// TODO Auto-generated catch block
//												compLevelTwoPassFailCntVarType2++;
												lvlFlag = true;
												
												e.printStackTrace();
											}
//											System.out.println(tt.get("LVL2"));
//											"LVL"+objects[0]
										}
										
										if (lvlFlag == true) {
											compLevelThreePassCntVarType1 = 2;
											compLevelThreePassCntVarType2 = 2;
											if (!(compLevelFourPassCntVarType1 == 2)) {
												compLevelFourPassCntVarType1 = 1;
											}
											
											System.out.println("LVL3 not found");
										}
//										 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
									}
							 
							 
						}else if(compLevelThreePassCntVarType1 == 2 && compLevelThreePassCntVarType2 < 2) {
							 String varType = "2";
							 int complLevel = 3;
							 
//							 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
							 
							 String[] selectedTopics = testVO.getSelectedTopics().split(",");
							 
									JSONObject totalQuestion = new JSONObject();
									totalQuestion = questionGroupServiceImpl
											.fetchLevelWiseCountOfQuestionGroupsByTopics(Long.parseLong((selectedTopics[0])));

									if (totalQuestion.get("TOTALQG").equals("0")) {
										
										data.put("done", false);
										data.put("msg", "Under development..");
										
									}else {
										 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
									}
							 
							 System.out.println("Move to next complextiy Level");
						}
						
						if (compLevelThreePassCntVarType1 == 2 && compLevelThreePassCntVarType2 == 2 && compLevelFourPassCntVarType1 < 2) {
							String varType = "1";
							 int complLevel = 4;
							 
//							 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
							 
							 String[] selectedTopics = testVO.getSelectedTopics().split(",");
							 
									JSONObject totalQuestion = new JSONObject();
									totalQuestion = questionGroupServiceImpl
											.fetchLevelWiseCountOfQuestionGroupsByTopics(Long.parseLong((selectedTopics[0])));

									if (totalQuestion.get("TOTALQG").equals("0")) {
										
//										data.put("done", false);
//										data.put("msg", "Under development..");
										compLevelFourPassCntVarType2 = 2;
//										compLevelFivePassFailCntVarType1 = 1;
										
										if (!(compLevelFivePassCntVarType1 == 2)) {
											compLevelFivePassCntVarType1 = 1;
										}
									}else {
										JSONArray temp = new JSONArray();
										temp =  (JSONArray) totalQuestion.get("data");
										boolean lvlFlag = false;
										for (Object object : temp) {
											JSONObject tt = new JSONObject(object.toString());
											try {
												if (tt.get("LVL4") == null) {
													lvlFlag = true;
												}else {
													lvlFlag = false;
													 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
													 break;
												}
											} catch (JSONException e) {
												// TODO Auto-generated catch block
//												compLevelTwoPassFailCntVarType2++;
												lvlFlag = true;
												
												e.printStackTrace();
											}
//											System.out.println(tt.get("LVL2"));
//											"LVL"+objects[0]
										}
										
										if (lvlFlag == true) {
											compLevelFourPassCntVarType1 = 2;
											compLevelFourPassCntVarType2 = 2;
											
//											compLevelFivePassFailCntVarType1 = 1;
											if (!(compLevelFivePassCntVarType1 == 2)) {
												compLevelFivePassCntVarType1 = 1;
											}
											System.out.println("LVL4 not found");
										}
//										 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
									}
							 
							 
						}else if(compLevelFourPassCntVarType1 == 2 && compLevelFourPassCntVarType2 < 2) {
							 String varType = "2";
							 int complLevel = 4;
							 
//							 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
							 
							 String[] selectedTopics = testVO.getSelectedTopics().split(",");
							 
									
									JSONObject totalQuestion = new JSONObject();
									totalQuestion = questionGroupServiceImpl
											.fetchLevelWiseCountOfQuestionGroupsByTopics(Long.parseLong((selectedTopics[0])));

									if (totalQuestion.get("TOTALQG").equals("0")) {
										
										data.put("done", false);
										data.put("msg", "Under development..");
										
									}else {
										 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
									}
		
							 System.out.println("Move to next complextiy Level");
						}
						
						if (compLevelFourPassCntVarType1 == 2 && compLevelFourPassCntVarType2 == 2 && compLevelFivePassCntVarType1 < 2) {
							String varType = "1";
							 int complLevel = 5;
//							 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
							 
							 
							 String[] selectedTopics = testVO.getSelectedTopics().split(",");
							 
									JSONObject totalQuestion = new JSONObject();
									totalQuestion = questionGroupServiceImpl
											.fetchLevelWiseCountOfQuestionGroupsByTopics(Long.parseLong((selectedTopics[0])));

									if (totalQuestion.get("TOTALQG").equals("0")) {
										
//										data.put("done", false);
//										data.put("msg", "Under development..");
										compLevelFivePassCntVarType2 = 2;
//										compLevelFinalPassFailCntVarType = 1;
										if (!(compLevelFinalPassCntVarType == 2)) {
											compLevelFinalPassCntVarType = 1;
										}
									}else {
										JSONArray temp = new JSONArray();
										temp =  (JSONArray) totalQuestion.get("data");
										boolean lvlFlag = false;
										for (Object object : temp) {
											JSONObject tt = new JSONObject(object.toString());
											try {
												if (tt.get("LVL5") == null) {
													lvlFlag = true;
												}else {
													lvlFlag = false;
													 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
													 break;
												}
											} catch (JSONException e) {
												// TODO Auto-generated catch block
//												compLevelTwoPassFailCntVarType2++;
												lvlFlag = true;
												
												e.printStackTrace();
											}
//											System.out.println(tt.get("LVL2"));
//											"LVL"+objects[0]
										}
										
										if (lvlFlag == true) {
											compLevelFivePassCntVarType1 = 2;
											compLevelFivePassCntVarType2 = 2;
//											compLevelFinalPassFailCntVarType = 1;
											if (!(compLevelFinalPassCntVarType == 2)) {
												compLevelFinalPassCntVarType = 1;
											}
											System.out.println("LVL5 not found");
										}
//										 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
									}
							 
						}else if(compLevelFivePassCntVarType1 == 2 && compLevelFivePassCntVarType2 < 2) {
							 String varType = "2";
							 int complLevel = 5;
//							 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
							 
							 String[] selectedTopics = testVO.getSelectedTopics().split(",");
							 
									JSONObject totalQuestion = new JSONObject();
									totalQuestion = questionGroupServiceImpl
											.fetchLevelWiseCountOfQuestionGroupsByTopics(Long.parseLong((selectedTopics[0])));

									if (totalQuestion.get("TOTALQG").equals("0")) {
										
										data.put("done", false);
										data.put("msg", "Under development..");
										
									}else {
										 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);
									}
									
							 System.out.println("Move to next complextiy Level");
						}
						
						if (compLevelFivePassCntVarType1 == 2 && compLevelFivePassCntVarType2 == 2 && compLevelFinalPassCntVarType < 3) {
							
							
							 System.out.println("Move to next Level and create test on all levels");
							 String varType = "ALL";
							 int complLevel = 6; // this indicates all test passed and will have test paper on all complexity Levels and all variation
							 
							 String[] selectedTopics = testVO.getSelectedTopics().split(",");
							 
								JSONObject totalQuestion = new JSONObject();
								totalQuestion = questionGroupServiceImpl
										.fetchLevelWiseCountOfQuestionGroupsByTopics(Long.parseLong((selectedTopics[0])));
								totalQuestion.put("TOTALQG", 0);  //Line added to avoid 6th level and to finish the topic on 5th level
								if (totalQuestion.get("TOTALQG").equals("0")) {
									
									compLevelFinalPassCntVarType = 3;
									
								}else {
									JSONArray temp = new JSONArray();
									temp =  (JSONArray) totalQuestion.get("data");
									boolean lvlFlag = false;
									for (Object object : temp) {
										JSONObject tt = new JSONObject(object.toString());
										try {
											if (tt.get("LVL5") == null) {
												lvlFlag = true;
											}else {
												lvlFlag = false;
//												 data = createTestPaperForPracticeTest(testVO, user, test, varType, complLevel);//Line commented to avoid test creation for level 6 and to finish the topic on 5th level
												lvlFlag = true; //Line added to avoid 6th level and to finish the topic on 5th level
												break;
											}
										} catch (JSONException e) {
											lvlFlag = true;
											
											e.printStackTrace();
										}
									}
									
									if (lvlFlag == true) {
										compLevelFinalPassCntVarType = 2; // before it was 3
										System.out.println("last LVL not found");
									}
								}
							 System.out.println("Move to next complextiy Level");
							 
						}
						
						if (compLevelFinalPassCntVarType == 2) {  // before it was 3
							System.out.println("END OF ALL TEST, MOVE TO THE NEXT STEP");
							data.put("done", false);
							
							if (!(testInstanceStateToSetLevelCompleteFlag.isLevelComplete())) {
								
								testInstanceStateToSetLevelCompleteFlag.setLevelComplete(true);
								instanceStateDao.update(testInstanceStateToSetLevelCompleteFlag);
							}
							
							data.put("msg", "Congratulations.. You have successfully cleared all stages for this topic, We will move to the next topic <br> अभिनंदन!!! या विभागातील सर्व पायऱ्या तुम्ही पूर्ण केलाय आहेत. आता पुढच्या विभागात प्रवेश करूयात ");
						}
						

					}else {
						
						return data;
					}
					
				
					return data; // this will change
				}
				
			}else if (testVO.getTestType().getTestTypeId() == 5) {  //  for other TestTypes 
				
				test = copyTestVOToTest(test, testVO);
				test.setCreatedBy(user.getUserId()+"-"+user.getFirstName() +" "+ user.getLastName());
				

				JSONObject totalQuestion = new JSONObject();
				JSONArray totalQuestionArray = new JSONArray();
				String[] selectedTopics = testVO.getSelectedTopics().split(",");
				String[] selectedVarNosFinal = null;
				
				if (testVO.getTestType().getTestTypeId() == 1) {
					for (int i = 0; i < selectedTopics.length; i++) {
						
						totalQuestion = questionGroupServiceImpl
								.fetchLevelWiseCountOfQuestionGroupsByTopics(Long.parseLong((selectedTopics[i])));

						totalQuestion.put("TID", selectedTopics[i]);

						totalQuestionArray.put(totalQuestion);
					}
				}else if (testVO.getTestType().getTestTypeId() == 5) {
					
					String[] selectedVarNo = testVO.getVarNo().split(",");
					
					int len = 0;
					for (int i = 0; i < selectedTopics.length; i++) {
						
						String[] selectedVarNosF = new String[selectedVarNo.length];
						
						for (int j = 0; j < selectedVarNo.length; j++) {
							
							Topic topic = topicDao.getTopicByTopicId(Long.parseLong((selectedTopics[i])));
							String[] selectedVarNos = selectedVarNo[j].split(" - "+topic.getTopicNo());
//							System.out.println(selectedVarNos);
							if (selectedVarNos[0].contains(" - ")) {
//								System.out.println(selectedVarNos[0]);
							}else {
								len++;
								selectedVarNosF[j] =  selectedVarNos[0].trim();
							}
							
						}
//						System.out.println(selectedVarNosF);
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
//						System.out.println(selectedVarNosFinal);
						totalQuestion = questionGroupServiceImpl
								.fetchVarNOWiseCountOfQuestionGroupsByTopics(Long.parseLong((selectedTopics[i])), selectedVarNosFinal);

						totalQuestion.put("TID", selectedTopics[i]);

						totalQuestionArray.put(totalQuestion);
					}
				}
				
				String[] selectedVarNo = testVO.getVarNo().split(",");
				String[] selectedVarNos = new String[selectedVarNo.length];
				
				for (int i = 0; i < selectedVarNo.length; i++) {
					String[] selectedVarNo2 = selectedVarNo[i].split(" - ");
					selectedVarNos[i] = selectedVarNo2[0];
				}
				
				System.out.println(selectedVarNos);
//				String[] selectedVarNosTosave = null;
				
				 StringJoiner joinNames = new StringJoiner(","); 
				 for (int i = 0; i < selectedVarNos.length; i++) {
					  joinNames.add(selectedVarNos[i]);
				}
				
				  
//				test.setVarNo(joinNames.toString());
				test.setVarNo(testVO.getVarNo());
				test.setVarType("Other");
				
				long testId = testDao.configureTestPaper(test);
			

				data.put("done", true);
				data.put("data", totalQuestionArray);
				data.put("TID", testId);
				data.put("msg", Constants.testcreationsuccess);

				return data;
			}
					

		
		} else {
			data.put("done", false);
			data.put("msg", Constants.testcreationfail);
			return data;
		}
		
		return data;
	}

	@SuppressWarnings("unused")
	private long getNextTopicAfterAllTestDone(String selectedTopics) throws Exception {
		// TODO Auto-generated method stub
		
		long nextTopicId = 0l;
		long topicId = Long.parseLong(selectedTopics);
//		System.out.println(topicId);
		TopicMapping topic = topicDao.getParentTopicsByTopicId(topicId); // first get the parent topic 
		
		List<TopicMapping> topicMapping = topicMappingDao.getTopicChildsByTopicId(topic.getParent().getTopicId());
		
		if (topicMapping.size() == 1) {
			
		}else {
			
			TopicMapping mapping = topicMapping.get(topicMapping.size()-1);
			
			if (mapping.getTopic().getTopicId() == topicId) { // this means if current topic is last in the subtopic
				System.out.println(mapping.getTopic().getTopicId());
			}else {
				
				int index = 0;
				for (TopicMapping topicMapping2 : topicMapping) {
					
					if(topicMapping2.getTopic().getTopicId() == topicId) {
						index = topicMapping.indexOf(topicMapping2);
					}
				}
				
				TopicMapping nextTM =  topicMapping.get((index + 1));
				nextTopicId = nextTM.getTopic().getTopicId();
			}
			
			
		}
		
		return nextTopicId;
	}

	@SuppressWarnings("unused")
	private boolean checkForDODWithOnlyOneVariation(int levelNo, long testId) throws Exception {
		// TODO Auto-generated method stub
		
		// code to get whether there is only one variation for the DoD start
		 boolean ifOnlyOneVariationAvailableInDOD = false;
		 
		 Test test = testDao.getTestByTestId(testId);
		 
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
			
			List<Integer> varNoList = new ArrayList<>(varNO_Set);
	        Collections.sort(varNoList);
	        
	        System.out.println(varNoList);
	        System.out.println("size -"+varNoList.size());
	        
	        Object[] varNoArray =  varNoList.toArray();
	        
	        Object[] varNoSetArray1 = {};
	        Object[] varNoSetArray2 = {};
	        
			if (varNoList.size() == 1) {

				ifOnlyOneVariationAvailableInDOD = true;

			} else {

				ifOnlyOneVariationAvailableInDOD = false;
				System.out.println("v22 " + varNoSetArray2);
			}
		}
		
		// code to get whether there is only one variation for the DoD end
		return ifOnlyOneVariationAvailableInDOD;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.service.TestService#modifyTestPaper(in.ac.coep.vo.TestVO)
	 */
	@Override
	public JSONObject modifyTestPaper(TestVO testVO,User user) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		if (testVO != null) {

			Test test = testDao.getTestByTestId(testVO.getTestId());

			test = copyTestVOToTest(test, testVO);
			test.setCreatedBy(user.getFirstName() +" "+ user.getLastName());
			testDao.modifyTestPaper(test);

			long testId = testVO.getTestId();

			data.put("done", true);
			data.put("TID", testId);
			data.put("msg", Constants.testcreationsuccess);

			return data;
		} else {

			data.put("done", false);
			data.put("msg", Constants.testcreationfail);
			return data;
		}
	}

	private Test copyTestVOToTest(Test test, TestVO testVO) {

		test.setActive(true);
		
		test.setUpdatedTime(System.currentTimeMillis());
		
		Standard standard = new Standard();
		standard.setStdId(testVO.getStandard().getStdId());
		test.setStandard(standard);
		
		
		TestType testType = new TestType();
		testType.setTestTypeId(testVO.getTestType().getTestTypeId());
		test.setTestType(testType);
		
		Calendar sd = Calendar.getInstance();
		sd.setTime(testVO.getStartDate());
		test.setStartDate(sd.getTime());
		
		
		Calendar ed = Calendar.getInstance();
		ed.setTime(testVO.getEndDate());
		ed.add(Calendar.YEAR, 15);
		ed.add(Calendar.HOUR, 720);
		ed.add(Calendar.MINUTE, 30);
		test.setEndDate(ed.getTime());

		if (testVO.getTestType().getTestTypeId() == 1) {
			test.setName(testVO.getName().trim()+" - Practice Test");
		}else {
			test.setName(testVO.getName().trim());
		}
		
		test.setSelectedTopics(testVO.getSelectedTopics());
		test.setCreationTime(System.currentTimeMillis());
		test.setTestLevel(testVO.getTestLevel());
		
		return test;
	}

	@Override
	public JSONObject getAvailableTestForLoggedInUser(Long userId) throws Exception {

		return null;
	}
	
	
	private JSONObject createTestPaperForPracticeTest(TestVO testVO, User user, Test test, String varType, int complLevel) throws Exception {
		
		JSONObject data = new JSONObject();
		
		JSONObject totalQuestion = new JSONObject();
		String[] selectedTopics = testVO.getSelectedTopics().split(",");
		
		test = testDao.getTestForPracticeTestByTopicId(selectedTopics[0], varType, complLevel); 
		
		if(test == null) {

				for (int i = 0; i < selectedTopics.length; i++) {

					totalQuestion = questionGroupServiceImpl
							.fetchLevelWiseCountOfQuestionGroupsByTopics(Long.parseLong((selectedTopics[i])));

					if (totalQuestion.get("TOTALQG").equals("0")) {
						
						data.put("done", false);
						data.put("msg", "Under development..");
						
					}else {
						List<QuestionGroup> questionGroups = null;
						if (complLevel == 6) {
							Object[] complLevelArr = {1,2,3,4,5};
							questionGroups = getQuestionsBycomplLevelForAllLevel(Long.parseLong((selectedTopics[i])), complLevelArr);
							
							if (questionGroups.isEmpty()) {
								data.put("done", false);
								data.put("msg", "Under development..");
								return data; 
								
							}else {
								
							}
						}else {
							 questionGroups = getQuestionsBycomplLevel(Long.parseLong((selectedTopics[i])), complLevel);
							 if (complLevel == 1 && questionGroups.isEmpty()) {
								 complLevel++;
								 questionGroups = getQuestionsBycomplLevel(Long.parseLong((selectedTopics[i])), complLevel);
							}
							if (questionGroups.isEmpty()) {
								data.put("done", false);
								data.put("msg", "Under development..");
								return data; 
								
							}else {
								
							}
						}
						
						Set<Integer> varNO_Set = new HashSet<Integer>();
						
						for (QuestionGroup questionGroup : questionGroups) {
							
							if (complLevel != 6) {
								if (questionGroup.getComplexityLevel().getQgComplexityLevelId() == complLevel) {
									if (questionGroup.getVarNo() != null) {
										varNO_Set.add(Integer.parseInt(questionGroup.getVarNo()));
									}
								}
							}else if (complLevel == 6)  {
								if (questionGroup.getComplexityLevel().getQgComplexityLevelId() == 5 || questionGroup.getComplexityLevel().getQgComplexityLevelId() == 4) {
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
				        
				        JSONObject data1111 = twoVariationFilter(varNoArray);
				       
				        
				        if (varNoList.size() == 1) {
				        	 varNoSetArray1 =  varNoArray;
				        	 System.out.println("v1 "+varNoSetArray1);
					         varNoSetArray2 = varNoArray;
					         System.out.println("v2 "+varNoSetArray2);
						}else {
								varNoSetArray1 = (Object[]) data1111.get("var1");
						        varNoSetArray2 = (Object[]) data1111.get("var2");
						}
				  
				        long testId = 0l;
				        int varLength = 0;
				        for(int itr = 0; itr < 2; itr++) {
				        	if(itr == 0) {
				        		varType = "1";
				        		varLength = varNoSetArray1.length;
				        	}else if(itr == 1) {
				        		varType = "2";
				        		varLength = varNoSetArray2.length;
				        	}
				        Test newTest = new Test();
				        
						newTest = copyTestVOToTest(newTest, testVO);
						if (varType == "1") {
							
							String temp1 = Arrays.toString(varNoSetArray1).replace("[","").replace("]","").replaceAll("\\s", "");
							String[] varNoArrayVar1= temp1.split(",");
							
							 System.out.println(varNoArrayVar1);
							 
							List<QuestionGroup> checkForQuestionAvailableInGivenVariations1 = questionGroupDao.getAllQuestionGroupsByTestConfiguration(
									Long.parseLong((selectedTopics[i])), varLength,
									complLevel, varNoArrayVar1);
							if (checkForQuestionAvailableInGivenVariations1.isEmpty()) {
								
								String temp2 = Arrays.toString(varNoSetArray2).replace("[","").replace("]","").replaceAll("\\s", "");
								String[] varNoArrayVar2 = temp2.split(",");
								List<QuestionGroup> checkForQuestionAvailableInGivenVariations2 = questionGroupDao.getAllQuestionGroupsByTestConfiguration(
										Long.parseLong((selectedTopics[i])), varLength,
										complLevel, varNoArrayVar2);
								
								if (checkForQuestionAvailableInGivenVariations2.isEmpty()) {
								}else {
									varType = "2";
									newTest.setVarNo(Arrays.toString(varNoSetArray2).replace("[","").replace("]","").replaceAll("\\s", ""));
								}
								
							}else {
								newTest.setVarNo(Arrays.toString(varNoSetArray1).replace("[","").replace("]","").replaceAll("\\s", ""));
							}
							
						}else if (varType == "2") {
							if (varNoSetArray1.equals(varNoSetArray2)) {
								varType = "1";
								complLevel = (complLevel+1);
								
								varNO_Set.clear();
								for (QuestionGroup questionGroup : questionGroups) {
									
									if (complLevel != 6) {
										if (questionGroup.getComplexityLevel().getQgComplexityLevelId() == complLevel) {
											if (questionGroup.getVarNo() != null) {
												varNO_Set.add(Integer.parseInt(questionGroup.getVarNo()));
											}
											
										}
									}else if (complLevel == 6)  {
										if (questionGroup.getComplexityLevel().getQgComplexityLevelId() == 5) {
											if (questionGroup.getVarNo() != null) {
												varNO_Set.add(Integer.parseInt(questionGroup.getVarNo()));
											}
											
										}
									}
									
								}
								
								System.out.println(varNO_Set);
								System.out.println("size -"+varNO_Set.size());
								
								varNoList.clear();
								varNoList = new ArrayList<>(varNO_Set);
						        Collections.sort(varNoList);
						        
						        varNoArray = null;
						        varNoArray =  varNoList.toArray();
						        
								if (varNoList.size() == 1) {
									varNoSetArray1 = null;
									varNoSetArray1 = varNoArray;
									varNoSetArray2 = null;
									varNoSetArray2 = varNoArray;
								} else {
									varNoSetArray1 = null;
									varNoSetArray2 = null;
									varNoSetArray1 = (Object[]) data1111.get("var1");
							        varNoSetArray2 = (Object[]) data1111.get("var2");
								}
						       
						        
						        System.out.println("First half of the array:: "+Arrays.toString(varNoSetArray1));
						        System.out.println("First second of the array:: "+Arrays.toString(varNoSetArray2));
						        if (varType == "1") {
						        	test = testDao.getTestForPracticeTestByTopicId(selectedTopics[0], varType, complLevel); 
						        	if (test == null) {
						        		newTest.setVarNo(Arrays.toString(varNoSetArray1).replace("[","").replace("]","").replaceAll("\\s", ""));
									}else {
										data.put("done", true);
										data.put("COMPLEVEL", complLevel); // this is the start of the topic
										data.put("TID", test.getTestId());
										data.put("msg", Constants.testcreationsuccess);
										
										return data;
									}
								}else  if (varType == "2") {
									 
									test = testDao.getTestForPracticeTestByTopicId(selectedTopics[0], varType, complLevel); 
						        	if (test == null) {
						        		newTest.setVarNo(Arrays.toString(varNoSetArray2).replace("[","").replace("]","").replaceAll("\\s", ""));
									}else {
										data.put("done", true);
										data.put("COMPLEVEL", complLevel); // this is the start of the topic
										data.put("TID", test.getTestId());
										data.put("msg", Constants.testcreationsuccess);
										
										return data;
									}
									
								}else if (varType == "ALL") {
									
									test = testDao.getTestForPracticeTestByTopicId(selectedTopics[0], varType, complLevel); 
						        	if (test == null) {
						        		newTest.setVarNo(Arrays.toString(varNoArray).replace("[","").replace("]","").replaceAll("\\s", ""));
									}else {
										data.put("done", true);
										data.put("COMPLEVEL", complLevel); // this is the start of the topic
										data.put("TID", test.getTestId());
										data.put("msg", Constants.testcreationsuccess);
										
										return data;
									}
								}
						      
						        
							}else {
								newTest.setVarNo(Arrays.toString(varNoSetArray2).replace("[","").replace("]","").replaceAll("\\s", ""));
							}
							
						}else if (varType == "ALL") {
							newTest.setVarNo(Arrays.toString(varNoArray).replace("[","").replace("]","").replaceAll("\\s", ""));
						}
						
						newTest.setVarType(varType);
						
						newTest.setTestLevel(complLevel); //  this to find test by complexity number
						
						newTest.setCreatedBy(user.getUserId()+"-"+user.getFirstName() +" "+ user.getLastName());
						
						Test test2 = testDao.getTestBytopicIdVarNoAndVarTypeAndCompLevel(selectedTopics[i], varType,
								complLevel, newTest.getVarNo());
						if (test2 == null) {
							testId = testDao.configureTestPaper(newTest);
							
							testVO.setTestId(testId);

							JSONArray jsArrayLvlNm = (JSONArray) totalQuestion.get("LVLNO");
							System.out.println(jsArrayLvlNm);
							
							/// test Configuration starts
							
							TestConfiguration testConfiguration = new TestConfiguration();
							
							QGComplexityLevel complexityLevel = new QGComplexityLevel();
							if (complLevel != 6) {
								complexityLevel.setQgComplexityLevelId(complLevel);
								testConfiguration.setComplexityLevel(complexityLevel);
							}else if(complLevel == 6){
								complexityLevel.setQgComplexityLevelId(complLevel - 1);
								testConfiguration.setComplexityLevel(complexityLevel);
							}
							
							
								testConfiguration.setTopicTimeLimit(Constants.defaultTimeLimitForPracticeTest); //default set time for test
							
								Standard standard = new Standard();
								standard.setStdId(testVO.getStandard().getStdId());
								testConfiguration.setStandard(standard);
								
								if (complLevel != 6) {
									testConfiguration.setNoOfQuestionGroup(varLength);
								}else if (complLevel == 6) {
									testConfiguration.setNoOfQuestionGroup(Constants.defaultNoOfQuestionsForLastPracticeTest);
								}	
								Topic topic = new Topic();
								topic.setTopicId(totalQuestion.getLong("TID"));
								testConfiguration.setTopic(topic);
								
								Test setTest = new Test();
								setTest.setTestId(testId);
								testConfiguration.setTest(setTest);
								
								testConfigurationDao.configureTest(testConfiguration);
								
								Thread t = new Thread();
								t.sleep(2000);
						} else {
							testId = test2.getTestId();
						}
						
				        }
						data.put("done", true);
							
						data.put("COMPLEVEL", complLevel); // this is the start of the topic
						data.put("TID", (testId-1));
						data.put("msg", Constants.testcreationsuccess);
					}
				}
			
			return data;
			
		}else {
			
			data.put("done", true);
			data.put("COMPLEVEL", complLevel); // this is the start of the topic
			data.put("TID", test.getTestId());
			data.put("msg", Constants.testcreationsuccess);
			
			return data;
			
		}
	
	}// method ends

	private JSONObject twoVariationFilter(Object[] obj) {
		Arrays.sort(obj);
		
		ArrayList<Integer> smallEven = new ArrayList<>();
		ArrayList<Integer> smallOdd = new ArrayList<>();
		ArrayList<Integer> big1 = new ArrayList<>();
		ArrayList<Integer> big2 = new ArrayList<>();
		ArrayList<Integer> var1 = new ArrayList<>();
		ArrayList<Integer> var2 = new ArrayList<>();
		
		Integer[] arr = Arrays.copyOf(obj, obj.length, Integer[].class);
		
		boolean flgg = true;
		for(int i=0; i < arr.length; i++) {
			if(arr[i] < 100) {
				if(arr[i] % 2 == 0) {
					smallEven.add(arr[i]);
				}else {
					smallOdd.add(arr[i]);
				}
			}else {
				if(flgg == true) {
					flgg = false;
					big1.add(arr[i]);
				}else {
					flgg = true;
					big2.add(arr[i]);
				}
			}
		}
		
		int se = smallEven.size();
		int so = smallOdd.size();
		
		int tmp = 0;
		if(se < so) {
			tmp = se;
		}else {
			tmp = so;
		}
		
		int flg = 0;
		for(int j=0; j < tmp; j++) {
			flg = (int) (Math.random() * (1 - 0 + 1) + 0);
			if(flg == 0) {
				var1.addAll(smallEven.subList(j, j+1));
			}else {
				var1.addAll(smallOdd.subList(j, j+1));
			}
			
			if(flg == 1) {
				var2.addAll(smallEven.subList(j, j+1));
			}else {
				var2.addAll(smallOdd.subList(j, j+1));
			}
		}
		
		var1.addAll(big1);
		var2.addAll(big2);
		
		JSONObject varObj = new JSONObject();
		varObj.put("var1", var1.toArray());
		varObj.put("var2", var2.toArray());
		return varObj;
	}

	private List<QuestionGroup> getQuestionsBycomplLevelForAllLevel(long topicId, Object[] complLevelArr) throws Exception {
		List<QuestionGroup> questionGroupsByComlLevel = questionGroupDao.getAllApprovedQuestionGroupsByTopicIdAndAllcomplLevel(topicId, complLevelArr);
		
//		if (questionGroupsByComlLevel.isEmpty()) {
//			complLevel = complLevel + 1;
//			questionGroupsByComlLevel = getQuestionsBycomplLevel(topicId, complLevel);
//		}
		
		return questionGroupsByComlLevel;
	}

	private List<QuestionGroup> getQuestionsBycomplLevel(long topicId, int complLevel) throws Exception {
		List<QuestionGroup> questionGroupsByComlLevel = questionGroupDao.getAllApprovedQuestionGroupsByTopicIdAndcomplLevel(topicId, complLevel);
		
//		if (questionGroupsByComlLevel.isEmpty()) {
//			complLevel = complLevel + 1;
//			questionGroupsByComlLevel = getQuestionsBycomplLevel(topicId, complLevel);
//		}
		
		return questionGroupsByComlLevel;
	}

	
//	TODO: Set test configuration new development
	@Override
	public JSONObject setTestConfigurationForSelectedTopic(TestVO testVO, User user) throws Exception {
		
		JSONObject data = new JSONObject();

		if (testVO != null) {
			
			Topic tp = topicDao.getTopicByTopicId(Long.parseLong(testVO.getSelectedTopics()));
			
			if(testVO.getTestType().getTestTypeId() == 1) {
				
				List<Test> testList = testDao.getTestForPracticeTestByTopicIdOnly(testVO.getSelectedTopics());
				
				if(!testList.isEmpty()) {
					for(Test t : testList) {
						t.setActive(false);
						t.setUpdatedTime(System.currentTimeMillis());
						testDao.updateTestDetails(t);
					}
				}
				
				testVO.setName(tp.getTopicName() + "*" + tp.getTopicName1());
				String[] selectedTopics = testVO.getSelectedTopics().split(",");
				data = generateTestConfiguration(selectedTopics, testVO, user);
			}
		}
		return data;
	}

	private JSONObject generateTestConfiguration(String[] selectedTopics, TestVO testVO, User user)
			throws NumberFormatException, Exception {
		
		JSONObject data = new JSONObject();
		JSONObject totalQuestion = new JSONObject();
		
		int complLevel = 1;
		String varType = null;
		for (int i = 0; i < selectedTopics.length; i++) {
			totalQuestion = questionGroupServiceImpl
					.fetchLevelWiseCountOfQuestionGroupsByTopics(Long.parseLong((selectedTopics[i])));

			if (totalQuestion.get("TOTALQG").equals("0")) {

				data.put("done", false);
				data.put("msg", "No question found for this topic");

			} else {
				int[] r = new int[totalQuestion.getJSONArray("LVLNO").length()];

				for (int n = 0; n < totalQuestion.getJSONArray("LVLNO").length(); n++) {
					JSONObject lvlnoObject = totalQuestion.getJSONArray("LVLNO").getJSONObject(n);
					for (String key : lvlnoObject.keySet()) {
						r[n] = Integer.parseInt(key);
					}
				}

				for (int z = 0; z < totalQuestion.getInt("TOTALQG"); z++) {
					List<QuestionGroup> questionGroups = null;
					complLevel = r[z];
					questionGroups = getQuestionsBycomplLevel(Long.parseLong((selectedTopics[i])), complLevel);
					if (questionGroups.isEmpty()) {
						data.put("done", false);
						data.put("msg", "Under development..");
						return data;
					}

					Set<Integer> varNO_Set = new HashSet<Integer>();

					for (QuestionGroup questionGroup : questionGroups) {

						if (questionGroup.getComplexityLevel().getQgComplexityLevelId() == complLevel) {
							if (questionGroup.getVarNo() != null) {
								varNO_Set.add(Integer.parseInt(questionGroup.getVarNo()));
							}
						}
					}

					List<Integer> varNoList = new ArrayList<>(varNO_Set);
					Collections.sort(varNoList);

					Object[] varNoArray = varNoList.toArray();

					Object[] varNoSetArray1 = {};
					Object[] varNoSetArray2 = {};

					JSONObject data1111 = twoVariationFilter(varNoArray);

					if (varNoList.size() == 1) {
						varNoSetArray1 = varNoArray;
						varNoSetArray2 = varNoArray;
					} else {
						varNoSetArray1 = (Object[]) data1111.get("var1");
						varNoSetArray2 = (Object[]) data1111.get("var2");
					}

					long testId = 0l;
					int varLength = 0;
					for (int itr = 0; itr < 2; itr++) {
						if (itr == 0) {
							varType = "1";
							varLength = varNoSetArray1.length;
						} else if (itr == 1) {
							varType = "2";
							varLength = varNoSetArray2.length;
						}
						Test newTest = new Test();

						newTest = copyTestVOToTest(newTest, testVO);
						if (varType == "1") {
								newTest.setVarNo(Arrays.toString(varNoSetArray1).replace("[", "").replace("]", "")
										.replaceAll("\\s", ""));
						} else if (varType == "2") {
							 if (!varNoSetArray1.equals(varNoSetArray2)) {
								newTest.setVarNo(Arrays.toString(varNoSetArray2).replace("[", "").replace("]", "")
										.replaceAll("\\s", ""));
							}
						}

						newTest.setVarType(varType);

						newTest.setTestLevel(complLevel); // this to find test by complexity number

						newTest.setCreatedBy(user.getUserId() + "-" + user.getFirstName() + " " + user.getLastName());

						Test test2 = testDao.getTestBytopicIdVarNoAndVarTypeAndCompLevel(selectedTopics[i], varType,
								complLevel, newTest.getVarNo());
						if (test2 == null) {
							if(!(varNoSetArray1.equals(varNoSetArray2) && varType == "2")) {
								testId = testDao.configureTestPaper(newTest);
							}

							testVO.setTestId(testId);

							JSONArray jsArrayLvlNm = (JSONArray) totalQuestion.get("LVLNO");
							System.out.println(jsArrayLvlNm);

							/// test Configuration starts

							TestConfiguration testConfiguration = new TestConfiguration();

							QGComplexityLevel complexityLevel = new QGComplexityLevel();
							if (complLevel != 6) {
								complexityLevel.setQgComplexityLevelId(complLevel);
								testConfiguration.setComplexityLevel(complexityLevel);
							} else if (complLevel == 6) {
								complexityLevel.setQgComplexityLevelId(complLevel - 1);
								testConfiguration.setComplexityLevel(complexityLevel);
							}

							testConfiguration.setTopicTimeLimit(Constants.defaultTimeLimitForPracticeTest); // default
																											// set time
																											// for test

							Standard standard = new Standard();
							standard.setStdId(testVO.getStandard().getStdId());
							testConfiguration.setStandard(standard);

							testConfiguration.setNoOfQuestionGroup(varLength);
							Topic topic = new Topic();
							topic.setTopicId(totalQuestion.getLong("TID"));
							testConfiguration.setTopic(topic);

							Test setTest = new Test();
							setTest.setTestId(testId);
							testConfiguration.setTest(setTest);
							if(!(varNoSetArray1.equals(varNoSetArray2) && varType == "2")) {
								testConfigurationDao.configureTest(testConfiguration);
							}
						} else {
							testId = test2.getTestId();
						}
					}
					data.put("done", true);

					data.put("COMPLEVEL", complLevel); // this is the start of the topic
					data.put("TID", testId);
					data.put("msg", Constants.testcreationsuccess);
				}
			}
		}

		return data;
	}
	
	

}
