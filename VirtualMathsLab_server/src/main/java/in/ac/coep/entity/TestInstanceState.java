
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
@Table(name = "testInstanceState")
public class TestInstanceState implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "testInstanceStateId")
	private long testInstanceStateId;
	
	@Column
	private long windowViolationTime;
	
	@Column
	private long lastQuestionId;
	
	@Column
	private Date TestStart;
	
	@Column
	private Date TestEnd;
	
	@Column
	private Date PlannedStartTime;
	
	@Column
	private Date plannedTestEndTime;
	
	@Column
	private String Status;
	
	@Column
	private String TestURL;
	
	@Column
	private Date TickTime;
	
	@Column
	private int testLevel;
	
	@Column
	private int rescheduleNo;
	
	@Column
	private boolean isActive;
	
	@Column
	private int practiceTestNo;
	
	@Column
	private boolean pdfGenerate;
	
	@Column
	private String scoreCardLink;
	
	@Column
	private String grade;
	
	@Column
	private String currentTestTime;
	
	@Column
	private String percentage;
	
	@Column
	private String result;
	
	@Column
	private int complexityLevel;
	
	@Column
	private boolean isLevelComplete;
	
	@OneToOne
	@ForeignKey(name = "TIS_testId")
	private Test test;

	@OneToOne
	@ForeignKey(name = "TIS_userId")
	private User user;
	
	@OneToOne
	@ForeignKey(name = "TIS_testTypeId")
	private TestType testType;
	
	@OneToOne
	@ForeignKey(name = "TIS_stdId")
	private Standard standard;
	
	/**
	 * @return the percentage
	 */
	public String getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	/**
	 * @return the currentTestTime
	 */
	public String getCurrentTestTime() {
		return currentTestTime;
	}

	/**
	 * @param currentTestTime the currentTestTime to set
	 */
	public void setCurrentTestTime(String currentTestTime) {
		this.currentTestTime = currentTestTime;
	}

	/**
	 * @return the testInstanceStateId
	 */
	public long getTestInstanceStateId() {
		return testInstanceStateId;
	}

	/**
	 * @param testInstanceStateId the testInstanceStateId to set
	 */
	public void setTestInstanceStateId(long testInstanceStateId) {
		this.testInstanceStateId = testInstanceStateId;
	}

	/**
	 * @return the windowViolationTime
	 */
	public long getWindowViolationTime() {
		return windowViolationTime;
	}

	/**
	 * @param windowViolationTime the windowViolationTime to set
	 */
	public void setWindowViolationTime(long windowViolationTime) {
		this.windowViolationTime = windowViolationTime;
	}

	/**
	 * @return the lastQuestionId
	 */
	public long getLastQuestionId() {
		return lastQuestionId;
	}

	/**
	 * @param lastQuestionId the lastQuestionId to set
	 */
	public void setLastQuestionId(long lastQuestionId) {
		this.lastQuestionId = lastQuestionId;
	}

	/**
	 * @return the testStart
	 */
	public Date getTestStart() {
		return TestStart;
	}

	/**
	 * @param testStart the testStart to set
	 */
	public void setTestStart(Date testStart) {
		TestStart = testStart;
	}

	/**
	 * @return the testEnd
	 */
	public Date getTestEnd() {
		return TestEnd;
	}

	/**
	 * @param testEnd the testEnd to set
	 */
	public void setTestEnd(Date testEnd) {
		TestEnd = testEnd;
	}

	/**
	 * @return the plannedStartTime
	 */
	public Date getPlannedStartTime() {
		return PlannedStartTime;
	}

	/**
	 * @param plannedStartTime the plannedStartTime to set
	 */
	public void setPlannedStartTime(Date plannedStartTime) {
		PlannedStartTime = plannedStartTime;
	}

	/**
	 * @return the plannedTestEndTime
	 */
	public Date getPlannedTestEndTime() {
		return plannedTestEndTime;
	}

	/**
	 * @param plannedTestEndTime the plannedTestEndTime to set
	 */
	public void setPlannedTestEndTime(Date plannedTestEndTime) {
		this.plannedTestEndTime = plannedTestEndTime;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return Status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		Status = status;
	}

	/**
	 * @return the testURL
	 */
	public String getTestURL() {
		return TestURL;
	}

	/**
	 * @param testURL the testURL to set
	 */
	public void setTestURL(String testURL) {
		TestURL = testURL;
	}

	/**
	 * @return the tickTime
	 */
	public Date getTickTime() {
		return TickTime;
	}

	/**
	 * @param tickTime the tickTime to set
	 */
	public void setTickTime(Date tickTime) {
		TickTime = tickTime;
	}

	/**
	 * @return the testLevel
	 */
	public int getTestLevel() {
		return testLevel;
	}

	/**
	 * @param testLevel the testLevel to set
	 */
	public void setTestLevel(int testLevel) {
		this.testLevel = testLevel;
	}

	/**
	 * @return the test
	 */
	public Test getTest() {
		return test;
	}

	/**
	 * @param test the test to set
	 */
	public void setTest(Test test) {
		this.test = test;
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
	 * @return the rescheduleNo
	 */
	public int getRescheduleNo() {
		return rescheduleNo;
	}

	/**
	 * @param rescheduleNo the rescheduleNo to set
	 */
	public void setRescheduleNo(int rescheduleNo) {
		this.rescheduleNo = rescheduleNo;
	}

	/**
	 * @return the pdfGenerate
	 */
	public boolean isPdfGenerate() {
		return pdfGenerate;
	}

	/**
	 * @param pdfGenerate the pdfGenerate to set
	 */
	public void setPdfGenerate(boolean pdfGenerate) {
		this.pdfGenerate = pdfGenerate;
	}

	/**
	 * @return the scoreCardLink
	 */
	public String getScoreCardLink() {
		return scoreCardLink;
	}

	/**
	 * @param scoreCardLink the scoreCardLink to set
	 */
	public void setScoreCardLink(String scoreCardLink) {
		this.scoreCardLink = scoreCardLink;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the practiceTestNo
	 */
	public int getPracticeTestNo() {
		return practiceTestNo;
	}

	/**
	 * @param practiceTestNo the practiceTestNo to set
	 */
	public void setPracticeTestNo(int practiceTestNo) {
		this.practiceTestNo = practiceTestNo;
	}

	/**
	 * @return the testType
	 */
	public TestType getTestType() {
		return testType;
	}

	/**
	 * @param testType the testType to set
	 */
	public void setTestType(TestType testType) {
		this.testType = testType;
	}

	/**
	 * @return the standard
	 */
	public Standard getStandard() {
		return standard;
	}

	/**
	 * @param standard the standard to set
	 */
	public void setStandard(Standard standard) {
		this.standard = standard;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	/**
	 * @return the complexityLevel
	 */
	public int getComplexityLevel() {
		return complexityLevel;
	}

	/**
	 * @param complexityLevel the complexityLevel to set
	 */
	public void setComplexityLevel(int complexityLevel) {
		this.complexityLevel = complexityLevel;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the isLevelComplete
	 */
	public boolean isLevelComplete() {
		return isLevelComplete;
	}

	/**
	 * @param isLevelComplete the isLevelComplete to set
	 */
	public void setLevelComplete(boolean isLevelComplete) {
		this.isLevelComplete = isLevelComplete;
	}

	
	
	
}
