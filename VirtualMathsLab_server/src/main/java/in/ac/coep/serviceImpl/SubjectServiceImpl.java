package in.ac.coep.serviceImpl;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.SubjectDao;
import in.ac.coep.entity.Subject;
import in.ac.coep.entity.User;
import in.ac.coep.service.SubjectService;
import in.ac.coep.vo.SubjectVO;

@Service
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectDao subjectDao;
	
	@Override
	public JSONObject addSubject(SubjectVO subjectVO, User user) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
		
		try {
			
			if(subjectVO != null) {
				
				Subject subject = new Subject();
				
				subject.setSubName(subjectVO.getSubName());
				
				
				Date date = new Date(System.currentTimeMillis());
				subject.setCreatedDate(date);
				subject.setUpdatedDate(date);
				subject.setCreatedBy(String.valueOf(user.getUserId()));
				
				subjectDao.save(subject);
				
				data.put("done", true);
				data.put("msg", "Subject saved successfully");
				
				
			}else {
				data.put("done", false);
				data.put("msg", "Something went wrong..");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			data.put("done", false);
			data.put("msg", "Something went wrong..");
		}
		
		
		return data;
	}

	@Override
	public JSONObject getAllSubjects() throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();
		JSONArray subjectJsonArray = new JSONArray();

		try {
				List<Subject> subList = subjectDao.getAllSubjects();
				
				if (!subList.isEmpty()) {

				for (Subject subjectList : subList) {
					JSONObject subject = new JSONObject();
					subject.put("SID", subjectList.getSubjectId());
					subject.put("SN", subjectList.getSubName());
//					LevelOne.put("SNO", subjectList.getSubNo());
					subject.put("CB", subjectList.getCreatedBy());
					subjectJsonArray.put(subject);
				}
				data.put("done", true);
				data.put("subData", subjectJsonArray);
			} else {
				data.put("subData", subjectJsonArray);
				data.put("done", false);
				data.put("msg", "Something went wrong.. Please try later.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			data.put("subData", subjectJsonArray);
			data.put("done", false);
			data.put("msg", "Something went wrong.. Please try later.");
		}

		return data;
	}

}
