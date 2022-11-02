package in.ac.coep.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.MediaTypeDao;
import in.ac.coep.entity.MediaType;
import in.ac.coep.service.MediaTypeService;

/**
 * @author Prashant
 *
 */
@Service
public class MediaTypeServiceImpl implements MediaTypeService{

	
	private static final Logger LOGGER = Logger.getLogger(MediaTypeServiceImpl.class);

	
	@Autowired
	private MediaTypeDao mediaTypeDao;
	
	
	@Override
	public JSONObject fetchMediaTypes() throws Exception {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();

		LOGGER.info("Fetch MediaType Start ..");

		List<MediaType> mediaTypes = mediaTypeDao.fetchMediaType();

		if (mediaTypes.size() != 0) {
			JSONArray jsonArray = new JSONArray();

			JSONObject medTypeJsonObject = null;
			for (MediaType mediaType : mediaTypes) {
				
				medTypeJsonObject = new JSONObject();
				medTypeJsonObject.put("MTN", mediaType.getName());
				medTypeJsonObject.put("MTID", mediaType.getMediaTypeId());

				 jsonArray.put(medTypeJsonObject);
			}

			data.put("done", true);
			data.put("data", jsonArray);

			LOGGER.info("Fetch MediaType End ..");
		} else {
			LOGGER.info("Fetch MediaType Failed ..");
			data.put("done", false);
			data.put("msg", "MediaType not available");
		}

		return data;

	}

}
