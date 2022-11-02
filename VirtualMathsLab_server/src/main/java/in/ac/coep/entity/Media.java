//package in.ac.coep.entity;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import org.springframework.stereotype.Component;
//
///**
// * @author Prashant
// *
// */
//@SuppressWarnings("serial")
//@Component
//@Entity
//@Table(name = "media")
//public class Media  implements Serializable {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "mediaId")
//	private int mediaId;
//
//	@Column
//	private String content;
//	
//	@Column
//	private String urlPath; 
//	
//	@Column
//	private boolean isUrl;
//
//	@Column
//	private String bucketName;
//	/**
//	 * @return the mediaId
//	 */
//	public long getMediaId() {
//		return mediaId;
//	}
//
//	/**
//	 * @param mediaId the mediaId to set
//	 */
//	public void setMediaId(int mediaId) {
//		this.mediaId = mediaId;
//	}
//
//	/**
//	 * @return the content
//	 */
//	public String getContent() {
//		return content;
//	}
//
//	/**
//	 * @param content the content to set
//	 */
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	/**
//	 * @return the urlPath
//	 */
//	public String getUrlPath() {
//		return urlPath;
//	}
//
//	/**
//	 * @param urlPath the urlPath to set
//	 */
//	public void setUrlPath(String urlPath) {
//		this.urlPath = urlPath;
//	}
//
//	/**
//	 * @return the isUrl
//	 */
//	public boolean isUrl() {
//		return isUrl;
//	}
//
//	/**
//	 * @param isUrl the isUrl to set
//	 */
//	public void setUrl(boolean isUrl) {
//		this.isUrl = isUrl;
//	}
//
//	/**
//	 * @return the bucketName
//	 */
//	public String getBucketName() {
//		return bucketName;
//	}
//
//	/**
//	 * @param bucketName the bucketName to set
//	 */
//	public void setBucketName(String bucketName) {
//		this.bucketName = bucketName;
//	}
//	
//	
//}
