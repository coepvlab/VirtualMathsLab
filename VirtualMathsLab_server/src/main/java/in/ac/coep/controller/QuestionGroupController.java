package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.entity.User;
import in.ac.coep.service.QuestionGroupService;
import in.ac.coep.vo.QuestionGroupVO;

/**
 * @author Prashant
 *
 */
@Controller
@RequestMapping(value = "/questionGroups")
public class QuestionGroupController {

	private static final Logger LOGGER = Logger.getLogger(QuestionGroupController.class);
	
	@Autowired
	private QuestionGroupService questionGroupService;
	
	/**
	 * 
	 * @param questionGroupVO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String addQuestionGroup(Authentication authentication, @RequestBody QuestionGroupVO questionGroupVO) {
		LOGGER.info("addQuestionGroup....Start");
		JSONObject data = new JSONObject();
		User user = (User) authentication.getPrincipal();
		try {
			data = questionGroupService.addQuestionGroup(questionGroupVO , user);
			
			LOGGER.info("addQuestionGroup....End");
			
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody String modifyQuestion(Authentication authentication, @RequestBody QuestionGroupVO questionGroupVO) {
		LOGGER.info("modifyQuestion....Start");
		JSONObject data = new JSONObject();
		User user = (User) authentication.getPrincipal();
		try {
			data = questionGroupService.modifyQuestionGroup(questionGroupVO , user);
			LOGGER.info("modifyQuestion....End");
			return data.toString();
		} catch (ConstraintViolationException exception) {
			LOGGER.info("This question can not be modified or delete.");
			LOGGER.error("Exception-", exception);
			data.put("done", false);
			data.put("msg", "This question is allocated for Test, so you can not modified or delete it.");
			return data.toString();
		}catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}
	
	
	
	@RequestMapping(value="/api/get/user/questGroups/{status}", method = RequestMethod.GET)
	public @ResponseBody String fetchQuestionsGroupsForUser(Authentication authentication,@PathVariable String status) {
		LOGGER.info("fetchQuestionGroup....Start");
		JSONObject data = new JSONObject();
		User user = (User) authentication.getPrincipal();
		try {
			data = questionGroupService.fetchQuestionsGroupByUserId(user.getUserId(), status);
			LOGGER.info("fetchQuestionGroup....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}

	
	@RequestMapping(value="/api/get/all/questGroups/{status}", method = RequestMethod.GET)
	public @ResponseBody String fetchAllQuestionsGroupsFromQuesBank(@PathVariable String status) {
		LOGGER.info("fetchQuestionGroup....Start");
		JSONObject data = new JSONObject();
		try {
			data = questionGroupService.fetchQuestionGroups(status);
			LOGGER.info("fetchQuestionGroup....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String fetchQuestionsGroupById(@RequestParam long QGID) {
		LOGGER.info("fetchQuestionGroup....Start");
		JSONObject data = new JSONObject();
		try {
			data = questionGroupService.fetchQuestionsGroupById(QGID);
			LOGGER.info("fetchQuestionGroup....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}
	
	
	
	/**
	 * DELETE -- Archived
	 * @param questionGroupId
	 * @return
	 */
	@RequestMapping(value = "/{questionGroupId}",method = RequestMethod.DELETE)
	public @ResponseBody String archivedQuestionsGroupById(@PathVariable long questionGroupId) {
		LOGGER.info("fetchQuestionGroup....Start");
		JSONObject data = new JSONObject();
		try {
			data = questionGroupService.archivedQuestionsGroupById(questionGroupId);
			LOGGER.info("fetchQuestionGroup....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}
	
	/**
	 * DELETE -- Archived
	 * @param questionGroupId
	 * @return
	 */
//	/approve/multiple/[{QGIDArr}]
	@RequestMapping(value = "archived/multiple/[{QGIDArr}]",method = RequestMethod.DELETE)
	public @ResponseBody String archivedMultipleQuestionsGroupById(Authentication authentication, @PathVariable long QGIDArr[]) {
		LOGGER.info("fetchQuestionGroup....Start");
		User user = (User) authentication.getPrincipal();
		JSONObject data = new JSONObject();
		try {
			System.out.println(QGIDArr);
			data = questionGroupService.archivedMultipleQuestionsGroupById(user, QGIDArr);
			LOGGER.info("fetchQuestionGroup....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}
	
	
	/**
	 * DELETE -- delete questionGroup permanently from database
	 * @param questionGroupId
	 * @return
	 */
	@RequestMapping(value = "/delete/archived/{questionGroupId}",method = RequestMethod.DELETE)
	public @ResponseBody String deleteQuestionsGroupByIdFromarchived(@PathVariable long questionGroupId) {
		LOGGER.info("fetchQuestionGroup....Start");
		JSONObject data = new JSONObject();
		try {
			data = questionGroupService.deleteQuestionsGroupById(questionGroupId);
			LOGGER.info("fetchQuestionGroup....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}
	
	
//	/**
//	 * This function is to approve question
//	 * @param questionGroupId
//	 * @return
//	 */
	@RequestMapping(value="/approve", method = RequestMethod.GET)
	public @ResponseBody String approveQuestionsGroupById(Authentication authentication, @RequestParam long QGID) {
		LOGGER.info("fetchQuestionGroup....Start");
		User user = (User) authentication.getPrincipal();
		JSONObject data = new JSONObject();
		try {
			data = questionGroupService.approveQuestionsGroupById(user, QGID);
			LOGGER.info("fetchQuestionGroup....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}
	
	

//	/**
//	 * This function is to approve question
//	 * @param questionGroupId
//	 * @return
//	 */
	@RequestMapping(value="/approve/multiple/[{QGIDArr}]", method = RequestMethod.GET)
	public @ResponseBody String approveMultipleQuestionsGroupById(Authentication authentication, @PathVariable long QGIDArr[]) {
		LOGGER.info("fetchQuestionGroup....Start");
		User user = (User) authentication.getPrincipal();
		JSONObject data = new JSONObject();
		try {
			data = questionGroupService.approveMultipleQuestionsGroupById(user, QGIDArr);
			LOGGER.info("fetchQuestionGroup....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}
	

	/**
	 * 
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/{status}", method = RequestMethod.GET)
	public @ResponseBody String fetchQuestionGroups(@PathVariable String status) {
		LOGGER.info("fetchQuestion....Start");
		JSONObject data = new JSONObject();
		try {
			data = questionGroupService.fetchQuestionGroups(status);
			LOGGER.info("fetchQuestion....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}
	
	
	
	
	
//	@RequestMapping(value="/api/get/topic/questGroups/{status}", method = RequestMethod.GET)
//	public @ResponseBody String fetchQuestionsGroupsForAllTopicsForFilter(@PathVariable String status) {
//		LOGGER.info("fetchQuestionGroup....Start");
//		JSONObject data = new JSONObject();
////		User user = (User) authentication.getPrincipal();
//		try {
//			data = questionGroupService.fetchQuestionsGroupsForFilter(status);
//			LOGGER.info("fetchQuestionGroup....End");
//			return data.toString();
//		} catch (Exception e) {
//			LOGGER.error("Exception-", e);
//			return null;
//		}
//
//	}
	
	// get topic from question group mapping
	@RequestMapping(value="/api/get/topic/questGroups/{status}", method = RequestMethod.GET)
	public @ResponseBody String fetchQuestionsGroupsForAllTopicsForFilterFromMapping(@PathVariable String status) {
		LOGGER.info("fetchQuestionGroup....Start");
		JSONObject data = new JSONObject();
//		User user = (User) authentication.getPrincipal();
		try {
			data = questionGroupService.fetchQuestionsGroupsForFilterFromMapping(status);
			LOGGER.info("fetchQuestionGroup....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}

	
	
	@RequestMapping(value = "/api/getData/{status}/[{topicID}]/[{variationNum}]/[{difficultyLevel}]", method = RequestMethod.POST)
	public @ResponseBody String getQuestionGroupsByFilter(@PathVariable String status, @PathVariable long[] topicID, @PathVariable long[] variationNum , @PathVariable int[] difficultyLevel ) {

		LOGGER.info(" topicmapping ....Start");

		JSONObject data = new JSONObject();
		try {

			data = questionGroupService.getQuestionGroupsByFilter(status, topicID, variationNum, difficultyLevel);
			LOGGER.info("topicmapping ....end");
			return data.toString();
			
		} catch (Exception e) {
			LOGGER.error("topicmapping - ", e);
			e.printStackTrace();
			return null;
		}

	}
	
	
	@RequestMapping(value = "/api/getData/{status}/[{topicID}]/[{variationNum}]/[{difficultyLevel}]/[{time}]", method = RequestMethod.POST)
	public @ResponseBody String changeTimeForGivenVariationNo(@PathVariable String status, @PathVariable long[] topicID, @PathVariable long[] variationNum , @PathVariable int[] difficultyLevel , @PathVariable int[] time ) {

		LOGGER.info(" topicmapping ....Start");

		JSONObject data = new JSONObject();
		try {

			data = questionGroupService.changeTimeForGivenVariationNo(status, topicID, variationNum, difficultyLevel,time);
			LOGGER.info("topicmapping ....end");
			return data.toString();
			
		} catch (Exception e) {
			LOGGER.error("topicmapping - ", e);
			e.printStackTrace();
			return null;
		}

	}
	

	
	
	

	

	
}
