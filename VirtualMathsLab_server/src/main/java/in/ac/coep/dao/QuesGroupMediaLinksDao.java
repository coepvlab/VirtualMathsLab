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

	public QuesGroupMediaLinks getQGMedilLinkByMediaId(long quesGroupMediaLinks)throws Exception;

	public void deleteQuesGroupMediaLink(QuesGroupMediaLinks qgml)throws Exception;
}
