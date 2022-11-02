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
@Table(name = "topicMapping")
public class TopicMapping implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "topicMappingId")
	private long topicMappingId;
	
	// additional fields
    private String status;
	
	@ManyToOne
	@ForeignKey(name = "T_topicId")
	private Topic topic;

	
	@ManyToOne
	@ForeignKey(name = "T_parentId")
	private Topic parent;
	
	
	/**
	 * @return the topic
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	/**
	 * @return the parent
	 */
	public Topic getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Topic parent) {
		this.parent = parent;
	}

	/**
	 * @return the topicMappingId
	 */
	public long getTopicMappingId() {
		return topicMappingId;
	}

	/**
	 * @param topicMappingId the topicMappingId to set
	 */
	public void setTopicMappingId(long topicMappingId) {
		this.topicMappingId = topicMappingId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	
}
