package NewPrograms;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.jfree.data.json.impl.JSONObject;
import org.json.JSONObject;

public class Pythagoreous_Theorem {
	public static void main(String args[]) {

		try {
			String filename = "E:\\Nayan_Eclipse_workspace\\Excel\\Pythagoreous_Theorem.xlsx";
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Instruction ");
			XSSFSheet sheet1 = workbook.createSheet("Questions");

//			String[] header = { "Sr. No", "Question", "Correct Answer 1", "Wrong Answer 1", "Wrong Answer 2",
//					"Wrong Answer 3", "Solution", "Email ID" };
//			for (int count = 0; count < header.length; count++) {
//				row.createCell(count).setCellValue(header[count]);
//
//			}

			XSSFRow rowhead = sheet1.createRow((short) 0);
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

			sheet1.setColumnWidth(4, 2 * 5000);
			sheet1.setColumnWidth(16, 2 * 5000);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the number of questions:");
			int n = sc.nextInt();
			XSSFRow row1 = sheet1.createRow((short) n);
			ArrayList<String> Q = new ArrayList<String>();
			int k=0;
			int k1=0;
			for (int i = 1; i <= n; i++) {

				row1 = sheet1.createRow(i);
				int diameter = 500; // Diameter of the circle
				int borderWidth = 5; // Width of the border
				Color backgroundColor = Color.WHITE; // Background color
				Color borderColor = Color.BLACK; // Border color
				String outputFileName = "E:\\Nayan_Eclipse_workspace\\Images\\Que" + i + ".png";

				System.out.println("diamter=" + diameter);
				System.out.println("borderWidth=" + borderWidth);
				System.out.println("backgroundColor=" + backgroundColor);
				System.out.println("borderColor=" + borderColor);
				System.out.println("outputFileName=" + outputFileName);
				
				
				String Names[] ={"ABC","BCA","CAB","PQR","QRP","RPQ","LMN","MNL","NLM","XYZ","YZX","ZXY"};
				String str=Names[k];
				
				char l1=str.charAt(0);
				char l2=str.charAt(1);
				char l3=str.charAt(2);
				
				generateTriangleImageWithBorder(diameter, borderWidth, backgroundColor, borderColor,
						outputFileName, i,l1,l2,l3);
                 
                 if(i>12) {
                
                	 generateTriangleImageWithBorder(diameter, borderWidth, backgroundColor, borderColor,
     						outputFileName, i,l1,l2,l3);
                	k1=1;
                 }if(k1==1){
                	
                	 generateTriangleImageWithBorder1(diameter, borderWidth, backgroundColor, borderColor,
     						outputFileName, i,l1,l2,l3);
                	 
                 }
                 if(i==12) {
                	 k=-1;
            	 }
            	
              k++;
//				int lengthCord = (int) result.get("length");
//				int genRadius = (int) result.get("radius");
//				String centerPoint = (String) result.get("center");
//				String firstPoint = (String) result.get("cordFirstPoint");
//				String secondPoint = (String) result.get("cordSecondPoint");
//				String centerP = (String) result.get("center1");
//
//				String queImgPath = "E:\\Nayan_Eclipse_workspace\\Images\\Que2" + i + ".png";
                
                 String base=Character.toString(l1)+Character.toString(l2);
                 String height=Character.toString(l1)+Character.toString(l3);
                 String hypo=Character.toString(l2)+Character.toString(l3);
//
				String question = "In the right-angled $\\triangle "+str+"$, $\\angle "+l1+" = 90^0$. If $l("+base+")=3$ cm and $l("+height+")=4$ cm, then,"
						+ " find the length of seg $"+hypo+"$.<br>"
						+ "#काटकोन $\\triangle "+str+"$ मधे $\\angle ,"+l1+" = 90^0$ आहे. तसेच  $"+base+"$ ही बाजू $3$ सेंमी असून $"+height+"$ ही बाजू $4$ सेंमी आहे."
						+ " अशा काटकोन त्रिकोणाच्या $"+hypo+"$ बाजूची लांबी किती असेल?<br> ";
				
				

//				int rsq = genRadius * genRadius;
//				int chordsq = lengthCord * lengthCord;
//
//				int finaAns = rsq - chordsq;
//				int sqrtFinAns = (int) Math.sqrt(finaAns);
//
//				int correctAns = sqrtFinAns * 2;
//
//				String solution = "Radius of the circle " + centerPoint + firstPoint + " = $" + genRadius + " $<br>"
//						+ " Length of chord " + firstPoint + secondPoint + " <br>" + centerPoint + centerP + "  = $"
//						+ lengthCord + " $ cm <br>" + " As we know " + centerPoint + centerP + "$^2$ <br>" + centerPoint
//						+ firstPoint + " $^2$ = " + centerPoint + centerP + "$^2$ + " + firstPoint + centerP
//						+ "$^2$<br>" + "$" + genRadius + "^2 = " + lengthCord + "^2$ + " + firstPoint + centerP
//						+ "$^2$ <br>" + firstPoint + centerP + "$^2 = " + genRadius + "^2 - " + lengthCord + "^2 $ <br>"
//						+ firstPoint + centerP + "$^2 = " + rsq + " - " + chordsq + "$<br>" + firstPoint + centerP
//						+ "$^2 = " + finaAns + " $<br>" + "$\\therefore$ " + firstPoint + centerP + " = $" + sqrtFinAns
//						+ "$ cm <br>" + firstPoint + secondPoint + " = 2" + firstPoint + centerP + " = $ " + sqrtFinAns
//						+ " \\times 2 =   " + correctAns + "$ <br> " + "$\\therefore$ length of chord = $" + correctAns
//						+ "$ cm , is the answer.";

//				int w1 = correctAns + 1;
//				int w2 = correctAns - 1;
//				int w3 = genRadius + 1;

				//String duplicateCheck = question + solution;
                 insertImageIntoExcel(sheet1, outputFileName, i, 8,workbook);
				if (Q.contains(false)) {
					i--;
				} else {
					//Q.add(duplicateCheck);

					row1.createCell(0).setCellValue(i);
					row1.createCell(1).setCellValue("Text");
					row1.createCell(2).setCellValue(1);
					row1.createCell(3).setCellValue("##########");
					row1.createCell(4).setCellValue("question"); // Question
					row1.createCell(5).setCellValue("correctAns"); // Answer
					//row1.createCell(6).setCellValue();// Question Image Path
					// row.createCell(7).setCellValue("");
					// row.createCell(8).setCellValue("");
					row1.createCell(9).setCellValue("w1"); // w1
					row1.createCell(10).setCellValue("w2"); // w2
					row1.createCell(11).setCellValue(" w3 "); // w3
					row1.createCell(12).setCellValue(90);
					row1.createCell(13).setCellValue(1);
					// row.createCell(14).setCellValue("");
					row1.createCell(15).setCellValue("nayanlokhande007@gmail.com");
					row1.createCell(16).setCellValue("solution");// solution
					// row.createCell(17).setCellValue("");
					row1.createCell(18).setCellValue(101);
				}
			}

			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();
			fileOut.close();
			System.out.println("File generated successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void generateTriangleImageWithBorder(int diameter, int borderWidth, Color backgroundColor,
			Color borderColor, String outputFileName, int que,char l1,char l2,char l3) {

		String lb1=Character.toString(l1);
		String lb2=Character.toString(l2);
		String lb3=Character.toString(l3);
		
	// Create a BufferedImage with a specified type
	BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
	Graphics2D graphics = image.createGraphics();
	
	// Enable anti-aliasing for smooth edges
	graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	// Fill the background with the specified color (white)
	graphics.setComposite(AlphaComposite.Src);
	graphics.setColor(backgroundColor); // Set the background color
	graphics.fillRect(0, 0, diameter, diameter); // Fill the entire image with the background color

	// Set the color for the border (black)
	graphics.setColor(borderColor);
	Font font = new Font("Arial", Font.PLAIN, 30); // Font name, style, and size
	graphics.setFont(font);
	// Draw the triangle with the border
	graphics.drawLine(100, 50, 100, 450);
	graphics.drawLine(100, 450,400,450);
	graphics.drawLine(400,450,100,50);
	graphics.drawString(lb2, 70, 460);
	graphics.drawString(lb3, 410, 460);
	graphics.drawString(lb1, 80, 40);
	graphics.drawString("3", 250, 490);
	graphics.drawString("4", 65, 250);
	graphics.drawString("5", 265, 250);
	
	graphics.drawLine(100, 425, 125, 425);
	graphics.drawLine(125, 425,125,450);
	// Dispose the graphics object to release resources
	graphics.dispose();

	// Save the image to file
	try {
		File outputFile = new File(outputFileName);
		ImageIO.write(image, "png", outputFile);
		System.out.println("Triangle image with border generated successfully.");
	} catch (IOException e) {
		System.err.println("Error while generating triangle image with border: " + e.getMessage());
	}
}
	
	
	@SuppressWarnings("unchecked")
	public static void generateTriangleImageWithBorder1(int diameter, int borderWidth, Color backgroundColor,
			Color borderColor, String outputFileName, int que,char l1,char l2,char l3) {

		String lb1=Character.toString(l1);
		String lb2=Character.toString(l2);
		String lb3=Character.toString(l3);
		
	// Create a BufferedImage with a specified type
	BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
	Graphics2D graphics = image.createGraphics();
	
	// Enable anti-aliasing for smooth edges
	graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	// Fill the background with the specified color (white)
	graphics.setComposite(AlphaComposite.Src);
	graphics.setColor(backgroundColor); // Set the background color
	graphics.fillRect(0, 0, diameter, diameter); // Fill the entire image with the background color

	// Set the color for the border (black)
	graphics.setColor(borderColor);
	Font font = new Font("Arial", Font.PLAIN, 30); // Font name, style, and size
	graphics.setFont(font);
	// Draw the triangle with the border
	graphics.drawLine(400, 50, 400, 450);
	graphics.drawLine(400, 450,100,450);
	graphics.drawLine(100,450,400,50);
	graphics.drawString(lb2, 70, 460);
	graphics.drawString(lb3, 410, 460);
	graphics.drawString(lb1, 400, 40);
	graphics.drawString("3", 250, 490);
	graphics.drawString("5", 220, 250);
	graphics.drawString("4", 415, 250);
	
	graphics.drawLine(400, 425, 375, 425);
	graphics.drawLine(375, 425,375,450);
	// Dispose the graphics object to release resources
	graphics.dispose();

	// Save the image to file
	try {
		File outputFile = new File(outputFileName);
		ImageIO.write(image, "png", outputFile);
		System.out.println("Triangle image with border generated successfully.");
	} catch (IOException e) {
		System.err.println("Error while generating triangle image with border: " + e.getMessage());
	}
}
	
	
	static String[] lablesTriplte() {
		Random r = new Random();
		String var[] = new String[3];
		String[] Chr1 = { "A", "B", "F", "L", "P", "R", "U", "X", "D" };
		String[] Chr2 = { "B", "C", "G", "M", "Q", "S", "V", "Y", "E" };
		String[] Chr3 = { "C", "D", "H", "N", "R", "T", "W", "Z", "F" };
		int chi = r.nextInt(Chr1.length);

		String v1 = Chr1[chi];
		String v2 = Chr2[chi];
		String v3 = Chr3[chi];
		var[0] = v1;
		var[1] = v2;
		var[2] = v3;

		return var;
	}

	static String[] lablesTriplte2() {
		Random r = new Random();
		String var[] = new String[3];
		String[] Chr1 = { "A", "B", "C", "E", "D", "L", "M", "S", "U", "X", "V" };
		String[] Chr2 = { "B", "C", "D", "F", "E", "M", "N", "T", "V", "Y", "W" };

		int chi = r.nextInt(Chr1.length);

		String v1 = Chr1[chi];
		String v2 = Chr2[chi];

		var[0] = v1;
		var[1] = "P";
		var[2] = v2;

		return var;
	}

	static int[] valuesTriplate() {
		Random r = new Random();
		int var[] = new int[3];
		int Chr1[] = { 3, 6, 9, 12, 15, 18, 5, 10, 8, 7 };
		int Chr2[] = { 4, 8, 12, 16, 20, 24, 12, 24, 15, 24 };
		int Chr3[] = { 5, 10, 15, 20, 25, 30, 13, 26, 17, 25 };
		int chi = r.nextInt(Chr1.length);

		int v1 = Chr1[chi];
		int v2 = Chr2[chi];
		int v3 = Chr3[chi];

		var[0] = v1;
		var[1] = v2;
		var[2] = v3;

		return var;
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
