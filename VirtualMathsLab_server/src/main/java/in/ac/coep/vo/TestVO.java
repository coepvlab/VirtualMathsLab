package in.ac.coep.vo;

import java.util.Date;

/**
 * @author Prashant
 *
 */
public class TestVO {

	
	private long testId;
	
	private String name;
	
	private String createdBy;
	
	private Date startDate;
	
	private Date endDate;
	
	private boolean isActive;

	private String selectedTopics;
	
	private String varNo;
	
	private String varType;
	
	private StandardVO standard;
	
	private int testLevel;
	
	private TestTypeVO testType;
	

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
	 * @return the standard
	 */
	public StandardVO getStandard() {
		return standard;
	}

	/**
	 * @param standard the standard to set
	 */
	public void setStandard(StandardVO standard) {
		this.standard = standard;
	}

	/**
	 * @return the testType
	 */
	public TestTypeVO getTestType() {
		return testType;
	}

	/**
	 * @param testType the testType to set
	 */
	public void setTestType(TestTypeVO testType) {
		this.testType = testType;
	}

	
	
	
	
	
	
	
}
