package in.ac.coep.serviceImpl;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSFile;

import in.ac.coep.controller.MediaController;
import in.ac.coep.dao.UserDao;
import in.ac.coep.entity.User;
import in.ac.coep.service.FileStorageOnMongo;
import in.ac.coep.service.MediaService;

@Service
public class MediaServiceImpl implements MediaService {

	private static final Logger LOGGER = Logger.getLogger(MediaController.class);

@Autowired
private FileStorageOnMongo fileStorageOnMongo;

@Autowired
private UserDao userDao;

	@Override
	public JSONObject uploadProfilePic(MultipartFile file, User user) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();
		
		LOGGER.info("Media Upload Start ..");
		if (file != null) {

			GridFSFile gridFSFile = fileStorageOnMongo.storeFileOnMongoDB(file.getInputStream(), file.getOriginalFilename());

			 if(gridFSFile.getId() == null)
			 {
				 data.put("done", false);
					data.put("msg", "Media Upload Failed, Please Try again");
			 }
			 else{
				 String fileUrl = gridFSFile.getId().toString();
				
				 data.put("done", true);
				 data.put("URL", fileUrl);
				 
				 user.setProfilePicUrl(fileUrl);
				 userDao.updateUSer(user);
				 data.put("msg", "Media Uploaded successfully");
				 
			 }

			LOGGER.info("Media Upload and Saved End ..");
		}
		return data;
	}

	@Override
	public byte[] getImageByMediaId(String name, HttpServletRequest request, String mediaID) throws Exception {
		// TODO Auto-generated method stub

		byte[] tmp = null;

		if (mediaID != null) {

			InputStream is = fileStorageOnMongo.getFileFromMongoWithThisId(mediaID).getInputStream();
			if (is != null) {
				tmp = IOUtils.toByteArray(is);
			}
		}
		return tmp;
	}
	
	@Override
	public JSONObject uploadMediaFile(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();

		LOGGER.info("Media Upload Start ..");
		if (file != null) {

			GridFSFile gridFSFile = fileStorageOnMongo.storeFileOnMongoDB(file.getInputStream(),
					file.getOriginalFilename());

			if (gridFSFile.getId() == null) {
				data.put("done", false);
				data.put("msg", "Media Upload Failed, Please Try again");
			} else {
				String fileUrl = gridFSFile.getId().toString();

				data.put("done", true);
				data.put("URL", fileUrl);
				data.put("msg", "Media Uploaded successfully");
			}

			LOGGER.info("Media Upload and Saved End ..");
		}
		return data;
	}

	@Override
	public byte[] getMediaImageByQuestionGroupId(String name, HttpServletRequest request, String mediaID,
			String questionGroupId) throws Exception {
		// TODO Auto-generated method stub
		
		byte[] tmp = null;
		
			if(mediaID != null && questionGroupId != null){
			
			InputStream is = fileStorageOnMongo.getFileFromMongoWithThisId(mediaID).getInputStream();
			if (is != null) {
				tmp = IOUtils.toByteArray(is);
			} 
			}
		return tmp;
	
	}
	
	
//	public static String generateRandomChars(String candidateChars, int length) {
//	    StringBuilder sb = new StringBuilder();
//	    Random random = new Random();
//	    for (int i = 0; i < length; i++) {
//	        sb.append(candidateChars.charAt(random.nextInt(candidateChars
//	                .length())));
//	    }
//
//	    return sb.toString();
//	}
}
