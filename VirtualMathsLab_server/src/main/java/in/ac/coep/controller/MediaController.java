package in.ac.coep.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import in.ac.coep.entity.User;
import in.ac.coep.service.MediaService;

@Controller
@RequestMapping(value = "/media")
public class MediaController {

	
	@Autowired 
	private MediaService mediaService;
	
	private static final Logger LOGGER = Logger.getLogger(MediaController.class);
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String uploadMediaFile(@RequestParam MultipartFile file) {

		JSONObject data = new JSONObject();
		try {
			LOGGER.info("Media Upload Start ..");
			data = mediaService.uploadMediaFile(file);
			LOGGER.info("Media Upload End ..");
			return data.toString();
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Media Upload Failed ..",e);
			return null;
		}
		
	}
	
	
	
	
	@RequestMapping(value = "/profImage", method = RequestMethod.POST)
	public @ResponseBody String uploadProfilePic(Authentication authentication, @RequestParam MultipartFile file) {

		User user = (User) authentication.getPrincipal();
		JSONObject data = new JSONObject();
		try {
			LOGGER.info("Media Upload Start ..");
			data = mediaService.uploadProfilePic(file, user);
			LOGGER.info("Media Upload End ..");
			return data.toString();
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Media Upload Failed ..",e);
			return null;
		}
		
	}
	
	@RequestMapping(value = "/getProfImage", method = RequestMethod.GET)
	public @ResponseBody
	byte[] getmedia(Authentication authentication,
			@RequestParam String mediaID, HttpServletResponse response, HttpServletRequest request) {

		byte[] temp = null;
		try {
			if(!(mediaID.equalsIgnoreCase("undefined"))) {
			temp = mediaService.getImageByMediaId(
					authentication.getName(),request, mediaID);
			response.setContentType("image/jpg");
			}
		} catch (Exception e) {
			System.out.println("Profile picture url not found");
//			e.printStackTrace();
			return null;
		}
		return temp;
	}
	
	
	
	// to upload image of question group
	 @RequestMapping(value = "/get/getImage", method = RequestMethod.GET)
	   
		public @ResponseBody
		byte[] getmediaFile(Authentication authentication,
				@RequestParam String mediaID, HttpServletResponse response, HttpServletRequest request) {
			byte[] temp = null;
			try {
				temp = mediaService.getImageByMediaId(
						authentication.getName(),request, mediaID);
				response.setContentType("image/jpg");
				
			} catch (Exception e) {
				e.printStackTrace();

				return null;
			}
			return temp;
		}
		
		
   @RequestMapping(value = "/getImage", method = RequestMethod.GET)
   
	public @ResponseBody
	byte[] getmedia(Authentication authentication,
			@RequestParam String mediaID, @RequestParam String questionGroupId, HttpServletResponse response, HttpServletRequest request) {
//	   JSONObject data = new JSONObject();
		byte[] temp = null;
		try {
//			LOGGER.info("Media Upload Start ..");
			temp = mediaService.getMediaImageByQuestionGroupId(
					authentication.getName(),request, mediaID, questionGroupId);
//			LOGGER.info("Media Upload End ..");
			response.setContentType("image/jpg");
			
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
		return temp;
	}
	
}
