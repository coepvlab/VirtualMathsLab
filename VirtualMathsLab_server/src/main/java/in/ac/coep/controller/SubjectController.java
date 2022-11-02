package in.ac.coep.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ac.coep.entity.User;
import in.ac.coep.service.SubjectService;
import in.ac.coep.vo.SubjectVO;

/**
 * @author Prashant
 *
 */

@Controller
@RequestMapping(value = "/subject")
public class SubjectController {

	private static final Logger LOGGER = Logger.getLogger(SubjectController.class);

	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value = "/api/create", method = RequestMethod.POST)
	public @ResponseBody String createSubject(Authentication authentication, @RequestBody SubjectVO subjectVO) {

		LOGGER.info("create subject ....Start");

		User user = (User) authentication.getPrincipal();

		JSONObject data = new JSONObject();
		try {

			data = subjectService.addSubject(subjectVO, user);

			return data.toString();
		} catch (Exception e) {

			LOGGER.error("create subject-", e);

			return null;
		}

	}

	@RequestMapping(value = "/api/get/subjects", method = RequestMethod.GET)
	public @ResponseBody String getAllSubjects() {

		LOGGER.info("get subject ....Start");

		JSONObject data = new JSONObject();
		try {
			
			data = subjectService.getAllSubjects();

			return data.toString();
		} catch (Exception e) {

			LOGGER.error("get subject -", e);

			return null;
		}

	}

}
