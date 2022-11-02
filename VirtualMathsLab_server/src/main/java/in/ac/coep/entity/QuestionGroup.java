package in.ac.coep.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.springframework.stereotype.Component;

/**
 * @author Prashant
 *
 */

@SuppressWarnings("serial")
@Component
@Entity
@Table(name = "questionGroup")
public class QuestionGroup implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "questionGroupId")
	private long questionGroupId;

	private boolean isApproved;

	private boolean isArchive;

	private String name;

	private String type;
	
	private String varNo; // variation number
	
	private long creationTime;
	
	private long updatedTime;
	
//	@Column(columnDefinition = "TEXT", length = 100000 )
//	private String content;

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

	@OneToOne
	@ForeignKey(name = "QG_mediaTypeId")
	private MediaType mediaType;


	@ManyToOne
	@ForeignKey(name = "QG_subjectId")
	private Subject subject;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "QuestionGroupMapping", joinColumns = { @JoinColumn(name = "questionGroupId") }, inverseJoinColumns = {
			@JoinColumn(name = "topicId") })
	private Set<Topic> topicSet;
	
	public Set<Topic> getTopicSet() {
		return topicSet;
	}

	public void setTopicSet(Set<Topic> topicSet) {
		this.topicSet = topicSet;
	}

	@ManyToOne
	@ForeignKey(name = "QG_quesGroupMediaLinkId")
	private QuesGroupMediaLinks quesGroupMediaLinks;
	
	
	@OneToOne
	@ForeignKey(name = "QG_qgComplexityLevelId")
	private QGComplexityLevel complexityLevel;
	
	private String createdBy;

	private String approvedBy;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "questionGroup")
	 private List<Question> questions;
	
	
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
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

	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
	

//	public String getMedia() {
//		return media;
//	}
//
//	public void setMedia(String media) {
//		this.media = media;
//	}

//	public QuestionBank getQuestionBank() {
//		return questionBank;
//	}
//
//	public void setQuestionBank(QuestionBank questionBank) {
//		this.questionBank = questionBank;
//	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

//	public Subject getSubject() {
//		return subject;
//	}
//
//	public void setSubject(Subject subject) {
//		this.subject = subject;
//	}
//
//	public Level1 getLevel1() {
//		return level1;
//	}
//
//	public void setLevel1(Level1 level1) {
//		this.level1 = level1;
//	}
//
//	public Level2 getLevel2() {
//		return level2;
//	}
//
//	public void setLevel2(Level2 level2) {
//		this.level2 = level2;
//	}
//
//	public Level3 getLevel3() {
//		return level3;
//	}
//
//	public void setLevel3(Level3 level3) {
//		this.level3 = level3;
//	}
//
//	public Level4 getLevel4() {
//		return level4;
//	}
//
//	public void setLevel4(Level4 level4) {
//		this.level4 = level4;
//	}
//
//	public Level5 getLevel5() {
//		return level5;
//	}
//
//	public void setLevel5(Level5 level5) {
//		this.level5 = level5;
//	}
//
//	public Level6 getLevel6() {
//		return level6;
//	}
//
//	public void setLevel6(Level6 level6) {
//		this.level6 = level6;
//	}

	public QGComplexityLevel getComplexityLevel() {
		return complexityLevel;
	}

	public void setComplexityLevel(QGComplexityLevel complexityLevel) {
		this.complexityLevel = complexityLevel;
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

	

	/**
	 * @return the quesGroupMediaLinks
	 */
	public QuesGroupMediaLinks getQuesGroupMediaLinks() {
		return quesGroupMediaLinks;
	}

	/**
	 * @param quesGroupMediaLinks the quesGroupMediaLinks to set
	 */
	public void setQuesGroupMediaLinks(QuesGroupMediaLinks quesGroupMediaLinks) {
		this.quesGroupMediaLinks = quesGroupMediaLinks;
	}


	
	
}
