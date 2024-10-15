package NewPrograms;
import java.util.*;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
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

import java.io.FileInputStream;
import java.io.IOException;
//import org.jfree.data.json.impl.JSONObject;
import org.json.JSONObject;

public class vlab_04050403_101_Assign1_Nayan_Lokhande {
	public static void main(String args[]) {

		try {
			String filename = "E:\\Nayan_Eclipse_workspace\\Geometry\\Geometry_Excels\\vlab_04050403_101_Assign1_Nayan_Lokhande.xlsx";
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Instruction ");
			XSSFSheet sheet1 = workbook.createSheet("Questions");
			XSSFRow rowhead = sheet1.createRow((short) 0);
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
			for (int i = 1; i <= n; i++) {
                Random r=new Random();
				row1 = sheet1.createRow(i);
				int diameter = 200; // Diameter of the circle
				int borderWidth = 2; // Width of the border
				Color backgroundColor = Color.WHITE; // Background color
				Color borderColor = Color.BLACK; // Border color
				int circleType=r.nextInt(8);
				String outputFileName = "E:\\Nayan_Eclipse_workspace\\04050403_101\\Que" + i + ".png";
				//circleType=4;
				JSONObject result;
//				if(i<=2) {
//					circleType=0;
//				}
//				else if(i<=4 && i>=3) {
//					circleType=1;
//				}
//				else if(i<=6 && i>=5) {
//					circleType=2;
//				}
//				else if(i<=8 && i>=7) {
//					circleType=3;
//				}
//				else if(i<=10 && i>=9) {
//					circleType=4;
//				}
//				else if(i<=12 && i>=11) {
//					circleType=5;
//				}
//				else if(i<=14 && i>=13) {
//					circleType=6;
//				}
//				else if(i<=16 && i>=15) {
//					circleType=7;
//					
//				}
				
				if(circleType==0) {
					 result = generateCircleImageWithBorder(diameter, borderWidth, backgroundColor, borderColor,
							outputFileName, i);
				}else if(circleType==1) {
					 result = generateCircleImageWithBorder2(diameter, borderWidth, backgroundColor, borderColor,
								outputFileName, i);
				}else if(circleType==2) {
					 result = generateCircleImageWithBorder3(diameter, borderWidth, backgroundColor, borderColor,
								outputFileName, i);
				}else if(circleType==3) {
					 result = generateCircleImageWithBorder4(diameter, borderWidth, backgroundColor, borderColor,
								outputFileName, i);
				}else if(circleType==4) {
					 result = generateCircleImageWithBorder5(diameter, borderWidth, backgroundColor, borderColor,
								outputFileName, i);
				}else if(circleType==5){
					 result = generateCircleImageWithBorder6(diameter, borderWidth, backgroundColor, borderColor,
								outputFileName, i);
				}else if(circleType==6){
					result = generateCircleImageWithBorder7(diameter, borderWidth, backgroundColor, borderColor,
							outputFileName, i);
				}else {
					result = generateCircleImageWithBorder8(diameter, borderWidth, backgroundColor, borderColor,
							outputFileName, i);
				}
				 
				
				int lengthchord = (int) result.get("length");
				int genRadius = (int) result.get("radius");
				String centerPoint = (String) result.get("center");
				String firstPoint = (String) result.get("chordFirstPoint");
				String secondPoint = (String) result.get("chordSecondPoint");
				String centerP = (String) result.get("center1");

				String queImgPath = "E:\\Nayan_Eclipse_workspace\\04050403_101\\Que" + i + ".png";

				String question = "There is  a circle with radius $r= " + genRadius + "$ cm and $" + firstPoint
						+ secondPoint + "$ is it's  " + " chord at a distance of $" + lengthchord
						+ "$ cm from the centre of the circle. " + "What will be the length of the chord?<br>"
						+ "#एका वर्तुळाची त्रिज्या $r= "+genRadius+"$ सेंमी आहे. त्याची $"+firstPoint+secondPoint+"$ ही जीवा वर्तुळ मध्या पासून $"+lengthchord+"$ सेंमी आहे.<br>"
				        + " तर त्या जीवेची लांबी किती?<br>";
				int rsq = genRadius * genRadius;
				int chordsq = lengthchord * lengthchord;

				int finaAns = rsq - chordsq;
				int sqrtFinAns = (int) Math.sqrt(finaAns);

				int correctAns = sqrtFinAns * 2;

				String chord = "" + firstPoint + secondPoint + "";
				String base = "" + firstPoint + centerP + "";
				String height = "" + centerPoint + centerP + "";
				String triangle = "" + centerPoint + centerP + firstPoint + "";
				String hypo = "" + centerPoint + firstPoint + "";

				int trHeight = lengthchord;
				int trHypo = genRadius;

				int heightSquare = trHeight * trHeight;
				int hypoSquare = trHypo * trHypo;
				int sub = hypoSquare - heightSquare;
				

				String solution = "Ans : $" + correctAns + "$ cm<br>"
				        + "Radius of the circle $" + centerPoint
						+ firstPoint + " = " + genRadius + " $ cm<br>"
						+ "We know that, if we draw a perpendicular to chord $" + firstPoint + secondPoint
						+ "$ from the centre $" + centerPoint + "$, it bisects the chord $" + firstPoint + secondPoint
						+ "$ at $" + centerP + "$.<br>" + "$\\therefore " + firstPoint + centerP + "=" + centerP
						+ secondPoint + "$<br>" + "Also, it is given that,  $" + centerPoint + centerP + "  = "
						+ lengthchord + " $ cm <br>" + "Let us consider $\\triangle " + triangle + "$<br>"
						+ "In $\\triangle " + triangle + "$, we know that, $" + hypo + "= " + trHypo + "$ cm, and $"
						+ height + "=" + trHeight + "$ cm <br>" + "Also $\\angle " + triangle + " = 90^0$<br>"
						+ "$\\therefore \\triangle " + triangle + "$ is a right angled $\\triangle$<br>"
						+ "$\\therefore$ by applying Pythagoras Theorem to $\\triangle " + triangle + "$, we get<br>"
						+ "$" + hypo + "^2 =" + height + "^2 + " + base + "^2$<br>" + "By substituting the values of $"
						+ hypo + "$ and $" + height + "$ in this equation, we get<br>" + "$" + trHypo + "^2=" + trHeight
						+ "^2+" + base + "^2$<br>" + "$\\therefore " + base + "^2 = " + hypoSquare + "-" + heightSquare
						+ " =" + sub + "$<br>" + "$\\therefore " + base + "=" + sqrtFinAns + "$<br>" + "Also $" + chord
						+ " = " + base + " +" + centerP + secondPoint + " = 2" + base + " . . . . (\\because " + base
						+ "=" + secondPoint + centerP + ")$<br>" + "$\\therefore$ length of the chord $" + chord
						+ " = 2\\times " + sqrtFinAns + " = " + correctAns + "$ cm is the answer. <br>"		
						+ "#उत्तर : $"+correctAns+"$ सेंमी <br>"
						+ " दिलेल्या वर्तुळाची त्रिज्या $"+hypo+" ="+genRadius+" $ सेंमी <br> "
						+ "आपल्याला हे माहिती आहे की जर वर्तुळ मध्यातून जीवा $"+chord+"$ वर आपण लंब काढला तर तो"
						+ " जीवा $"+chord+"$ ला $"+centerP+"$ या बिंदूपाशी दुभागतो. <br>"
						+ " $\\therefore "+base+"="+ centerP+secondPoint + "$<br> "
						+ "आपल्याला हे सुद्धा दिलेले आहे की $"+height+" = "+trHeight+" $ सेंमी <br>"
						+ " आपण $\\triangle "+triangle+"$ विचारात घेऊ. <br>"
						+ "$\\triangle "+triangle+"$ हा काटकोन त्रिकोण आहे $\\triangle "+triangle+"$ मध्ये $"+hypo+"= "+trHypo+"$ सेंमी आणि $"+height+"="+trHeight+"$ सेंमी आहे. <br>"
						+ " तसेच $\\angle "+triangle+" = 90^0$<br> $\\therefore \\triangle "+triangle+"$ हा काटकोन त्रिकोण आहे. <br>"
						+ " $\\therefore \\triangle "+triangle+"$ साठी पायथॅगोरसचा सिद्धांत वापरून, आपल्याला<br>"
						+ " $"+hypo+"^2 = "+height+"^2 + "+base+"^2$ असे मिळते. <br> "
						+ "या समीकरणात $"+hypo+"$ आणि $"+height+"$ यांच्या किमती ठेवून आपल्याला <br> "
						+ "$"+trHypo+"^2="+trHeight+"^2+"+base+"^2$ असे मिळते. <br> "
						+ "$\\therefore "+base+"^2 = "+hypoSquare+"-"+heightSquare+" ="+sub+"$<br>"
						+ " $\\therefore "+base+"="+sqrtFinAns+"$<br> "
						+ "तसेच $"+chord+" = "+base+" +"+ centerP + secondPoint +" = 2"+base+" . . . . (\\because "+base+"=" + secondPoint+centerP+")$<br>"
						+ " $\\therefore$ जीवा $"+chord+" = 2\\times "+sqrtFinAns+" = "+correctAns+"$ सेंमी आहे, हे उत्तर. <br>";
				
				
				
				int w1 = sqrtFinAns;
				int sum = hypoSquare + heightSquare;
				int w3 = (int) Math.sqrt(sum);
				int w2=w3*2;
				
				HashSet<Integer>hs=new HashSet<Integer>();
				hs.add(w1);
				hs.add(w2);
				hs.add(w3);
				String duplicateCheck = question + solution;
				
				//insertImageIntoExcel(sheet1, outputFileName, i, 8,workbook);
				
	                
				if (Q.contains(duplicateCheck)) {
					i--;
				} else {
					Q.add(duplicateCheck);

					row1.createCell(0).setCellValue(i);
					row1.createCell(1).setCellValue("Image");
					row1.createCell(2).setCellValue(1);
					row1.createCell(3).setCellValue("04050403");
					row1.createCell(4).setCellValue(question); // Question
					row1.createCell(5).setCellValue("$"+correctAns + "$<br>"); // Answer
					//row1.createCell(6).setCellValue();// Question Image Path
					// row.createCell(7).setCellValue("");
					// row.createCell(8).setCellValue("");
					row1.createCell(9).setCellValue("$" + w1 + "$<br>"); // w1
					row1.createCell(10).setCellValue("$" + w2 + "$<br>"); // w2
					row1.createCell(11).setCellValue("$" + w3 + "$<br>"); // w3
					row1.createCell(12).setCellValue(150);
					row1.createCell(13).setCellValue(2);
					row1.createCell(14).setCellValue(outputFileName);
					row1.createCell(15).setCellValue("nayanlokhande007@gmail.com");
					row1.createCell(16).setCellValue(solution);// solution
					 row1.createCell(17).setCellValue(outputFileName);//Solution Image Path
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
	public static JSONObject generateCircleImageWithBorder(int diameter, int borderWidth, Color backgroundColor,
			Color borderColor, String outputFileName, int que) {

		JSONObject obj = new JSONObject();
		String lables[] = new String[3];
		if (que <= 90) {
			lables = lablesTriplte();
		} else {
			lables = lablesTriplte2();
		}
		int values[] = valuesTriplate();

		int hypoteneous = values[2];
		int height = values[0];

		// BufferedImage image = new BufferedImage(diameter, diameter, height);
		BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB); // Explicitly specify
																									// image type
		Graphics2D graphics = image.createGraphics();
		// Enable anti-aliasing for smooth edges
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int radiusLabel = hypoteneous / 2;

		String label = "O";
		String label1 = lables[0];
		String label2 = lables[2];
		String label3 = " " + hypoteneous + " cm ";
		String label4 = " " + height + " cm ";
		String label5 = lables[1];

		obj.put("length", height);
		obj.put("radius", hypoteneous);
		obj.put("center", label);
		obj.put("chordFirstPoint", label1);
		obj.put("chordSecondPoint", label2);
		obj.put("RadiusLabel", label3);
		obj.put("chordLabel", label4);
		obj.put("center1", label5);
		

		graphics.setComposite(AlphaComposite.Clear);
		graphics.fillRect(0, 0, 400, 300);
		graphics.setComposite(AlphaComposite.Src);

		// Draw the outer circle (border)
		graphics.setColor(borderColor);
		graphics.fillOval(0, 0, diameter, diameter);

		int innerDiameter = diameter - 2 * borderWidth;
		int x = borderWidth;
		int y = borderWidth;
		graphics.setColor(backgroundColor);
		graphics.fillOval(x, y, innerDiameter, innerDiameter);

//       // Define the line coordinates

		int x1 = 100; // x-coordinate of the start point
		int y1 = 100; // y-coordinate of the start point
		int x2 = 15; // x-coordinate of the end point
		int y2 = 150; // y-coordinate of the end point

		float strokeWidth = 1.5f;
		graphics.setStroke(new BasicStroke(strokeWidth));

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x1, y1, x2, y2);

		int x11 = 15; // x-coordinate of the start point
		int y11 = 150; // y-coordinate of the start point
		int x22 = 185; // x-coordinate of the end point
		int y22 = 150; // y-coordinate of the end point

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x11, y11, x22, y22);

		int x111 = 100; // x-coordinate of the start point
		int y111 = 100; // y-coordinate of the start point
		int x222 = 100; // x-coordinate of the end point
		int y222 = 150; // y-coordinate of the end point

		// Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x111, y111, x222, y222);

		// Draw a label
		graphics.setColor(Color.RED); // Label color
		graphics.setFont(new Font("Times New Roman", Font.BOLD, 16)); // Label font

		graphics.drawString(label, 95, 95);//O position
		graphics.drawString(label1, 2, 160);//Chord ABC ->A Position
		graphics.drawString(label2, 188, 160);//C Position
		graphics.drawString(label5, 95, 170);//B Position

		//values
		graphics.drawString(label3, 20, 120); //hypoteneous value
		
		if(height>=10) {
			graphics.drawString(label4, 93 + radiusLabel, 130);//height value
		}else {
			if(height==7) {
				graphics.drawString(label4, 93 + radiusLabel, 130);
			}else {
				graphics.drawString(label4, 100 + radiusLabel, 130);
			}
			
		}
		
		// Dispose the graphics object to release resources
		graphics.dispose();

		// Save the image to file
		try {
			File outputFile = new File(outputFileName);
			ImageIO.write(image, "png", outputFile);
			System.out.println("Circle image with border generated successfully.");
		} catch (IOException e) {
			System.err.println("Error while generating circle image with border: " + e.getMessage());
		}
		return obj;

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
	
	@SuppressWarnings("unchecked")
	public static JSONObject generateCircleImageWithBorder2(int diameter, int borderWidth, Color backgroundColor,
			Color borderColor, String outputFileName, int que) {

		JSONObject obj = new JSONObject();
		String lables[] = new String[3];
		if (que <= 90) {
			lables = lablesTriplte();
		} else {
			lables = lablesTriplte2();
		}
		int values[] = valuesTriplate();

		int hypoteneous = values[2];
		int height = values[0];

		// BufferedImage image = new BufferedImage(diameter, diameter, height);
		BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB); // Explicitly specify
																									// image type
		Graphics2D graphics = image.createGraphics();
		// Enable anti-aliasing for smooth edges
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int radiusLabel = hypoteneous / 2;

		String label = "O";
		String label1 = lables[0];
		String label2 = lables[2];
		String label3 = " " + height + " cm ";
		String label4 = " " + hypoteneous + " cm ";
		String label5 = lables[1];

		obj.put("length", height);
		obj.put("radius", hypoteneous);
		obj.put("center", label);
		obj.put("chordFirstPoint", label2);
		obj.put("chordSecondPoint", label1);
		obj.put("RadiusLabel", label3);
		obj.put("chordLabel", label4);
		obj.put("center1", label5);
		// System.out.print(obj);

		graphics.setComposite(AlphaComposite.Clear);
		 graphics.fillRect(0, 0, 400, 300);
	        graphics.setComposite(AlphaComposite.Src);

	        
//		graphics.setColor(backgroundColor);
//		graphics.fillRect(0, 0, 400, 400);

		// Draw the outer circle (border)
		graphics.setColor(borderColor);
		graphics.fillOval(0, 0, diameter, diameter);

		int innerDiameter = diameter - 2 * borderWidth;
		int x = borderWidth;
		int y = borderWidth;
		graphics.setColor(backgroundColor);
		graphics.fillOval(x, y, innerDiameter, innerDiameter);

//       // Define the line coordinates
//
		int x1 = 100; // x-coordinate of the start point
		int y1 = 100; // y-coordinate of the start point
		int x2 = 15; // x-coordinate of the end point
		int y2 = 150; // y-coordinate of the end point

		float strokeWidth = 1.5f;
		graphics.setStroke(new BasicStroke(strokeWidth));

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x1, y1, 185, 150);

		int x11 = 15; // x-coordinate of the start point
		int y11 = 150; // y-coordinate of the start point
		int x22 = 185; // x-coordinate of the end point
		int y22 = 150; // y-coordinate of the end point

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x11, y11, x22, y22);

