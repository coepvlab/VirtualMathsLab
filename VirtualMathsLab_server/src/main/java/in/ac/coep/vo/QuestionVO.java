/**
 * 
 */
package in.ac.coep.vo;

import java.util.Set;

import in.ac.coep.entity.QuestionGroup;

/**
 * @author Prashant
 *
 */
public class QuestionVO {

	private long questionId;

	private String content;
	
	private String solText;

	private String solMedia;
	
	private int time;
	
	private String solType;

	private QuestionGroup questionGroup;

	private AnswerTypeVO answerType;

	private Set<AnswersVO>  answers;
	
	

	/**
	 * @return the solText
	 */
	public String getSolText() {
		return solText;
	}

	/**
	 * @param solText the solText to set
	 */
	public void setSolText(String solText) {
		this.solText = solText;
	}

	/**
	 * @return the solMedia
	 */
	public String getSolMedia() {
		return solMedia;
	}

	/**
	 * @param solMedia the solMedia to set
	 */
	public void setSolMedia(String solMedia) {
		this.solMedia = solMedia;
	}

	/**
	 * @return the answers
	 */
	public Set<AnswersVO> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(Set<AnswersVO> answers) {
		this.answers = answers;
	}

	/**
	 * @return the questionId
	 */
	public long getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId
	 *            the questionId to set
	 */
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * @return the questionGroup
	 */
	public QuestionGroup getQuestionGroup() {
		return questionGroup;
	}

	/**
	 * @param questionGroup
	 *            the questionGroup to set
	 */
	public void setQuestionGroup(QuestionGroup questionGroup) {
		this.questionGroup = questionGroup;
	}

	/**
	 * @return the answerType
	 */
	public AnswerTypeVO getAnswerType() {
		return answerType;
	}

	/**
	 * @param answerType
	 *            the answerType to set
	 */
	public void setAnswerType(AnswerTypeVO answerType) {
		this.answerType = answerType;
	}

	public String getSolType() {
		return solType;
	}

	public void setSolType(String solType) {
		this.solType = solType;
	}
	
	

}
