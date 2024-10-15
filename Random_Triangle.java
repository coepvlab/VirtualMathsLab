package ac.in.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Random_Triangle extends JPanel {

    // Generate a random point around the center of the panel
    public int[] generateRandomPointAroundCenter(int centerX, int centerY, int range) {
        Random rand = new Random();
        int x = centerX + rand.nextInt(range) - range / 2;  // Random x-coordinate within the range
        int y = centerY + rand.nextInt(range) - range / 2;  // Random y-coordinate within the range
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
        double theta = Math.acos(cosTheta);  // In radians
        return Math.toDegrees(theta);  // Convert to degrees
    }

    // Check if all angles in the triangle are greater than or equal to 30 degrees
    public boolean isValidTriangle(int[] p1, int[] p2, int[] p3) {
        double angle1 = calculateAngle(p1, p2, p3); // Angle at p1
        double angle2 = calculateAngle(p2, p1, p3); // Angle at p2
        double angle3 = calculateAngle(p3, p1, p2); // Angle at p3

        return (angle1 >= 30 && angle2 >= 30 && angle3 >= 30);
    }

    // Check if three points are collinear by calculating the area of the triangle formed
    public boolean areCollinear(int[] p1, int[] p2, int[] p3) {
        return (p1[0] * (p2[1] - p3[1]) + p2[0] * (p3[1] - p1[1]) + p3[0] * (p1[1] - p2[1])) == 0;
    }

    // Ensure the points are not too close or too far apart, not collinear, and have valid angles
    public int[] generateValidThirdPoint(int[] p1, int[] p2, int centerX, int centerY, int range) {
        Random rand = new Random();
        int[] p3;
        double distanceP1P3, distanceP2P3;

        do {
            // Generate a random point for p3 around the center
            p3 = generateRandomPointAroundCenter(centerX, centerY, range);

            // Calculate distances from p3 to p1 and p2
            distanceP1P3 = calculateDistance(p1, p3);
            distanceP2P3 = calculateDistance(p2, p3);

            // Repeat if the points are collinear, if p3 is too close or too far, or the angles are invalid
        } while (areCollinear(p1, p2, p3) || distanceP1P3 < 100 || distanceP1P3 > 300 || distanceP2P3 < 100 || distanceP2P3 > 300 || !isValidTriangle(p1, p2, p3));

        return p3;
    }
    
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);

        // Get the width and height of the panel and calculate the center
        int maxWidth = getWidth();
        int maxHeight = getHeight();
        int centerX = maxWidth / 2;
        int centerY = maxHeight / 2;

        // Define a range around the center where points can be generated
        int range = Math.min(maxWidth, maxHeight) / 3; // Limit range to 1/3 of the panel size

        // Generate two random points around the center and ensure they are between 100 and 300 pixels apart
        int[] p1, p2;
        double distanceP1P2;
        do {
            p1 = generateRandomPointAroundCenter(centerX, centerY, range);
            p2 = generateRandomPointAroundCenter(centerX, centerY, range);
            distanceP1P2 = calculateDistance(p1, p2);
        } while (distanceP1P2 < 100 || distanceP1P2 > 300); // Ensure distance between p1 and p2 is valid

        // Generate a third point such that the points are not collinear, the distances are valid, and the angles are valid
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
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));

        // Adjust label positions based on vertex positions
        // Adding offsets to avoid overlap
        int labelOffsetX = 10;
        int labelOffsetY = 10;
        
        // Calculate offsets dynamically
        int p1LabelX = p1[0] + labelOffsetX;
        int p1LabelY = p1[1] - labelOffsetY;
        int p2LabelX = p2[0] + labelOffsetX;
        int p2LabelY = p2[1] - labelOffsetY;
        int p3LabelX = p3[0] + labelOffsetX;
        int p3LabelY = p3[1] - labelOffsetY;

        // Draw the labels
        g.drawString("P1", p1LabelX, p1LabelY);
        g.drawString("P2", p2LabelX, p2LabelY);
        g.drawString("P3", p3LabelX, p3LabelY);
        
        // Print the coordinates of the points
//        System.out.println("Point 1: (" + p1[0] + ", " + p1[1] + ")");
//        System.out.println("Point 2: (" + p2[0] + ", " + p2[1] + ")");
//        System.out.println("Point 3: (" + p3[0] + ", " + p3[1] + ")");
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Random_Triangle trianglePanel = new Random_Triangle();
        frame.add(trianglePanel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
