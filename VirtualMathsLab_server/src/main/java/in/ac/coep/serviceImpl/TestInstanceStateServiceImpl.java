/**
 * 
 */
package in.ac.coep.serviceImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.constants.Constants;
import in.ac.coep.dao.TestInstanceDao;
import in.ac.coep.dao.TestInstanceStateDao;
import in.ac.coep.dao.TopicDao;
import in.ac.coep.dao.TopicMappingDao;
import in.ac.coep.entity.TestInstanceState;
import in.ac.coep.entity.Topic;
import in.ac.coep.entity.TopicMapping;
import in.ac.coep.service.TestInstanceStateService;

/**
 * @author Prashant
 *
 */
@Service
public class TestInstanceStateServiceImpl implements TestInstanceStateService {

	private static final Logger LOGGER = Logger.getLogger(TestInstanceStateServiceImpl.class);

	long attemptCount = 0;

	@Autowired
	private TestInstanceStateDao testInstanceStateDao;
	
	@Autowired
	private TestInstanceDao testInstanceDao;
	
	@Autowired
	private TopicMappingDao topicMappingDao;

	@Autowired
	private TopicDao topicDao;

	
	
	@Override
	public String isURLValid(String token, long userId) throws Exception {
		// TODO Auto-generated method stub
		String result = "";

		if (token != null) {
			String urlCheck = Constants.baseURLPath + "home?token=" + token;
//			List<TestInstanceState> testInstanceState2 =  testInstanceStateDao.getTestInstanceStateByUserId(userId);
			List<TestInstanceState> testInstanceState2 =  testInstanceStateDao.getTestInstanceStateByUserIdAndtestLink(userId, urlCheck);
			if (testInstanceState2 != null) {
				// TestInstanceState testInstanceState =
				// testInstanceStateDao.getTestInstanceStateByUserId(userId);
				String url = Constants.baseURLPath + "home?token=" + token;
				for (TestInstanceState testInstanceState : testInstanceState2) {
					

					long expiredTime = testInstanceState.getPlannedTestEndTime().getTime();
					long currentTime = System.currentTimeMillis();
					long startTime = testInstanceState.getPlannedStartTime().getTime();
					// if (testInstanceState2 != null) {
					if (testInstanceState.getTestURL().equals(url)) {
						if (startTime < currentTime) {
							if (expiredTime > currentTime) {
								if (!testInstanceState.getStatus().equalsIgnoreCase("C")) {

									if (testInstanceState.getStatus().equalsIgnoreCase("N")) {
										result = "true";
//										break;
									} else if (testInstanceState.getStatus().equalsIgnoreCase("P")) {
										if (Constants.testAttempts.CountTestAttempts
												.get(testInstanceState.getUser().getUserId()) == null) {
											Constants.testAttempts.CountTestAttempts
													.put(testInstanceState.getUser().getUserId(), 0l);
										}

										Calendar calendar = Calendar.getInstance();
										calendar.setTime(testInstanceState.getTickTime());

										if (Constants.testAttempts.CountTestAttempts.get(testInstanceState.getUser()
												.getUserId()) != Constants.noOfAttemptForTest) {

												result = "true";
												break;
										} else {
											result = Constants.exceededTestAttempts;
										}
									} else {
										result = "Something went wrong. Please check again";
									}
								} else {
									result = Constants.testCompletedMsg;
								}
							} else {
								result = Constants.expiredLinkMsg;
							}
						} else {
							result = Constants.beforeTestStartLinkMsg;
						}
					} else {
						result = Constants.linkWrongMsg;
					}
				}

			} else {
				result = "false";
				LOGGER.info("Wrong Url or no URL is generated for logged in user");
			}
		}

		return result;
	}



	@Override
//	@PreAuthorize("hasAnyRole('STUDENT')")
	public JSONObject updateTestInstanceState(TestInstanceState instanceState) throws Exception {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if (instanceState.getStatus() != null) {
			
			TestInstanceState instanceState1 = testInstanceStateDao
					.getTestInstanceStateById(instanceState.getTestInstanceStateId());
			
			Thread.sleep(100);
			
			instanceState1.setStatus("C");
			instanceState1.setTestEnd(new Date(System.currentTimeMillis()));
			
			testInstanceStateDao.update(instanceState1);
			
			
			instanceState1.setStatus("C");
			instanceState1.setTestEnd(new Date(System.currentTimeMillis()));
			testInstanceStateDao.update(instanceState1);
			

		} else if (instanceState.getWindowViolationTime() != 0) {
			
			TestInstanceState instanceState1 = testInstanceStateDao
					.getTestInstanceStateById(instanceState.getTestInstanceStateId());
			instanceState1.setWindowViolationTime(instanceState1.getWindowViolationTime() + 1);
			testInstanceStateDao.update(instanceState1);
		}

		json.put("done", true);
		return json;
	}




	@Override
	public JSONObject fetchAttemptedAndTotalQuestion(long tisId) throws Exception {
		// TODO Auto-generated method stub
		List<Object[]> list = testInstanceDao.fetchAttemptedAndTotalQuestion(tisId, false);

		List<Object[]> attempted = testInstanceDao.fetchAttemptedAndTotalQuestion(tisId, true);
		
		TestInstanceState testInstanceState  = testInstanceStateDao.getTestInstanceStateById(tisId);

		JSONObject json = new JSONObject();
		JSONObject att = new JSONObject();
		JSONObject data = new JSONObject();
		JSONObject topicNm = new JSONObject();
		
		for (Object[] objects : list) {
			
			System.out.println(Constants.topics.topicsMap.get(objects[0]));
			System.out.println(objects[0]);
			String topicName = "";
			topicName =  getParentTopicNameBytopicId((Long)objects[0]);
			
			json.put(Constants.topics.topicsMap.get(objects[0]), objects[1]);
			topicNm.put("topicNm",topicName);
			
			
		}

		for (Object[] objects : attempted) {
			att.put(Constants.topics.topicsMap.get(objects[0]), objects[1]);
		}

		data.put("TOPICNM", topicNm);
		data.put("TOT", json);
		data.put("ATT", att);
		data.put("userId", testInstanceState.getUser().getUserId());

		// Thread thread = new Thread(new
		// DeleteTestInstancesThread(testInstanceDao, tisId));
		// thread.start();

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


	@Override
	public JSONObject updateCurrentTestTime(TestInstanceState instanceState) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject jsonObject = new JSONObject();
		
		if(instanceState != null){
			
			
			try {
				TestInstanceState instanceState2 = testInstanceStateDao.getTestInstanceStateById(instanceState.getTestInstanceStateId());
				instanceState2.setCurrentTestTime(instanceState.getCurrentTestTime());
				
				testInstanceStateDao.update(instanceState2);
				
				jsonObject.put("done", "true");
				jsonObject.put("msg", "current test time update successfully");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				jsonObject.put("done", false);
				jsonObject.put("msg", "current test time update fail");
				LOGGER.info("current test time update fail");
			}
			
		}
		
		return jsonObject;
	}



}