		int x111 = 100; // x-coordinate of the start point
		int y111 = 100; // y-coordinate of the start point
		int x222 = 100; // x-coordinate of the end point
		int y222 = 150; // y-coordinate of the end point

		// Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x111, y111, x222, y222);

		// Draw a label
		graphics.setColor(Color.RED); // Label color
		graphics.setFont(new Font("Times New Roman", Font.BOLD, 16)); // Label font

		graphics.drawString(label, 95, 95);
		graphics.drawString(label1, 2, 160);
		graphics.drawString(label2, 190, 160);
		graphics.drawString(label3, 50, 130);

		graphics.drawString(label4, 145, 120);
		graphics.drawString(label5, 100, 170);

		// Dispose the graphics object to release resources
		graphics.dispose();

		// Save the image to file
		try {
			File outputFile = new File(outputFileName);
			ImageIO.write(image, "png", outputFile);
			
			System.out.println("Circle image with border generated successfully.");
		} catch (IOException e) {
			System.err.println("Error while generating circle image with border: " + e.getMessage());
		}
		return obj;

	}
	
	//Circle Type 3
	@SuppressWarnings("unchecked")
	public static JSONObject generateCircleImageWithBorder3(int diameter, int borderWidth, Color backgroundColor,
			Color borderColor, String outputFileName, int que) {

		JSONObject obj = new JSONObject();
		String lables[] = new String[3];
		if (que <= 90) {
			lables = lablesTriplte();
		} else {
			lables = lablesTriplte2();
		}
		int values[] = valuesTriplate();

		int hypoteneous = values[2];
		int height = values[0];

		// BufferedImage image = new BufferedImage(diameter, diameter, height);
		BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB); // Explicitly specify
																									// image type
		Graphics2D graphics = image.createGraphics();
		// Enable anti-aliasing for smooth edges
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int radiusLabel = hypoteneous / 2;

		String label = "O";
		String label1 = lables[0];
		String label2 = lables[2];
		String label3 = " " + height + " cm ";
		String label4 = " " + hypoteneous + " cm ";
		String label5 = lables[1];

		obj.put("length", height);
		obj.put("radius", hypoteneous);
		obj.put("center", label);
		obj.put("chordFirstPoint", label2);
		obj.put("chordSecondPoint", label1);
		obj.put("RadiusLabel", label3);
		obj.put("chordLabel", label4);
		obj.put("center1", label5);
		// System.out.print(obj);

		graphics.setComposite(AlphaComposite.Clear);
		 graphics.fillRect(0, 0, 400, 300);
	        graphics.setComposite(AlphaComposite.Src);

	        
