/**
 * 
 */
package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.TestInstanceCompletion;

/**
 * @author Prashant
 *
 */
public interface TestInstanceCompletionDao {

	/**
	 * @param completion
	 */
	public void save(TestInstanceCompletion completion)throws Exception;

	/**
	 * @return
	 */
	public List<Object[]> fetchRecordsGroupByUserAndSections()throws Exception;

	/**
	 * @param long1
	 * @param testInstanceStateId
	 * @return
	 */
	public List<TestInstanceCompletion> getByQuestionGroupIdAndTisId(Long long1, long testInstanceStateId,boolean lvl1)throws Exception;

	/**
	 * @param i
	 * @param testInstanceStateId
	 * @return
	 */
	public List<TestInstanceCompletion> getByQuestionGroupLvlAndTisId(int i, long testInstanceStateId)throws Exception;

	public List<TestInstanceCompletion> getTestInstanceByTISTdfromCompletion(long testInstanceStateId, long l)throws Exception;

	public List<TestInstanceCompletion> fetchRecordsGroupByUserIdAndTISID(long userId, long tisId) throws Exception;


	
	
}
