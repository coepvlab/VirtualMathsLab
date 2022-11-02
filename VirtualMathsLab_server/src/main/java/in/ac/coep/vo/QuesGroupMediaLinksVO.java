/**
 * 
 */
package in.ac.coep.vo;

import javax.persistence.Column;

/**
 * @author Prashant
 *
 */
public class QuesGroupMediaLinksVO {

	private long quesGroupMediaLinkId;
	
	private String quesUsage;

	@Column(columnDefinition = "TEXT", length = 100000 )
	private String mediaURLText;

	/**
	 * @return the quesGroupMediaLinkId
	 */
	public long getQuesGroupMediaLinkId() {
		return quesGroupMediaLinkId;
	}

	/**
	 * @param quesGroupMediaLinkId the quesGroupMediaLinkId to set
	 */
	public void setQuesGroupMediaLinkId(long quesGroupMediaLinkId) {
		this.quesGroupMediaLinkId = quesGroupMediaLinkId;
	}

	/**
	 * @return the quesUsage
	 */
	public String getQuesUsage() {
		return quesUsage;
	}

	/**
	 * @param quesUsage the quesUsage to set
	 */
	public void setQuesUsage(String quesUsage) {
		this.quesUsage = quesUsage;
	}

	/**
	 * @return the mediaURLText
	 */
	public String getMediaURLText() {
		return mediaURLText;
	}

	/**
	 * @param mediaURLText the mediaURLText to set
	 */
	public void setMediaURLText(String mediaURLText) {
		this.mediaURLText = mediaURLText;
	}
	
	
	
	
}
