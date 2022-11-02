package in.ac.coep.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "test")
public class Test implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "testId")
	private long testId;

	@Column
	private String name;

	@Column
	private String createdBy;

	@Column
	private Date startDate;

	@Column
	private Date endDate;

	@Column
	private boolean isActive;

	@Column
	private long creationTime;

	@Column
	private long updatedTime;
	
	@Column
	private int testLevel;
	
	@Column
	private String selectedTopics;
	
	@Column
	private String varNo;
	
	@Column
	private String varType;  // this field is to save variation Type i.e. first half or second half 

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

	@ManyToOne
	@ForeignKey(name = "T_stdId")
	private Standard standard;


	@ManyToOne
	@ForeignKey(name = "T_testTypeId")
	private TestType testType;

	/**
	 * @return the testId
	 */
	public long getTestId() {
		return testId;
	}

	/**
	 * @param testId the testId to set
	 */
	public void setTestId(long testId) {
		this.testId = testId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * @return the creationTime
	 */
	public long getCreationTime() {
		return creationTime;
	}

	/**
	 * @param creationTime the creationTime to set
	 */
	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * @return the updatedTime
	 */
	public long getUpdatedTime() {
		return updatedTime;
	}

	/**
	 * @param updatedTime the updatedTime to set
	 */
	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
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
	 * @return the selectedTopics
	 */
	public String getSelectedTopics() {
		return selectedTopics;
	}

	/**
	 * @param selectedTopics the selectedTopics to set
	 */
	public void setSelectedTopics(String selectedTopics) {
		this.selectedTopics = selectedTopics;
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
	 * @return the varType
	 */
	public String getVarType() {
		return varType;
	}

	/**
	 * @param varType the varType to set
	 */
	public void setVarType(String varType) {
		this.varType = varType;
	}
	

	
}
