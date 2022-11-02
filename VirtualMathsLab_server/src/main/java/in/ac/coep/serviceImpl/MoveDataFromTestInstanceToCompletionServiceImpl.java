package in.ac.coep.serviceImpl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.TestInstanceStateDao;
import in.ac.coep.entity.TestInstanceState;
import in.ac.coep.service.BatchJobService;
import in.ac.coep.service.MoveDataFromTestInstanceToCompletionService;



@Service
public class MoveDataFromTestInstanceToCompletionServiceImpl implements MoveDataFromTestInstanceToCompletionService {

//	private static final Logger LOGGER = Logger.getLogger(MoveDataFromTestInstanceToCompletionServiceImpl.class);
	
	@Autowired
	private TestInstanceStateDao testInstanceStateDao;
	
	@Autowired
	private BatchJobService batchJobService;
	

	@Override
	public JSONObject moveDataFromTestInstToComp(long userId, long tISID) throws Exception {
		
		JSONObject data = new JSONObject();
		
		try {
			
			TestInstanceState testInstanceState = testInstanceStateDao.getTestInstanceStateByUserIdandTestLevelAndTISID(userId, tISID);
			
			data = batchJobService.moveDataFromTestInstanceToTestInstanceCompletion(testInstanceState);		
			
			if (data.get("done").equals(true)) {
				
				testInstanceState.setStatus("C");
				testInstanceStateDao.update(testInstanceState);

				data.put("done", true);
				data.put("msg", "All Records Moved Successfully");

			}else{
				
				data.put("done", false);
				data.put("msg", "Records are already present in testInstaceCompletion for this user");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data.put("done", false);
			data.put("msg", "Something went wrong.. Please try later ");
		}
		return data;
		
	}
	
}
