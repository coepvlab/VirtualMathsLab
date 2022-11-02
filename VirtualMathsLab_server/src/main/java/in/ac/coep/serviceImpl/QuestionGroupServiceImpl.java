package in.ac.coep.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.constants.Constants;
import in.ac.coep.dao.QuesGroupMediaLinksDao;
import in.ac.coep.dao.QuestionDao;
import in.ac.coep.dao.QuestionGroupDao;
import in.ac.coep.dao.TopicDao;
import in.ac.coep.dao.UserDao;
import in.ac.coep.entity.Answers;
import in.ac.coep.entity.MediaType;
import in.ac.coep.entity.QGComplexityLevel;
import in.ac.coep.entity.QuesGroupMediaLinks;
import in.ac.coep.entity.Question;
import in.ac.coep.entity.QuestionGroup;
import in.ac.coep.entity.Subject;
import in.ac.coep.entity.Topic;
import in.ac.coep.entity.User;
import in.ac.coep.service.QuestionGroupService;
import in.ac.coep.service.QuestionService;
import in.ac.coep.vo.QuestionGroupVO;

/**
 * @author Prashant
 *
 */
@Service
public class QuestionGroupServiceImpl implements QuestionGroupService {

	@Autowired
	private QuestionGroupDao questionGroupDao;
	
//	@Autowired
//	private MediaTypeDao mediaTypeDao;

	@Autowired
	private QuestionService questionService;
	
//	@Autowired
//	private FileStorageOnMongo fileStorageOnMongo;
//	
//	@Autowired
//	private MediaService mediaService;
//	
	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	private TopicDao topicDao;
	
