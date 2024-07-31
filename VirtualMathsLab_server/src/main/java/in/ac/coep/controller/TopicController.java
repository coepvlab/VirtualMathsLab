package in.ac.coep.controller;

import org.apache.log4j.Logger;
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
import in.ac.coep.service.TopicService;
import in.ac.coep.vo.TopicVO;

/**
 * @author Prashant
 *
 */

@Controller
@RequestMapping(value = "/topic")
public class TopicController {
	
	private static final Logger LOGGER = Logger.getLogger(TopicController.class);
	
	@Autowired
	private TopicService topicService;

	@RequestMapping(value = "/api/create", method = RequestMethod.POST)
	public @ResponseBody String createLevelOne( @RequestBody TopicVO TopicVO) {

		LOGGER.info("create topic ....Start");
		
		JSONObject data = new JSONObject();
		try {

			data = topicService.addTopic(TopicVO);
			
			LOGGER.info("create topic ....end");
			return data.toString();
			
		} catch (Exception e) {
			LOGGER.error("create topic - ", e);
			return null;
		}

	}
	
	
	
	@RequestMapping(value = "/api/get/topics", method = RequestMethod.GET)
	public @ResponseBody String getTopicsDetails(Authentication authentication) {

		LOGGER.info("get topics ....Start");
		
		User user = (User) authentication.getPrincipal();

		JSONObject data = new JSONObject();
		try {
			
			data = topicService.getLTopicsDetails();
			data.put("UID", user.getUserId());
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("get topics failed-", e);
			return null;
		}
	}
	
	@RequestMapping(value = "/api/get/main/topics", method = RequestMethod.GET)
	public @ResponseBody String getTopicsDetails1(Authentication authentication) {

		LOGGER.info("get topics ....Start");
		
		User user = (User) authentication.getPrincipal();

		JSONObject data = new JSONObject();
		try {
			
			data = topicService.getLTopicsDetails1();
			data.put("UID", user.getUserId());
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("get topics failed-", e);
			return null;
		}
	}
	
	@RequestMapping(value = "/api/get/home/topics", method = RequestMethod.GET)
	public @ResponseBody String getTopicsToHomePage(Authentication authentication) {

		LOGGER.info("get topics ....Start");
		
		User user = (User) authentication.getPrincipal();

		JSONObject data = new JSONObject();
		try {
			
			data = topicService.getLTopicsToHomePage();
			data.put("UID", user.getUserId());
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("get topics failed-", e);
			return null;
		}
	}
	
	
	@RequestMapping(value = "/api/get/topicMapping", method = RequestMethod.GET)
	public @ResponseBody String getTopicMappingDetails(@RequestParam String status) {

		LOGGER.info("get topicMapping ....Start");

		JSONObject data = new JSONObject();
		try {
			
			data = topicService.getTopicMappingDetails(status);
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("get topicMapping failed-", e);
			return null;
		}
	}
	
	
	// to check availability
		@RequestMapping(value = "/api/chkavl", method = RequestMethod.GET)
		public @ResponseBody String checkAvailability(@RequestParam String topicNo) {

			LOGGER.info("get topic  ....Start");

			JSONObject data = new JSONObject();
			try {
				data = topicService.checkAvailability(topicNo);

				return data.toString();
			} catch (Exception e) {

				LOGGER.error("get topic-", e);

				return null;
			}

		}
	
	
	/**
	 * @param status -  it is used to decide whether sent topic is parent. 1 being child and 2 being prerequisite.
	 * @param topicId - topic id to which we assign parent 
	 * @param parentId - it is an array of topic id's to which assigned as parent
	 * @return 
	 */
	@RequestMapping(value = "/api/mapping/parent/{status}/{parentId}/[{topicId}]", method = RequestMethod.POST)
	public @ResponseBody String assignParent(@PathVariable String status ,@PathVariable long parentId, @PathVariable long[] topicId ) {

		LOGGER.info(" topicmapping ....Start");

		JSONObject data = new JSONObject();
		try {

			data = topicService.assignParent(parentId, topicId,  status);
			LOGGER.info("topicmapping ....end");
			return data.toString();
			
		} catch (Exception e) {
			LOGGER.error("topicmapping - ", e);
			e.printStackTrace();
			return null;
		}

	}

