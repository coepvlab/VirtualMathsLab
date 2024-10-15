package ac.in.geometory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Circle extends JPanel {

    private int radius;
    private int pointCount;
    private static final int POINT_RADIUS = 4; // Radius of the points
    private static final int SHAPE_POINTS = 4; // Number of points for the shapes

    public Circle(int radius, int pointCount) {
        this.radius = radius;
        this.pointCount = pointCount;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Draw the circle
//        g2d.setColor(Color.BLUE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

        // Set the color for the circumference points
        g2d.setColor(Color.RED);

        // Draw points on the circumference
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < pointCount; i++) {
            double angle = 2 * Math.PI * i / pointCount; // Angle in radians
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            points.add(new Point(x, y));

            // Draw a small circle (point) on the circumference
            g2d.fill(new Ellipse2D.Double(x - POINT_RADIUS, y - POINT_RADIUS, 2 * POINT_RADIUS, 2 * POINT_RADIUS)); // Point size
        }

        // Draw one shape
        Random random = new Random();
        g2d.setColor(Color.BLACK);
        Point[] shapePoints = getValidShapePoints(points, random);
        if (shapePoints != null) {
            // Draw the shape
            drawShape(g2d, shapePoints);

            // Set the font to bold
            Font boldFont = new Font("Arial", Font.BOLD, 12);
            g2d.setFont(boldFont);

            // Draw labels
            String[] labels = generateLabels();
            int labelOffset = 16; // Distance from the circle

            for (int i = 0; i < SHAPE_POINTS; i++) {
                Point p = shapePoints[i];
                String label = labels[i];

                // Calculate label position slightly outside the circle
                double angle = Math.atan2(p.y - centerY, p.x - centerX);
                int labelX = (int) (centerX + (radius + labelOffset) * Math.cos(angle));
                int labelY = (int) (centerY + (radius + labelOffset) * Math.sin(angle));

                // Draw label
                g2d.drawString(label, labelX, labelY);
            }
        }

        // Draw the title below the circle
        String titleEnglish = "Circle";
        String titleMarathi = "वर्तुळ";
        String separator = " / "; // Separator between titles

        // Use a font known to support Devanagari script
        Font titleFont = new Font("Arial Unicode MS", Font.BOLD, 16);
        g2d.setFont(titleFont);
        g2d.setColor(Color.BLACK);
        FontMetrics metrics = g2d.getFontMetrics(titleFont);

        // Calculate widths for titles and separator
        int englishWidth = metrics.stringWidth(titleEnglish);
        int marathiWidth = metrics.stringWidth(titleMarathi);
        int separatorWidth = metrics.stringWidth(separator);

        int totalWidth = englishWidth + separatorWidth + marathiWidth; // Total width including separator

        int titleX = centerX - totalWidth / 2; // Center the combined titles horizontally
        int titleY = centerY + radius + metrics.getHeight() + 20; // Position title below the circle

        // Draw English title
        g2d.drawString(titleEnglish, titleX, titleY);

        // Draw separator
        int separatorX = titleX + englishWidth;
        g2d.drawString(separator, separatorX, titleY);

        // Draw Marathi title next to separator
        int titleMarathiX = separatorX + separatorWidth;
        g2d.drawString(titleMarathi, titleMarathiX, titleY);
    }





    private Point[] getValidShapePoints(List<Point> points, Random random) {
        Point[] shapePoints;
        int attempts = 0;
        final int maxAttempts = 100; // Maximum attempts to find valid points

        do {
            shapePoints = getRandomFourPoints(points);
            attempts++;

            if (attempts > maxAttempts) {
                return null; // Return null if a valid shape cannot be found
            }

        } while (!isValidShape(shapePoints) || hasIntersectingLines(shapePoints));

        return shapePoints;
    }

    private Point[] getRandomFourPoints(List<Point> points) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < pointCount; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        Point[] shapePoints = new Point[SHAPE_POINTS];
        for (int i = 0; i < SHAPE_POINTS; i++) {
            shapePoints[i] = points.get(indices.get(i));
        }
        return shapePoints;
    }

    private boolean isValidShape(Point[] points) {
        // For simplicity, let's consider non-standard quadrilaterals like irregular quadrilaterals
        return true; // All shapes formed by four points on the circumference are considered valid for this example
    }

    private boolean hasIntersectingLines(Point[] points) {
        for (int i = 0; i < SHAPE_POINTS; i++) {
            for (int j = i + 1; j < SHAPE_POINTS; j++) {
                if (i != j) {
                    if (linesIntersect(points[i], points[(i + 1) % SHAPE_POINTS], points[j], points[(j + 1) % SHAPE_POINTS])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean linesIntersect(Point p1, Point p2, Point p3, Point p4) {
        double d1 = direction(p3, p4, p1);
        double d2 = direction(p3, p4, p2);
        double d3 = direction(p1, p2, p3);
        double d4 = direction(p1, p2, p4);

        return d1 * d2 < 0 && d3 * d4 < 0;
    }

    private double direction(Point pi, Point pj, Point pk) {
        return (pk.y - pi.y) * (pj.x - pi.x) - (pj.y - pi.y) * (pk.x - pi.x);
    }

    private void drawShape(Graphics2D g2d, Point[] shapePoints) {
        for (int i = 0; i < SHAPE_POINTS; i++) {
            Point p1 = shapePoints[i];
            Point p2 = shapePoints[(i + 1) % SHAPE_POINTS];
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
    private String[] generateLabels() {
        Random r = new Random();
        String[] var = new String[4];
        String[] Chr1 = { "A", "E", "K", "P", "S", "W" };
        String[] Chr2 = { "B", "F", "L", "Q", "T", "X" };
        String[] Chr3 = { "C", "G", "M", "R", "U", "Y" };
        String[] Chr4 = { "D", "H", "N", "S", "V", "Z" };

        int chi = r.nextInt(Chr1.length);

        String v1 = Chr1[chi];
        String v2 = Chr2[chi];
        String v3 = Chr3[chi];
        String v4 = Chr4[chi];
        var[0] = v1;
        var[1] = v2;
        var[2] = v3;
        var[3] = v4;

        return var;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Take input from the user for the number of images
        System.out.print("Enter the number of images to generate: ");
        int imageCount = scanner.nextInt();

        // Create a folder to save images
        File folder = new File("E:\\Komal Divase\\Circles1");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        for (int i = 0; i < imageCount; i++) {
//            int radius = 100 + random.nextInt(50); // Random radius between 100 and 150
            int radius = 100 ; 
//            int pointCount = 12 + random.nextInt(10); // Random point count between 12 and 21
            int pointCount = 10 ; // Random point count between 12 and 21

            Circle panel = new Circle(radius, pointCount);
            JFrame frame = new JFrame("Random Shapes");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.setSize(800, 600);
            frame.setVisible(true);

            // Save the image
            BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            panel.paintComponent(g2d);
            g2d.dispose();

            try {
                ImageIO.write(image, "png", new File(folder, "image_" + i + ".png"));
                System.out.println("Saved image successfully");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}
