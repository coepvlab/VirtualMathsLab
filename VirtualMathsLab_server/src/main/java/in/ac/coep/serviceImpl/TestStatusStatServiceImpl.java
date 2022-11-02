//package in.ac.coep.serviceImpl;
//
//import java.util.List;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import in.ac.coep.dao.TestInstanceStateDao;
//import in.ac.coep.dao.UserDao;
//import in.ac.coep.entity.TestInstanceState;
//import in.ac.coep.entity.User;
//import in.ac.coep.service.TestStatusStatService;
//
//
//@Service
//public class TestStatusStatServiceImpl implements TestStatusStatService{
//
//	
//	@Autowired
//	private UserDao userDao;
//
//	@Autowired
//	private TestInstanceStateDao instanceStateDao;
//	
//	
//	@Override
//	public JSONObject inTechDetails(int testLevel) throws Exception {
//		
//		
//		JSONObject jsonObject = new JSONObject();
//		int testCompletedCount = 0;
//		int totalPaymentDoneCount = 0;
//		int testInProgressCount = 0;
//		int totalRegisteredCount = 0;
//		int totalCurrentTestInProgressCount = 0;
//		try {
//			
//			List<TestInstanceState> instanceStates =  instanceStateDao.getTotalTestCompletedAndTestInProgressCount(testLevel);
//			
//			for (TestInstanceState testInstanceState : instanceStates) {
//				if(testInstanceState.getStatus().equals("C")){
//					testCompletedCount++;
//				}else if(testInstanceState.getStatus().equals("P")){
//				long time =  (System.currentTimeMillis() - 2400000); 
//				 
//				 if(testInstanceState.getTickTime().getTime() >= time ){
//					 totalCurrentTestInProgressCount++;
//				 }
//					testInProgressCount++;
//				}
//			}
//			
//			List<User> user = userDao.getAllUserList();
//			
//			for(User user1 : user) {
////				if(user1.isIsPaymentDone() == true ) {
////					totalPaymentDoneCount++;
////				}
//			}
//			
//			
//			List<User> users = userDao.listofUserbyRoleID(3);
//			
//			totalRegisteredCount = users.size();
//			
//			
//			jsonObject.put("TC", testCompletedCount);
//			jsonObject.put("PD", totalPaymentDoneCount);
//			jsonObject.put("TP", testInProgressCount);
//			jsonObject.put("TR", totalRegisteredCount);
//			jsonObject.put("TTP", totalCurrentTestInProgressCount);
//			jsonObject.put("done", true);
//			
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			jsonObject.put("done", false);
//			jsonObject.put("msg", "something went wrong..");
//		}
//		
//		
//		// TODO Auto-generated method stub
//		return jsonObject;
//	}
//	
//	
//	
//	
//	
//	@Override
//	public JSONObject inTechTotalRegistered() throws Exception {
//
//		JSONObject data = new JSONObject();
//		int totalRegisteredCount = 0;
//		try {
//			List<User> users = userDao.listofUserbyRoleID(3);
//			totalRegisteredCount = users.size();
//
//			data.put("TR", totalRegisteredCount);
//			data.put("done", true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			data.put("done", false);
//			data.put("msg", "something went wrong..");
//		}
//		return data;
//	}
//
//	@Override
//	public JSONObject totalStudInProgState(int testLevel) throws Exception {
//	
//	JSONObject data = new JSONObject();
//	JSONArray datac = new JSONArray();
//	JSONArray datap = new JSONArray();
//	JSONArray dataph = new JSONArray();
//	try {
//		int testCompletedCount = 0;
//		int testInProgressCount = 0;
//		int totalCurrentTestInProgressCount = 0;
//		
//			List<TestInstanceState> instanceStates =  instanceStateDao.getTotalTestCompletedAndTestInProgressCount(testLevel);
//			
//			for (TestInstanceState testInstanceState : instanceStates) {
//				if(testInstanceState.getStatus().equals("C")){
//					 JSONObject datac1 = new JSONObject();
//					 datac1.put("UID", (testInstanceState.getUser().getUserId()+ " - " + testInstanceState.getUser().getEmailId()));
//					 datac1.put("TSTINSTSTID", testInstanceState.getTestInstanceStateId());
//					 datac1.put("STS", testInstanceState.getStatus());
//					 datac1.put("TESTEND", testInstanceState.getTestEnd());
//					 datac1.put("TESTSTART", testInstanceState.getTestStart());
//					 datac1.put("URL", testInstanceState.getTestURL());
//					 datac1.put("TT", testInstanceState.getTickTime());
////					 datac1.put("DEPT", testInstanceState.getDepartment().getName());
//					 datac1.put("TSTID", testInstanceState.getTest().getTestId());
//					 datac1.put("WV", testInstanceState.getWindowViolationTime());
//					 datac1.put("RS", testInstanceState.getRescheduleNo());
//					 
//					 datac.put(datac1);
//					
//					testCompletedCount++;
//				}else if(testInstanceState.getStatus().equals("P")){
//				long time =  (System.currentTimeMillis() - 2400000); 
//				 
//				 if(testInstanceState.getTickTime().getTime() >= time ){
//					 JSONObject dataph1 = new JSONObject();
//					 dataph1.put("UID", (testInstanceState.getUser().getUserId()+ " - " + testInstanceState.getUser().getEmailId()));
//					 dataph1.put("TSTINSTSTID", testInstanceState.getTestInstanceStateId());
//					 dataph1.put("STS", testInstanceState.getStatus());
//					 dataph1.put("TESTEND", testInstanceState.getTestEnd());
//					 dataph1.put("TESTSTART", testInstanceState.getTestStart());
//					 dataph1.put("URL", testInstanceState.getTestURL());
//					 dataph1.put("TT", testInstanceState.getTickTime());
////					 dataph1.put("DEPT", testInstanceState.getDepartment().getName());
//					 dataph1.put("TSTID", testInstanceState.getTest().getTestId());
//					 dataph1.put("WV", testInstanceState.getWindowViolationTime());
//					 dataph1.put("RS", testInstanceState.getRescheduleNo());
//					 
//					 dataph.put(dataph1);
//					 totalCurrentTestInProgressCount++;
//				 }
//				
//				 JSONObject datap1 = new JSONObject();
////				 datap1.put("UID", testInstanceState.getUser().getUserId());
//				 
//				 datap1.put("UID", (testInstanceState.getUser().getUserId()+ " - " + testInstanceState.getUser().getEmailId()));
//				 datap1.put("TSTINSTSTID", testInstanceState.getTestInstanceStateId());
//				 datap1.put("STS", testInstanceState.getStatus());
//				 datap1.put("TESTEND", testInstanceState.getTestEnd());
//				 datap1.put("TESTSTART", testInstanceState.getTestStart());
//				 datap1.put("URL", testInstanceState.getTestURL());
//				 datap1.put("TT", testInstanceState.getTickTime());
////				 datap1.put("DEPT", testInstanceState.getDepartment().getName());
//				 datap1.put("TSTID", testInstanceState.getTest().getTestId());
//				 datap1.put("WV", testInstanceState.getWindowViolationTime());
//				 datap1.put("RS", testInstanceState.getRescheduleNo());
//				 datap.put(datap1);
//				 testInProgressCount++;
//				}
//			}
//			
//			data.put("TC1", testCompletedCount);
//			data.put("TC", datac);
//			data.put("TP1", testInProgressCount);
//			data.put("TP", datap);
//			data.put("TTP1", totalCurrentTestInProgressCount);
//			data.put("TTP", dataph);
//			data.put("done", true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			data.put("done", false);
//			data.put("msg", "something went wrong..");
//		}
//	return data;
//	}
//
//	@Override
//	public JSONObject studInProgStateUpdateByInstStId(TestInstanceState testInstanceState) throws Exception {
//		JSONObject data = new JSONObject();
//		
//		
//		if (testInstanceState.getTestInstanceStateId() != 0) {
//			TestInstanceState instanceState1 = instanceStateDao
//					.getTestInstanceStateById(testInstanceState.getTestInstanceStateId());
//			
//			instanceState1.setStatus(testInstanceState.getStatus());
//			instanceState1.setTestStart(testInstanceState.getTestStart());
//			instanceState1.setTestEnd(testInstanceState.getTestEnd());
////			instanceState1.setActive(testInstanceState.isActive());
//			
////			instanceState1.setPlannedStartTime(testInstanceState.getPlannedStartTime());
////			instanceState1.setPlannedTestEndTime(testInstanceState.getPlannedTestEndTime());
////			instanceState1.setActive(testInstanceState.isActive());
//			instanceStateDao.update(instanceState1);
//			data.put("done", true);
//		}else{
//			data.put("done", false);
//		}
//		return data;
//	}
//
//	@Override
//	public JSONObject totalStudInCompletedTestTimeBound(int testLevel, long startDate, long endDate) throws Exception {
//		
//		JSONObject data = new JSONObject();
//		JSONArray datac = new JSONArray();
//		try {
//			int testCompletedCount = 0;
//			
//				List<TestInstanceState> instanceStates =  instanceStateDao.getTotalTestCompletedAndTestInProgressCount(testLevel);
//				
//				for (TestInstanceState testInstanceState : instanceStates) {
//					if(testInstanceState.getTickTime().getTime() >= startDate &&  testInstanceState.getTickTime().getTime() < endDate) {
//					if(testInstanceState.getStatus().equals("C")){					 
//						 
//							 testCompletedCount++;
//							 JSONObject datac1 = new JSONObject();
//							 datac1.put("UID", (testInstanceState.getUser().getUserId()+ " - " + testInstanceState.getUser().getEmailId()));
//							 datac1.put("TSTINSTSTID", testInstanceState.getTestInstanceStateId());
//							 datac1.put("STS", testInstanceState.getStatus());
//							 datac1.put("TESTEND", testInstanceState.getTestEnd());
//							 datac1.put("TESTSTART", testInstanceState.getTestStart());
//							 datac1.put("URL", testInstanceState.getTestURL());
//							 datac1.put("TT", testInstanceState.getTickTime());
////							 datac1.put("DEPT", testInstanceState.getDepartment().getName());
//							 datac1.put("TSTID", testInstanceState.getTest().getTestId());
//							 datac1.put("WV", testInstanceState.getWindowViolationTime());
//							 datac1.put("RS", testInstanceState.getRescheduleNo());
//							 
//							 datac.put(datac1);
//						 }
//					}
//				}
//				data.put("TC1", testCompletedCount);
//				data.put("TC", datac);
//				data.put("done", true);
//			} catch (Exception e) {
//				e.printStackTrace();
//				data.put("done", false);
//				data.put("msg", "something went wrong..");
//			}
//		return data;
//		}
//
//	@Override
//	public JSONObject totalStudInProgressTestTimeBound(int testLevel, long startDate, long endDate) throws Exception {
//		
//		JSONObject data = new JSONObject();
//		JSONArray datap = new JSONArray();
//		try {
//			int testInProgressCount = 0;
//			
//				List<TestInstanceState> instanceStates =  instanceStateDao.getTotalTestCompletedAndTestInProgressCount(testLevel);
//				
//				for (TestInstanceState testInstanceState : instanceStates) {
//					if(testInstanceState.getTickTime().getTime() >= startDate &&  testInstanceState.getTickTime().getTime() < endDate) {
//						if(testInstanceState.getStatus().equals("P")){
//						
//						 JSONObject datap1 = new JSONObject();
//	//					 datap1.put("UID", testInstanceState.getUser().getUserId());
//						 
//						 datap1.put("UID", (testInstanceState.getUser().getUserId()+ " - " + testInstanceState.getUser().getEmailId()));
//						 datap1.put("TSTINSTSTID", testInstanceState.getTestInstanceStateId());
//						 datap1.put("STS", testInstanceState.getStatus());
//						 datap1.put("TESTEND", testInstanceState.getTestEnd());
//						 datap1.put("TESTSTART", testInstanceState.getTestStart());
//						 datap1.put("URL", testInstanceState.getTestURL());
//						 datap1.put("TT", testInstanceState.getTickTime());
////						 datap1.put("DEPT", testInstanceState.getDepartment().getName());
//						 datap1.put("TSTID", testInstanceState.getTest().getTestId());
//						 datap1.put("WV", testInstanceState.getWindowViolationTime());
//						 datap1.put("RS", testInstanceState.getRescheduleNo());
//						 datap.put(datap1);
//						 testInProgressCount++;
//						}
//					}
//				}
//				data.put("TP1", testInProgressCount);
//				data.put("TP", datap);
//				data.put("done", true);
//			} catch (Exception e) {
//				e.printStackTrace();
//				data.put("done", false);
//				data.put("msg", "something went wrong..");
//			}
//		return data;
//		}
//
//	@Override
//	public JSONObject totalStudInProgStateForLastHr(int testLevel) throws Exception {
//		
//		JSONObject data = new JSONObject();
//		JSONArray dataplh = new JSONArray();
//		try {
//			int totalCurrentTestInProgressCount = 0;
//			
//				List<TestInstanceState> instanceStates =  instanceStateDao.getTotalTestCompletedAndTestInProgressCount(testLevel);
//				
//				for (TestInstanceState testInstanceState : instanceStates) {
//					 if(testInstanceState.getStatus().equals("P")){
//					long time =  (System.currentTimeMillis() - 2400000); 
//					 
//					 if(testInstanceState.getTickTime().getTime() >= time ){
//						 JSONObject dataphl = new JSONObject();
//						 dataphl.put("UID", (testInstanceState.getUser().getUserId()+ " - " + testInstanceState.getUser().getEmailId()));
//						 dataphl.put("TSTINSTSTID", testInstanceState.getTestInstanceStateId());
//						 dataphl.put("STS", testInstanceState.getStatus());
//						 dataphl.put("TESTEND", testInstanceState.getTestEnd());
//						 dataphl.put("TESTSTART", testInstanceState.getTestStart());
//						 dataphl.put("URL", testInstanceState.getTestURL());
//						 dataphl.put("TT", testInstanceState.getTickTime());
////						 dataphl.put("DEPT", testInstanceState.getDepartment().getName());
//						 dataphl.put("TSTID", testInstanceState.getTest().getTestId());
//						 dataphl.put("WV", testInstanceState.getWindowViolationTime());
//						 dataphl.put("RS", testInstanceState.getRescheduleNo());
//						 
//						 dataplh.put(dataphl);
//						 totalCurrentTestInProgressCount++;
//					 }
//					}
//				}
//				
//				data.put("TTPH1", totalCurrentTestInProgressCount);
//				data.put("TTPH", dataplh);
//				data.put("done", true);
//			} catch (Exception e) {
//				e.printStackTrace();
//				data.put("done", false);
//				data.put("msg", "something went wrong..");
//			}
//		return data;
//		}
//	
//	
//}
