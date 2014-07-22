package racing;

import java.awt.*;

public class Rect
{
   int x;
   int y;

   int w;
   int h;
   double vx;
   double vy;


   public Rect(int x, int y, int w, int h)
   {
      this.x = x;
      this.y = y;

      this.w = w;
      this.h = h;
      
      int dx = w/2;
      int dy = h/2;

      double mag = Math.sqrt(dx*dx + dy*dy);

      vx = dx / mag;
      vy = dy / mag;
   }
   
   /*public double distanceTo(int x, int y)
   {
      return (x - w) * vy - (y - h) * vx;
   }
*/
   public void moveBy(int dx, int dy)
   {
      x += dx;

      y += dy;
   }

   public void moveUpBy(int dy)
   {
      y -= dy;
   }

   public void moveDownBy(int dy)
   {
      y += dy;
   }


   public void moveLeftBy(int dx)
   {
      x -= dx;
   }

   public void moveRightBy(int dx)
   {
      x += dx;
   }





   public boolean hasCollidedWith(Rect r)
   {
      return
         (  x +   w >= r.x) &&
         (r.x + r.w >=   x) &&
         (r.y + r.h >=   y) &&
         (  y  +  h >= r.y);
   }

   public boolean hasCollidedRightWith(Rect r)
   {
	   return (  x +   w >= r.x);
   }
   
   public boolean hasCollidedLeftWith(Rect r)
   {
	   return  (r.x + r.w >=   x);
   }
   
   public boolean hasCollidedTopWith(Rect r)
   {
	   return (r.y + r.h >=   y);
   }
   
   public boolean hasCollidedBottomWith(Rect r)
   {
	   return (  y  +  h >= r.y);
   }

   public boolean contains(int mx, int my)
   {
       return (my < y+h) && (my > y) && (mx > x) && (mx < x+w);
   }


   public void draw(Graphics g)
   {
      g.drawRect(x, y, w, h);
   }

}