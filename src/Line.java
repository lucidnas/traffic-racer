package racing;

import java.awt.*;

public class Line
{
   public int x1;
   public int y1;

   public int x2;
   public int y2;

   public static double vx;
   public static double vy;



   public Line(int x1, int y1, int x2, int y2)
   {
      this.x1 = x1;
      this.y1 = y1;

      this.x2 = x2;
      this.y2 = y2;


      int dx = x2 - x1;
      int dy = y2 - y1;

      double mag = Math.sqrt(dx*dx + dy*dy);

      vx = dx / mag;
      vy = dy / mag;
   }


   public double distanceTo( int x,int  y)
   {
      return (x - x1) * vy - (y - y1) * vx;
   }


   public void draw(Graphics g)
   {
      g.drawLine(x1, y1, x2, y2);
   }


}