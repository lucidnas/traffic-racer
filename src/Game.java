package racing;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;



public class Game extends Applet implements KeyListener, Runnable
{
	boolean[] pressed = new boolean[256];

   	Thread timer;
   
   	static int winW = 1200;
   	static int winH = 700;
   	int imageW = 1200;
   	int imageH = 700;
   	Road road; 
   	Grass grass;
   //Grass grass2;
   	Trees tree;
   	Houses houses;
   	Houses water;
   	ImageLoader finish;
   	ImageLoader logo;
   	Cars nice;
   	AICars[] ai1;
   	AICars[] ai2;
   	Line line = new Line (315, 10, 315, 20);
   	Line line1 = new Line (725, 10, 725, 20);
   	Image offScreen;
   	Graphics offScreen_g;
   	Sound sound;
   	private int fps = 75;
   
   
   	AudioClip startup;
   	AudioClip moving;
   	AudioClip collision;
   	AudioClip gameover;
   
   	boolean left      = false;
   	boolean right     = false;

   	private boolean finished;

	public int vel = 15;

	private int avel = 2;

	private AICars[] white;
	private AICars[] blue;
	private AICars[] truck;
	//private AICars[] sportred;
	private AICars[] green;
	private Trees bush1;
	private Trees bush2;
	private Trees bush3;
	private Trees bush4;
	private int wvel =  1;
	private int bvel = 2;
	private int tvel =3;
	private int svel = 4;
	private int gvel = 5;

	private Houses carPark;

	long sleep = (long) System.currentTimeMillis(); 
   
	public static final int waittime = 10;
   
   
	public void initialize() {
		
		 loadAICars();
	     
	     loadImages();
	     loadSounds();
		
	}
     
   	public void init()
   {
      setSize(winW, winH);
     
      
      initialize();
      offScreen = createImage(winW, winH);
      offScreen_g = offScreen.getGraphics();
      requestFocus();
      addKeyListener(this);
      timer = new Thread(this);

     timer.start();
   }

	public void loadSounds() 
	 {
	
		 startup   = getAudioClip (getCodeBase(), "sounds/themes.wav");
	     moving   = getAudioClip (getCodeBase(), "sounds/running.wav");
	     collision   = getAudioClip (getCodeBase(), "sounds/car_crash.wav");//
	     gameover = getAudioClip (getCodeBase(), "sounds/gameover.ogg");
		
	}
	
	public void loadImages() 
	{
		
		 road = new Road("road.png", 300, 700);
		   grass = new Grass("grasstile.png", 800, 0, 0);
		    //grass2 = new Grass("grasstile.png", 0, 0, 0);
		    houses = new Houses("house_1.png",800, 0, 0);
		    tree = new Trees("treetile.png",200, 700,1000);
		    bush1 = new Trees("treetile.png", 150, 700, 500);
		    bush2 = new Trees("treetile.png", 100, 700, 500);
		    bush3 = new Trees("treetile.png", 50, 700, 500);
		    bush4 = new Trees("treetile.png", 0, 700, 500);
		    
		   water = new Houses("water.png", 0, 0,5);
		   carPark = new Houses("blueSide.png", 820,150, 5);
		   finish = new ImageLoader("finish.png", 325, -40000, 0);
		  nice = new Cars("nice.png",550, 500, 70, 120);
		
	}
	
	public void loadAICars() {
	   white =  new AICars[20];
	   blue = new AICars[20];
	   truck = new AICars[7];
	  // sportred = new AICars[20];
	   green = new AICars [20];
	   
	   
	   String[] image = {"white.gif", "truck.png", "sportblue.gif", "blacks.png", "sportred.gif",
			   			 "white.gif", "blue.png", "sportblue.gif", "blacks.png", "sportred.gif",
			   			 "white.gif", "blue.png", "sportblue.gif", "blacks.png", "sportred.gif",
			   			 "white.gif", "blue.png", "sportblue.gif", "blacks.png", "sportred.gif",};
	   
	   for(int i = 0; i < white.length; i++) {white[i] = new AICars("white.gif",350, -700 - i * 1700, 60, 120);}
	   
	   for(int i = 0; i < blue.length; i++) {blue[i] = new AICars("blue.gif",450, 1800 - i * 1400, 50, 120);}
	   
	   for(int i = 0; i < truck.length; i++) {truck[i] = new AICars("truck.png",570, -600 - i * 2000, 120, 230);}
	   
	   //for(int i = 0; i < sportred.length; i++) {sportred[i] = new AICars("sportred.gif",450, -1200 - i * 700, 50, 100);}
	   
	   for(int i = 0; i < green.length; i++) {green[i] = new AICars("green.gif",690, -100 - i * 3000, 50, 120);}
	   
}

