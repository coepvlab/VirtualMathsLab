package in.ac.coep.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
@Entity
@Table(name = "performancetag")
public class PerformanceTag implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tagId")
	private long ptagId;

	@Column
	private long updatedTime;

	@Column
	private char tag;
	
	@OneToOne
	@ForeignKey(name = "PT_userId")
	private User user;
	
	@Column
	private String topics;
	
	@Column
	private int variationNo;

	public long getPtagId() {
		return ptagId;
	}

	public void setPtagId(long ptagId) {
		this.ptagId = ptagId;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

//	public String getTag() {
//		return tag;
//	}
//
//	public void setTag(String tag) {
//		this.tag = tag;
//	}

	public User getUser() {
		return user;
	}

	public char getTag() {
		return tag;
	}

	public void setTag(char tag) {
		this.tag = tag;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public int getVariationNo() {
		return variationNo;
	}

	public void setVariationNo(int variationNo) {
		this.variationNo = variationNo;
	}
	
	
	
	
}
