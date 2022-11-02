package in.ac.coep.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.AnserTypeDao;
import in.ac.coep.entity.AnswerType;
import in.ac.coep.service.AnswerTypeService;
@Service
public class AnswerTypeServiceImpl implements AnswerTypeService{
	private static final Logger LOGGER = Logger.getLogger(AnswerTypeServiceImpl.class);

	
	@Autowired
	AnserTypeDao AnserTypeDao;
	
	@Override
	public JSONObject fetchAnswerTypes() throws Exception {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();

		LOGGER.info("Fetch AnswerType Start ..");

		List<AnswerType> answerTypes = AnserTypeDao.fetchAnswerType();

		if (answerTypes.size() != 0) {
			JSONArray jsonArray = new JSONArray();

			JSONObject ansTypeJsonObject = null;
			for (AnswerType answerType : answerTypes) {
				
				ansTypeJsonObject = new JSONObject();
				ansTypeJsonObject.put("ATN", answerType.getName());
				ansTypeJsonObject.put("ATID", answerType.getAnswerTypeId());

				 jsonArray.put(ansTypeJsonObject);
			}

			data.put("done", true);
			data.put("data", jsonArray);

			LOGGER.info("Fetch AnswerType End ..");
		} else {
			LOGGER.info("Fetch AnswerTypeFailed ..");
			data.put("done", true);
			data.put("msg", "AnswerType not available");
		}

		return data;

	}

}
