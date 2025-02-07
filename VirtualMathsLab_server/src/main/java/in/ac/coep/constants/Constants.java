package in.ac.coep.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Prashant
 *
 */
public interface Constants {

	public static final String ItoRegistrationId = "GO";
	
	public static final String quesGrpMsg = "Question Group added Or modified";

	public static final String quesGrpMsgFail = "Question Group failed To add Or modify";

	public static final String quesGrpMsgArchive = "Question Group moved to archive";

	public static final String quesGrpMsgActive = "Question Group is active now";

	public static final String testcreationsuccess = "Test Created Successfully";

	public static final String testcreationfail = "Test Creation Failed, Please try later";

	public static final String testConfigurationsuccess = "Test Configured Successfully";

	public static final String testConfigurationfail = "Test Configuration Failed, Please try later";
	
	public static final String testConfigurationUpdatesuccess = "Test Configuration updated Successfully";

	public static final String testConfigUpdatefail = "Test Configuration updation Failed Please try later";
	
	public static final String CSRF_TOKEN = "token";
	
//	public static final String baseURLPath = "http://192.168.1.35:8080/VirtualMathsLab/"; 
//	public static final String srcURLForSinglePdfResult = "E:/PdfReports/coep/";
//	public static final String destURLForSinglePdfResult = "E:/PdfReports/coep/";
//	public static final String path = "\\";
//	public static final String CERTIFICATE_IMG_DEST = "D:/images/Certificates/";
//	public static final String mathsFileUplaod = "D:\\VirtualMathLab_2020\\VirtualMathsLab\\src\\main\\java\\intechOlympiad\\";

//	COEP server
	
	public static final String baseURLPath = "https://portal.coepvlab.ac.in/VirtualMathsLab/";
	public static final String srcURLForSinglePdfResult = "/home/administrator/reports/";
	public static final String destURLForSinglePdfResult = "/home/administrator/MergeReports/";
	public static final String path = "/";
	public static final String CERTIFICATE_IMG_DEST = "/home/administrator/Certificates/";
	public static final String mathsFileUplaod = "/var/lib/tomcat8/webapps/VirtualMathsLab/WEB-INF/classes/intechOlympiad/";
	
//	AWS 
//	public static final String baseURLPath = "https://portal.coepvlab.ac.in/VirtualMathsLab/";
//	public static final String srcURLForSinglePdfResult = "/home/administrator/reports/";
//	public static final String destURLForSinglePdfResult = "/home/administrator/MergeReports/";
//	public static final String path = "/";
//	public static final String CERTIFICATE_IMG_DEST = "/home/administrator/Certificates/";
//	public static final String mathsFileUplaod = "/var/lib/tomcat8/webapps/VirtualMathsLab/WEB-INF/classes/intechOlympiad/";
	
	
	public static final String mathsFileUplaodPackage = "intechOlympiad.";
	
	public static final String imageBaseURL = "http://localhost:8080/VirtualMathsLab/resource/images/";
	
	public static final String REGISTER_SUCCESS_MAIL_SUBJECT = "Virtual Maths Lab Registration !!!";
	
//	public static final String PDFREPORT_SUCCESS_MAIL_SUBJECT = "ITO 2017 SCORECARD REPORT for  ";
	
	public static final String FORGOTPASSWORD_MAIL_SUBJECT = "Reply to Forgot Password Request";
	
	public static final String REGISTER_SUCCESS_MAIL_TEMPLATE = "register.html";
	
	public static final String FORGOTPASSWORD_MAIL_TEMPLATE = "forgotpass.html";
	
	public static final String PDFREPORT_SUCCESS_MAIL_TEMPLATE = "sendPdfReportinEmailAttachment.html";
	
//	public static final String FROM_EMAIL = "intecholympiad-noreply@coep.ac.in";
//	public static final String FROM_EMAIL = "itorcpp-noreply@coep.ac.in";
	public static final String FROM_EMAIL = "virtualmathslab.noreply@gmail.com";
	
	public static final String splitter = "#";


	public static final long answer_Dummy_Record_Id = 99999999;

	
	public static final int noOfAttemptForTest = 20; // no of attempts student can get if test fails
	
	public static final String linkWrongMsg = "URL is wrong. Please check the URL and try again";
	
	public static final String testCompletedMsg = "You have already completed the test successfully. Result will be generated soon";
	
	public static final String expiredLinkMsg = "Your Test link has expired. Please send a mail to contact@itolympiad.org";
	
	public static final String exceededTestAttempts = "The URL you are using is expired due to multiple attempts. The system will not allow you to appear for the test.";
	
	public static final String beforeTestStartLinkMsg = "Please check the schedule for the test and appear accordingly. In case of difficulty please contact us at contact@itolympiad.org";
	
	public static final String refreshPageErrMsg = "During the test, you are not allowed to refresh the Test Window.<br/> The system will not allow you to appear for the test. <br/>";
	
	public static final int RoundThree = 3;
	
	public static final int LastRound = 2;
	
	public static final int resumeTimeInCaseOfError = 1800;
	
