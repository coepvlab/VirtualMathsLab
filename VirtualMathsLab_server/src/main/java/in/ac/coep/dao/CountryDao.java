package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.Cities;
import in.ac.coep.entity.Countries;
import in.ac.coep.entity.States;




public interface CountryDao {
	
	public List<Countries> fetchAllCountries()throws Exception;

	public List<States> fetchAllStates(int id)throws Exception;

	public List<Cities> fetchAllCity(int id)throws Exception;

}
