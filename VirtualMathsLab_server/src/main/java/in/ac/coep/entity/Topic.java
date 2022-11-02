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
@Table(name = "topic")
public class Topic implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "topicId")
	private long topicId;
	
	private String topicName;
	
	private String topicName1;
	
	private String topicNo;

	private String level;
	
	private String redirectNo;  // in case of any change in topic no then we will assign new topic No to redirect.

	
	public String getRedirectNo() {
		return redirectNo;
	}



	public void setRedirectNo(String redirectNo) {
		this.redirectNo = redirectNo;
	}



	@ManyToOne
	@ForeignKey(name = "T_subjectId")
	private Subject subject;
	
	
	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}



	/**
	 * @param subject the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	

	/**
	 * @return the topicId
	 */
	public long getTopicId() {
		return topicId;
	}



	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}



	/**
	 * @return the topicName
	 */
	public String getTopicName() {
		return topicName;
	}



	/**
	 * @param topicName the topicName to set
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}



	/**
	 * @return the topicName1
	 */
	public String getTopicName1() {
		return topicName1;
	}



	/**
	 * @param topicName1 the topicName1 to set
	 */
	public void setTopicName1(String topicName1) {
		this.topicName1 = topicName1;
	}



	/**
	 * @return the topicNo
	 */
	public String getTopicNo() {
		return topicNo;
	}



	/**
	 * @param topicNo the topicNo to set
	 */
	public void setTopicNo(String topicNo) {
		this.topicNo = topicNo;
	}



	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}



	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}



//	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//	@JoinTable(name = "topicMapping", joinColumns = { @JoinColumn(name = "topicId") }, inverseJoinColumns = {
//			@JoinColumn(name = "parentId") })
//	private Set<Topic> topicSet;


	/**
	 * @return the topicSet
	 */
//	public Set<Topic> getTopicSet() {
//		return topicSet;
//	}
//
//
//
//	/**
//	 * @param topicSet the topicSet to set
//	 */
//	public void setTopicSet(Set<Topic> topicSet) {
//		this.topicSet = topicSet;
//	}
//	
	
//	  private Set<TopicMapping> topicMappings = new HashSet<TopicMapping>();
//
//
//	 @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//	    public Set<TopicMapping> getTopicMapping() {
//	        return topicMappings;
//	    }


	/**
	 * @return the topicMappings
	 */
//	public Set<TopicMapping> getTopicMappings() {
//		return topicMappings;
//	}
//
//
//	/**
//	 * @param topicMappings the topicMappings to set
//	 */
//	public void setTopicMappings(Set<TopicMapping> topicMappings) {
//		this.topicMappings = topicMappings;
//	}
//
//	
//	 
	
	
	
}
