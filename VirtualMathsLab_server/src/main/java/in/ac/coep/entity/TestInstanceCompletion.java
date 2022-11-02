/**
 * 
 */
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
@Table(name = "testInstanceCompletion")
public class TestInstanceCompletion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "testInstanceCompletionId")
	private long testInstanceCompletionId;
	
	
	@OneToOne
	@ForeignKey(name = "TIC_testInstanceStateId")
	private TestInstanceState testInstanceState;
 

	@OneToOne
	@ForeignKey(name = "TIC_userId")
	private User user;

	@OneToOne
	@ForeignKey(name = "TIC_topicId")
	private Topic topic;

	@OneToOne
	@ForeignKey(name = "TIC_questionGroupId")
	private QuestionGroup questionGroup;

	@OneToOne
	@ForeignKey(name = "TIC_questionId")
	private Question question;
	
	@OneToOne
	@ForeignKey(name = "TIC_answersId")
	private Answers answers;
	
	@Column
	private boolean result;
	
	
	@Column
	private Date PlannedAnsEndTime;
	
	@Column
	private Date ActualAnsStartTime;
	
	@Column
	private Date ActualAnsEndTime;
	
	@Column
	private boolean Tagged;
	
	
	@Column
	private long actualGivenOptionsAnsId;


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
	 * @return the testInstanceCompletionId
	 */
	public long getTestInstanceCompletionId() {
		return testInstanceCompletionId;
	}

	/**
	 * @param testInstanceCompletionId the testInstanceCompletionId to set
	 */
	public void setTestInstanceCompletionId(long testInstanceCompletionId) {
		this.testInstanceCompletionId = testInstanceCompletionId;
	}

	/**
	 * @return the testInstanceState
	 */
	public TestInstanceState getTestInstanceState() {
		return testInstanceState;
	}

	/**
	 * @param testInstanceState the testInstanceState to set
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
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the section
	 */
//	public Section getSection() {
//		return section;
//	}
//
//	/**
//	 * @param section the section to set
//	 */
//	public void setSection(Section section) {
//		this.section = section;
//	}

	/**
	 * @return the questionGroup
	 */
	public QuestionGroup getQuestionGroup() {
		return questionGroup;
	}

	/**
	 * @param questionGroup the questionGroup to set
	 */
	public void setQuestionGroup(QuestionGroup questionGroup) {
		this.questionGroup = questionGroup;
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the answers
	 */
	public Answers getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(Answers answers) {
		this.answers = answers;
	}

	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * @return the plannedAnsEndTime
	 */
	public Date getPlannedAnsEndTime() {
		return PlannedAnsEndTime;
	}

	/**
	 * @param plannedAnsEndTime the plannedAnsEndTime to set
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
	 * @param actualAnsStartTime the actualAnsStartTime to set
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
	 * @param actualAnsEndTime the actualAnsEndTime to set
	 */
	public void setActualAnsEndTime(Date actualAnsEndTime) {
		ActualAnsEndTime = actualAnsEndTime;
	}

	/**
	 * @return the tagged
	 */
	public boolean isTagged() {
		return Tagged;
	}

	/**
	 * @param tagged the tagged to set
	 */
	public void setTagged(boolean tagged) {
		Tagged = tagged;
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
	
	
	
	
}
