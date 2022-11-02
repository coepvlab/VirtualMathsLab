///**
// * 
// */
//package in.ac.coep.dao;
//
//import java.util.List;
//
//import in.ac.coep.entity.Section;
//
///**
// * @author Prashant
// *
// */
//public interface SectionDao {
//
//	/**
//	 * @param section
//	 */
//	public void addSection(Section section)throws Exception;
//
//	/**
//	 * @param section
//	 */
//	public void deleteSection(Section section)throws Exception;
//
//
//	/**
//	 * @param secId
//	 * @return Section object of selected sectionId
//	 */
//	public Section getSectionBySectionId(int secId)throws Exception;
//
//	/**
//	 * @param section
//	 */
//	public void updateSection(Section section)throws Exception;
//
//	/**
//	 * @param deptId
//	 * @return List<Section>
//	 */
//	public List<Section> fetchSections(int deptId)throws Exception;
//
//	/**
//	 * 
//	 * @param deptId
//	 * @return List<Section>
//	 * @throws Exception 
//	 */
//	public List<Section> fetchSectionsHavingAtleastOneQuestion(int deptId) throws Exception;
//
//
//
//	
//	
//	
//}