	@RequestMapping(value = "/api/mapping/parent/update", method = RequestMethod.PUT)
	public @ResponseBody String updateParent(@RequestBody String parentTMJson) {

		LOGGER.info(" topicmapping ....update Start");

		JSONObject data = new JSONObject();
		try {

			data = topicService.updateParent(parentTMJson);
			LOGGER.info("topicmapping ....update end");
			return data.toString();
			
		} catch (Exception e) {
			LOGGER.error("topicmapping - ", e);
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * @param status -  it is used to decide whether sent topic is  prerequisite. 1 being child and 2 being prerequisite.
	 * @param topicId - topic id to which we assign  prerequisite
	 * @param parentId - it is an array of topic id's to which assigned as child or prerequisite 
	 * @return 
	 */
	@RequestMapping(value = "/api/mapping/prerequisite/{status}/{topicId}/[{preId}]", method = RequestMethod.POST)
	public @ResponseBody String assignPrerequisite(@PathVariable String status ,@PathVariable long topicId, @PathVariable long[] preId ) {

		LOGGER.info(" topicmapping ....Start");

		JSONObject data = new JSONObject();
		try {

			data = topicService.assignPrerequisite(topicId, preId,  status);
			LOGGER.info("topicmapping ....end");
			return data.toString();
			
		} catch (Exception e) {
			LOGGER.error("topicmapping - ", e);
			e.printStackTrace();
			return null;
		}

	}
	
	@RequestMapping(value = "/api/mapping/prerequisite/update", method = RequestMethod.PUT)
	public @ResponseBody String updatePrerequisite(@RequestBody String parentTMJson) {

		LOGGER.info(" topicmapping ....update Start");

		JSONObject data = new JSONObject();
		try {

			data = topicService.updatePrerequisite(parentTMJson);
			LOGGER.info("topicmapping ....update end");
			return data.toString();
			
		} catch (Exception e) {
			LOGGER.error("topicmapping - ", e);
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/api/mapping/delete/{tmId}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteTopicMapping(@PathVariable long tmId) {

		LOGGER.info(" topicmapping ....delete Start");

		JSONObject data = new JSONObject();
		try {

			data = topicService.deleteTomppingMappingByTMid(tmId);
			LOGGER.info("topicmapping ....delete end");
			return data.toString();
			
		} catch (Exception e) {
			LOGGER.error("topicmapping - ", e);
			e.printStackTrace();
			return null;
		}

	}
	
	@RequestMapping(value = "/api/update", method = RequestMethod.PUT)
	public @ResponseBody String updateTopic(@RequestBody TopicVO TopicVO) {

		LOGGER.info("update topic ....Start");

		JSONObject data = new JSONObject();
		try {

			data = topicService.updateTopic(TopicVO);
			LOGGER.info("update topic ....end");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("update topic - ", e);
			return null;
		}

	}
	
	@RequestMapping(value = "/api/delete", method = RequestMethod.DELETE)
	public @ResponseBody String deleteTopic(@RequestParam String topicNo) {

		LOGGER.info("delete topic ....Start");

		JSONObject data = new JSONObject();
		try {
			data = topicService.deleteTopic(topicNo);
			LOGGER.info("delete topic ....end");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("delete topic - ", e);
			return null;
		}

	}
	
	
	
	
	
	@RequestMapping(value = "/api/level", method = RequestMethod.GET)
	public @ResponseBody String getSubTopicsByTopicId(@RequestParam long topicId) {

		LOGGER.info("get child topics ....Start");

		JSONObject data = new JSONObject();
		try {
			data = topicService.getTopicByTopicID(topicId);
			LOGGER.info("get child topics ....end");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("child topics topic - ", e);
			return null;
		}

	}
	
	
//	@RequestMapping(value = "/api/getVN/[{topicId}]", method = RequestMethod.POST)
//	public @ResponseBody String getVariaionNoByTopicIds( @PathVariable long[] topicId ) {
//
//		LOGGER.info(" topicmapping ....Start");
//
//		JSONObject data = new JSONObject();
//		try {
//
////			data = topicService.assignParent(parentId, topicId,  status);
//			LOGGER.info("topicmapping ....end");
//			return data.toString();
//			
//		} catch (Exception e) {
//			LOGGER.error("topicmapping - ", e);
//			e.printStackTrace();
//			return null;
//		}
//
//	}
	
	
	/**
	 * @param status -  it is used to decide whether sent topic is parent. 1 being child and 2 being prerequisite.
	 * @param topicId - topic id to which we assign parent 
	 * @param parentId - it is an array of topic id's to which assigned as parent
	 * @return 
	 */
//	@RequestMapping(value = "/api/mapping/parent/[{topicId}]", method = RequestMethod.POST)
//	public @ResponseBody String getVariaionNoByTopicIds1(@PathVariable long[] topicId ) {
//
//		LOGGER.info(" topicmapping ....Start");
//
//		JSONObject data = new JSONObject();
//		try {
//
////			data = topicService.assignParent(parentId, topicId,  status);
//			LOGGER.info("topicmapping ....end");
//			return data.toString();
//			
//		} catch (Exception e) {
//			LOGGER.error("topicmapping - ", e);
//			e.printStackTrace();
//			return null;
//		}
//
//	}
	
	
	
	@RequestMapping(value = "/api/mapping/varNo/{status}/[{topicId}]", method = RequestMethod.POST)
	public @ResponseBody String getVariaionNoByTopicIds(@PathVariable String status , @PathVariable long[] topicId ) {

		LOGGER.info(" topicmapping ....Start");

		JSONObject data = new JSONObject();
		try {

			data = topicService.getVariationNoByTopicId(topicId,  status);
			LOGGER.info("topicmapping ....end");
			return data.toString();
			
		} catch (Exception e) {
			LOGGER.error("topicmapping - ", e);
			e.printStackTrace();
			return null;
		}

	}
	
	
	@RequestMapping(value = "/api/get/name", method = RequestMethod.GET)
	public @ResponseBody String getTopicNameByTopicId(@RequestParam long topicId) {

		LOGGER.info("get TopicNam  ....Start");

		JSONObject data = new JSONObject();
		try {
			data = topicService.getTopicNameByTopicID(topicId);
			LOGGER.info("get TopicNam  ....end");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("get TopicNam  ....failed - ", e);
			return null;
		}

	}
	
	
	
	@RequestMapping(value = "/api/get/parent", method = RequestMethod.GET)
	public @ResponseBody String getParentTopicsByTopicId(@RequestParam long topicId) {

		LOGGER.info("get ParentTopics ....Start");

		JSONObject data = new JSONObject();
		try {
			data = topicService.getParentTopicByTopicID(topicId);
			LOGGER.info("get ParentTopics ....end");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("get ParentTopics failed - ", e);
			return null;
		}

	}
	
	
	@RequestMapping(value = "/api/get/Prerquisite", method = RequestMethod.GET)
	public @ResponseBody String getPrerequisiteTopicsByTopicId(@RequestParam long topicId) {

		LOGGER.info("get Prerequisite Topics ....Start");

		JSONObject data = new JSONObject();
		try {
			data = topicService.getPrerequisiteTopicByTopicID(topicId);
			LOGGER.info("get Prerequisite  ....end");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("get Prerequisite failed - ", e);
			return null;
		}

	}
	
	
	@RequestMapping(value = "/api/get/nextTopic", method = RequestMethod.GET)
	public @ResponseBody String getnextTopicByTopicId(@RequestParam long topicId) {

		LOGGER.info("get nextTopic Topics ....Start");

		JSONObject data = new JSONObject();
		try {
			data = topicService.getnextTopicByTopicID(topicId);
			LOGGER.info("get nextTopic  ....end");
			return data.toString();
		} catch (Exception e) {
			LOGGER.error("get nextTopic failed - ", e);
			return null;
		}

	}
}