//		graphics.setColor(backgroundColor);
//		graphics.fillRect(0, 0, 400, 400);

		// Draw the outer circle (border)
		graphics.setColor(borderColor);
		graphics.fillOval(0, 0, diameter, diameter);

		int innerDiameter = diameter - 2 * borderWidth;
		int x = borderWidth;
		int y = borderWidth;
		graphics.setColor(backgroundColor);
		graphics.fillOval(x, y, innerDiameter, innerDiameter);

//       // Define the line coordinates
//
		int x1 = 100; // x-coordinate of the start point
		int y1 = 100; // y-coordinate of the start point
		int x2 = 185; // x-coordinate of the end point
		int y2 = 50; // y-coordinate of the end point

		float strokeWidth = 1.5f;
		graphics.setStroke(new BasicStroke(strokeWidth));

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x1, y1, x2, y2);

		int x11 = 15; // x-coordinate of the start point
		int y11 = 50; // y-coordinate of the start point
		int x22 = 185; // x-coordinate of the end point
		int y22 = 50; // y-coordinate of the end point

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x11, y11, x22, y22);

		int x111 = 100; // x-coordinate of the start point
		int y111 = 100; // y-coordinate of the start point
		int x222 = 100; // x-coordinate of the end point
		int y222 = 50; // y-coordinate of the end point

		// Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x111, y111, x222, y222);

		// Draw a label
		graphics.setColor(Color.RED); // Label color
		graphics.setFont(new Font("Times New Roman", Font.BOLD, 16)); // Label font

		// 3 variables
		graphics.drawString(label, 95, 115);
		graphics.drawString(label1, 2, 50);
		if(label2.equals("W")) {
			graphics.drawString(label2, 185, 50);
		}else {
			graphics.drawString(label2, 187, 50);
		}
		//2 values
		graphics.drawString(label3, 50, 75);
		graphics.drawString(label4, 135, 100);
		
		//top center variable
		graphics.drawString(label5, 95, 40);

		// Dispose the graphics object to release resources
		graphics.dispose();

		// Save the image to file
		try {
			File outputFile = new File(outputFileName);
			ImageIO.write(image, "png", outputFile);
			System.out.println("Circle image with border generated successfully.");
		} catch (IOException e) {
			System.err.println("Error while generating circle image with border: " + e.getMessage());
		}
		return obj;

	}

	@SuppressWarnings("unchecked")
	public static JSONObject generateCircleImageWithBorder4(int diameter, int borderWidth, Color backgroundColor,
			Color borderColor, String outputFileName, int que) {

		JSONObject obj = new JSONObject();
		String lables[] = new String[3];
		if (que <= 90) {
			lables = lablesTriplte();
		} else {
			lables = lablesTriplte2();
		}
		int values[] = valuesTriplate();

		int hypoteneous = values[2];
		int height = values[0];

		// BufferedImage image = new BufferedImage(diameter, diameter, height);
		BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB); // Explicitly specify
																									// image type
		Graphics2D graphics = image.createGraphics();
		// Enable anti-aliasing for smooth edges
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int radiusLabel = hypoteneous / 2;

		String label = "O";
		String label1 = lables[0];
		String label2 = lables[2];
		String label3 = " " + height + " cm ";
		String label4 = " " + hypoteneous + " cm ";
		String label5 = lables[1];

		obj.put("length", height);
		obj.put("radius", hypoteneous);
		obj.put("center", label);
		obj.put("chordFirstPoint", label1);
		obj.put("chordSecondPoint", label2);
		obj.put("RadiusLabel", label3);
		obj.put("chordLabel", label4);
		obj.put("center1", label5);
		// System.out.print(obj);

		graphics.setComposite(AlphaComposite.Clear);
		 graphics.fillRect(0, 0, 400, 300);
	        graphics.setComposite(AlphaComposite.Src);

	        
