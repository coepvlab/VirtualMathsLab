//package in.ac.coep.serviceImpl;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.StringReader;
//import java.text.DateFormat;
//import java.text.DecimalFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.log4j.Logger;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.tool.xml.XMLWorker;
//import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
//import com.itextpdf.tool.xml.html.Tags;
//import com.itextpdf.tool.xml.parser.XMLParser;
//import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
//import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
//import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
//import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
//import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
//
//import in.ac.coep.constants.Constants;
//import in.ac.coep.dao.TestDao;
//import in.ac.coep.dao.TestInstanceStateDao;
//import in.ac.coep.dao.TestStatisticDao;
//import in.ac.coep.entity.Test;
//import in.ac.coep.entity.TestInstanceState;
//import in.ac.coep.entity.TestInstanceStatistics;
//import in.ac.coep.service.CreatePdfforSingleUserService;
//import in.ac.coep.service.MediaService;
//@Service
//public class CreatePdfReportforSingleUserServiceImpl implements CreatePdfforSingleUserService {
//	
//	
//	private static final Logger LOGGER = Logger.getLogger(CreatePdfReportforSingleUserServiceImpl.class);
//
//	@Autowired
//	private TestStatisticDao testStatisticDao;
//
//	@Autowired
//	private TestInstanceStateDao testInstanceStateDao;
//
////	@Autowired
////	private AWSServiceToStorePdfReport aWSServiceToStorePdfReport;
//
//	@Autowired 
//	MediaService mediaService;
//	
//	@Autowired
//	TestDao testDao;
//	
//	private TestInstanceStatistics testInstanceStatistics = null;
//	
//	
//
//	@Override
//	public void getAllDataForPDFReportforSingleUser(long userId, int testLevel) throws Exception {
//		// TODO Auto-generated method stub
//		
//
//		// TODO Auto-generated method stub
//		DecimalFormat df2 = new DecimalFormat("00.00");
//
//		String DEST = null;
//		String psyDataStr = "";
//		String userName = null;
//		String testStartDate = null;
//		String testName = null;
//		String testStartTime = null;
//		String tempTime = null;
//		String imgUrl_Rotary = Constants.imageBaseURL + "Rotary.png";
////		String imgUrl_Coep = Constants.imageBaseURL + "COEP.jpg";
////		String imgUrl_Rcpp = Constants.imageBaseURL + "RCPP.png";
//		String imgUrl_ITOTitle = Constants.imageBaseURL + "VirtualMathsLabLogo.png";
////		String imgUrl_Afour = Constants.imageBaseURL + "Afour.png";
////		String imgUrl_Vlab = Constants.imageBaseURL + "Vlab.png";
////		String imgUrl_sign = Constants.imageBaseURL + "deepak_kelkar.png";
//		String imgUrl_ganitA = Constants.imageBaseURL + "ganit_adhyapak.jpg";
//		
////		String PIE_IMG_DEST = null;
//		
////		String imgUrl_PieChart = Constants.imageBaseURL + "pieChart/";
//		
////		List<TestInstanceState> recordWithTestCompleteNoPDF = testInstanceStateDao.getAllStatesByStatusAndPdfGenerate();
////		List<TestInstanceState> testInstanceState1  =  testInstanceStateDao.getTestInstanceStateByUserId(userId);
//		TestInstanceState testInstanceState  =  testInstanceStateDao.getTestInstanceStateByUserIdandTestLevel(userId, testLevel);
//		String HTML = null;
//	//	for (TestInstanceState testInstanceState : testInstanceState1) {
//
//			if(testInstanceState.getStatus().equalsIgnoreCase("C") && testInstanceState.isPdfGenerate() == false){
//			
//			long totalQues = 0;
//			long totalPassedQues = 0;
//			long totalPercentage = 0;
//			String finalgrade = null;
//			ArrayList<String> strength = new ArrayList<String>();
//			ArrayList<String> weakness = new ArrayList<String>();
//
//			userName = testInstanceState.getUser().getFirstName() + " " + testInstanceState.getUser().getLastName();
//			
//			int roundNo = testInstanceState.getTestLevel();
//			
//			testName = testInstanceState.getTest().getName() /*+ " (Mock Test "+roundNo +")"*/;
//			
//			Date date1 = new Date();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy") ;
//            String curDate =dateFormat.format(date1);
//
//			 HTML = null;
////			 PIE_IMG_DEST = Constants.PIE_IMG_DEST;
//
//			 File fileLocal = new File(Constants.srcURLForSinglePdfResult+curDate);
//	            
//             if(!fileLocal.exists()){
//            	 fileLocal.mkdir();
//             }		 
//			 
////			 DEST = fileLocal+ Constants.path +testInstanceState.getUser().getPhoneNumber() + "_" + testName + ".pdf";
//
//			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//			testStartDate = formatter.format(testInstanceState.getTickTime());
//
//			DateFormat formatter1 = new SimpleDateFormat("HH:mm:ss");
//			tempTime = formatter1.format(testInstanceState.getTickTime());
//
//			Date date = null;
//			try {
//				date = formatter1.parse(tempTime);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			DateFormat pstFormat = new SimpleDateFormat("HH:mm:ss");
//
//			DateFormat f1 = new SimpleDateFormat("HH:mm:ss"); //HH for hour of the day (0 - 23)
//			Date d = f1.parse(pstFormat.format(date));
//			DateFormat f2 = new SimpleDateFormat("h:mm a");
//			
//			testStartTime = f2.format(d).toLowerCase();;
//			
//			HTML += "<html>" + "<head>" + "    <title>ScoreCard</title>" + "</head>"
//					+ "<body style=\"height: 90%; width: 90%;\">" + "<div align=\"center\">"
//					+ "  <div style=\"width:600px; background-color:#f5f7fa; padding-bottom:2em;\">" + "    <div >"
//					+ "      <table style=\"width: 100% !important;\">" + "        <tr>"
//					+ "       <td  align=\"center\" style=\"width: 25% !important;border: 0px;\">"
//					+ "            <img alt=\"Rotary Club\" src='" + imgUrl_Rotary
//					+ "' style=\" width: 75% !important;\" />" + "          </td>"
//					+ "          <td  align=\"center\" style=\"width: 25% !important;border: 0px;\">"
//					+ "            <br></br>" 
//					+ "            <img src='" + imgUrl_ITOTitle
//					+ "' style=\"width: 75% !important; \"/>" + "          </td>" 
//					+ "       <td align=\"center\" style=\"width: 25% !important; border: 0px;\">"
//					+ "            <img alt=\"Ganit\" src='" + imgUrl_ganitA + "' style=\" width: 75% !important;\"/>"
//					+ "          </td>" 
//					+ "        </tr>" + "      </table>"
//					+ "    </div>" + "    <hr/>" + "    <div>      "
//					+ "      <table style =\"width: 100% !important; border-spacing: 4px;\">"
//					+ "        <tr colspan=\"2\"><td style=\" font-size: 14px; margin-top: 0px; margin-bottom: 0px; letter-spacing: 1.25px; display: inline; "
//					+ "        background-color: #346ba2 !important; color: #ffffff; font-family:lato !important; font-weight: bold; padding:5% 5% 2% 5%; float: left;\">MOCK TEST DETAILS</td>"
////					+ "<td style=\"width: 35% !important;  background-color: white; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\"><span style=\"font-size: 14px; font-weight: 400; color: #1a252f;\">Registration ID : "+testInstanceState.getUser().getGoRegistrationId()+"</span></td></tr>"
//					+ "        <tr>"
//					+ "          <td style=\"width: 35% !important;  background-color: white; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
//					+ "            <span style=\"font-size: 14px; font-weight: 400; color: #1a252f;\">Name:</span>"
//					+ "            <span style=\"font-size: 13px; font-weight: 200; color: #1a252f;\">" + userName
//					+ "</span>" + "          </td>"
//					+ "          <td style=\"width: 35% !important; background-color: white; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
//					+ "            <span style=\"font-size: 14px; font-weight: 400; color: #1a252f;\">Test Date:</span>"
//					+ "            <span style=\"font-size: 13px; font-weight: 200; color: #1a252f;\">" + testStartDate
//					+ "</span>" + "          </td>" + "        </tr>" + "        <tr>"
//					+ "          <td style=\"width: 35% !important;  background-color: white; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
//					+ "            <span  style=\"font-size: 14px; font-weight: 400; color: #1a252f;\">Test Name:</span>"
//					+ "            <span style=\"font-size: 13px; font-weight: 200; color: #1a252f;\">" + testName
//					+ "</span>" + "          </td>"
//					+ "          <td style=\"width: 35% !important;  background-color: white; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
//					+ "            <span  style=\"font-size: 14px; font-weight: 400; color: #1a252f;\">Test Start Time:</span>"
//					+ "            <span style=\"font-size: 13px; font-weight: 200; color: #1a252f;\">" + testStartTime
//					+ "</span>" + "          </td>" + "        </tr>" + "      </table>" + "    </div>";
//
//			
//			Test test = testDao.getTestByTestId(testInstanceState.getTest().getTestId());
//			String sections[] = test.getSelectedSections().split(",");
//			
//			Integer[] sectionArr = new Integer[sections.length];
//			for(int i = 0;i < sectionArr.length;i++)
//			{
//			  
//				sectionArr[i] = Integer.parseInt(sections[i]);
//			}
//			
//			List<TestInstanceStatistics> statisticsCountForQues = testStatisticDao
//					.fetchStatisticsByUserId(testInstanceState.getUser().getUserId(), sectionArr);
//			System.out.println("uid :-"+testInstanceState.getUser().getUserId());
//			
//			long currentTestLevel = 0;
//			for (Iterator<TestInstanceStatistics> iterator1 = statisticsCountForQues.iterator(); iterator1.hasNext();) {
//				TestInstanceStatistics testInstanceStatistics1 = (TestInstanceStatistics) iterator1.next();
//				currentTestLevel = testInstanceStatistics1.getTestLevel();
//				try {
//					if(testLevel == currentTestLevel){
//					totalQues = totalQues + testInstanceStatistics1.getTotalNoOfQuestions();
//					totalPassedQues = totalPassedQues + testInstanceStatistics1.getPassedQuestions();
//					}
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//			if(testLevel == currentTestLevel){
//			totalPercentage = (totalPassedQues * 100) / totalQues;
//			if (totalPercentage >= 80) {
//				finalgrade = "Grade A";
//			} else if (totalPercentage >= 65 && totalPercentage < 80) {
//				finalgrade = "Grade B";
//			} else if (totalPercentage >= 50 && totalPercentage < 65) {
//				finalgrade = "Grade C";
//			}else {
//				finalgrade = "Needs Improvement and study";
//			}
//
//			testInstanceState.setGrade(finalgrade);
//			testInstanceStateDao.update(testInstanceState);
//			}
////			 PieChart chart = new PieChartBuilder().width(1000).height(300).theme(ChartTheme.Matlab).build();
////				
////			    chart.getStyler().setLegendVisible(false);
////			    chart.getStyler().setAnnotationType(AnnotationType.LabelAndPercentage);
////			    chart.getStyler().setAnnotationDistance(1.06);
//////			    chart.getStyler().setPlotContentSize(.6);
////			    chart.getStyler().setStartAngleInDegrees(90);
////			    chart.getStyler().setDonutThickness(0.5);
////			    chart.getStyler().setPlotBorderVisible(false);
////			    
////			    Color[] sliceColors = new Color[] { new Color(149,206,255), new Color(255,188,117), new Color(169,255,150), new Color(67,67,72), new Color(159,164,255) };
////			    chart.getStyler().setLegendVisible(false);
////			    chart.getStyler().setAnnotationType(AnnotationType.Label);
//////			    chart.getStyler().setAnnotationDistance(.82);
////			    chart.getStyler().setPlotContentSize(.9);
////			    chart.getStyler().setDefaultSeriesRenderStyle(PieSeriesRenderStyle.Donut);
//			    
////			List<TestInstanceStatistics> statisticsPDFDataToCreatePieChart = testStatisticDao
////					.fetchStatisticsByUserId(testInstanceState.getUser().getUserId(),sectionArr);
////			for (TestInstanceStatistics testInstanceStatistics : statisticsPDFDataToCreatePieChart) {
////				float sectionpercentage = 0.0f;
////				float roundpercentage = 0.0f;
////				sectionpercentage = testInstanceStatistics.getPercentage();
////				roundpercentage = Float.parseFloat((df2.format(sectionpercentage)));
////				
////				if(testInstanceStatistics.getPercentage() == 0){
////					int temp = 1; 
////					chart.addSeries(Constants.sections.sectionsMap.get(testInstanceStatistics.getSection().getSectionId())+":"+roundpercentage+"%", temp);
////				}else{
////					chart.addSeries(Constants.sections.sectionsMap.get(testInstanceStatistics.getSection().getSectionId())+":"+roundpercentage+"%", testInstanceStatistics.getPercentage());
////				}
////			}
////			chart.getStyler().setDrawAllAnnotations(true);
////			chart.getStyler().setDefaultSeriesRenderStyle(null);//
//			
////			for (TestInstanceStatistics testInstanceStatistics : statisticsCountForQues) {
////				 BitmapEncoder.saveJPGWithQuality(chart, PIE_IMG_DEST +testInstanceStatistics.getUser().getUserId()+"_Round_"+testInstanceState.getTestLevel() +".jpg", 1.0f);
////				 BufferedImage image = ImageIO.read(new File(PIE_IMG_DEST +testInstanceStatistics.getUser().getUserId()+"_Round_"+testInstanceState.getTestLevel() +".jpg"));
////
////				    Graphics g = image.getGraphics();
////				    g.setFont(g.getFont().deriveFont(20f));
////				    g.setColor(Color.black);
////				    g.drawString(String.valueOf(totalPercentage)+"%", 485, 160);
////				    g.dispose();
//
////				    ImageIO.write(image, "jpg", new File(PIE_IMG_DEST +testInstanceStatistics.getUser().getUserId()+"_Round_"+testInstanceState.getTestLevel() +".jpg"));
////				}
//		
//			
//			
////			new SwingWrapper(chart).displayChart();
//			
//			if(totalPercentage != 0){
//				HTML += "	<div >" + "      <table style=\"width: 100% !important; border-spacing: 0px;\">"
//						+ "        <tr>"
//						+ "          <td style=\"width: 30% !important; border: 0px; background-color: #0080ff !important; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
//						+ "            <label style=\"color: #ffffff !important; font-size: 14px; font-weight: 400; color: #1a252f;\">Score: "
//						+ totalPassedQues + " Out of " + totalQues + "</label>" + "          </td>"
//						+ "          <td style=\"width: 25% !important; border: 0px; background-color: #0080ff !important; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
//						+ "            <label style=\"color: #ffffff !important;font-size: 14px; font-weight: 400; color: #1a252f;\">Percentage:"
//						+ totalPercentage + "%</label>" + "          </td>"
//						+ "          <td style=\"width: 14% !important; border: 0px; background-color: #0080ff !important; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
//						+ "            <label style=\"color: #ffffff !important;font-size: 14px; font-weight: 400; color:#1a252f;\"> "
//						+ finalgrade + "</label>" + "          </td>" + "        </tr>" + "      </table>"
////						+ "      <div id=\"pieChart\">" + "      </div>" + "    </div>         <div id=\"piechart\"> <img alt=\"Rotary Club\" src='"+PIE_IMG_DEST+testInstanceState.getUser().getUserId()+"_Round_"+Constants.sysConfig.sysConfigMap.get("CURRENT_ROUND")+".jpg' style=\"width: 585px; height: 200px; !important;\"/>"
//						+ "      </div><div><table style=\"width: 100% !important; border-spacing: 4px;\">"
//						+ "        <tr style=\"width:180px;\"><td style=\" font-size: 14px; margin-top: 0px; margin-bottom: 0px; letter-spacing: 1.25px; display: inline; "
//						+ "        background-color: #346ba2 !important; color: #ffffff; font-family:lato !important; font-weight: bold; padding:5% 5% 2% 5%; float: left;\">SECTION PERFORMANCE</td></tr>"
//						+ "        <tr></tr>";
//				}else{
//					HTML += "	<div >" + "      <table style=\"width: 100% !important; border-spacing: 0px;\">"
//							+ "        <tr>"
//							+ "          <td style=\"width: 30% !important; border: 0px; background-color: #0080ff !important; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
//							+ "            <label style=\"color: #ffffff !important; font-size: 14px; font-weight: 400; color: #1a252f;\">Score: "
//							+ totalPassedQues + " Out of " + totalQues + "</label>" + "          </td>"
//							+ "          <td style=\"width: 25% !important; border: 0px; background-color: #0080ff !important; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
//							+ "            <label style=\"color: #ffffff !important;font-size: 14px; font-weight: 400; color: #1a252f;\">Percentage:"
//							+ totalPercentage + "%</label>" + "          </td>"
//							+ "          <td style=\"width: 14% !important; border: 0px; background-color: #0080ff !important; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
//							+ "            <label style=\"color: #ffffff !important;font-size: 14px; font-weight: 400; color:#1a252f;\"> "
//							+ finalgrade + "</label>" + "          </td>" + "        </tr>" + "      </table>"
////							+ "      <div id=\"pieChart\">" + "      </div>"
//							+ "     </div><div> <table style=\"width: 100% !important; border-spacing: 4px;\">"
//							+ "        <tr style=\"width:180px;\"><td style=\" font-size: 14px; margin-top: 0px; margin-bottom: 0px; letter-spacing: 1.25px; display: inline; "
//							+ "        background-color: #346ba2 !important; color: #ffffff; font-family:lato !important; font-weight: bold; padding:5% 5% 2% 5%; float: left;\">SECTION PERFORMANCE</td></tr>"
//							+ "        <tr></tr>";
//
//				}
//				
//				
//				
//				
//				
//				List<TestInstanceStatistics> statisticsPDFData = testStatisticDao
//						.fetchStatisticsByUserId(testInstanceState.getUser().getUserId(),sectionArr);
//
//				for (Iterator<TestInstanceStatistics> iterator = statisticsPDFData.iterator(); iterator.hasNext();) {
//
//					testInstanceStatistics = (TestInstanceStatistics) iterator.next();
////					try {
////						if (!testInstanceStatistics.getSection().getName().equalsIgnoreCase(Constants.psySecname)) {
////							float sectionpercentage = 0.0f;
////							float roundpercentage = 0.0f;
////
////							sectionpercentage = testInstanceStatistics.getPercentage();
////
////							roundpercentage = Float.parseFloat((df2.format(sectionpercentage)));
////
////							System.out.printf("%1$.2f", roundpercentage);
////
////							if(testLevel == testInstanceStatistics.getTestLevel()){
////							HTML += "<tr> <td class=\"custom-border\" style=\"width: 35% !important; text-align: right !important;  background-color: white; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
////									+ "            <label style=\"height:25px; font-size: 12px; font-weight: 200; color: #1a252f;\">"
////									+ testInstanceStatistics.getSection().getName()
////									+ "</label><br /><label style=\"height:25px ;font-size: 11px; font-weight: 100; color: #1a252f;\">Score:"
////									+ testInstanceStatistics.getPassedQuestions() + " / "
////									+ testInstanceStatistics.getTotalNoOfQuestions() + "</label>" + "          </td>"
////									+ "<td style=\"height:25px; width: 35% !important; border: 0px; border: 1px solid #a7d6eb;\">";
////									
////									if(totalPercentage != 0){
////										if(roundpercentage !=0){
////										HTML +=  "<div style=\"height:25px; width:"+ roundpercentage +"%; background-color: #0080FF;\"> </div>" + "          </td>" + "        </tr>";
////										}else{
////											HTML += " <div style=\"font-size: 13px; font-weight: 200; color: #1a252f;\">"
////													+ roundpercentage + "%            </div>" + "          </td>" + "        </tr>";
////										}
////									}else{
////										HTML += " <label style=\"font-size: 13px; font-weight: 200; color: #1a252f;\">"
////												+ roundpercentage + "%            </label>" + "          </td>" + "        </tr>";
////									}
////						}
////						}
////					if (testInstanceStatistics.getPercentage() > 65
////							&& !testInstanceStatistics.getSection().getName().equalsIgnoreCase(Constants.psySecname)) {
////						strength.add(testInstanceStatistics.getSection().getName());
////					} else if (!testInstanceStatistics.getSection().getName().equalsIgnoreCase(Constants.psySecname)) {
////						weakness.add(testInstanceStatistics.getSection().getName());
////					}
////
////				} catch (Exception e) {
////					e.printStackTrace();
////				}
//
////				if (testInstanceStatistics.getSection().getName().equalsIgnoreCase(Constants.psySecname)) {
////
////					psyDataStr = "";
////
////					String str[] = findSequence(testInstanceStatistics.getGrade());
////
////					psyDataStr +="<label style=\"text-align: left !important; float: left; padding:5% 5% 2% 5%; font-weight: 400; color: #1a252f; font-size: 11px;\"><b><u><i>Analysis of Psychometric Test</i></u></b></label><br/><br/>"
////								+"<label style=\"text-align: left !important; float: left; padding:5% 5% 2% 5%; font-weight: 400; color: #1a252f; font-size: 11px;\">1. As per the response for the Career Anchor you possess "
////							+ "<b>";
////					if (str[3] != null && str[2] != null) {
////						psyDataStr += str[3] + " and " + str[2];
////					} else {
////						psyDataStr += " (NA)  ";
////					}
////					psyDataStr += "</b> competence.</label>" + "	  <br />";
////
////					String strarr[] = testInstanceStatistics.getGrade().split("#");
////
////					String arr[] = strarr[9].split("-");
////					if (Integer.parseInt(arr[1]) >= 23) {
////						psyDataStr += "      <label style=\"text-align: left !important; float: left; padding:5% 5% 2% 5%; font-weight: 400; color: #1a252f; font-size: 11px;\">2. You have <b>External</b> Locus of Control</label>"
////								+ "	  <br />";
////					} else if (Integer.parseInt(arr[1]) < 23 && Integer.parseInt(arr[1]) != 0) {
////						psyDataStr += "      <label style=\"text-align: left !important; float: left; padding:5% 5% 2% 5%; font-weight: 400; color: #1a252f; font-size: 11px;\">2. You have <b>Internal</b> Locus of Control</label>"
////								+ "	  <br />";
////					} else if (Integer.parseInt(arr[1]) == 0) {
////						psyDataStr += "      <label style=\"text-align: left !important; float: left; padding:5% 5% 2% 5%; font-weight: 400; color: #1a252f; font-size: 11px;\">2. You have <b>----</b> Locus of Control  <b>(NA)</b> </label>"
////								+ "	  <br />";
////					}
////
////					String bfModelStr[] = findBigFiveFactor(testInstanceStatistics.getGrade());
////					psyDataStr += "<label style=\"text-align: left !important; float: left; padding:5% 5% 2% 5%; font-weight: 400; color: #1a252f; font-size: 11px;\">3. As per your response to big Five Model you possess ";
////					for (int p = 0; p < bfModelStr.length; p++) {
////						psyDataStr += "<b>" + bfModelStr[p];
////						if (p != bfModelStr.length - 1) {
////							psyDataStr += "</b>,";
////						} else if (p == bfModelStr.length - 2) {
////							psyDataStr += "</b> ";
////						} else {
////							psyDataStr += "</b> ";
////						}
////					}
////					psyDataStr += "qualities.</label>";
////					
////				}else{
////					psyDataStr += " ";
////				}
//			}
//
//			HTML += "      </table>" + "    </div>" ;
////					+ "	<div>      "
////					+ "      <table style=\"width: 100% !important; border-spacing: 4px;\">";
////					+ "        <tr><td colspan=\"2\" style=\" font-size: 14px; margin-top: 0px; margin-bottom: 0px; letter-spacing: 1.25px; display: inline; "
////					+ "        background-color: #346ba2 !important; color: #ffffff; font-family:lato !important; font-weight: bold; padding:5% 5% 2% 5%; float: left;\">STRENGTH AND AREAS FOR IMPROVEMENT</td></tr>"
////					+ "        <tr>"
////					+ "          <td style=\"width: 35% !important;  background-color: white; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
////					+ "            <label style=\"font-size: 14px; font-weight: 400; color: #1a252f;\">Strength(s)</label>"
////					+ "          </td>"
////					+ "          <td style=\"width: 35% !important;  background-color: white; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
////					+ "            <label style=\"font-size: 13px; font-weight: 200; color: #1a252f;\">";
////			if (strength.size() != 0) {
////				for (String temp : strength) {
////					HTML += temp + "<br />";
////				}
////			} else {
////				HTML += "(NA)" + "<br />";
////			}
//			
////			HTML += "</label>" + "          </td>" + "        </tr>" 
////				HTML	+= "        <tr>"
////					+ "          <td style=\"width: 35% !important;  background-color: white; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
////					+ "            <label style=\"font-size: 14px; font-weight: 400; color: #1a252f;\">Area(s) for Improvement</label>"
////					+ "          </td>"
////					+ "          <td style=\"width: 35% !important; background-color: white; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
////					+ "            <label style=\"font-size: 13px; font-weight: 200; color: #1a252f;\">";
////			if (weakness.size() != 0) {
////				for (String temp : weakness) {
////					HTML += temp + "<br />";
////				}
////			} else {
////				HTML += "(NA)" + "<br />";
////			}
//			
////			HTML += "</label>" + "          </td>" + "        </tr>" + "      </table>" + "    </div>" + "	"
////			HTML += "      <label style=\"text-align: left !important; float: left; padding:5% 5% 2% 5%; font-weight: 400; color: #1a252f; font-size: 11px;\"><b><u><i></i></u></b></label>";
////					+ "	  ";
//			
////			HTML += "</label>" + "          </td>" + "        </tr>" + "        ";
////			if (weakness.size() != 0) {
////			  HTML += "    <tr>      <td style=\"width: 35% !important;  background-color: white; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
////					+ "            <label style=\"font-size: 14px; font-weight: 400; color: #1a252f;\">Area(s) for Improvement</label>"
////					+ "          </td>"
////					+ "          <td style=\"width: 35% !important; background-color: white; padding:5% 5% 2% 5%; border: 1px solid #a7d6eb;\">"
////					+ "            <label style=\"font-size: 13px; font-weight: 200; color: #1a252f;\">";
////			
////				for (String temp : weakness) {
////					HTML += temp + "<br />";
////				}
////				HTML += "</label>" + "          </td>" + "        </tr>" ;
////			}
////			HTML +=  "      </table>" + "    </div>" + "	<div>"
//////				+ "      <label style=\"text-align: left !important; float: left; padding:5% 5% 2% 5%; font-weight: 400; color: #1a252f; font-size: 11px;\"><b><u><i></i></u></b></label>"
////				+ "	   </div>";
//
//			
//			HTML += psyDataStr;
//
////			HTML += "<div>"
////					+ "	<label style=\"text-align: left !important; float: left; padding:1% 5% 1% 5%; font-weight: 400; color: #1a252f; font-size: 10px;\"><span style=\" color: red;\">&#42;</span>Note : 1) Strength - Section score greater than 65% <br/><p>             2) Areas for Improvement - Section score less than 65%</p> </label>"
////					+ "      <br />"
////					+ "      <label style=\"text-align: left !important; float: left; padding:5% 5% 2% 5%; font-weight: 400; color: #1a252f; font-size: 10px;\">            3) (NA) - Not Attempted</label>"
////					+ "</div>" 
//			HTML += "<div>" + "<table style=\"width:150px;\" align=\"right\"><tr><td align=\"center\">"
////					+ "<img alt=\"Sign\" src='" + imgUrl_sign + "' style=\" width:45%; height:20%; !important;\" />"
////					+ "<br/><span style=\"font-size: 12px;\"><b>Dr. Deepak Kelkar <br/>"
//					+ "<br/><br/><span>Ganit Olympiad 2018</span></td></tr></table>"
////					+ "<label style=\"text-align: left !important; float: left; padding:5% 5% 2% 5%; font-weight: 400; color: #1a252f; font-size: 12px;\"><b>Organized by  : </b></label><br/><hr/> "
////					+ "<table align=\"center\" border = \"1px\" style=\"width: 100% !important;\">" + "<tr>"
////					+ "       <td  align=\"center\" style=\"width: 25% !important;border: 0px;\">"
////					+ "            <img alt=\"Rotary Club\" src='" + imgUrl_Rotary
////					+ "' style=\" width: 75% !important;\" />" + "          </td>"
//////					+ "       <td  align=\"center\" style=\"width: 18% !important; border: 0px;\">"
//////					+ "            <img alt=\"COEP\" src='" + imgUrl_Coep + "' style=\" width: 85% !important;\" />"
//////					+ "          </td>" 
////					+ "       <td align=\"center\" style=\"width: 25% !important; border: 0px;\">"
////					+ "            <img alt=\"Ganit\" src='" + imgUrl_ganitA + "' style=\" width: 75% !important;\"/>"
////					+ "          </td>" 
//////					+ "       <td  align=\"center\" style=\"width: 18% !important; border: 0px;\">"
//////					+ "            <img alt=\"VLAB\" src='" + imgUrl_Vlab + "' style=\" width: 75% !important;\" />"
//////					+ "          </td>" 
////					+ "</tr>" + "</table>" 
//					+ "</div>" + "</div>" + "</div>" + "</body>" + "</html>";
//
//			Document document = new Document(PageSize.LETTER,100f,25f,140f,0f);
////			Document document = new Document(PageSize.A5.rectangle(top, bottom));
//			document.setMargins(70f, 30f, 40f, 10f);
//			
//			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
//			
//			
//			document.open();
//			CSSResolver cssResolver = new StyleAttrCSSResolver();
//
//			HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
//			htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
//
//			PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
//			HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
//			CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
//
//			XMLWorker worker = new XMLWorker(css, true);
//			XMLParser p = new XMLParser(worker);
//			p.parse(new StringReader(HTML));
//
//			document.close();
//
//			File file = new File(DEST);
//			
//			 FileInputStream input = new FileInputStream(file);
//			    MultipartFile multipartFile = new MockMultipartFile("file",
//			            file.getName(), "application/pdf", IOUtils.toByteArray(input));
//			
//			
//			
//			/*DiskFileItem fileItem = new DiskFileItem("file", "application/pdf", false, file.getName(), (int) file.length() , file.getParentFile());
//		    fileItem.getOutputStream();
//			
//			
//			MultipartFile multipartFile = new CommonsMultipartFile(fileItem);*/
//			
////			String url = aWSServiceToStorePdfReport.createAwsUrlForReport(file);
//			
//			JSONObject data = new JSONObject();
//
////			data = mediaService.uploadMediaFile(multipartFile);
////			System.out.println("pdf url" + data.toString());
//			
//			testInstanceState.setScoreCardLink(data.getString("URL"));
//			
//			
//			testInstanceState.setPdfGenerate(true);
//			testInstanceStateDao.update(testInstanceState);
//			LOGGER.info("PDF records created successfully");
//		}	
//	
//	//	}	
//	}
//	
//	
//	public static String[] findSequence(String grade) {
//
//		// String str =
//		// "A-1# C-3# M-3# T-2# SE-T-9# IPEE-F-11# CU-T-10# TA-F-7# TMA-F-6# LOC-27";
//
//		String strarr[] = grade.split("#");
//
//		String string[] = new String[4];
//		int[] auto = new int[4];
//		String temp[] = new String[4];
//
//		for (int i = 0; i < 4; i++) {
//			String[] str1 = strarr[i].split("-");
//
//			auto[i] = Integer.parseInt(str1[1]);
//			temp[i] = str1[1];
//		}
//
//		Arrays.sort(auto);
//
//		// Arrays.asList(auto).remove();
//
//		for (int i = 0; i < 4; i++) {
//
//			int index = Arrays.asList(temp).indexOf(auto[i] + "");
//
//			// int index =ArrayUtils.indexOf(arr, 2);
//
//			if (index == 0 && Integer.parseInt(temp[index]) != 0) {
//				string[i] = "Autonomy &amp; Independance";
//			} else if (index == 1 && Integer.parseInt(temp[index]) != 0) {
//				string[i] = "Creativity &amp; entrepreneurship";
//			} else if (index == 2 && Integer.parseInt(temp[index]) != 0) {
//				string[i] = "Managerial";
//			} else if (index == 3 && Integer.parseInt(temp[index]) != 0) {
//				string[i] = "Technical &amp; Analytical";
//			}
//
//			temp[index] = "4";
//		}
//
//		// for (int i = string.length - 1; i >= 0; i--) {
//		// if (string[i] != null)
//		// System.out.println(string[i]);
//		// }
//		return string;
//
//	}
//	
//	
//	private static String[] findBigFiveFactor(String grade) {
//
//		String strarr[] = grade.split("#");
//
//		String bfModel[] = new String[5];
//		for (int j = 4; j < 9; j++) {
//
//			String tempArr[] = strarr[j].split("-");
//
//			if (tempArr[0].equals("IPEE")) {
//				if (tempArr[1].equals("T") && Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[0] = "Introverted and Passive";
//				} else if (Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[0] = "Extraverted and high energy";
//				} else {
//					bfModel[0] = " (NA) ";
//				}
//			} else if (tempArr[0].equals("TA")) {
//				if (tempArr[1].equals("T") && Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[1] = "Traditionalist(Closed)";
//				} else if (Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[1] = "Adventurer(Open)";
//				} else {
//					bfModel[1] = " (NA) ";
//				}
//			} else if (tempArr[0].equals("TMA")) {
//				if (tempArr[1].equals("T") && Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[2] = "Tough-Minded(Self-Centered)";
//				} else if (Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[2] = "Aggreable(Other-Centered)";
//				} else {
//					bfModel[2] = " (NA) ";
//				}
//			} else if (tempArr[0].equals("CU")) {
//				if (tempArr[1].equals("T") && Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[3] = "Conscientious";
//				} else if (Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[3] = "Undirected";
//				} else {
//					bfModel[3] = " (NA) ";
//				}
//			} else if (tempArr[0].equals("SE")) {
//				if (tempArr[1].equals("T") && Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[4] = "Stable";
//				} else if (Integer.parseInt(tempArr[2]) != 0) {
//					bfModel[4] = "Emotional";
//				} else {
//					bfModel[4] = " (NA) ";
//				}
//			}
//
//		}
//
//		return bfModel;
//	}
//	
//	
//	
//	
//	
//	
//
//}
