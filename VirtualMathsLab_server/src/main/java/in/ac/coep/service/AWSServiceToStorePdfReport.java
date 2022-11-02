/**
 * 
 */
package in.ac.coep.service;

import java.io.File;

/**
 * @author Prashant
 *
 */
public interface AWSServiceToStorePdfReport {

	/**
	 * @param file
	 */
	public String createAwsUrlForReport(File file)throws Exception;

	
	
	
}