//		graphics.setColor(backgroundColor);
//		graphics.fillRect(0, 0, 400, 400);

		// Draw the outer circle (border)
		graphics.setColor(borderColor);
		graphics.fillOval(0, 0, diameter, diameter);

		int innerDiameter = diameter - 2 * borderWidth;
		int x = borderWidth;
		int y = borderWidth;
		graphics.setColor(backgroundColor);
		graphics.fillOval(x, y, innerDiameter, innerDiameter);

//       // Define the line coordinates
//
		int x1 = 100; // x-coordinate of the start point
		int y1 = 100; // y-coordinate of the start point
		int x2 = 15; // x-coordinate of the end point
		int y2 = 50; // y-coordinate of the end point

		float strokeWidth = 1.5f;
		graphics.setStroke(new BasicStroke(strokeWidth));

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x1, y1, x2, y2);

		int x11 = 15; // x-coordinate of the start point
		int y11 = 50; // y-coordinate of the start point
		int x22 = 185; // x-coordinate of the end point
		int y22 = 50; // y-coordinate of the end point

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x11, y11, x22, y22);

		int x111 = 100; // x-coordinate of the start point
		int y111 = 100; // y-coordinate of the start point
		int x222 = 100; // x-coordinate of the end point
		int y222 = 50; // y-coordinate of the end point

		// Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x111, y111, x222, y222);

		// Draw a label
		graphics.setColor(Color.RED); // Label color
		graphics.setFont(new Font("Times New Roman", Font.BOLD, 16)); // Label font

		// 3 variables
		graphics.drawString(label, 95, 115);
		graphics.drawString(label1, 2, 50);
		if(label2.equals("W")) {
			graphics.drawString(label2, 185, 50);
		}else {
			graphics.drawString(label2, 187, 50);
		}
		
		
		//2 values
		graphics.drawString(label4, 23, 90);
		graphics.drawString(label3, 105, 80);
		
		//top center variable
		graphics.drawString(label5, 95, 43);

		// Dispose the graphics object to release resources
		graphics.dispose();

		// Save the image to file
		try {
			File outputFile = new File(outputFileName);
			ImageIO.write(image, "png", outputFile);
			System.out.println("Circle image with border generated successfully.");
		} catch (IOException e) {
			System.err.println("Error while generating circle image with border: " + e.getMessage());
		}
		return obj;

	}

	@SuppressWarnings("unchecked")
	public static JSONObject generateCircleImageWithBorder5(int diameter, int borderWidth, Color backgroundColor,
			Color borderColor, String outputFileName, int que) {

		JSONObject obj = new JSONObject();
		String lables[] = new String[3];
		if (que <= 90) {
			lables = lablesTriplte();
		} else {
			lables = lablesTriplte2();
		}
		int values[] = valuesTriplate();

		int hypoteneous = values[2];
		int height = values[0];

		// BufferedImage image = new BufferedImage(diameter, diameter, height);
		BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB); // Explicitly specify
																									// image type
		Graphics2D graphics = image.createGraphics();
		// Enable anti-aliasing for smooth edges
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int radiusLabel = hypoteneous / 2;

		String label = "O";
		String label1 = lables[0];
		String label2 = lables[2];
		String label3 = " " + height + " cm ";
		String label4 = " " + hypoteneous + " cm ";
		String label5 = lables[1];

		obj.put("length", height);
		obj.put("radius", hypoteneous);
		obj.put("center", label);
		obj.put("chordFirstPoint", label1);
		obj.put("chordSecondPoint", label2);
		obj.put("RadiusLabel", label3);
		obj.put("chordLabel", label4);
		obj.put("center1", label5);
		// System.out.print(obj);

		graphics.setComposite(AlphaComposite.Clear);
		 graphics.fillRect(0, 0, 400, 300);
	        graphics.setComposite(AlphaComposite.Src);

	        
