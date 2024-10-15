	package ac.in.Task;

	import javax.imageio.ImageIO;
	import java.awt.*;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.IOException;
	import java.util.Random;
	import java.util.Scanner;
	import javax.swing.*;

	public class Quadrilateral_Inscribed_Circle extends JPanel {
	    private int radius;
	    private Point center;

	    // Constructor to initialize the circle's radius and center
	    public Quadrilateral_Inscribed_Circle(int radius, Point center) {
	        this.radius = radius;
	        this.center = center;
	    }

	    // Method to calculate a point on the circumference given an angle in degrees
	    private Point getPointOnCircumference(int angle) {
	        double radian = Math.toRadians(angle);
	        int x = (int) (center.x + radius * Math.cos(radian));
	        int y = (int) (center.y + radius * Math.sin(radian));
	        return new Point(x, y);
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setColor(Color.BLACK);
	        int diameter = radius * 2;
	        int topLeftX = center.x - radius;
	        int topLeftY = center.y - radius;
	        g2d.drawOval(topLeftX, topLeftY, diameter, diameter);

	        // Set color for the chords of the rhombus
	        g2d.setColor(Color.RED);

	        // Generate a random starting angle
	        Random random = new Random();
	        int startingAngle = random.nextInt(360); // Random angle between 0 and 359

	        // Calculate four angles for the rhombus (90 degrees apart)
	        int angle1 = startingAngle;
	        int angle2 = (angle1 + 90) % 360;
	        int angle3 = (angle2 + 90) % 360;
	        int angle4 = (angle3 + 90) % 360;

	        // Calculate points on the circumference
	        Point p1 = getPointOnCircumference(angle1);
	        Point p2 = getPointOnCircumference(angle2);
	        Point p3 = getPointOnCircumference(angle3);
	        Point p4 = getPointOnCircumference(angle4);

	        // Draw the rhombus
	        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
	        g2d.drawLine(p2.x, p2.y, p3.x, p3.y);
	        g2d.drawLine(p3.x, p3.y, p4.x, p4.y);
	        g2d.drawLine(p4.x, p4.y, p1.x, p1.y);

	        // Set color for the triangle sides
	        g2d.setColor(Color.BLUE);

	        // Draw lines from the center to points on the rhombus
	        g2d.drawLine(center.x, center.y, p1.x, p1.y);
	        g2d.drawLine(center.x, center.y, p2.x, p2.y);
//	        g2d.drawLine(center.x, center.y, p3.x, p3.y);
//	        g2d.drawLine(center.x, center.y, p4.x, p4.y);

	        // Set color for the height of the triangle
	        g2d.setColor(Color.GREEN);
	        
	        // Draw the height from the center to one side of the rhombus
//	        g2d.drawLine(center.x, center.y, (p1.x + p2.x) / 2, (p1.y + p2.y) / 2); // Height to the line between p1 and p2

	        // Draw the line connecting the midpoint of the base to the center to form the height line
	        g2d.setColor(Color.BLUE);
//	        g2d.drawLine(center.x, center.y, (p3.x + p4.x) / 2, (p3.y + p4.y) / 2); // Height to the line between p3 and p4
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        // Get the number of circles from the user
	        System.out.print("Enter the number of circles to generate: ");
	        int numberOfCircles = scanner.nextInt();

	        // Define the range for the radius
	        int minRadius = 50;
	        int maxRadius = 150;

	        // Define the folder to save images
	        File outputFolder = new File("E:\\Komal Divase\\Circles1");
	        if (!outputFolder.exists()) {
	            outputFolder.mkdir(); // Create the folder if it doesn't exist
	        }

	        Random random = new Random();
	     

	        for (int i = 0; i < numberOfCircles; i++) {
	            // Generate a random radius within the specified range
	            int radius = random.nextInt(maxRadius - minRadius + 1) + minRadius;

	            // Calculate circle's center position to be in the center of the screen
	            Point center = new Point(500, 500); // Set a fixed center for simplicity

	            // Creating a JPanel to draw our circle
	            Quadrilateral_Inscribed_Circle circlePanel = new Quadrilateral_Inscribed_Circle(radius, center);

	            // Create a BufferedImage and Graphics2D for rendering the JPanel
	            BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
	            Graphics2D g2d = image.createGraphics();
	            
	            // Set a background color if needed
	            g2d.setBackground(Color.WHITE);
	            g2d.clearRect(0, 0, image.getWidth(), image.getHeight());
	            
	            // Draw the JPanel content onto the BufferedImage
	            circlePanel.paintComponent(g2d);
	            g2d.dispose();

	            // Define the output file path
	            File outputFile = new File(outputFolder, "Circle_" + i + ".png");

	            // Save the image file
	            try {
	                ImageIO.write(image, "png", outputFile);
	                System.out.println("Image saved successfully: " + outputFile.getPath());
	            } catch (IOException e) {
	                System.err.println("Error saving image: " + e.getMessage());
	            }
	        }

	        scanner.close();
	    }
	}


