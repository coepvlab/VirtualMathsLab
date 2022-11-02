package in.ac.coep.vo;

/**
 * @author Prashant
 *
 */
public class MediaVO {
	
	
	private int mediaId;

	private String content;
	
	private String urlPath; 

	private boolean isUrl;

	/**
	 * @return the mediaId
	 */
	public int getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
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
	 * @return the urlPath
	 */
	public String getUrlPath() {
		return urlPath;
	}

	/**
	 * @param urlPath the urlPath to set
	 */
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	/**
	 * @return the isUrl
	 */
	public boolean isUrl() {
		return isUrl;
	}

	/**
	 * @param isUrl the isUrl to set
	 */
	public void setUrl(boolean isUrl) {
		this.isUrl = isUrl;
	}
	
	
}