//		graphics.setColor(backgroundColor);
//		graphics.fillRect(0, 0, 400, 400);

		// Draw the outer circle (border)
		graphics.setColor(borderColor);
		graphics.fillOval(0, 0, diameter, diameter);

		int innerDiameter = diameter - 2 * borderWidth;
		int x = borderWidth;
		int y = borderWidth;
		graphics.setColor(backgroundColor);
		graphics.fillOval(x, y, innerDiameter, innerDiameter);

//       // Define the line coordinates
//
		int x1 = 100; // x-coordinate of the start point
		int y1 = 100; // y-coordinate of the start point
		int x2 = 50; // x-coordinate of the end point
		int y2 = 185; // y-coordinate of the end point

		float strokeWidth = 1.5f;
		graphics.setStroke(new BasicStroke(strokeWidth));

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x1, y1, x2, y2);

		int x11 = 50; // x-coordinate of the start point
		int y11 = 13; // y-coordinate of the start point
		int x22 = 50; // x-coordinate of the end point
		int y22 = 185; // y-coordinate of the end point

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x11, y11, x22, y22);

		int x111 = 100; // x-coordinate of the start point
		int y111 = 100; // y-coordinate of the start point
		int x222 = 50; // x-coordinate of the end point
		int y222 = 100; // y-coordinate of the end point

		// Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x111, y111, x222, y222);

		// Draw a label
		graphics.setColor(Color.RED); // Label color
		graphics.setFont(new Font("Times New Roman", Font.BOLD, 16)); // Label font

		// 3 variables
		graphics.drawString(label, 105, 105);
		graphics.drawString(label2, 35, 15);
		graphics.drawString(label1, 35, 195);
		
		//2 values
		graphics.drawString(label3, 60, 90);
		graphics.drawString(label4, 80, 150);
		
		//top center variable
		graphics.drawString(label5, 35, 105);

		// Dispose the graphics object to release resources
		graphics.dispose();

		// Save the image to file
		try {
			File outputFile = new File(outputFileName);
			ImageIO.write(image, "png", outputFile);
			System.out.println("Circle image with border generated successfully.");
		} catch (IOException e) {
			System.err.println("Error while generating circle image with border: " + e.getMessage());
		}
		return obj;

	}

	@SuppressWarnings("unchecked")
	public static JSONObject generateCircleImageWithBorder6(int diameter, int borderWidth, Color backgroundColor,
			Color borderColor, String outputFileName, int que) {

		JSONObject obj = new JSONObject();
		String lables[] = new String[3];
		if (que <= 90) {
			lables = lablesTriplte();
		} else {
			lables = lablesTriplte2();
		}
		int values[] = valuesTriplate();

		int hypoteneous = values[2];
		int height = values[0];

		// BufferedImage image = new BufferedImage(diameter, diameter, height);
		BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB); // Explicitly specify
																									// image type
		Graphics2D graphics = image.createGraphics();
		// Enable anti-aliasing for smooth edges
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int radiusLabel = hypoteneous / 2;

		String label = "O";
		String label1 = lables[0];
		String label2 = lables[2];
		String label3 = " " + height + " cm ";
		String label4 = " " + hypoteneous + " cm ";
		String label5 = lables[1];

		obj.put("length", height);
		obj.put("radius", hypoteneous);
		obj.put("center", label);
		obj.put("chordFirstPoint", label1);
		obj.put("chordSecondPoint", label2);
		obj.put("RadiusLabel", label3);
		obj.put("chordLabel", label4);
		obj.put("center1", label5);
		// System.out.print(obj);

		graphics.setComposite(AlphaComposite.Clear);
		 graphics.fillRect(0, 0, 400, 300);
	        graphics.setComposite(AlphaComposite.Src);

	        
