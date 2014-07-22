package racing;

import java.awt.*;





public class Cars extends Rect
{
   private static final int DN = 1;
   private static final int RT = 3;
	private static final int UP = 0;
	private static final int LT = 2;

   public Image image;
   private int dir = 0;
  // int newY = y - Camera.y;
  // int newX = x + Camera.x;
   
   public Cars(String name, int x, int y, int w, int h)
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
      //y = newY;
      dir = UP;
   }

   public void moveDownBy(int dy)
   {
      y += dy;
      
     // y = newY;
      dir =DN;
   }


   public void moveLeftBy(int dx)
   {
      x -= dx;
     // x = newX;
      dir = LT;
   }

   public void moveRightBy(int dx)
   {
      x += dx;
    //  x = newX;
      dir = RT;
   }



   public boolean hasCollidedWith(AICars carb)
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
   
   
   void handleCollision(Cars cara, AICars carb) 
   {
	   
	   
	   if(cara.hasCollidedWith(carb)  && dir == DN)
	   {
		  cara.y = carb.y - cara.h;
	   }
	   
	   if (cara.hasCollidedWith(carb)&& dir == RT)
	  	{
		   cara.x = carb.x - cara.w;			
		 }
	   
	   if (cara.hasCollidedWith(carb)&& dir == UP)
	   {
		   cara.y = carb.y + carb.h;
	   }
	   
	  
	if (cara.hasCollidedWith(carb)&& dir == LT)
	   {
		   cara.x = carb.x + carb.w;
	   }
	
   }

   
   public void draw(Graphics g)
   {
      g.drawImage(image, x, y, null);
   }
   }