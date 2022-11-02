///**
// * 
// */
//package in.ac.coep.controller;
//
//import org.apache.log4j.Logger;
//import org.hibernate.exception.ConstraintViolationException;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import in.ac.coep.service.SectionService;
//import in.ac.coep.vo.SectionVO;
//
///**
// * @author Prashant
// *
// */
//@Controller
//@RequestMapping(value = "/sections")
//public class SectionController {
//
//	private static final Logger LOGGER = Logger.getLogger(SectionController.class);
//
//
//	@Autowired
//	SectionService sectionService;
//	/**
//	 * 
//	 * @param section
//	 * @return {JSONObject} as a {String}
//	 * 
//	 *         Add New Section in database
//	 */
//	// @PreAuthorize()
//	@RequestMapping(method = RequestMethod.POST)
//	public @ResponseBody String addSection(@RequestBody SectionVO sectionVO) {
//		LOGGER.info("addSection....Start");
//		JSONObject data = new JSONObject();
//		try {
//			data = sectionService.addSection(sectionVO);
//			LOGGER.info("addSection....End");
//			return data.toString();
//		} catch (ConstraintViolationException exception) {
//			LOGGER.info("Section with this name already exist");
//			LOGGER.error("Exception-", exception);
//			data.put("err", true);
//			data.put("msg", "Section with this name already exist");
//			return data.toString();
//		}catch (Exception e) {
//			LOGGER.error("Exception-", e);
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//
//	}
//
//	/**
//	 * @param json
//	 * @return {JSONObject} as a {String}
//	 * 
//	 *         Update existing section
//	 */
//	@RequestMapping(method = RequestMethod.PUT)
//	public @ResponseBody String updateSection(@RequestBody SectionVO sectionVO) {
//		LOGGER.info("updateSection....Start");
//		JSONObject data = new JSONObject();
//		try {
//			data = sectionService.updateSection(sectionVO);
//			LOGGER.info("updateSection....End");
//			return data.toString();
//		} catch (Exception e) {
//			// TODO: handle exception
//			LOGGER.error("Exception-", e);
//			return null;
//		}
//	}
//
//	/**
//	 * 
//	 * @param secId
//	 * @return {JSONObject} as a {String}
//	 * 
//	 *         delete existing section
//	 */
//	@RequestMapping(method = RequestMethod.DELETE)
//	public @ResponseBody String deleteSection(@RequestBody int secId) {
//
//		JSONObject data = new JSONObject();
//
//		try {
//			data = sectionService.deleteSection(secId);
//			return data.toString();
//		} catch (Exception e) {
//			// TODO: handle exception
//			return null;
//		}
//
//	}
//
//	/**
//	 * 
//	 * @param deptId
//	 * @return
//	 * 
//	 * 		get all section of selected deptID 
//	 * 		OR 
//	 * 		if deptId is 0 then it will fetch all sections from database
//	 */
//	@RequestMapping(value = "/{deptId}",method = RequestMethod.GET)
//	public @ResponseBody String fetchSections(@PathVariable("deptId") int deptId) {
//
//		JSONObject data = new JSONObject();
//
//		try {
//			data = sectionService.fetchSections(deptId);
//			return data.toString();
//		} catch (Exception e) {
//			// TODO: handle exception
//			return null;
//		}
//
//	}
//}
