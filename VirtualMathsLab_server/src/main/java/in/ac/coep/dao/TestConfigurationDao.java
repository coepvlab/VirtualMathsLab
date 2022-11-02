package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.TestConfiguration;

/**
 * @author Prashant
 *
 */
public interface TestConfigurationDao {

	/**
	 * @param testConfiguration
	 * @return 
	 */
	public long configureTest(TestConfiguration testConfiguration)throws Exception;

	/**
	 * @param testConfigId
	 * @return
	 */
	public TestConfiguration getTestConfigurationById(long testConfigId)throws Exception;

	/**
	 * @param testConfiguration
	 */
	public void modifyTestPaper(TestConfiguration testConfiguration)throws Exception;

	/**
	 * @param sectionId
	 * @param testId 
	 * @return
	 */
//	public List<TestConfiguration> fetchtestConfigurationDataBySectionId(int sectionId, long testId)throws Exception;

	/**
	 * @param testId
	 * @return
	 */
	public List<TestConfiguration> fetchTestConfigurationOfTestIdGroupBySection(long testId)throws Exception;
	
	public List<TestConfiguration> getAllTestConfigDetails(long testId)throws Exception;

	public List<TestConfiguration> fetchComplexcityLvlBySectId(int sectId, int complexLevel) throws Exception;

	public List<TestConfiguration> fetchtestConfigurationDataByTopicId(long topicId, long testId) throws Exception;


}
