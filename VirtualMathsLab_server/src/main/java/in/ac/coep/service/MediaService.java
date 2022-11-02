package in.ac.coep.service;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import in.ac.coep.entity.User;

public interface MediaService {

	public JSONObject uploadMediaFile(MultipartFile file)throws Exception;

	public JSONObject uploadProfilePic(MultipartFile file, User user)throws Exception;
	
	public byte[] getImageByMediaId(String name, HttpServletRequest request, String mediaID)throws Exception;

	public byte[] getMediaImageByQuestionGroupId(String name, HttpServletRequest request, String mediaID, String questionGroupId) throws Exception;
}