	public static final int defaultNoOfQuestionsForPracticeTest = 6; // no of questions students will get during practice test
	
	public static final int defaultTimeLimitForPracticeTest = 10; // default set time for practice test, In case of error with time it will automatically assign to 10
	
	public static final int defaultNoOfQuestionsForLastPracticeTest = 6; //default No Of Questions For Last Practice Test
	
	public static final float percentageCriteriaForPractiseTestCompLevel1 = 55f;

	public static final float percentageCriteriaForPractiseTestCompLevel2 = 50f;

	public static final float percentageCriteriaForPractiseTestCompLevel3 = 45f;

	public static final float percentageCriteriaForPractiseTestCompLevel4 = 0f;
	
	public static final float percentageCriteriaForPractiseTestCompLevel5 = 0f;
	
	public static final String passedFirstHalfDiffLevelMsg = "Congratulations.. You are successful, let us do it one more time.."; //अभिनंदन, पुन्हा एक प्रयन्त करू
//	You are successful, let us give it one more try..  
//	You are successful, let us try it again..	
	public static final String passedFirstHalfDiffLevelMsgArray[] = {"Congratulations. You are successful, let us do it one more time..<br>अभिनंदन, पुन्हा एक प्रयन्त करू ","You are successful, shall we do it one more time..<br>अभिनंदन आणखीन एकदा प्रयत्न करू","You are successful, shall we do it again..<br>अभिनंदन, पुन्हा एकदा प्रयत्‍न करायचा का","You are successful, let us give it one more try..<br>अभिनंदन, पुन्हा एकदा प्रयत्‍न करायचा का","You are successful, let us try it again..<br>अभिनंदन, पुन्हा एकदा प्रयत्‍न करायचा का"};
	
	public static final String completedFirstHalfSameDiffLevel = "Congratulations.. You have successfully clear this test, Now you will get the next test with different variation for the same difficulty level..."; 
	
	public static final String completedFirstHalfSameDiffLevelArray[] = {"Congratulations.. You are successful, Let's practice one more time..<BR> अभिनंदन! छान. अजून एकदा करूयात... ","Well done! One more practice test..<br> मस्त! आता आणखीन एकदा सराव हवा","Great! Let's have more practice..<br>खूपच छान! तरीही आणखी एकदा करून बघुयात","Wonderfully done. Let's try one more time.<br>सुंदर, पुन्हा एकदा सोडवून पाहुयात का?","Great job!!! Let's give a try again <br> वाह! परत एकदा सर्व करूया"}; 
	
//	public static final String completedSecondtHalfSameDiffLevel = "Congratulations.. You have successfully clear this test, Now you will get the next test with different variation for the same difficulty level..."; 
	
	public static final String passedSecondHalfDiffLevelMsg = "Congratulations.. You have successfully clear this test, Now you will get the next test on a higher difficulty level..."; 
	public static final String passedSecondHalfDiffLevelMsgArray[] = {"Congratulations.. You have successfully cleared this level, Let's go further...<br>अभिनंदन !!! सध्याची पातळी यशस्वीरीत्या पूर्ण झाली. आता पुढे जाऊ ","Well done!  Now a step further...<br> फारच छान, आता पुढच्या पातळीकडे वळूयात","Great job!  Now let's move a step ahead...<br>सुंदर. आता एक पायरी पुढे जाऊया.", "Excellent  job!  Now let's move to the next step...<br>मस्त. पुढची पायरी चढूयात का आता?", "Hurray!!! We shall go to the next stage...<br>हे!!! निघूया आता पुढच्या पायरीला"};
	
	public static final String passedFirstHalfOfFinalDiffLevelMsg = "Congratulations.. You have successfully clear this test, but to go for the next level you have to appear for the same level again..."; 
	public static final String passedFirstHalfOfFinalDiffLevelMsgArray[] = {"Congratulations.. You are successful, Let's practice one more time..<br>अभिनंदन! छान. अजून एकदा करूयात . . . ","Well done! One more practice test..<br> मस्त! आता आणखीन एकदा सराव हवा","Great! Let's have more practice..<br>खूपच छान! तरीही आणखी एकदा करून बघुयात","Wonderfully done. Let's try one more time.<br> सुंदर, पुन्हा एकदा सोडवून पाहुयात का?","Great job!!! Let's give a try again <br> वाह! परत एकदा सर्व करूया"}; 
	
	public static final String passedSecondHalfOfFinalDiffLevelMsg = "Congratulations.. You have successfully clear all difficulty level on this topic, you can go the next topic or sub-topic...";
	public static final String passedSecondHalfOfFinalDiffLevelMsgArray[] = {"Congratulations.. You have successfully cleared all stages for this topic, We will move to the next topic...<br> अभिनंदन!!! या विभागातील सर्व पायऱ्या तुम्ही पूर्ण केलाय आहेत. आता पुढच्या विभागात प्रवेश करूयात", "Well done!  Now it's time to move further...<br>अरे वाह! पुढच्या विभागाकडे जायची तयारी झाली आहे","Excellent! Shall we move further for the next topic...<br>अप्रतिम. जाऊया आता पुढच्या विभागाकडे","Excellent Job !  We shall  move further for the next topic...<br>सुंदरच की!!! आता पुढच्या विभागाकडे जायला हरकत नाही","Wonderful !  We deserve to move further for the next topic...<br> जमलंय आपल्याला... आता नव्या विभागात शिरुयात का?"};
	 
