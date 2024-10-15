package ac.in.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


import javax.imageio.ImageIO;

public class Final_Triangle extends JPanel {

    private static final String OUTPUT_FOLDER = "E:\\Komal Divase\\Circles1\\"; // Change this to your local folder

    // Generate a random point around the center of the panel
    public int[] generateRandomPointAroundCenter(int centerX, int centerY, int range) {
        Random rand = new Random();
        int x = centerX + rand.nextInt(range) - range / 2;
        int y = centerY + rand.nextInt(range) - range / 2;
        return new int[]{x, y};
    }

    // Calculate the distance between two points
    public double calculateDistance(int[] p1, int[] p2) {
        return Math.sqrt(Math.pow(p2[0] - p1[0], 2) + Math.pow(p2[1] - p1[1], 2));
    }

    // Calculate the angle between two vectors (in degrees)
    public double calculateAngle(int[] p1, int[] p2, int[] p3) {
        double dotProduct = (p2[0] - p1[0]) * (p3[0] - p1[0]) + (p2[1] - p1[1]) * (p3[1] - p1[1]);
        double magnitudeP1P2 = calculateDistance(p1, p2);
        double magnitudeP1P3 = calculateDistance(p1, p3);
        double cosTheta = dotProduct / (magnitudeP1P2 * magnitudeP1P3);
        double theta = Math.acos(cosTheta); // In radians
        return Math.toDegrees(theta); // Convert to degrees
    }

    // Check if all angles in the triangle are greater than or equal to 30 degrees
    public boolean isValidTriangle(int[] p1, int[] p2, int[] p3) {
        double angle1 = calculateAngle(p1, p2, p3); // Angle at p1
        double angle2 = calculateAngle(p2, p1, p3); // Angle at p2
        double angle3 = calculateAngle(p3, p1, p2); // Angle at p3

        // Check if all angles are greater than or equal to 30 degrees
        boolean allAnglesAtLeast30 = (angle1 > 30 && angle2 > 30 && angle3 > 30);

        // Check if none of the angles are exactly 60 or 90 degrees
        boolean noAngles60Or90 = !(Math.abs(angle1 - 60) < 1e-6 || Math.abs(angle1 - 90) < 1e-6 ||
                                   Math.abs(angle2 - 60) < 1e-6 || Math.abs(angle2 - 90) < 1e-6 ||
                                   Math.abs(angle3 - 60) < 1e-6 || Math.abs(angle3 - 90) < 1e-6);

        return allAnglesAtLeast30 && noAngles60Or90;
    }


    // Check if three points are collinear
    public boolean areCollinear(int[] p1, int[] p2, int[] p3) {
        return (p1[0] * (p2[1] - p3[1]) + p2[0] * (p3[1] - p1[1]) + p3[0] * (p1[1] - p2[1])) == 0;
    }

    // Generate a valid third point for a triangle
 
