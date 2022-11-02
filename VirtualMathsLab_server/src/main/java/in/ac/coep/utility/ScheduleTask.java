/**
 * 
 */
package in.ac.coep.utility;

import org.springframework.stereotype.Service;

/**
 * @author Prashant
 *
 */
@Service
public class ScheduleTask {
//	private static final Logger LOGGER = Logger.getLogger(ScheduleTask.class);

//	@Autowired
//	private TestInstanceStateService testInstanceStateService;
//
//	@Autowired
//	private CreatePdfReportService createPdfReportService;
	
//	@Autowired
//	private TestInstanceStateDao testInstanceStateDao;
//	
//	@Autowired
//	private TestLinkService testLinkService;
	
//	@Autowired
//	private TestInstanceDao testInstanceDao;

//	@Autowired
//	private BatchJobService batchJobService;
	
//	@Autowired
//	private TestInstanceCompletionDao testInstanceCompletionDao;
	
	
//	@Autowired
//	private UserDao userDao;

	// @Scheduled(cron = "0 15 19 * * ?")
	public void moveDataFromTestInstanceToTestInstanceState() throws Exception {
		try {

//			 testInstanceStateService.moveDataFromTestInstanceToTestInstanceCompletion();

			// Date date = new Date(System.currentTimeMillis());
			// System.out.println("Testing scheduler" + date);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Scheduled(cron = "0 53 14 * * ?")
//	public void createStatisticsDataForReport() throws Exception {
//		try {
//
//			testInstanceStateService.createStatisticsDataForReport();
//
//			// Date date = new Date(System.currentTimeMillis());
//			// System.out.println("Testing scheduler" + date);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	@Scheduled(cron = "0 30 05 * * ?")//"0 11 11 24 8 ?"
//	public void createPDFReportsForUser() {
//		try {
//			createPdfReportService.getAllDataForPDFReport();
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}
//	}
	

//	@Scheduled(cron = "0 0/30 8-9 * * ?")//0 0/30 1-5 * * ? 
//	public void test() {
//		try {
//			System.out.println("hi");
//			createPdfReportService.getAllDataForPDFReport();
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}
//
//	}
	
	
//		@Scheduled(cron = "0 32 10 * * ?")//"0 11 11 24 8 ?"
//		public void updateUserCurrentRoundForNextRound() {
////		try {
//////			createPdfReportService.getAllDataForPDFReport();
////			
////			
////			List<TestInstanceState> data = testInstanceStateDao.getTestInstanceStateByStatus("C");
////			
////			
////			
////			for (TestInstanceState testInstanceState : data) {
////				
////				int currentTestLevel = testInstanceState.getTestLevel();
////					currentTestLevel += 1;
////				if(currentTestLevel >  Constants.LastRound ){
//////					break; // round 4 code
////				}else{
//////					testLinkService.generateLinkForNextRound(testInstanceState.getUser().getEmailId(), testInstanceState.getDepartment().getDepartmentId(), currentTestLevel);
////					
////				}
////				
////			}
////			
////		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}

//	}
		
		
		
		
//		@Scheduled(cron = "0 00 01 * * ?")//"0 11 11 24 8 ?"
		public void updateUserCurrentRoundForNextRoundForGanit() {
		try {
//			createPdfReportService.getAllDataForPDFReport();
			
			
//			List<TestInstanceState> data = testInstanceStateDao.getTestInstanceStateByStatus("C");
			
//			List<User> data = userDao.getAllUserList();
//			
//			for (User user : data) {
//				
////				int currentTestLevel = testInstanceState.getTestLevel();
////					currentTestLevel += 1;
////				if(currentTestLevel >  Constants.LastRound ){
//////					break; // round 4 code
////				}else{
////					testLinkService.generateLinkForNextRound(user.getEmailId(), user.getDepartment().getDepartmentId(), Constants.LastRound);
//					
////				}
//				
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}
	
		
		
//		@SuppressWarnings("static-access")
//		@Scheduled(cron = "0 00 03 * * ?")//"0 11 11 24 8 ?"
//		public void moveDataFromTestInstanseToTestInstanceComletion() {
//			
//			try {
//				
//				List<TestInstanceState> data = testInstanceStateDao.getTestInstanceStateByStatus("C");
//				
//				System.out.println("api start :"+new Date(System.currentTimeMillis()));
//				
////				for (TestInstanceState testInstanceState : data) {
////					
////					List<TestInstanceCompletion> testInstanceCompletionlist = testInstanceCompletionDao.getTestInstanceByTISTdfromCompletion(testInstanceState.getTestInstanceStateId(),testInstanceState.getUser().getUserId());
////					
////					Thread.sleep(500);
////					
////					if(testInstanceCompletionlist.isEmpty()){
////						
////							Thread thread = new Thread(new MoveTestInstanceToCompletionThread(batchJobService, testInstanceState, testInstanceDao));
////							thread.sleep(2000);
////							thread.start();
////					
////					}else{
////						
////						LOGGER.info("Records are already their in testInstance Completion for this user");
////					}
////				}
//				
//				System.out.println("api end :"+new Date(System.currentTimeMillis()));
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//	}
	
}