  /*public void start()
   {
      timer.start();
   }

   //-------------------------------------------------------------------------//

   public void stop()
   {
      finished = true; //timer.stop();
   }*/

	public void preGameLoop () 
{
	logo = new ImageLoader("logo.jpg", 300,500,0);
	Graphics g = getGraphics();
	logo.draw(g);
	
}

	public void run()
   {
	  startup.loop();
	  
	  
	  preGameLoop();
	  // wait(100);
	   
      while(true)
      {
    	  
    	  inGameLoop();
          
         // aiCarsUpMovement();
         // aiCarsDownMovement(avel);
          
          //
    	 // Camera.moveUpBy(1);
        
         
          
         long time = (long) System.currentTimeMillis(); 
         
        
         repaint();
         
         wait (waittime);
         //  delay for each frame  -   time it took for one frame 
         
         
        /* time = (1000 / fps ) - (System.currentTimeMillis() - time); 
         
          if (time > 0) 
         { 
                 try 
                 { 
                         Thread.sleep(time); 
                 } 
                 catch(Exception e){} 
         } 
         */

         
      }
      	
   }

	// Handles Player input, collisons and changes moving object's position   
	public final void inGameLoop()
   {
      applyPlayerInput();

      moveGameObjectsToNextPosition();

      handleCollisions();
   }
  
   //Handles collision of player's car to the side of the road and player's car to AICars
	public void handleCollisions() 
	   {

		      double dist = 0, adj = 0;
		       dist = line.distanceTo(nice.x,nice.y);
		      if (dist <= 0)
		  		{
		  			adj = (0 - dist);
		  		
		  			nice.x += (int)(adj * Line.vy);
		  			nice.y -= (int)(adj * Line.vx);
		  			
		  			//collision.play();
		  		}
		      
		        dist = line1.distanceTo(nice.x,nice.y);
		        if (dist >= 0)
		  		{
		  			 adj = (0 - dist);
		  		
		  			nice.x += (int)(adj * Line.vy);
		  			nice.y -= (int)(adj * Line.vx);
		  			
		  			
		  			//collision.play();
		  			
		  		}
		       
		       for(int i = 0; i < white.length; i++)
		       {
		    	   if (white[i].hasCollidedWith(nice))
		    	   { 
		    		   white[i].handleCollision(white[i], nice);
		    		   startup.stop();
		    		   vel = 0;
		    		  wvel =  0;
		    		   bvel = 0;
		    		  tvel = 0;
		    		   svel =  0;
		    		   gvel = 0;
			    	   	Camera.moveUpBy(vel);
			    	  	aiCarsDownMovement();
			    	   	 collision.play(); 
			    	   	 
			    	   	 ///wait(60);
		    	   }
		       }
		       
		       for(int i = 0; i < blue.length; i++)
		       {
		    	   if (blue[i].hasCollidedWith(nice))
		    	   { 
		    		   blue[i].handleCollision(blue[i], nice);
		    		   startup.stop();
		    		   vel = 0;
		    		  
			    		  wvel =  0;
			    		   bvel = 0;
			    		  tvel = 0;
			    		   svel =  0;
			    		   gvel = 0;
			    	   	Camera.moveUpBy(vel);
			    	  	aiCarsDownMovement();
			    	   	 collision.play(); 
		    	   }
		       }
		       
		       for(int i = 0; i < truck.length; i++)
		       {
		    	   if (truck[i].hasCollidedWith(nice))
		    	   { 
		    		   truck[i].handleCollision(truck[i], nice);
		    		   startup.stop();
		    		   vel = 0;
		    		   
			    		  wvel =  0;
			    		   bvel = 0;
			    		  tvel = 0;
			    		   svel =  0;
			    		   gvel = 0;
			    	   	Camera.moveUpBy(vel);
			    	  	aiCarsDownMovement();
			    	   	 collision.play(); 
		    	   }
		       }
		       
		       /*for(int i = 0; i < sportred.length; i++)
		       {
		    	   if (sportred[i].hasCollidedWith(nice))
		    	   { 
		    		   sportred[i].handleCollision(sportred[i], nice);
		    		   startup.stop();
		    		   vel = 0;
			    		  wvel =  0;
			    		   bvel = 0;
			    		  tvel = 0;
			    		   svel =  0;
			    		   gvel = 0;
			    	   	Camera.moveUpBy(vel);
			    	  	//aiCarsDownMovement();
			    	   	 collision.play(); 
		    	   }
		       }
*/		       
		       for(int i = 0; i < green.length; i++)
		       {
		    	   if (green[i].hasCollidedWith(nice)){ green[i].handleCollision(green[i], nice); startup.stop();
		    	   vel = 0;
		    		  wvel =  0;
		    		   bvel = 0;
		    		  tvel = 0;
		    		   svel =  0;
		    		   gvel = 0;
		    	   	 Camera.moveUpBy(vel);
		    	   	aiCarsDownMovement();
		    	   	//Camera.y = 0;
		    	   	 collision.play();
		    	   }
		       }
		
		
	}


// Movement of each AI Cars.

