///**
// * 
// */
//package in.ac.coep.thread;
//
//import java.util.List;
//
//import in.ac.coep.dao.TestInstanceDao;
//import in.ac.coep.entity.TestInstance;
//
///**
// * @author Prashant
// *
// */
//public class DeleteTestInstancesThread implements Runnable {
//
//	TestInstanceDao testInstanceDao;
//
//	long testInstanceStateId;
//
//	/**
//	 * @param testInstanceDao
//	 * @param tisId
//	 */
//	public DeleteTestInstancesThread(TestInstanceDao testInstanceDao, long tisId) {
//		// TODO Auto-generated constructor stub
//
//		this.testInstanceDao = testInstanceDao;
//		this.testInstanceStateId = tisId;
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
//		List<TestInstance> instances;
//		try {
//			instances = testInstanceDao.getTestInstanceByTISId(testInstanceStateId);
//
//			for (TestInstance testInstance : instances) {
//				testInstanceDao.delete(testInstance);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//}
