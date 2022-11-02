/**
 * 
 */
package in.ac.coep.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.ComplexityLevelDao;
import in.ac.coep.entity.QGComplexityLevel;
import in.ac.coep.service.ComplexityLevelService;

/**
 * @author Prashant
 *
 */
@Service
public class ComplexityLevelServiceImpl implements ComplexityLevelService {
	
	private static final Logger LOGGER = Logger.getLogger(ComplexityLevelServiceImpl.class);

	/* (non-Javadoc)
	 * @see in.ac.coep.service.ComplexityLevelService#fetchComplexityLevel()
	 */
	
	@Autowired
	ComplexityLevelDao complexityLevelDao;
	
	@Override
	public JSONObject fetchComplexityLevel() throws Exception {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();

		LOGGER.info("Fetch Complexity level Start ..");

		List<QGComplexityLevel> complexityLevels = complexityLevelDao.fetchComplexityLevel();

		if (complexityLevels.size() != 0) {
			JSONArray jsonArray = new JSONArray();

			JSONObject deptJsonObject = null;
			for (QGComplexityLevel complexityLevel : complexityLevels) {
				
				deptJsonObject = new JSONObject();
				deptJsonObject.put("CLN", complexityLevel.getName());
				 deptJsonObject.put("CLID", complexityLevel.getQgComplexityLevelId());

				 jsonArray.put(deptJsonObject);
			}

			data.put("done", true);
			data.put("data", jsonArray);

			LOGGER.info("Fetch Complexity level End ..");
		} else {
			LOGGER.info("Fetch Complexity level Failed ..");
			data.put("done", false);
			data.put("msg", "Complexity level not available");
		}

		return data;

	}

}