	public void aiCarsDownMovement() {
		
		   for(int i = 0; i < white.length; i++) {white[i].moveDownBy(wvel);}
		   for(int i = 0; i < blue.length; i++) {blue[i].moveDownBy(bvel);}
		   for(int i = 0; i < truck.length; i++) {truck[i].moveDownBy(tvel);}
		 ///  for(int i = 0; i < sportred.length; i++) {sportred[i].moveDownBy(svel);}
		   for(int i = 0; i < green.length; i++) {green[i].moveDownBy(gvel);}
		  // for(int i = 0; i < ai2.length; i++) {ai2[i].moveDownBy(avel);}
		
		   
	}

	/*public void aiCarsUpMovement() {
	
	   for(int i = 0; i < ai1.length; i++) {ai1[i].moveUpBy(2);}
	   for(int i = 0; i < ai2.length; i++) {ai2[i].moveUpBy(2);}
}*/

	public void moveGameObjectsToNextPosition() {
	aiCarsDownMovement();
	//aiCarsUpMovement();
	
}

	public void applyPlayerInput() {
	 
	
   // if(pressed[KeyEvent.VK_UP])     nice.moveUpBy(10);
  //  if(pressed[KeyEvent.VK_DOWN])   nice.moveDownBy(10);
    if(pressed[KeyEvent.VK_LEFT])   {nice.moveLeftBy(3);}
    if(pressed[KeyEvent.VK_RIGHT])  {nice.moveRightBy(3);}		 
    if(pressed[KeyEvent.VK_UP] )   {Camera.moveUpBy(vel);} 
/*    if(pressed[KeyEvent.VK_UP] == false )   
    { 
    	for (int i = 0; i < white.length; i++) 
    		if (white[i].hasCollidedWith(nice)){ Camera.moveUpBy(0);}
    	
    	Camera.moveUpBy(0);}
    	*/
    
    
    if(pressed[KeyEvent.VK_DOWN]) {Camera.moveDownBy(4);}
}

	public void wait(int miliseconds)
	{
		try
		{
      Thread.sleep(miliseconds);
		}catch (InterruptedException ie){}
}

	public void paint(Graphics g)
	{
    //line.draw(g);
    //line1.draw(g);
	
		drawObjects(g);
	
		drawAICars(g);
	   
		
	
	}

	public void drawObjects(Graphics g) 
	{

	   grass.draw(g); 
	  // grass2.draw(g);
	   tree.draw(g);
	   bush1.draw(g);
	   bush2.draw(g);
	   bush3.draw(g);
	   bush4.draw(g);
	   houses.draw(g);
	   road.draw(g);
	   finish.draw(g);
	   nice.draw(g);
	   carPark.draw(g);
	 //  water.draw(g);
	
	}

	public void drawAICars(Graphics g) 
	{
	   for(int i = 0; i < white.length; i++){ white[i].draw(g);}
	   for(int i = 0; i < blue.length; i++){ blue[i].draw(g);}
	   for(int i = 0; i < truck.length; i++){ truck[i].draw(g);}
	 //  for(int i = 0; i < sportred.length; i++){ sportred[i].draw(g);}
	   for(int i = 0; i < green.length; i++){ green[i].draw(g);}
	   //for(int i = 0; i < ai2.length; i++){ ai2[i].draw(g);}
	   
	   
	
	}
   
	public void update(Graphics g)
   {
      offScreen_g.clearRect(0, 0, winW, winH);
      paint(offScreen_g);
      g.drawImage(offScreen, 0, 0, null);
     // moving.play();
 
   }
      
	public void keyPressed(KeyEvent e){pressed[e.getKeyCode()] = true;}
	public void keyReleased(KeyEvent e) {pressed[e.getKeyCode()] = false;}
	public void keyTyped(KeyEvent e){}


}