/**
 * 
 */
package ac.in.coep.service;

import java.io.InputStream;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

/**
 * @author Laxmikant Kumbhare.
 * @Date Oct 28, 2013
 *       <p>
 * 
 *       This interface provides functionality to store files on mongo db
 *       </p>
 */
public interface FileStorageOnMongo {

	/**
	 * @author Laxmikant Kumbhare
	 * @Date Sep 27, 2013
	 * @param inputStream
	 *            is {@link InputStream} which is to be stored on mongo db
	 * 
	 * @param fileName
	 *            is name of file stored on mongo
	 * @return {@link GridFSFile} which contains storage info
	 * @since vtu 1.0
	 *        <p>
	 *        This method stores file on mongo db and returns storage info.
	 * 
	 *        </p>
	 */

	public GridFSFile storeFileOnMongoDB(InputStream inputStream,
			String fileName) throws Exception;

	public GridFSDBFile getFileFromMongoWithThisId(String documentMongoId)
			throws Exception;
//	
//	public void deleteFileFromMongoWithThisId(String documentMongoId) throws Exception;
	public void deleteFileFromMongoWithThisId(String documentMongoId) throws Exception;
}
