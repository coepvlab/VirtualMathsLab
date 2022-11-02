/**
 * 
 */
package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.QGComplexityLevel;

/**
 * @author Prashant
 *
 */
public interface ComplexityLevelDao {

	public List<QGComplexityLevel> fetchComplexityLevel() throws Exception;

}
