//package in.ac.coep.serviceImpl;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import in.ac.coep.dao.TestDao;
//import in.ac.coep.dao.TestInstanceDao;
//import in.ac.coep.dao.TestInstanceStateDao;
//import in.ac.coep.dao.TestStatisticDao;
//import in.ac.coep.dao.UserDao;
//import in.ac.coep.entity.Test;
//import in.ac.coep.entity.TestInstance;
//import in.ac.coep.entity.TestInstanceState;
//import in.ac.coep.entity.TestInstanceStatistics;
//import in.ac.coep.entity.User;
//import in.ac.coep.service.ModifyStudentTestService;
//
//@Service
//public class ModifyStudentTestServiceImpl implements ModifyStudentTestService {
//	
//	
//	@Autowired
//	UserDao userDao;
//	
//	@Autowired
//	private TestInstanceStateDao testStateDao;
//	
////	@Autowired
////	private DepartmentDao departmentdao;
//	
//	@Autowired
//	private TestInstanceStateDao testInstanceStateDao;
//	
//	@Autowired
//	private TestInstanceDao testInstanceDao;
//	
//	@Autowired
//	private TestDao testDao;
//	
//	@Autowired
//	private TestStatisticDao testStatisticDao;
//	
//
//	@Override
//	public JSONObject getAllStudentList() throws Exception {
//		// TODO Auto-generated method stub
//		
//		JSONObject data = new JSONObject();
//		JSONArray jsonArray = new JSONArray();
//		
//		
//		
//		List<User> user = userDao.fetchUsers();
//		
//		if (user.size() != 0) {
//			for (User user1 : user) {
//				JSONObject userdata = new JSONObject();
//				
//				userdata.put("UID", user1.getUserId());
//				userdata.put("UNM", user1.getFirstName()+ " " + user1.getLastName());
//				userdata.put("EMAIL", user1.getEmailId());
////				userdata.put("UITOID", user1.getGoRegistrationId());
//								 
//				
//				jsonArray.put(userdata);
//			}
//
//			data.put("data", jsonArray);
//			data.put("done", true);
//
//		} else {
//			data.put("done", false);
//			data.put("msg", "No Record found for User");
//		}
//		
//		
//		return data;
//	}
//
//
//	@Override
//	public JSONObject getTestInstanceStaedetailByUserId(long userId) throws Exception {
//		// TODO Auto-generated method stub
//		JSONObject data = new JSONObject();
//		JSONArray jsonArray = new JSONArray();
//		
//		List<TestInstanceState> instanceState = testStateDao.getTestInstanceStateByUserId(userId);
//		
//		if (instanceState.size() != 0) {
//			for (TestInstanceState instanceState1 : instanceState) {
//				JSONObject instancesatejson = new JSONObject();
//				
////				if(instanceState1.getStatus().equalsIgnoreCase("C")){
//				
//				instancesatejson.put("TEST_IINST_ID", instanceState1.getTestInstanceStateId());
//				instancesatejson.put("TESTURL", instanceState1.getTestURL());
//				instancesatejson.put("STATUS", instanceState1.getStatus());
//				instancesatejson.put("STARTDATE", instanceState1.getPlannedStartTime());
//				instancesatejson.put("ENDDATE", instanceState1.getPlannedTestEndTime());
//				instancesatejson.put("PDFSTATUS", instanceState1.isPdfGenerate());
//				instancesatejson.put("TESTLEVEL", instanceState1.getTestLevel());				
////				instancesatejson.put("DEPT", instanceState1.getDepartment().getName());
//				instancesatejson.put("TESTID", instanceState1.getTest().getTestId());
//				instancesatejson.put("TESTNAME", instanceState1.getTest().getName());
////				instancesatejson.put("ACTIVE", instanceState1.isActive());
//				instancesatejson.put("SCORECARDLINK", instanceState1.getScoreCardLink());
//								 
//				jsonArray.put(instancesatejson);
//				
////				}
//			}
//
//			data.put("data", jsonArray);
//			data.put("done", true);
//
//		} else {
//			data.put("done", false);
//			data.put("msg", "No Record found for User");
//		}
//		
//		
//		return data;
//	}
//
//
//	@Override
//	public JSONObject updateTestInstanceStaedetail(TestInstanceState testInstanceState) throws Exception {
//		// TODO Auto-generated method stub
//		
//		JSONObject json = new JSONObject();
//		
//		
//		if (testInstanceState.getTestInstanceStateId() != 0) {
//			TestInstanceState instanceState1 = testInstanceStateDao
//					.getTestInstanceStateById(testInstanceState.getTestInstanceStateId());
//			
//			if(instanceState1.getStatus().equalsIgnoreCase("P")){
//				
//				List<TestInstance> instances = testInstanceDao.getTestInstanceByTISId(instanceState1.getTestInstanceStateId());
//
//				for (TestInstance testInstance : instances) {
//					testInstanceDao.delete(testInstance);
//				}
//				
//				instanceState1.setStatus(testInstanceState.getStatus());
//				instanceState1.setPlannedStartTime(testInstanceState.getPlannedStartTime());
//				instanceState1.setPlannedTestEndTime(testInstanceState.getPlannedTestEndTime());
//				testInstanceStateDao.update(instanceState1);
//				
//				
//			}else{
//				
//				instanceState1.setStatus(testInstanceState.getStatus());
//				instanceState1.setPlannedStartTime(testInstanceState.getPlannedStartTime());
//				instanceState1.setPlannedTestEndTime(testInstanceState.getPlannedTestEndTime());
//				testInstanceStateDao.update(instanceState1);
//				
//				
//				
//			}
//			
//			
//			json.put("done", true);
//			return json;
//			
//		}else{
//			
//			json.put("done", false);
//			return json;
//			
//		}
//		
//	}
//
//
//	@Override
//	public JSONObject getSectionsFromTestId(long testId, long userId) throws Exception {
//
//		JSONObject data = new JSONObject();
//		JSONArray jsonArray = new JSONArray();
//
//		Test test = testDao.getTestByTestId(testId);
//		TestInstanceState instancestate = testInstanceStateDao.getTestInstanceStateTestLevelByUserIdAndTestId(userId, testId);
//
//		if (test != null) {
//
//			String sections[] = test.getSelectedSections().split(",");
//			Integer[] sectionArr = new Integer[sections.length];
//			for (int i = 0; i < sectionArr.length; i++) {
//
//				sectionArr[i] = Integer.parseInt(sections[i]);
//			}
//
//			List<TestInstanceStatistics> statisticsCountForQues = testStatisticDao.fetchStatisticsByUserId(userId,
//					sectionArr);
//			
//			//TestInstanceState instancestate = testInstanceStateDao.getTestInstanceStateByUserIdandTestLevel(userId, test.getTestLevel());
//		
//			
//
//			if (statisticsCountForQues.size() != 0) {
//				for (TestInstanceStatistics testInstanceStatistics1 : statisticsCountForQues) {
//
//					JSONObject instancesatejson = new JSONObject();
//					
//					
////					if (testInstanceStatistics1.getSection().getName().equalsIgnoreCase(Constants.psySecname)) {
////						
////					instancesatejson.put("TEST_INST_STAT_ID", testInstanceStatistics1.getTestInstanceStatisticId());
////					instancesatejson.put("DEPT", testInstanceStatistics1.getDepartment().getName());
////					instancesatejson.put("SEC", testInstanceStatistics1.getSection().getName());
////					
////					String str[] = findSequence(testInstanceStatistics1.getGrade());
////					String str1 = null;
////					if (str[3] != null && str[2] != null) {
////						 str1 = str[3] + " and " + str[2];
////					} else {
////					   	 str1 = " (NA)  ";
////					}
////					instancesatejson.put("PSYRESULT1", str1);
////					
////					String strarr[] = testInstanceStatistics1.getGrade().split("#");
////					String arr[] = strarr[9].split("-");
////					String str2 = null;
////					if (Integer.parseInt(arr[1]) >= 23) {						
////						str2 = "External";					
////					}else if (Integer.parseInt(arr[1]) < 23 && Integer.parseInt(arr[1]) != 0) {
////						str2 = "Internal";
////					}else if (Integer.parseInt(arr[1]) == 0) {
////						str2 = "(NA)";						
////					}
////					
////					instancesatejson.put("PSYRESULT2", str2);
////					
////					String bfModelStr[] = findBigFiveFactor(testInstanceStatistics1.getGrade());
////					String str3 = null;
////					String str4 = "";
////					for (int p = 0; p < bfModelStr.length; p++) {
////						str3 = "<b>"+ bfModelStr[p];
////						if (p != bfModelStr.length - 1) {
////							str3 += "</b>,";
////						} else if (p == bfModelStr.length - 2) {
////							str3 += "</b> ";
////						} else {
////							str3 += "</b> ";
////						}
////						str4 +=  str3;
////					}
////					instancesatejson.put("PSYRESULT3", str4);
////					jsonArray.put(instancesatejson);
////						
////					}else{
//					
////					instancesatejson.put("TEST_INST_STAT_ID", testInstanceStatistics1.getTestInstanceStatisticId());
////					instancesatejson.put("DEPT", testInstanceStatistics1.getDepartment().getName());
////					instancesatejson.put("SEC", testInstanceStatistics1.getSection().getName());
////					instancesatejson.put("TOTALQ", testInstanceStatistics1.getTotalNoOfQuestions());
////					instancesatejson.put("PASSEDQ", testInstanceStatistics1.getPassedQuestions());					
////					instancesatejson.put("PERCENTAGE", testInstanceStatistics1.getPercentage());					
////					instancesatejson.put("GRADE", testInstanceStatistics1.getGrade());
////					instancesatejson.put("UID", testInstanceStatistics1.getUser().getUserId());
////					
////				
////					jsonArray.put(instancesatejson);
////					
////					}
//				}
//
//				data.put("done", true);
//				data.put("data", jsonArray);
//				
////				if(instancestate.getScoreCardLink() != null){
//					data.put("scorecardlink", instancestate.getScoreCardLink());
////				}
//				
//				data.put("testlevel", instancestate.getTestLevel());
//				data.put("userId", instancestate.getUser().getUserId());
//				data.put("isPdfGenerated", instancestate.isPdfGenerate());
//				data.put("testNm", instancestate.getTest().getName());
//			    data.put("EmailId", instancestate.getUser().getEmailId());
//			} else {
//				data.put("msg", "The test is not completed yet.");
//				data.put("done", false);
//
//			}
//
//		}
//		return data;
//	}
//	
//	
//	// getting the maximum value
//	public static String[] findSequence(String grade) {
//
//		// String str =
//		// "A-1# C-3# M-3# T-2# SE-T-9# IPEE-F-11# CU-T-10# TA-F-7# TMA-F-6# LOC-27";
//
//		String strarr[] = grade.split("#");
//
//		String string[] = new String[4];
//		int[] auto = new int[4];
//		String temp[] = new String[4];
//
//		for (int i = 0; i < 4; i++) {
//			String[] str1 = strarr[i].split("-");
//
//			auto[i] = Integer.parseInt(str1[1]);
//			temp[i] = str1[1];
//		}
//
//		Arrays.sort(auto);
//
//		// Arrays.asList(auto).remove();
//
//		for (int i = 0; i < 4; i++) {
//
//			int index = Arrays.asList(temp).indexOf(auto[i] + "");
//
//			// int index =ArrayUtils.indexOf(arr, 2);
//
//			if (index == 0 && Integer.parseInt(temp[index]) != 0) {
//				string[i] = "Autonomy &amp; Independance";
//			} else if (index == 1 && Integer.parseInt(temp[index]) != 0) {
//				string[i] = "Creativity &amp; entrepreneurship";
//			} else if (index == 2 && Integer.parseInt(temp[index]) != 0) {
//				string[i] = "Managerial";
//			} else if (index == 3 && Integer.parseInt(temp[index]) != 0) {
//				string[i] = "Technical &amp; Analytical";
//			}
//
//			temp[index] = "4";
//		}
//
//		// for (int i = string.length - 1; i >= 0; i--) {
//		// if (string[i] != null)
//		// System.out.println(string[i]);
//		// }
//		return string;
//
//	}
//	
//	private static String[] findBigFiveFactor(String grade) {
//
//		String strarr[] = grade.split("#");
//
//		String bfModel[] = new String[5];
//		for (int j = 4; j < 9; j++) {
//
//			String tempArr[] = strarr[j].split("-");
//
//			if (tempArr[0].equals("IPEE")) {
//				if (tempArr[1].equals("T") && Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[0] = "Introverted and Passive";
//				} else if (Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[0] = "Extraverted and high energy";
//				} else {
//					bfModel[0] = " (NA) ";
//				}
//			} else if (tempArr[0].equals("TA")) {
//				if (tempArr[1].equals("T") && Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[1] = "Traditionalist(Closed)";
//				} else if (Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[1] = "Adventurer(Open)";
//				} else {
//					bfModel[1] = " (NA) ";
//				}
//			} else if (tempArr[0].equals("TMA")) {
//				if (tempArr[1].equals("T") && Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[2] = "Tough-Minded(Self-Centered)";
//				} else if (Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[2] = "Aggreable(Other-Centered)";
//				} else {
//					bfModel[2] = " (NA) ";
//				}
//			} else if (tempArr[0].equals("CU")) {
//				if (tempArr[1].equals("T") && Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[3] = "Conscientious";
//				} else if (Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[3] = "Undirected";
//				} else {
//					bfModel[3] = " (NA) ";
//				}
//			} else if (tempArr[0].equals("SE")) {
//				if (tempArr[1].equals("T") && Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[4] = "Stable";
//				} else if (Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[4] = "Emotional";
//				} else {
//					bfModel[4] = " (NA) ";
//				}
//			}
//
//		}
//
//		return bfModel;
//	}
//
//
//	@Override
//	public JSONObject gettestInstanceStateForScoreCardLink(long userId, int testLevel) throws Exception {
//		// TODO Auto-generated method stub
//		JSONObject data = new JSONObject();
//
//		TestInstanceState instancestate = testInstanceStateDao.getTestInstanceStateByUserIdandTestLevel(userId, testLevel);
//		data.put("USERID", instancestate.getUser().getUserId());
//		data.put("TSTLEVEL", instancestate.getTestLevel());
//		data.put("SCRCARDLINK",  instancestate.getScoreCardLink());
//		data.put("ISPDFGEN", instancestate.isPdfGenerate());
//		data.put("testNm", instancestate.getTest().getName());
//	    data.put("EmailId", instancestate.getUser().getEmailId());
//		data.put("done", true);
//		return data;
//	}	
//}
