package ac.in.graphics;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;

public class Isosceles_Triangle extends Frame {

   public Isosceles_Triangle() {
      super("Java AWT Examples");
      prepareGUI();
   }

   public static void main(String[] args) {
      Isosceles_Triangle awtGraphicsDemo = new Isosceles_Triangle();
      awtGraphicsDemo.setVisible(true);
   }

   private void prepareGUI() {
      setSize(1000, 1000);
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent) {
            System.exit(0);
         }
      });
   }

   @Override
   public void paint(Graphics g) {
      // Randomly generate the triangle dimensions
      Random random = new Random();
      
//      int centerX = random.nextInt(500 - 200 + 1) + 200;  // Random center for the triangle
//      int centerY = random.nextInt(400 - 150 + 1) + 150;
//      int baseLength = random.nextInt(300 - 100 + 1) + 100;  // Random base length
//      int sideLength = random.nextInt(300 - 150 + 1) + 150;  // Equal side lengths
      
      int centerX =  500;  // Random center for the triangle
      int centerY =  400;
      int baseLength = 300;  // Random base length
      int sideLength = 300;  // Equal side lengths

      // Calculate the height of the isosceles triangle using Pythagoras theorem
      double halfBase = baseLength / 2.0;
      double height = Math.sqrt(sideLength * sideLength - halfBase * halfBase);

      // Top vertex (apex of the isosceles triangle)
      int x1 = centerX;
      int y1 = centerY - (int) height;

      // Bottom-left vertex (left end of the base)
      int x2 = centerX - (baseLength / 2);
      int y2 = centerY;

      // Bottom-right vertex (right end of the base)
      int x3 = centerX + (baseLength / 2);
      int y3 = centerY;

      // Create the triangle using Line2D
      Line2D line1 = new Line2D.Double(x1, y1, x2, y2);
      Line2D line2 = new Line2D.Double(x2, y2, x3, y3);
      Line2D line3 = new Line2D.Double(x3, y3, x1, y1);

      Graphics2D g2 = (Graphics2D) g;

      // Draw the triangle
      g2.draw(line1);
      g2.draw(line2);
      g2.draw(line3);

      // Optional: Draw text
      Font font = new Font("Serif", Font.PLAIN, 24);
      g2.setFont(font);
      g.drawString("Isosceles Triangle", 100, 120);
   }
}
