/**
 * 
 */
package in.ac.coep.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.TestStatisticDao;
import in.ac.coep.entity.TestInstanceStatistics;
import in.ac.coep.entity.User;
import in.ac.coep.service.TestInstanceStatisticsService;

/**
 * @author Prashant
 *
 */
@Service
public class TestInstanceStatisticsServiceImpl implements TestInstanceStatisticsService{
	
//	private static final Logger LOGGER = Logger.getLogger(TestInstanceStatisticsServiceImpl.class);
	
	@Autowired
	private TestStatisticDao testStatisticDao;
	
//	@Autowired
//	private TestInstanceStateDao testInstanceStateDao;

	@Override
	public JSONObject getAllInstanceStatistics(User user) throws Exception {

		JSONObject json = new JSONObject();
		try {
			
			List<TestInstanceStatistics> instanceStatistics = testStatisticDao.getAlltestStatisticsRecordsByUserId(user.getUserId());

			if (instanceStatistics.size() != 0) {
			
				Set<Long> getTestInstanceStateIdSet = new HashSet<Long>();
				
				JSONArray jsonArray = new JSONArray();
				
				for (TestInstanceStatistics testInstanceStatistics : instanceStatistics) {
					
					JSONObject data = new JSONObject();
					System.out.println("topicId - "+testInstanceStatistics.getTopic().getTopicId()+" userId - "+testInstanceStatistics.getUser().getUserId()+" tInstateId - "+testInstanceStatistics.getTestInstanceState().getTestInstanceStateId());
					
					data.put("TOPICID", testInstanceStatistics.getTopic().getTopicId());
					data.put("TOPICNM", testInstanceStatistics.getTopic().getTopicName()+" - "+testInstanceStatistics.getTopic().getTopicName1());
					data.put("TESTNM", testInstanceStatistics.getTestInstanceState().getTest().getName());
					data.put("TOTALNOQUES", testInstanceStatistics.getTotalNoOfQuestions()); // total no of question
					data.put("PASSNOQUES", testInstanceStatistics.getPassedQuestions()); // pass no of question
					data.put("TISID", testInstanceStatistics.getTestInstanceState().getTestInstanceStateId()); // pass no of question

					getTestInstanceStateIdSet.add(testInstanceStatistics.getTestInstanceState().getTestInstanceStateId()); // this is to get total no test

					data.put("topic", testInstanceStatistics.getTopic().getTopicId());
					
					jsonArray.put(data);
				}

				json.put("TOTALTESTGIVEN", getTestInstanceStateIdSet.size());
				
				json.put("data", jsonArray);
				json.put("done", true);
//				json.put("msg", "Record Fetch Successfully..");
			} else {
				json.put("msg", "No Record Found");
				json.put("done", false);

			}
		} catch (Exception e) {

			e.printStackTrace();
			json.put("done", false);
			json.put("msg", "Something went wrong... Please try again.");
		}
		return json;

	}

	

}
