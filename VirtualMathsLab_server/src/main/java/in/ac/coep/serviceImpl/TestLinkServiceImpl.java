package in.ac.coep.serviceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.constants.Constants;
import in.ac.coep.dao.TestDao;
import in.ac.coep.dao.TestInstanceStateDao;
import in.ac.coep.entity.Standard;
import in.ac.coep.entity.Test;
import in.ac.coep.entity.TestInstanceState;
import in.ac.coep.entity.TestType;
import in.ac.coep.entity.User;
import in.ac.coep.service.TestLinkService;
import in.ac.coep.utility.EncoderDecoder;

/**
 * 
 * @author Prashant
 *
 */
@Service
public class TestLinkServiceImpl implements TestLinkService {

	@Autowired
	private TestInstanceStateDao instanceStateDao;

//	@Autowired
//	private UserDao userDao;

	@Autowired
	private TestDao testDao;

	@Override
	public JSONObject generatePracticeTestLinkForRequestedUser(User user, long topicId, long testTypeId, long testId, int compLevel)
			throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		if (topicId != 0) {

			if (testTypeId == 1) { // practice Test

				Test test = testDao.getTestByTestId(testId);

				if (test != null) {
					
					List<TestInstanceState> testInstanceState = instanceStateDao
							.getTestInstanceStateByUserIdAndTestId(user.getUserId(), test.getTestId());

					if (testInstanceState.size() == 0) {
						
//						boolean firstTest = true;
						int testNo = 0;
						data = createTestInstanceStateTestLink(test, user, compLevel, testNo); 

					} else {
						
						boolean flag = false;
						int passFailCnt = 0;
						int highTestNo[] = new int[testInstanceState.size()];;
						int count = 0;
						
						for (TestInstanceState availableTestInsState : testInstanceState) {
							
							highTestNo[count] = availableTestInsState.getPracticeTestNo();
							
//							if(testInstanceState.size() == 1) {
								if (availableTestInsState.getStatus().equalsIgnoreCase("N") || availableTestInsState.getStatus().equalsIgnoreCase("P")) {
									data.put("done", true);
									data.put("emailId", user.getEmailId());
									data.put("testURL", availableTestInsState.getTestURL());
									data.put("startDate", availableTestInsState.getPlannedStartTime().getTime());
									data.put("endDate", availableTestInsState.getPlannedTestEndTime().getTime());
									
									flag = true;
								}else if (availableTestInsState.getStatus().equalsIgnoreCase("C")) {
									
									if (availableTestInsState.getResult().equals("P")) {
										passFailCnt++;
									}
								}
								
								count++;
								
//								if (availableTestInsState.getStatus().equalsIgnoreCase("C")) {
//								data.put("done", false);
//								data.put("emailId", user.getEmailId());
//								data.put("testURL", availableTestInsState.getTestURL());
//								data.put("startDate", availableTestInsState.getPlannedStartTime().getTime());
//								data.put("endDate", availableTestInsState.getPlannedTestEndTime().getTime());
//								data.put("msg", "You have alraedy given the test..");
//							}
						} // for loop ends 
						
						if (flag != true) {
								if (passFailCnt < 2) {
									System.out.println("test");
									int testNo = largestPracticeTestNo(highTestNo);
									data = createTestInstanceStateTestLink(test, user, compLevel, testNo);
									
								}else if(passFailCnt >= 2 && compLevel == 6){
									int testNo = largestPracticeTestNo(highTestNo);
									data = createTestInstanceStateTestLink(test, user, compLevel, testNo);
								}else {
									System.out.println("Move to next compelxity Level");
								}
						}
					}
				} else {
					data.put("done", false);
					data.put("msg", "Test is not created for the given topic..");
				}

			} // if close

		} else {
			data.put("done", false);
			data.put("msg", "topic Id is invalid..");
		}
		return data;
	}
	
	
	
	
	
	

	private int largestPracticeTestNo(int[] highTestNo) {
		// TODO Auto-generated method stub
//		int array[] = {10, 20, 25, 63, 96, 57};
	      int size = highTestNo.length;
	      Arrays.sort(highTestNo);
//	      System.out.println("sorted Array ::"+Arrays.toString(array));
	      int largestNo = highTestNo[size-1];
//	      System.out.println("largest element is ::"+largestNo);
		return largestNo;
	}







	private JSONObject createTestInstanceStateTestLink(Test test, User user, int compLevel, int testNo) throws Exception {
		// TODO Auto-generated method stub
			
			JSONObject data = new JSONObject();
		
			String link = creteLink(user, test.getTestId());
	
			String url = Constants.baseURLPath + "home?token=" + link;
	
	
			TestInstanceState instanceState = new TestInstanceState();
			instanceState.setTestURL(url);
			
			Standard standard = new Standard();
			standard.setStdId(1);
			instanceState.setStandard(standard);
			
			TestType testType = new TestType();
			testType.setTestTypeId(Constants.practiceTestTypeId); // this Id is for practice test
			instanceState.setTestType(testType);
	
			instanceState.setUser(user);
			instanceState.setTest(test);
			instanceState.setWindowViolationTime(0);
			instanceState.setStatus("N");
			Date date = new Date();
			instanceState.setTickTime(date);
			
			instanceState.setLastQuestionId(0);
			
			instanceState.setTestStart(test.getStartDate());
			instanceState.setTestEnd(test.getEndDate());
			
			if (testNo == 0) {
				instanceState.setPracticeTestNo(1);
			}else {
				instanceState.setPracticeTestNo(testNo + 1);
			}
			
			instanceState.setComplexityLevel(compLevel);
			
			instanceState.setCurrentTestTime("0");
	
			instanceState.setPlannedStartTime(test.getStartDate());
			instanceState.setPlannedTestEndTime(test.getEndDate());
	
			instanceState.setActive(true);
			
			instanceState.setLevelComplete(false);
	
			instanceStateDao.save(instanceState);
	
			Thread.sleep(1000);
			
			data.put("done", true);
			data.put("emailId", user.getEmailId());
			data.put("testURL", url);
			data.put("startDate", instanceState.getPlannedStartTime().getTime());
			data.put("endDate", instanceState.getPlannedTestEndTime().getTime());
		
		return data;
	}







	private static String creteLink(User user, long testId) throws Exception {
		// TODO Auto-generated method stub

		String random = randomAlphaNumeric(25);

		String link = user.getEmailId() + Constants.splitter + user.getCityId()
				+ Constants.splitter + testId + Constants.splitter + random;
		link = EncoderDecoder.encodeString(link);

		return link;
	}

	private static final String ALPHA_NUMERIC_STRING = "ABCDEghijklmnopqrstuFGHIJKLMNOP=@!#%$QRSTUVWXYZabcdefvwxyz0123456789&^*";

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}







	@Override
	public JSONObject generateOtherTestLinkForRequestedUser(User user, long testTypeId, long testId) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		if (testId != 0) {

			if (testTypeId == 5) { // other Test

				Test test = testDao.getTestByTestId(testId);

				if (test != null) {
					
					List<TestInstanceState> testInstanceState = instanceStateDao
							.getTestInstanceStateByUserIdAndTestId(user.getUserId(), test.getTestId());

					int compLevel = 0;
					if (testInstanceState.size() == 0) {
						
//						boolean firstTest = true;
//						int testNo = 0;
						
						data = createTestInstanceStateTestLinkForOtherTest(test, user, testTypeId); 

					} else {
						
						boolean flag = false;
						int passFailCnt = 0;
						int highTestNo[] = new int[testInstanceState.size()];;
						int count = 0;
						
						for (TestInstanceState availableTestInsState : testInstanceState) {
							
							highTestNo[count] = availableTestInsState.getPracticeTestNo();
							
//							if(testInstanceState.size() == 1) {
								if (availableTestInsState.getStatus().equalsIgnoreCase("N") || availableTestInsState.getStatus().equalsIgnoreCase("P")) {
									data.put("done", true);
									data.put("emailId", user.getEmailId());
									data.put("testURL", availableTestInsState.getTestURL());
									data.put("startDate", availableTestInsState.getPlannedStartTime().getTime());
									data.put("endDate", availableTestInsState.getPlannedTestEndTime().getTime());
									
									flag = true;
								}else if (availableTestInsState.getStatus().equalsIgnoreCase("C")) {
									
									if (availableTestInsState.getResult().equals("P")) {
										passFailCnt++;
									}
								}
								
								count++;
								
						} // for loop ends 
						
						if (flag != true) {
								if (passFailCnt < 2) {
									System.out.println("test");
//									int testNo = largestPracticeTestNo(highTestNo);
									data = createTestInstanceStateTestLinkForOtherTest(test, user,  testTypeId);
									
								}else if(passFailCnt >= 2 && compLevel == 6){
//									int testNo = largestPracticeTestNo(highTestNo);
									data = createTestInstanceStateTestLinkForOtherTest(test, user,  testTypeId);
								}else {
									System.out.println("Move to next compelxity Level");
								}
						}
					}
				} else {
					data.put("done", false);
					data.put("msg", "Test is not created for the given topic..");
				}

			} // if close

		} else {
			data.put("done", false);
			data.put("msg", "topic Id is invalid..");
		}
		return data;
	}



	private JSONObject createTestInstanceStateTestLinkForOtherTest(Test test, User user, long testTypeId) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
	
		String link = creteLink(user, test.getTestId());

		String url = Constants.baseURLPath + "home?token=" + link;


		TestInstanceState instanceState = new TestInstanceState();
		instanceState.setTestURL(url);
		
		Standard standard = new Standard();
		standard.setStdId(1);
		instanceState.setStandard(standard);
		
		TestType testType = new TestType();
		testType.setTestTypeId(testTypeId); // this Id is for other test
		instanceState.setTestType(testType);

		instanceState.setUser(user);
		instanceState.setTest(test);
		instanceState.setWindowViolationTime(0);
		instanceState.setStatus("N");
		Date date = new Date();
		instanceState.setTickTime(date);
		
		instanceState.setLastQuestionId(0);
		
		instanceState.setTestStart(test.getStartDate());
		instanceState.setTestEnd(test.getEndDate());
		
		int testNo = 1;
		if (testNo  == 0) {
			instanceState.setPracticeTestNo(1);
		}else {
			instanceState.setPracticeTestNo(testNo + 1);
		}
		
		int compLevel = 1;
		instanceState.setComplexityLevel(compLevel);
		
		instanceState.setCurrentTestTime("0");

		instanceState.setPlannedStartTime(test.getStartDate());
		instanceState.setPlannedTestEndTime(test.getEndDate());

		instanceState.setActive(true);
		
		instanceState.setLevelComplete(false);

		instanceStateDao.save(instanceState);

		Thread.sleep(1000);
		
		data.put("done", true);
		data.put("emailId", user.getEmailId());
		data.put("testURL", url);
		data.put("startDate", instanceState.getPlannedStartTime().getTime());
		data.put("endDate", instanceState.getPlannedTestEndTime().getTime());
	
	return data;
}
	
	
	