	public static final String failedFirstHalfDiffLevelMsg = "Let us improve performance for better result...<br> सुधारणेची गरज आहे";
	public static final String failedFirstHalfDiffLevelMsgArray[] = {"Let us improve performance for better result...<br> सुधारणेची गरज आहे","Shall we give it a try again to improve?..<br>तयारी कमी पडते आहे. पुन्हा प्रयत्न करूयात","Improvement needed. We will try again... <br>थोडासा आणखीन प्रयत्न हवाय. परत हाच सराव करूयात का?","Need to improve your performance. Let's try...<br>आणखीन सरावाची गरज आहे, चला थोडा जास्त प्रयत्न करू","Let's try hard and get better results!!!<br>अजून चांगले प्रयत्न केले कि नक्कीच पुढे जाता येईल, तर प्रयत्न करू"}; 

	public static final String failedSecondHalfDiffLevelMsg = "Let us improve performance for better result...<br> सुधारणेची गरज आहे"; 
	public static final String failedSecondHalfDiffLevelMsgArray[] = {"Let us improve performance for better result...<br> सुधारणेची गरज आहे","Shall we give it a try again to improve?..<br>तयारी कमी पडते आहे. पुन्हा प्रयत्न करूयात","Improvement needed. We will try again... <br>थोडासा आणखीन प्रयत्न हवाय. परत हाच सराव करूयात का?","Need to improve your performance. Let's try...<br>आणखीन सरावाची गरज आहे, चला थोडा जास्त प्रयत्न करू","Let's try hard and get better results!!!<br>अजून चांगले प्रयत्न केले कि नक्कीच पुढे जाता येईल, तर प्रयत्न करू"}; 

	
	public static final String prerequisiteMsg = "It is observed that you need to work on the following topic/s <br> पुढे जाण्यासाठी तुम्हाला खालील विभागांची जास्त तयारी करावी लागेल : <span class='red-color'>";
//	public static final String prerequisiteMsg = "Based on your performance on the current topic, you need to go through the following topic: <span class='red-color'>";

	public static final long otherTestTypeId = 5; // this testType id is for other test which are used to check the modified questions during Tests

	public static final long practiceTestTypeId = 1;   // this testType id is for practice test 
	
	public static final int noOfPrevTestForGeneratingTab = 3;
	
	public static final int taggingPercentageCriteria = 40;
	
//	public static final String realPathtoUploads = "D:/VirtualMathLab_2020/VirtualMathsLab/src/main/java/in/ac/coep/maths/";
	
//	public static final String realPathtoUploads = "D:\VirtualMathLab_2020\VirtualMathsLab\src\main\java\in\ac\coep\maths";

//	public static final String ganitSection = "Basic Mathematics";
//	
//	public static final int ganitSectionID = 1;
//	
//	public static final boolean ganitResumeFlag = true;
	
	public interface RoleConstants {
		String ITOSTUD = "STUDENT";
		String ITOADMIN = "ADMIN";
		int ITOSTUDID = 3;
	}
	
	
	public interface sysConfig {

		@SuppressWarnings("serial")
		Map<String, String> sysConfigMap = new HashMap<String, String>() {
			{
				put("MAX_USERS_BATCH_SIZE", "500");
			}
		};
	}
	
	public interface sections {
		
		@SuppressWarnings("serial")
		Map<Integer, String> sectionsMap = new HashMap<Integer, String>(){
			  {
				  put(1,"section");
			  }
		  };
	
	}
	
	
	public interface topics {
		
		@SuppressWarnings("serial")
		Map<Long, String> topicsMap = new HashMap<Long, String>(){
			  {
				  put(1l,"topic");
			  }
		  };
	
	}
	
	
	public interface Departments {
		@SuppressWarnings("serial")
		Map<Integer, String> DepartmentList = new HashMap<Integer, String>() {
			{
				put(1, "7th - 8th GROUP A");
				put(2, "9th - 10th GROUP B");
			}
		};
	}

	
	public interface QuestionGroupTF {
		@SuppressWarnings("serial")
		Map<Long, String> QuestionGroupLvl1 = new HashMap<Long, String>() {
			{
				// put(5, "Electical Engineering");
			}
		};
	}
	
	public interface QuestionGroupMca {
		@SuppressWarnings("serial")
		Map<Long, String> QuestionGroupLvl3 = new HashMap<Long, String>() {
			{
				
				// put(4, "Instrumentation and Control");
				// put(5, "Electical Engineering");
			}
		};
	}
	
	public interface testAttempts {
		@SuppressWarnings("serial")
		Map<Long, Long> CountTestAttempts = new HashMap<Long, Long>() {
			{
//				put(1l, "0");
				
			}
		};
	}
	
}
