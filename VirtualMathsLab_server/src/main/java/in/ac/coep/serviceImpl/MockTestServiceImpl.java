package in.ac.coep.serviceImpl;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.constants.Constants;
import in.ac.coep.dao.MockTestDao;
import in.ac.coep.entity.PerformanceTag;
import in.ac.coep.entity.TestInstanceStatistics;
import in.ac.coep.entity.User;
import in.ac.coep.service.MockTestService;

@Service
public class MockTestServiceImpl implements MockTestService {

	@Autowired
	private MockTestDao mockTestDao;
	
	@Override
	public JSONObject generateTag(User user, long topicId, String topicNo) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
		
		String tno = topicNo.substring(0, 2);
		
		List<TestInstanceStatistics> tisList = mockTestDao.getTestByUserAndTopicId(user, topicId);
		int dodArr[] = new int[Constants.noOfPrevTestForGeneratingTab];
		float perArr[] = new float[Constants.noOfPrevTestForGeneratingTab];
		
		String varList = "";
		
		int i=0, cnt=0;
		char tag = '\0';
		if(!tisList.isEmpty()) {
			for(TestInstanceStatistics tis : tisList) {
				System.out.println(tis.getTestInstanceStatisticId());
				if(i < 3) {
					dodArr[i] = tis.getTestInstanceState().getComplexityLevel();
					perArr[i] = tis.getPercentage();
								
					if(perArr[i] > Constants.taggingPercentageCriteria) {
						cnt++;
					}
					
					i++;
				}
				varList += tis.getTestInstanceState().getTest().getVarNo() + ",";	
			}
		}
		
		if(cnt == 3) {
			if(dodArr[0] == 1) {
				tag = 'D';
			}else if(dodArr[0] == 2) {
				tag = 'C';
			}else if(dodArr[0] == 3) {
				tag = 'B';
			}else if(dodArr[0] >= 4) {
				tag = 'A';
			}
			
			PerformanceTag pt1 = mockTestDao.getRecordByVariationNo(Integer.parseInt(tno), user);
			
			if(pt1 != null) {
				boolean flag = false;
				String []arr = pt1.getTopics().split(",");
				for(int j=0; j < arr.length; j++) {
					if(arr[j].equals(String.valueOf(topicId))) {
						flag = true;
					}
				}
				
				if(flag == false) {
					pt1.setTopics(pt1.getTopics()+","+String.valueOf(topicId));
					pt1.setTag(tag);
					pt1.setUpdatedTime(System.currentTimeMillis());
					
					mockTestDao.updatePerformanceTag(pt1);
				}
			}else {
				
				PerformanceTag pt = new PerformanceTag();
				pt.setTopics(String.valueOf(topicId));
				pt.setVariationNo(Integer.parseInt(tno));
				pt.setTag(tag);
				pt.setUser(user);
				pt.setUpdatedTime(System.currentTimeMillis());
				
				mockTestDao.updatePerformanceTag(pt);
			}
			data.put("done", true);
			data.put("msg", "You are eligible for mock test");
			
		}else {
			data.put("done", true);
			data.put("msg", "You need some more practice. Please continue with practice test");
		}
		
		
		String args[] = varList.split(",");
		
		HashMap<String,Integer> varMap = new HashMap<String,Integer>(); 
		for (int j = 0; j < args.length; j++) {   
			varMap.put(args[j], j);   
	    }  
		 
		
		System.out.println(tag + " " + varMap.keySet());
		
		return data;
	}

}