//		graphics.setColor(backgroundColor);
//		graphics.fillRect(0, 0, 400, 400);

		// Draw the outer circle (border)
		graphics.setColor(borderColor);
		graphics.fillOval(0, 0, diameter, diameter);

		int innerDiameter = diameter - 2 * borderWidth;
		int x = borderWidth;
		int y = borderWidth;
		graphics.setColor(backgroundColor);
		graphics.fillOval(x, y, innerDiameter, innerDiameter);

//       // Define the line coordinates
//
		int x1 = 100; // x-coordinate of the start point
		int y1 = 100; // y-coordinate of the start point
		int x2 = 50; // x-coordinate of the end point
		int y2 = 13; // y-coordinate of the end point

		float strokeWidth = 1.5f;
		graphics.setStroke(new BasicStroke(strokeWidth));

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x1, y1, x2, y2);

		int x11 = 50; // x-coordinate of the start point
		int y11 = 13; // y-coordinate of the start point
		int x22 = 50; // x-coordinate of the end point
		int y22 = 185; // y-coordinate of the end point

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x11, y11, x22, y22);

		int x111 = 100; // x-coordinate of the start point
		int y111 = 100; // y-coordinate of the start point
		int x222 = 50; // x-coordinate of the end point
		int y222 = 100; // y-coordinate of the end point

		// Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x111, y111, x222, y222);

		// Draw a label
		graphics.setColor(Color.RED); // Label color
		graphics.setFont(new Font("Times New Roman", Font.BOLD, 16)); // Label font

		// 3 variables
		graphics.drawString(label, 105, 105);
		graphics.drawString(label1, 35, 15);
		graphics.drawString(label2, 35, 195);
		
		//2 values
		graphics.drawString(label3, 60, 115);
		graphics.drawString(label4, 75, 60);
		
		//top center variable
		graphics.drawString(label5, 35, 105);

		// Dispose the graphics object to release resources
		graphics.dispose();

		// Save the image to file
		try {
			File outputFile = new File(outputFileName);
			ImageIO.write(image, "png", outputFile);
			System.out.println("Circle image with border generated successfully.");
		} catch (IOException e) {
			System.err.println("Error while generating circle image with border: " + e.getMessage());
		}
		return obj;

	}
	
	public static JSONObject generateCircleImageWithBorder7(int diameter, int borderWidth, Color backgroundColor,
			Color borderColor, String outputFileName, int que) {

		JSONObject obj = new JSONObject();
		String lables[] = new String[3];
		if (que <= 90) {
			lables = lablesTriplte();
		} else {
			lables = lablesTriplte2();
		}
		int values[] = valuesTriplate();

		int hypoteneous = values[2];
		int height = values[0];

		// BufferedImage image = new BufferedImage(diameter, diameter, height);
		BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB); // Explicitly specify
																									// image type
		Graphics2D graphics = image.createGraphics();
		// Enable anti-aliasing for smooth edges
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int radiusLabel = hypoteneous / 2;

		String label = "O";
		String label1 = lables[0];
		String label2 = lables[2];
		String label3 = " " + height + " cm ";
		String label4 = " " + hypoteneous + " cm ";
		String label5 = lables[1];

		obj.put("length", height);
		obj.put("radius", hypoteneous);
		obj.put("center", label);
		obj.put("chordFirstPoint", label2);
		obj.put("chordSecondPoint", label1);
		obj.put("RadiusLabel", label3);
		obj.put("chordLabel", label4);
		obj.put("center1", label5);
		// System.out.print(obj);

		graphics.setComposite(AlphaComposite.Clear);
		 graphics.fillRect(0, 0, 400, 300);
	        graphics.setComposite(AlphaComposite.Src);

		// Draw the outer circle (border)
		graphics.setColor(borderColor);
		graphics.fillOval(0, 0, diameter, diameter);

		int innerDiameter = diameter - 2 * borderWidth;
		int x = borderWidth;
		int y = borderWidth;
		graphics.setColor(backgroundColor);
		graphics.fillOval(x, y, innerDiameter, innerDiameter);

