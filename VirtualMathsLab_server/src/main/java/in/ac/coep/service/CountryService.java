package in.ac.coep.service;

import org.json.JSONObject;

public interface CountryService {
	
	public JSONObject fetchAllCountries()throws Exception;

	public JSONObject fetchAllStates(int id)throws Exception;

	public JSONObject fetchAllCity(int id)throws Exception;

}
