/**
 * 
 */
package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.MathsFileStorage;
import in.ac.coep.entity.QuestionGroup;
import in.ac.coep.entity.TestType;

/**
 * @author Prashant
 *
 */
public interface UtilityServiceDao {
	
	public void save(MathsFileStorage fileStorage) throws Exception;

	public List<MathsFileStorage> getAllFiles() throws Exception;

	public List<TestType> getCallDemoData()throws Exception;

	
}
