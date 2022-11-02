/**
 * 
 */
package in.ac.coep.serviceImpl;

import java.io.InputStream;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

import in.ac.coep.service.FileStorageOnMongo;



/**
 * @author Laxmikant Kumbhare.
 * @Date Oct 28, 2013
 *       <p>
 * 
 * 
 *       </p>
 */
@Service
public class FileStorageOnMongoImpl implements FileStorageOnMongo {

	@Autowired
	private GridFsOperations gridOperation;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ac.in.coep.vtu.services.FileStorageOnMongo#storeFileOnMongoDB()
	 */
	@Override
	public GridFSFile storeFileOnMongoDB(InputStream inputStream,
			String fileName) throws Exception {
		// TODO Auto-generated method stub
		GridFSFile file = gridOperation.store(inputStream, fileName);
		return file;
	}

	@Override
	public GridFSDBFile getFileFromMongoWithThisId(String documentMongoId)
			throws Exception {
		// TODO Auto-generated method stub

		return gridOperation.findOne(new Query(Criteria.where("_id").is(
				new ObjectId(documentMongoId))));

	}

//	@Override
//	public void deleteFileFromMongoWithThisId(String documentMongoId)
//			throws Exception {
//		
//		// TODO Auto-generated method stub
//		System.out.println("documentMongoId : <<<<<<-"+documentMongoId+"->>>>>>>");
//		gridOperation.delete(new Query(Criteria.where("_id").is(new ObjectId(documentMongoId))));
//		//mongoTemplate.remove(new Query(Criteria.where("id").is(documentMongoId)));
//		
//		
//	}
	
	
	@Override
	public void deleteFileFromMongoWithThisId(String documentMongoId)
			throws Exception {
		
		// TODO Auto-generated method stub
		System.out.println("documentMongoId : <<<<<<-"+documentMongoId+"->>>>>>>");
		gridOperation.delete(new Query(Criteria.where("_id").is(new ObjectId(documentMongoId))));
		//mongoTemplate.remove(new Query(Criteria.where("id").is(documentMongoId)));
		
		
	}
	
	 
}
