
package in.ac.coep.vo;

import in.ac.coep.entity.QGComplexityLevel;

/**
 * @author Prashant
 *
 */
public class TestConfigurationVO {

	private long testConfigId;

	private int noOfQuestionGroup;

	private int topicTimeLimit;
	
	private String varNo; // variation number

	private TestVO testVO;

	private TopicVO topicVO;

	private QGComplexityLevel complexityLevel;
	
	private StandardVO standardVO;

	/**
	 * @return the testConfigId
	 */
	public long getTestConfigId() {
		return testConfigId;
	}

	/**
	 * @param testConfigId the testConfigId to set
	 */
	public void setTestConfigId(long testConfigId) {
		this.testConfigId = testConfigId;
	}

	/**
	 * @return the noOfQuestionGroup
	 */
	public int getNoOfQuestionGroup() {
		return noOfQuestionGroup;
	}

	/**
	 * @param noOfQuestionGroup the noOfQuestionGroup to set
	 */
	public void setNoOfQuestionGroup(int noOfQuestionGroup) {
		this.noOfQuestionGroup = noOfQuestionGroup;
	}

	/**
	 * @return the topicTimeLimit
	 */
	public int getTopicTimeLimit() {
		return topicTimeLimit;
	}

	/**
	 * @param topicTimeLimit the topicTimeLimit to set
	 */
	public void setTopicTimeLimit(int topicTimeLimit) {
		this.topicTimeLimit = topicTimeLimit;
	}

	

	/**
	 * @return the testVO
	 */
	public TestVO getTestVO() {
		return testVO;
	}

	/**
	 * @param testVO the testVO to set
	 */
	public void setTestVO(TestVO testVO) {
		this.testVO = testVO;
	}

	/**
	 * @return the topicVO
	 */
	public TopicVO getTopicVO() {
		return topicVO;
	}

	/**
	 * @param topicVO the topicVO to set
	 */
	public void setTopicVO(TopicVO topicVO) {
		this.topicVO = topicVO;
	}

	/**
	 * @return the complexityLevel
	 */
	public QGComplexityLevel getComplexityLevel() {
		return complexityLevel;
	}

	/**
	 * @param complexityLevel the complexityLevel to set
	 */
	public void setComplexityLevel(QGComplexityLevel complexityLevel) {
		this.complexityLevel = complexityLevel;
	}

	/**
	 * @return the standardVO
	 */
	public StandardVO getStandardVO() {
		return standardVO;
	}

	/**
	 * @param standardVO the standardVO to set
	 */
	public void setStandardVO(StandardVO standardVO) {
		this.standardVO = standardVO;
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

	
	
}
