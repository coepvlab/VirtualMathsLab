package in.ac.coep.serviceImpl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.TestInstanceDao;
import in.ac.coep.dao.TestInstanceStateDao;
import in.ac.coep.entity.Answers;
import in.ac.coep.entity.Question;
import in.ac.coep.entity.TestInstance;
import in.ac.coep.entity.TestInstanceState;
import in.ac.coep.service.TestInstanceService;
import in.ac.coep.vo.TestInstanceVO;

/**
 * @author Prashant
 *
 */
@Service
public class TestInstanceServiceImpl implements TestInstanceService {

	@Autowired
	private TestInstanceDao testInstanceDao;

	@Autowired
	private TestInstanceStateDao testInstanceStateDao;

//	@Autowired
//	private TestDao testDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.service.TestInstanceService#updateTestInstance(in.ac.coep.vo.
	 * TestInstanceVO)
	 */
	@Override
	public JSONObject updateTestInstance(TestInstanceVO[] testInstanceVO) throws Exception {
		// TODO Auto-generated method stub
		int i = 0;
		long totalTime = 0;
		long actualTotalTime = 0;
		
		JSONObject data = new JSONObject();
		for (TestInstanceVO testInstanceVO2 : testInstanceVO) {
			TestInstance testInstance = testInstanceDao.getTestInstanceById(testInstanceVO2.getTestInsId());

			if (i == 0) {
				TestInstanceState instanceState = testInstanceStateDao
						.getTestInstanceStateById(testInstance.getTestInstanceState().getTestInstanceStateId());
				instanceState.setLastQuestionId(testInstanceVO2.getQuesId());
				instanceState.setStatus("P");
//				instanceState.setTickTime(new Date(System.currentTimeMillis()));
				testInstanceStateDao.update(instanceState);

				i = 1;
			}
			
			if (testInstance.getActualAnsStartTime() != null && testInstance.getActualAnsEndTime() != null) {
				
			}
			
			Date aAnsEndTime = new Date(testInstanceVO2.getActAnsEndTime());
			Date aAnsStTime = new Date(testInstanceVO2.getActAnsStTime());
			testInstance.setActualAnsEndTime(aAnsEndTime);
			testInstance.setActualAnsStartTime(aAnsStTime);
			Answers answers = new Answers();
			answers.setAnswersId(testInstanceVO2.getAnsId());
			testInstance.setAnswersId(answers.getAnswersId());
			Question question = new Question();
			question.setQuestionId(testInstanceVO2.getQuesId());
			testInstance.setQuestionId(question.getQuestionId());
			
			 totalTime = aAnsEndTime.getTime() - aAnsStTime.getTime();
			 actualTotalTime = testInstance.getTotalTimeToAnswer() + totalTime;
			 
			 long minutes = TimeUnit.MILLISECONDS.toSeconds(actualTotalTime);
			
			 if (actualTotalTime > 0){
				 testInstance.setTotalTimeToAnswer(minutes); 
			 }else {
				 System.out.println(minutes);
			 }
			

			testInstanceDao.updateTestInstance(testInstance);
			
		}
		
		return data;
	}

//	@Override
//	public JSONObject updateTestInstanceGO(long tiId, boolean mcqFlag) throws Exception {
//		// TODO Auto-generated method stub
//		
//		JSONObject data = new JSONObject();
//		
//		if(mcqFlag == false){
//			
//			TestInstance testInstance = testInstanceDao.getTestInstanceById(tiId);
//			testInstanceDao.updateTestInstance(testInstance);
//			
//		}else{
//			
//			List<TestInstance> testInstance = testInstanceDao.getTestInstanceByQuesId(tiId); //Question ID
//			
//			for (TestInstance testInstance2 : testInstance) {
//				testInstanceDao.updateTestInstance(testInstance2);
//			}
//			
//			
//		}
//		
//		
//		return data;
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.service.TestInstanceService#
	 * fetchTestQuestionGroupsForResumeTest(long)
	 */
	/*@Override
	public JSONObject fetchTestQuestionGroupsForResumeTest(long userId) throws Exception {
		// TODO Auto-generated method stub
		if (userId != 0) {

			TestInstanceState instanceState = testInstanceStateDao.getTestInstanceStateByUserId(userId);

			Test test = testDao.getTestByTestId(instanceState.getTest().getTestId());

			String sections[] = test.getSelectedSections().split(",");

			JSONObject sectionObj = new JSONObject();
			JSONObject secObj = new JSONObject();
			JSONObject json = new JSONObject();
			for (int i = 0; i < sections.length; i++) {

				JSONArray array = new JSONArray();

				List<TestInstance> testInstances = testInstanceDao.getTestInstanceByQidUsrIdTSid(0, userId,
						instanceState.getTestInstanceStateId());

				for (TestInstance testInstance : testInstances) {

					JSONObject jsonObject = new JSONObject();
					JSONArray ques = new JSONArray();

					jsonObject.put("MEDTYP", testInstance.getQuestionGroup().getMediaType());
					jsonObject.put("QGID", testInstance.getQuestionGroup().getQuestionGroupId());

					for (Question question : testInstance.getQuestionGroup().getQuestions()) {

					}

				}

			}

		}

		return null;
	}*/

}
