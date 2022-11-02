/**
 * 
 */
package in.ac.coep.vo;

/**
 * @author Prashant
 *
 */
public class TestInstanceVO {

	private long testInsId;

	private long PAnsEndTime;

	private long actAnsStTime; //actualAnsStartTime

	private long actAnsEndTime; //actualAnsEndTime

	private boolean result;

	private boolean tagged;

	private long TISID;//testInstanceStateId

	private long userId;

	private int topicId;

	private long qGId; //questionGroupId

	private long quesId;

	private long ansId;

	private long actualGivenOptionsAnsId;
	
//	private Date questionStartTime;
//	
//	private Date questionEndTime;
	
	private long totalTimeToAnswer;
	
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
	 * @return the testInsId
	 */
	public long getTestInsId() {
		return testInsId;
	}

	/**
	 * @param testInsId the testInsId to set
	 */
	public void setTestInsId(long testInsId) {
		this.testInsId = testInsId;
	}

	/**
	 * @return the pAnsEndTime
	 */
	public long getPAnsEndTime() {
		return PAnsEndTime;
	}

	/**
	 * @param pAnsEndTime the pAnsEndTime to set
	 */
	public void setPAnsEndTime(long pAnsEndTime) {
		PAnsEndTime = pAnsEndTime;
	}

	

	/**
	 * @return the actAnsStTime
	 */
	public long getActAnsStTime() {
		return actAnsStTime;
	}

	/**
	 * @param actAnsStTime the actAnsStTime to set
	 */
	public void setActAnsStTime(long actAnsStTime) {
		this.actAnsStTime = actAnsStTime;
	}

	/**
	 * @return the actAnsEndTime
	 */
	public long getActAnsEndTime() {
		return actAnsEndTime;
	}

	/**
	 * @param actAnsEndTime the actAnsEndTime to set
	 */
	public void setActAnsEndTime(long actAnsEndTime) {
		this.actAnsEndTime = actAnsEndTime;
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
	 * @return the tagged
	 */
	public boolean isTagged() {
		return tagged;
	}

	/**
	 * @param tagged the tagged to set
	 */
	public void setTagged(boolean tagged) {
		this.tagged = tagged;
	}

	/**
	 * @return the tISID
	 */
	public long getTISID() {
		return TISID;
	}

	/**
	 * @param tISID the tISID to set
	 */
	public void setTISID(long tISID) {
		TISID = tISID;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	
	/**
	 * @return the topicId
	 */
	public int getTopicId() {
		return topicId;
	}

	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	/**
	 * @return the qGId
	 */
	public long getqGId() {
		return qGId;
	}

	/**
	 * @param qGId the qGId to set
	 */
	public void setqGId(long qGId) {
		this.qGId = qGId;
	}

	/**
	 * @return the quesId
	 */
	public long getQuesId() {
		return quesId;
	}

	/**
	 * @param quesId the quesId to set
	 */
	public void setQuesId(long quesId) {
		this.quesId = quesId;
	}

	/**
	 * @return the ansId
	 */
	public long getAnsId() {
		return ansId;
	}

	/**
	 * @param ansId the ansId to set
	 */
	public void setAnsId(long ansId) {
		this.ansId = ansId;
	}

	
	
	
}