//       // Define the line coordinates
//
		int x1 = 100; // x-coordinate of the start point
		int y1 = 100; // y-coordinate of the start point
		int x2 = 150; // x-coordinate of the end point
		int y2 = 100; // y-coordinate of the end point

		float strokeWidth = 1.5f;
		graphics.setStroke(new BasicStroke(strokeWidth));

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x1, y1, x2, y2);

		int x11 = 150; // x-coordinate of the start point
		int y11 = 15; // y-coordinate of the start point
		int x22 = 150; // x-coordinate of the end point
		int y22 = 185; // y-coordinate of the end point

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x11, y11, x22, y22);
//
		int x111 = 100; // x-coordinate of the start point
		int y111 = 100; // y-coordinate of the start point
		int x222 = 150; // x-coordinate of the end point
		int y222 = 185; // y-coordinate of the end point

		// Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x111, y111, x222, y222);

		// Draw a label
		graphics.setColor(Color.RED); // Label color
		graphics.setFont(new Font("Times New Roman", Font.BOLD, 16)); // Label font

		// 3 variables
		graphics.drawString(label, 87, 100);
		graphics.drawString(label1, 150, 13);
		graphics.drawString(label2, 153, 196);
		
		//2 values
		graphics.drawString(label4, 80, 150);
		graphics.drawString(label3, 105, 95);
		
		//top center variable
		graphics.drawString(label5, 155, 105);

		// Dispose the graphics object to release resources
		graphics.dispose();

		// Save the image to file
		try {
			File outputFile = new File(outputFileName);
			ImageIO.write(image, "png", outputFile);
			
			System.out.println("Circle image with border generated successfully.");
		} catch (IOException e) {
			System.err.println("Error while generating circle image with border: " + e.getMessage());
		}
		return obj;

	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject generateCircleImageWithBorder8(int diameter, int borderWidth, Color backgroundColor,
			Color borderColor, String outputFileName, int que) {

		JSONObject obj = new JSONObject();
		String lables[] = new String[3];
		if (que <= 90) {
			lables = lablesTriplte();
		} else {
			lables = lablesTriplte2();
		}
		int values[] = valuesTriplate();

		int hypoteneous = values[2];
		int height = values[0];

		// BufferedImage image = new BufferedImage(diameter, diameter, height);
		BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB); // Explicitly specify
																									// image type
		Graphics2D graphics = image.createGraphics();
		// Enable anti-aliasing for smooth edges
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int radiusLabel = hypoteneous / 2;

		String label = "O";
		String label1 = lables[0];
		String label2 = lables[2];
		String label3 = " " + height + " cm ";
		String label4 = " " + hypoteneous + " cm ";
		String label5 = lables[1];

		obj.put("length", height);
		obj.put("radius", hypoteneous);
		obj.put("center", label);
		obj.put("chordFirstPoint", label1);
		obj.put("chordSecondPoint", label2);
		obj.put("RadiusLabel", label3);
		obj.put("chordLabel", label4);
		obj.put("center1", label5);
		// System.out.print(obj);

		graphics.setComposite(AlphaComposite.Clear);
		 graphics.fillRect(0, 0, 400, 300);
	        graphics.setComposite(AlphaComposite.Src);

	        
