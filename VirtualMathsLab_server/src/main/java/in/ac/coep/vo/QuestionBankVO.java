/**
 * 
 */
package in.ac.coep.vo;

/**
 * @author Prashant
 *
 */
public class QuestionBankVO {

	
	private long questionBankId;

	private String name;

	private boolean isActive;

	/**
	 * @return the questionBankId
	 */
	public long getQuestionBankId() {
		return questionBankId;
	}

	/**
	 * @param questionBankId the questionBankId to set
	 */
	public void setQuestionBankId(long questionBankId) {
		this.questionBankId = questionBankId;
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
	
	
}
