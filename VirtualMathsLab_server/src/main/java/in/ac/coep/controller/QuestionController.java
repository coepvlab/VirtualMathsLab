/**
 * 
 */
package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import in.ac.coep.service.QuestionService;
import in.ac.coep.vo.QuestionVO;

/**
 * @author Prashant
 *
 */
@Controller
@RequestMapping(value = "/questions")
public class QuestionController {

	private static final Logger LOGGER = Logger.getLogger(QuestionController.class);

	@Autowired
	QuestionService questionService;

//	/**
//	 * 
//	 * @param questionVO
//	 * @return
//	 */
//	@RequestMapping(method = RequestMethod.POST)
//	public @ResponseBody String addQuestion(@RequestBody QuestionGroupVO questionGroupVO) {
//		LOGGER.info("addQuestion....Start");
//		JSONObject data = new JSONObject();
//		try {
//			data = questionService.addQuestion(questionGroupVO);
//			LOGGER.info("addQuestion....End");
//			return data.toString();
//		} catch (Exception e) {
//			LOGGER.error("Exception-", e);
//			return null;
//		}
//
//	}

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public @ResponseBody String echoFile() {

		return null;
	}

	@RequestMapping(value = "/uploadExcel/{sId}/{dId}", method = RequestMethod.POST)
	public @ResponseBody String uploadQuestionUsingExcelsheet(@PathVariable long dId,
 @RequestParam MultipartFile file,
			@PathVariable long sId) {

		JSONObject data = new JSONObject();
		try {
//			data = questionService.uploadQuestionExcel(file);

			return data.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * @param questionVO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody String modifyQuestion(@RequestBody QuestionVO questionVO) {
		LOGGER.info("modifyQuestion....Start");
		JSONObject data = new JSONObject();
		try {
//			data = questionService.modifyQuestion(questionVO);
			LOGGER.info("modifyQuestion....End");
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
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String fetchQuestionsByQuestionId(@RequestParam long questionId) {
		LOGGER.info("fetchQuestion....Start");
		JSONObject data = new JSONObject();
		try {
//			data = questionService.fetchQuestionsByQuestionId(questionId);
			LOGGER.info("fetchQuestion....End");
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
	public @ResponseBody String fetchQuestions(@PathVariable String status) {
		LOGGER.info("fetchQuestion....Start");
		JSONObject data = new JSONObject();
		try {
//			data = questionService.fetchQuestions(status);
			LOGGER.info("fetchQuestion....End");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("Exception-", e);
			return null;
		}

	}

	/**
	 * 
	 * delete existing Question
	 */
	@RequestMapping(value = "/{questionId}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteQuestion(@PathVariable long questionId) {

		JSONObject data = new JSONObject();

		try {
			data = questionService.deleteQuestion(questionId);
			return data.toString();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}
}
