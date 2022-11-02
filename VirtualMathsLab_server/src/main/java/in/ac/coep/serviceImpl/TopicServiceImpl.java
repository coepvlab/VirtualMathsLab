/**
 * 
 */
package in.ac.coep.serviceImpl;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.QuestionGroupDao;
import in.ac.coep.dao.SubjectDao;
import in.ac.coep.dao.TopicDao;
import in.ac.coep.dao.TopicMappingDao;
import in.ac.coep.entity.QuestionGroup;
import in.ac.coep.entity.Subject;
import in.ac.coep.entity.Topic;
import in.ac.coep.entity.TopicMapping;
import in.ac.coep.service.TopicService;
import in.ac.coep.vo.TopicVO;

/**
 * @author Prashant
 *
 */

@Service
public class TopicServiceImpl implements TopicService{

	
	@Autowired
	private SubjectDao subjectDao;
	
	@Autowired
	private TopicDao topicDao;
	
	@Autowired
	private QuestionGroupDao questionGroupDao;
	
	@Autowired
	private TopicMappingDao topicMappingDao;

	private static final Logger LOGGER = Logger.getLogger(TopicServiceImpl.class);
	
	@Override
	public JSONObject addTopic(TopicVO topicVO) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("create topic ....Start");

		JSONObject data = new JSONObject();

