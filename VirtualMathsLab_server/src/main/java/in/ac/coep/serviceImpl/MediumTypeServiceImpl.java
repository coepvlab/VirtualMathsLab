package in.ac.coep.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.MediumTypeDao;
import in.ac.coep.entity.Medium;
import in.ac.coep.service.MediumTypeService;

@Service
public class MediumTypeServiceImpl implements MediumTypeService{
	private static final Logger LOGGER = Logger.getLogger(MediumTypeServiceImpl.class);

	
	@Autowired
	MediumTypeDao mediumTypeDao;
	
	@Override
	public JSONObject fetchMediumTypes() throws Exception {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();

		LOGGER.info("Fetch MediumType Start ..");

		List<Medium> mediumTypes = mediumTypeDao.fetchMediumType();

		if (mediumTypes.size() != 0) {
			JSONArray jsonArray = new JSONArray();

			JSONObject medTypeJsonObject = null;
			for (Medium medType : mediumTypes) {
				
				medTypeJsonObject = new JSONObject();
				medTypeJsonObject.put("MN", medType.getMedium());
				medTypeJsonObject.put("MID", medType.getMediumId());

				 jsonArray.put(medTypeJsonObject);
			}

			data.put("done", true);
			data.put("data", jsonArray);

			LOGGER.info("Fetch mediumType End ..");
		} else {
			LOGGER.info("Fetch mediumTypeFailed ..");
			data.put("done", true);
			data.put("msg", "MediumType not available");
		}

		return data;

	}

}
