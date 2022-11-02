package in.ac.coep.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.dao.CountryDao;
import in.ac.coep.entity.Cities;
import in.ac.coep.entity.Countries;

import in.ac.coep.entity.States;

import in.ac.coep.service.CountryService;

@Service
public class CountrySrviceImpl implements CountryService {
	
	private static final Logger LOGGER = Logger.getLogger(CountrySrviceImpl.class);
	
	@Autowired
	CountryDao countrydao;

	@Override
	public JSONObject fetchAllCountries() throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();

		List<Countries> countries = countrydao.fetchAllCountries();
		
		if (countries.size() != 0) {
			JSONArray jsonArray = new JSONArray();

			JSONObject countryJsonObject = null;
			for (Countries country : countries) {
				
				countryJsonObject = new JSONObject();
				countryJsonObject.put("CN", country.getName());
				countryJsonObject.put("CID", country.getId());

				 jsonArray.put(countryJsonObject);
			}

			data.put("done", true);
			data.put("data", jsonArray);

			LOGGER.info("Fetch Countries End ..");
		} else {
			LOGGER.info("Fetch Countries Failed ..");
			data.put("done", true);
			data.put("msg", "Countries data not available");
		}

		return data;

	}

	@Override
	public JSONObject fetchAllStates(int id) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();

		List<States> states = countrydao.fetchAllStates(id);
		
		if (states.size() != 0) {
			JSONArray jsonArray = new JSONArray();

			JSONObject stateJsonObject = null;
			for (States state : states) {
				
				stateJsonObject = new JSONObject();
				stateJsonObject.put("SN", state.getName());
				stateJsonObject.put("SID", state.getId());

				 jsonArray.put(stateJsonObject);
			}

			data.put("done", true);
			data.put("data", jsonArray);

			LOGGER.info("Fetch states End ..");
		} else {
			LOGGER.info("Fetch states Failed ..");
			data.put("done", true);
			data.put("msg", "states data not available");
		}

		return data;
		
		
		
	}

	@Override
	public JSONObject fetchAllCity(int id) throws Exception {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();

		List<Cities> cities = countrydao.fetchAllCity(id);
		
		if (cities.size() != 0) {
			JSONArray jsonArray = new JSONArray();

			JSONObject cityJsonObject = null;
			for (Cities city : cities) {
				
				cityJsonObject = new JSONObject();
				cityJsonObject.put("CIN", city.getName());
				cityJsonObject.put("CIID", city.getId());

				 jsonArray.put(cityJsonObject);
			}

			data.put("done", true);
			data.put("data", jsonArray);

			LOGGER.info("Fetch city End ..");
		} else {
			LOGGER.info("Fetch city Failed ..");
			data.put("done", true);
			data.put("msg", "city data not available");
		}

		return data;
		
		
	}
		
		
		
	}

	


