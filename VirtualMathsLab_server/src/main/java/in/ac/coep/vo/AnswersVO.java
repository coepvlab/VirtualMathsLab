/**
 * 
 */
package in.ac.coep.vo;

/**
 * @author Prashant
 *
 */
public class AnswersVO {

	
	private String content;

	private boolean isRightAnswer;
	
	private String media; 
	
	

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the isRightAnswer
	 */
	public boolean isRightAnswer() {
		return isRightAnswer;
	}

	/**
	 * @param isRightAnswer the isRightAnswer to set
	 */
	public void setRightAnswer(boolean isRightAnswer) {
		this.isRightAnswer = isRightAnswer;
	}
	
	
}
