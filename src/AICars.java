package racing;

import java.awt.*;
import java.util.ArrayList;






public class AICars extends Rect
{
   private static final int DN = 1;
   private static final int RT = 3;
	private static final int UP = 0;
	private static final int LT = 2;

   public Image image;
   private int dir = 0;
   
   
   public AICars(String name, int x, int y, int w, int h)
   {
	   super(x, y, w, h);
	  image = Toolkit.getDefaultToolkit().getImage("images/" + name);
   }

   public void moveBy(int dx, int dy)
   {
      x += dx;
      y += dy;
   }

   public void moveUpBy(int dy)
   {
      y -= dy;
      dir = UP;
   }

   public void moveDownBy(int dy)
   {
      y += dy;
      dir =DN;
   }


   public void moveLeftBy(int dx)
   {
      x -= dx;
      dir = LT;
   }

   public void moveRightBy(int dx)
   {
      x += dx;
      dir = RT;
   }



   public boolean hasCollidedWith(Cars carb)
   {
      return
         (  x +   w >= carb.x) &&
         (carb.x + carb.w >=   x) &&
         (carb.y + carb.h >=   y) &&
         (  y  +  h >= carb.y);
   }


   public boolean contains(int mx, int my)
   {
       return (my < y+h) && (my > y) && (mx > x) && (mx < x+w);
   }
   
   
   void handleCollision(AICars cara, Cars carb) 
   {
	   
	   
	   if(cara.hasCollidedWith(carb)  && dir == DN)
	   {
		  cara.y = carb.y- cara.h - 10;
	   }
	   
	   if (cara.hasCollidedWith(carb)&& dir == RT)
	  	{
		   cara.x = carb.x - cara.w - 10;			
		 }
	   
	   if (cara.hasCollidedWith(carb)&& dir == UP)
	   {
		   cara.y = carb.y + carb.h - 10;
	   }
	   
	  
	if (cara.hasCollidedWith(carb)&& dir == LT)
	   {
		   cara.x = carb.x + carb.w - 10;
	   }
	
   }

   public void draw(Graphics g)
   {
	   //int enemy = 500;
	   g.drawImage(image, x, y, null);
	  
   }

}