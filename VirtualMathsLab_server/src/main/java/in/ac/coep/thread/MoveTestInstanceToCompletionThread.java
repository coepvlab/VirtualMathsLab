///**
// * 
// */
//package in.ac.coep.thread;
//
//import java.util.List;
//
//import org.json.JSONObject;
//
//import in.ac.coep.dao.TestInstanceDao;
//import in.ac.coep.entity.TestInstance;
//import in.ac.coep.entity.TestInstanceState;
//import in.ac.coep.service.BatchJobService;
//
///**
// * @author Prashant
// *
// */
//public class MoveTestInstanceToCompletionThread implements Runnable {
//
//	BatchJobService batchJobService;
//	TestInstanceState testInstanceState;
//	TestInstanceDao testInstanceDao;
//
//	/**
//	 * @param testInstanceService
//	 * @param instanceState1
//	 * @param testInstanceDao
//	 */
//	public MoveTestInstanceToCompletionThread(BatchJobService batchJobService, TestInstanceState instanceState1,
//			TestInstanceDao testInstanceDao) {
//		// TODO Auto-generated constructor stub
//		this.batchJobService = batchJobService;
//		this.testInstanceState = instanceState1;
//		this.testInstanceDao = testInstanceDao;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see java.lang.Runnable#run()
//	 */
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//
//		try {
//			JSONObject data = new JSONObject();
//			data = batchJobService.moveDataFromTestInstanceToTestInstanceCompletion(testInstanceState);
//
////			if (data.getBoolean("done")) {
////				List<TestInstance> instances = testInstanceDao
////						.getTestInstanceByTISId(testInstanceState.getTestInstanceStateId());
////
////				for (TestInstance testInstance : instances) {
////					testInstanceDao.delete(testInstance);
////				}
////			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//}
