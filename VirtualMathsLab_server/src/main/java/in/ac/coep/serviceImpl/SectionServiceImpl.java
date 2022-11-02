///**
// * 
// */
//package in.ac.coep.serviceImpl;
//
//import java.util.List;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import in.ac.coep.dao.DepartmentDao;
//import in.ac.coep.dao.QuestionGroupDao;
//import in.ac.coep.dao.SectionDao;
//import in.ac.coep.entity.Department;
//import in.ac.coep.entity.Section;
//import in.ac.coep.service.SectionService;
//import in.ac.coep.vo.SectionVO;
//
///**
// * @author Prashant
// *
// */
//@Service
//public class SectionServiceImpl implements SectionService {
//
////	@Autowired
////	private SectionDao sectionDao;
//
//	@Autowired
//	private DepartmentDao departmentDao;
//
//	@Autowired
//	private QuestionGroupDao questionGroupDao;
//
//	/**
//	 * @return the sectionDao
//	 */
////	public SectionDao getSectionDao() {
////		return sectionDao;
////	}
//
//	/**
//	 * @param sectionDao
//	 *            the sectionDao to set
//	 */
////	public void setSectionDao(SectionDao sectionDao) {
////		this.sectionDao = sectionDao;
////	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see in.ac.coep.service.SectionService#addSection(java.lang.String)
//	 */
//	@Override
//	public JSONObject addSection(SectionVO sectionVO) throws Exception {
//		// TODO Auto-generated method stub
//		JSONObject data = new JSONObject();
//
//		if (sectionVO != null) {
//
//			// JSONObject jsonObject = new JSONObject(json);
//
//			Section section = new Section();
//
//			section.setName(sectionVO.getName());
//
//			Department department = departmentDao.getDepartmentById(sectionVO.getDepartment().getDepartmentId());
//
//			section.setDepartment(department);
//
//			this.getSectionDao().addSection(section);
//
//			data.put("done", true);
//			data.put("msg", "Section added successfully");
//		} else {
//			data.put("done", false);
//			data.put("msg", "");
//		}
//
//		return data;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see in.ac.coep.service.SectionService#updateSection(long)
//	 */
//	@Override
//	public JSONObject updateSection(SectionVO sectionVO) throws Exception {
//		// TODO Auto-generated method stub
//		JSONObject data = new JSONObject();
//
//		if (sectionVO != null) {
//
//			Section section = sectionDao.getSectionBySectionId(sectionVO.getSectionId());
//
//			section.setName(sectionVO.getName());
//
//			Department department = departmentDao.getDepartmentById(sectionVO.getDepartment().getDepartmentId());
//
//			section.setDepartment(department);
//
//			sectionDao.updateSection(section);
//
//			data.put("done", true);
//			data.put("msg", "Section added successfully");
//		} else {
//			data.put("done", false);
//			data.put("msg", "");
//
//		}
//
//		return data;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see in.ac.coep.service.SectionService#deleteSection(long)
//	 */
//	@Override
//	public JSONObject deleteSection(int secId) throws Exception {
//		// TODO Auto-generated method stub
//		JSONObject data = new JSONObject();
//		if (secId != 0) {
//
//			Section section = sectionDao.getSectionBySectionId(secId);
//
//			if (section != null) {
//				sectionDao.deleteSection(section);
//				data.put("done", true);
//				data.put("msg", "Section added successfully");
//			} else {
//				data.put("done", false);
//				data.put("msg", "Section Not available in database");
//			}
//
//		} else {
//			data.put("done", false);
//			data.put("msg", "");
//
//		}
//		return data;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see in.ac.coep.service.SectionService#fetchSections(long)
//	 */
//	@Override
//	public JSONObject fetchSections(int deptId) throws Exception {
//		// TODO Auto-generated method stub
//
//		JSONObject data = new JSONObject();
//
//		List<Section> sections = sectionDao.fetchSections(deptId);
//		JSONArray sectionArray = new JSONArray();
//		JSONObject sectionObj = null;
//
//		for (final Section section : sections) {
//
//			sectionObj = new JSONObject().put("SID", section.getSectionId()).put("SN", section.getName());
//
//			sectionArray.put(sectionObj);
//		}
//		data.put("done", true);
//		data.put("data", sectionArray);
//
//		return data;
//	}
//
//	@Override
//	public JSONObject fetchSectionsHavingAtleastOneQuestion(int deptId) throws Exception {
//
//		JSONObject data = new JSONObject();
//
//		List<Section> sections = sectionDao.fetchSectionsHavingAtleastOneQuestion(deptId);
//
//		JSONArray sectionArray = new JSONArray();
//		JSONObject sectionObj = null;
//
//		for (final Section section : sections) {
//
//			boolean result = questionGroupDao.checkQuestionGroupsExistForSectionId(section.getSectionId());
//
//			if (result) {
//				sectionObj = new JSONObject().put("SID", section.getSectionId()).put("SN", section.getName());
//				sectionArray.put(sectionObj);
//			}
//		}
//		data.put("done", true);
//		data.put("data", sectionArray);
//
//		return data;
//	}
//
//}