	@Autowired
	private UserDao userDao;

	
	@Autowired
	private QuesGroupMediaLinksDao quesGroupMediaLinksDao;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.service.QuestionGroupService#addQuestionGroup(in.ac.coep.vo.
	 * QuestionGroupVO)
	 */
	@Override
	public JSONObject addQuestionGroup(QuestionGroupVO questionGroupVO, User user) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		QuestionGroup questionGroup = new QuestionGroup();

		questionGroup = copyQuestionGroupToQuestionGroupVo(questionGroupVO, questionGroup, user);

		long questionGroupId = questionGroupDao.addQuestionGroup(questionGroup);

		questionGroup = questionGroupDao.getQuestionGroupById(questionGroupId);

		try {
			if (questionGroupVO.getQuestions() != null) {
				data = questionService.addQuestion(questionGroupVO, questionGroup);
			} else {
				data.put("QGID", questionGroupId);
				data.put("QGMLID", questionGroup.getQuesGroupMediaLinks().getQuesGroupMediaLinkId());
				data.put("msg", "Question Group added succesfully...");
				data.put("done", true);
			}
		} catch (NullPointerException exception) {
			data.put("QGID", questionGroupId);
			data.put("msg", "Question Group adding failed...");
			data.put("done", false);
			return data;
		}

		return data;
	}

	public QuestionGroup copyQuestionGroupToQuestionGroupVo(QuestionGroupVO questionGroupVO,
			QuestionGroup questionGroup,  User user) throws Exception {

		questionGroup.setCreationTime(System.currentTimeMillis());
		questionGroup.setUpdatedTime(System.currentTimeMillis());
		
		questionGroup.setArchive(false);
		questionGroup.setName(questionGroupVO.getName());
		questionGroup.setType(questionGroupVO.getType());
//		questionGroup.setContent(questionGroupVO.getContent());

		QGComplexityLevel complexityLevel = new QGComplexityLevel();
		complexityLevel.setQgComplexityLevelId(questionGroupVO.getLevel());
		questionGroup.setComplexityLevel(complexityLevel);
		
		Set<Topic> topicSet = new HashSet<>();
		for (int i = 0; i < questionGroupVO.getTopic().length; i++) {
			Topic topic = new Topic();
			if(questionGroupVO.getTopic()[i] != null) {
				topic = topicDao.getTopicByTopicId(questionGroupVO.getTopic()[i].getTopicId()); // zz
				topicSet.add(topic);
			}
		}
		
		if(topicSet.size() == 0) {
			Topic topic = new Topic();
			topic.setTopicId(1);
			topicSet.add(topic);
		}
		
		questionGroup.setTopicSet(topicSet);
		
		QuesGroupMediaLinks quesGroupMediaLinks = new QuesGroupMediaLinks();
		quesGroupMediaLinks.setQuesUsage(questionGroupVO.getQuesGroupMediaLinks().getQuesUsage());
		quesGroupMediaLinks.setMediaURLText(questionGroupVO.getQuesGroupMediaLinks().getMediaURLText());
		long quesGroupMediaLinkId = quesGroupMediaLinksDao.addQuesGroupMediaLinks(quesGroupMediaLinks);

		quesGroupMediaLinks = quesGroupMediaLinksDao.getQGMedilLinkById(quesGroupMediaLinkId);
		questionGroup.setQuesGroupMediaLinks(quesGroupMediaLinks);
		
		if(questionGroupVO.getCreatedBy() != null) {
//			User createdUser =  userDao.getUserByEmailId(questionGroupVO.getCreatedBy());
			questionGroup.setCreatedBy(String.valueOf(questionGroupVO.getCreatedBy()));
		}else {
			questionGroup.setCreatedBy(String.valueOf(38));
		}
		
		questionGroup.setApproved(false);
		
		Subject subject = new Subject();
		subject.setSubjectId(questionGroupVO.getSubject().getSubjectId());
		questionGroup.setSubject(subject);

//		questionGroup.setMedia(questionGroupVO.getMedia());

		MediaType mediaType = new MediaType();
		mediaType.setMediaTypeId(questionGroupVO.getMediaType().getMediaTypeId());
		questionGroup.setMediaType(mediaType);

		questionGroup.setVarNo(questionGroupVO.getVarNo());
		return questionGroup;
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.service.QuestionGroupService#fetchQuestionGroups(java.lang.
	 * String)
	 */
	@Override
	public JSONObject fetchQuestionGroups(String status) throws Exception {

		JSONObject data = new JSONObject();

		if (status.equalsIgnoreCase("Active")) {

			JSONArray questionGroupArr = new JSONArray();

			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsByStatus(status);

			if (questionGroups.size() != 0) {

				for (QuestionGroup questionGroup : questionGroups) {

					JSONObject questionGroupObj = new JSONObject();

					questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
					questionGroupObj.put("NME", questionGroup.getName());
					questionGroupObj.put("QGTYP", questionGroup.getType());
					
					questionGroupObj.put("varNo", questionGroup.getVarNo());
					
					questionGroupObj.put("QGLVL", questionGroup.getComplexityLevel().getQgComplexityLevelId());
					if(questionGroup.getQuesGroupMediaLinks().getQuesUsage().equals("question") ) {
						questionGroupObj.put("QGMEDIA", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
					}
					
					
					JSONArray topicArr = new JSONArray();
					Set<Topic> topics = questionGroup.getTopicSet();

					for (Topic topic : topics) {
						JSONObject topicData = new JSONObject();
						topicData.put("TID", topic.getTopicId());
						topicData.put("TNO", topic.getTopicNo());
						topicData.put("TNM", topic.getTopicName());
						topicData.put("TNM1", topic.getTopicName1());
						topicArr.put(topicData);
					}

					
					questionGroupObj.put("QGTOPICS", topicArr);
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy") ;
		            String creationDate = dateFormat.format(questionGroup.getCreationTime());
		            questionGroupObj.put("DATE", creationDate);
		            
		            
		            User user = userDao.getUserById(Long.parseLong(questionGroup.getCreatedBy()));
		            
		            questionGroupObj.put("CBY", user.getFirstName()+" "+user.getLastName());

					JSONArray questionArr = new JSONArray();

					Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
					
					for (Question question : myOrderedSet) {

						JSONObject questionObj = new JSONObject();

						questionObj.put("QID", question.getQuestionId());
						questionObj.put("QTXT", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
						questionObj.put("QTYP", question.getAnswerType().getName());
						questionObj.put("QTYPID", question.getAnswerType().getAnswerTypeId());
						questionObj.put("QGID", question.getQuestionGroup().getQuestionGroupId());
						questionObj.put("QTIM", question.getTime());
						questionObj.put("QSOLTYPE", question.getSolType());
						questionObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
						questionObj.put("QSOLMEDIA", question.getSolMedia());
						
						JSONArray ans = new JSONArray();
						for (Answers answer : question.getAnswers()) {

							JSONObject ansObj = new JSONObject();
							ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
							ansObj.put("rightAnswer", answer.isRightAnswer());
							ansObj.put("ansMedia", answer.getMedia());
							ans.put(ansObj);
						}
						questionObj.put("answers", ans);
						questionArr.put(questionObj);
					}
					
					questionGroupObj.put("QUESTION", questionArr);
					questionGroupArr.put(questionGroupObj);
				}
				
				data.put("done", true);
				data.put("data", questionGroupArr);
			} else {
				data.put("done", false);
				data.put("data", "Data Not Avilable");
			}
			
		} else if (status.equalsIgnoreCase("Archived")){

			JSONArray questionGroupArr = new JSONArray();

			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsByStatus(status);

			if (questionGroups.size() != 0) {

				for (QuestionGroup questionGroup : questionGroups) {

					JSONObject questionGroupObj = new JSONObject();

					questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
					questionGroupObj.put("NME", questionGroup.getName());
					questionGroupObj.put("QGTYP", questionGroup.getType());
					
					questionGroupObj.put("varNo", questionGroup.getVarNo());
					
					questionGroupObj.put("QGLVL", questionGroup.getComplexityLevel().getQgComplexityLevelId());
					if(questionGroup.getQuesGroupMediaLinks().getQuesUsage().equals("question") ) {
						questionGroupObj.put("QGMEDIA", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
					}
					
					
					JSONArray topicArr = new JSONArray();
					Set<Topic> topics = questionGroup.getTopicSet();

					for (Topic topic : topics) {
						JSONObject topicData = new JSONObject();
						topicData.put("TID", topic.getTopicId());
						topicData.put("TNO", topic.getTopicNo());
						topicData.put("TNM", topic.getTopicName());
						topicData.put("TNM1", topic.getTopicName1());
						topicArr.put(topicData);
					}

					
					questionGroupObj.put("QGTOPICS", topicArr);
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy") ;
		            String creationDate =dateFormat.format(questionGroup.getCreationTime());
		            questionGroupObj.put("DATE", creationDate);

		            
		            User user = userDao.getUserById(Long.parseLong(questionGroup.getCreatedBy()));
		            questionGroupObj.put("CBY", user.getFirstName()+" "+user.getLastName());
		            
					JSONArray questionArr = new JSONArray();

					Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
					
					for (Question question : myOrderedSet) {

						JSONObject questionObj = new JSONObject();

						questionObj.put("QID", question.getQuestionId());
						questionObj.put("QTXT", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
						questionObj.put("QTYP", question.getAnswerType().getName());
						questionObj.put("QTYPID", question.getAnswerType().getAnswerTypeId());
						questionObj.put("QGID", question.getQuestionGroup().getQuestionGroupId());
						questionObj.put("QTIM", question.getTime());
						questionObj.put("QSOLTYPE", question.getSolType());
						questionObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
						questionObj.put("QSOLMEDIA", question.getSolMedia());
						
						JSONArray ans = new JSONArray();
						for (Answers answer : question.getAnswers()) {

							JSONObject ansObj = new JSONObject();
							ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
							ansObj.put("rightAnswer", answer.isRightAnswer());
							ansObj.put("ansMedia", answer.getMedia());
							ans.put(ansObj);
						}
						questionObj.put("answers", ans);
						questionArr.put(questionObj);
					}
					
					questionGroupObj.put("QUESTION", questionArr);
					questionGroupArr.put(questionGroupObj);
				}
				
				data.put("done", true);
				data.put("data", questionGroupArr);
			} else {
				data.put("done", false);
				data.put("data", "Data Not Avilable");
			}
			
		}else if (status.equalsIgnoreCase("Non-Approved")){

			JSONArray questionGroupArr = new JSONArray();

			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsToApproveByStatus(status);

			if (questionGroups.size() != 0) {

				for (QuestionGroup questionGroup : questionGroups) {

					JSONObject questionGroupObj = new JSONObject();

					questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
					questionGroupObj.put("NME", questionGroup.getName());
					questionGroupObj.put("QGTYP", questionGroup.getType());
					
					questionGroupObj.put("varNo", questionGroup.getVarNo());
					
					questionGroupObj.put("QGLVL", questionGroup.getComplexityLevel().getQgComplexityLevelId());
					if(questionGroup.getQuesGroupMediaLinks().getQuesUsage().equals("question") ) {
						questionGroupObj.put("QGMEDIA", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
					}
					
					
					JSONArray topicArr = new JSONArray();
					Set<Topic> topics = questionGroup.getTopicSet();

					for (Topic topic : topics) {
						JSONObject topicData = new JSONObject();
						topicData.put("TID", topic.getTopicId());
						topicData.put("TNO", topic.getTopicNo());
						topicData.put("TNM", topic.getTopicName());
						topicData.put("TNM1", topic.getTopicName1());
						topicArr.put(topicData);
					}

					
					questionGroupObj.put("QGTOPICS", topicArr);
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy") ;
		            String creationDate =dateFormat.format(questionGroup.getCreationTime());
		            questionGroupObj.put("DATE", creationDate);

		            
		            User user = userDao.getUserById(Long.parseLong(questionGroup.getCreatedBy()));
		            questionGroupObj.put("CBY", user.getFirstName()+" "+user.getLastName());
		            
					JSONArray questionArr = new JSONArray();

					Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
					
					for (Question question : myOrderedSet) {

						JSONObject questionObj = new JSONObject();

						questionObj.put("QID", question.getQuestionId());
						questionObj.put("QTXT", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
						questionObj.put("QTYP", question.getAnswerType().getName());
						questionObj.put("QTYPID", question.getAnswerType().getAnswerTypeId());
						questionObj.put("QGID", question.getQuestionGroup().getQuestionGroupId());
						questionObj.put("QTIM", question.getTime());
						questionObj.put("QSOLTYPE", question.getSolType());
						questionObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
						questionObj.put("QSOLMEDIA", question.getSolMedia());
						
						JSONArray ans = new JSONArray();
						for (Answers answer : question.getAnswers()) {

							JSONObject ansObj = new JSONObject();
							ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
							ansObj.put("rightAnswer", answer.isRightAnswer());
							ansObj.put("ansMedia", answer.getMedia());
							ans.put(ansObj);
						}
						questionObj.put("answers", ans);
						questionArr.put(questionObj);
					}
					
					questionGroupObj.put("QUESTION", questionArr);
					questionGroupArr.put(questionGroupObj);
				}
				
				data.put("done", true);
				data.put("data", questionGroupArr);
			} else {
				data.put("done", false);
				data.put("data", "Data Not Avilable");
			}
		}else if (status.equalsIgnoreCase("Approved")){

			JSONArray questionGroupArr = new JSONArray();

			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsToApproveByStatus(status);

			if (questionGroups.size() != 0) {

				for (QuestionGroup questionGroup : questionGroups) {

					JSONObject questionGroupObj = new JSONObject();

					questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
					questionGroupObj.put("NME", questionGroup.getName());
					questionGroupObj.put("QGTYP", questionGroup.getType());
					
					questionGroupObj.put("varNo", questionGroup.getVarNo());
					
					questionGroupObj.put("QGLVL", questionGroup.getComplexityLevel().getQgComplexityLevelId());
					if(questionGroup.getQuesGroupMediaLinks().getQuesUsage().equals("question") ) {
						questionGroupObj.put("QGMEDIA", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
					}
					
					
					JSONArray topicArr = new JSONArray();
					Set<Topic> topics = questionGroup.getTopicSet();

					for (Topic topic : topics) {
						JSONObject topicData = new JSONObject();
						topicData.put("TID", topic.getTopicId());
						topicData.put("TNO", topic.getTopicNo());
						topicData.put("TNM", topic.getTopicName());
						topicData.put("TNM1", topic.getTopicName1());
						topicArr.put(topicData);
					}

					
					questionGroupObj.put("QGTOPICS", topicArr);
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy") ;
		            String creationDate =dateFormat.format(questionGroup.getCreationTime());
		            questionGroupObj.put("DATE", creationDate);

		            
		            User user = userDao.getUserById(Long.parseLong(questionGroup.getCreatedBy()));
		            questionGroupObj.put("CBY", user.getFirstName()+" "+user.getLastName());
		            
		            if (questionGroup.getApprovedBy() != null) {
		            	User approvedUser = userDao.getUserById(Long.parseLong(questionGroup.getApprovedBy()));
		            	 questionGroupObj.put("ABY", approvedUser.getFirstName()+" "+approvedUser.getLastName());
					}else {
						User approvedUser = userDao.getUserById(Long.parseLong("38"));
		            	 questionGroupObj.put("ABY", approvedUser.getFirstName()+" "+approvedUser.getLastName());
					}
		           
		            
					JSONArray questionArr = new JSONArray();

					Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
					
					for (Question question : myOrderedSet) {

						JSONObject questionObj = new JSONObject();

						questionObj.put("QID", question.getQuestionId());
						questionObj.put("QTXT", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
						questionObj.put("QTYP", question.getAnswerType().getName());
						questionObj.put("QTYPID", question.getAnswerType().getAnswerTypeId());
						questionObj.put("QGID", question.getQuestionGroup().getQuestionGroupId());
						questionObj.put("QTIM", question.getTime());
						questionObj.put("QSOLTYPE", question.getSolType());
						questionObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
						questionObj.put("QSOLMEDIA", question.getSolMedia());
						
						JSONArray ans = new JSONArray();
						for (Answers answer : question.getAnswers()) {

							JSONObject ansObj = new JSONObject();
							ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
							ansObj.put("rightAnswer", answer.isRightAnswer());
							ansObj.put("ansMedia", answer.getMedia());
							ans.put(ansObj);
						}
						questionObj.put("answers", ans);
						questionArr.put(questionObj);
					}
					
					questionGroupObj.put("QUESTION", questionArr);
					questionGroupArr.put(questionGroupObj);
				}
				
				data.put("done", true);
				data.put("data", questionGroupArr);
			} else {
				data.put("done", false);
				data.put("data", "Data Not Avilable");
			}
		}
		
		
		
		
		return data;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.service.QuestionGroupService#fetchQuestionsGroupById(long)
	 */
	@Override
	public JSONObject fetchQuestionsGroupById(long questionGroupId) throws Exception {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();

		if (questionGroupId != 0) {

			QuestionGroup questionGroup = questionGroupDao.getQuestionGroupById(questionGroupId);

			if (questionGroup != null) {

					JSONObject questionGroupObj = new JSONObject();

//					questionGroupObj.put("questionGroupId", questionGroup.getQuestionGroupId());
					questionGroupObj.put("name", questionGroup.getName());
					questionGroupObj.put("type", questionGroup.getType());
					questionGroupObj.put("level", questionGroup.getComplexityLevel().getQgComplexityLevelId()+"");
					
					questionGroupObj.put("varNo", questionGroup.getVarNo()); // get variation number
					
					questionGroupObj.put("subject", new JSONObject().put("subjectId", String.valueOf(questionGroup.getSubject().getSubjectId())));
					
					JSONObject quesGroupMediaLinks = new JSONObject();
					
					if(questionGroup.getQuesGroupMediaLinks().getQuesUsage().equals("question")) {
						quesGroupMediaLinks.put("quesGroupMediaLinkId", questionGroup.getQuesGroupMediaLinks().getQuesGroupMediaLinkId());
						quesGroupMediaLinks.put("quesUsage", questionGroup.getQuesGroupMediaLinks().getQuesUsage());
						quesGroupMediaLinks.put("mediaURLText", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
					}
					
					questionGroupObj.put("quesGroupMediaLinks", quesGroupMediaLinks);
					
					questionGroupObj.put("mediaType",
							new JSONObject().put("mediaTypeId", String.valueOf(questionGroup.getMediaType().getMediaTypeId())));
					
					JSONArray topicArr = new JSONArray();
					Set<Topic> topics = questionGroup.getTopicSet();

					for (Topic topic : topics) {
						JSONObject topicData = new JSONObject();
						topicData.put("topicId", topic.getTopicId());
//						topicData.put("topicName", topic.getTopicName());
						topicArr.put(topicData);
					}

					
					questionGroupObj.put("topic", topicArr);
					
					JSONArray questionArr = new JSONArray();

					Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
					
					for (Question question : myOrderedSet) {

						JSONObject questionObj = new JSONObject();

//						questionObj.put("QID", question.getQuestionId());
						questionObj.put("content", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />"));
						questionObj.put("answerType",
								new JSONObject().put("answerTypeId", question.getAnswerType().getAnswerTypeId()));
						
						questionObj.put("time", question.getTime());
						
						questionObj.put("solType", question.getSolType());
						questionObj.put("solText", question.getSolText());
						questionObj.put("solMedia", question.getSolMedia());
						
						JSONArray ans = new JSONArray();
						for (Answers answer : question.getAnswers()) {

							JSONObject ansObj = new JSONObject();
							ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />"));
							ansObj.put("rightAnswer", answer.isRightAnswer());
							ansObj.put("media", answer.getMedia());
							ans.put(ansObj);
						}
						questionObj.put("answers", ans);
						questionArr.put(questionObj);
					}
					
					questionGroupObj.put("questions", questionArr);
				
				data.put("done", true);
				data.put("data", questionGroupObj);
			} else {
				data.put("done", false);
				data.put("data", "Data Not Avilable");
			}	

		} else {
			data.put("done", false);
			data.put("data", "Please Try Later !!");
		}
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.service.QuestionGroupService#modifyQuestionGroup(in.ac.coep.vo
	 * .QuestionGroupVO)
	 */
	@Override
	public JSONObject modifyQuestionGroup(QuestionGroupVO questionGroupVO,  User user) throws ConstraintViolationException, Exception {

		JSONObject data = new JSONObject();

		QuestionGroup questionGroup = questionGroupDao.getQuestionGroupById(questionGroupVO.getQuestionGroupId());

		// delete all question of this questionGroup
		if (questionGroup.getQuestions() != null) {
			questionService.deleteAllQuestionByQuestionGroupId(questionGroup.getQuestionGroupId());
		}

		questionGroup = copyModifiedQuestionGroupToQuestionGroupVo(questionGroupVO, questionGroup, user);

		questionGroupDao.modifyQuestionGroup(questionGroup);

		questionGroup = questionGroupDao.getQuestionGroupById(questionGroup.getQuestionGroupId());

		try {
			if (questionGroupVO.getQuestions() != null) {
				data = questionService.addQuestion(questionGroupVO, questionGroup);
				data.put("QGID", questionGroup.getQuestionGroupId());
				data.put("QGMLID", questionGroup.getQuesGroupMediaLinks().getQuesGroupMediaLinkId());
				data.put("msg", Constants.quesGrpMsg);
				data.put("done", true);
			} else {
				data.put("QGID", questionGroup.getQuestionGroupId());
				data.put("QGMLID", questionGroup.getQuesGroupMediaLinks().getQuesGroupMediaLinkId());
				data.put("msg", Constants.quesGrpMsg);
				data.put("done", true);
			}

		} catch (NullPointerException exception) {
			data.put("QGID", questionGroup.getQuestionGroupId());
			data.put("QGMLID", questionGroup.getQuesGroupMediaLinks().getQuesGroupMediaLinkId());
			data.put("msg", "Question Group modification failed");
			data.put("done", false);
			return data;
		}

		return data;

	}

	private QuestionGroup copyModifiedQuestionGroupToQuestionGroupVo(QuestionGroupVO questionGroupVO,
			QuestionGroup questionGroup, User user) throws Exception {

		questionGroup.setCreationTime(System.currentTimeMillis());
		questionGroup.setUpdatedTime(System.currentTimeMillis());
		
		questionGroup.setArchive(false);
		questionGroup.setName(questionGroupVO.getName());
		questionGroup.setType(questionGroupVO.getType());
//		questionGroup.setContent(questionGroupVO.getContent());

		QGComplexityLevel complexityLevel = new QGComplexityLevel();
		complexityLevel.setQgComplexityLevelId(questionGroupVO.getLevel());
		questionGroup.setComplexityLevel(complexityLevel);

		Set<Topic> topicSet = new HashSet<>();
		for (int i = 0; i < questionGroupVO.getTopic().length; i++) {
			Topic topic = new Topic();
			topic = topicDao.getTopicByTopicId(questionGroupVO.getTopic()[i].getTopicId()); // zz
			topicSet.add(topic);
		}
		questionGroup.setTopicSet(topicSet);
		
//		if(questionGroup.getQuesGroupMediaLinks().getQuesGroupMediaLinkId() != questionGroupVO.getQuesGroupMediaLinks().getQuesGroupMediaLinkId()) {
			
			QuesGroupMediaLinks quesGroupMediaLinks = new QuesGroupMediaLinks();
			quesGroupMediaLinks = quesGroupMediaLinksDao.getQGMedilLinkById(questionGroupVO.getQuesGroupMediaLinks().getQuesGroupMediaLinkId());
			quesGroupMediaLinks.setQuesUsage(questionGroupVO.getQuesGroupMediaLinks().getQuesUsage());
			quesGroupMediaLinks.setMediaURLText(questionGroupVO.getQuesGroupMediaLinks().getMediaURLText());
//			long quesGroupMediaLinkId = quesGroupMediaLinksDao.addQuesGroupMediaLinks(quesGroupMediaLinks);

			quesGroupMediaLinksDao.updateQuesGroupMediaLinks(quesGroupMediaLinks);
//			quesGroupMediaLinks = quesGroupMediaLinksDao.getQGMedilLinkById(quesGroupMediaLinkId);
			questionGroup.setQuesGroupMediaLinks(quesGroupMediaLinks);
//		}
		
		
//		if(questionGroupVO.getCreatedBy() != null) {
//			User createdUser =  userDao.getUserByEmailId(questionGroupVO.getCreatedBy());
//			questionGroup.setCreatedBy(String.valueOf(createdUser.getUserId()));
//		}else {
//			questionGroup.setCreatedBy(String.valueOf(user.getUserId()));
//		}
		
		if (!questionGroup.isApproved()) {
			questionGroup.setApproved(false);
		}
		
		
		Subject subject = new Subject();
		subject.setSubjectId(questionGroupVO.getSubject().getSubjectId());
		questionGroup.setSubject(subject);

//		questionGroup.setMedia(questionGroupVO.getMedia());

		MediaType mediaType = new MediaType();
		mediaType.setMediaTypeId(questionGroupVO.getMediaType().getMediaTypeId());
		questionGroup.setMediaType(mediaType);
		
		questionGroup.setVarNo(questionGroupVO.getVarNo());

		return questionGroup;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.service.QuestionGroupService#archivedQuestionsGroupById(long)
	 */
	@Override
	public JSONObject archivedQuestionsGroupById(long questionGroupId) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		if (questionGroupId != 0) {
			QuestionGroup questionGroup = questionGroupDao.getQuestionGroupById(questionGroupId);

			if (questionGroup.isArchive()) {
				questionGroup.setArchive(false);
				questionGroupDao.modifyQuestionGroup(questionGroup);
				data.put("done", true);
				data.put("msg", Constants.quesGrpMsgActive);
			} else {
				questionGroup.setArchive(true);
				questionGroupDao.modifyQuestionGroup(questionGroup);
				data.put("done", true);
				data.put("msg", Constants.quesGrpMsgArchive);
			}
		} else {
			data.put("done", false);
			data.put("msg", "Unable to perform this operation now, please try later");
		}

		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.service.QuestionGroupService#
	 * fetchQuestionGroupsOnDifferentCeiteria(java.lang.String)
	 */
	@Override
	public JSONObject fetchQuestionGroupsOnDifferentCeiteria(String refineJson) throws Exception {

		JSONObject data = new JSONObject();

		if (refineJson != null) {

//			JSONObject filtersJson = new JSONObject(refineJson);

			JSONArray questionGroupArr = new JSONArray();

//			List<QuestionGroup> questionGroups = questionGroupDao.fetchQuestionGroupsOnDifferentCeiteria(filtersJson);

//			for (QuestionGroup questionGroup : questionGroups) {
//
//				JSONObject questionGroupObj = new JSONObject();
//
//				questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
//				questionGroupObj.put("NME", questionGroup.getName());
//				questionGroupObj.put("DEPT", questionGroup.getDepartmant().getName());
//				questionGroupObj.put("SEC", questionGroup.getSection().getName());
//				questionGroupObj.put("QGTYP", questionGroup.getType());
//				questionGroupObj.put("QGLVL", questionGroup.getComplexityLevel().getQgComplexityLevelId());
//
//				JSONArray questionArr = new JSONArray();
//
//				// List<Question> questions = questionDao
//				// .getQuestionsByQuestionGroupId(questionGroup.getQuestionGroupId());
//				Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
//				for (Question question : myOrderedSet) {
//
//					JSONObject questionObj = new JSONObject();
//
//					questionObj.put("QID", question.getQuestionId());
//					questionObj.put("QTXT", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />"));
//					questionObj.put("QTYP", question.getAnswerType().getName());
//					questionObj.put("QGID", question.getQuestionGroup().getQuestionGroupId());
//
//					questionArr.put(questionObj);
//				}
//				questionGroupObj.put("QUESTION", questionArr);
//
//				questionGroupArr.put(questionGroupObj);
//			}
			data.put("done", true);
			data.put("data", questionGroupArr);

		}

		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.ac.coep.service.QuestionGroupService#
	 * fetchLevelWiseCountOfQuestionGroupsBySections(int)
	 */
	@Override
	public JSONObject fetchLevelWiseCountOfQuestionGroupsBySections(int sectionId) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		if (sectionId != 0) {

			List<Object[]> list = questionGroupDao.fetchLevelWiseCountOfQuestionGroupsBySections(sectionId);

			if (list.size() != 0) {
				for (Object[] objects : list) {

					data.put("LVL" + objects[0], objects[1]);

				}

				// data.put("done", true);

			} else {
				data.put("done", false);
				data.put("msg", "QuestionGroups are not avilable for this section");
			}
		}

		return data;
	}

	@Override
	public JSONObject fetchQuestionsGroupByUserId(long userId, String status) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
		
		if (status.equalsIgnoreCase("Active")) {
		
		JSONArray questionGroupArr = new JSONArray();

		List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsByUserId(userId, status);

		if (questionGroups.size() != 0) {

			for (QuestionGroup questionGroup : questionGroups) {

				JSONObject questionGroupObj = new JSONObject();

				questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
				questionGroupObj.put("NME", questionGroup.getName());
				questionGroupObj.put("QGTYP", questionGroup.getType());
				
				questionGroupObj.put("varNo", questionGroup.getVarNo());
				
				questionGroupObj.put("QGLVL", questionGroup.getComplexityLevel().getQgComplexityLevelId());
				if(questionGroup.getQuesGroupMediaLinks().getQuesUsage().equals("question") ) {
					questionGroupObj.put("QGMEDIA", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
				}
				
				
				JSONArray topicArr = new JSONArray();
				Set<Topic> topics = questionGroup.getTopicSet();

				for (Topic topic : topics) {
					JSONObject topicData = new JSONObject();
					topicData.put("TID", topic.getTopicId());
					topicData.put("TNO", topic.getTopicNo());
					topicData.put("TNM", topic.getTopicName());
					topicData.put("TNM1", topic.getTopicName1());
					topicArr.put(topicData);
				}

				
				questionGroupObj.put("QGTOPICS", topicArr);
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy") ;
	            String creationDate =dateFormat.format(questionGroup.getCreationTime());
	            questionGroupObj.put("DATE", creationDate);

				JSONArray questionArr = new JSONArray();

				Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
				
				for (Question question : myOrderedSet) {

					JSONObject questionObj = new JSONObject();

					questionObj.put("QID", question.getQuestionId());
					questionObj.put("QTXT", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
					questionObj.put("QTYP", question.getAnswerType().getName());
					questionObj.put("QTYPID", question.getAnswerType().getAnswerTypeId());
					questionObj.put("QGID", question.getQuestionGroup().getQuestionGroupId());
					questionObj.put("QTIM", question.getTime());
					questionObj.put("QSOLTYPE", question.getSolType());
					questionObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
					questionObj.put("QSOLMEDIA", question.getSolMedia());
					
					JSONArray ans = new JSONArray();
					for (Answers answer : question.getAnswers()) {

						JSONObject ansObj = new JSONObject();
						ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />"));
						ansObj.put("rightAnswer", answer.isRightAnswer());
						ansObj.put("ansMedia", answer.getMedia());
						ans.put(ansObj);
					}
					questionObj.put("answers", ans);
					questionArr.put(questionObj);
				}
				
					questionGroupObj.put("QUESTION", questionArr);
					questionGroupArr.put(questionGroupObj);
				}
				
				data.put("done", true);
				data.put("data", questionGroupArr);
			} else {
				data.put("done", false);
				data.put("data", "Data Not Avilable");
			}	
		
		}else {
			
			JSONArray questionGroupArr = new JSONArray();

			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsByUserId(userId, status);

			if (questionGroups.size() != 0) {

				for (QuestionGroup questionGroup : questionGroups) {

					JSONObject questionGroupObj = new JSONObject();

					questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
					questionGroupObj.put("NME", questionGroup.getName());
					questionGroupObj.put("QGTYP", questionGroup.getType());
					
					questionGroupObj.put("varNo", questionGroup.getVarNo());
					
					questionGroupObj.put("QGLVL", questionGroup.getComplexityLevel().getQgComplexityLevelId());
					if(questionGroup.getQuesGroupMediaLinks().getQuesUsage().equals("question") ) {
						questionGroupObj.put("QGMEDIA", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
					}
					
					
					JSONArray topicArr = new JSONArray();
					Set<Topic> topics = questionGroup.getTopicSet();

					for (Topic topic : topics) {
						JSONObject topicData = new JSONObject();
						topicData.put("TID", topic.getTopicId());
						topicData.put("TNO", topic.getTopicNo());
						topicData.put("TNM", topic.getTopicName());
						topicData.put("TNM1", topic.getTopicName1());
						topicArr.put(topicData);
					}

					
					questionGroupObj.put("QGTOPICS", topicArr);
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy") ;
		            String creationDate =dateFormat.format(questionGroup.getCreationTime());
		            questionGroupObj.put("DATE", creationDate);

					JSONArray questionArr = new JSONArray();

					Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
					
					for (Question question : myOrderedSet) {

						JSONObject questionObj = new JSONObject();

						questionObj.put("QID", question.getQuestionId());
						questionObj.put("QTXT", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
						questionObj.put("QTYP", question.getAnswerType().getName());
						questionObj.put("QTYPID", question.getAnswerType().getAnswerTypeId());
						questionObj.put("QGID", question.getQuestionGroup().getQuestionGroupId());
						questionObj.put("QTIM", question.getTime());
						questionObj.put("QSOLTYPE", question.getSolType());
						questionObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
						questionObj.put("QSOLMEDIA", question.getSolMedia());
						
						JSONArray ans = new JSONArray();
						for (Answers answer : question.getAnswers()) {

							JSONObject ansObj = new JSONObject();
							ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />"));
							ansObj.put("rightAnswer", answer.isRightAnswer());
							ansObj.put("ansMedia", answer.getMedia());
							ans.put(ansObj);
						}
						questionObj.put("answers", ans);
						questionArr.put(questionObj);
					}
					
						questionGroupObj.put("QUESTION", questionArr);
						questionGroupArr.put(questionGroupObj);
					}
					
					data.put("done", true);
					data.put("data", questionGroupArr);
				} else {
					data.put("done", false);
					data.put("data", "Data Not Avilable");
				}	
			
			}
		
		
		return data;
	}

	@Override
	public JSONObject approveQuestionsGroupById(User user, long questionGroupId) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		if (questionGroupId != 0) {
			QuestionGroup questionGroup = questionGroupDao.getQuestionGroupById(questionGroupId);

			if (questionGroup.isApproved()) {
				questionGroup.setApproved(false);
				questionGroup.setApprovedBy(String.valueOf(user.getUserId()));
				questionGroupDao.modifyQuestionGroup(questionGroup);
				data.put("done", true);
				data.put("msg", "Question Moved to Non-Approved Successfully..");
			} else {
				questionGroup.setApproved(true);
				questionGroup.setApprovedBy(String.valueOf(user.getUserId()));
				questionGroupDao.modifyQuestionGroup(questionGroup);
				data.put("done", true);
				data.put("msg", "Question Approved Successfully..");
			}
		} else {
			data.put("done", false);
			data.put("msg", "Unable to perform this operation now, please try later");
		}

		return data;
	}

	@Override
	public JSONObject approveMultipleQuestionsGroupById(User user, long[] qGIDArr) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		if(qGIDArr.length != 0) {
			
			for (int i = 0; i < qGIDArr.length; i++) {
					QuestionGroup questionGroup = questionGroupDao.getQuestionGroupById(qGIDArr[i]);

					if (questionGroup.isApproved()) {
						questionGroup.setApproved(false);
						questionGroup.setApprovedBy(String.valueOf(user.getUserId()));
						questionGroupDao.modifyQuestionGroup(questionGroup);
						data.put("done", true);
						data.put("msg", qGIDArr.length+" Questions Rejected Successfully..");
					} else {
						questionGroup.setApproved(true);
						questionGroup.setApprovedBy(String.valueOf(user.getUserId()));
						questionGroupDao.modifyQuestionGroup(questionGroup);
						data.put("done", true);
						data.put("msg", qGIDArr.length+" Questions Approved Successfully..");
					}
			}
		
			
		} else {
			data.put("done", false);
			data.put("msg", "Unable to perform this operation now, please try later");
		}
		
		
		return data;
	}

	@Override
	public JSONObject archivedMultipleQuestionsGroupById(User user, long[] qGIDArr) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();
		
		
		for (int i = 0; i < qGIDArr.length; i++) {
			
			if (qGIDArr.length != 0) {
				QuestionGroup questionGroup = questionGroupDao.getQuestionGroupById(qGIDArr[i]);

				if (questionGroup.isArchive()) {
					questionGroup.setArchive(false);
					questionGroupDao.modifyQuestionGroup(questionGroup);
					data.put("done", true);
					data.put("msg", Constants.quesGrpMsgActive);
				} else {
					questionGroup.setArchive(true);
					questionGroupDao.modifyQuestionGroup(questionGroup);
					data.put("done", true);
					data.put("msg", Constants.quesGrpMsgArchive);
				}
			} else {
				data.put("done", false);
				data.put("msg", "Unable to perform this operation now, please try later");
			}
		}

		

		return data;
	}

	@Override
	public JSONObject deleteQuestionsGroupById(long questionGroupId) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data =  new JSONObject();
		
		try {
			
			if (questionGroupId != 0) {
				
				QuestionGroup questionGroup = questionGroupDao.getQuestionGroupById(questionGroupId);
				
				
				if (questionGroup.getQuestions() != null) {
					questionService.deleteAllQuestionByQuestionGroupId(questionGroup.getQuestionGroupId());
				}
				Thread.sleep(100);
				questionGroupDao.deleteQuestionByQuestionGroup(questionGroup);
				
				data.put("done", true);
				data.put("msg", "Question Group deleted sucessfully..");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			data.put("done", false);
			data.put("msg", "Question Group deletion failed..");
		}
		
		return data;
	}

	
	
	
	public JSONObject fetchLevelWiseCountOfQuestionGroupsByTopics(long topicId) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		
		JSONArray jsArrayLvlNm = new JSONArray();
//		JSONArray jsArrayLvlNoOfQues = new JSONArray();

		if (topicId != 0) {

			List<Object[]> list = questionGroupDao.fetchLevelWiseCountOfQuestionGroupsByTopicId(topicId);

			if (list.size() != 0) {
				
				long totalQuesCount = 0;
				for (Object[] objects : list) {
					
					JSONObject leveldata = new JSONObject();
					JSONObject jsArrayLvlNmData = new JSONObject();
//					JSONObject jsArrayLvlNoOfQuesData = new JSONObject();
					leveldata.put("LVL"+objects[0],String.valueOf(objects[1])); // both complexity level and no of quesGroups
					
					jsArrayLvlNmData.put(""+objects[0], String.valueOf(objects[1]) ); // complexity level number
//					jsArrayLvlNoOfQuesData.put(""+objects[0], String.valueOf(objects[1]) ); // number of quesGrouop available for the same complexity level number
					
					jsonArr.put(leveldata);
					
					jsArrayLvlNm.put(jsArrayLvlNmData);
//					jsArrayLvlNoOfQues.put(jsArrayLvlNoOfQuesData);
					
					long obj = (long) objects[1];
					totalQuesCount =  totalQuesCount + obj;
					System.out.println(totalQuesCount);
				}
				
				data.put("data", jsonArr);
				data.put("TID", topicId);
				data.put("LVLNO", jsArrayLvlNm);
//				data.put("QGAVABL", jsArrayLvlNoOfQues);
				data.put("TOTALQG", list.size()); // total no of complexity level available
				data.put("TOTALQUES", totalQuesCount); 
				data.put("done", true);

			} else {
				data.put("done", false);
				data.put("TOTALQG", "0");
				data.put("msg", "QuestionGroups are not avilable for this section");
			}
		}

		return data;
	}

	@Override
	public JSONObject getQuestionGroupsByFilter(String status, long[] topicID, long[] variationNum, int[] difficultyLevel)
			throws Exception {
		// TODO Auto-generated method stub
		
		String varNo[] = null;
		if (variationNum.length == 0) {
			 varNo = new String[1];
		}else {
			 varNo = new String[variationNum.length];
		}
		
		
		if (variationNum.length == 0) {
			varNo[0] = String.valueOf(0);
		}else {
			for (int i = 0; i < variationNum.length; i++) {
				varNo[i] = String.valueOf(variationNum[i]);
	        }
		}
		
		
		Object diffLevel[] = new Object[difficultyLevel.length];
		
		for (int i = 0; i < difficultyLevel.length; i++) {
			diffLevel[i] = difficultyLevel[i];
        }
		
		JSONObject data = new JSONObject();

		if (status.equalsIgnoreCase("Active")) {

			JSONArray questionGroupArr = new JSONArray();

//			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsByStatus(status);
			List<QuestionGroup> questionGroups =  questionGroupDao.getAllQuestionGroupsByFilter(status, topicID[0], varNo, diffLevel);
			

			if (questionGroups.size() != 0) {

				for (QuestionGroup questionGroup : questionGroups) {

					JSONObject questionGroupObj = new JSONObject();

					questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
					questionGroupObj.put("NME", questionGroup.getName());
					questionGroupObj.put("QGTYP", questionGroup.getType());
					
					questionGroupObj.put("varNo", questionGroup.getVarNo());
					
					questionGroupObj.put("QGLVL", questionGroup.getComplexityLevel().getQgComplexityLevelId());
					if(questionGroup.getQuesGroupMediaLinks().getQuesUsage().equals("question") ) {
						questionGroupObj.put("QGMEDIA", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
					}
					
					
					JSONArray topicArr = new JSONArray();
					Set<Topic> topics = questionGroup.getTopicSet();

					for (Topic topic : topics) {
						JSONObject topicData = new JSONObject();
						topicData.put("TID", topic.getTopicId());
						topicData.put("TNO", topic.getTopicNo());
						topicData.put("TNM", topic.getTopicName());
						topicData.put("TNM1", topic.getTopicName1());
						topicArr.put(topicData);
					}

					
					questionGroupObj.put("QGTOPICS", topicArr);
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy") ;
		            String creationDate =dateFormat.format(questionGroup.getCreationTime());
		            questionGroupObj.put("DATE", creationDate);
		            
		            
		            User user = userDao.getUserById(Long.parseLong(questionGroup.getCreatedBy()));
		            
		            questionGroupObj.put("CBY", user.getFirstName()+" "+user.getLastName());

					JSONArray questionArr = new JSONArray();

					Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
					
					for (Question question : myOrderedSet) {

						JSONObject questionObj = new JSONObject();

						questionObj.put("QID", question.getQuestionId());
						questionObj.put("QTXT", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
						questionObj.put("QTYP", question.getAnswerType().getName());
						questionObj.put("QTYPID", question.getAnswerType().getAnswerTypeId());
						questionObj.put("QGID", question.getQuestionGroup().getQuestionGroupId());
						questionObj.put("QTIM", question.getTime());
						questionObj.put("QSOLTYPE", question.getSolType());
						questionObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
						questionObj.put("QSOLMEDIA", question.getSolMedia());
						
						JSONArray ans = new JSONArray();
						for (Answers answer : question.getAnswers()) {

							JSONObject ansObj = new JSONObject();
							ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
							ansObj.put("rightAnswer", answer.isRightAnswer());
							ansObj.put("ansMedia", answer.getMedia());
							ans.put(ansObj);
						}
						questionObj.put("answers", ans);
						questionArr.put(questionObj);
					}
					
					questionGroupObj.put("QUESTION", questionArr);
					questionGroupArr.put(questionGroupObj);
				}
				
				data.put("done", true);
				data.put("data", questionGroupArr);
			} else {
				data.put("done", false);
				data.put("data", "Data Not Avilable");
			}
			
		} else if (status.equalsIgnoreCase("Archived")){

			JSONArray questionGroupArr = new JSONArray();

//			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsByStatus(status);
			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsByFilter(status, topicID[0], varNo, diffLevel);

			if (questionGroups.size() != 0) {

				for (QuestionGroup questionGroup : questionGroups) {

					JSONObject questionGroupObj = new JSONObject();

					questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
					questionGroupObj.put("NME", questionGroup.getName());
					questionGroupObj.put("QGTYP", questionGroup.getType());
					
					questionGroupObj.put("varNo", questionGroup.getVarNo());
					
					questionGroupObj.put("QGLVL", questionGroup.getComplexityLevel().getQgComplexityLevelId());
					if(questionGroup.getQuesGroupMediaLinks().getQuesUsage().equals("question") ) {
						questionGroupObj.put("QGMEDIA", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
					}
					
					
					JSONArray topicArr = new JSONArray();
					Set<Topic> topics = questionGroup.getTopicSet();

					for (Topic topic : topics) {
						JSONObject topicData = new JSONObject();
						topicData.put("TID", topic.getTopicId());
						topicData.put("TNO", topic.getTopicNo());
						topicData.put("TNM", topic.getTopicName());
						topicData.put("TNM1", topic.getTopicName1());
						topicArr.put(topicData);
					}

					
					questionGroupObj.put("QGTOPICS", topicArr);
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy") ;
		            String creationDate =dateFormat.format(questionGroup.getCreationTime());
		            questionGroupObj.put("DATE", creationDate);

		            
		            User user = userDao.getUserById(Long.parseLong(questionGroup.getCreatedBy()));
		            questionGroupObj.put("CBY", user.getFirstName()+" "+user.getLastName());
		            
					JSONArray questionArr = new JSONArray();

					Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
					
					for (Question question : myOrderedSet) {

						JSONObject questionObj = new JSONObject();

						questionObj.put("QID", question.getQuestionId());
						questionObj.put("QTXT", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
						questionObj.put("QTYP", question.getAnswerType().getName());
						questionObj.put("QTYPID", question.getAnswerType().getAnswerTypeId());
						questionObj.put("QGID", question.getQuestionGroup().getQuestionGroupId());
						questionObj.put("QTIM", question.getTime());
						questionObj.put("QSOLTYPE", question.getSolType());
						questionObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
						questionObj.put("QSOLMEDIA", question.getSolMedia());
						
						JSONArray ans = new JSONArray();
						for (Answers answer : question.getAnswers()) {

							JSONObject ansObj = new JSONObject();
							ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
							ansObj.put("rightAnswer", answer.isRightAnswer());
							ansObj.put("ansMedia", answer.getMedia());
							ans.put(ansObj);
						}
						questionObj.put("answers", ans);
						questionArr.put(questionObj);
					}
					
					questionGroupObj.put("QUESTION", questionArr);
					questionGroupArr.put(questionGroupObj);
				}
				
				data.put("done", true);
				data.put("data", questionGroupArr);
			} else {
				data.put("done", false);
				data.put("data", "Data Not Avilable");
			}
			
		}else if (status.equalsIgnoreCase("Non-Approved")){

			JSONArray questionGroupArr = new JSONArray();

//			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsToApproveByStatus(status);
			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsByFilter(status, topicID[0], varNo, diffLevel);

			if (questionGroups.size() != 0) {

				for (QuestionGroup questionGroup : questionGroups) {

					JSONObject questionGroupObj = new JSONObject();

					questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
					questionGroupObj.put("NME", questionGroup.getName());
					questionGroupObj.put("QGTYP", questionGroup.getType());
					
					questionGroupObj.put("varNo", questionGroup.getVarNo());
					
					questionGroupObj.put("QGLVL", questionGroup.getComplexityLevel().getQgComplexityLevelId());
					if(questionGroup.getQuesGroupMediaLinks().getQuesUsage().equals("question") ) {
						questionGroupObj.put("QGMEDIA", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
					}
					
					
					JSONArray topicArr = new JSONArray();
					Set<Topic> topics = questionGroup.getTopicSet();

					for (Topic topic : topics) {
						JSONObject topicData = new JSONObject();
						topicData.put("TID", topic.getTopicId());
						topicData.put("TNO", topic.getTopicNo());
						topicData.put("TNM", topic.getTopicName());
						topicData.put("TNM1", topic.getTopicName1());
						topicArr.put(topicData);
					}

					
					questionGroupObj.put("QGTOPICS", topicArr);
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy") ;
		            String creationDate =dateFormat.format(questionGroup.getCreationTime());
		            questionGroupObj.put("DATE", creationDate);

		            
		            User user = userDao.getUserById(Long.parseLong(questionGroup.getCreatedBy()));
		            questionGroupObj.put("CBY", user.getFirstName()+" "+user.getLastName());
		            
					JSONArray questionArr = new JSONArray();

					Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
					
					for (Question question : myOrderedSet) {

						JSONObject questionObj = new JSONObject();

						questionObj.put("QID", question.getQuestionId());
						questionObj.put("QTXT", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
						questionObj.put("QTYP", question.getAnswerType().getName());
						questionObj.put("QTYPID", question.getAnswerType().getAnswerTypeId());
						questionObj.put("QGID", question.getQuestionGroup().getQuestionGroupId());
						questionObj.put("QTIM", question.getTime());
						questionObj.put("QSOLTYPE", question.getSolType());
						questionObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
						questionObj.put("QSOLMEDIA", question.getSolMedia());
						
						JSONArray ans = new JSONArray();
						for (Answers answer : question.getAnswers()) {

							JSONObject ansObj = new JSONObject();
							ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
							ansObj.put("rightAnswer", answer.isRightAnswer());
							ansObj.put("ansMedia", answer.getMedia());
							ans.put(ansObj);
						}
						questionObj.put("answers", ans);
						questionArr.put(questionObj);
					}
					
					questionGroupObj.put("QUESTION", questionArr);
					questionGroupArr.put(questionGroupObj);
				}
				
				data.put("done", true);
				data.put("data", questionGroupArr);
			} else {
				data.put("done", false);
				data.put("data", "Data Not Avilable");
			}
		}else if (status.equalsIgnoreCase("Approved")){

			JSONArray questionGroupArr = new JSONArray();

//			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsToApproveByStatus(status);
			
			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsByFilter(status, topicID[0], varNo, diffLevel);

			if (questionGroups.size() != 0) {

				for (QuestionGroup questionGroup : questionGroups) {

					JSONObject questionGroupObj = new JSONObject();

					questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
					questionGroupObj.put("NME", questionGroup.getName());
					questionGroupObj.put("QGTYP", questionGroup.getType());
					
					questionGroupObj.put("varNo", questionGroup.getVarNo());
					
					questionGroupObj.put("QGLVL", questionGroup.getComplexityLevel().getQgComplexityLevelId());
					if(questionGroup.getQuesGroupMediaLinks().getQuesUsage().equals("question") ) {
						questionGroupObj.put("QGMEDIA", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
					}
					
					
					JSONArray topicArr = new JSONArray();
					Set<Topic> topics = questionGroup.getTopicSet();

					for (Topic topic : topics) {
						JSONObject topicData = new JSONObject();
						topicData.put("TID", topic.getTopicId());
						topicData.put("TNO", topic.getTopicNo());
						topicData.put("TNM", topic.getTopicName());
						topicData.put("TNM1", topic.getTopicName1());
						topicArr.put(topicData);
					}

					
					questionGroupObj.put("QGTOPICS", topicArr);
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy") ;
		            String creationDate =dateFormat.format(questionGroup.getCreationTime());
		            questionGroupObj.put("DATE", creationDate);

		            
		            User user = userDao.getUserById(Long.parseLong(questionGroup.getCreatedBy()));
		            questionGroupObj.put("CBY", user.getFirstName()+" "+user.getLastName());
		            
		            if (questionGroup.getApprovedBy() != null) {
		            	User approvedUser = userDao.getUserById(Long.parseLong(questionGroup.getApprovedBy()));
		            	 questionGroupObj.put("ABY", approvedUser.getFirstName()+" "+approvedUser.getLastName());
					}else {
						User approvedUser = userDao.getUserById(Long.parseLong("38"));
		            	 questionGroupObj.put("ABY", approvedUser.getFirstName()+" "+approvedUser.getLastName());
					}
		           
		            
					JSONArray questionArr = new JSONArray();

					Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
					
					for (Question question : myOrderedSet) {

						JSONObject questionObj = new JSONObject();

						questionObj.put("QID", question.getQuestionId());
						questionObj.put("QTXT", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
						questionObj.put("QTYP", question.getAnswerType().getName());
						questionObj.put("QTYPID", question.getAnswerType().getAnswerTypeId());
						questionObj.put("QGID", question.getQuestionGroup().getQuestionGroupId());
						questionObj.put("QTIM", question.getTime());
						questionObj.put("QSOLTYPE", question.getSolType());
						questionObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
						questionObj.put("QSOLMEDIA", question.getSolMedia());
						
						JSONArray ans = new JSONArray();
						for (Answers answer : question.getAnswers()) {

							JSONObject ansObj = new JSONObject();
							ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
							ansObj.put("rightAnswer", answer.isRightAnswer());
							ansObj.put("ansMedia", answer.getMedia());
							ans.put(ansObj);
						}
						questionObj.put("answers", ans);
						questionArr.put(questionObj);
					}
					
					questionGroupObj.put("QUESTION", questionArr);
					questionGroupArr.put(questionGroupObj);
				}
				
				data.put("done", true);
				data.put("data", questionGroupArr);
			} else {
				data.put("done", false);
				data.put("data", "Data Not Avilable");
			}
		}
		
		
		return data;

	
	}

	@Override
	public JSONObject fetchQuestionsGroupsForFilter(String status) throws Exception {

		JSONObject data = new JSONObject();

	   if (status.equalsIgnoreCase("Non-Approved")){
		   
		   JSONArray topicArray = new JSONArray();

			JSONArray questionGroupArr = new JSONArray();

			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsToApproveByStatus(status);

			if (questionGroups.size() != 0) {
				
				Set<Long> set =  new HashSet<>(); 
				for (QuestionGroup questionGroup : questionGroups) {

					JSONObject questionGroupObj = new JSONObject();
					
					questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
					questionGroupObj.put("NME", questionGroup.getName());
					questionGroupObj.put("QGTYP", questionGroup.getType());
					
					questionGroupObj.put("varNo", questionGroup.getVarNo());
					
					questionGroupObj.put("QGLVL", questionGroup.getComplexityLevel().getQgComplexityLevelId());
					if(questionGroup.getQuesGroupMediaLinks().getQuesUsage().equals("question") ) {
						questionGroupObj.put("QGMEDIA", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
					}
					
					
					JSONArray topicArr = new JSONArray();
					Set<Topic> topics = questionGroup.getTopicSet();
				
					for (Topic topic : topics) {
						JSONObject topicData = new JSONObject();
						
						topicData.put("TNO", topic.getTopicNo());
						topicData.put("TID", topic.getTopicId());
						topicData.put("TN", topic.getTopicName());
						topicData.put("TN1", topic.getTopicName1());
						topicArr.put(topicData);
						
						if (set.add(topic.getTopicId())) {
							topicArray.put(topicData);
						}
						set.add(topic.getTopicId());
						
					}

					
					questionGroupObj.put("QGTOPICS", topicArr);
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy") ;
		            String creationDate =dateFormat.format(questionGroup.getCreationTime());
		            questionGroupObj.put("DATE", creationDate);

		            
		            User user = userDao.getUserById(Long.parseLong(questionGroup.getCreatedBy()));
		            questionGroupObj.put("CBY", user.getFirstName()+" "+user.getLastName());
		            
					JSONArray questionArr = new JSONArray();

					Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
					
					for (Question question : myOrderedSet) {

						JSONObject questionObj = new JSONObject();

						questionObj.put("QID", question.getQuestionId());
						questionObj.put("QTXT", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
						questionObj.put("QTYP", question.getAnswerType().getName());
						questionObj.put("QTYPID", question.getAnswerType().getAnswerTypeId());
						questionObj.put("QGID", question.getQuestionGroup().getQuestionGroupId());
						questionObj.put("QTIM", question.getTime());
						questionObj.put("QSOLTYPE", question.getSolType());
						questionObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
						questionObj.put("QSOLMEDIA", question.getSolMedia());
						
						JSONArray ans = new JSONArray();
						for (Answers answer : question.getAnswers()) {

							JSONObject ansObj = new JSONObject();
							ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
							ansObj.put("rightAnswer", answer.isRightAnswer());
							ansObj.put("ansMedia", answer.getMedia());
							ans.put(ansObj);
						}
						questionObj.put("answers", ans);
						questionArr.put(questionObj);
					}
					
					questionGroupObj.put("QUESTION", questionArr);
					questionGroupArr.put(questionGroupObj);
				}
				
				data.put("done", true);
				data.put("data", questionGroupArr);
				
				JSONArray sortedTopicArray = getSortedJsonArray(topicArray);
				data.put("topicData", sortedTopicArray);
//				data.put("topicData", topicArray);
				
			} else {
				data.put("done", false);
				data.put("data", "Data Not Avilable");
			}
		}else if (status.equalsIgnoreCase("Approved")){

			JSONArray questionGroupArr = new JSONArray();
			
			 JSONArray topicArray1 = new JSONArray();

			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsToApproveByStatus(status);

			if (questionGroups.size() != 0) {
				
				Set<Long> set =  new HashSet<>(); 

				for (QuestionGroup questionGroup : questionGroups) {

					JSONObject questionGroupObj = new JSONObject();

					questionGroupObj.put("QGID", questionGroup.getQuestionGroupId());
					questionGroupObj.put("NME", questionGroup.getName());
					questionGroupObj.put("QGTYP", questionGroup.getType());
					
					questionGroupObj.put("varNo", questionGroup.getVarNo());
					
					questionGroupObj.put("QGLVL", questionGroup.getComplexityLevel().getQgComplexityLevelId());
					if(questionGroup.getQuesGroupMediaLinks().getQuesUsage().equals("question") ) {
						questionGroupObj.put("QGMEDIA", questionGroup.getQuesGroupMediaLinks().getMediaURLText());
					}
					
					
					JSONArray topicArr = new JSONArray();
					Set<Topic> topics = questionGroup.getTopicSet();

					for (Topic topic : topics) {
						JSONObject topicData = new JSONObject();
						
						topicData.put("TNO", topic.getTopicNo());
						topicData.put("TID", topic.getTopicId());
						topicData.put("TN", topic.getTopicName());
						topicData.put("TN1", topic.getTopicName1());
						topicArr.put(topicData);
						
						
						if (set.add(topic.getTopicId())) {
							topicArray1.put(topicData);
						}
						set.add(topic.getTopicId());
						
					}

					
					questionGroupObj.put("QGTOPICS", topicArr);
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy") ;
		            String creationDate =dateFormat.format(questionGroup.getCreationTime());
		            questionGroupObj.put("DATE", creationDate);

		            
		            User user = userDao.getUserById(Long.parseLong(questionGroup.getCreatedBy()));
		            questionGroupObj.put("CBY", user.getFirstName()+" "+user.getLastName());
		            
		            if (questionGroup.getApprovedBy() != null) {
		            	User approvedUser = userDao.getUserById(Long.parseLong(questionGroup.getApprovedBy()));
		            	 questionGroupObj.put("ABY", approvedUser.getFirstName()+" "+approvedUser.getLastName());
					}else {
						User approvedUser = userDao.getUserById(Long.parseLong("38"));
		            	 questionGroupObj.put("ABY", approvedUser.getFirstName()+" "+approvedUser.getLastName());
					}
		           
		            
					JSONArray questionArr = new JSONArray();

					Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
					
					for (Question question : myOrderedSet) {

						JSONObject questionObj = new JSONObject();

						questionObj.put("QID", question.getQuestionId());
						questionObj.put("QTXT", question.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
						questionObj.put("QTYP", question.getAnswerType().getName());
						questionObj.put("QTYPID", question.getAnswerType().getAnswerTypeId());
						questionObj.put("QGID", question.getQuestionGroup().getQuestionGroupId());
						questionObj.put("QTIM", question.getTime());
						questionObj.put("QSOLTYPE", question.getSolType());
						questionObj.put("QSOLTEXT", question.getSolText().replace("#", "<br />"));
						questionObj.put("QSOLMEDIA", question.getSolMedia());
						
						JSONArray ans = new JSONArray();
						for (Answers answer : question.getAnswers()) {

							JSONObject ansObj = new JSONObject();
							ansObj.put("content", answer.getContent().replaceAll("(\\r\\n|\\n)", "<br />").replace("#", "<br />"));
							ansObj.put("rightAnswer", answer.isRightAnswer());
							ansObj.put("ansMedia", answer.getMedia());
							ans.put(ansObj);
						}
						questionObj.put("answers", ans);
						questionArr.put(questionObj);
					}
					
					questionGroupObj.put("QUESTION", questionArr);
					questionGroupArr.put(questionGroupObj);
				}
				
				data.put("done", true);
				data.put("data", questionGroupArr);
				
				JSONArray sortedTopicArray = getSortedJsonArray(topicArray1);
				data.put("topicData", sortedTopicArray);
//				data.put("topicData", topicArray1);
				
				
			} else {
				data.put("done", false);
				data.put("data", "Data Not Avilable");
			}
		}
		
		
		
		
		return data;

	}

	private JSONArray getSortedJsonArray(JSONArray topicArray) {
		
//		JSONArray jsonArr = new JSONArray(topicArray);
		JSONArray sortedJsonArray = new JSONArray();

	    List<JSONObject> jsonValues = new ArrayList<JSONObject>();
	    for (int i = 0; i < topicArray.length(); i++) {
	        jsonValues.add(topicArray.getJSONObject(i));
	    }
	    Collections.sort( jsonValues, new Comparator<JSONObject>() {
	        //You can change "Name" with "ID" if you want to sort by ID
	        private static final String KEY_NAME = "TNO";
	
	        public int compare(JSONObject a, JSONObject b) {
	            String valA = new String();
	            String valB = new String();
	
	            try {
	                valA = (String) a.get(KEY_NAME);
	                valB = (String) b.get(KEY_NAME);
	            } 
	            catch (JSONException e) {
	                //do something
	            }
	
	            return valA.compareTo(valB);
	            //if you want to change the sort order, simply use the following:
	            //return -valA.compareTo(valB);
	        }
	    });
	
	    for (int i = 0; i < topicArray.length(); i++) {
	        sortedJsonArray.put(jsonValues.get(i));
	    }
		return sortedJsonArray;
	}

	@Override
	public JSONObject fetchQuestionsGroupsForFilterFromMapping(String status) throws Exception {
		// TODO Auto-generated method stub
		
		
		JSONObject data = new JSONObject();

//		   if (status.equalsIgnoreCase("Non-Approved")){
			   
			   JSONArray topicArray = new JSONArray();

				List<QuestionGroup> topics = questionGroupDao.getAllQuestionGroupsFromQuesGroupMappingToApproveByStatus(status);
				
				Set<Long> set =  new HashSet<>(); 
				if (!topics.isEmpty()) {
					
					JSONArray topicArr = new JSONArray();
				
					for (QuestionGroup questionGroup : topics) {
						
						Set<Topic> topic = questionGroup.getTopicSet();
						
						for (Topic topicss : topic) {
							JSONObject topicData = new JSONObject();
							
							topicData.put("TNO", topicss.getTopicNo());
							topicData.put("TID", topicss.getTopicId());
							topicData.put("TN", topicss.getTopicName());
							topicData.put("TN1", topicss.getTopicName1());
							topicArr.put(topicData);
							
							if (set.add(topicss.getTopicId())) {
								topicArray.put(topicData);
							}
							set.add(topicss.getTopicId());
							
						}
						
						
					}
					
					JSONArray sortedTopicArray = getSortedJsonArray(topicArray);
					data.put("topicData", sortedTopicArray);
					
				}
				
		   
		return data;
	}

	@Override
	public JSONObject changeTimeForGivenVariationNo(String status, long[] topicID, long[] variationNum,
			int[] difficultyLevel, int[] time) throws Exception {
		// TODO Auto-generated method stub
		
		String varNo[] = null;
		if (variationNum.length == 0) {
			 varNo = new String[1];
		}else {
			 varNo = new String[variationNum.length];
		}
		
		
		if (variationNum.length == 0) {
			varNo[0] = String.valueOf(0);
		}else {
			for (int i = 0; i < variationNum.length; i++) {
				varNo[i] = String.valueOf(variationNum[i]);
	        }
		}
		
		
		Object diffLevel[] = new Object[difficultyLevel.length];
		
		for (int i = 0; i < difficultyLevel.length; i++) {
			diffLevel[i] = difficultyLevel[i];
        }
		
		JSONObject data = new JSONObject();

		if (status.equalsIgnoreCase("Non-Approved")){

			JSONArray questionGroupArr = new JSONArray();

			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsByFilter(status, topicID[0], varNo, diffLevel);
			
			if (questionGroups.size() != 0) {

				for (QuestionGroup questionGroup : questionGroups) {

					Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
					
					for (Question question : myOrderedSet) {

						 question.setTime(time[0]);
						 questionDao.updateQuestion(question);
					}
					
					questionGroup.setUpdatedTime(System.currentTimeMillis());
					questionGroupDao.modifyQuestionGroup(questionGroup);
				}
				
				data.put("done", true);
				data.put("msg", "Time Updated Successfully..");
				data.put("data", questionGroupArr);
			} else {
				data.put("done", false);
				data.put("data", "Data Not Avilable");
			}
		}else if (status.equalsIgnoreCase("Approved")){

			JSONArray questionGroupArr = new JSONArray();

			List<QuestionGroup> questionGroups = questionGroupDao.getAllQuestionGroupsByFilter(status, topicID[0], varNo, diffLevel);

			if (questionGroups.size() != 0) {

				for (QuestionGroup questionGroup : questionGroups) {

					Set<Question> myOrderedSet = new LinkedHashSet<Question>(questionGroup.getQuestions());
					
					for (Question question : myOrderedSet) {

						 question.setTime(time[0]);
						 questionDao.updateQuestion(question);
					}
					
					questionGroup.setUpdatedTime(System.currentTimeMillis());
					questionGroupDao.modifyQuestionGroup(questionGroup);
				}
				
				data.put("done", true);
				data.put("msg", "Time Updated Successfully..");
				data.put("data", questionGroupArr);
			} else {
				data.put("done", false);
				data.put("data", "Data Not Avilable");
			}
		}
		
		
		return data;

	
	}

	public JSONObject fetchVarNOWiseCountOfQuestionGroupsByTopics(long topicId, String[] selectedVarNo) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		
		JSONArray jsArrayLvlNm = new JSONArray();
//		JSONArray jsArrayLvlNoOfQues = new JSONArray();

		if (topicId != 0) {

			List<Object[]> list = questionGroupDao.fetchVarNoWiseCountOfQuestionGroupsByTopicId(topicId, selectedVarNo);
//			List<Object[]> list = questionGroupDao.fetchLevelWiseCountOfQuestionGroupsByTopicId(topicId);
			
			if (list.size() != 0) {
				
				long totalQuesCount = 0;
				for (Object[] objects : list) {
					
					JSONObject leveldata = new JSONObject();
					JSONObject jsArrayLvlNmData = new JSONObject();
//					JSONObject jsArrayLvlNoOfQuesData = new JSONObject();
					leveldata.put("VAR"+objects[0],String.valueOf(objects[1])); // both variation No and no of quesGroups
					
					jsArrayLvlNmData.put(""+objects[0], String.valueOf(objects[1]) ); // variation number
//					jsArrayLvlNoOfQuesData.put(""+objects[0], String.valueOf(objects[1]) ); // number of quesGrouop available for the same variation number
					
					jsonArr.put(leveldata);
					
					jsArrayLvlNm.put(jsArrayLvlNmData);
//					jsArrayLvlNoOfQues.put(jsArrayLvlNoOfQuesData);
					
					long obj = (long) objects[1];
					totalQuesCount =  totalQuesCount + obj;
					System.out.println(totalQuesCount);
				}
				
				data.put("data", jsonArr);
				data.put("TID", topicId);
				
				Topic topic = topicDao.getTopicByTopicId(topicId);
				data.put("TNO", topic.getTopicNo());
				
				data.put("TNAME", topic.getTopicName()+" ("+topic.getTopicName1()+")");
				
				data.put("VARARR", selectedVarNo);
				
				data.put("VARNO", jsArrayLvlNm);
//				data.put("QGAVABL", jsArrayLvlNoOfQues);
				data.put("TOTALQG", list.size()); // total no of variation number available
				data.put("TOTALQUES", totalQuesCount); 
				data.put("done", true);

			} else {
				data.put("done", false);
				data.put("TOTALQG", "0");
				data.put("msg", "QuestionGroups are not avilable for this section");
			}
		}

		return data;
	}

	
	
}
