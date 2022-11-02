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
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author Prashant
 *
 */

@SuppressWarnings("serial")
@Component
@Entity
@Table(name = "quesGroupMediaLinks")
public class QuesGroupMediaLinks implements Serializable  {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "quesGroupMediaLinkId")
	private long quesGroupMediaLinkId;
	
	@Column
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
