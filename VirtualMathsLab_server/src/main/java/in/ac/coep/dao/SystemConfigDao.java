/**
 * 
 */
package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.SystemConfig;

/**
 * @author Prashant
 *
 */
public interface SystemConfigDao {

	/**
	 * @return
	 */
	public List<SystemConfig> fetchAllRecordsFromSystemConfig()throws Exception;

	public SystemConfig getcurrentItoregistrationId()throws Exception;

	public void update(SystemConfig sysconfig)throws Exception;
	
	
	

	
}