 // Generate a valid third point for a triangle
    public int[] generateValidThirdPoint(int[] p1, int[] p2, int centerX, int centerY, int range) {
        Random rand = new Random();
        int[] p3 = new int[2]; // Initialize p3

        double distanceP1P2 = calculateDistance(p1, p2);
        
        // New criteria for distances based on your input
        double p1p2Min = 0.5 * distanceP1P2;  // 50% of p1p2
        double p1p2Max = 0.8 * distanceP1P2;  // 80% of p1p2
        double p1p3Min = 1.5 * distanceP1P2;  // 150% of p1p2
        double p1p3Max = 1.8 * distanceP1P2;  // 180% of p1p2
        double distanceP2P3Min = distanceP1P2 + 20; // p2p3 should be at least 20 units more than p1p2

        double distanceP1P3, distanceP2P3;

        int maxAttempts = 1000;
        int attempts = 0;

        do {
            if (attempts++ > maxAttempts) {
                System.out.println("Unable to find a valid point after " + maxAttempts + " attempts.");
                break;
            }

            // Generate a random point for p3
            p3 = generateRandomPointAroundCenter(centerX, centerY, range);
            distanceP1P3 = calculateDistance(p1, p3);
            distanceP2P3 = calculateDistance(p2, p3);

            // Log the attempts for debugging purposes
            System.out.println("Attempt #" + attempts + ": p1=" + p1[0] + "," + p1[1] +
                    " p2=" + p2[0] + "," + p2[1] + " p3=" + p3[0] + "," + p3[1] +
                    " d(p1, p3)=" + distanceP1P3 + " d(p2, p3)=" + distanceP2P3);

        } while (areCollinear(p1, p2, p3) || distanceP2P3 < p1p2Min || distanceP2P3 > p1p2Max || 
                 distanceP1P3 < p1p3Min || distanceP1P3 > p1p3Max || distanceP2P3 < distanceP1P2 + 20 || 
                 distanceP1P2 == distanceP1P3 || distanceP1P2 == distanceP2P3 || 
                 distanceP1P3 == distanceP2P3 || !isValidTriangle(p1, p2, p3));

        return p3;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Get the width and height of the panel and calculate the center
        int maxWidth = getWidth();
        int maxHeight = getHeight();
        int centerX = maxWidth / 2;
        int centerY = maxHeight / 2;

        // Increase the range to half of the panel size
        int range = Math.min(maxWidth, maxHeight) / 2; 

        // Generate two random points around the center and ensure they are between 80 and 300 pixels apart
        int[] p1, p2;
        double distanceP1P2;
        do {
            p1 = generateRandomPointAroundCenter(centerX, centerY, range);
            p2 = generateRandomPointAroundCenter(centerX, centerY, range);
            distanceP1P2 = calculateDistance(p1, p2);
        } while (!(distanceP1P2 > 80 && distanceP1P2 < 300)); // Ensure valid distance between p1 and p2

        // Generate a third point such that the points are not collinear, distances are valid, and angles are valid
        int[] p3 = generateValidThirdPoint(p1, p2, centerX, centerY, range);

        // Draw the triangle
        g.setColor(Color.BLUE);
        g.drawLine(p1[0], p1[1], p2[0], p2[1]);
        g.drawLine(p2[0], p2[1], p3[0], p3[1]);
        g.drawLine(p3[0], p3[1], p1[0], p1[1]);

        // Mark the points
        g.setColor(Color.RED);
        g.fillOval(p1[0] - 5, p1[1] - 5, 10, 10); // Point 1
        g.fillOval(p2[0] - 5, p2[1] - 5, 10, 10); // Point 2
        g.setColor(Color.BLACK);
        g.fillOval(p3[0] - 5, p3[1] - 5, 10, 10); // Point 3

        // Set the font for the labels
        g.setFont(new Font("Arial", Font.BOLD, 12));

        // Draw the labels for points with coordinates
        g.drawString("P1 (" + p1[0] + ", " + p1[1] + ")", p1[0] + 10, p1[1] - 10);
        g.drawString("P2 (" + p2[0] + ", " + p2[1] + ")", p2[0] + 10, p2[1] - 10);
        g.drawString("P3 (" + p3[0] + ", " + p3[1] + ")", p3[0] + 10, p3[1] - 10);

        // Draw the distances
        g.drawString(String.format("%d", (int) distanceP1P2), (p1[0] + p2[0]) / 2, (p1[1] + p2[1]) / 2 - 10);
        g.drawString(String.format("%d", (int) calculateDistance(p2, p3)), (p2[0] + p3[0]) / 2, (p2[1] + p3[1]) / 2 - 10);
        g.drawString(String.format("%d", (int) calculateDistance(p1, p3)), (p1[0] + p3[0]) / 2, (p1[1] + p3[1]) / 2 - 10);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of triangles you want to generate: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            JFrame frame = new JFrame();
            Final_Triangle panel = new Final_Triangle();
            frame.add(panel);
            frame.setSize(800, 600); // Set window size
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            // Save the panel as an image
            BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            panel.paint(g2d);
            g2d.dispose();

            File outputFile = new File(OUTPUT_FOLDER + "triangle_" + i + ".png");
            try {
                ImageIO.write(image, "png", outputFile);
                System.out.println("Triangle " + (i + 1) + " saved as " + outputFile.getPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
