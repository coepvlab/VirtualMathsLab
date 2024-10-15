package ac.in.circle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.Random;

public class Solution extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g;

	    // Set rendering hints for smooth drawing
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	    // Circle center
	    int centerX = 200;
	    int centerY = 200;
	    int radius = 100;

	    // Draw circle
	    g2d.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

	    // Points A, B, C, D
	    Point A = new Point(centerX, centerY - radius);
	    Point B = new Point(centerX - 88, centerY + 50);
	    Point C = new Point(centerX + 88, centerY + 50);
	    Point D = new Point(centerX, centerY + radius);

	    // Draw solid lines
	    g2d.setColor(Color.RED);
	    g2d.drawLine(centerX, centerY, B.x, B.y); // O to B
	    g2d.drawLine(centerX, centerY, C.x, C.y); // O to C
	    g2d.drawLine(B.x, B.y, A.x, A.y); // B to A
	    g2d.drawLine(A.x, A.y, C.x, C.y); // A to C
	    g2d.setColor(Color.BLUE);
	    g2d.drawLine(B.x, B.y, D.x, D.y); // B to D
	    g2d.drawLine(C.x, C.y, D.x, D.y); // C to D

	    // Draw dotted line from O to A
	    g2d.setColor(Color.BLACK);
	    float[] dashPattern = {5, 5}; // Pattern for dashed line (length of dashes and spaces)
	    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
	    g2d.drawLine(centerX, centerY, A.x, A.y);

	    // Reset stroke back to solid
	    g2d.setStroke(new BasicStroke());

	    // Draw labels
	    g2d.setColor(Color.BLACK);
	    g2d.drawString("A", A.x - 3, A.y - 5);
	    g2d.drawString("B", B.x - 15, B.y + 15);
	    g2d.drawString("C", C.x + 5, C.y + 15);
	    g2d.drawString("D", D.x - 5, D.y + 20);
	    g2d.drawString("O", centerX - 15, centerY + 2);

	    
	    Random random=new Random();
	    int angle=(int)(Math.random()*(40-20+1)+20);
	    // Draw angle symbols with labels
//	    drawAngleSymbol(g2d, C.x - 25, C.y - 20, 20,"20°"); // Adjust positions as needed
//	    drawAngleSymbol(g2d, B.x + 22, B.y - 19, 20, "x°"); // Adjust positions as needed
	    
	    drawAngleSymbol(g2d, B.x + 22, B.y - 19, 18, "x°", 15); // Right position
	    drawAngleSymbol(g2d, C.x - 25, C.y - 20, 19, ""+angle+"\u00B0", -25); // Left position
	   

	}


    // Helper method to draw angles
	
	
	private void drawAngleSymbol(Graphics2D g2d, int x, int y, int angleSize, String labelAbove, int xOffset) {
	    g2d.setColor(Color.BLACK);
	    
	    // Draw arc representing the angle
	    g2d.drawArc(x - angleSize / 2, y - angleSize / 2, angleSize, angleSize, 0, 180);
	    
	    // Set font metrics to calculate the width of the label
	    FontMetrics fm = g2d.getFontMetrics();
	    int labelWidth = fm.stringWidth(labelAbove);
	    
	    // Draw the label above the angle symbol
	    int labelX = x + xOffset - labelWidth / 4; // Adjust X position for centering label horizontally
	    int labelY = y - angleSize / 2 - 6; // Position the label above the arc
	    g2d.drawString(labelAbove, labelX, labelY);
	}


 
    // Main method to run the JFrame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Circle Diagram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new Solution());
        frame.setVisible(true);
    }
    
    
}
