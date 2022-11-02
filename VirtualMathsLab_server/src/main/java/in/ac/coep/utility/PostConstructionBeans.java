/**
 * 
 */
package in.ac.coep.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ac.coep.constants.Constants;
import in.ac.coep.dao.SystemConfigDao;
import in.ac.coep.dao.TopicDao;
import in.ac.coep.entity.SystemConfig;
import in.ac.coep.entity.Topic;

/**
 * @author Prashant
 *
 */
@Service
public class PostConstructionBeans {

	@Autowired
	private SystemConfigDao systemConfigDao;

	@Autowired
	private TopicDao topicDao;

//	@Autowired
//	private QuestionGroupDao questionGroupDao;

//	@Autowired
//	TestInstanceStateDao testInstanceStateDao;
//
//	@Autowired
//	TestStatisticDao testStatisticDao;

//	@Autowired
//	CreatePdfReportService createPdfReportService;

	@PostConstruct
	public void setSystemConfigRecordsInConstants() {
		try {
			List<SystemConfig> configs = systemConfigDao.fetchAllRecordsFromSystemConfig();

			for (SystemConfig systemConfig : configs) {
				Constants.sysConfig.sysConfigMap.put(systemConfig.getConfigKey(), systemConfig.getConfigValue());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void setAllTopicsInConstants() {
		try {
			List<Topic> topics = topicDao.getAllTopicDetails();

			for (Topic topic : topics) {
				Constants.topics.topicsMap.put(topic.getTopicId(), topic.getTopicName()+"-"+topic.getTopicName1());
//				System.out.println(topic.getTopicId());
//				System.out.println(topic.getTopicName()+"-"+topic.getTopicName1());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

//	@PostConstruct
//	public void setQuestionGroupIdF() {
//		try {
//			List<QuestionGroup> questionGroups = questionGroupDao
//					.getAllQuestionGroupsBySectionIdAndLevel(Constants.psySecId, Constants.psySecLevelforintro);
//
//			for (QuestionGroup questionGroup : questionGroups) {
//				Constants.QuestionGroupTF.QuestionGroupLvl1.put(questionGroup.getQuestionGroupId(),
//						questionGroup.getName());
//			}
//
//			List<QuestionGroup> questionGroupsMca = questionGroupDao
//					.getAllQuestionGroupsBySectionIdAndLevel(Constants.psySecId, Constants.psySecLevelformca);
//
//			for (QuestionGroup questionGroup : questionGroupsMca) {
//				Constants.QuestionGroupMca.QuestionGroupLvl3.put(questionGroup.getQuestionGroupId(),
//						questionGroup.getName());
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}

	// @PostConstruct
	@SuppressWarnings("resource")
	public void createExcel() {

		try {
			// Blank workbook
			XSSFWorkbook workbook = new XSSFWorkbook();

			// Create a blank sheet

			XSSFSheet sheet = workbook.createSheet("Employee Data");

			// This data needs to be written (Object[])
//			List<TestInstanceState> data = testInstanceStateDao.getTestInstanceStateByStatus("C");
			int rownum = 0;
			Row rowHead = sheet.createRow(rownum++);
			int cellNumHead = 0;

			rowHead.createCell(cellNumHead++).setCellValue("Record Id");

			rowHead.createCell(cellNumHead++).setCellValue("User");

			rowHead.createCell(cellNumHead++).setCellValue("Test");

			rowHead.createCell(cellNumHead++).setCellValue("Department");

			rowHead.createCell(cellNumHead++).setCellValue("Section");

			rowHead.createCell(cellNumHead++).setCellValue("Total No of Question");

			rowHead.createCell(cellNumHead++).setCellValue("Passed No of Question");

			rowHead.createCell(cellNumHead++).setCellValue("Percentage");

			rowHead.createCell(cellNumHead++).setCellValue("Grade");

//			for (TestInstanceState testInstanceState : data) {
//				List<TestInstanceStatistics> instanceStatistics = testStatisticDao
//						.getAllUsersStatisticsByuserIdFrmState(testInstanceState.getUser().getUserId());
//
//				for (TestInstanceStatistics testInstanceStatistics : instanceStatistics) {
//
//					Row row = sheet.createRow(rownum++);
//					int cellNum = 0;
//
//					row.createCell(cellNum++).setCellValue((Long) testInstanceStatistics.getTestInstanceStatisticId());
//
//					row.createCell(cellNum++)
//							.setCellValue(testInstanceStatistics.getUser().getFirstName() + " "
//									+ testInstanceStatistics.getUser().getLastName() + "-"
//									+ testInstanceStatistics.getUser().getEmailId());
//					row.createCell(cellNum++).setCellValue(
//							testInstanceState.getTest().getTestId() + "-" + testInstanceState.getTest().getName());
//
////					row.createCell(cellNum++).setCellValue(testInstanceStatistics.getDepartment().getName());
////					row.createCell(cellNum++).setCellValue(testInstanceStatistics.getSection().getName());
//					row.createCell(cellNum++).setCellValue((Long) testInstanceStatistics.getTotalNoOfQuestions());
//
//					row.createCell(cellNum++).setCellValue((Long) testInstanceStatistics.getPassedQuestions());
//					row.createCell(cellNum++).setCellValue((Float) testInstanceStatistics.getPercentage());
//					row.createCell(cellNum++).setCellValue(testInstanceStatistics.getGrade());
//
//					System.gc();
//				}
//
//			}

			FileOutputStream out = new FileOutputStream(new File("E:/howtodoinjava_dem.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
