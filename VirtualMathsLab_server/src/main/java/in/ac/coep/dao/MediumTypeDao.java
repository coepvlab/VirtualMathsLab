package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.Medium;

public interface MediumTypeDao {
	
	public List<Medium> fetchMediumType() throws Exception;
	
}