//	@SuppressWarnings("unused")
//	@Override
////	public JSONObject generateLinkForRequestedMailId(long phoneNumber, int domain, int testlvl) throws Exception {
////		// TODO Auto-generated method stub
////
////		JSONObject data = new JSONObject();
////
////		String email = phoneNumber + "@VirtualMathsLab.org";
//////		if(domain == 1 || domain == 2){
//////			domain = 1;
//////		}else if(domain == 3 || domain == 4){
//////			domain = 2;
//////		}else{
//////			data.put("done", false);
//////			data.put("msg", "Invalid department ID");
//////			return data;
//////		}
////		
//////		if (email != null) {
//////			User user = userDao.getUserByEmailId(email);
//////			if(!(domain == user.getDepartment().getDepartmentId())){
//////				data.put("done", false);
//////				data.put("msg", "Invalid department ID");
//////				return data;
//////			}else{
//////
//////			Test test = testDao.getTestByDepartmentId(user.getDepartment().getDepartmentId());
//////			
//////			if(test != null){
//////				List<TestInstanceState> testInstanceState = testStateDao.getTestInstanceStateByUserIdAndTestId(user.getUserId(),test.getTestId());
//////				
//////				if (testInstanceState.size() == 0) {
//////
//////					String link = creteLink(user, test.getTestId());
//////
//////					String url = Constants.baseURLPath + "home?token=" + link;
//////
////////					long rowCount = testDao.getTestInstanceStateRowCount();
//////
//////					TestInstanceState instanceState = new TestInstanceState();
//////					instanceState.setTestURL(url);
//////					instanceState.setDepartment(user.getDepartment());
//////					instanceState.setUser(user);
//////					instanceState.setTest(test);
//////					instanceState.setWindowViolationTime(0);
//////					instanceState.setStatus("N");
//////					Date date = new Date();
//////					instanceState.setTickTime(date);
//////					instanceState.setLastQuestionId(0);
//////					instanceState.setTestStart(test.getStartDate());
//////					instanceState.setTestEnd(test.getEndDate());
//////					instanceState.setTestLevel(testlvl);
//////					instanceState.setCurrentTestTime("0");
//////
//////					/*if (rowCount < Integer.parseInt(Constants.sysConfig.sysConfigMap.get("MAX_USERS_BATCH_SIZE"))) {
//////						instanceState.setPlannedStartTime(test.getStartDate());
//////						Calendar c = Calendar.getInstance();
//////						c.setTime(test.getStartDate());
//////						c.add(Calendar.DATE,
//////								Integer.parseInt(Constants.sysConfig.sysConfigMap.get("MAX_TEST_ACTIVE_DURATION")));
//////						instanceState.setPlannedTestEndTime(c.getTime());
//////
//////					} else {
//////
//////						long quotient = rowCount
//////								/ Integer.parseInt(Constants.sysConfig.sysConfigMap.get("MAX_USERS_BATCH_SIZE"));
//////
//////						Calendar st = Calendar.getInstance();
//////						st.setTime(test.getStartDate());
//////						st.add(Calendar.DATE, (int) (quotient));
//////						instanceState.setTestStart(st.getTime());
//////						instanceState.setPlannedStartTime(st.getTime());
//////						
//////						Calendar c = Calendar.getInstance();
//////						c.setTime(st.getTime());
//////						c.add(Calendar.DATE, (int) (Integer.parseInt(Constants.sysConfig.sysConfigMap.get("MAX_TEST_ACTIVE_DURATION"))));
//////						instanceState.setPlannedTestEndTime(c.getTime());
//////					}*/
//////
//////					instanceState.setPlannedStartTime(test.getStartDate());
//////					instanceState.setPlannedTestEndTime(test.getEndDate());
//////					
//////					testStateDao.save(instanceState);
//////
//////					data.put("phoneNumber", user.getPhoneNumber());
//////					data.put("testURL", url);
//////					data.put("startDate", instanceState.getPlannedStartTime().getTime());
//////					data.put("endDate", instanceState.getPlannedTestEndTime().getTime());
////////					data.put("roundNumber", Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND"));
//////					if(testlvl != 1){
//////						data.put("roundNumber", testlvl);
//////					}else{
//////						data.put("roundNumber", Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND"));
//////					}
//////					
//////					
////////					Thread t = new Thread(new MailSenderForRegistration(data,
////////							mailSender, classtemplateEngine));
////////					t.start();
//////					
//////					
//////					
//////					
//////				} else {
//////					
//////					TestInstanceState  testInstanceState1 = testStateDao.getTestInstanceStateByEmailIdAndDeptId(user.getUserId(), test.getTestId(), testlvl);
////////					for (TestInstanceState testInstanceState2 : testInstanceState1) {
////////						testStateDao.update(testInstanceState1);
//////						data.put("phoneNumber", user.getPhoneNumber());
//////						data.put("testURL", testInstanceState1.getTestURL());
//////						data.put("startDate", testInstanceState1.getPlannedStartTime().getTime());
//////						data.put("endDate", testInstanceState1.getPlannedTestEndTime().getTime());
////////						data.put("roundNumber", Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND"));
//////						if(testlvl != 1){
//////							data.put("roundNumber", testlvl);
//////						}else{
//////							data.put("roundNumber", Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND"));
//////						}
////////					}
////////					
//////				}
//////			}else{
//////				data.put("done", false);
//////				data.put("msg", "No test is created for this department Id");
//////			}
//////			}
//////		} else {
////			data.put("done", false);
////			data.put("msg", "email Id is Empty");
////		}
////		return data;
////	}
//
//	/**
//	 * @param user
//	 * @param l
//	 * @return
//	 */
////	private static String creteLink(User user, long testId) throws Exception {
////		// TODO Auto-generated method stub
////
////		String random = randomAlphaNumeric(25);
////
////		String link = user.getEmailId() + Constants.splitter + user.getDepartment().getDepartmentId()
////				+ Constants.splitter + testId + Constants.splitter + random;
////		link = EncoderDecoder.encodeString(link);
////
////		return link;
////	}
//
//	private static final String ALPHA_NUMERIC_STRING = "ABCDEghijklmnopqrstuFGHIJKLMNOP=@!#%$QRSTUVWXYZabcdefvwxyz0123456789&^*";
//
//	public static String randomAlphaNumeric(int count) {
//		StringBuilder builder = new StringBuilder();
//		while (count-- != 0) {
//			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
//			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
//		}
//		return builder.toString();
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * in.ac.coep.service.TestLinkService#insertUserInDatabase(java.lang.String)
//	 */
//	@Override
//	public JSONObject insertUserInDatabase(UserVO userVO) throws ConstraintViolationException, Exception {
//		// TODO Auto-generated method stub
//		JSONObject data = new JSONObject();
//
//		if (userVO != null) {
//			
//			User user1 = userDao.getUserByEmailId(userVO.getEmailId());
//			
//			if(user1 != null){
//				
//				data.put("done", false);
//				data.put("msg", "User with this phone number is already registered");
//				
//				
//			}else{
//				
//			User user = new User();
////			user.setITOUserId(userVO.getUserId());
//			user.setPhoneNumber(userVO.getPhoneNumber());
//			user.setEmailId(userVO.getPhoneNumber() + "@VirtualMathsLab.org");
//			  user.setFirstName(userVO.getFirstName());
//			  user.setMiddleName(userVO.getMiddleName());
//			  user.setLastName(userVO.getLastName()); 
//			  user.setGender(userVO.getGender());
//			  user.setSchoolName(userVO.getSchoolName());
////			  user.setStandard(userVO.getStandard());
//			  
////			  Medium medium = new Medium();
////			  medium.setMediumId(userVO.getMediumId());
////				user.setMedium(medium);
//			  
//			  
////			  user.setRollNo(userVO.getRollNo());
////			  user.setAddress(userVO.getAddress());
////			  user.setCityName(userVO.getCityName());
//			  user.setPincode(userVO.getPincode());
//			  Date date = new Date(userVO.getDateOfBirth());
//			  user.setDateOfBirth(date);
////			  user.setPassword(userVO.getPassword());
////			  user.setConpass(userVO.getConpass());
//
////			user.setEmailId(userVO.getPhoneNumber() + "@VirtualMathsLab.org");
//			user.setCountryId(101);
////			user.setCityId(userVO.getCityId());
////			//user.setPassword(userVO.getPassword());
//			user.setPassword(Md5Encryption.md5(userVO.getPassword()));
////			user.setCollegeName(userVO.getCollegeName());
////			user.setUniversityName(userVO.getUniversityName());
////			
////			user.setSchoolName(userVO.getSchoolName());
////			user.setRollNo(userVO.getRollNo());
////			
//			Medium medium = new Medium();
//			medium.setMediumId(userVO.getMediumId());
//			user.setMedium(medium);
//			
////			user.setParEmailId(userVO.getParEmailId());
////			
////			user.setStandard(userVO.getStandard());
////			user.setCityName(userVO.getCityName());
////			user.setPincode(userVO.getPinCode());
////			user.setParEmailId(userVO.getEmailId());
////			
////			Department department = new Department();
//			
////			int std = userVO.getStandard();
//			
////			if(std == 1 || std == 2){
////				department.setDepartmentId(1);
////				user.setDepartment(department);
////			}else if(std == 3 || std == 4){
////				department.setDepartmentId(2);
////				user.setDepartment(department);	
////			}			
//			user.setPhoneNumber(userVO.getPhoneNumber());
//			user.setGender(userVO.getGender());
//			user.setTermsAndConditionsAccepted(true);
//			user.setDeleted(true);
//			user.setEmailValidated(true);
////			user.setGoRegistrationId(userVO.getGoRegistrationId());
////			user.setIsPaymentDone(userVO.isIsPaymentDone());
////			user.setAddress(userVO.getAddress());
//			
//			Date dateOfBirth = new Date(userVO.getDateOfBirth());
//			user.setDateOfBirth(dateOfBirth);
//
//			
//			userVO.setCreatedDate(System.currentTimeMillis());
//			
//			userVO.setUpdatedDate(System.currentTimeMillis());
//			
//			
//			Date cT = new Date(userVO.getCreatedDate());
//			user.setCreatedDate(cT);
//
//			Date uT = new Date(userVO.getUpdatedDate());
//			user.setUpdatedDate(uT);			
//			
//			Roles roles = roleDao.getRoleByRoleName("STUDENT");
//			Set<Roles> set = new HashSet<>();
//			if(roles != null){
//			
//			set.add(roles);
//			}
//			user.setRoles(set);		
//			
//			
//			//////
//			
//			SystemConfig sysconfig = systemConfigDao.getcurrentItoregistrationId();			
//			int GoRegistrationId = Integer.parseInt(sysconfig.getConfigValue());
//			int incrementedGOID = GoRegistrationId + 1;
//			
////			user.setGoRegistrationId(Constants.ItoRegistrationId+ incrementedGOID);
//			
//			user.setIsAdmin(false);
//			
//			userDao.addUser(user);
//			
//			sysconfig.setConfigValue(String.valueOf(incrementedGOID));
//			
//			systemConfigDao.update(sysconfig);
//			
////				if (Integer.parseInt(Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND")) == 1) {
//
////					generateLinkForRequestedMailId(user.getPhoneNumber(), user.getDepartment().getDepartmentId(), 2);
//
////				} else {
//
//					// generateLinkForRequestedMailId(user.getEmailId(),
//					// user.getDepartment().getDepartmentId(),Integer.parseInt(Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND")));
//
////				}
//			
//
//			data.put("done", true);
//			data.put("msg", "User Registered successfully. To continue with the test, you need to upload your school ID card scan copy by logging in to the system.");
//
//		}
//		}else {
//			data.put("done", false);
//			data.put("msg", "Something went wrong. Please try again");
//		}
//		
//
//		return data;
//	}
//
//	
//	
//	@SuppressWarnings("unused")
//	@Override
//	public JSONObject updateUserInDatabase(UserVO userVO) throws Exception {
//		
//		User user = userDao.getUserByEmailId(userVO.getEmailId());
//		JSONObject data = new JSONObject();
//		
//		if (userVO != null) {
//
////			user.setIsPaymentDone(userVO.isIsPaymentDone());
////			user.setGoRegistrationId(userVO.getGoRegistrationId());
//			userDao.updateUSer(user);
//
//			data.put("done", true);
//			data.put("msg", "user updated successfully");
//
//		}else {
//			data.put("done", false);
//			data.put("msg", "Something went wrong. Please try again");
//		}
//
//		return data;
//	}
//	
//	@SuppressWarnings("unused")
//	@Override
//	public JSONObject updatePaymentInfo(UserVO userVO) throws Exception {
//		// TODO Auto-generated method stub
//		JSONObject data = new JSONObject();
//			
//		String emailId = userVO.getPhoneNumber() + "@VirtualMathsLab.org";
//		User user = userDao.getUserByEmailId(emailId);
//	
//		if(user == null){
//			
//			data.put("done", false);
//			data.put("msg", "Mobile number does not exist");
//			
//			
//		}else{
//			
//			if (userVO != null) {
//				
////				user.setIsPaymentDone(userVO.isIsPaymentDone());
//				userDao.updateUSer(user);
//
//				data.put("done", true);
//				data.put("msg", "Success");
//				
//			}else{
//				
//				data.put("done", false);
//				data.put("msg", "Fail");
//			}
//		}
//		
//		return data;
//	}
//
//	@Override
//	public JSONObject getAllStudentsListFromTestInstanceState(int testLevel) throws Exception {
//		// TODO Auto-generated method stub
//		JSONObject data = new JSONObject();
//		if(testLevel <= 0 && testLevel == 1){
//			data.put("done", false);
//			data.put("msg", "test level should be grater than 1");
//		}else{
//			List<TestInstanceState> usersList = testStateDao.getAllUSersListWithLastRound(Integer.parseInt(Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND")) - 1);
//			
//			for (TestInstanceState testInstanceState : usersList) {
////				generateLinkForRequestedMailId(testInstanceState.getUser().getPhoneNumber(), testInstanceState.getUser().getDepartment().getDepartmentId(),Integer.parseInt(Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND")));
//			}
//			data.put("done", true);
//			data.put("msg", "Test Link created for all students");
//		}
//		
//		return data;
//	}
//
//	@Override
//	public JSONObject generateLinkForNextRound(String email, int domain, int testlvl) throws Exception {
//		// TODO Auto-generated method stub
//		
//		JSONObject data = new JSONObject();
//
//		if (email != null) {
//			User user = userDao.getUserByEmailId(email);
//
////			Test test = testDao.getTestByDepartmentId(domain);
//			Test test = testDao.getTestByDepartmentIdAndTestLevel(domain, testlvl);
//			
//			if(test != null){
//				List<TestInstanceState> testInstanceState = testStateDao.getTestInstanceStateByUserIdAndTestId(user.getUserId(),test.getTestId());
//				
//				if (testInstanceState.size() == 0) {
//
////					String link = creteLink(user, test.getTestId());
//
////					String url = Constants.baseURLPath + "home?token=" + link;
//
////					long rowCount = testDao.getTestInstanceStateRowCount();
//
//					TestInstanceState instanceState = new TestInstanceState();
////					instanceState.setTestURL(url);
////					instanceState.setDepartment(user.getDepartment());
//					instanceState.setUser(user);
//					instanceState.setTest(test);
//					instanceState.setWindowViolationTime(0);
//					instanceState.setStatus("N");
//					Date date = new Date();
//					instanceState.setTickTime(date);
//					instanceState.setLastQuestionId(0);
//					instanceState.setTestStart(test.getStartDate());
//					instanceState.setTestEnd(test.getEndDate());
//					instanceState.setTestLevel(testlvl);
//
//					instanceState.setPlannedStartTime(test.getStartDate());
//					instanceState.setPlannedTestEndTime(test.getEndDate());
//					
//					testStateDao.save(instanceState);
//
//					data.put("emailId", user.getEmailId());
//					data.put("testURL", url);
//					data.put("startDate", instanceState.getPlannedStartTime().getTime());
//					data.put("endDate", instanceState.getPlannedTestEndTime().getTime());
//					data.put("roundNumber", testlvl);
////					data.put("roundNumber", Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND"));
//					
//					
////					Thread t = new Thread(new MailSenderForRegistration(data,mailSender, classtemplateEngine));
////					t.start();
//					
//				} else {
//					try {
//						TestInstanceState  testInstanceState1 = testStateDao.getTestInstanceStateByEmailIdAndDeptId(user.getUserId(), test.getTestId(), testlvl);
////						for (TestInstanceState testInstanceState2 : testInstanceState1) {
////							testStateDao.update(testInstanceState1);
//							data.put("emailId", user.getEmailId());
//							data.put("testURL", testInstanceState1.getTestURL());
//							data.put("startDate", testInstanceState1.getPlannedStartTime().getTime());
//							data.put("endDate", testInstanceState1.getPlannedTestEndTime().getTime());
//							data.put("roundNumber", testlvl);
////						}
////						
//					} catch (NullPointerException e) {
//						// TODO: handle exception
//					}
//					
//				}
//			}else{
//				data.put("done", false);
//				data.put("msg", "No test is created for this department Id");
//			}
//		
//		} else {
//			data.put("done", false);
//			data.put("msg", "email Id is Empty");
//		}
//		return data;
//	}
//
//	
//	public JSONObject updateUserCurrentRoundForNextRoundForGanit() {
//		JSONObject data1 = new JSONObject();
//		try {
//			
//			List<User> data = userDao.getAllUserList();
//			
//			if(!data.isEmpty()){
//			for (User user : data) {
//				//if(user.getSchoolId() != null && user.isIsPaymentDone() == true)
//				
//					generateLinkForNextRound(user.getEmailId(), user.getDepartment().getDepartmentId(), Constants.LastRound);
//			}
//				data1.put("done", true);
//				data1.put("msg", "Link created successfully");
////			}else{
////				data1.put("done", false);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return data1;
//	}
//	
//	public JSONObject createTestLinkCurrentRoundForNextRoundForGanit() {
//		JSONObject data1 = new JSONObject();
//		try {
//			
////			List<User> data = userDao.getAllUserList();
//			
//			List<TestInstanceState> data = testStateDao.getAllUsersWithLastRoundTestCompleted(Constants.LastRound -1);
//			
//			if(!data.isEmpty()){
//			for (TestInstanceState user : data) {
//				
//				//if(user.getSchoolId() != null && user.isIsPaymentDone() == true)
//					generateLinkForNextRound(user.getUser().getEmailId(), user.getDepartment().getDepartmentId(), Constants.LastRound);
//			}
//				data1.put("done", true);
//				data1.put("msg", "Link created successfully for second round");
////			}else{
////				data1.put("done", false);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return data1;
//	}
//	
//	
//	
//	@SuppressWarnings("unused")
//	@Override
//	public JSONObject insertUserInDatabaseToRegisterRandomUsers(int nooFusers) throws Exception {
//		// TODO Auto-generated method stub
//		JSONObject data = new JSONObject();
//		
//		
//		
//		for (int i = 0; i < nooFusers; i++) {
//			
//			UserVO userVO = new UserVO();
//			
//			userVO.setFirstName("Demo"+(i+1));
//			userVO.setMiddleName("tset");
//			userVO.setLastName("ITO");
//			userVO.setEmailId("demo"+(i+1)+"@gmail.com");
//			userVO.setActive(false);
//			userVO.setPassword("123");
////			userVO.setDepartmentId(1);
////			userVO.setCollegeName("COEP");
////			userVO.setGender(1);
////			userVO.setGoRegistrationId("ITO00000000"+i);
//			
//			
//			if (userVO != null) {
//				
//				User user1 = userDao.getUserByEmailId(userVO.getEmailId());
//				
//				if(user1 != null){
//					
//					data.put("done", false);
//					data.put("msg", "User with this Email Id is already registered");
//					
//				}else{
//					
//				User user = new User();
////				user.setITOUserId(userVO.getUserId());
//				user.setFirstName(userVO.getFirstName());
//				user.setMiddleName(userVO.getMiddleName());
//				user.setLastName(userVO.getLastName());
//				user.setEmailId(userVO.getEmailId());
////				user.setCountryId(userVO.getCountryId());
////				user.setCityId(userVO.getCityId());
//				//user.setPassword(userVO.getPassword());
//				user.setPassword(Md5Encryption.md5(userVO.getPassword()));
////				user.setCollegeName(userVO.getCollegeName());
////				user.setUniversityName(userVO.getUniversityName());
//				Department department = new Department();
////				department.setDepartmentId(userVO.getDepartmentId());
//				user.setDepartment(department);
//
////				user.setPhoneNumber(userVO.getPhoneNumber());
//				user.setGender(userVO.getGender());
////				user.setTermsAndConditionsAccepted(true);
////				user.setDeleted(true);
//				user.setEmailValidated(true);
//				//user.setItoRegistrationId(userVO.getItoRegistrationId());
////				user.setIsPaymentDone(userVO.isIsPaymentDone());
////				user.setAddress(userVO.getAddress());
//				
////				Date dateOfBirth = new Date(userVO.getDateOfBirth());
////				user.setDateOfBirth(dateOfBirth);
//
//				
//				userVO.setCreatedDate(System.currentTimeMillis());
//				
//				userVO.setUpdatedDate(System.currentTimeMillis());
//				
//				
//				Date cT = new Date(userVO.getCreatedDate());
//				user.setCreatedDate(cT);
//
//				Date uT = new Date(userVO.getUpdatedDate());
//				user.setUpdatedDate(uT);			
//				
//				Roles roles = roleDao.getRoleByRoleName("STUDENT");
//				Set<Roles> set = new HashSet<>();
//				if(roles != null){
//				
//				set.add(roles);
//				}
//				user.setRoles(set);		
//				
//				
//				//////
//				
////				SystemConfig sysconfig = systemConfigDao.getcurrentItoregistrationId();			
////			
////				int ItoRegistrationId = Integer.parseInt(sysconfig.getConfigValue());
////				
////				int incrementedITOID = ItoRegistrationId + 1;
//				
////				user.setItoRegistrationId(Constants.ItoRegistrationId+ incrementedITOID);
//				
////				user.setItoRegistrationId(userVO.getItoRegistrationId());
//				
//				///////
//				
//				user.setIsAdmin(false);
//				
//				userDao.addUser(user);
//				
////				sysconfig.setConfigValue(String.valueOf(incrementedITOID));
//				
////				systemConfigDao.update(sysconfig);
//				
//					if (Integer.parseInt(Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND")) == 1) {
//
////						generateLinkForRequestedMailId(user.getPhoneNumber(), Constants.departmentId_Engg_Competencies, 1);
//
//					} else {
//
//						// generateLinkForRequestedMailId(user.getEmailId(),
//						// user.getDepartment().getDepartmentId(),Integer.parseInt(Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND")));
//
//					}
//				
//
//				data.put("done", true);
//				data.put("msg", "User Registered successfully");
//
//			}
//			}else {
//				data.put("done", false);
//				data.put("msg", "Something went wrong. Please try again");
//			}
//		}
//		
//		
//		
//
//		return data;
//	}
//	
//	@Override
//	public JSONObject getAccountUnlocked(String userMailId) throws Exception {
//		// TODO Auto-generated method stub
//		JSONObject object =  new JSONObject(userMailId);
//		JSONObject data = new JSONObject();
//		JSONArray mailNotFound = new JSONArray();
//		JSONArray mailAlreadyActive = new JSONArray();
//		JSONArray accountActivated = new JSONArray();
//		JSONArray mailArr = object.getJSONArray("mailId");
//		
//		String[] dataArr = getStringArray(mailArr);
//
//		for (int i = 0; i < dataArr.length; i++) {
//			User user = userDao.getUserByEmailId(dataArr[i]);
//			if (user == null) {
//				mailNotFound.put(dataArr[i].split("@")[0]);
//			} else {
////				if (user.isIsPaymentDone() == true) {
////					mailAlreadyActive.put(dataArr[i].split("@")[0]);
////				} else {
////					user.setIsPaymentDone(true);
////					userDao.updateUSer(user);
////					accountActivated.put(dataArr[i].split("@")[0]);
////				}
//			}
//		}
//		
//		data.put("done", true);
////		data.put("msg", "Account activated successfully.");
//		data.put("actNow", accountActivated);
//		data.put("notFound", mailNotFound);
//		data.put("active", mailAlreadyActive);
//		return data;
//	}
//	
//	public static String[] getStringArray(JSONArray jsonArray) {
//	    String[] stringArray = null;
//	    if (jsonArray != null) {
//	        int length = jsonArray.length();
//	        stringArray = new String[length];
//	        for (int i = 0; i < length; i++) {
//	            stringArray[i] = jsonArray.optString(i) +  "@VirtualMathsLab.org";
//	        }
//	    }
//	    return stringArray;
//	}
//
//	@Override
//	public JSONObject getAllStudentsIdCardsDetails() throws Exception {
//		
//		JSONObject data1 = new JSONObject();
//		JSONArray dataArr = new JSONArray();
//		List<User> data = userDao.getAllStudentIdCardDetails();
//		
//		
//		if(!data.isEmpty()){
//			for (User user : data) {
//				JSONObject userJson = new JSONObject();
//				userJson.put("contact", user.getPhoneNumber());
//				userJson.put("name", user.getFirstName()+ " " + user.getLastName());
////				userJson.put("schoolMedia", user.getSchoolId());
//				userJson.put("schoolName", user.getSchoolName());
////				userJson.put("payment", user.isIsPaymentDone());
//				dataArr.put(userJson);
//			}
//				data1.put("user", dataArr);
//				data1.put("done", true);
//				data1.put("msg", "All record fetched successfully");
//			}else{
//				data1.put("done", false);
//				data1.put("msg", "No such record found");
//			}
//		
//		// TODO Auto-generated method stub
//		return data1;
//	}
//
//	@Override
//	public JSONObject emailVerificationService(String token) throws Exception {
//		// TODO Auto-generated method stub
//	JSONObject json = new JSONObject();
//		
//		if (token == null) {
//			json.put("valid", "False");
//			json.put("msg", "Email Verification URL is invalid.");
//		} else {
//			
//			String decodedToken = EncoderDecoder.decodeString(token);
//			
//			User user =  userDao.getUserByEmailId(decodedToken);
//			
//			user.setAccountLocked(false);
//			
//			userDao.updateUSer(user);
//			
//			json.put("valid", "True");
//			json.put("msg", "Redirecting to email verification.");
//			json.put("email", decodedToken);
//			json.put("name", user.getFirstName()+" "+user.getLastName());
//		}
//		return json;
//	}

}
