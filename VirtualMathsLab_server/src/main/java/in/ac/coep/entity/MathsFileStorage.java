/**
 * 
 */
package in.ac.coep.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "fileStorage")
public class MathsFileStorage implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fileStorageId")
	private int fileStorageId;

	@Column
	private String fileName;
	
	@Column
	private String fileExt;

	@Column
	private String mediaURL; 
	
	@Column
	private String assignNo;
	
	@Column
	private String topicNo;
	
	@Column
	private String varNo; // variation number
	
	@OneToOne
	@ForeignKey(name = "FS_userId")
	private User user;

	@Column
	private Date createDate;

	@Column
	private Date updatedDate;

	/**
	 * @return the fileStorageId
	 */
	public int getFileStorageId() {
		return fileStorageId;
	}

	/**
	 * @param fileStorageId the fileStorageId to set
	 */
	public void setFileStorageId(int fileStorageId) {
		this.fileStorageId = fileStorageId;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileExt
	 */
	public String getFileExt() {
		return fileExt;
	}

	/**
	 * @param fileExt the fileExt to set
	 */
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	/**
	 * @return the mediaURL
	 */
	public String getMediaURL() {
		return mediaURL;
	}

	/**
	 * @param mediaURL the mediaURL to set
	 */
	public void setMediaURL(String mediaURL) {
		this.mediaURL = mediaURL;
	}

	/**
	 * @return the assignNo
	 */
	public String getAssignNo() {
		return assignNo;
	}

	/**
	 * @param assignNo the assignNo to set
	 */
	public void setAssignNo(String assignNo) {
		this.assignNo = assignNo;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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
