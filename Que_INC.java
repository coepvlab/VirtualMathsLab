package Quad_Shape;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Que_INC extends JPanel {
    private int radius;
    private Point center;
    private final Point[] circumferencePoints;
    private Point[] selectedPoints;
    private final String[] pointLabels = {"A", "B", "C", "D"};

    public Que_INC(int radius, Point center, int numPoints) {
        this.radius = radius;
        this.center = center;
        this.circumferencePoints = new Point[numPoints];
        this.selectedPoints = new Point[4];
        calculateCircumferencePoints(numPoints);
        selectValidChordPoints();
    }

    private void calculateCircumferencePoints(int numPoints) {
        for (int i = 0; i < numPoints; i++) {
            double angle = 2 * Math.PI * i / numPoints;
            int x = (int) (center.x + radius * Math.cos(angle));
            int y = (int) (center.y + radius * Math.sin(angle));
            circumferencePoints[i] = new Point(x, y);
        }
    }

    private void selectValidChordPoints() {
        Random random = new Random();
        int diameter = 2 * radius;
        double maxChordLength = 0.75 * diameter;
        double minChordLength = 0.30 * diameter;

        while (true) {
            for (int i = 0; i < 4; i++) {
                selectedPoints[i] = circumferencePoints[random.nextInt(circumferencePoints.length)];
            }

            Arrays.sort(selectedPoints, (p1, p2) -> {
                double angle1 = Math.atan2(p1.y - center.y, p1.x - center.x);
                double angle2 = Math.atan2(p2.y - center.y, p2.x - center.x);
                return Double.compare(angle1, angle2);
            });

            double chord1Length = distance(selectedPoints[0], selectedPoints[1]);
            double chord2Length = distance(selectedPoints[1], selectedPoints[2]);
            double chord3Length = distance(selectedPoints[2], selectedPoints[3]);
            double chord4Length = distance(selectedPoints[3], selectedPoints[0]);

            if (chord1Length <= maxChordLength && chord1Length >= minChordLength &&
                chord2Length <= maxChordLength && chord2Length >= minChordLength &&
                chord3Length <= maxChordLength && chord3Length >= minChordLength &&
                chord4Length <= maxChordLength && chord4Length >= minChordLength) {
                break;
            }
        }
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        drawCircle(g);
        highlightChordPoints(g);
        drawChords(g);
        drawAngleArc(g); // Draw the angle arc for ∠DOC
    }

    
    
    private void drawCircle(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);
    }

    private void highlightChordPoints(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < selectedPoints.length; i++) {
            Point p = selectedPoints[i];
            double angle = Math.atan2(p.y - center.y, p.x - center.x);
            int labelX = (int) (p.x + 15 * Math.cos(angle));
            int labelY = (int) (p.y + 15 * Math.sin(angle));
            g.drawString(pointLabels[i], labelX, labelY);
        }
        g.drawString("O", center.x, center.y);
    }

    private void drawChords(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(selectedPoints[0].x, selectedPoints[0].y, selectedPoints[1].x, selectedPoints[1].y);
        g.drawLine(selectedPoints[1].x, selectedPoints[1].y, selectedPoints[2].x, selectedPoints[2].y);
        g.drawLine(selectedPoints[2].x, selectedPoints[2].y, selectedPoints[3].x, selectedPoints[3].y);
        g.drawLine(selectedPoints[3].x, selectedPoints[3].y, selectedPoints[0].x, selectedPoints[0].y);
        g.drawLine(center.x, center.y, selectedPoints[2].x, selectedPoints[2].y);
        g.drawLine(center.x, center.y, selectedPoints[3].x, selectedPoints[3].y);
    }

    private void drawAngleArc(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);

        double angleC = Math.atan2(selectedPoints[2].y - center.y, selectedPoints[2].x - center.x);
        double angleD = Math.atan2(selectedPoints[3].y - center.y, selectedPoints[3].x - center.x);

        double startAngle = Math.toDegrees(angleC); 
        double extentAngle = Math.toDegrees(angleD - angleC); 

        // Normalize extentAngle to be within [0, 360) degrees
        if (extentAngle < 0) {
            extentAngle += 360;
        }

        // Ensure extentAngle is the smaller angle between the two points
        if (extentAngle > 180) {
            extentAngle = 360 - extentAngle;
            startAngle += extentAngle; // Adjust startAngle to maintain arc direction
        }

//        int arcRadius = 20; // Adjust as needed
//        g2d.draw(new Arc2D.Double(center.x - arcRadius, center.y - arcRadius, 
//                                   2 * arcRadius, 2 * arcRadius, 
//                                   startAngle -80, extentAngle -80, Arc2D.OPEN));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of images to generate: ");
        int numberOfCircles = scanner.nextInt();

        int minRadius = 100;
        int maxRadius = 200;
        File outputFolder = new File("E:\\Geometry\\Circles");
        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }

        Random random = new Random();

        for (int i = 0; i < numberOfCircles; i++) {
            int radius = random.nextInt(maxRadius - minRadius + 1) + minRadius;
            int numPoints = 100;
            int imageSize = 2 * (maxRadius + 50);
            Point center = new Point(imageSize / 2, imageSize / 2);

            BufferedImage bufferedImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, imageSize, imageSize);

            Que_INC circlePanel = new Que_INC(radius, center, numPoints);
            circlePanel.paintComponent(g2d);
            g2d.dispose();

            try {
                File outputfile = new File(outputFolder, "circle_" + i + ".png");
                ImageIO.write(bufferedImage, "png", outputfile);
                System.out.println("Image " + outputfile.getName() + " generated.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }
}



