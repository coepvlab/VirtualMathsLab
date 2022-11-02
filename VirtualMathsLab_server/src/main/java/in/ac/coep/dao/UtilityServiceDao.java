/**
 * 
 */
package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.MathsFileStorage;

/**
 * @author Prashant
 *
 */
public interface UtilityServiceDao {
	
	public void save(MathsFileStorage fileStorage) throws Exception;

	public List<MathsFileStorage> getAllFiles() throws Exception;

	
}