//		graphics.setColor(backgroundColor);
//		graphics.fillRect(0, 0, 400, 400);

		// Draw the outer circle (border)
		graphics.setColor(borderColor);
		graphics.fillOval(0, 0, diameter, diameter);

		int innerDiameter = diameter - 2 * borderWidth;
		int x = borderWidth;
		int y = borderWidth;
		graphics.setColor(backgroundColor);
		graphics.fillOval(x, y, innerDiameter, innerDiameter);

//       // Define the line coordinates
//
		int x1 = 100; // x-coordinate of the start point
		int y1 = 100; // y-coordinate of the start point
		int x2 = 150; // x-coordinate of the end point
		int y2 = 100; // y-coordinate of the end point

		float strokeWidth = 1.5f;
		graphics.setStroke(new BasicStroke(strokeWidth));

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x1, y1, x2, y2);

		int x11 = 150; // x-coordinate of the start point
		int y11 = 15; // y-coordinate of the start point
		int x22 = 150; // x-coordinate of the end point
		int y22 = 185; // y-coordinate of the end point

//       // Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x11, y11, x22, y22);
//
		int x111 = 100; // x-coordinate of the start point
		int y111 = 100; // y-coordinate of the start point
		int x222 = 150; // x-coordinate of the end point
		int y222 = 15; // y-coordinate of the end point

		// Draw the line
		graphics.setColor(borderColor);
		graphics.drawLine(x111, y111, x222, y222);

		// Draw a label
		graphics.setColor(Color.RED); // Label color
		graphics.setFont(new Font("Times New Roman", Font.BOLD, 16)); // Label font

		// 3 variables
		graphics.drawString(label, 87, 105);
		graphics.drawString(label1, 150, 13);
		graphics.drawString(label2, 153, 196);
		
		//2 values
		graphics.drawString(label4, 78, 60);
		graphics.drawString(label3, 105, 115);
		
		//top center variable
		graphics.drawString(label5, 155, 105);

		// Dispose the graphics object to release resources
		graphics.dispose();

		// Save the image to file
		try {
			File outputFile = new File(outputFileName);
			ImageIO.write(image, "png", outputFile);
			System.out.println("Circle image with border generated successfully.");
		} catch (IOException e) {
			System.err.println("Error while generating circle image with border: " + e.getMessage());
		}
		return obj;

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
