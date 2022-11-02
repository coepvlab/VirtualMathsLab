///**
// * 
// */
//package in.ac.coep.service;
//
//import org.json.JSONObject;
//
//import in.ac.coep.vo.SectionVO;
//
///**
// * @author Prashant
// *
// */
//public interface SectionService {
//
//	/**
//	 * @param section
//	 * @return
//	 * @throws Exception
//	 */
//	public JSONObject addSection(SectionVO section) throws Exception;
//
//	/**
//	 * @param json
//	 * @return
//	 * @throws Exception
//	 */
//	public JSONObject updateSection(SectionVO sectionVO) throws Exception;
//
//	/**
//	 * @param secId
//	 * @return
//	 */
//	public JSONObject deleteSection(int secId) throws Exception;
//
//	/**
//	 * @param deptId
//	 * @return
//	 */
//	public JSONObject fetchSections(int deptId) throws Exception;
//
//	/**
//	 * 
//	 * @param deptId
//	 * @return
//	 * @throws Exception 
//	 */
//	public JSONObject fetchSectionsHavingAtleastOneQuestion(int deptId) throws Exception;
//
//}
