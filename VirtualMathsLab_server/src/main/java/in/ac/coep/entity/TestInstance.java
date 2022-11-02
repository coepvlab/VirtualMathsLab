package in.ac.coep.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "testInstance")
public class TestInstance implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "testInstanceId")
	private long testInstanceId;

	@Column
	private Date PlannedAnsEndTime;

	@Column
	private Date ActualAnsStartTime;

	@Column
	private Date ActualAnsEndTime;
	
	@Column
	private long totalTimeToAnswer;
	
//	@Column
//	private Date questionEndTime;

	@Column
	private boolean result;

	@Column
	private boolean tagged;

	@Column
	private long actualGivenOptionsAnsId;

	@OneToOne
	@ForeignKey(name = "TI_testInstanceStateId")
	private TestInstanceState testInstanceState;


	@OneToOne
	@ForeignKey(name = "TI_userId")
	private User user;

	@OneToOne
	@ForeignKey(name = "TI_topicId")
	private Topic topic;

//	@OneToOne
//	@ForeignKey(name = "TI_questionGroupId")
//	private QuestionGroup questionGroup;
//
//	@OneToOne
//	@ForeignKey(name = "TI_questionId")
//	private Question question;
//
//	@OneToOne
//	@ForeignKey(name = "TI_answersId")
//	private Answers answers;

	private long questionGroupId;
	
	private long questionId;
	
	private long answersId;
	
//	private long actAnswersId;
	
	/**
	 * @return the actAnswersId
	 */
//	public long getActAnswersId() {
//		return actAnswersId;
//	}
//
//	/**
//	 * @param actAnswersId the actAnswersId to set
//	 */
//	public void setActAnswersId(long actAnswersId) {
//		this.actAnswersId = actAnswersId;
//	}

	/**
	 * @return the questionGroupId
	 */
	public long getQuestionGroupId() {
		return questionGroupId;
	}

	/**
	 * @param questionGroupId the questionGroupId to set
	 */
	public void setQuestionGroupId(long questionGroupId) {
		this.questionGroupId = questionGroupId;
	}

	/**
	 * @return the questionId
	 */
	public long getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the answersId
	 */
	public long getAnswersId() {
		return answersId;
	}

	/**
	 * @param answersId the answersId to set
	 */
	public void setAnswersId(long answersId) {
		this.answersId = answersId;
	}

	/**
	 * @return the testInstanceId
	 */
	public long getTestInstanceId() {
		return testInstanceId;
	}

	/**
	 * @param testInstanceId
	 *            the testInstanceId to set
	 */
	public void setTestInstanceId(long testInstanceId) {
		this.testInstanceId = testInstanceId;
	}

	/**
	 * @return the plannedAnsEndTime
	 */
	public Date getPlannedAnsEndTime() {
		return PlannedAnsEndTime;
	}

	/**
	 * @param plannedAnsEndTime
	 *            the plannedAnsEndTime to set
	 */
	public void setPlannedAnsEndTime(Date plannedAnsEndTime) {
		PlannedAnsEndTime = plannedAnsEndTime;
	}

	/**
	 * @return the actualAnsStartTime
	 */
	public Date getActualAnsStartTime() {
		return ActualAnsStartTime;
	}

	/**
	 * @param actualAnsStartTime
	 *            the actualAnsStartTime to set
	 */
	public void setActualAnsStartTime(Date actualAnsStartTime) {
		ActualAnsStartTime = actualAnsStartTime;
	}

	/**
	 * @return the actualAnsEndTime
	 */
	public Date getActualAnsEndTime() {
		return ActualAnsEndTime;
	}

	/**
	 * @param actualAnsEndTime
	 *            the actualAnsEndTime to set
	 */
	public void setActualAnsEndTime(Date actualAnsEndTime) {
		ActualAnsEndTime = actualAnsEndTime;
	}

	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * @return the tagged
	 */
	public boolean isTagged() {
		return tagged;
	}

	/**
	 * @param tagged
	 *            the tagged to set
	 */
	public void setTagged(boolean tagged) {
		this.tagged = tagged;
	}

	/**
	 * @return the testInstanceState
	 */
	public TestInstanceState getTestInstanceState() {
		return testInstanceState;
	}

	/**
	 * @param testInstanceState
	 *            the testInstanceState to set
	 */
	public void setTestInstanceState(TestInstanceState testInstanceState) {
		this.testInstanceState = testInstanceState;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the topic
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	

	/**
	 * @return the actualGivenOptionsAnsId
	 */
	public long getActualGivenOptionsAnsId() {
		return actualGivenOptionsAnsId;
	}

	/**
	 * @param actualGivenOptionsAnsId the actualGivenOptionsAnsId to set
	 */
	public void setActualGivenOptionsAnsId(long actualGivenOptionsAnsId) {
		this.actualGivenOptionsAnsId = actualGivenOptionsAnsId;
	}

	/**
	 * @return the totalTimeToAnswer
	 */
	public long getTotalTimeToAnswer() {
		return totalTimeToAnswer;
	}

	/**
	 * @param totalTimeToAnswer the totalTimeToAnswer to set
	 */
	public void setTotalTimeToAnswer(long totalTimeToAnswer) {
		this.totalTimeToAnswer = totalTimeToAnswer;
	}

	
	
	
    
	
}