		try {

			if (topicVO != null) {

				Topic topic = new Topic();
				topic.setTopicName(topicVO.getTopicName());
				topic.setTopicName1(topicVO.getTopicName1());
				topic.setTopicNo(topicVO.getTopicNo());
				topic.setLevel(topicVO.getLevel());
				
				Subject subject = subjectDao.getSubjectById(topicVO.getSubjectId());
				
				topic.setSubject(subject);

				topicDao.save(topic);

				data.put("done", true);
				data.put("msg", "Topic save successfully");

			} else {

				data.put("done", false);
				data.put("msg", "Something went wrong..");
			}

		} catch (Exception e) {
			// TODO: handle exception
			data.put("done", false);
			data.put("msg", "Something went wrong..");
		}
		LOGGER.info("create topic ....end");
		return data;
	}

	@Override
	public JSONObject assignParent(long parentId, long[]  topicId, String status) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
		
		try {
			
			Topic parentTopic = topicDao.getTopicByTopicId(parentId);
			Topic topic = null;
			String msgAdd = "Parent topic " + parentTopic.getTopicId() + " newly assigned to : ";
			String msgExist = "Parent topic " + parentTopic.getTopicId() + " already assigned to: ";
			
			for (int i = 0; i < topicId.length; i++) {
				
				topic = topicDao.getTopicByTopicId(topicId[i]);
				
				if (parentTopic.getTopicId() != topic.getTopicId()) {
					TopicMapping tm = topicDao.checkTopicMappingAlreadyExist(status, parentTopic, topic);

					if (tm == null) {
						TopicMapping mapping = new TopicMapping();
						mapping.setParent(parentTopic);
						mapping.setTopic(topic);
						mapping.setStatus(status);
						topicDao.saveTopicMapping(mapping);
						msgAdd = msgAdd.concat(topic.getTopicId() + ", ");
					} else {
						msgExist = msgExist.concat(tm.getTopic().getTopicId() + ", ");
					}
				}
			}
			
			data.put("done", true);
			data.put("msg", msgAdd + " and " + msgExist);	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			data.put("done", false);
			data.put("msg", "Something went wrong..");
		}

		return data;
	}

	
	
	@Override
	public JSONObject getLTopicsDetails() throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		try {
				List<Topic> topicList = topicDao.getAllTopicDetails();
				
				if (!topicList.isEmpty()) {
					JSONArray topicJsonArray = new JSONArray();

					for (Topic topicsList : topicList) {
						JSONObject topic = new JSONObject();
						topic.put("TID", topicsList.getTopicId());
						topic.put("TN", topicsList.getTopicName());
						topic.put("TN1", topicsList.getTopicName1());
						topic.put("TNO", topicsList.getTopicNo());
						topic.put("LNO", topicsList.getLevel());
						topicJsonArray.put(topic);
					}
					data.put("done", true);
					data.put("topicData", topicJsonArray); // to get topic data to all levels
				}else {
					data.put("done", false);
					data.put("msg", "Topic list is empty.. Please add topics.");
				}

		} catch (Exception e) {
			// TODO: handle exception
			data.put("done", false);
			data.put("msg", "Something went wrong.. Please try later.");
		}

		return data;
	}

	@Override
	public JSONObject checkAvailability(String topicNo) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		try {

			boolean isAvailable = false;

			if (topicNo != null) {

				Topic topic = topicDao.getTopicByTopicNo(topicNo);

				if (topic != null) {
					isAvailable = true;
				}

				data.put("chkavl", isAvailable);
				data.put("done", true);

			} else {
				data.put("msg", "Please enter level number");
				data.put("done", false);
			}

		} catch (Exception e) {
			// TODO: handle exception
			data.put("chkavl", true);
			data.put("msg", "Something went wrong..");
			data.put("done", false);
		}

		return data;
	}

	@Override
	public JSONObject assignPrerequisite(long topicId, long[] preId, String status) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
		
		try {
			
			Topic topic = topicDao.getTopicByTopicId(topicId);
			Topic preTopic = null;
			String msgAdd = "Prerequisite " + topic.getTopicId() + " newly assigned to : ";
			String msgExist = "Prerequisite " + topic.getTopicId() + " already assigned to: ";
			
			for (int i = 0; i < preId.length; i++) {

				preTopic = topicDao.getTopicByTopicId(preId[i]);

				if (topic.getTopicId() != preTopic.getTopicId()) {
					TopicMapping tm = topicDao.checkTopicMappingAlreadyExist(status, preTopic, topic);

					if (tm == null) {
						TopicMapping mapping = new TopicMapping();
						mapping.setParent(preTopic);
						mapping.setTopic(topic);
						mapping.setStatus(status);

						topicDao.saveTopicMapping(mapping);
						msgAdd = msgAdd.concat(preTopic.getTopicId() + ", ");
					} else {
						msgExist = msgExist.concat(tm.getParent().getTopicId() + ", ");
					}
				}
			}
			
			data.put("done", true);
			data.put("msg", msgAdd + " and  " + msgExist);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			data.put("done", false);
			data.put("msg", "Something went wrong..");
		}

		return data;
	}

	@Override
	public JSONObject updateTopic(TopicVO topicVO) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		Topic tp = topicDao.getTopicByTopicId(topicVO.getTopicId());

		tp.setTopicName(topicVO.getTopicName());
		tp.setTopicName1(topicVO.getTopicName1());
		topicDao.updateTopic(tp);

		data.put("done", true);
		data.put("msg", "Topic updated successfully");

		return data;
	}

	@Override
	public JSONObject deleteTopic(String topicNo) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		List<Topic> topicList = topicDao.getAllTopicByLikeFilterTopicNo(topicNo);

		if (topicList.size() != 0) {
			for (Topic topic : topicList) {

				List<TopicMapping> tmList = topicDao.getTopicMappingByTopicIdWithParent(topic.getTopicId());
				for (TopicMapping tm : tmList) {
					topicDao.deleteTopicMapping(tm);
				}

				List<TopicMapping> tmList1 = topicDao.getTopicMappingByTopicIdWithTopic(topic.getTopicId());
				for (TopicMapping tm : tmList1) {
					topicDao.deleteTopicMapping(tm);
				}

				topicDao.deleteTopic(topic);
			}

			data.put("done", true);
			data.put("msg", "Topic and topping mapping deleted successfully");
		} else {
			data.put("done", false);
			data.put("msg", "No record found");
		}

		return data;
	}

	@Override
	public JSONObject getTopicMappingDetails(String status) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();
		JSONArray tmAr = new JSONArray();
		List<TopicMapping> tmList = topicDao.getAllTopicMappingByStatus(status);
		for (TopicMapping tm : tmList) {

			JSONObject topicMap = new JSONObject();

			topicMap.put("TMID", tm.getTopicMappingId());
			topicMap.put("PTMID", tm.getParent().getTopicId());
			topicMap.put("PTMNM", tm.getParent().getTopicName());
			topicMap.put("PTMNO", tm.getParent().getTopicNo());

			topicMap.put("TTMID", tm.getTopic().getTopicId());
			topicMap.put("TTMNM", tm.getTopic().getTopicName());
			topicMap.put("TTMNO", tm.getTopic().getTopicNo());

			tmAr.put(topicMap);
		}

		data.put("topicMap", tmAr);

		return data;
	}

	@Override
	public JSONObject updateParent(String parentTMJson) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
		
		JSONObject updateParentJson = new JSONObject(parentTMJson);
		
		String status = updateParentJson.getString("status");
		long parentId = updateParentJson.getLong("parentId");
		JSONArray topicIds = updateParentJson.getJSONArray("topicId");
		JSONArray removedTMId = updateParentJson.getJSONArray("removedTMId");
		
		Topic parentTopic = topicDao.getTopicByTopicId(parentId);
		Topic topic = null;
		String msgAdd = "Parent topic " + parentTopic.getTopicId() + " newly assigned to : ";
		String msgExist = "Parent topic " + parentTopic.getTopicId() + " already assigned to: ";
		String msgRemove = "Parent topic " + parentTopic.getTopicId() + " removed assigned with: ";
		
		for (int i = 0; i < topicIds.length(); i++) {
			long tpm = Long.parseLong((topicIds.get(i)).toString());

			topic = topicDao.getTopicByTopicId(tpm);

			if (parentTopic.getTopicId() != topic.getTopicId()) {
				TopicMapping tm = topicDao.checkTopicMappingAlreadyExist(status, parentTopic, topic);

				if (tm == null) {
					TopicMapping mapping = new TopicMapping();
					mapping.setParent(parentTopic);
					mapping.setTopic(topic);
					mapping.setStatus(status);
					topicDao.saveTopicMapping(mapping);
					msgAdd = msgAdd.concat(topic.getTopicId() + ", ");
				} else {
					msgExist = msgExist.concat(tm.getTopic().getTopicId() + ", ");
				}
			}
		}
		
		for(int i=0; i< removedTMId.length();i++) {
			long tpm =Long.parseLong((removedTMId.get(i)).toString());
			
			topic = topicDao.getTopicByTopicId(tpm);
			TopicMapping tm = topicDao.checkTopicMappingAlreadyExist(status, parentTopic, topic);
			
			msgRemove = msgRemove.concat(tm.getTopic().getTopicId() + ", ");
			topicDao.removeToppingMapping(tm);
		}
		
		data.put("done", true);
		data.put("msg", msgAdd + " # " + msgExist + " # " + msgRemove);
		return data;
	}

	@Override
	public JSONObject updatePrerequisite(String parentTMJson) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
		
		JSONObject updateParentJson = new JSONObject(parentTMJson);
		
		String status = updateParentJson.getString("status");
		long parentId = updateParentJson.getLong("parentId");
		JSONArray topicIds = updateParentJson.getJSONArray("topicId");
		JSONArray removedTMId = updateParentJson.getJSONArray("removedTMId");
		
		Topic parentTopic = topicDao.getTopicByTopicId(parentId);
		Topic topic = null;
		String msgAdd = "Prerequisite " + parentTopic.getTopicId() + " newly assigned to : ";
		String msgExist = "Prerequisite " + parentTopic.getTopicId() + " already assigned to: ";
		String msgRemove = "Prerequisite " + parentTopic.getTopicId() + " removed assigned with: ";
		
		for (int i = 0; i < topicIds.length(); i++) {
			long tpm = Long.parseLong((topicIds.get(i)).toString());

			topic = topicDao.getTopicByTopicId(tpm);

			if (topic.getTopicId() != parentTopic.getTopicId()) {
				TopicMapping tm = topicDao.checkTopicMappingAlreadyExist(status, topic, parentTopic);

				if (tm == null) {
					TopicMapping mapping = new TopicMapping();
					mapping.setParent(topic);
					mapping.setTopic(parentTopic);
					mapping.setStatus(status);
					topicDao.saveTopicMapping(mapping);
					msgAdd = msgAdd.concat(topic.getTopicId() + ", ");
				} else {
					msgExist = msgExist.concat(tm.getParent().getTopicId() + ", ");
				}
			}
		}
		
		for(int i=0; i< removedTMId.length();i++) {
			long tpm =Long.parseLong((removedTMId.get(i)).toString());
			
			topic = topicDao.getTopicByTopicId(tpm);
			TopicMapping tm = topicDao.checkTopicMappingAlreadyExist(status, topic, parentTopic);
			
			msgRemove = msgRemove.concat(topic.getTopicId() + ", ");
			topicDao.removeToppingMapping(tm);
			
		}
		
		data.put("done", true);
		data.put("msg", msgAdd + " # " + msgExist + " # " + msgRemove);
		return data;
	}

	@Override
	public JSONObject deleteTomppingMappingByTMid(long tmId) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
		TopicMapping tm = topicDao.getTopicMappingByTMid(tmId);
		
		if(tm != null) {
			topicDao.removeToppingMapping(tm);
			
			data.put("done", true);
			data.put("msg", "Topic mapping deleted successfully");
		}else {
			data.put("done", false);
			data.put("msg", "Topic mapping deleted failed");
		}
		
		return data;
	}

	@Override
	public JSONObject getLTopicsToHomePage() throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		try {
			
				String topicLevel = "01";
				List<Topic> topicList = topicDao.getAllTopicDetailsByLevel(topicLevel);
				
				if (!topicList.isEmpty()) {
					JSONArray topicJsonArray = new JSONArray();

					for (Topic topicsList : topicList) {
						JSONObject topic = new JSONObject();
						topic.put("TID", topicsList.getTopicId());
						topic.put("TN", topicsList.getTopicName());
						topic.put("TN1", topicsList.getTopicName1());
						topic.put("TNO", topicsList.getTopicNo());
						topic.put("LNO", topicsList.getLevel());
						topic.put("RDNO", topicsList.getRedirectNo());
						topicJsonArray.put(topic);
					}
					data.put("done", true);
					data.put("topicData", topicJsonArray); // to get topic data to all levels
				}else {
					data.put("done", false);
					data.put("msg", "Topic list is empty.. Please add topics.");
				}

		} catch (Exception e) {
			// TODO: handle exception
			data.put("done", false);
			data.put("msg", "Something went wrong.. Please try later.");
		}

		return data;
	}

	
	
	
	@Override
	public JSONObject getTopicByTopicID(long topicId) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
		
		if (topicId != 0) {
			
			List<TopicMapping> topics =  topicDao.getChildTopicsByTopicId(topicId);
			if(topics.isEmpty() != true) {
			
				JSONArray topicJsonArray = new JSONArray();
				for (TopicMapping topic : topics) {
					JSONObject childData = new JSONObject();
					childData.put("TID", topic.getTopic().getTopicId());
					childData.put("TN", topic.getTopic().getTopicName());
					childData.put("TN1", topic.getTopic().getTopicName1());
					childData.put("TNO", topic.getTopic().getTopicNo());
					childData.put("LNO", topic.getTopic().getLevel());
					
					if (topic.getTopic().getRedirectNo() != null) {
						
						childData.put("RDNO", topic.getTopic().getRedirectNo());
						Topic redirectTopicNo = topicDao.getTopicByTopicNo(topic.getTopic().getRedirectNo());
						childData.put("RTN", redirectTopicNo.getTopicName());
						childData.put("RTN1", redirectTopicNo.getTopicName1());
					}
					
					
					topicJsonArray.put(childData);
				}
				
				data.put("data", topicJsonArray);
				data.put("done", true);
			}else {
				data.put("msg", "No Child Found");
				data.put("done", false);
			}
			
		}else {
			data.put("done", false);
			data.put("msg", "Something went wrong.. Please try later..");
		}
		
		return data;
	}

	@Override
	public JSONObject getVariationNoByTopicId(long[] topicId, String status) {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
		
		try {
			
			Topic topic = null;

			JSONArray jsonArray = new JSONArray();
 			
			boolean flag = false;
			
			 Set <String> set = new LinkedHashSet <String>();
			 Set <String> set2 = new LinkedHashSet <String>();
			int cnt = 0;
			for (int i = 0; i < topicId.length; i++) {
				
				topic = topicDao.getTopicByTopicId(topicId[i]);
				
				List<QuestionGroup> questionGroups =  questionGroupDao.getAllQuestionGroupsByTopicIdAndStatus(status , topic.getTopicId());
						
				if (!questionGroups.isEmpty()) {
					JSONObject object = new JSONObject();
					int j = 0;
					
					for (QuestionGroup questionGroup : questionGroups) {
						 if (set.add(questionGroup.getVarNo())) {
							 cnt++;
					       }
					}
					if (cnt != 0) {
							String varNo[] = new String[cnt];
							for (QuestionGroup questionGroup : questionGroups) {
								 if (set2.add(questionGroup.getVarNo())) {
										if (questionGroup.getVarNo() ==  null) {
											varNo[j] = "100";
											j++;
										}else {
											varNo[j] = questionGroup.getVarNo();
											j++;
										}
									 	
							       }
							}
							if (!(varNo[0].isEmpty())) {
								int [] varNoArray = new int[varNo.length];
//								String [] varNoArrayWithTopicNo = new String[varNo.length];
							      for(int ii = 0; ii < varNo.length; ii++) {
							    	  varNoArray[ii] = Integer.parseInt(varNo[ii]);
//							    	  varNoArrayWithTopicNo[ii] = topic.getTopicNo()+"-"+Integer.parseInt(varNo[ii]);
							      }
							      Arrays.sort(varNoArray);
//							      Arrays.sort(varNoArrayWithTopicNo);
							      object.put("VARNO", varNoArray );
//							      object.put("VARNOWITHTNO", varNoArrayWithTopicNo );
							}else {
								 object.put("VARNO", varNo );
							}
								
						object.put("TopicId", topic.getTopicId());
						object.put("TopicNo", topic.getTopicNo());
						object.put("TOTALVN", varNo.length);
					}else {
						
					}
					
					jsonArray.put(object);
					cnt = 0;
					flag = true;
				}else {
					
					flag = false;
				}
			}
			
			if (flag) {
				data.put("data", jsonArray); // not sorted
				data.put("done", true);
			}else {
				data.put("done", false);
				data.put("msg", "No questions found for selected topic");
			}
			
//			data.put("msg", msgAdd + " and " + msgExist);	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			data.put("done", false);
			data.put("msg", "Something went wrong..");
		}

		return data;
	}

	@Override
	public JSONObject getTopicNameByTopicID(long topicId) throws Exception {
		// TODO Auto-generated method stub
		
		Topic topic = topicDao.getTopicByTopicId(topicId);
		
		JSONObject data = new JSONObject();
		
		if (!(topic == null)) {
			data.put("TID", topic.getTopicId());
			
			data.put("TN", topic.getTopicName());
			data.put("TN1", topic.getTopicName1());
			data.put("TNO", topic.getTopicNo());
			data.put("LVL", topic.getLevel());
			data.put("TID", topic.getTopicId());
			
			data.put("done", true);
			data.put("msg", "Topic detail get successfull..");
		}else {
			data.put("done", false);
			data.put("msg", "Topic detail get failed..");
		}
		
		return data;
		
	}

	@SuppressWarnings("unused")
	@Override
	public JSONObject getParentTopicByTopicID(long topicId) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
		TopicMapping topicMapping = null;
		
		JSONObject TNODATA =  new JSONObject();
		
		JSONArray topicsArray = new JSONArray();
		 long newTopicId = 0;
		 int i = 0;
		    do{   
		    	
		    	JSONObject topicData = new JSONObject();
		    	if (newTopicId == 0) {
		    		 i++;
		    		 topicMapping = topicDao.getParentTopicsByTopicId(topicId);
//		    		 topicData.put("TID", topicId);
//		    		 topicsArray.put(topicData);
		    		 if (topicMapping != null) {
		    			 JSONObject topicData2 = new JSONObject();
						 topicData2.put(""+i, topicMapping.getParent().getTopicId()+"-"+topicMapping.getParent().getLevel());
						 TNODATA.put(""+topicMapping.getParent().getTopicId(), topicMapping.getParent().getTopicNo());
						 topicsArray.put(topicData2);
					}
				}else {
					 i++;
					 topicMapping = topicDao.getParentTopicsByTopicId(newTopicId);
						if (topicMapping != null) {
							 topicData.put(""+i, topicMapping.getParent().getTopicId()+"-"+topicMapping.getParent().getLevel());
							 TNODATA.put(""+topicMapping.getParent().getTopicId(), topicMapping.getParent().getTopicNo());
							 topicsArray.put(topicData);
						}
					
				}
		    	
		    	if (topicMapping != null) {
		    		 newTopicId = topicMapping.getParent().getTopicId();
		    		  System.out.println(topicMapping.getParent().getTopicId()+"-"+topicMapping.getParent().getLevel());
				}
		    	
		    }while(topicMapping != null);    
		
		if (!(topicsArray == null)) {
			
			data.put("data", topicsArray);
			data.put("TNODATA",  TNODATA);
//			Topic topic = topicDao.getTopicByTopicId(topicId);
			
			
//			JSONObject TDATA = getTopicNameByTopicID(topicId);
			
			
			data.put("done", true);
			data.put("msg", "Parent Topic detail get successfull..");
		}else {
			data.put("done", false);
			data.put("msg", "Parent Topic detail get failed..");
		}
		
		return data;
	}

	@Override
	public JSONObject getPrerequisiteTopicByTopicID(long topicId) throws Exception {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
//		TopicMapping topicMapping = null;
		
//		 topicMapping = topicDao.getPrerequisiteTopicsByTopicId(topicId);
		 
		 List<TopicMapping> topicMapping = topicDao.getPrerequisiteTopicsByTopicId(topicId);
		 String topicNames = "";
		 if (!topicMapping.isEmpty()) {
			 for (TopicMapping topicMapping2 : topicMapping) {
						
//						data.put("requisiteTopicNo", topicMapping2.getParent().getTopicId());
//						data.put("TOPICNM", topicMapping2.getParent().getTopicName()+" ("+topicMapping2.getParent().getTopicName1()+")");
						topicNames += topicMapping2.getParent().getTopicName()+" ("+topicMapping2.getParent().getTopicName1()+"), ";
			}
			 data.put("done", true);
			 data.put("TOPICNM",topicNames);
		 }else {
				data.put("done", false);
				data.put("msg", "Prequisite not found for the given topic");
			}
		
		return data;
		
	}

	@Override
	public JSONObject getnextTopicByTopicID(long topicId) throws Exception {
		// TODO Auto-generated method stub
		
		long newTopicId = getNextTopicAfterAllTestDone(topicId);
		
		JSONObject  data = new JSONObject();
		data.put("NTID", newTopicId);
		return data;
		
		
	}

	private long getNextTopicAfterAllTestDone(long currentTopicId) throws Exception {
		// TODO Auto-generated method stub
		
		long nextTopicId = 0l;
		long topicId = currentTopicId;
		System.out.println(topicId);
		
		TopicMapping topic = topicDao.getParentTopicsByTopicId(topicId); // first get the parent topic 
		
		List<TopicMapping> topicMapping = topicMappingDao.getTopicChildsByTopicId(topic.getParent().getTopicId());
		
		if (topicMapping.size() == 1) {
			
		}else {
			
			TopicMapping mapping = topicMapping.get(topicMapping.size()-1);
			
			if (mapping.getTopic().getTopicId() == topicId) { // this means if current topic is last in the subtopic
				System.out.println(mapping.getTopic().getTopicId());
				
				if (mapping.getTopic().getLevel().equals("02")) { // 2nd level
					System.out.println(mapping.getTopic().getLevel().equals("02"));
					
					TopicMapping getParent = topicDao.getParentTopicsByTopicId(mapping.getTopic().getTopicId());
					
					if (getParent.getTopic().getTopicNo().equals("12")) {
						
					}else {
						
						List<Topic> getVerticalTopicList = topicDao.getAllVerticalListByTopicLevel("01"); 
						int topicIndex = 0;
						for (Topic topic2 : getVerticalTopicList) {
							System.out.println(topic2.getTopicNo());
							if (topic2.getTopicNo().equals(getParent.getParent().getTopicNo())) {
								System.out.println(getVerticalTopicList.indexOf(topic2));
								topicIndex = getVerticalTopicList.indexOf(topic2);
								
							}
						}
						Topic nextTP =  getVerticalTopicList.get((topicIndex + 1)); // this will gives you the next vertical, now on that topic you need to find its children
//						nextTopicId = nextTP.getTopic().getTopicId();
						System.out.println(nextTP.getTopicId());
						
						List<TopicMapping> getchildren = topicDao.getChildTopicsByTopicId(nextTP.getTopicId()); 
						
						System.out.println(getchildren.size());
						for (TopicMapping topicMapping2 : getchildren) {
							List<TopicMapping> getchildren2 = topicDao.getChildTopicsByTopicId(topicMapping2.getTopic().getTopicId()); 
							
							if (getchildren2.isEmpty()) {
								System.out.println(topicMapping2.getTopic().getTopicId());
							}
						}
					}
					
				}
				
			}else {
				
				int index = 0;
				for (TopicMapping topicMapping2 : topicMapping) {
					
					if(topicMapping2.getTopic().getTopicId() == topicId) {
						index = topicMapping.indexOf(topicMapping2);
					}
				}
				
				TopicMapping nextTM =  topicMapping.get((index + 1));
				nextTopicId = nextTM.getTopic().getTopicId();
			}
			
			
		}
		
		return nextTopicId;
	}
	
//	List<TopicMapping> topicMapping = topicDao.getTopicMappingByTopicId(parentId,status);
//	
//	if(!topicMapping.isEmpty()) {
//		
//		for (TopicMapping topicMappings : topicMapping) {
//			
//			if(topicMappings.getTopic().getTopicId() == topicId[i]) {
//				data.put("done", true);
//				data.put("msg", topicMappings.getTopic().getTopicName()+"is already assgined. Please check again.");	
//			}
//		}
//		
//		
//	}
	

}
