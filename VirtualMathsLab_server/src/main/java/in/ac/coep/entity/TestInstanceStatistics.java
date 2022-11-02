/**
 * 
 */
package in.ac.coep.entity;

import java.io.Serializable;

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
@Table(name = "testInstanceStatistics")
public class TestInstanceStatistics implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TestInstanceStatisticId")
	private long TestInstanceStatisticId;

	private long totalNoOfQuestions;// per section

	private long passedQuestions;// per section

	private float percentage;// per section

	private String grade;// per section
	
	private long testLevel;
	
	private float timeGivenToCompleteTest;
	
	private float timeTakenToCompleteTest;
	

	/**
	 * @return the timeGivenToCompleteTest
	 */
	public float getTimeGivenToCompleteTest() {
		return timeGivenToCompleteTest;
	}

	/**
	 * @param timeGivenToCompleteTest the timeGivenToCompleteTest to set
	 */
	public void setTimeGivenToCompleteTest(float timeGivenToCompleteTest) {
		this.timeGivenToCompleteTest = timeGivenToCompleteTest;
	}

	/**
	 * @return the timeTakenToCompleteTest
	 */
	public float getTimeTakenToCompleteTest() {
		return timeTakenToCompleteTest;
	}

	/**
	 * @param timeTakenToCompleteTest the timeTakenToCompleteTest to set
	 */
	public void setTimeTakenToCompleteTest(float timeTakenToCompleteTest) {
		this.timeTakenToCompleteTest = timeTakenToCompleteTest;
	}

	public long getTestLevel() {
		return testLevel;
	}

	public void setTestLevel(long testLevel) {
		this.testLevel = testLevel;
	}

	@OneToOne
	@ForeignKey(name = "TIST_userId")
	private User user;

	@OneToOne
	@ForeignKey(name = "TIST_topicId")
	private Topic topic;

	@OneToOne
	@ForeignKey(name = "TIC_testInstanceStateId")
	private TestInstanceState testInstanceState;

	
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
	 * @return the testInstanceStatisticId
	 */
	public long getTestInstanceStatisticId() {
		return TestInstanceStatisticId;
	}

	/**
	 * @param testInstanceStatisticId
	 *            the testInstanceStatisticId to set
	 */
	public void setTestInstanceStatisticId(long testInstanceStatisticId) {
		TestInstanceStatisticId = testInstanceStatisticId;
	}

	/**
	 * @return the totalNoOfQuestions
	 */
	public long getTotalNoOfQuestions() {
		return totalNoOfQuestions;
	}

	/**
	 * @param totalNoOfQuestions the totalNoOfQuestions to set
	 */
	public void setTotalNoOfQuestions(long totalNoOfQuestions) {
		this.totalNoOfQuestions = totalNoOfQuestions;
	}

	/**
	 * @return the passedQuestions
	 */
	public long getPassedQuestions() {
		return passedQuestions;
	}

	/**
	 * @param passedQuestions the passedQuestions to set
	 */
	public void setPassedQuestions(long passedQuestions) {
		this.passedQuestions = passedQuestions;
	}

	/**
	 * @return the percentage
	 */
	public float getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
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
	 * @return the section
	 */
//	public Section getSection() {
//		return section;
//	}

	/**
	 * @param section
	 *            the section to set
//	 */
//	public void setSection(Section section) {
//		this.section = section;
//	}

	/**
	 * @return the department
//	 */
//	public Department getDepartment() {
//		return department;
//	}
//
//	/**
//	 * @param department
//	 *            the department to set
//	 */
//	public void setDepartment(Department department) {
//		this.department = department;
//	}

}
