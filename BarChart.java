package NewPrograms;

import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import org.jfree.chart.axis.NumberAxis;
import javax.imageio.ImageIO;

import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart {

    public static void main(String[] args) {
    	try (Scanner sc = new Scanner(System.in)) {
			try {
				System.out.println("How Many Questions do u want");
				int qn = sc.nextInt();

				// declare file name to be create
				String filename = "E:\\Nayan_Eclipse_workspace\\Graphs\\barchart.xlsx";
				// creating an instance of HSSFWorkbook class
				XSSFWorkbook workbook = new XSSFWorkbook();
				// invoking creatSheet() method and passing the name of the sheet to be created
				XSSFSheet sheet2 = workbook.createSheet("Instructions");
				XSSFSheet sheet = workbook.createSheet("Questions");
				// creating the 0th row using the createRow() method
				XSSFRow rowhead = sheet.createRow((short) 0);
				// creating cell by using the createCell() method and setting the values to the
				// cell by using the setCellValue() method
				rowhead.createCell(0).setCellValue("Sr. No");
				rowhead.createCell(1).setCellValue("Question Type");
				rowhead.createCell(2).setCellValue("Answer Type");
				rowhead.createCell(3).setCellValue("Topic Number");
				rowhead.createCell(4).setCellValue("Question (Text Only)");
				rowhead.createCell(5).setCellValue("Correct Answer 1");
				rowhead.createCell(6).setCellValue("Correct Answer 2");
				rowhead.createCell(7).setCellValue("Correct Answer 3");
				rowhead.createCell(8).setCellValue("Correct Answer 4");
				rowhead.createCell(9).setCellValue("Wrong Answer 1");
				rowhead.createCell(10).setCellValue("Wrong Answer 2");
				rowhead.createCell(11).setCellValue("Wrong Answer 3");
				// rowhead.createCell(12).setCellValue("Wrong Answer 4");
				rowhead.createCell(12).setCellValue("Time in seconds");
				rowhead.createCell(13).setCellValue("Difficulty Level");
				rowhead.createCell(14).setCellValue("Question (Image/ Audio/ Video)");
				rowhead.createCell(15).setCellValue("Contributor's Registered mailId");
				rowhead.createCell(16).setCellValue("Solution (Text Only)");
				rowhead.createCell(17).setCellValue("Solution (Image/ Audio/ Video)");
				rowhead.createCell(18).setCellValue("Variation Number");

				ArrayList<String> Q = new ArrayList<String>();
				for (int j = 1; j <= qn; j++) {
				
					Random r = new Random();
					 CategoryDataset dataset = createDataset();
					 JFreeChart chart = ChartFactory.createBarChart(
				                "Types and Number of Plants / रोपांचे प्रकार व संख्या",  // Chart title
				                "Types of plants / रोपांचे प्रकार",                  // X-axis Label
				                "Number of Plants / रोपांची संख्या ",                     // Y-axis Label
				                dataset,                     // Dataset
				                PlotOrientation.VERTICAL,    // Plot orientation
				                true,                        // Include legend
				                true,
				                false
				        );

					 CategoryPlot plot = chart.getCategoryPlot();
					 
					 NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
				        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				        yAxis.setRange(1, 20); // Set range from 1 to 30
				        
				        // Set background color to white
				        chart.setBackgroundPaint(Color.WHITE);
				        chart.getPlot().setBackgroundPaint(Color.WHITE);
					 
					    Font marathiFont = new Font("Noto Sans Devanagari", Font.BOLD, 12);
				        //CategoryPlot plot = chart.getCategoryPlot();
				        CategoryAxis domainAxis = plot.getDomainAxis();
				        domainAxis.setTickLabelFont(marathiFont);
				        domainAxis.setLabelFont(marathiFont);
				        ValueAxis rangeAxis = plot.getRangeAxis();
				        rangeAxis.setTickLabelFont(marathiFont);
				        rangeAxis.setLabelFont(marathiFont);
				        
				        
				        Font legendFont = new Font("Noto Sans Devanagari", Font.BOLD, 12); // Bold font for legend
				        LegendTitle legend = chart.getLegend();
				        if (legend != null) {
				            legend.setItemFont(legendFont);
				        }
				        
				        // Set font for the title
				        Font titleFont = new Font("Noto Sans Devanagari", Font.BOLD, 18); // Bold font for title
				        chart.setTitle(new org.jfree.chart.title.TextTitle("Types and Number of Plants / रोपांचे प्रकार व संख्या", titleFont));
				      
				        
					 try {
						
				        	ChartUtils.saveChartAsPNG(new File("E:\\Nayan_Eclipse_workspace\\0901_108\\Que_"+j+".png"), chart, 800, 600);

				        } catch (IOException e) {
				            e.printStackTrace();
				        }
					 
					// System.out.println(dataset);
					 String outputFileName = "E:\\Nayan_Eclipse_workspace\\0901_108\\Que_"+j+".png";
					 ArrayList<Integer> Values=new ArrayList<Integer>();
					 ArrayList<String> Series=new ArrayList<String>();
					 ArrayList<Integer> counts=new ArrayList<Integer>();
					 HashMap<Integer,String> hm=new HashMap<Integer,String>();
					  for (int i = 0; i < dataset.getRowCount(); i++) {
				            Comparable<?> seriesKey = dataset.getRowKey(i);
				            for (int k = 0; k < dataset.getColumnCount(); k++) {
				                Comparable<?> categoryKey = dataset.getColumnKey(k);
				                Number value = dataset.getValue(seriesKey, categoryKey);
				                int intValue = value.intValue();
				                Values.add(intValue);
				                hm.put(intValue, categoryKey.toString());
				                counts.add(intValue);
				                Series.add(seriesKey.toString());
				               // System.out.println("Series: " + seriesKey + ", Category: " + categoryKey + ", Value: " + intValue);
				            }
				        }
					  
					  System.out.println(hm);
					  //System.out.println(Series);
					  
					  int p=r.nextInt(3);
					  String ple="";
					  String plm="";
					  String plm1="";
					  String series=Series.get(0);
					  
					    String ser[]= {"Flower/फूल","Fruit/फळ","Vegetable/भाजी"};
				        String plants[]= {"Fruit","Flower","Vegetable"};
				        
				       
				        String plant1="";
				        String plant2="";
				        String plant3="";
				        String plant4="";
				        String plant5="";
				        
				        
				        String plant1m="";
				        String plant2m="";
				        String plant3m="";
				        String plant4m="";
				        String plant5m="";
				        
				        String plant1m1="";
				        String plant2m1="";
				        String plant3m1="";
				        String plant4m1="";
				        String plant5m1="";
				        
				        String add="";
				        
				        if(series.equals("Fruit/फळ")) {
				        	ple="fruit";
				        	plm="फळां";
				        	plm1="फळ";
				        	
				        	plant1="Mango";
							plant2="Apple";
							plant3="Banana";
							plant4="Orange";
							plant5="Grapes";
							
							plant1m="आंबा";
							plant2m="सफरचंद";
							plant3m="केळं";
							plant4m="संत्रा";
							plant5m="द्राक्षे";
							
							add="फळझाडांची";
							
							//आंब्यांच्या 				 सफरचंदांच्या 				 केळांच्या 				 संत्र्यांच्या 				 द्राक्ष्यांच्या 
							
							plant1m1="आंब्यां";
							plant2m1="सफरचंदां";
							plant3m1="केळीं";
							plant4m1="संत्र्यां";
							plant5m1="द्राक्ष्यां";
							
							
				        }else if(series.equals("Flower/फूल")){
				        	ple="flower";
				        	plm="फुलां";
				        	plm1="फूल";
				        	plant1="Rose";
							plant2="Marigold";
							plant3="Sunflower";
							plant4="Jasmine";
							plant5="Lotus";
							
							plant1m="गुलाब";
							plant2m="झेंडू";
							plant3m="सूर्यमुखी";
							plant4m="चंपा";
							plant5m="कमळ";
							
							plant1m1="गुलाबां";
							plant2m1="झेंडूं" ;			
							plant3m1="सूर्यमुखीं";
							plant4m1="चंपा";
							plant5m1="कमळां";
							
				        }else {
				        	ple="vegetables";
				        	plm="भाज्यां";
				        	plm1="फूल";
				        	plant1="Brinjal";
							plant2="Potato";
							plant3="Onion";
							plant4="Tomato";
							plant5="Cabbage";
							
							plant1m="वांगी"; 
							plant2m="बटाटा";
							plant3m="कांदा";
							plant4m="टमाटर";
							plant5m="कोबी";
							
							plant1m1="वांग्यां";
							plant2m1="बटाट्यां";
							plant3m1="कांद्यां";
							plant4m1="टमाटर";
							plant5m1="कोबीं"; 
				        }
				      
				        	HashMap<Integer,String>data=new HashMap<Integer,String>();
				        	
				        	data.put(Values.get(0), plant1);
				        	data.put(Values.get(1), plant2);
				        	data.put(Values.get(2), plant3);
				        	data.put(Values.get(3), plant4);
				        	data.put(Values.get(4), plant5);
				        	
				        	HashMap<Integer,String>datam=new HashMap<Integer,String>();
				        	datam.put(Values.get(0), plant1m);
				        	datam.put(Values.get(1), plant2m);
				        	datam.put(Values.get(2), plant3m);
				        	datam.put(Values.get(3), plant4m);
				        	datam.put(Values.get(4), plant5m);
				        	
				        	HashMap<Integer,String>datam1=new HashMap<Integer,String>();
				        	datam1.put(Values.get(0), plant1m1);
				        	datam1.put(Values.get(1), plant2m1);
				        	datam1.put(Values.get(2), plant3m1);
				        	datam1.put(Values.get(3), plant4m1);
				        	datam1.put(Values.get(4), plant5m1);
				        	
				        	Collections.sort(counts);
				        	int maxCount=counts.get(counts.size()-1);
				        	
				        	String maxPlantName=data.get(maxCount);
				        	String maxPlantNamem=datam.get(maxCount);
				        	String maxPlantNamem1=datam1.get(maxCount);
				        	
				        	
				        	
					String Que="To display the number of different "+ple+" plants in a garden a table is prepared with tally marks as shown in the figure. "
							+ "From the table decide the type and number of plants which are maximum.<br>"
							+ "#एका बागेतील वेगवेगळ्या प्रकारच्या "+plm+"च्या रोपांची संख्या दाखविण्यासाठी खालील तक्त्यात दाखविल्याप्रमाणे ताळ्याच्या खुणा केलेल्या आहेत."
							+ " तर बागेत सर्वात जास्त कोणत्या "+plm+"ची आणि किती रोपे आहेत ते तक्त्यावरून उत्तर द्या.<br> ";
					
					String solution="Ans : Maximum plants - "+maxPlantName+", No. of plants - $"+maxCount+"$<br>"
							+ "To decide the number of flower plants of each type, we will count the tally marks for each type.<br>"
							+ "They are "+plant1+" - $"+Values.get(0)+"$, "+plant2+" - $"+Values.get(1)+"$, "+plant3+" - $"+Values.get(2)+"$, "+plant4+" - $"+Values.get(3)+"$, "+plant5+" - $"+Values.get(4)+"$<br>"
							+ ""+maxPlantName+" plants are $"+maxCount+"$ and they are maximum.<br>"
							+ "Hence the answer is - Maximum plants  - "+maxPlantName+", and Number of plants $"+maxCount+"$ is the answer.<br>"
							+ "#उत्तर  : सर्वात जास्त रोपे - "+maxPlantNamem+" , रोपांची संख्या - $"+maxCount+"$ <br>"
							+ "बागेतील वेगवेगळ्या प्रकारच्या "+plm+"च्या रोपांची संख्या मिळवण्यासाठी ताळा करू.<br>"
							+ "तक्त्यावरून,   <br>"
							+ ""+plant1m+" - $"+Values.get(0)+"$, "+plant2m+" - $"+Values.get(1)+"$, "+plant3m+" - $"+Values.get(2)+"$, "+plant4m+" - $"+Values.get(3)+"$, "+plant5m+" - $"+Values.get(4)+"$  झाडांच्या  संख्या  अशा आहेत.<br>"
							+ ""+maxPlantNamem1+"च्या रोपांची संख्या सर्वात जास्त आहे.<br> "
							+ "म्हणून , सर्वात जास्त रोपे - "+maxPlantNamem+" , रोपांची संख्या - $"+maxCount+"$  हे उत्तर .<br>";
					
					
					String Ans="Maximum plants - "+maxPlantName+", No. of plants - $"+maxCount+"$<br>"
							+ "#सर्वात जास्त रोपे - "+maxPlantNamem+" , रोपांची संख्या - $"+maxCount+"$ <br>";
					
					String w1="Maximum plants - "+maxPlantName+", No. of plants - $"+counts.get(0)+"$<br>"
							+ "#सर्वात जास्त रोपे - "+maxPlantNamem+" , रोपांची संख्या - $"+counts.get(0)+"$ <br>";
					
					String w2="Maximum plants - "+maxPlantName+", No. of plants - $"+counts.get(1)+"$<br>"
							+ "#सर्वात जास्त रोपे - "+maxPlantNamem+" , रोपांची संख्या - $"+counts.get(1)+"$ <br>";
					
					String wplantName=data.get(counts.get(0));
					String wplantNamem=data.get(counts.get(0));
					
					String w3="Maximum plants - "+wplantName+", No. of plants - $"+maxCount+"$<br>"
							+ "#सर्वात जास्त रोपे - "+wplantNamem+" , रोपांची संख्या - $"+maxCount+"$ <br>";
					
					String wplantName1=data.get(counts.get(1));
					String wplantNamem1=data.get(counts.get(1));
					String w4="Maximum plants - "+wplantName1+", No. of plants - $"+maxCount+"$<br>"
							+ "#सर्वात जास्त रोपे - "+wplantNamem1+" , रोपांची संख्या - $"+maxCount+"$ <br>";
					
					String wplantName2=data.get(counts.get(2));
					String wplantNamem2=data.get(counts.get(2));
					String w5="Maximum plants - "+wplantName2+", No. of plants - $"+maxCount+"$<br>"
							+ "#सर्वात जास्त रोपे - "+wplantNamem2+" , रोपांची संख्या - $"+maxCount+"$ <br>";
					
					
					String wplantName3=data.get(counts.get(3));
					String wplantNamem3=data.get(counts.get(3));
					String w6="Maximum plants - "+wplantName3+", No. of plants - $"+maxCount+"$<br>"
							+ "#सर्वात जास्त रोपे - "+wplantNamem3+" , रोपांची संख्या - $"+maxCount+"$ <br>";
					
					String w7="Maximum plants - "+maxPlantName+", No. of plants - $"+counts.get(2)+"$<br>"
							+ "#सर्वात जास्त रोपे - "+maxPlantNamem+" , रोपांची संख्या - $"+counts.get(2)+"$ <br>";
					
					String w8="Maximum plants - "+maxPlantName+", No. of plants - $"+counts.get(3)+"$<br>"
							+ "#सर्वात जास्त रोपे - "+maxPlantNamem+" , रोपांची संख्या - $"+counts.get(3)+"$ <br>";
					
					String WA[] = { w1, w2, w3,w4,w5,w6,w7,w8};	
					
					
					int ar[] = GetIndex();

					ArrayList<String> W = new ArrayList<String>();// It is used to store wrong answer
					W.add(WA[ar[0]]);
					W.add(WA[ar[1]]);
					W.add(WA[ar[2]]);

					HashSet<String> hs = new HashSet<String>();
					hs.add(WA[ar[0]]);
					hs.add(WA[ar[1]]);
					hs.add(WA[ar[2]]);
					
					//insertImageIntoExcel(sheet, outputFileName, j, 8,workbook);
			
					 HashSet<Integer> hashSet = new HashSet<Integer>(Values);
					 
            	if (Q.contains(solution) || W.contains(Ans) || hs.size() != W.size() || hashSet.size()!=5) {
					j--;
				} else {
		
					Q.add(solution);
					XSSFRow row = sheet.createRow((short) j);
					row.createCell(0).setCellValue(j);
					row.createCell(1).setCellValue("Text");
					row.createCell(2).setCellValue(1);
					row.createCell(3).setCellValue("0901");
					row.createCell(4).setCellValue(Que); // Question
					row.createCell(5).setCellValue(Ans); // Answer
					// row.createCell(6).setCellValue("");
					// row.createCell(7).setCellValue("");
					// row.createCell(8).setCellValue("");
					row.createCell(9).setCellValue(WA[ar[0]]); // w1
					row.createCell(10).setCellValue(WA[ar[1]]); // w2
					row.createCell(11).setCellValue(WA[ar[2]]); // w3
					row.createCell(12).setCellValue(90);
					row.createCell(13).setCellValue(2);
					// row.createCell(14).setCellValue("");
					row.createCell(15).setCellValue("nayanlokhande007@gmail.com");
					row.createCell(16).setCellValue(solution);// solution
					// row.createCel l(17).setCellValue("");
					row.createCell(18).setCellValue(108);
				}
            
					
				}
				try {
					for(int i=1;i<=qn;i++) {
						insertImageIntoExcel(sheet, "E:\\Nayan_Eclipse_workspace\\0901_108\\Que_"+i+".png", i, 8,workbook);
					}
					XSSFRow row = sheet.createRow((short) 201);
					row.createCell(0).setCellValue("****");
					FileOutputStream fileOut = new FileOutputStream(filename);
					workbook.write(fileOut);
					// closing the Stream
					fileOut.close();
					// closing the workbook
					// workbook.close();
					// prints the message on the console
					
					System.out.println("Excel file has been generated successfully.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // end of try
			catch (Exception e) {

			}
		}

	} // end of main
      
    static int[] GetIndex() {
		Random r = new Random();
		int arr[] = new int[3];
		ArrayList<Integer> al = new ArrayList<Integer>();
		int count = 0;
		while (count != 3) {
			int a = r.nextInt(8);
			if (!(al.contains(a))) {
				al.add(a);
				count++;
			}

		}
		// System.out.println(al);
		for (int i = 0; i < 3; i++) {
			arr[i] = al.get(i);
		}
		return arr;
	}

    private static CategoryDataset createDataset() {
    	
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       
        Random r = new Random();
        int count1=r.nextInt(3,20);
        int count2=r.nextInt(3,20);
        int count3=r.nextInt(3,20);
        int count4=r.nextInt(3,20);
        int count5=r.nextInt(3,20);
        
        HashSet<Integer>hs=new HashSet<Integer>();
        hs.add(count1);
        hs.add(count2);
        hs.add(count3);  
        hs.add(count4);
        hs.add(count5);
        
        
//        while(hs.size()!=5) {
//        	
//        	 count1=r.nextInt(3,20);
//             count2=r.nextInt(3,20);
//             count3=r.nextInt(3,20);
//             count4=r.nextInt(3,20);
//             count5=r.nextInt(3,20);
//        }
        
//        count1=2;
//        count2=3;
//        count3=4;
//        count4=5;
//        count5=6;
      
        
        int p=r.nextInt(3);
        String plants[]= {"Fruit","Flower","Vegetable"};
        
        String plantsMarathi[] = {"फळ", "फूल", "भाजी"};
		
        String Series=plants[p];
        String Seriesm=plantsMarathi[p];
        
        String fruits[] = {"Mango", "Apple", "Banana", "Orange", "Grapes"};
        String fruitsMarathi[] = {"आंबा", "सफरचंद", "केळं", "संत्रा", "अंगूर"};

        String flowersEnglish[] = { "Rose", "Marigold", "Sunflower", "Jasmine","Lotus"};
        String flowersMarathi[] = { "गुलाब ", "झेंडू ", "सूर्यमुखी ", "चंपा ","कमळ "};

        String vegetablesEnglish[] = {"Brinjal", "Potato", "Onion", "Tomato", "Bitter Gourd"};
        String vegetablesMarathi[] = {"वांगी", "बटाटा", "कांदा", "टमाटर", "कारले"};
        
        String plant1="";
        String plant2="";
        String plant3="";
        String plant4="";
        String plant5="";
        
        
        String plant1m="";
        String plant2m="";
        String plant3m="";
        String plant4m="";
        String plant5m="";
        
        if(p==0) {
			plant1="Mango";
			plant2="Apple";
			plant3="Banana";
			plant4="Orange";
			plant5="Grapes";
			
			plant1m="आंबा";
			plant2m="सफरचंद";
			plant3m="केळं";
			plant4m="संत्रा";
			plant5m="द्राक्षे";
			
		}else if(p==1){
			
			plant1="Rose";
			plant2="Marigold";
			plant3="Sunflower";
			plant4="Jasmine";
			plant5="Lotus";
			
			plant1m="गुलाब";
			plant2m="झेंडू";
			plant3m="सूर्यमुखी";
			plant4m="चंपा";
			plant5m="कमळ";
			
		}else {
			plant1="Brinjal";
			plant2="Potato";
			plant3="Onion";
			plant4="Tomato";
			plant5="Cabbage";
			
			plant1m="वांगी";
			plant2m="बटाटा";
			plant3m="कांदा";
			plant4m="टमाटर";
			plant5m="कोबी";
		}
        

        dataset.addValue(count1, ""+Series+"/"+Seriesm+"", ""+plant1+"/"+plant1m+"");
        dataset.addValue(count2,  ""+Series+"/"+Seriesm+"", ""+plant2+"/"+plant2m+"");
        dataset.addValue(count3,  ""+Series+"/"+Seriesm+"", ""+plant3+"/"+plant3m+"");
        dataset.addValue(count4,  ""+Series+"/"+Seriesm+"", ""+plant4+"/"+plant4m+"");
        dataset.addValue(count5,  ""+Series+"/"+Seriesm+"", ""+plant5+"/"+plant5m+"");
        
        
        return dataset;
        
    }
    
    public static void insertImageIntoExcel(XSSFSheet sheet, String imagePath, int rowNumber, int columnNumber, XSSFWorkbook wb) throws IOException {
	    // Add the picture to the workbook
	    try (FileInputStream inputStream = new FileInputStream(imagePath)) {
	        byte[] bytes = IOUtils.toByteArray(inputStream);
	        int pictureIdx = sheet.getWorkbook().addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);

	        XSSFCreationHelper helper = wb.getCreationHelper();
	        // Create the drawing patriarch
	        XSSFDrawing drawing = sheet.createDrawingPatriarch();

	        // Add a picture shape
	        XSSFClientAnchor anchor = helper.createClientAnchor();
	        
	        // Load the image to get its dimensions
	        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(imagePath));
	        int imageWidth = bufferedImage.getWidth();
	        int imageHeight = bufferedImage.getHeight();
	        
	        // Set the cell width and height
	        float imageHeightInPoints = (float) (imageHeight * 72 / 96); // assuming 96 DPI
	        sheet.getRow(rowNumber).setHeightInPoints(imageHeightInPoints);
	        
	        // Column width is measured in units of 1/256th of a character width
	        // Assuming a character width of approximately 8 pixels
	        int columnWidthInUnits = (int) (imageWidth / 8.0 * 256);
	       // columnWidthInUnits=imageWidth;
	        sheet.setColumnWidth(columnNumber, columnWidthInUnits);

	        anchor.setCol1(columnNumber);
	        anchor.setRow1(rowNumber);
	        anchor.setCol2(columnNumber + 1);
	        anchor.setRow2(rowNumber + 1);

	        Picture pict = drawing.createPicture(anchor, pictureIdx);
	        pict.resize(); 
	    }
	}
	
   
}
