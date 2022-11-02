package in.ac.coep.vo;

import java.util.Set;


public class QuestionGroupVO {
	
	private long questionGroupId;

	private boolean isApproved;

	private boolean isArchive;

	private String name;

	private String type;
	
	private String varNo; // variation number

//	private String content;


	private MediaTypeVO mediaType;

//	private String media;

	private SubjectVO subject;
	
//	private Level1VO level1;
//
//	private Level2VO level2;
//	
//	private Level3VO level3;
//	
//	private Level4VO level4;
//	
//	private Level5VO level5;
//	
//	private Level6VO level6;
	
	private TopicVO[] topic;
	
	private QuesGroupMediaLinksVO quesGroupMediaLinks;
	
	private int level;
	
	private QuestionVO question;

	private String createdBy;
	
	private String approvedBy;
	
	private Set<QuestionVO> questions;

	/**
	 * @return the topic
	 */
//	public TopicVO getTopic() {
//		return topic;
//	}
//
//	/**
//	 * @param topic the topic to set
//	 */
//	public void setTopic(TopicVO topic) {
//		this.topic = topic;
//	}

	
	
	/**
	 * @return the topic
	 */
	public TopicVO[] getTopic() {
		return topic;
	}

	/**
	 * @return the varNo
	 */
	public String getVarNo() {
		return varNo;
	}

	/**
	 * @param varNo the varNo to set
	 */
	public void setVarNo(String varNo) {
		this.varNo = varNo;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(TopicVO[] topic) {
		this.topic = topic;
	}


	/**
	 * @return the quesGroupMediaLinks
	 */
	public QuesGroupMediaLinksVO getQuesGroupMediaLinks() {
		return quesGroupMediaLinks;
	}

	
	/**
	 * @param quesGroupMediaLinks the quesGroupMediaLinks to set
	 */
	public void setQuesGroupMediaLinks(QuesGroupMediaLinksVO quesGroupMediaLinks) {
		this.quesGroupMediaLinks = quesGroupMediaLinks;
	}

	public long getQuestionGroupId() {
		return questionGroupId;
	}

	public void setQuestionGroupId(long questionGroupId) {
		this.questionGroupId = questionGroupId;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public boolean isArchive() {
		return isArchive;
	}

	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}


	public MediaTypeVO getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaTypeVO mediaType) {
		this.mediaType = mediaType;
	}

//	public String getMedia() {
//		return media;
//	}
//
//	public void setMedia(String media) {
//		this.media = media;
//	}

	public SubjectVO getSubject() {
		return subject;
	}

	public void setSubject(SubjectVO subject) {
		this.subject = subject;
	}

//	public Level1VO getLevel1() {
//		return level1;
//	}
//
//	public void setLevel1(Level1VO level1) {
//		this.level1 = level1;
//	}
//
//	public Level2VO getLevel2() {
//		return level2;
//	}
//
//	public void setLevel2(Level2VO level2) {
//		this.level2 = level2;
//	}
//
//	public Level3VO getLevel3() {
//		return level3;
//	}
//
//	public void setLevel3(Level3VO level3) {
//		this.level3 = level3;
//	}
//
//	public Level4VO getLevel4() {
//		return level4;
//	}
//
//	public void setLevel4(Level4VO level4) {
//		this.level4 = level4;
//	}
//
//	public Level5VO getLevel5() {
//		return level5;
//	}
//
//	public void setLevel5(Level5VO level5) {
//		this.level5 = level5;
//	}
//
//	public Level6VO getLevel6() {
//		return level6;
//	}
//
//	public void setLevel6(Level6VO level6) {
//		this.level6 = level6;
//	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public QuestionVO getQuestion() {
		return question;
	}

	public void setQuestion(QuestionVO question) {
		this.question = question;
	}

	public Set<QuestionVO> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<QuestionVO> questions) {
		this.questions = questions;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	

}
