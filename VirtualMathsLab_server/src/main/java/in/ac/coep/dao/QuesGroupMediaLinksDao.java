/**
 * 
 */
package in.ac.coep.dao;

import in.ac.coep.entity.QuesGroupMediaLinks;

/**
 * @author Prashant
 *
 */
public interface QuesGroupMediaLinksDao {

	
	public long addQuesGroupMediaLinks(QuesGroupMediaLinks mediaLinks) throws Exception;

	public QuesGroupMediaLinks getQGMedilLinkById(long quesGroupMediaLinkId)  throws Exception;

	public void updateQuesGroupMediaLinks(QuesGroupMediaLinks quesGroupMediaLinks)  throws Exception;
}
